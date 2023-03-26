package fr.earthsky.silkspawner.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.earthsky.silkspawner.SilkSpawner;

public class earthSpawner implements CommandExecutor {

	SilkSpawner silkspawner;
	
	public earthSpawner(SilkSpawner ss){
		silkspawner = ss;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("earthspawner")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.hasPermission("earthspawner.admin")){ //earthspawner give pseudo mob nombre
					if(args.length == 0){
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLa commande est: /es help"));
					}
					if(args[0].equalsIgnoreCase("give")){
						
					} else if(args[0].equalsIgnoreCase("help")){
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
					} else if(args[0].equalsIgnoreCase("list")){
						
					}
				}
			}
		}
		return false;
	}

}
