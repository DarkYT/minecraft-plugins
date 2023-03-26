package fr.shiick.redshop.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.shiick.redshop.RedShop;

public class joinEvent implements Listener {

	RedShop shop;
	
	public joinEvent(RedShop shop) {
		this.shop = shop;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if(shop.lastProductViewed.containsKey(e.getPlayer().getName())) {
			shop.lastProductViewed.remove(e.getPlayer().getName());
		}
	}
	
}
