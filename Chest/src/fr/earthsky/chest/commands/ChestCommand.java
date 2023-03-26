package fr.earthsky.chest.commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.earthsky.chest.Base64;
import fr.earthsky.chest.Main;

public class ChestCommand implements CommandExecutor {
	
	FileConfiguration config;
	Main main;
	Base64 base64;
	
	public ChestCommand(Main main, Base64 base64) {
		this.base64 = base64;
		this.main = main;
		this.config = main.getConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("chest")){
			Inventory chest = Bukkit.createInventory(null, 54);
			String inv = Base64.toBase64(chest);
			Inventory inventory = null;
			try {
				inventory = Base64.fromBase64(inv);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.openInventory(inventory);
		}
		return false;
	}

}
