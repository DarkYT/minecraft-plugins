package fr.shiick.redcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.shiick.redcore.RedCore;

public class inventoryCommand implements CommandExecutor {

	RedCore core;
	public inventoryCommand(RedCore redCore) {this.core = redCore;}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("invcheck")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length > 0) {
					return false;
				}
				YamlConfiguration yc = YamlConfiguration.loadConfiguration(RedCore.confFile);
				String uuid = p.getUniqueId().toString();
				boolean isEnable = yc.getBoolean("Preferences."+uuid);
				if(isEnable) {
					yc.set("Preferences."+uuid, false);
					core.saveConfig(yc, RedCore.confFile);
					p.sendMessage(core.utils.colorMessage(core.getConfig().getString("InventoryFull.Messages.Disable")));
					return true;
				}else {
					yc.set("Preferences."+uuid, true);
					core.saveConfig(yc, RedCore.confFile);
					p.sendMessage(core.utils.colorMessage(core.getConfig().getString("InventoryFull.Messages.Enable")));
					return true;
				}
				
			}else {
				sender.sendMessage(core.utils.colorMessage(core.getConfig().getString("InventoryFull.Messages.Console")));
				return false;
			}
		}
		return false;
	}

}
