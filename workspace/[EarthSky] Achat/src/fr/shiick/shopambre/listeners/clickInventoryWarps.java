package fr.shiick.shopambre.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.shiick.shopambre.Core;

public class clickInventoryWarps implements Listener {

	Core core;
	public clickInventoryWarps(Core c) {
		core = c;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fWarps"))){
			e.setCancelled(true);
			switch (e.getCurrentItem().getType()) {
			case DIAMOND_HELMET:
				p.closeInventory();
				core.openDemon(p);
				break;
			case IRON_HELMET:
				p.closeInventory();
				core.openCorrompu(p);
				break;
			case GOLD_HELMET:
				p.closeInventory();
				core.openVampire(p);
				break;
			case ARROW:
				p.closeInventory();
				core.openMain(p);
			default:
				break;
			}
		}
	}

}
