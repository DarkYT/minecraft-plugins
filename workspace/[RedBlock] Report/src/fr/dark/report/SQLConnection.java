package fr.dark.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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
				System.out.println("[Report] Connexion MySql réussie");
			} catch (SQLException e) {
				System.out.println("[Report] La Connection MySql à échouée !");
				Bukkit.getPluginManager().disablePlugin(core);
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
				System.out.println("[Report] Connexion MySql déconnectée");
			} catch (SQLException e) {
				System.out.println("[Report] La Déconnexion MySql à échouée !");
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected(){
		return connection != null;
	}
	
	public void createAccount(Player p, String servername, String preuve, String raison, Player reporter) {
		try {
			PreparedStatement q = connection.prepareStatement("INSERT INTO report(pseudo,server,preuve,raison,reporter) VALUES (?,?,?,?,?)");
			q.setString(1, p.getName());
			q.setString(2, servername);
			q.setString(3, preuve);
			q.setString(4, raison);
			q.setString(5, reporter.getName());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasAccount(Player p) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT pseudo FROM report WHERE pseudo = ?");
			q.setString(1, p.getName());
			ResultSet resultat = q.executeQuery();
			boolean hasAccount = resultat.next();
			q.close();
			return hasAccount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<String> getReportsOfPlayer(OfflinePlayer p){
		List<String> list = new ArrayList<>();
		try {
			PreparedStatement q = connection.prepareStatement("SELECT * FROM report");
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				if(rs.getString("pseudo").equals(p.getName())){
					list.add(rs.getString("pseudo")+":"+rs.getInt("id"));
				}
			}
			q.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getPlayers(){
		List<String> list = new ArrayList<>();
		try {
			PreparedStatement q = connection.prepareStatement("SELECT * FROM report");
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				if(!(list.contains(rs.getString("pseudo")))){
					list.add(rs.getString("pseudo"));
				}
			}
			q.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getPseudo(int id) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT pseudo FROM report WHERE id = ?");
			q.setInt(1, id);
			String preuve = "";
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getString("pseudo");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getReporter(int id) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT reporter FROM report WHERE id = ?");
			q.setInt(1, id);
			String preuve = "";
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getString("reporter");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getServer(int id) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT server FROM report WHERE id = ?");
			q.setInt(1, id);
			String preuve = "";
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getString("server");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getRaison(int id) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT raison FROM report WHERE id = ?");
			q.setInt(1, id);
			String preuve = "";
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getString("raison");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getPreuve(int id) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT preuve FROM report WHERE id = ?");
			q.setInt(1, id);
			String preuve = "";
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getString("preuve");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void remove(int id) {
		try {
			PreparedStatement q = connection.prepareStatement("DELETE FROM report WHERE id = ?");
			q.setInt(1, id);
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removePlayer(OfflinePlayer p) {
		try {
			PreparedStatement q = connection.prepareStatement("DELETE FROM report WHERE pseudo = ?");
			q.setString(1, p.getName());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeAll() {
		try {
			PreparedStatement q = connection.prepareStatement("TRUNCATE TABLE report");
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}