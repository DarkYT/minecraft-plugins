package fr.earthsky.scoreboard.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;

import fr.earthsky.scoreboard.EarthScoreboard;

public class joinEvent implements Listener {

	public static Scoreboard pb;
	
	EarthScoreboard earthscoreboard;
	
	public joinEvent(EarthScoreboard es){
		earthscoreboard = es;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		earthscoreboard.refresh(p);
		p.setScoreboard(earthscoreboard.board);
	}
	
}
