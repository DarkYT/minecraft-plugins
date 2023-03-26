package fr.dark.sellstick.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dark.sellstick.Core;
import fr.dark.sellstick.SellStick;

public class sellstickCommand implements CommandExecutor {

	Core core;

	public sellstickCommand(Core core) {
		this.core = core;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sellstick")) {

			if(sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						if (p.isOp()) {
							core.reloadConfig();
							p.sendMessage(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.Reload")));
							return true;
						} else {
							p.sendMessage(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.NoPerm")));
							return false;
						}
					} else {
						sender.sendMessage(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.Usage")));
						return false;
					}
				}
			}

			if (args.length == 0 || args.length == 2 || args.length == 3) {
				sender.sendMessage(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.Usage")));
				return false;
			}

			if (args.length == 4) {
				if (args[0].equalsIgnoreCase("give")) {
					Player t = Bukkit.getPlayer(args[1]);
					if (t == null) {
						sender.sendMessage(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.Player")));
						return false;
					} else {
						if (core.utils.isNumeric(args[2])) {
							int qte = Integer.valueOf(args[2]);
							if (args[3].equalsIgnoreCase("i") || core.utils.isNumeric(args[3])) {
								for (int i = 0; i < qte; i++) {
									SellStick stick = new SellStick(core, t);
									t.getInventory().addItem(stick.stick(args[3]));
									t.sendMessage(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.Getting")));
								}
							}
						}
					}
				} else {
					sender.sendMessage(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.Usage")));
					return false;
				}
			}
		}

		return false;
	}

}
