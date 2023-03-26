package fr.earthsky.silkspawner.commands;

import org.bukkit.Bukkit;
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
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("earthspawner")){ //es "give" {pseudo} "mob" (nombre) !! es "help" !! es "list"   "" = text {} = joueur () = int
			if(sender instanceof Player){
				if(p.hasPermission("earthspawner.admin")){
					if(args.length == 0){
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c'Spèce de noob '-' fais /es help"));
					}
					if(args[0].equalsIgnoreCase("help")){
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l-------&eEarthSpawners&a-------"));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l/es give <pseudo> <mob> <quantité>"));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l/es list"));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l/es help"));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l-------------------------------"));
						
					}else if(args[0].equalsIgnoreCase("list")){
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&fListe des mobs: &f"));
					}else if(args[0].equalsIgnoreCase("give")){
						if(args.length == 1 || args.length == 2 || args.length == 3){
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cFais /es help !"));
						}
						if(args.length == 4){
							Player target = Bukkit.getPlayerExact(args[1]);
							if(target != null){
								int amount = Integer.parseInt(args[3]);
								switch(args[2]){
								case "cow":
									silkspawner.getSpawner(target, "cow", amount);
									break;
								case "zombie":
									silkspawner.getSpawner(target, "zombie", amount);
									break;
								case "skeleton":
									silkspawner.getSpawner(target, "skeleton", amount);
									break;
								case "spider":
									silkspawner.getSpawner(target, "spider", amount);
									break;
								case "creeper":
									silkspawner.getSpawner(target, "creeper", amount);
									break;
								case "slime":
									silkspawner.getSpawner(target, "slime", amount);
									break;
								case "iron_golem":
									silkspawner.getSpawner(target, "iron_golem", amount);
									break;
								case "pig":
									silkspawner.getSpawner(target, "pig", amount);
									break;
								default:
									break;
								}
							}else{
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Joueur hors ligne ! :)"));
							}
						}
					}
				}else{
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cT'as pas les perms' ! C'est bêta ^^"));
				}
			}
				
return false;
	}
}
