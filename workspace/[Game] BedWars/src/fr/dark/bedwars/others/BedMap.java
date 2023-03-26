package fr.dark.bedwars.others;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class BedMap {
	
	String world;
	Location spawn;
	
	public BedMap(String world, Location spawn) {
		this.world = world;
		this.spawn = spawn;
	}
	
	public World getWorld() {
		return Bukkit.getServer().getWorld(world);
	}

	public Location getSpawn() {
		return spawn;
	}

}
