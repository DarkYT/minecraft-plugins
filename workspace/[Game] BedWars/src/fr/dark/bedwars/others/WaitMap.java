package fr.dark.bedwars.others;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class WaitMap {
	
	String world;
	Location spawn;
	
	public WaitMap(String world, Location spawn) {
		this.world = world;
		this.spawn = spawn;
	}
	
	public World getWorld() {
		return Bukkit.getWorld(world);
	}
	
	public Location getSpawn() {
		return spawn;
	}

}
