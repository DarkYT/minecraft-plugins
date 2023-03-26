package fr.dark.hikabrain.tasks;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.dark.hikabrain.Core;
import fr.dark.hikabrain.HStates;
import fr.dark.hikabrain.api.ScoreboardSign;
import fr.dark.hikabrain.teams.Team;

public class HAutoStart extends BukkitRunnable{
	
	private Core core;
	private int timer = 10;

	public HAutoStart(Core core) { this.core = core; }
	@Override
	public void run() {
		
		if(Bukkit.getServer().getOnlinePlayers().size() < 2){
			cancel();
			Bukkit.broadcastMessage("§8[§c!§8] §8Il n'y a pas assez de joueurs !");
			core.setState(HStates.WAITING);
		}
		
		if(timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1){
			for(Player pls : Bukkit.getServer().getOnlinePlayers()){
				core.titles.sendTitle(pls, "", "§6" + timer + "§es", 20);
			}
		}
		
		if(timer == 0){
			cancel();
			core.loadTeams();
			for(Player pl : Bukkit.getServer().getOnlinePlayers()){
				pl.setGameMode(GameMode.SURVIVAL);
				Team team = core.getTeam(pl);
				ScoreboardSign sb = new ScoreboardSign(pl, "§6§l>>§r§eHikaBrain§6§l<<");
				sb.create();
				sb.setLine(0, "§c ");
				sb.setLine(1, "§a§l>> §r§f" + pl.getName());
				sb.setLine(2, "§a§l>> §r§fJoueurs: §e" + Bukkit.getServer().getOnlinePlayers().size());
				sb.setLine(3, "§a§l>> §r§fPoints: §e" + team.getPoints());
				sb.setLine(4, "§a§l>> §r§fPing:§6 " + core.getPing(pl));
				sb.setLine(5, "§a ");
				sb.setLine(6, "§eplay.stellaria-mc.fr");
				
				core.boards.put(pl, sb);
				pl.setDisplayName(team.getTag() + "[" + team.getName() + "] " + pl.getName());
				pl.setPlayerListName(team.getTag() + "[" + team.getName() + "] " + pl.getName());
				pl.teleport(core.getTeam(pl).getSpawn());
				core.equip(pl);
				core.titles.sendTitle(pl, "", "§6Le jeu va commencer !", 25);
			}
		}
		
		timer--;
	}
	
}
