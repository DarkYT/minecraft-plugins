package fr.shiick.recycleur.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import fr.shiick.recycleur.Recycleur;
import net.md_5.bungee.api.ChatColor;

public class recyclerInventoryEvent implements Listener {

	Recycleur recycleur;

	public recyclerInventoryEvent(Recycleur recycleur) {
		this.recycleur = recycleur;
	}

	public String colorMessage(String message) {
		String msg = ChatColor.translateAlternateColorCodes('&', message);
		return msg;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(colorMessage(recycleur.getConfig().getString("Recycleur.Name")))) {
			switch (e.getCurrentItem().getType()) {
			case STAINED_GLASS_PANE:
				e.setCancelled(true);
				switch(e.getRawSlot()) {
				case 22:
					ItemStack item = e.getInventory().getItem(13);
					if(!(item == null || item.getType() == Material.AIR)) {
						String type = recycleur.getType(item.getType());
						if(item.getItemMeta().hasLore()){
							if(type.equalsIgnoreCase("shovel")) {
								recycleur.recycleItem("shovel", item.getType(), item.getDurability(), item.getItemMeta().getLore(), p);
							} else if(type.equalsIgnoreCase("hoe")) {
								recycleur.recycleItem("hoe", item.getType(), item.getDurability(), item.getItemMeta().getLore(), p);
							} else if(type.equalsIgnoreCase("sword")) {
								recycleur.recycleItem("sword", item.getType(), item.getDurability(), item.getItemMeta().getLore(), p);
							} else if(type.equalsIgnoreCase("axe")) {
								recycleur.recycleItem("axe", item.getType(), item.getDurability(), item.getItemMeta().getLore(), p);
							} else if(type.equalsIgnoreCase("pickaxe")) {
								recycleur.recycleItem("pickaxe", item.getType(), item.getDurability(), item.getItemMeta().getLore(), p);
							} else if(type.equalsIgnoreCase("helmet")) {
								recycleur.recycleItem("helmet", item.getType(), item.getDurability(), item.getItemMeta().getLore(), p);
							} else if(type.equalsIgnoreCase("chestplate")) {
								recycleur.recycleItem("chestplate", item.getType(), item.getDurability(), item.getItemMeta().getLore(), p);
							} else if(type.equalsIgnoreCase("leggings")) {
								recycleur.recycleItem("leggings", item.getType(), item.getDurability(), item.getItemMeta().getLore(), p);
							} else if(type.equalsIgnoreCase("boots")) {
								recycleur.recycleItem("boots", item.getType(), item.getDurability(), item.getItemMeta().getLore(), p);
							} else if(type.equalsIgnoreCase("misc")) {
								recycleur.recycleItem("misc", item.getType(), item.getDurability(), item.getItemMeta().getLore(), p);
							} else {
								p.sendMessage(colorMessage(recycleur.getConfig().getString("Recycleur.Invalid")));
								p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
							}
						}else{
							if(type.equalsIgnoreCase("shovel")) {
								recycleur.recycleItem("shovel", item.getType(), item.getDurability(), null, p);
							} else if(type.equalsIgnoreCase("hoe")) {
								recycleur.recycleItem("hoe", item.getType(), item.getDurability(), null, p);
							} else if(type.equalsIgnoreCase("sword")) {
								recycleur.recycleItem("sword", item.getType(), item.getDurability(), null, p);
							} else if(type.equalsIgnoreCase("axe")) {
								recycleur.recycleItem("axe", item.getType(), item.getDurability(), null, p);
							} else if(type.equalsIgnoreCase("pickaxe")) {
								recycleur.recycleItem("pickaxe", item.getType(), item.getDurability(), null, p);
							} else if(type.equalsIgnoreCase("helmet")) {
								recycleur.recycleItem("helmet", item.getType(), item.getDurability(), null, p);
							} else if(type.equalsIgnoreCase("chestplate")) {
								recycleur.recycleItem("chestplate", item.getType(), item.getDurability(), null, p);
							} else if(type.equalsIgnoreCase("leggings")) {
								recycleur.recycleItem("leggings", item.getType(), item.getDurability(), null, p);
							} else if(type.equalsIgnoreCase("boots")) {
								recycleur.recycleItem("boots", item.getType(), item.getDurability(), null, p);
							} else if(type.equalsIgnoreCase("misc")) {
								recycleur.recycleItem("misc", item.getType(), item.getDurability(), null, p);
							} else {
								p.sendMessage(colorMessage(recycleur.getConfig().getString("Recycleur.Invalid")));
								p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
							}
						}
					} else if(item == null || item.getType() == Material.AIR) {
						p.sendMessage(colorMessage(recycleur.getConfig().getString("Recycleur.Empty")));
						p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
					} else {
						p.sendMessage(colorMessage(recycleur.getConfig().getString("Recycleur.Invalid")));
						p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
					}
					break;
				default:
					break;
				}
				break;
			case SKULL_ITEM:
				e.setCancelled(true);
				break;
			case HOPPER:
				e.setCancelled(true);
				break;
			case DIAMOND:
				e.setCancelled(true);
				break;
			default:
				break;
			}
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if(e.getInventory().getName().equalsIgnoreCase(colorMessage(recycleur.getConfig().getString("Recycleur.Name")))) {
			if(e.getInventory().getItem(13) != null) {
				ItemStack item = e.getInventory().getItem(13);
				p.getInventory().addItem(item);
				e.getInventory().setItem(13, new ItemStack(Material.AIR));
			}
		}
	}

}
