package fr.dark.stats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class SQLConnexion {
	
	private Connection connection;
	private String urlbase, host, database, user, pass;
	Stats core;

	public SQLConnexion(String urlbase, String host, String database, String user, String pass, Stats core) {
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
				System.out.println("[Stats] Connexion MySql réussie");
			} catch (SQLException e) {
				System.out.println("[Stats] La Connection MySql à échouée !");
				Bukkit.getPluginManager().disablePlugin(core);
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
				System.out.println("[Stats] Connexion MySql déconnectée");
			} catch (SQLException e) {
				System.out.println("[Stats] La Déconnexion MySql à échouée !");
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected(){
		return connection != null;
	}
	
	public void createAccount(Player p) {
		try {
			PreparedStatement q = connection.prepareStatement("INSERT INTO stats(uuid,spend,placed_blocks,broken_blocks,deaths) VALUES (?,?,?,?,?)");
			q.setString(1, p.getUniqueId().toString());
			q.setString(2, "0");
			q.setInt(3, 0);
			q.setInt(4, 0);
			q.setInt(5, 0);
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasAccount(Player p) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT uuid FROM stats WHERE uuid = ?");
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
	
	public String getSpendTime(String uuid) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT spend FROM stats WHERE uuid = ?");
			q.setString(1, uuid);
			String preuve = "";
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getString("spend");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void setSpendTime(Player p, long spend) {
		try {
			PreparedStatement q = connection.prepareStatement("UPDATE stats SET spend = ? WHERE uuid = ?");
			q.setString(1, Long.toString(spend));
			q.setString(2, p.getUniqueId().toString());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setPlacedBlocks(Player p, int num) {
		try {
			PreparedStatement q = connection.prepareStatement("UPDATE stats SET placed_blocks = ? WHERE uuid = ?");
			q.setInt(1, num);
			q.setString(2, p.getUniqueId().toString());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setBrokenBlocks(Player p, int num) {
		try {
			PreparedStatement q = connection.prepareStatement("UPDATE stats SET broken_blocks = ? WHERE uuid = ?");
			q.setInt(1, num);
			q.setString(2, p.getUniqueId().toString());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setDeaths(Player p, int num) {
		try {
			PreparedStatement q = connection.prepareStatement("UPDATE stats SET deaths = ? WHERE uuid = ?");
			q.setInt(1, num);
			q.setString(2, p.getUniqueId().toString());
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getPlacedBlocks(String uuid) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT placed_blocks FROM stats WHERE uuid = ?");
			q.setString(1, uuid);
			int preuve = 0;
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getInt("placed_blocks");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getBrokenBlocks(String uuid) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT broken_blocks FROM stats WHERE uuid = ?");
			q.setString(1, uuid);
			int preuve = 0;
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getInt("broken_blocks");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getDeaths(String uuid) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT deaths FROM stats WHERE uuid = ?");
			q.setString(1, uuid);
			int preuve = 0;
			ResultSet rs = q.executeQuery();
			while(rs.next()){
				preuve = rs.getInt("deaths");
			}
			q.close();
			return preuve;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
