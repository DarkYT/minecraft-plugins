package fr.shiick.redshop.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.shiick.redshop.RedShop;
import fr.shiick.redshop.utils.Menu;

public class shopCmd implements CommandExecutor {

	RedShop shop;
	Menu menu;
	
	public shopCmd(RedShop shop) {
		this.shop = shop;
		menu = new Menu(shop);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			menu.openMain(p);
		}
		return false;
	}

}
