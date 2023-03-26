package fr.thephoenix2feu.claims;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class SelectZone implements Listener {

	public Plugin pl;
	
	public SelectZone(Plugin plugin) {
		this.pl = plugin;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		for(String s : (ArrayList<String>) pl.getConfig().get("list")) {
			UUID uuid = UUID.fromString(s);
			if(pl.isInto(e.getFrom(), uuid, "null") == false && pl.isInto(e.getTo(), uuid, "null")) {
				e.getPlayer().sendTitle(ChatColor.GOLD+"Bienvenue chez", ChatColor.GOLD+Bukkit.getOfflinePlayer(uuid).getName());
			}
		}
	
	}
	
	@EventHandler
	public void onClik(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		if(p.getInventory().getItemInMainHand().getType() != Material.WOOD_SPADE) return;
		if(!p.getInventory().getItemInMainHand().hasItemMeta()) return;
		if(!p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) return;
		if(!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§8Outil de Claim")) return;
		
		if(Plugin.click.contains(p))return;
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) {
			e.setCancelled(true);
			HashMap<Player, Location> hashmap = pl.getPoint2();
			hashmap.put(p, e.getClickedBlock().getLocation());
			pl.setPoint2(hashmap);
			p.sendMessage("§aLe point 2 a été défini");
			
			Plugin.click.add(p);
			Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
				
				@Override
				public void run() {
					Plugin.click.remove(p);
					
				}
			}, 2);
			
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(p.getInventory().getItemInMainHand().getType() != Material.WOOD_SPADE) return;
		if(!p.getInventory().getItemInMainHand().hasItemMeta()) return;
		if(!p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) return;
		if(!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§8Outil de Claim")) return;
		e.setCancelled(true);
	
		HashMap<Player, Location> hashmap = pl.getPoint1();
		hashmap.put(p, e.getBlock().getLocation());
		pl.setPoint1(hashmap);
		p.sendMessage("§aLe point 1 a été défini");
	}
}
