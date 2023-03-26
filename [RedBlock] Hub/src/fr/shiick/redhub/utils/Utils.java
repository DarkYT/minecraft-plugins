package fr.shiick.redhub.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.shiick.redhub.RedHub;

public class Utils {

	RedHub redhub;

	public Utils(RedHub redhub) {
		this.redhub = redhub;
	}

	public String colorMessage(String msg) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		return message;
	}

	public void teleportServer(Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.sendPluginMessage(redhub, "BungeeCord", b.toByteArray());
		String msg = redhub.getConfig().getString("Menu.Message").replaceAll("%server%", server);
		p.sendMessage(colorMessage(msg));
	}

	public List<String> lore(String path) {
		List<String> lore = new ArrayList<String>();
		for (String lores : redhub.getConfig().getConfigurationSection(path).getKeys(false)) {
			lore.add(colorMessage(redhub.getConfig().getString(path + "." + lores)));
		}
		return lore;
	}

	public void selectAction(String path, Player p) {
		String type = redhub.getConfig().getString(path + ".Type");
		if (type.equalsIgnoreCase("SERVER")) {
			String server = redhub.getConfig().getString(path + ".Bungee");
			teleportServer(p, server);
		} else if (type.equalsIgnoreCase("MESSAGE")) {
			String message = redhub.getConfig().getString(path + ".Message");
			p.sendMessage(colorMessage(message));
		} else if (type.equalsIgnoreCase("COMMAND")) {
			String command = redhub.getConfig().getString(path + ".Command");
			p.performCommand(command);
		}
	}

}