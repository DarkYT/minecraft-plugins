package fr.earthsky.furnace;

import org.bukkit.plugin.java.JavaPlugin;

import fr.earthsky.furnace.commands.FurnaceCommand;

public class Core extends JavaPlugin {
	@Override
	public void onEnable() {
		getCommand("four").setExecutor(new FurnaceCommand(this));
	}
}
