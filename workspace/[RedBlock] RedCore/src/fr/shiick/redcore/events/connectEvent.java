package fr.shiick.redcore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.shiick.redcore.RedCore;

public class connectEvent implements Listener {

	RedCore core;
	
	public connectEvent(RedCore core) {
		this.core = core;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for(Player pl : core.vanished) {
			p.hidePlayer(pl);
			if(p.hasPermission("vanish.admin")) {
				p.showPlayer(pl);
			}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(core.vanished.contains(p)) {
			core.vanished.remove(p);
			for(Player pl : Bukkit.getOnlinePlayers()) {
				pl.showPlayer(p);
			}
		}
		if (core.admin.contains(p)) {
			e.setQuitMessage(null);
		}
		if(core.noob.contains(p)) {
			core.noob.remove(p);
		}
	}
	
}
