package fr.dark.stats.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.dark.stats.Stats;

public class eventsListener implements Listener {

	Stats core;
	public eventsListener(Stats stats) {this.core = stats;}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(!Stats.sql.hasAccount(e.getPlayer())) {
			Stats.sql.createAccount(e.getPlayer());
		}
		core.joinTime.put(e.getPlayer(), System.currentTimeMillis());
		core.broken.put(e.getPlayer(), 0);
		core.placed.put(e.getPlayer(), 0);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if(core.joinTime.containsKey(e.getPlayer())) {
			long now = System.currentTimeMillis();
			long arrived = core.joinTime.get(e.getPlayer());
			long all = Long.parseLong(Stats.sql.getSpendTime(e.getPlayer().getUniqueId().toString()));
			Stats.sql.setSpendTime(e.getPlayer(), (now-arrived) + all);
			Stats.sql.setBrokenBlocks(e.getPlayer(), Stats.sql.getBrokenBlocks(e.getPlayer().getUniqueId().toString())+core.broken.get(e.getPlayer()));
			Stats.sql.setPlacedBlocks(e.getPlayer(), Stats.sql.getPlacedBlocks(e.getPlayer().getUniqueId().toString())+core.placed.get(e.getPlayer()));
			core.joinTime.remove(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(core.broken.containsKey(e.getPlayer())) {
			int i = core.broken.get(e.getPlayer());
			core.broken.put(e.getPlayer(), i+1);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(core.placed.containsKey(e.getPlayer())) {
			int i = core.placed.get(e.getPlayer());
			core.placed.put(e.getPlayer(), i+1);
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Stats.sql.setDeaths(e.getEntity(), Stats.sql.getDeaths(e.getEntity().getUniqueId().toString())+1);
	}

}
