package fr.dark.condensator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Condensator extends JavaPlugin implements Listener {
	
	private static Condensator instance;
	
	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public static Condensator getInstance() {
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	public static boolean canCondense(ItemStack it) {
		switch(it.getType()) {
		case COAL:
			return true;
		case REDSTONE:
			return true;
		case INK_SACK:
			if(it.getData().getData() == 4) {
				return true;
			}else {
				return false;
			}
		case IRON_INGOT:
			return true;
		case GOLD_INGOT:
			return true;
		case DIAMOND:
			return true;
		case EMERALD:
			return true;
		case SLIME_BALL:
			return true;
		case SNOW_BALL:
			return true;
		default:
			return false;
		}
	}
	
	public static int getAmount(Material mat) {
		switch(mat) {
		case COAL:
			return (int) 9;
		case REDSTONE:
			return (int) 9;
		case INK_SACK:
			return (int) 9;
		case IRON_INGOT:
			return (int) 9;
		case GOLD_INGOT:
			return (int) 9;
		case DIAMOND:
			return (int) 9;
		case EMERALD:
			return (int) 9;
		case SLIME_BALL:
			return (int) 9;
		case SNOW_BALL:
			return (int) 4;
		default:
			return (int) 0;
		}
	}
	
	public static ItemStack getResult(Material mat, int amount) {
		switch(mat) {
		case COAL:
			return new ItemStack(Material.COAL_BLOCK, amount);
		case REDSTONE:
			return new ItemStack(Material.REDSTONE_BLOCK, amount);
		case INK_SACK:
			return new ItemStack(Material.LAPIS_BLOCK, amount);
		case IRON_INGOT:
			return new ItemStack(Material.IRON_BLOCK, amount);
		case GOLD_INGOT:
			return new ItemStack(Material.GOLD_BLOCK, amount);
		case DIAMOND:
			return new ItemStack(Material.DIAMOND_BLOCK, amount);
		case EMERALD:
			return new ItemStack(Material.EMERALD_BLOCK, amount);
		case SLIME_BALL:
			return new ItemStack(Material.SLIME_BLOCK, amount);
		case SNOW_BALL:
			return new ItemStack(Material.SNOW_BLOCK, amount);
		default:
			return null;
		}
	}
	
	public static void condenseType(Player p, int amount, Material mat) {
			int nbrBlock = amount / getAmount(mat);
			int rest = amount - (nbrBlock*getAmount(mat));
			if(rest == 0) {
				p.getInventory().addItem(getResult(mat, nbrBlock));
				p.updateInventory();
			}else {
				if(mat == Material.INK_SACK) {
					p.getInventory().addItem(new ItemStack(mat, rest, (byte)4));
				}else {
					p.getInventory().addItem(new ItemStack(mat, rest));
				}
				p.getInventory().addItem(getResult(mat, nbrBlock));
				p.updateInventory();
			}
	}
	
	public static void condenseSlot(Player p, int slot) {
		if(canCondense(p.getInventory().getItem(slot))) {
			ItemStack it = p.getInventory().getItem(slot);
			int nbrBlock = it.getAmount() / getAmount(it.getType());
			int rest = it.getAmount() - (nbrBlock*getAmount(it.getType()));
			if(rest == 0) {
				p.getInventory().setItem(slot, new ItemStack(Material.AIR));
				p.getInventory().addItem(getResult(it.getType(), nbrBlock));
				p.updateInventory();
			}else {
				if(p.getInventory().firstEmpty() != -1) {
					p.getInventory().setItem(slot, new ItemStack(Material.AIR));
					p.updateInventory();
					p.getInventory().addItem(getResult(it.getType(), nbrBlock));
					p.getInventory().addItem(new ItemStack(it.getType(), rest));
				}else {
					p.sendMessage("§cVous n'avez plus de place dans votre inventaire !");
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if(e.getMessage().equalsIgnoreCase("/condense")) {
			if(p.hasPermission("condensator.use")) {
				if(p.getInventory().firstEmpty() != -1) {
					for(int y = 0; y < 3; y++) {
						Map<Material, Integer> items = new HashMap<>();
						for(int i = 0; i < 36; i++) {
							if(p.getInventory().getItem(i) != null) {
								if(Condensator.canCondense(p.getInventory().getItem(i))) {
									if(items.containsKey(p.getInventory().getItem(i).getType())) {
										int amount = items.get(p.getInventory().getItem(i).getType());
										items.remove(p.getInventory().getItem(i).getType());
										items.put(p.getInventory().getItem(i).getType(), amount + p.getInventory().getItem(i).getAmount());
										p.getInventory().setItem(i, new ItemStack(Material.AIR));
										p.updateInventory();
									}else {
										items.put(p.getInventory().getItem(i).getType(), p.getInventory().getItem(i).getAmount());
										p.getInventory().setItem(i, new ItemStack(Material.AIR));
										p.updateInventory();
									}
								}
							}
						}
						for(Entry<Material, Integer> entries : items.entrySet()) {
							Condensator.condenseType(p, entries.getValue(), entries.getKey());
						}
					}
					e.setCancelled(true);
				}else {
					p.sendMessage("§cTon inventaire est full, ça peut causer des problèmes !");
				}
			}else {
				p.sendMessage("§cT'as pas les perms !");
			}
		}
	}

}
