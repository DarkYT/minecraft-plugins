package fr.dark.ctfcore.utils;

import org.bukkit.ChatColor;

import fr.dark.ctfcore.CTFCore;

public class Utils {
	
	CTFCore core;
	public Utils(CTFCore core){
		this.core = core;
	}
	
	public String colorMessage(String msg) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		return message;
	}

}
