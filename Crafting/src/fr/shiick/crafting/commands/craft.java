package fr.shiick.crafting.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import fr.shiick.crafting.Crafting;

public class craft implements CommandExecutor, Listener {
	
	Crafting crafting;
	
	public craft(Crafting c) {
		crafting = c;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("crafting")){
				crafting.openCraft(p);
			}
		}
		return false;
	}

}
