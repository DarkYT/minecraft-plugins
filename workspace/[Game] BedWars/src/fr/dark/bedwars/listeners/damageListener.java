package fr.dark.bedwars.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.dark.bedwars.BedWars;
import fr.dark.bedwars.others.Team;
import fr.dark.bedwars.utils.Utils;

public class damageListener implements Listener {

	BedWars core;

	public damageListener(BedWars core) {
		this.core = core;
	}
	
	@EventHandler
	public void onFight(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			if(e.getDamager() instanceof Player) {
				Player killer = (Player) e.getDamager();
				Player victim = (Player) e.getEntity();
				if(victim.getHealth() <= e.getDamage()) {
					e.setDamage(0.0D);
					Random r = new Random();
					int rand = r.nextInt(3);
					Bukkit.broadcastMessage(Utils.getRandomKillMessage(rand, killer, victim));
					core.respawn(victim);
					int kills = core.kills.get(killer);
					core.boards.get(killer).setLine("§8Kills: §7"+(kills+1), 6);
					core.kills.remove(killer);
					core.kills.put(killer, (kills+1));
				}
			}
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {

		if (e.getEntity() instanceof Player) {

			Player p = (Player) e.getEntity();
			if (p.getHealth() <= e.getDamage()) {

				e.setDamage(0);
				Random r = new Random();
				int rand = r.nextInt(3);
				Bukkit.broadcastMessage(Utils.getRandomDeathMessage(rand, p));
				core.respawn(p);
				
			}

		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreakBed(BlockBreakEvent e) {
		Block b = e.getBlock();
		if (b.getType().equals(Material.BED_BLOCK)) {
			Block wool = b.getLocation().add(0, -0.7, 0).getBlock();
			if (wool.getType() == Material.WOOL) {
				byte data = wool.getData();
				Team t = core.getTeamWithData(data);
				if (t == null)
					return;
				if (t.hasBed()) {
					t.breakBed();
					int beds = core.beds.get(e.getPlayer());
					core.boards.get(e.getPlayer()).setLine("§5Lits cassés: §d"+(beds+1), 7);
					core.beds.remove(e.getPlayer());
					core.beds.put(e.getPlayer(), (beds+1));
					for (Player p : t.getPlayers()) {
						core.title.sendTitle(p, "§6Votre lit est détruit", "§cAttention !", 40);
						p.sendMessage("§e[§6Bed§eWars] §6§l" + e.getPlayer().getName() + " §ea cassé votre lit !");
					}
				}
			}
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (core.respawn.contains(p)) {
			e.setCancelled(true);
		}

		if (p.getLocation().getY() < 0) {
			core.respawn(p);
			Bukkit.broadcastMessage("§e[§6Bed§eWars] §6" + p.getName() + " §ea fait une Dark !");
		}
	}

}
