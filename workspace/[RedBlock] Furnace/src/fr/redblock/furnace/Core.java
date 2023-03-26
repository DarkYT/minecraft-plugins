package fr.redblock.furnace;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.redblock.furnace.commands.furnaceCommand;
import fr.redblock.furnace.events.furnaceListener;

public class Core extends JavaPlugin {
	
	public Map<Location, Block> furnaces = new HashMap<>();
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new furnaceListener(this), this);
		getCommand("furnace").setExecutor(new furnaceCommand(this));
	}
	
	public void openFurnace(Player p){
		Location loc = p.getLocation();
		Location aLoc = loc.add(2,0,0);
		aLoc.getBlock().setType(Material.FURNACE);
		furnaces.put(aLoc, aLoc.getBlock());
	}
}
