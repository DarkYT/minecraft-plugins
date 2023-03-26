package fr.blizzrpg.classsystem.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.blizzrpg.classsystem.ClassAPI;

public class classe implements CommandExecutor {

	ClassAPI classapi;
	
	public classe(ClassAPI capi){
		classapi = capi;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			YamlConfiguration yc = YamlConfiguration.loadConfiguration(ClassAPI.confFile);
			Player p = (Player) sender;
			if(!(yc.contains(p.getName() + ".Class"))){
				classapi.menuClass(p);
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous avez déjà sélectionné une classe !"));
			}
		}
		return false;
	}

}
