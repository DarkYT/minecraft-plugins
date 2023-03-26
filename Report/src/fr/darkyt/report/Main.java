package fr.darkyt.report;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.darkyt.report.commands.CommandReport;

public class Main extends JavaPlugin {
	
	public static Map<String, Long> cooldown = new HashMap<>();
	
	@Override
	public void onEnable() {
		
		System.out.println("Le plugin est allume");
		getCommand("report").setExecutor(new CommandReport());
		for(Player ps : Bukkit.getOnlinePlayers()){
			cooldown.put(ps.getName(), (long) 0);
		}
		
	}

	@Override
	public void onDisable() {
		
		System.out.println("Le plugin est eteint");
		
	}
}
