package fr.dark.ram;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dark.ram.commands.ramCommand;
import fr.dark.ram.events.mainListener;

public class Core extends JavaPlugin {
	
	public static File confFile;

	public File customYml = new File(getDataFolder() + "/Rams.yml");
	public FileConfiguration customConfig = YamlConfiguration.loadConfiguration(this.customYml);
	
	@Override
	public void onEnable() {
		confFile = customYml;
		saveCustomYml(customConfig, customYml);
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		if (yc.getConfigurationSection("Rams") == null) {
			yc.createSection("Rams");
		}
		saveCustomYml(yc, Core.confFile);
		if (yc.getConfigurationSection("Plates") == null) {
			yc.createSection("Plates");
		}
		saveCustomYml(yc, Core.confFile);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new mainListener(this), this);
		getCommand("ram").setExecutor(new ramCommand(this));
		saveDefaultConfig();
	}

	public BlockFace CardinalDirection(Player p) {
		float yaw = p.getLocation().getYaw();
		if (yaw < 0) {
			yaw += 360;
		}
		if (yaw >= 315 || yaw < 45) {
			return BlockFace.SOUTH;
		} else if (yaw < 135) {
			return BlockFace.WEST;
		} else if (yaw < 225) {
			return BlockFace.NORTH;
		} else if (yaw < 315) {
			return BlockFace.EAST;
		}
		return BlockFace.NORTH;
	}

	public Location radiusFinding(Location loc, int radius, Material mat) {
		int cx = loc.getBlockX();
		int cy = loc.getBlockY();
		int cz = loc.getBlockZ();

		for (int x = cx - radius; x <= cx + radius; x++) {
			for (int z = cz - radius; z <= cz + radius; z++) {
				for (int y = (cy - radius); y < (cy + radius); y++) {
					double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + ((cy - y) * (cy - y));

					if (dist < radius * radius) {
						Location l = new Location(loc.getWorld(), x, y + 2, z);
						if (l.getBlock().getType() == mat) {
							return l;
						}
					}
				}
			}
		}
		return null;
	}
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setBlockLocation(Material m, byte d, Block b, Location nL){
		nL.getBlock().setType(m);
		nL.getBlock().setData(d);
		nL.getBlock().setBiome(b.getBiome());
		b.setType(Material.AIR);
	}
	
}
