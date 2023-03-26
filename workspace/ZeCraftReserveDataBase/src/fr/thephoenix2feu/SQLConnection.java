package fr.thephoenix2feu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class SQLConnection {
	
	private Connection connection;
	private String urlbase, host, database, user, pass;
	plugin core;

	public SQLConnection(String urlbase, String host, String database, String user, String pass, plugin core) {
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
				System.out.println("[ZeCraftReserve] Connexion MySql réussie");
			} catch (SQLException e) {
				System.out.println("[ZeCraftReserve] La Connection MySql à échouée !");
				Bukkit.getPluginManager().disablePlugin(core);
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
				System.out.println("[ZeCraftReserve] Connexion MySql déconnectée");
			} catch (SQLException e) {
				System.out.println("[ZeCraftReserve] La Déconnexion MySql à échouée !");
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected(){
		return connection != null;
	}
	
	public void createInv(Player p, Inventory inv) {
		try {
			PreparedStatement q = connection.prepareStatement("INSERT INTO reserve (uuid,inventory) VALUES (?,?)");
			q.setString(1, p.getUniqueId().toString());
			q.setString(2, InventoryManage.InventoryToString(inv));
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasInv(OfflinePlayer p) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT uuid FROM reserve WHERE uuid = ?");
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
	
	public void setInv(Player p, Inventory inv) {
		try {
			PreparedStatement q = connection.prepareStatement("UPDATE reserve SET inventory = ? WHERE uuid = ?");
			q.setString(1, InventoryManage.InventoryToString(inv));
			q.setString(2, p.getUniqueId().toString());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Inventory getInv(OfflinePlayer p) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT inventory FROM reserve WHERE uuid = ?");
			q.setString(1, p.getUniqueId().toString());
			String inventory = "";
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				inventory = rs.getString("inventory");
			}
			q.close();
			Inventory inv = InventoryManage.StringToInventory(inventory, p.getName());
			return inv;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
