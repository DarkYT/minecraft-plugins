package fr.darkyt.earthchest.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import fr.darkyt.earthchest.Main;

public class onPlayerJoin implements Listener {

	Main main;
	public onPlayerJoin(Main m) {
		main = m;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Inventory chest = Bukkit.getServer().createInventory(e.getPlayer(), 54, "Coffre peronnel");
		
		
	}

}
