package fr.darkyt.nightvision.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.darkyt.nightvision.Main;

public class joinAndLeftEvents implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		Main.nightvision.remove(p);
		Main.removeNV(p);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		Main.nightvision.remove(p);
		Main.removeNV(p);
	}
	
}
