package fr.stellaria.vote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

public class sqlConnect {

	private Connection connection;
	private String urlbase, host, bddname, user, password;

	public sqlConnect(String urlbase, String host, String bddname, String user, String password) {
		this.urlbase = urlbase;
		this.host = host;
		this.bddname = bddname;
		this.user = user;
		this.password = password;
	}

	public void connection() {
		if (!isConnected()) {
			try {
				connection = (Connection) DriverManager.getConnection(urlbase + host + "/" + bddname, user, password);
				System.out.println("Database connected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
				System.out.println("Database diconnected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean isConnected() {
		return connection != null;
	}

	public boolean hasVoted(Player player) {

		try {
			PreparedStatement q = connection.prepareStatement("SELECT voted FROM vote WHERE pseudo = ?");
			q.setString(1, player.getName());

			int voted = 0;
			ResultSet resultat = q.executeQuery();

			while(resultat.next()){ 
				voted = resultat.getInt("voted"); 
			}

			if (voted == 1)
				return true;

			else if (voted == 0)
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public void hasRewarded(Player player) {

		try {
			PreparedStatement q = connection.prepareStatement("UPDATE vote SET voted = 0 WHERE pseudo = ?");
			q.setString(1, player.getName());
			q.executeUpdate();
			q.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void hasAlreadyVoted(Player player) {
		try {
			PreparedStatement q = connection.prepareStatement("SELECT COUNT(*) FROM vote WHERE pseudo =?");
			q.setString(1, player.getName());
			ResultSet resultat = q.executeQuery();
			q.close();

			int registeredPseudo = 0;
			while (resultat.next()) {
				registeredPseudo = resultat.getInt(1);
			}

			if (registeredPseudo == 1) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
