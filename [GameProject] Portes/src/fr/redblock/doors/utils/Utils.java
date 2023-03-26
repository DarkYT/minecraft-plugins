package fr.redblock.doors.utils;

import fr.redblock.doors.Core;
import net.md_5.bungee.api.ChatColor;

public class Utils {

	Core core;
	
	public Utils(Core core) {
		this.core = core;
	}
	
	public String colorMessage(String msg) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		return message;
	}
	
	@SuppressWarnings("unused")
	public String getID() {
		int id = 0;
		for(String ids : core.getConfig().getConfigurationSection("Doors").getKeys(false)) {
			id++;
		}
		return id + "";
	}
	
}
