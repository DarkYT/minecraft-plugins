package fr.shiick.redcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.shiick.redcore.RedCore;
import fr.shiick.redcore.utils.Utils;

public class reloadCmd implements CommandExecutor {

	RedCore core;
	Utils utils;
	
	public reloadCmd(RedCore core) {
		this.core = core;
		utils = new Utils(core);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.isOp()) {
				core.reloadConfig();
				String msg = core.getConfig().getString("Message.Reload");
				p.sendMessage(utils.colorMessage(msg));
			} else {
				String noPerm = core.getConfig().getString("Message.NoPerm");
				p.sendMessage(utils.colorMessage(noPerm));
			}
		}
		return false;
	}

}
