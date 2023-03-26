package fr.shiick.redhub.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.shiick.redhub.RedHub;
import fr.shiick.redhub.utils.Utils;

public class hubSpawnCmd implements CommandExecutor {

	RedHub redhub;
	Utils utils;
	
	public hubSpawnCmd(RedHub redhub) {
		this.redhub = redhub;
		utils = new Utils(redhub);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			YamlConfiguration yc = YamlConfiguration.loadConfiguration(RedHub.spawnFile);
			World world = Bukkit.getWorld(yc.getString("Spawn.World"));
			Location loc = new Location(world, yc.getDouble("Spawn.X"), yc.getDouble("Spawn.Y"), yc.getDouble("Spawn.Z"), yc.getLong("Spawn.YAW"), yc.getLong("Spawn.PITCH"));
			p.teleport(loc);
			String msg = redhub.getConfig().getString("Spawn.Warped");
			p.sendMessage(utils.colorMessage(msg));
			return true;
		}
		return false;
	}

}
