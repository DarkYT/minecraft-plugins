package fr.dark.bedwars.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.dark.bedwars.BedWars;

public class headshotListener implements Listener {

	BedWars core;

	public headshotListener(BedWars core) {
		this.core = core;
	}

	@EventHandler
	public void onShoot(EntityDamageByEntityEvent e) {
		if (e.getCause().equals(DamageCause.PROJECTILE)) {
			Projectile proj = (Projectile) e.getDamager();
			if (proj.getShooter() instanceof Entity) {
				if (proj.getShooter() instanceof Player) {
					if (e.getEntity() instanceof Player) {
						Player p = (Player) e.getEntity();
						Player s = (Player) proj.getShooter();
						double yP = p.getLocation().getY();
						double yA = proj.getLocation().getY();
						String broadcast = "§e[§6Bed§eWars] §6"+p.getName()+" §ea été §lheadshot §r§epar §6"+s.getName();

						if (p.isSneaking()) {
							if (yA - yP > 1.2) {
								p.setHealth(0.0D);
								Bukkit.broadcastMessage(broadcast);
							}
						} else {
							if (yA - yP > 1.4) {
								p.setHealth(0.0D);
								Bukkit.broadcastMessage(broadcast);
							}
						}

					}
				}
			}
		}
	}

}
