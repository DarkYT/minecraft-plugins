package fr.earthsky.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class UpdateSB extends BukkitRunnable {
	
	EarthScoreboard earthscoreboard;
	Player p;
	
	public  UpdateSB(Player pl, EarthScoreboard m) {
		earthscoreboard = m;
		p = pl;
	}

	@Override
	public void run() {
		earthscoreboard.scoreboard(p);
	}

}
