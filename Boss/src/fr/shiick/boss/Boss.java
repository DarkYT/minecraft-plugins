package fr.shiick.boss;

import org.bukkit.plugin.java.JavaPlugin;

public class Boss extends JavaPlugin {
	
	@Override
	public void onEnable(){
		registerBoss(); //Last
		addCommands(); //Commande de next event && décompte
		addListeners(); //Le reste
	}

}
