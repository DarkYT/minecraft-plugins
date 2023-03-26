package fr.dark.ctf.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dark.ctf.CTF;

public class ctfCommand implements CommandExecutor {

	CTF core;
	public ctfCommand(CTF ctf) {
		this.core = ctf;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ctf")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(args.length == 0){
					if(p.isOp()){
						for(String help : core.getConfig().getStringList("CTF.Command.Help.Op")){
							p.sendMessage(core.utils.colorMessage(help));
						}
					}else{
						for(String help : core.getConfig().getStringList("CTF.Command.Help.notOp")){
							p.sendMessage(core.utils.colorMessage(help));
						}
					}
				}
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("reload")){
						if(p.isOp()){
							core.reloadConfig();
							p.sendMessage(core.utils.colorMessage(core.getConfig().getString("CTF.Command.Reload")));
						}else{
							return false;
						}
					}else if(args[0].equalsIgnoreCase("help")){
						if(p.isOp()){
							for(String help : core.getConfig().getStringList("CTF.Command.Help.Op")){
								p.sendMessage(core.utils.colorMessage(help));
							}
						}else{
							for(String help : core.getConfig().getStringList("CTF.Command.Help.notOp")){
								p.sendMessage(core.utils.colorMessage(help));
							}
						}
					}else if(args[0].equalsIgnoreCase("edit")){
						if(p.isOp()){
							if(!(core.isEditor(p))){
								core.setEditor(p);
								p.sendMessage(core.utils.colorMessage(core.getConfig().getString("CTF.Command.Editor.Set")));
							}else{
								core.removeEditor(p);
								p.sendMessage(core.utils.colorMessage(core.getConfig().getString("CTF.Command.Editor.Remove")));
							}
						}else{
							return false;
						}
					}else if(args[0].equalsIgnoreCase("setspawn")){
						if(p.isOp()){
							core.setSpawn(p.getLocation());
							p.sendMessage(core.utils.colorMessage(core.getConfig().getString("CTF.Command.Spawn.Set")));
						}else{
							return false;
						}
					}else if(args[0].equalsIgnoreCase("spawn")){
						p.teleport(core.getSpawn());
						p.sendMessage(core.utils.colorMessage(core.getConfig().getString("CTF.Command.Spawn.Teleport")));
					}
				}
			}else{
				sender.sendMessage(core.utils.colorMessage(core.getConfig().getString("CTF.Command.Deny.Console")));
			}
		}
		return false;
	}

}
