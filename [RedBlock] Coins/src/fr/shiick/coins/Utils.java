package fr.shiick.coins;

import org.bukkit.ChatColor;

public class Utils {

	Coins coins;

	public Utils(Coins coins) {
		this.coins = coins;
	}

	public static int getDefault() {
		int amount = Coins.getInstance().getConfig().getInt("Coin.Default");
		return amount;
	}
	
	public String colorMessage(String msg) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		return message;
	}

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}
