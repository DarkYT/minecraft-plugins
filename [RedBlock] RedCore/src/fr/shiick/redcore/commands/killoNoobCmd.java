package fr.shiick.redcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.shiick.redcore.RedCore;
import fr.shiick.redcore.utils.Utils;

public class killoNoobCmd implements CommandExecutor {

	RedCore core;
	Utils utils;
	
	public killoNoobCmd(RedCore core) {
		this.core = core;
		utils = new Utils(core);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("killonoob.get")) {
				if (utils.isFull(p) == false) {
					p.getInventory().addItem(utils.getKillONoob());
					String msg = core.getConfig().getString("Message.KillONoob.Recieved");
					p.sendMessage(utils.colorMessage(msg));
				} else {
					String msg = core.getConfig().getString("Message.KillONoob.Full");
					p.sendMessage(utils.colorMessage(msg));
				}
			} else {
				String msg = core.getConfig().getString("Message.NoPerm");
				p.sendMessage(utils.colorMessage(msg));
			}
		}
		return false;
	}

}
