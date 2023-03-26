package fr.shiick.redcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.shiick.redcore.RedCore;
import fr.shiick.redcore.utils.Utils;

public class hideCmd implements CommandExecutor {

	RedCore core;
	Utils utils;

	public hideCmd(RedCore core) {
		this.core = core;
		utils = new Utils(core);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			String joinMsg = core.getEssConfig().getString("custom-join-message");
			String quitMsg = core.getEssConfig().getString("custom-quit-message");
			if(p.hasPermission("vanish.admin")) {
				if(core.admin.contains(p)) {
					core.admin.remove(p);
					Bukkit.broadcastMessage(utils.colorMessage(joinMsg.replace("{USERNAME}", p.getName())));
					for(Player pl : Bukkit.getOnlinePlayers()) {
						pl.showPlayer(p);
					}
				} else {
					core.admin.add(p);
					Bukkit.broadcastMessage(utils.colorMessage(quitMsg.replace("{USERNAME}", p.getName())));
					for (Player pl : Bukkit.getOnlinePlayers()) {
						pl.hidePlayer(p);
						if(pl.hasPermission("vanish.admin")) {
							pl.showPlayer(p);
						}
					}
				}
			} else {
				String noPerm = core.getConfig().getString("Message.NoPerm");
				p.sendMessage(utils.colorMessage(noPerm));
			}
		}
		return false;
	}

}
