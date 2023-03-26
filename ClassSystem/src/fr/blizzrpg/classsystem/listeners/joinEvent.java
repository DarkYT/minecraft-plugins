package fr.blizzrpg.classsystem.listeners;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.blizzrpg.classsystem.ClassAPI;

public class joinEvent implements Listener {

	ClassAPI classapi;
	
	public joinEvent(ClassAPI capi){
		classapi = capi;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(ClassAPI.confFile);
		if(p.hasPlayedBefore() == false){
			yc.set(p.getName() + ".Niveau", 0);
			yc.set(p.getName() + ".XP", 0);
		}
	}
	
}
