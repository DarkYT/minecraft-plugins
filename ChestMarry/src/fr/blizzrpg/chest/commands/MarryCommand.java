package fr.blizzrpg.chest.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.blizzrpg.chest.Main;

public class MarryCommand implements CommandExecutor {

	Main main;
	public MarryCommand(Main m) {
		main = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.equals("marryme")){
			
		}
		return false;
	}

}
