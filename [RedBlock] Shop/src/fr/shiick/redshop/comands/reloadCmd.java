package fr.shiick.redshop.comands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.shiick.redshop.RedShop;
import fr.shiick.redshop.utils.Utils;

public class reloadCmd implements CommandExecutor {

	RedShop shop;
	Utils utils;
	
	public reloadCmd(RedShop shop) {
		this.shop = shop;
		utils = new Utils(shop);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.isOp()) {
				shop.reloadConfig();
				String msg = shop.getConfig().getString("Message.Reload");
				p.sendMessage(utils.colorMessage(msg));
			} else {
				String noPerm = shop.getConfig().getString("Message.NoPerm");
				p.sendMessage(utils.colorMessage(noPerm));
			}
		}
		return false;
	}

}
