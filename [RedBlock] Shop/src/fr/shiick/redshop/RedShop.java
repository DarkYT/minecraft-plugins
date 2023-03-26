package fr.shiick.redshop;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.shiick.coins.Coins;
import fr.shiick.redshop.comands.reloadCmd;
import fr.shiick.redshop.commands.shopCmd;
import fr.shiick.redshop.events.joinEvent;
import fr.shiick.redshop.events.menuEvent;
import fr.shiick.redshop.utils.Menu;

public class RedShop extends JavaPlugin {

	private static RedShop instance;

	public static RedShop getInstance() {
		return instance;
	}
	
	public Map<String, String> lastProductViewed = new HashMap<>();
	
	public Coins coins = (Coins) Bukkit.getPluginManager().getPlugin("Coins");
	
	public static SQLConnection sql;
	
	public Menu menu;
	
	@Override
	public void onEnable() {
		// Commands
		getCommand("shop").setExecutor(new shopCmd(this));
		getCommand("shopreload").setExecutor(new reloadCmd(this));
		// Events
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new menuEvent(this), this);
		pm.registerEvents(new joinEvent(this), this);
		// Configs
		saveDefaultConfig();
		
		String protocol = getConfig().getString("MySQL.Protocol");
		String host = getConfig().getString("MySQL.Host");
		String database = getConfig().getString("MySQL.Database");
		String username = getConfig().getString("MySQL.Username");
		String password = getConfig().getString("MySQL.Password");

		sql = new SQLConnection(protocol, host, database, username, password, this);
		sql.connection();
		
		menu = new Menu(this);
	}
	
}
