package fr.darkyt.repair;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.darkyt.repair.listeners.PluginListeners;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	public static Economy econ = null;
	
	
	
	@Override
	public void onEnable() {
		
		System.out.println("Le plugin repair s'allume");
		getServer().getPluginManager().registerEvents(new PluginListeners(this), this);
		if (!setupEconomy()){
		   getLogger().severe(String.format("Vault est absent ! Désactivation...", getDescription().getName()));
		   getServer().getPluginManager().disablePlugin(this);
		   return;
		}
		
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	@Override
	public void onDisable() {
		
		System.out.println("Le plugin repair s'eteint");
		
	}

}
