package fr.shiick.redshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SQLConnection {

	private Connection connection;
	private String urlbase, host, database, user, pass;
	RedShop shop;

	public SQLConnection(String urlbase, String host, String database, String user, String pass, RedShop shop) {
		this.urlbase = urlbase;
		this.host = host;
		this.database = database;
		this.user = user;
		this.pass = pass;
		this.shop = shop;
	}
	
	public void connection() {
		if (!isConnected()) {
			try {
				connection = DriverManager.getConnection(urlbase + host + "/" + database, user, pass);
				System.out.println("[RedShop] Connexion MySql réussie");
			} catch (SQLException e) {
				System.out.println("[RedShop] La Connection MySql à échouée !");
				Bukkit.getPluginManager().disablePlugin(RedShop.getInstance());
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
				System.out.println("[RedShop] Connexion MySql déconnectée");
			} catch (SQLException e) {
				System.out.println("[RedShop] La Déconnexion MySql à échouée !");
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected(){
		return connection != null;
	}
	
	public void addTransaction(Player p, String product, int price, int id) {
		try {
			PreparedStatement q = connection.prepareStatement("INSERT INTO transactions(id, pseudo, prix, produit) VALUES (?, ?, ?, ?)");
			q.setInt(1, id);
			q.setString(2, p.getName().toString());
			q.setLong(3, price);
			q.setString(4, product);
			q.execute();
			q.close();
			shop.getConfig().set("MySQL.ID", id);
			shop.saveConfig();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
