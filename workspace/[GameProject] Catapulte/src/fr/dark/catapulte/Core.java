package fr.dark.catapulte;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Zombie;
import org.bukkit.material.Button;
import org.bukkit.material.Lever;
import org.bukkit.material.Stairs;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import fr.dark.catapulte.commands.mainCommand;
import fr.dark.catapulte.events.mainListener;

public class Core extends JavaPlugin {

	public static File confFile;

	public File customYml = new File(getDataFolder() + "/Catapulte.yml");
	public FileConfiguration customConfig = YamlConfiguration.loadConfiguration(this.customYml);
	
	@Override
	public void onEnable() {
		confFile = customYml;
		saveCustomYml(customConfig, customYml);
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		if (yc.getConfigurationSection("Catapulte") == null) {
			yc.createSection("Catapulte");
		}
		saveCustomYml(yc, Core.confFile);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new mainListener(this), this);
		getCommand("catapulte").setExecutor(new mainCommand(this));
	}
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void spawnStairs(BlockFace f, Material m, Location loc, boolean flipped){
		Block b = loc.getBlock();
		b.setType(m);
		BlockState state = b.getState();
	       
        Stairs stairs = (Stairs) state.getData();
        stairs.setFacingDirection(f);
        stairs.setInverted(flipped);
       
        state.setData(stairs);
        state.update(false, false);
	}
	
	public static void spawnButton(BlockFace f, Material m, Location loc){
		Block b = loc.getBlock();
		b.setType(m);
		BlockState state = b.getState();
	       
        Button stairs = (Button) state.getData();
        stairs.setFacingDirection(f);
       
        state.setData(stairs);
        state.update(false, false);
	}
	
	public static void spawnLever(BlockFace f, Material m, Location loc){
		Block b = loc.getBlock();
		b.setType(m);
		BlockState state = b.getState();
	       
        Lever stairs = (Lever) state.getData();
        stairs.setFacingDirection(f);
       
        state.setData(stairs);
        state.update(false, false);
	}
	
	@SuppressWarnings("deprecation")
	public static void spawnWood(byte b, Material m, Location loc){
		Block block = loc.getBlock();
		block.setType(m);
		block.setData(b);
	}
	
	public Vector rotateYAxis(Vector dir, double angleD) {
		double angleR = Math.toRadians(angleD);
		double x = dir.getX();
		double z = dir.getZ();
		double cos = Math.cos(angleR);
		double sin = Math.sin(angleR);
		return (new Vector(x * cos + z * (-sin), 0.0, x * sin + z * cos)).normalize();
	}

	public void shoot(Zombie player, Location location, Vector direction, int[] angles, double speed) {
		direction.normalize();
		Vector dirY = (new Location(location.getWorld(), 0, 0, 0, location.getYaw(), 0)).getDirection().normalize();
		for (int angle : angles) {
			Vector vec;
			if (angle != 0) {
				vec = rotateYAxis(dirY, angle);
				vec.multiply(Math.sqrt(vec.getX() * vec.getX() + vec.getZ() * vec.getZ())).subtract(dirY);
				vec = direction.clone().add(vec).normalize();
			} else {
				vec = direction.clone();
			}
			Snowball snowball = location.getWorld().spawn(location, Snowball.class);
			snowball.setShooter(player);
			snowball.setVelocity(vec.clone().multiply(speed));
		}
	}
}
