package fr.shiick.redhub.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.shiick.redhub.RedHub;
import fr.shiick.redhub.utils.Utils;

public class hubSetSpawnCmd implements CommandExecutor {

	RedHub redhub;
	Utils utils;

	public hubSetSpawnCmd(RedHub redhub) {
		this.redhub = redhub;
		utils = new Utils(redhub);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.isOp()) {
				YamlConfiguration yc = YamlConfiguration.loadConfiguration(RedHub.spawnFile);
				yc.set("Spawn.World", p.getWorld().getName());
				yc.set("Spawn.X", p.getLocation().getX());
				yc.set("Spawn.Y", p.getLocation().getY());
				yc.set("Spawn.Z", p.getLocation().getZ());
				yc.set("Spawn.YAW", p.getLocation().getYaw());
				yc.set("Spawn.PITCH", p.getLocation().getPitch());
				try {
					yc.save(RedHub.spawnFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				String msg = redhub.getConfig().getString("Spawn.Set");
				p.sendMessage(utils.colorMessage(msg));
				return true;
			} else {
				String noPerm = redhub.getConfig().getString("Spawn.NoPerm");
				p.sendMessage(utils.colorMessage(noPerm));
			}
		}
		return false;
	}

}
