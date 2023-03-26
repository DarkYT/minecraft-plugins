package fr.shiick.recycleur.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.shiick.recycleur.Recycleur;

public class recycleurCmd implements CommandExecutor {

	Recycleur recycleur;
	
	public recycleurCmd(Recycleur recycleur) {
		this.recycleur = recycleur;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				recycleur.openRecycleur(p);
			} else if(args[0].equalsIgnoreCase("open")) {
				recycleur.openRecycleur(p);
			} else if(args[0].equalsIgnoreCase("reload")) {
				if(p.hasPermission("recycleur.admin")) {
					recycleur.reloadConfig();
					p.sendMessage(recycleur.colorMessage(recycleur.getConfig().getString("Recycleur.ConfigReload")));
				} else {
					p.sendMessage(recycleur.colorMessage(recycleur.getConfig().getString("Recycleur.NoAdminPerm")));
				}
			}
		}
		return false;
	}

}
