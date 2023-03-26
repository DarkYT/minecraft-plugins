package fr.shiick.coins;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.shiick.coins.commands.coinsCmd;

public class Coins extends JavaPlugin implements Listener {

	private static Coins instance;

	public static Coins getInstance() {
		return instance;
	}

	public static SQLConnection sql;
	
	public CoinsAPI coinsAPI;

	@Override
	public void onEnable() {
		instance = this;

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(getInstance(), getInstance());

		getCommand("coins").setExecutor(new coinsCmd(getInstance()));

		saveDefaultConfig();

		String protocol = getConfig().getString("MySQL.Protocol");
		String host = getConfig().getString("MySQL.Host");
		String database = getConfig().getString("MySQL.Database");
		String username = getConfig().getString("MySQL.Username");
		String password = getConfig().getString("MySQL.Password");

		sql = new SQLConnection(protocol, host, database, username, password);
		sql.connection();
		
		coinsAPI = new CoinsAPI();
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		boolean hub = getConfig().getBoolean("Coin.Hub");
		if (hub == true) {
			sql.createAccount(p);
		}
	}

}
