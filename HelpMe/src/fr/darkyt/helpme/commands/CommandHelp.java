package fr.darkyt.helpme.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHelp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("helpme")){
			
			if(args.length == 0){
				player.sendMessage("§4La commande est: /helpme <demande>");
			}
			
			if(args.length >= 1){
				
				StringBuilder bc = new StringBuilder();
				for(String part : args){
					bc.append(part + " ");
				}
				
				for(Player ps : Bukkit.getOnlinePlayers()){
					if(ps.hasPermission("staffchat.mod")){
						Bukkit.broadcastMessage("§l§a" + player.getName() + " demande de l'aide >> §f" + bc.toString());
					}
				}
				
				
			}
			
		}
		
		return false;
	}

}
