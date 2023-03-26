package fr.earthsky.shop.listeners;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.earthsky.shop.Core;

public class clickEventRank implements Listener {
	
	Core core;

	public clickEventRank(Core c) {
		core = c;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		OfflinePlayer pl = p;
		double money = Core.econ.getBalance(pl);
		if(e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&a&lGrades &7(&6" + money + "&7)"))){
			e.setCancelled(true);
			switch(e.getCurrentItem().getType()){
			case IRON_HELMET:
				p.closeInventory();
				core.buyRank(p, "corrompu");
				break;
			case GOLD_HELMET:
				p.closeInventory();
				core.confirmVam(p);
				break;
			case DIAMOND_HELMET:
				p.closeInventory();
				core.confirmDem(p);
				break;
			default:
				break;
			}
		}
	}

}
