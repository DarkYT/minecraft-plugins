package fr.dark.semirp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class SQLConnection {

	
	private Connection connection;
	private String urlbase, host, database, user, pass;
	Core core;

	public SQLConnection(String urlbase, String host, String database, String user, String pass, Core core) {
		this.urlbase = urlbase;
		this.host = host;
		this.database = database;
		this.user = user;
		this.pass = pass;
		this.core = core;
		
	}
	
	public void connection() {
		if (!isConnected()) {
			try {
				connection = DriverManager.getConnection(urlbase + host +  "/" + database, user, pass);
				System.out.println("[SemiRP] Connexion MySql réussie");
			} catch (SQLException e) {
				System.out.println("[SemiRP] La Connection MySql à échouée !");
				Bukkit.getPluginManager().disablePlugin(core);
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
				System.out.println("[SemiRP] Connexion MySql déconnectée");
			} catch (SQLException e) {
				System.out.println("[SemiRP] La Déconnexion MySql à échouée !");
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected(){
		return connection != null;
	}
	
	public void createAccount(Player p, int level, int coins, String job) {
		if(!(hasAccount(p))) {
			try {
				PreparedStatement q = connection.prepareStatement("INSERT INTO semirp(uuid,level,coins,metier) VALUES (?,?,?,?)");
				q.setString(1, p.getUniqueId().toString());
				q.setInt(2, level);
				q.setInt(3, coins);
				q.setString(4, job);
				q.execute();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean hasAccount(Player p) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT uuid FROM semirp WHERE uuid = ?");
			q.setString(1, p.getUniqueId().toString());
			ResultSet resultat = q.executeQuery();
			boolean hasAccount = resultat.next();
			q.close();
			return hasAccount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getCoins(Player p) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT coins FROM semirp WHERE uuid = ?");
			q.setString(1, p.getUniqueId().toString());
			int preuve = 0;
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getInt("coins");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getLevel(Player p) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT level FROM semirp WHERE uuid = ?");
			q.setString(1, p.getUniqueId().toString());
			int preuve = 0;
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getInt("level");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void setCoins(Player p, long coins) {
		try {
			PreparedStatement q = connection.prepareStatement("UPDATE semirp SET coins = ? WHERE uuid = ?");
			q.setLong(1, coins);
			q.setString(2, p.getUniqueId().toString());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setLevel(Player p, int level) {
		try {
			PreparedStatement q = connection.prepareStatement("UPDATE semirp SET level = ? WHERE uuid = ?");
			q.setLong(1, level);
			q.setString(2, p.getUniqueId().toString());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}