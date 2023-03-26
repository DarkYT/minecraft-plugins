package fr.earthsky.chest;

import org.bukkit.plugin.java.JavaPlugin;

import fr.earthsky.chest.commands.ChestCommand;


public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		getCommand("chest").setExecutor(new ChestCommand(this, null));
	}
}
