package fr.dark.report.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dark.report.Core;

public class reportAdminCommand implements CommandExecutor {

	Core core;
	public reportAdminCommand(Core core) {this.core = core;}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("areport")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.hasPermission("report.check")){
					core.openReport(p);
				}else{
					return false;
				}
			}
		}
		return false;
	}

}
