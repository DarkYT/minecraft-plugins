package fr.darkyt.dark;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.darkyt.dark.commands.CommandPlugin;
import fr.darkyt.dark.listeners.PluginListeners;

public class Main extends JavaPlugin {
	
	public static Map<String, Long> cooldown = new HashMap<>();

	@Override
	public void onEnable() {
		
		saveDefaultConfig();
		
		System.out.println("Le plugin Dark s'allume");
		getCommand("report").setExecutor(new CommandPlugin(this));
		getCommand("helpme").setExecutor(new CommandPlugin(this));
		getServer().getPluginManager().registerEvents(new PluginListeners(this), this);
		
		for(Player ps : Bukkit.getOnlinePlayers()){
			cooldown.put(ps.getName(), (long) 0);
		}
		
	}
	
	@Override
	public void onDisable() {
		
		System.out.println("Le plugin Dark s'eteint");
		
	}
	
}

