package fr.darkyt.news;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class News extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(this, this);
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPreCommand(PlayerCommandPreprocessEvent e){
		Player player = e.getPlayer();
		String[] args = e.getMessage().split(" ");
		if(args[0].equalsIgnoreCase("/news")){
			e.setCancelled(true);
			
			String ligne1 = getConfig().getString("news.ligne1");
			String ligne2 = getConfig().getString("news.ligne2");
			String ligne3 = getConfig().getString("news.ligne3");
			String ligne4 = getConfig().getString("news.ligne4");
			String ligne5 = getConfig().getString("news.ligne5");
			String ligne6 = getConfig().getString("news.ligne6");
			String ligne7 = getConfig().getString("news.ligne7");
			String ligne8 = getConfig().getString("news.ligne8");
			
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ligne1));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ligne2));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ligne3));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ligne4));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ligne5));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ligne6));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ligne7));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ligne8));
			
		}	
	}

}
