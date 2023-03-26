package fr.darkyt.call;

import org.bukkit.plugin.java.JavaPlugin;

import fr.darkyt.call.listeners.PluginListeners;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PluginListeners(), this);
	}
	
}
