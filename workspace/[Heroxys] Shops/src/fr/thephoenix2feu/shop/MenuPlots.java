package fr.thephoenix2feu.shop;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.md_5.bungee.api.ChatColor;

public class MenuPlots implements Listener {

	public plugin pl;
	
	public MenuPlots(plugin plugin) {
		this.pl = plugin;
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onClck(InventoryClickEvent e) {
		
		if(e.getCurrentItem() != null && e.getInventory().getName().equalsIgnoreCase("Shop")) {
			e.setCancelled(true);
			
			if(e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				
				String name = e.getCurrentItem().getItemMeta().getDisplayName();
				
				for(String s : (ArrayList<String>) pl.getConfig().get("shops")) {
					
					if(pl.getConfig().getString("shop."+s+".name").equalsIgnoreCase(name)) {
						e.getWhoClicked().teleport((Location) pl.getConfig().get("shop."+s+".spawn"));
						e.getWhoClicked().sendMessage(ChatColor.DARK_GREEN+"Vous avez été téléporté au shop de "+name);
					}
					
				}
				
				
			}
			
		}
		
	}
	
}
