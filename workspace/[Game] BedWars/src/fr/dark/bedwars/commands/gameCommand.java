package fr.dark.bedwars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dark.bedwars.BedWars;
import fr.dark.bedwars.tasks.GameAutoStart;

public class gameCommand implements CommandExecutor {

	BedWars core;
	public gameCommand(BedWars core) {
		this.core = core;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("bedwars.manage")) {
				if(args.length >= 1) {
					if(args[0].equalsIgnoreCase("tp")){
						if(args[1].equalsIgnoreCase("game")) {
							p.teleport(core.activeMap.getSpawn());
							p.sendMessage("§a[§2Bed§aWars] §2Vous venez de vous téléporter dans la map de jeu");
						}else if(args[1].equalsIgnoreCase("lobby")) {
							p.teleport(core.lobby.getSpawn());
							p.sendMessage("§a[§2Bed§aWars] §2Vous venez de vous téléporter au lobby");
						}
					}else if(args[0].equalsIgnoreCase("launch")) {
						GameAutoStart start = new GameAutoStart(core, true);
						start.runTaskTimer(core, 20, 20);
					}
				}else {
					p.sendMessage("§c[§4Bed§cWars] §4 Mauvais usage");
				}
			}
		}
		return false;
	}

}
