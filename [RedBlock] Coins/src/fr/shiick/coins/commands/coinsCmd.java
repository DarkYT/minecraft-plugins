package fr.shiick.coins.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.shiick.coins.Coins;
import fr.shiick.coins.Utils;

public class coinsCmd implements CommandExecutor {

	Coins coins;
	Utils utils;

	public coinsCmd(Coins coins) {
		this.coins = coins;
		utils = new Utils(coins);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			String usageGive = coins.getConfig().getString("Message.Give.Usage");
			String usageRemove = coins.getConfig().getString("Message.Remove.Usage");
			String usageSet = coins.getConfig().getString("Message.Set.Usage");
			String disconnectedTarget = coins.getConfig().getString("Message.Divers.Disconnected");
			String noPerm = coins.getConfig().getString("Message.Divers.NoPerm");
			String reloaded = coins.getConfig().getString("Message.Divers.Reloaded");
			if (args.length == 0) {
				long money = coins.coinsAPI.getCoins(p);
				String moneyMsg = coins.getConfig().getString("Message.Divers.Money").replaceAll("%coins%", money + "");
				p.sendMessage(utils.colorMessage(moneyMsg));
			}
			if (args.length >= 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if(args[0].equalsIgnoreCase("reload")) {
					if (p.isOp()) {
						coins.reloadConfig();
						p.sendMessage(utils.colorMessage(reloaded));
					} else {
						p.sendMessage(utils.colorMessage(noPerm));
					}
					return true;
				}
				if (target != null) {
					if (args.length == 1) {
						long money = coins.coinsAPI.getCoins(target);
						String moneyMsg = coins.getConfig().getString("Message.Divers.MoneyTarget").replaceAll("%target%", target.getName()).replaceAll("%coins%", money + "");
						p.sendMessage(utils.colorMessage(moneyMsg));
						return true;
					} else {
						if (args[1].equalsIgnoreCase("give")) {
							if (args.length >= 3) {
								if (p.hasPermission("coins.admin")) {
									if (utils.isInteger(args[2])) {
										coins.coinsAPI.addCoins(p, Integer.valueOf(args[2]));
										String successGiveSender = coins.getConfig().getString("Message.Give.Success.Sender").replaceAll("%target%", target.getName()).replaceAll("%coins%", coins.coinsAPI.getCoins(p) + "").replaceAll("%increment%", args[2]);
										String successGiveTarget = coins.getConfig().getString("Message.Give.Success.Target").replaceAll("%coins%", coins.coinsAPI.getCoins(p) + "").replaceAll("%increment%", args[2]);
										p.sendMessage(utils.colorMessage(successGiveSender));
										target.sendMessage(utils.colorMessage(successGiveTarget));
									} else {
										p.sendMessage(utils.colorMessage(usageGive));
									}
								} else {
									p.sendMessage(utils.colorMessage(noPerm));
								}
							} else {
								p.sendMessage(utils.colorMessage(usageGive));
							}
							return true;
						} else if (args[1].equalsIgnoreCase("remove")) {
							if (args.length >= 3) {
								if (p.hasPermission("coins.admin")) {
									if (utils.isInteger(args[2])) {
										coins.coinsAPI.withdrawCoins(p, Integer.valueOf(args[2]));
										String successRemoveSender = coins.getConfig().getString("Message.Remove.Success.Sender").replaceAll("%target%", target.getName()).replaceAll("%coins%", coins.coinsAPI.getCoins(p) + "").replaceAll("%increment%", args[2]);
										String successRemoveTarget = coins.getConfig().getString("Message.Remove.Success.Target").replaceAll("%coins%", coins.coinsAPI.getCoins(p) + "").replaceAll("%increment%", args[2]);
										p.sendMessage(utils.colorMessage(successRemoveSender));
										target.sendMessage(utils.colorMessage(successRemoveTarget));
									} else {
										p.sendMessage(utils.colorMessage(usageRemove));
									}
								} else {
									p.sendMessage(utils.colorMessage(noPerm));
								}
							} else {
								p.sendMessage(utils.colorMessage(usageRemove));
							}
							return true;
						} else if (args[1].equalsIgnoreCase("set")) {
							if (args.length >= 3) {
								if (p.hasPermission("coins.admin")) {
									if (utils.isInteger(args[2])) {
										coins.coinsAPI.setCoins(p, Integer.valueOf(args[2]));
										String successSetSender = coins.getConfig().getString("Message.Set.Success.Sender").replaceAll("%target%", target.getName()).replaceAll("%coins%", Integer.valueOf(args[2]) + "");
										String successSetTarget = coins.getConfig().getString("Message.Set.Success.Target").replaceAll("%coins%", Integer.valueOf(args[2]) + "");
										p.sendMessage(utils.colorMessage(successSetSender));
										target.sendMessage(utils.colorMessage(successSetTarget));
									} else {
										p.sendMessage(utils.colorMessage(usageSet));
									}
								} else {
									p.sendMessage(utils.colorMessage(noPerm));
								}
								
							} else {
								p.sendMessage(utils.colorMessage(usageSet));
							}
							return true;
						} else if (args[1].equalsIgnoreCase("reset")) {
							if (p.hasPermission("coins.admin")) {
								coins.coinsAPI.resetCoins(p);
								String successResetSender = coins.getConfig().getString("Message.Reset.Success.Sender").replaceAll("%target%", target.getName());
								String successResetTarget = coins.getConfig().getString("Message.Reset.Success.Target");
								p.sendMessage(utils.colorMessage(successResetSender));
								target.sendMessage(utils.colorMessage(successResetTarget));
							} else {
								p.sendMessage(utils.colorMessage(noPerm));
							}
							return true;
						}
					}
				} else {
					p.sendMessage(utils.colorMessage(disconnectedTarget));
					return true;
				}
			}
		}
		return false;
	}

}
