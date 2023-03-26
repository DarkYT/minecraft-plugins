package fr.darkyt.nightvision.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import fr.darkyt.nightvision.Main;

public class CommandNv implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		

		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("nightvision")){
			if(player.hasPermission("essentials.kits.corrompu")){
				
				if(Main.nightvision.contains(player)){
					
					if(player.hasPotionEffect(PotionEffectType.NIGHT_VISION)){
						
						Main.removeNV(player);
						
					}else {
						
						player.sendMessage("§cVous n'avez pas l'effet NightVision");
						
					}
					
				}else {
					
					Main.addNV(player);
					
				}		
			}			
		}
		return false;
	}

}
