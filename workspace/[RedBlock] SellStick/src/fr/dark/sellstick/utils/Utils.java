package fr.dark.sellstick.utils;

import org.bukkit.ChatColor;

public class Utils {
	
	public String colorMessage(String msg) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		return message;
	}
	
	public String modifyMessage(String msg, String old, String nouv) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		message = message.replace(old, nouv);
		return message;
	}
	
	@SuppressWarnings("unused")
	public boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
