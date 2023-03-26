package fr.shiick.redcore.commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.shiick.redcore.RedCore;
import fr.shiick.redcore.utils.Utils;

public class sethomeCmd implements CommandExecutor {

	RedCore core;
	Utils utils;
	
	public sethomeCmd(RedCore core) {
		this.core = core;
		utils = new Utils(core);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			YamlConfiguration yc = YamlConfiguration.loadConfiguration(RedCore.confFile);
			World w = p.getWorld();
			if(w.equals(Bukkit.getWorld(core.getConfig().getString("NetherSethome.Nether")))) {
				Location loc = p.getLocation();
				Location newLoc = new Location(w, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
				Block block = newLoc.getBlock();
				Block above = block.getRelative(BlockFace.UP);
				if(above.getType() != null || above.getType() != Material.AIR) {
					yc.set(p.getName() + ".X", p.getLocation().getX());
					yc.set(p.getName() + ".Y", p.getLocation().getY());
					yc.set(p.getName() + ".Z", p.getLocation().getZ());
					yc.set(p.getName() + ".YAW", p.getLocation().getYaw());
					yc.set(p.getName() + ".PITCH", p.getLocation().getPitch());
					try {
						yc.save(RedCore.confFile);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					String msg = core.getConfig().getString("Message.NetherSethome.Created");
					p.sendMessage(utils.colorMessage(msg));
				} else {
					String msg = core.getConfig().getString("Message.NetherSethome.Unsafe");
					p.sendMessage(utils.colorMessage(msg));
				}
			} else {
				String msg = core.getConfig().getString("Message.NetherSethome.WrondWorld");
				p.sendMessage(utils.colorMessage(msg));
			}
		}
		return false;
	}

}
