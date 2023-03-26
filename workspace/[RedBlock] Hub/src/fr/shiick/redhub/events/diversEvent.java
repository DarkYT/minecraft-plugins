package fr.shiick.redhub.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.shiick.redhub.RedHub;
import fr.shiick.redhub.utils.Items;
import fr.shiick.redhub.utils.Utils;

public class diversEvent implements Listener {

	RedHub redhub;
	Utils utils;
	Items items;

	public diversEvent(RedHub redhub) {
		this.redhub = redhub;
		utils = new Utils(redhub);
		items = new Items(redhub);
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		p.getInventory().clear();
		items.giveItems(p);
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		e.setDroppedExp(0);
		e.setDeathMessage("");
		e.getDrops().clear();
	}

	@EventHandler
	public void onPickup(EntityPickupItemEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (!(p.isOp())) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (!(p.isOp())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onXP(PlayerExpChangeEvent e) {
		if (!(e.getPlayer().isOp())) {
			e.setAmount(0);
		}
	}

}
