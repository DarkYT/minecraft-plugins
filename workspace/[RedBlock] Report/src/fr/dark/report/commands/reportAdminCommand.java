package fr.dark.report.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dark.report.Core;

public class reportAdminCommand implements CommandExecutor {

	Core core;

	public reportAdminCommand(Core core) {
		this.core = core;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (cmd.getName().equalsIgnoreCase("areport")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 0) {
					if (p.hasPermission("report.check")) {
						core.openReport(p);
					} else {
						return false;
					}
				}
				if (args.length == 1) {
					if (p.hasPermission("report.admin")) {
						p.sendMessage(core.utils.colorMessage(core.getConfig().getString("Report.AReport.ChatMessage.Del")));
					}
				}
				if (args.length == 2) {
					if (p.hasPermission("report.admin")) {
						if(args[1].equalsIgnoreCase("all")) {
							Core.sql.removeAll();
							core.utils.colorMessage(core.getConfig().getString("Report.AReport.ChatMessage.DelAllSuccess"));
						}else {
							OfflinePlayer pl = Bukkit.getOfflinePlayer(args[1]);
							if(pl == null) {
								p.sendMessage(core.utils.colorMessage(core.getConfig().getString("Report.AReport.ChatMessage.Del")));
							}else {
								p.sendMessage(core.utils.modifyMessage(core.getConfig().getString("Report.AReport.ChatMessage.RemoveSuccess"), "<player>", pl.getName()));
								Core.sql.removePlayer(pl);
							}
						}
					}
				}
			}
		}
		return false;
	}

}
