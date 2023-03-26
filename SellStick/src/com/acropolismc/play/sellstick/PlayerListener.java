package com.acropolismc.play.sellstick;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.acropolismc.play.sellstick.Configs.PriceConfig;
import com.acropolismc.play.sellstick.Configs.StickConfig;
import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;

import net.milkbowl.vault.economy.EconomyResponse;

public class PlayerListener implements Listener {
	
	private SellStick plugin;

	public PlayerListener(SellStick plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("unused")
	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public boolean isInfinite(List<String> lores) {
		if (lores.get(1).equalsIgnoreCase(StickConfig.instance.infiniteLore))
			return true;
		return false;
	}

	public int getUsesFromLore(List<String> lores) {
		String found = "";
		for (int i = 0; i < lores.get(1).length(); i++) {
			if (Character.isDigit(lores.get(1).charAt(i))) {
				if (i != 0) {
					if (lores.get(1).charAt(i - 1) != ChatColor.COLOR_CHAR) {
						found += lores.get(1).charAt(i);
					} else {
						found += "-";
					}
				} else {
					found += lores.get(1).charAt(i);
				}
			} else {
				found += "-";
			}
		}
		String[] split = found.split("-");
		List<Integer> hold = new ArrayList<Integer>();
		for (int i = 0; i < split.length; i++) {
			if (isNumeric(split[i])) {
				hold.add(Integer.parseInt(split[i]));
			}
		}
		int min = hold.get(0);
		for (int i = 0; i < hold.size(); i++) {
			if (hold.get(i) < min) {
				min = hold.get(i);
			}
		}
		return min;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onUse(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Material sellItem = Material.getMaterial(StickConfig.instance.item.toUpperCase());
		if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (p.getInventory().getItemInMainHand().getType() == sellItem) {
				if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() != null && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(StickConfig.instance.name)) {
					if (e.getClickedBlock().getType() == Material.CHEST || e.getClickedBlock().getType() == Material.TRAPPED_CHEST) {

						if (!p.hasPermission("sellstick.use")) {
							p.sendMessage(StickConfig.instance.prefix + StickConfig.instance.noPerm);
							return;
						}

						Location location = e.getClickedBlock().getLocation();

						if (StickConfig.instance.onlyOwn) {
							if (plugin.skyblock) {
								Island island = null;
								island = ASkyBlockAPI.getInstance().getIslandAt(location);
								
								if(p.getWorld().getName().equalsIgnoreCase(StickConfig.instance.sbWorldName) || p.getWorld().getName().equalsIgnoreCase(StickConfig.instance.sbNetherName)) {
									if (!island.getMembers().contains(p.getUniqueId())) {
										p.sendMessage(StickConfig.instance.territoryMessage);
										e.setCancelled(true);
										return; 
									}
								} else {
									p.sendMessage(StickConfig.instance.wrongWorld);
								}
							}
						}

						ItemStack is = p.getInventory().getItemInMainHand();
						ItemMeta im = is.getItemMeta();

						
						List<String> lores = im.getLore();
						int uses = -1;
						if (!isInfinite(lores)) {
							uses = getUsesFromLore(lores);
						}

						InventoryHolder c = (InventoryHolder) e.getClickedBlock().getState();
						ItemStack[] contents = (ItemStack[]) c.getInventory().getContents();
						
						double total = 0;
						double slotPrice = 0;
						double price = 0;
						for (int i = 0; i < c.getInventory().getSize(); i++) {
							try {
								if(!StickConfig.instance.useEssentialsWorth) {
									for (String key : PriceConfig.instance.getConfig().getConfigurationSection("prices").getKeys(false)) {

										int data;
										String name;

										if (!key.contains(":")) {
											data = 0;
											name = key;
										} else {
											name = (key.split(":"))[0];
											data = Integer.parseInt(key.split(":")[1]);
										}

										
										if ((contents[i].getType().toString().equalsIgnoreCase(name) || (isNumeric(name) && contents[i].getType().getId() == Integer.parseInt(name))) && contents[i].getDurability() == data) {
											price = Double.parseDouble(PriceConfig.instance.getConfig().getString("prices." + key));
										}
									}
								} else {
									price = plugin.ess.getWorth().getPrice(contents[i]).doubleValue();
								}
								
								int amount = (int) contents[i].getAmount();
								
								slotPrice = price * amount;
								if (slotPrice > 0) {
									ItemStack sell = contents[i];

									c.getInventory().remove(sell);
									e.getClickedBlock().getState().update();
								}
								
							} catch (NullPointerException ex) {

							}
							total += slotPrice;
							slotPrice = 0;
							price = 0;
						}
						if (total > 0) {
							if (!isInfinite(lores)) {
								lores.set(1, lores.get(1).replaceAll(uses + "", (uses - 1) + ""));
								im.setLore(lores);
								is.setItemMeta(im);
							}

							EconomyResponse r = plugin.getEcon().depositPlayer(p, total);
							if (r.transactionSuccess()) {
								if(StickConfig.instance.sellMessage.contains("\\n")) {
									String[] send = StickConfig.instance.sellMessage.split("\\\\n");
									for(String msg: send) {
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar {\"text\":\"" + msg.replace("%balance%", plugin.getEcon().format(r.balance)).replace("%price%", plugin.getEcon().format(r.amount)) + "\"}");
									}
								}else {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar {\"text\":\"" + StickConfig.instance.sellMessage.replace("%balance%", plugin.getEcon().format(r.balance)).replace("%price%", plugin.getEcon().format(r.amount)) + "\"}");
								}
								System.out.println(p.getName() + " a vendu des items via le sellstick " + r.amount + " et a maintenant " + r.balance);
							} else {
								p.sendMessage(String.format("Une erreur est apparue : %s", r.errorMessage));
							}

							if (uses - 1 == 0) {
								p.getInventory().remove(p.getItemInHand());
								p.updateInventory();
								p.sendMessage(StickConfig.instance.brokenStick);
							}

						} else {
							p.sendMessage(StickConfig.instance.nothingWorth);
						}						
					}
				}
			}
		}
	}
}
