package fr.redblock.furnace.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import fr.redblock.furnace.Core;

public class furnaceListener implements Listener {

	Core core;
	public furnaceListener(Core core) {
		this.core = core;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClose(InventoryCloseEvent e){
		Inventory inv = e.getInventory();
		if(inv.getHolder() instanceof Furnace){
			Furnace furnace = (Furnace) inv.getHolder();
			Location loc = furnace.getBlock().getLocation();
			Block b = furnace.getBlock();
			
			if(core.furnaces.containsKey(loc)){
				Location nLoc = b.getLocation().add(0,10,0);
				loc.getBlock().setType(Material.AIR);
				nLoc.getBlock().setType(Material.FURNACE);
				nLoc.getBlock().setData(b.getData());
			}
		}
	}

}
