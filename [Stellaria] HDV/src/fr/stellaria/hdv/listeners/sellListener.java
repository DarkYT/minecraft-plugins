package fr.stellaria.hdv.listeners;

import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;

import fr.stellaria.hdv.Core;
import fr.stellaria.hdv.api.ScrollerInventory;

public class sellListener implements Listener {

	Core core;

	public sellListener(Core core) {
		this.core = core;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		Player p = (Player) e.getWhoClicked();

		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fPage suivante"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);

		if (inv.getName().contains("§8Hôtel")) {
			e.setCancelled(true);
			if(e.getCurrentItem().getType().equals(Material.AIR)){
				p.sendMessage("§cTu ne peux pas acheter du vide enfin !");
				return;
			}
			List<String> lores = e.getCurrentItem().getItemMeta().getLore();
			for (String sellers : core.getConfig().getConfigurationSection("Sells").getKeys(false)) {
				for (String items : core.getConfig().getConfigurationSection("Sells." + sellers).getKeys(false)) {
					for (int i = 0; i < 5001; i++) {
						if(!e.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.previousPageName) || !e.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.nextPageName)){
							if (lores.get(0).contains("(#" + i + ")")) {
								if (i == core.getConfig().getInt("Sells." + sellers + "." + items + ".hastag")) {
									System.out.println("YES " + i);
									int price = core.getConfig().getInt("Sells." + sellers + "." + items + ".price");
									ItemStack money = null;
									ItemStack remove = null;
									if (core.getConfig().getString("Sells." + sellers + "." + items + ".money")
											.equals("péridots")) {
										ItemStack peridot = new ItemStack(Material.FIREWORK_CHARGE, 1);
										ItemMeta meta3 = peridot.getItemMeta();
										FireworkEffectMeta metaFw3 = (FireworkEffectMeta) meta3;
										FireworkEffect effect3 = FireworkEffect.builder().withColor(Color.LIME).build();
										metaFw3.setEffect(effect3);
										meta3.setDisplayName("§a§lPéridot");
										peridot.setItemMeta(metaFw3);

										ItemStack peridot1 = new ItemStack(Material.FIREWORK_CHARGE, price);
										ItemMeta meta31 = peridot1.getItemMeta();
										FireworkEffectMeta metaFw31 = (FireworkEffectMeta) meta31;
										FireworkEffect effect31 = FireworkEffect.builder().withColor(Color.LIME).build();
										metaFw31.setEffect(effect31);
										meta31.setDisplayName("§a§lPéridot");
										peridot1.setItemMeta(metaFw31);

										money = peridot;
										remove = peridot1;
									} else if (core.getConfig().getString("Sells." + sellers + "." + items + ".money")
											.equals("saphirs")) {
										ItemStack saphir = new ItemStack(Material.FIREWORK_CHARGE, 1);
										ItemMeta meta2 = saphir.getItemMeta();
										FireworkEffectMeta metaFw2 = (FireworkEffectMeta) meta2;
										FireworkEffect effect2 = FireworkEffect.builder().withColor(Color.BLUE).build();
										metaFw2.setEffect(effect2);
										meta2.setDisplayName("§1§lSaphir");
										saphir.setItemMeta(metaFw2);

										ItemStack peridot1 = new ItemStack(Material.FIREWORK_CHARGE, price);
										ItemMeta meta31 = peridot1.getItemMeta();
										FireworkEffectMeta metaFw31 = (FireworkEffectMeta) meta31;
										FireworkEffect effect31 = FireworkEffect.builder().withColor(Color.BLUE).build();
										metaFw31.setEffect(effect31);
										meta31.setDisplayName("§1§lSaphir");
										peridot1.setItemMeta(metaFw31);

										money = saphir;
										remove = peridot1;
									} else if (core.getConfig().getString("Sells." + sellers + "." + items + ".money")
											.equals("rubis")) {
										ItemStack rubis = new ItemStack(Material.FIREWORK_CHARGE, 1);
										ItemMeta meta = rubis.getItemMeta();
										FireworkEffectMeta metaFw = (FireworkEffectMeta) meta;
										FireworkEffect effect = FireworkEffect.builder().withColor(Color.RED).build();
										metaFw.setEffect(effect);
										meta.setDisplayName("§4§lRubis");
										rubis.setItemMeta(metaFw);

										ItemStack peridot1 = new ItemStack(Material.FIREWORK_CHARGE, price);
										ItemMeta meta31 = peridot1.getItemMeta();
										FireworkEffectMeta metaFw31 = (FireworkEffectMeta) meta31;
										FireworkEffect effect31 = FireworkEffect.builder().withColor(Color.RED).build();
										metaFw31.setEffect(effect31);
										meta31.setDisplayName("§4§lRubis");
										peridot1.setItemMeta(metaFw31);

										money = rubis;
										remove = peridot1;
									}

									if (p.getInventory().containsAtLeast(money, price)) {
										core.giveItem("Sells." + sellers + "." + items + ".item", p);
										YamlConfiguration yc = YamlConfiguration.loadConfiguration(Core.confFile);
										yc.set("Rewards."
												+ core.getConfig().getString("Sells." + sellers + "." + items + ".seller")
												+ ".item"
												+ core.getConfig().getInt("Sells." + sellers + "." + items + ".hastag")
												+ ".price",
												core.getConfig().getInt("Sells." + sellers + "." + items + ".price"));
										yc.set("Rewards."
												+ core.getConfig().getString("Sells." + sellers + "." + items + ".seller")
												+ ".item"
												+ core.getConfig().getInt("Sells." + sellers + "." + items + ".hastag")
												+ ".money",
												core.getConfig().getString("Sells." + sellers + "." + items + ".money"));
										try {
											yc.save(Core.confFile);
										} catch (IOException e1) {
											e1.printStackTrace();
										}
										core.getConfig().set("Sells." + sellers + "." + items, null);
										p.getInventory().removeItem(remove);
										p.updateInventory();
										core.saveConfig();
										p.closeInventory();
										core.opSell(p);
										break;
									} else {
										p.sendMessage("§cTu n'as pas assez d'argent !");
									}
									break;
								}
							}else{
								if (!ScrollerInventory.users.containsKey(p.getUniqueId()))
									return;
								ScrollerInventory inv1 = ScrollerInventory.users.get(p.getUniqueId());
								if (e.getCurrentItem() == null)
									return;
								if (e.getCurrentItem().getItemMeta() == null)
									return;
								if (e.getCurrentItem().getItemMeta().getDisplayName() == null)
									return;
								// If the pressed item was a nextpage button
								if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.nextPageName)) {
									e.setCancelled(true);
									// If there is no next page, don't do anything
									if (inv1.currpage >= inv1.pages.size() - 1) {
										return;
									} else {
										// Next page exists, flip the page
										inv1.currpage += 1;
										p.openInventory(inv1.pages.get(inv1.currpage));
									}
									// if the pressed item was a previous page button
								} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.previousPageName)) {
									e.setCancelled(true);
									// If the page number is more than 0 (So a previous page exists)
									if (inv1.currpage > 0) {
										// Flip to previous page
										inv1.currpage -= 1;
										p.openInventory(inv1.pages.get(inv1.currpage));
									}
								}
							}
						}
					}
				}
			}
		}

		if (!ScrollerInventory.users.containsKey(p.getUniqueId()))
			return;
		ScrollerInventory inv1 = ScrollerInventory.users.get(p.getUniqueId());
		if (e.getCurrentItem() == null)
			return;
		if (e.getCurrentItem().getItemMeta() == null)
			return;
		if (e.getCurrentItem().getItemMeta().getDisplayName() == null)
			return;
		// If the pressed item was a nextpage button
		if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.nextPageName)) {
			e.setCancelled(true);
			// If there is no next page, don't do anything
			if (inv1.currpage >= inv1.pages.size() - 1) {
				return;
			} else {
				// Next page exists, flip the page
				inv1.currpage += 1;
				p.openInventory(inv1.pages.get(inv1.currpage));
			}
			// if the pressed item was a previous page button
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.previousPageName)) {
			e.setCancelled(true);
			// If the page number is more than 0 (So a previous page exists)
			if (inv1.currpage > 0) {
				// Flip to previous page
				inv1.currpage -= 1;
				p.openInventory(inv1.pages.get(inv1.currpage));
			}
		}
		
		if(inv.getName().equals("§8Suppression du villageois")){
			Entity entity = core.entities.get(1);
			if(e.getCurrentItem().getType().equals(Material.WOOL)){
				System.out.println(1);
				if(e.getCurrentItem().getData().getData() == 13){
					System.out.println(2);
					e.getWhoClicked().closeInventory();
					entity.remove();
					e.getWhoClicked().sendMessage("§aLe PNJ a bien été supprimé !");
				}else if(e.getCurrentItem().getData().getData() == 14){
					System.out.println(3);
					p.closeInventory();
					p.sendMessage("§cVous avez annulé l'opération !");
				}
			}
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e){
		if (e.getInventory().getName().contains("§8Hôtel des Ventes")) {
			if (e.getPlayer() instanceof Player) {
				Player p = (Player) e.getPlayer();
				if(core.watchs.containsKey(p)){
					core.watchs.remove(p);
				}
				core.itemsloaded.clear();
			}
		}
	}
	
	@EventHandler
	public void  VillagerInteract(PlayerInteractEntityEvent e){
		Player player = e.getPlayer();
		Entity target = e.getRightClicked();
		
		if(target instanceof Villager){
			Villager villager = (Villager) target;
			if(villager.getCustomName().equals("§8Hôtel des Ventes")){
				e.setCancelled(true);
				if(player.isSneaking()){
					if(player.isOp()){
						core.entities.put(1, target);
						core.openConfig(player);
					}
				}else{
					Bukkit.dispatchCommand(player, "hdv");
				}
			}
		}
	}
	
	@EventHandler
	public void onOpen(InventoryOpenEvent e){
		if(e.getPlayer() instanceof Player){
			if(e.getInventory().getName().contains("§8Hôtel des Ventes")){
				Player p = (Player) e.getPlayer();
				core.watchs.put(p, "Oppened");
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Villager){
			Villager villager = (Villager) e.getEntity();
			if(villager.getCustomName().equals("§8Hôtel des Ventes")){
				e.setCancelled(true);
			}
		}
	}
	
}
