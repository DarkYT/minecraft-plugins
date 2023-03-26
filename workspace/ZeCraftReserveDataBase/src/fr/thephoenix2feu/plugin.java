package fr.thephoenix2feu;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class plugin extends JavaPlugin{

	public static plugin pl;
	public static SQLConnection sql;
	
	@SuppressWarnings("static-access")
	@Override
	public void onEnable() {
		
		this.pl = this;
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Mine(this), this);
		pm.registerEvents(new Commande(this), this);
		pm.registerEvents(new Inv(this), this);
		
		
		getConfig().options().copyDefaults(true);
		
		saveConfig();
		
		String protocol = getConfig().getString("MySQL.Protocol");
		String host = getConfig().getString("MySQL.Host");
		String database = getConfig().getString("MySQL.Database");
		String username = getConfig().getString("MySQL.Username");
		String password = getConfig().getString("MySQL.Password");

		sql = new SQLConnection(protocol, host, database, username, password, this);
		sql.connection();
		super.onEnable();
	}
	
}
