package fr.earthsky.convert;

import org.bukkit.plugin.java.JavaPlugin;

import fr.earthsky.convert.commands.ConvertCommand;

public class Core extends JavaPlugin {
	@Override
	public void onEnable() {
		getCommand("convert").setExecutor(new ConvertCommand(this));
	}
}
