package fr.redblock.furnace.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.redblock.furnace.Core;

public class furnaceCommand implements CommandExecutor {

	Core core;
	public furnaceCommand(Core core) {
		this.core = core;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("furnace")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				core.openFurnace(p);
			}
		}
		return false;
	}

}
