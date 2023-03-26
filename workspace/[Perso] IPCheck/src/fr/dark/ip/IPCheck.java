package fr.dark.ip;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.xephi.authme.AuthMe;



public class IPCheck extends JavaPlugin implements Listener {
	
	public static SQLConnection sql;
	private static IPCheck instance;
	
	@Override
	public void onEnable() {
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		instance = this;
		
		getServer().getPluginManager().registerEvents(this, this);
		
		saveDefaultConfig();
		
		String protocol = getConfig().getString("MySQL.Protocol");
		String host = getConfig().getString("MySQL.Host");
		String database = getConfig().getString("MySQL.Database");
		String username = getConfig().getString("MySQL.Username");
		String password = getConfig().getString("MySQL.Password");

		sql = new SQLConnection(protocol, host, database, username, password, this);
		sql.connection();
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String ip = p.getAddress().getAddress().getHostAddress();
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(p.isOnline()) {
					if(!sql.hasAccount(p)) {
						if(AuthMe.getApi().isAuthenticated(p)) {
							sql.createAccount(p);
							teleportServer(p, "hub");
							cancel();
						}
					}else {
						if(ip.equals(sql.getLastIP(p.getUniqueId().toString()))) {
							getLogger().info("Connexion bypassed for "+p.getName());
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "authme forcelogin "+p.getName());
							Bukkit.getScheduler().runTaskLater(IPCheck.getInstance(), new Runnable() {

								@Override
								public void run() {
									teleportServer(p, "hub");
								}
								
							}, 25L);
							p.sendMessage("§aVous avez été automatiquement connecté ! Bon jeu !");
						}else {
							if(!AuthMe.getApi().isAuthenticated(p)) {
								p.sendMessage("§cVeuillez vous connecter");
							}
							if(AuthMe.getApi().isAuthenticated(p)) {
								sql.setNewIP(p);
							}
						}
						if(AuthMe.getApi().isAuthenticated(p)) {
							cancel();
						}
					}
				}else {
					cancel();
				}
			}
		}.runTaskTimer(this, 0, 20);
	}

	public static IPCheck getInstance() {
		return instance;
	}
	
	public void teleportServer(Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
	}

}
