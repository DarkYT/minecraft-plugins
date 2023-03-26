package fr.blizzrpg.chest.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import fr.blizzrpg.chest.Main;

public class onJoin implements Listener {

	Main main;
	public onJoin(Main m) {
		main = m;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Inventory inv = Bukkit.getServer().createInventory(e.getPlayer(), InventoryType.CHEST, "Coffre personnel");
		
		if (main.getConfig().contains("chests." + e.getPlayer().getUniqueId())) {
			for (String item : main.getConfig().getConfigurationSection("chests." + e.getPlayer().getUniqueId()).getKeys(false)) {
				inv.addItem(main.loadItem(main.getConfig().getConfigurationSection("chests." + e.getPlayer().getUniqueId() + "." + item)));
			}
		}
		
		main.chests.put(e.getPlayer().getUniqueId(), inv);
	}


}
