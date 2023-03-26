package fr.darkyt.dark.commands;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.darkyt.dark.Main;

public class CommandPlugin implements CommandExecutor {
	
	private Main main;

	public CommandPlugin(Main main) {
		
		this.main = main;
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("report")){
			
			if(args.length == 0 || args.length == 1){
				String messageError = main.getConfig().getString("commands.report.error");
				
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', messageError));
			}
			
			if(Main.cooldown.containsKey(player.getName())){
				
				int secondes = 300;
				long timeleft = ((Main.cooldown.get(player.getName()) / 1000) + secondes) - (System.currentTimeMillis() / 1000);
				if(timeleft > 0){
					 String messageTime = main.getConfig().getString("commands.report.cooldown");
				     String time = Objects.toString(timeleft, null);
				     String msgTime = messageTime.replace("{0}", time);
				     player.sendMessage(ChatColor.translateAlternateColorCodes('&', msgTime));
					 return true;
				}
				
			}
			
			Main.cooldown.put(player.getName(), System.currentTimeMillis());
			
			
			
			if(args.length == 2){
				
				String cheater = args[0];
				String cheat = args[1];
				for(Player ps : Bukkit.getOnlinePlayers()){
					
					if(ps.hasPermission("staffchat.mod")){
						
						String messageValidate = main.getConfig().getString("commands.report.validate");
					    String msgValidate = messageValidate.replace("{0}", player.getName());
					    String msgValidate1 = msgValidate.replace("{1}", cheater);
					    String msgValidate2 = msgValidate1.replace("{2}", cheat);
						Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msgValidate2));
						return false;
						
					}
				}
				
			}
			
		}
		
		if(cmd.getName().equalsIgnoreCase("helpme")){
			
			if(args.length == 0){
				String messageError = main.getConfig().getString("commands.helpme.error");
				
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', messageError));
			}
			
			if(args.length >= 1){
				
				StringBuilder bc = new StringBuilder();
				for(String part : args){
					bc.append(part + " ");
				}
				
				for(Player ps : Bukkit.getOnlinePlayers()){
					if(ps.hasPermission("staffchat.mod")){
						
						String messageValidate = main.getConfig().getString("commands.helpme.validate");
					    String msgValidate = messageValidate.replace("{0}", player.getName());
					    String msgValidate1 = msgValidate.replace("{1}", bc.toString());
						Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msgValidate1));
						return false;
					}
				}
				
				
			}
			
		}
		
		return false;
	}

}
