package fr.dark.hikabrain.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.dark.hikabrain.Core;
import fr.dark.hikabrain.HStates;

public class respawnTask extends BukkitRunnable{

	int timer = 2;
	Core core;
	public respawnTask(Core core) {
		this.core = core;
	}
	@Override
	public void run() {
		if(timer == 2 || timer == 1){
			for(Player p : Bukkit.getServer().getOnlinePlayers()){
				core.titles.sendTitle(p, "", "§e" + timer + "§6s", 25);
			}
		}
		if(timer == 0){
			cancel();
			for(Player p : Bukkit.getServer().getOnlinePlayers()){
				core.titles.sendTitle(p, "", "§eGo !", 25);
				core.setState(HStates.GAME);
			}
		}
		timer--;
	}

}
