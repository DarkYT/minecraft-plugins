package fr.thephoenix2feu.claims;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ProtectZone implements Listener {

	public Plugin pl;
	
	public ProtectZone(Plugin plugin) {
		this.pl = plugin;
	}
	
	
	//TEST DES ENDERPEARLS, SETHOME ETC...
	
	
	@SuppressWarnings({ "unchecked" })
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		for(String s : (ArrayList<String>) pl.getConfig().get("list")) {
			
			if(pl.getConfig().getString("zone."+s).equalsIgnoreCase("no zone"))return;
			
			UUID uuid = UUID.fromString(s);
			
			if(e.getPlayer().getUniqueId().equals(UUID.fromString(s)))
				return;
			
			Player p = e.getPlayer();


			if(pl.getConfig().get("zone."+s+".sections") instanceof ArrayList<?>) {
			
			ArrayList<String> array = (ArrayList<String>) pl.getConfig().get("zone."+s+".sections");
				for(String sections : array) {
					for(String players : (ArrayList<String>) pl.getConfig().get("zone."+s+"."+sections+".players")) {
						if(pl.isInto(e.getBlock().getLocation(), UUID.fromString(s), sections)) {
							if(players.contains(p.getName())) {
								return;
							}
						}
					}
				}
			}
			if(pl.isInto(e.getBlock().getLocation(), uuid, "null")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.DARK_RED+"Vous ne faites pas parti des utilisateurs de ce terrain");
			}
		}
	}
	@SuppressWarnings({ "unchecked"})
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		for(String s : (ArrayList<String>) pl.getConfig().get("list")) {
			UUID uuid = UUID.fromString(s);
			if(pl.getConfig().getString("zone."+s).equalsIgnoreCase("no zone"))return;
			if(e.getPlayer().getUniqueId().equals(UUID.fromString(s)))
				return;
			
			Player p = e.getPlayer();


			if(pl.getConfig().get("zone."+s+".sections") instanceof ArrayList<?>) {
			
			ArrayList<String> array = (ArrayList<String>) pl.getConfig().get("zone."+s+".sections");
				for(String sections : array) {
					for(String players : (ArrayList<String>) pl.getConfig().get("zone."+s+"."+sections+".players")) {
						if(pl.isInto(e.getBlock().getLocation(), UUID.fromString(s), sections)) {
							if(players.contains(p.getName())) {
								return;
							}
						}
					}
				}
			}
			
			if(pl.isInto(e.getBlock().getLocation(), uuid, "null")) {
				e.getPlayer().sendMessage(ChatColor.DARK_RED+"Vous ne faites pas parti des utilisateurs de ce terrain");
				e.setCancelled(true);
			}
		}
	}
	@SuppressWarnings({ "unchecked" })
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		for(String s : (ArrayList<String>) pl.getConfig().get("list")) {
			UUID uuid = UUID.fromString(s);
			if(pl.getConfig().getString("zone."+s).equalsIgnoreCase("no zone"))return;
			if(e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				if(p.getUniqueId().equals(UUID.fromString(s)))
					return;

				


				if(pl.getConfig().get("zone."+s+".sections") instanceof ArrayList<?>) {
				
				ArrayList<String> array = (ArrayList<String>) pl.getConfig().get("zone."+s+".sections");
					for(String sections : array) {
						for(String players : (ArrayList<String>) pl.getConfig().get("zone."+s+"."+sections+".players")) {
							if(pl.isInto(p.getLocation(), UUID.fromString(s), sections)) {
								if(players.contains(p.getName())) {
									return;
								}
							}
						}
					}
				}
			}
			
			if(pl.isInto(e.getEntity().getLocation(), uuid, "null") && pl.isInto(e.getDamager().getLocation(), uuid, "null") == false) {
				e.setCancelled(true);
				if(e.getEntity() instanceof Player) {
					Player p = (Player) e.getEntity();
					p.sendMessage(ChatColor.DARK_RED+"Vous ne faites pas parti des utilisateurs de ce terrain");
				}
			}
		}
	}
	@SuppressWarnings({ "unchecked"})
	@EventHandler
	public void onBlockClick(PlayerInteractEvent e) {
		for(String s : (ArrayList<String>) pl.getConfig().get("list")) {
			UUID uuid = UUID.fromString(s);

			if(pl.getConfig().getString("zone."+s).equalsIgnoreCase("no zone"))return;
			if(e.getPlayer().getUniqueId().equals(UUID.fromString(s)))
				return;
			
			Player p = e.getPlayer();
			


			if(pl.getConfig().get("zone."+s+".sections") instanceof ArrayList<?>) {
			
			ArrayList<String> array = (ArrayList<String>) pl.getConfig().get("zone."+s+".sections");
				for(String sections : array) {
					for(String players : (ArrayList<String>) pl.getConfig().get("zone."+s+"."+sections+".players")) {
						if(pl.isInto(p.getLocation(), UUID.fromString(s), sections)) {
							if(players.contains(p.getName())) {
								return;
							}
						}
					}
				}
			}
			
			if(e.getClickedBlock() != null) {
				if(pl.isInto(e.getClickedBlock().getLocation(), uuid, "null")) {	
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.DARK_RED+"Vous ne faites pas parti des utilisateurs de ce terrain");
				}
			}
		}
	}
}
