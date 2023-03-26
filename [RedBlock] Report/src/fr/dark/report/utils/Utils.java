package fr.dark.report.utils;

import org.bukkit.ChatColor;

import fr.dark.report.Core;


public class Utils {

	Core core;
	public Utils(Core core){
		this.core = core;
	}
	
	public String colorMessage(String msg) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		return message;
	}
	public String modifyMessage(String msg, String old, String nouv) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		message = message.replace(old, nouv);
		return message;
	}
	
}
