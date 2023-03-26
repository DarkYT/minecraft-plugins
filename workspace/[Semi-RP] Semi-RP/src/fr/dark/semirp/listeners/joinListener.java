package fr.dark.semirp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.dark.semirp.Core;

public class joinListener implements Listener {

	Core core;

	public joinListener(Core core) {
		this.core = core;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (Core.sql.hasAccount(p)) {
			p.teleport(core.getLastLocation(p));
		} else {
			p.teleport(core.getSpawn());
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {
				@Override
				public void run() {
					core.openJobs(p);
				}
			}, 20L);
		}
		String joinMess = core.utils.colorMessage(core.getConfig().getString("SemiRP.Join.Broadcast"));
		joinMess = joinMess.replace("<player>", p.getName());
		Bukkit.broadcastMessage(joinMess);

		p.setGameMode(GameMode.SURVIVAL);

	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		core.setLastLocation(p);
		String joinMess = core.utils.colorMessage(core.getConfig().getString("SemiRP.Leave.Message"));
		joinMess = joinMess.replace("<player>", p.getName());
		Bukkit.broadcastMessage(joinMess);
	}

}
