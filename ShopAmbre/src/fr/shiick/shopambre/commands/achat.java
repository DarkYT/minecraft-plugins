package fr.shiick.shopambre.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.shiick.shopambre.Core;

public class achat implements CommandExecutor {

	Core core;
	
	public achat(Core c) {
		core = c;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("achat")){
				core.openMain(p);
			}
		}
		return false;
	}

}
