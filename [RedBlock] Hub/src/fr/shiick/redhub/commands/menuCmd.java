package fr.shiick.redhub.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.shiick.redhub.RedHub;
import fr.shiick.redhub.utils.Menu;

public class menuCmd implements CommandExecutor {

	RedHub redhub;
	Menu menu;
	
	public menuCmd(RedHub redhub) {
		this.redhub = redhub;
		menu = new Menu(redhub);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			menu.openServers(p);
			return true;
		}
		return false;
	}
	
}