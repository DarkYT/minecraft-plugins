package fr.dark.report.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dark.report.Core;

public class reportPlayerCommand implements CommandExecutor {

	Core core;
	public reportPlayerCommand(Core core) {this.core = core;}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("report")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(args.length == 0 || args.length == 1 || args.length == 2){
					p.sendMessage(core.utils.colorMessage(core.getConfig().getString("Report.Messages.Error.IncorrectUsage")));
					return false;
				}
				Player t = Bukkit.getPlayer(args[0]);
				if(t == null){
					p.sendMessage(core.utils.colorMessage(core.getConfig().getString("Report.Messages.Error.NotPlayer")));
					return false;
				}
				if(args.length >= 3){
					StringBuilder bc = new StringBuilder();
					for(int i = 2; i<args.length;i++){
						bc.append(args[i] + " ");
					}
					Core.sql.createAccount(t, core.getConfig().getString("Report.ServerName"), bc.toString(), args[1], p);
				}
			}
		}
		return false;
	}

}
