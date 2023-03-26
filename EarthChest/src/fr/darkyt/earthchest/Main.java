package fr.darkyt.earthchest;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.darkyt.earthchest.listeners.onPlayerJoin;

public class Main extends JavaPlugin {
	
	public FileConfiguration config = getConfig();
	public HashMap<UUID, Inventory> chests = new HashMap<UUID, Inventory>();
			
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new onPlayerJoin(this), this);
	}
	
	public void onDisable() {
		
	}
	
	public void saveHashMap(HashMap<UUID, Inventory> hm, Player p) {
		
		char c = '1';
		for (Object key : hm.keySet()) {
			config.set("Chests." + p.getName() + "." + c++ + key, hm.get(key));
		}
		saveConfig();
	}
	
	public HashMap<UUID, Inventory> loadHashMap() {
		HashMap<UUID, Inventory> hm = new HashMap<UUID, Inventory>();
		for (String key : config.getConfigurationSection("Chests").getKeys(false)) {
			hm.put(key, config.get("Chests."+key));
			}
		 
		return hm;
	}

}
