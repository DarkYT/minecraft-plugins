package fr.shiick.redhub.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.shiick.redhub.RedHub;
import fr.shiick.redhub.utils.Items;

public class joinEvent implements Listener {

	RedHub redhub;
	Items items;
	
	public joinEvent(RedHub redhub) {
		this.redhub = redhub;
		items = new Items(redhub);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.getInventory().clear();
		items.giveItems(p);
	}
	
}
