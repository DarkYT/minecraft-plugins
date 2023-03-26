package fr.stellaria.vote.tasks;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.stellaria.vote.Core;

public class DeleteTask extends BukkitRunnable{

	Core core;
	Player p;
	public DeleteTask(Core core, Player p) {
		this.core = core;
		this.p = p;
	}
	@Override
	public void run() {
		String now = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(now);
		String[] nowAll = now.split(":");
		int nowH = Integer.valueOf(nowAll[0]);
		int nowM = Integer.valueOf(nowAll[1]);
		String key =  core.getConfig().getString("Votes." + p.getName() + ".key");
		System.out.println(key);
		String[] keyAll = key.split(":");
		int keyH = Integer.valueOf(keyAll[0]);
		int keyM = Integer.valueOf(keyAll[1]);
		
		if(nowH - keyH == 0 && nowM - keyM == 2){
			core.getConfig().set("Votes." + p.getName(), null);
			core.saveConfig();
		}
	}

}
