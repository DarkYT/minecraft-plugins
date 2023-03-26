package fr.dark.ctfcore;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dark.ctfcore.commands.ctfCommand;
import fr.dark.ctfcore.listeners.playerJoinListener;
import fr.dark.ctfcore.utils.Utils;

public class CTFCore extends JavaPlugin {
	
	public Utils utils = new Utils(this);
	Map<Player, Integer> edition = new HashMap<>();
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new playerJoinListener(this), this);
		
		getCommand("ctf").setExecutor(new ctfCommand(this));
		
		saveDefaultConfig();
	}
	
	public void setEditor(Player p){
		edition.put(p, 1);
	}
	
	public boolean isEditor(Player p){
		return edition.containsKey(p);
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

}
