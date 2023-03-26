package fr.darkyt.report.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.darkyt.report.Main;

public class CommandReport implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("report")){
			
			if(args.length == 0 || args.length == 1){
				player.sendMessage("La commande est: /report <nom du joueur> <raison>");
			}
			
			if(Main.cooldown.containsKey(player.getName())){
				
				int secondes = 300;
				long timeleft = ((Main.cooldown.get(player.getName()) / 1000) + secondes) - (System.currentTimeMillis() / 1000);
				if(timeleft > 0){
					player.sendMessage("§l§fTu dois attendre encore §4" + timeleft + "s §fpour report un joueur");
					return true;
				}
				
			}
			
			Main.cooldown.put(player.getName(), System.currentTimeMillis());
			
			
			
			if(args.length == 2){
				
				String cheater = args[0];
				String cheat = args[1];
				for(Player ps : Bukkit.getOnlinePlayers()){
					if(ps.hasPermission("staffchat.mod")){
						Bukkit.broadcastMessage("§l§f" + player.getName() + " a report §4" + cheater + " §fpour §4" + cheat);
					}
				}
				
			}
			
		}
		
		return false;
	}

}
