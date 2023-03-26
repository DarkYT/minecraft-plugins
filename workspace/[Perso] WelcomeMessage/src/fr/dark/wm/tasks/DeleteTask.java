package fr.dark.wm.tasks;

import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;

import fr.dark.wm.WMessage;

public class DeleteTask extends BukkitRunnable{

	WMessage core;
	OfflinePlayer p;
	int timer;
	
	public DeleteTask(WMessage wMessage, OfflinePlayer p) {
		this.core = wMessage;
		this.p = p;
		this.timer = 0;
	}

	@Override
	public void run() {
		if(timer == 20) {
			if(core.recentPlayers.contains(p)) {
				core.recentPlayers.remove(p);
			}
			cancel();
		}
		timer++;
	}

}
