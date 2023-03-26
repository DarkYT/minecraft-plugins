package fr.dark.semirp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dark.semirp.Core;

public class rpCommand implements CommandExecutor {

	Core core;
	public rpCommand(Core core) {
		this.core = core;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("semirp")){
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("reload")){
						if(p.isOp()){
							core.reloadConfig();
							p.sendMessage(core.utils.colorMessage(core.getConfig().getString("SemiRP.Command.Reload")));
						}else{
							return false;
						}
					}else if(args[0].equalsIgnoreCase("setspawn")){
						if(p.isOp()){
							core.setSpawn(p.getLocation());
							p.sendMessage(core.utils.colorMessage(core.getConfig().getString("SemiRP.Command.Spawn.Set")));
						}else{
							return false;
						}
					}else if(args[0].equalsIgnoreCase("spawn")){
						p.teleport(core.getSpawn());
						p.sendMessage(core.utils.colorMessage(core.getConfig().getString("SemiRP.Command.Spawn.Teleport")));
					}
				}
			}
		}
		return false;
	}

}
