package fr.shiick.coins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SQLConnection {

	Utils utils;
	
	private Connection connection;
	private String urlbase, host, database, user, pass;

	public SQLConnection(String urlbase, String host, String database, String user, String pass) {
		this.urlbase = urlbase;
		this.host = host;
		this.database = database;
		this.user = user;
		this.pass = pass;
		
		utils = new Utils(Coins.getInstance());
	}
	
	public void connection() {
		if (!isConnected()) {
			try {
				connection = DriverManager.getConnection(urlbase + host + "/" + database, user, pass);
				System.out.println("[Coins] Connexion MySql réussie");
			} catch (SQLException e) {
				System.out.println("[Coins] La Connection MySql à échouée !");
				Bukkit.getPluginManager().disablePlugin(Coins.getInstance());
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
				System.out.println("[Coins] Connexion MySql déconnectée");
			} catch (SQLException e) {
				System.out.println("[Coins] La Déconnexion MySql à échouée !");
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected(){
		return connection != null;
	}
	
	public void createAccount(Player p) {
		if(!hasAccount(p)) {
			try {
				PreparedStatement q = connection.prepareStatement("INSERT INTO coins(pseudo,coins) VALUES (?,?)");
				q.setString(1, p.getUniqueId().toString());
				q.setInt(2, Utils.getDefault());
				q.execute();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean hasAccount(Player p) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT pseudo FROM coins WHERE pseudo = ?");
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
			PreparedStatement q = connection.prepareStatement("SELECT coins FROM coins WHERE pseudo = ?");
			q.setString(1, p.getName().toString());
			int coins = 0;
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				coins = rs.getInt("coins");
			}
			q.close();
			return coins;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void setCoins(Player p, long coins) {
		try {
			PreparedStatement q = connection.prepareStatement("UPDATE coins SET coins = ? WHERE pseudo = ?");
			q.setLong(1, coins);
			q.setString(2, p.getName().toString());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
