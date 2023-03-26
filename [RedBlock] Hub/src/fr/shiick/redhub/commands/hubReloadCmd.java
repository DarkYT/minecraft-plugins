package fr.shiick.redhub.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.shiick.redhub.RedHub;
import fr.shiick.redhub.utils.Utils;

public class hubReloadCmd implements CommandExecutor {

	RedHub redhub;
	Utils utils;

	public hubReloadCmd(RedHub redhub) {
		this.redhub = redhub;
		utils = new Utils(redhub);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.isOp()) {
				redhub.reloadConfig();
				String msg = redhub.getConfig().getString("Spawn.Reloaded");
				p.sendMessage(utils.colorMessage(msg));
			} else {
				String noPerm = redhub.getConfig().getString("Spawn.NoPerm");
				p.sendMessage(utils.colorMessage(noPerm));
			}
		}
		return false;
	}

}
