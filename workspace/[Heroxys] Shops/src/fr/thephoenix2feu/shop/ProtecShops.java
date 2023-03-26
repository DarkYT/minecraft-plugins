package fr.thephoenix2feu.shop;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;

import net.md_5.bungee.api.ChatColor;

public class ProtecShops implements Listener {

	public plugin pl;
	
	public ProtecShops(plugin plugin) {
		this.pl = plugin;
	}
	
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		ArrayList<String> a = (ArrayList<String>) pl.getConfig().get("shops");
		if(a == null || e.getPlayer().isOp()) {
			return;
		}
		
		for(String s : a) {
			CuboidSelection c = plugin.buildSection(s);
			
			if(c.contains(e.getBlock().getLocation()) ) {
				if(pl.getConfig().get("shop."+s+".owner").equals(e.getPlayer().getUniqueId().toString())) {
					if(e.getBlock().getType() != Material.CHEST && e.getBlock().getType() != Material.SIGN_POST && e.getBlock().getType() != Material.TRAPPED_CHEST)
						e.setCancelled(true);
				}else {
					e.setCancelled(true);
				}
			}
			
		}
		
	}

	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		ArrayList<String> a = (ArrayList<String>) pl.getConfig().get("shops");
		if(a == null || e.getPlayer().isOp()) {
			return;
		}
		
		for(String s : a) {
			CuboidSelection c = plugin.buildSection(s);
			
			if(c.contains(e.getBlockPlaced().getLocation())) {
				if(pl.getConfig().get("shop."+s+".owner").equals(e.getPlayer().getUniqueId().toString())) {
					if(e.getPlayer().getItemInHand().getType() != Material.CHEST && e.getPlayer().getItemInHand().getType() != Material.SIGN && e.getBlock().getType() != Material.TRAPPED_CHEST) {
						e.setCancelled(true);
					}
				}else {
					e.setCancelled(true);
				}
			}
			
		}
		
	}
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		for(String s : (ArrayList<String>) pl.getConfig().get("shops")) {
			
			@SuppressWarnings("static-access")
			CuboidSelection c = pl.buildSection(s);
			
			if(c.contains(e.getFrom()) == false && c.contains(e.getTo())) {
				
				if(pl.getConfig().getString("shop."+s+".owner").equalsIgnoreCase("nobody")) {
					e.getPlayer().sendTitle(ChatColor.YELLOW+"Zone Libre ("+pl.getConfig().getInt("shop."+s+".price")+"$)", "Faites /shop buyplot pour l'acheter");
				}else {
					UUID uuid = UUID.fromString(pl.getConfig().getString("shop."+s+".owner"));
					e.getPlayer().sendTitle(ChatColor.YELLOW+Bukkit.getOfflinePlayer(uuid).getName(), ChatColor.GRAY+"vous souhaite la bienvenue dans sa parcelle");
				}
			}
			
		}
		
	}
	//plotinfo
	//inventaire taille 54
	
}
