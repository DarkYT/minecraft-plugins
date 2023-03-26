package fr.dark.tag.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.dark.tag.Core;

public class onTagEvent implements Listener {

	Core core;
	public onTagEvent(Core core) {
		this.core = core;
	}
	
	public void onTag(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage();
		for(Player all : Bukkit.getServer().getOnlinePlayers()) {
			if(all.equals(p))continue;
			if(msg.contains(all.getName())){
				String nMsg = msg.replaceAll(all.getName(), ChatColor.translateAlternateColorCodes('&', "&c" + all.getName()));
				e.setMessage(nMsg);
			}
		}
	}

}
