package fr.thephoenix2feu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class Inv implements Listener {

	public plugin pl;
	
	public Inv(plugin plugin) {
		this.pl = plugin;
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Player player = Bukkit.getPlayer(e.getInventory().getName());
		if(player != e.getPlayer()) return;
		plugin.sql.setInv(player, e.getInventory());
	}
	

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player == false)return;
		
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getName().equals(p.getName()) == false)return;
		
		e.setCancelled(true);
		e.getWhoClicked().sendMessage("§cPour récupérer le contenue de votre réserve, veuillez faire /getinv");
	}
}
