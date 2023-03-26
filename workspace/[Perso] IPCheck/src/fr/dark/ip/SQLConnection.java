package fr.dark.ip;

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
	IPCheck core;

	public SQLConnection(String urlbase, String host, String database, String user, String pass, IPCheck core) {
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
				System.out.println("[IPCheck] Connexion MySql réussie");
			} catch (SQLException e) {
				System.out.println("[IPCheck] La Connection MySql à échouée !");
				Bukkit.getPluginManager().disablePlugin(core);
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
				System.out.println("[IPCheck] Connexion MySql déconnectée");
			} catch (SQLException e) {
				System.out.println("[IPCheck] La Déconnexion MySql à échouée !");
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected(){
		return connection != null;
	}
	
	public void createAccount(Player p) {
		try {
			PreparedStatement q = connection.prepareStatement("INSERT INTO ipconnect(uuid,ip) VALUES (?,?)");
			q.setString(1, p.getUniqueId().toString());
			q.setString(2, p.getAddress().getAddress().getHostAddress());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasAccount(Player p) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT uuid FROM ipconnect WHERE uuid = ?");
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
	
	public String getLastIP(String uuid) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT ip FROM ipconnect WHERE uuid = ?");
			q.setString(1, uuid);
			String preuve = "";
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getString("ip");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void setNewIP(Player p) {
		try {
			PreparedStatement q = connection.prepareStatement("UPDATE ipconnect SET ip = ? WHERE uuid = ?");
			q.setString(1, p.getAddress().getAddress().getHostAddress());
			q.setString(2, p.getUniqueId().toString());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
