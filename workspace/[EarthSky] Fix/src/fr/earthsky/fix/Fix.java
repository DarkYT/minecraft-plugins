package fr.earthsky.fix;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.plugin.java.JavaPlugin;



public class Fix extends JavaPlugin implements Listener{
	@Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
   
    @SuppressWarnings("deprecation")
	@EventHandler
    public void fixWater(BlockFromToEvent e) {
    	int id = e.getBlock().getTypeId();
    	Block blockFormed = e.getBlock();
    	Block blockFormedOn = blockFormed.getRelative(BlockFace.DOWN);
		if(id == 8 || id == 9) {
    		if(blockFormedOn.getType().equals(null) || blockFormedOn.getType().equals(Material.AIR)){
    			if(isNotMatBelow(blockFormedOn, Material.AIR, 255)){
    				e.setCancelled(true);
    			}else{
    				e.setCancelled(false);
    			}
    		}
    	}
    }
    
    public static boolean isNotMatBelow(Block block, Material material, int depth) {
        Location location = block.getLocation().clone();
        for (int blocks = 1; blocks <= depth; blocks++) { 
            location.subtract(0, 1, 0); 
            if (location.getBlock().getType() != material) { 
                return false;
            }
        }
        return true;
    }
}
