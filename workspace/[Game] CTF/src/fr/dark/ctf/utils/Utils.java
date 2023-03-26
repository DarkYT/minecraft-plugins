package fr.dark.ctf.utils;

import org.bukkit.ChatColor;

import fr.dark.ctf.CTF;

public class Utils {

	CTF ctf;

	public Utils(CTF ctf) {
		this.ctf = ctf;
	}

	public String colorMessage(String msg) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		return message;
	}
	
	@SuppressWarnings("unused")
	public String getID() {
		int id = 0;
		for(String ids : ctf.getConfig().getConfigurationSection("Doors").getKeys(false)) {
			id++;
		}
		return id + "";
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
