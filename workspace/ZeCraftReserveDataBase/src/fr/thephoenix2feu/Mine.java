package fr.thephoenix2feu;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Mine implements Listener {

	public plugin pl;
	
	public Mine(plugin plugin) {
		this.pl = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDrop(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if(!pl.getConfig().getBoolean("serveur.minage") || p.getGameMode() != GameMode.SURVIVAL)
		return;
		
		if(!plugin.sql.hasInv(p)){
			Inventory inv = Bukkit.createInventory(p, 36, p.getName());
			for(ItemStack i : e.getBlock().getDrops()) {
				inv.addItem(i);
			}
			plugin.sql.createInv(p, inv);
		}else {
			Inventory inv = plugin.sql.getInv(p);
			if(inv.firstEmpty() == -1) {
				p.sendMessage("§cVous n'avez plus de place dans votre inventaire, allez le vider !");
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				return;
			}
			for(ItemStack i : e.getBlock().getDrops()) {
				inv.addItem(i);
			}
			plugin.sql.setInv(p, inv);
			if(inv.firstEmpty() == -1) {
				p.sendTitle("§cVous n'avez plus de place dans votre inventaire", "§cAllez le vider !");
			}
		}
		e.setCancelled(true);
		e.getBlock().setType(Material.AIR);
	}
	
}
