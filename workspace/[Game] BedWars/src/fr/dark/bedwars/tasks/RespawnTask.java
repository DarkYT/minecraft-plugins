package fr.dark.bedwars.tasks;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.dark.bedwars.BedWars;

public class RespawnTask  extends BukkitRunnable{

	BedWars core;
	Player p;
	int timer = 3;
	
	public RespawnTask(Player p, BedWars bedWars) {
		this.core = bedWars;
		this.p = p;
	}

	@Override
	public void run() {
		if(timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1){
			core.title.sendTitle(p, "§eRespawn..", "§6" + timer+"§es", 20);
		}
		
		if(timer == 0){
			core.title.sendTitle(p, "§6Respawn !", "", 20);
			p.setGameMode(GameMode.SURVIVAL);
			p.teleport(core.getTeam(p).getSpawn());
			core.respawn.remove(p);
			p.removePotionEffect(PotionEffectType.SPEED);
			int deaths = core.deaths.get(p);
			core.boards.get(p).setLine("§6Morts: §e"+(deaths+1), 5);
			core.deaths.remove(p);
			core.deaths.put(p, deaths+1);
			cancel();
		}
		
		timer--;
	}

}
