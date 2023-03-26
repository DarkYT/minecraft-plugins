package fr.dark.ctf;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.material.Button;
import org.bukkit.material.Lever;
import org.bukkit.material.Stairs;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dark.ctf.commands.ctfCommand;
import fr.dark.ctf.commands.editCommand;
import fr.dark.ctf.enums.Doors;
import fr.dark.ctf.enums.GameState;
import fr.dark.ctf.events.interactEvent;
import fr.dark.ctf.events.playerJoinListener;
import fr.dark.ctf.machines.Door;
import fr.dark.ctf.utils.Utils;

public class CTF extends JavaPlugin {
	
	public Utils utils = new Utils(this);
	public Door door = new Door();
	Map<Player, Integer> edition = new HashMap<>();
	public Map<String, Doors> type = new HashMap<>();
	GameState current;
	public static File confFile;

	public File customYml = new File(getDataFolder() + "/Machines.yml");
	public FileConfiguration customConfig = YamlConfiguration.loadConfiguration(this.customYml);
	
	@Override
	public void onEnable() {
		confFile = customYml;
		saveCustomYml(customConfig, customYml);
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		if (yc.getConfigurationSection("Catapulte") == null) {
			yc.createSection("Catapulte");
		}
		if (yc.getConfigurationSection("Ram") == null) {
			yc.createSection("Ram");
		}
		if (yc.getConfigurationSection("Plates") == null) {
			yc.createSection("Plates");
		}
		if (yc.getConfigurationSection("Baliste") == null) {
			yc.createSection("Baliste");
		}
		saveCustomYml(yc, CTF.confFile);
		
		current = GameState.WAITING;
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new interactEvent(this), this);
		pm.registerEvents(new playerJoinListener(this), this);
		getCommand("ctfedit").setExecutor(new editCommand(this));
		getCommand("ctf").setExecutor(new ctfCommand(this));
		saveDefaultConfig();
	}
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GameState getState() {
		return current;
	}
	
	public boolean isState(GameState state) {
		return current == state;
	}
	
	public void setState(GameState state) {
		current = state;
	}
	
	public void setEditor(Player p){
		edition.put(p, 1);
	}
	
	public boolean isEditor(Player p){
		if(edition.isEmpty()) {
			return false;
		}else {
			return edition.containsKey(p);
		}
	}
	
	public void removeEditor(Player p){
		if(isEditor(p)){
			edition.remove(p);
		}
	}
	
	public Location getSpawn(){
		World w = Bukkit.getWorld(getConfig().getString("CTFCore.Spawn.World"));
		double x = getConfig().getDouble("CTFCore.Spawn.X");
		double y = getConfig().getDouble("CTFCore.Spawn.Y");
		double z = getConfig().getDouble("CTFCore.Spawn.Z");
		float yaw = (float) getConfig().getInt("CTFCore.Spawn.Yaw");
		float pitch = (float) getConfig().getInt("CTFCore.Spawn.Pitch");
		
		return new Location(w,x,y,z,yaw,pitch);
	}
	
	public void setSpawn(Location loc){
		getConfig().set("CTFCore.Spawn.World", loc.getWorld().getName());
		getConfig().set("CTFCore.Spawn.X", loc.getX());
		getConfig().set("CTFCore.Spawn.Y", loc.getY());
		getConfig().set("CTFCore.Spawn.Z", loc.getZ());
		getConfig().set("CTFCore.Spawn.Yaw", loc.getYaw());
		getConfig().set("CTFCore.Spawn.Pitch", loc.getPitch());
		saveConfig();
	}
	
	public void spawnStairs(BlockFace f, Material m, Location loc, boolean flipped){
		Block b = loc.getBlock();
		b.setType(m);
		BlockState state = b.getState();
	       
        Stairs stairs = (Stairs) state.getData();
        stairs.setFacingDirection(f);
        stairs.setInverted(flipped);
       
        state.setData(stairs);
        state.update(false, false);
	}
	
	public void spawnButton(BlockFace f, Material m, Location loc){
		Block b = loc.getBlock();
		b.setType(m);
		BlockState state = b.getState();
	       
        Button stairs = (Button) state.getData();
        stairs.setFacingDirection(f);
       
        state.setData(stairs);
        state.update(false, false);
	}
	
	public void spawnLever(BlockFace f, Material m, Location loc){
		Block b = loc.getBlock();
		b.setType(m);
		BlockState state = b.getState();
	       
        Lever stairs = (Lever) state.getData();
        stairs.setFacingDirection(f);
       
        state.setData(stairs);
        state.update(false, false);
	}
	
	@SuppressWarnings("deprecation")
	public void spawnWood(byte b, Material m, Location loc){
		Block block = loc.getBlock();
		block.setType(m);
		block.setData(b);
	}
	
	@SuppressWarnings("deprecation")
	public void create(String ramRange, BlockFace face) {
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		String[] coords = yc.getString("Rams."+ramRange+".body1.loc").split(";");
		String[] coords2 = yc.getString("Rams."+ramRange+".body2.loc").split(";");
		String[] coords3 = yc.getString("Rams."+ramRange+".body3.loc").split(";");
		String[] coords4 = yc.getString("Rams."+ramRange+".body4.loc").split(";");
		String[] coords5 = yc.getString("Rams."+ramRange+".body5.loc").split(";");
		String[] tail = yc.getString("Rams."+ramRange+".tail.loc").split(";");
		String[] handle1 = yc.getString("Rams."+ramRange+".handle1.loc").split(";");
		String[] handle2 = yc.getString("Rams."+ramRange+".handle2.loc").split(";");
		String[] handle3 = yc.getString("Rams."+ramRange+".handle3.loc").split(";");
		String[] handle4 = yc.getString("Rams."+ramRange+".handle4.loc").split(";");
		String[] press1 = yc.getString("Plates."+ramRange+".first").split(";");
		String[] press2 = yc.getString("Plates."+ramRange+".second").split(";");
		String[] press3 = yc.getString("Plates."+ramRange+".third").split(";");
		String[] press4 = yc.getString("Plates."+ramRange+".fourth").split(";");
		World world = Bukkit.getWorld(yc.getString("Rams."+ramRange+".world"));
		double x = Double.parseDouble(coords[0]);
		double y = Double.parseDouble(coords[1]);
		double z = Double.parseDouble(coords[2]);
		double x1 = Double.parseDouble(coords2[0]);
		double y1 = Double.parseDouble(coords2[1]);
		double z1 = Double.parseDouble(coords2[2]);
		double x2 = Double.parseDouble(coords3[0]);
		double y2 = Double.parseDouble(coords3[1]);
		double z2 = Double.parseDouble(coords3[2]);
		double x3 = Double.parseDouble(coords4[0]);
		double y3 = Double.parseDouble(coords4[1]);
		double z3 = Double.parseDouble(coords4[2]);
		double x4 = Double.parseDouble(coords5[0]);
		double y4 = Double.parseDouble(coords5[1]);
		double z4 = Double.parseDouble(coords5[2]);
		double x5 = Double.parseDouble(tail[0]);
		double y5 = Double.parseDouble(tail[1]);
		double z5 = Double.parseDouble(tail[2]);
		double x6 = Double.parseDouble(handle1[0]);
		double y6 = Double.parseDouble(handle1[1]);
		double z6 = Double.parseDouble(handle1[2]);
		double x7 = Double.parseDouble(handle2[0]);
		double y7 = Double.parseDouble(handle2[1]);
		double z7 = Double.parseDouble(handle2[2]);
		double x8 = Double.parseDouble(handle3[0]);
		double y8 = Double.parseDouble(handle3[1]);
		double z8 = Double.parseDouble(handle3[2]);
		double x9 = Double.parseDouble(handle4[0]);
		double y9 = Double.parseDouble(handle4[1]);
		double z9 = Double.parseDouble(handle4[2]);
		double x10 = Double.parseDouble(press1[0]);
		double y10 = Double.parseDouble(press1[1]);
		double z10 = Double.parseDouble(press1[2]);
		double x11 = Double.parseDouble(press2[0]);
		double y11 = Double.parseDouble(press2[1]);
		double z11 = Double.parseDouble(press2[2]);
		double x12 = Double.parseDouble(press3[0]);
		double y12 = Double.parseDouble(press3[1]);
		double z12 = Double.parseDouble(press3[2]);
		double x13 = Double.parseDouble(press4[0]);
		double y13 = Double.parseDouble(press4[1]);
		double z13 = Double.parseDouble(press4[2]);
		Location body1 = new Location(world, x, y, z);
		Location body2 = new Location(world, x1, y1, z1);
		Location body3 = new Location(world, x2, y2, z2);
		Location body4 = new Location(world, x3, y3, z3);
		Location body5 = new Location(world, x4, y4, z4);
		Location ltail = new Location(world, x5, y5, z5);
		Location lhandle1 = new Location(world, x6, y6, z6);
		Location lhandle2 = new Location(world, x7, y7, z7);
		Location lhandle3 = new Location(world, x8, y8, z8);
		Location lhandle4 = new Location(world, x9, y9, z9);
		Location lpress1 = new Location(world, x10, y10, z10);
		Location lpress2 = new Location(world, x11, y11, z11);
		Location lpress3 = new Location(world, x12, y12, z12);
		Location lpress4 = new Location(world, x13, y13, z13);
		switch(face){
		case NORTH:
			body1.getBlock().setType(Material.SMOOTH_BRICK);
			body1.getBlock().setData((byte)3);
			body1.getBlock().getState().update(true);
			
			body2.getBlock().setType(Material.LOG_2);
			body2.getBlock().setData((byte)9);
			body2.getBlock().getState().update(true);
			
			body3.getBlock().setType(Material.LOG_2);
			body3.getBlock().setData((byte)9);
			body3.getBlock().getState().update(true);
			
			body4.getBlock().setType(Material.LOG_2);
			body4.getBlock().setData((byte)9);
			body4.getBlock().getState().update(true);
			
			body5.getBlock().setType(Material.LOG_2);
			body5.getBlock().setData((byte)9);
			body5.getBlock().getState().update(true);
			
			ltail.getBlock().setType(Material.LOG_2);
			ltail.getBlock().setData((byte)9);
			ltail.getBlock().getState().update(true);
			
			lhandle1.getBlock().setType(Material.IRON_FENCE);
			lhandle2.getBlock().setType(Material.IRON_FENCE);
			lhandle3.getBlock().setType(Material.IRON_FENCE);
			lhandle4.getBlock().setType(Material.IRON_FENCE);
			lpress1.getBlock().setType(Material.STONE_PLATE);
			lpress2.getBlock().setType(Material.STONE_PLATE);
			lpress3.getBlock().setType(Material.STONE_PLATE);
			lpress4.getBlock().setType(Material.STONE_PLATE);
			break;
		case SOUTH:
			body1.getBlock().setType(Material.SMOOTH_BRICK);
			body1.getBlock().setData((byte)3);
			body1.getBlock().getState().update(true);
			
			body2.getBlock().setType(Material.LOG_2);
			body2.getBlock().setData((byte)9);
			body2.getBlock().getState().update(true);
			
			body3.getBlock().setType(Material.LOG_2);
			body3.getBlock().setData((byte)9);
			body3.getBlock().getState().update(true);
			
			body4.getBlock().setType(Material.LOG_2);
			body4.getBlock().setData((byte)9);
			body4.getBlock().getState().update(true);
			
			body5.getBlock().setType(Material.LOG_2);
			body5.getBlock().setData((byte)9);
			body5.getBlock().getState().update(true);
			
			ltail.getBlock().setType(Material.LOG_2);
			ltail.getBlock().setData((byte)9);
			ltail.getBlock().getState().update(true);
			
			lhandle1.getBlock().setType(Material.IRON_FENCE);
			lhandle2.getBlock().setType(Material.IRON_FENCE);
			lhandle3.getBlock().setType(Material.IRON_FENCE);
			lhandle4.getBlock().setType(Material.IRON_FENCE);
			lpress1.getBlock().setType(Material.STONE_PLATE);
			lpress2.getBlock().setType(Material.STONE_PLATE);
			lpress3.getBlock().setType(Material.STONE_PLATE);
			lpress4.getBlock().setType(Material.STONE_PLATE);
			break;
		case EAST:
			body1.getBlock().setType(Material.SMOOTH_BRICK);
			body1.getBlock().setData((byte)3);
			body1.getBlock().getState().update(true);
			
			body2.getBlock().setType(Material.LOG_2);
			body2.getBlock().setData((byte)5);
			body2.getBlock().getState().update(true);
			
			body3.getBlock().setType(Material.LOG_2);
			body3.getBlock().setData((byte)5);
			body3.getBlock().getState().update(true);
			
			body4.getBlock().setType(Material.LOG_2);
			body4.getBlock().setData((byte)5);
			body4.getBlock().getState().update(true);
			
			body5.getBlock().setType(Material.LOG_2);
			body5.getBlock().setData((byte)5);
			body5.getBlock().getState().update(true);
			
			ltail.getBlock().setType(Material.LOG_2);
			ltail.getBlock().setData((byte)5);
			ltail.getBlock().getState().update(true);
			
			lhandle1.getBlock().setType(Material.IRON_FENCE);
			lhandle2.getBlock().setType(Material.IRON_FENCE);
			lhandle3.getBlock().setType(Material.IRON_FENCE);
			lhandle4.getBlock().setType(Material.IRON_FENCE);
			lpress1.getBlock().setType(Material.STONE_PLATE);
			lpress2.getBlock().setType(Material.STONE_PLATE);
			lpress3.getBlock().setType(Material.STONE_PLATE);
			lpress4.getBlock().setType(Material.STONE_PLATE);
			break;
		case WEST:
			body1.getBlock().setType(Material.SMOOTH_BRICK);
			body1.getBlock().setData((byte)3);
			body1.getBlock().getState().update(true);
			
			body2.getBlock().setType(Material.LOG_2);
			body2.getBlock().setData((byte)5);
			body2.getBlock().getState().update(true);
			
			body3.getBlock().setType(Material.LOG_2);
			body3.getBlock().setData((byte)5);
			body3.getBlock().getState().update(true);
			
			body4.getBlock().setType(Material.LOG_2);
			body4.getBlock().setData((byte)5);
			body4.getBlock().getState().update(true);
			
			body5.getBlock().setType(Material.LOG_2);
			body5.getBlock().setData((byte)5);
			body5.getBlock().getState().update(true);
			
			ltail.getBlock().setType(Material.LOG_2);
			ltail.getBlock().setData((byte)5);
			ltail.getBlock().getState().update(true);
			
			lhandle1.getBlock().setType(Material.IRON_FENCE);
			lhandle2.getBlock().setType(Material.IRON_FENCE);
			lhandle3.getBlock().setType(Material.IRON_FENCE);
			lhandle4.getBlock().setType(Material.IRON_FENCE);
			lpress1.getBlock().setType(Material.STONE_PLATE);
			lpress2.getBlock().setType(Material.STONE_PLATE);
			lpress3.getBlock().setType(Material.STONE_PLATE);
			lpress4.getBlock().setType(Material.STONE_PLATE);
			break;
		default:
			break;
		}
	}

}
