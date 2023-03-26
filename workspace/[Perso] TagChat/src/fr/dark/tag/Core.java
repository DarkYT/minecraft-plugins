package fr.dark.tag;

import org.bukkit.plugin.java.JavaPlugin;

import fr.dark.tag.listeners.onTagEvent;

public class Core extends JavaPlugin {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new onTagEvent(this), this);
	}
}
