package fr.dark.staffmode.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dark.staffmode.StaffMode;
import fr.dark.staffmode.utils.Utils;

public class staffCommand implements CommandExecutor {

	StaffMode core;
	public staffCommand(StaffMode staffMode) {
		this.core = staffMode;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equals("staffmode")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.hasPermission("staffmode.use")) {
					if(core.modeUsers.containsKey(p)) {
						core.removeModInterface(p);
						Utils.sendColorMessage(p, core.getConfig().getString("StaffMode.Config.Disable"));
						return true;
					}else {
						core.giveModInterface(p);
						Utils.sendColorMessage(p, core.getConfig().getString("StaffMode.Config.Enable"));
						return true;
					}
				}else {
					Utils.sendColorMessage(p, core.getConfig().getString("StaffMode.Config.NoPerm"));
					return false;
				}
			}
		}
		return false;
	}

}
