package fr.shiick.redhub.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import fr.shiick.redhub.RedHub;
import fr.shiick.redhub.utils.Menu;
import fr.shiick.redhub.utils.Utils;

public class citizensEvent implements Listener {

	RedHub redhub;
	Utils utils;
	Menu menu;
	
	public citizensEvent(RedHub redhub) {
		this.redhub = redhub;
		utils = new Utils(redhub);
		menu = new Menu(redhub);
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		Entity target = e.getRightClicked();
		if(target.hasMetadata("NPC")) {
			String menuName = redhub.getConfig().getString("Items.Menu.Name");
			String shopName = redhub.getConfig().getString("Items.Shop.Name");
			if(target.getName().equalsIgnoreCase(utils.colorMessage(menuName))) {
				menu.openServers(p);
			} else if (target.getName().equalsIgnoreCase(utils.colorMessage(shopName))) {
				redhub.shop.menu.openMain(p);
			}
		}
	}
	
}
