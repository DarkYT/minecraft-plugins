package fr.darkyt.helpme;

import org.bukkit.plugin.java.JavaPlugin;

import fr.darkyt.helpme.commands.CommandHelp;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		System.out.println("Le plugin HelpMe s'allume");
		getCommand("helpme").setExecutor(new CommandHelp());
	}
	
	@Override
	public void onDisable() {
		
		System.out.println("Le plugin HelpMe s'eteint");
	}

}
