package fr.shiick.crafting.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.shiick.crafting.Crafting;

public class inventoryCraft implements Listener {

	Crafting crafting;

	public inventoryCraft(Crafting c) {
		crafting = c;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&c&lCraft Topaze"))) {
			e.setCancelled(true);
			switch (e.getCurrentItem().getType()) {
			case NETHER_STAR:
				p.closeInventory();
				crafting.openCraft2(p);
				break;
			default:
				break;
			}
		}
	}
	
}
