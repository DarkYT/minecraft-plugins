package fr.dark.semirp.utils;

import org.bukkit.ChatColor;

public class Utils {
	
	public Utils() {
		
	}
	
	public String colorMessage(String msg) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		return message;
	}

}
