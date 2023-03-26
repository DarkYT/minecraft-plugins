package fr.earthsky.silkspawner;

import org.bukkit.plugin.java.JavaPlugin;

import fr.earthsky.silkspawner.commands.earthSpawner;

public class SilkSpawner extends JavaPlugin {
	
	@Override
	public void onEnable(){
		getCommand("earthspawner").setExecutor(new earthSpawner(this));
	}

}
