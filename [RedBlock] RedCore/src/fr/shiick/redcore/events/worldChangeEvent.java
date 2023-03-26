package fr.shiick.redcore.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import fr.shiick.redcore.RedCore;
import fr.shiick.redcore.utils.Utils;

public class worldChangeEvent implements Listener {

	RedCore core;
	Utils utils;
	
	public worldChangeEvent(RedCore core) {
		this.core = core;
		utils = new Utils(core);
	}
	
	@EventHandler
	public void onWorld(PlayerChangedWorldEvent e) {
		Player p = e.getPlayer();
		World world = p.getWorld();
		if (core.vanished.contains(p) || core.admin.contains(p)) {
			for (Player pl : Bukkit.getOnlinePlayers()) {
				pl.hidePlayer(p);
				if (pl.hasPermission("vanish.admin")) {
					pl.showPlayer(p);
				}
			}
		} else {
			if (world.equals(Bukkit.getWorld("world_the_end")) || world.equals(Bukkit.getWorld("world")) || world.equals(Bukkit.getWorld("world_iles_the_end"))) {
				if (!p.hasPermission("commands.world")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fly " + p.getName() + " off");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "speed walk 1 " + p.getName());
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "god " + p.getName() + " off");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamemode 2 " + p.getName());
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "heal " + p.getName());
				}
			} else {
				if (!p.hasPermission("commands.world")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamemode 0 " + p.getName());
				}
			}
			World from = e.getFrom();
			World isWorld = Bukkit.getWorld(core.getConfig().getString("NetherSethome.Skyblock"));
			World isNether = Bukkit.getWorld(core.getConfig().getString("NetherSethome.Nether"));
			if(from.equals(isWorld) && world.equals(isNether)) {
				YamlConfiguration yc = YamlConfiguration.loadConfiguration(RedCore.confFile);
				if(yc.contains(p.getName() + ".X")) {
					Location loc = new Location(isNether, yc.getLong(p.getName() + ".X"), yc.getLong(p.getName() + ".Y"), yc.getLong(p.getName() + ".Z"), yc.getLong(p.getName() + ".YAW"), yc.getLong(p.getName() + ".PITCH"));
					Block block = loc.getBlock();
					Block above = block.getRelative(BlockFace.UP);
					if(above.getType() != null || above.getType() != Material.AIR) {
						p.teleport(loc);
					} else {
						p.sendMessage(utils.colorMessage(core.getConfig().getString("Message.NetherSethome.Unsafe")));
					}
				}
			}
		}
	}
	
}
