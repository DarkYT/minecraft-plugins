package fr.dark.bedwars.tasks;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.dark.bedwars.BedStates;
import fr.dark.bedwars.BedWars;
import fr.dark.bedwars.others.BPlayer;
import fr.dark.bedwars.others.ScoreboardSign;
import fr.dark.bedwars.others.Team;
import fr.dark.bedwars.utils.NpcAPI;
import fr.dark.bedwars.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class GameAutoStart extends BukkitRunnable {

	BedWars core;
	int timer = 5;
	boolean throught;

	public GameAutoStart(BedWars core, boolean throught) {
		this.core = core;
		this.throught = throught;
	}

	@Override
	public void run() {

		for (Player pls : Bukkit.getOnlinePlayers()) {
			pls.setLevel(timer);
		}

		if (timer == 15 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
			for (Player pls : Bukkit.getOnlinePlayers()) {
				core.title.sendTitle(pls, "", "§e" + timer + "§cs", 30);
			}
		}

		if (Bukkit.getOnlinePlayers().size() < core.getMaxPlayersSize()) {
			if(!throught){
				Bukkit.broadcastMessage("§c[§4Bed§cWars] §4Il n'y a pas assez de joueurs pour lancer le jeu");
				core.setState(BedStates.WAITING);
				cancel();
			}
		}

		if (timer == 0) {
			cancel();
			
			for(Player pl : Bukkit.getOnlinePlayers()) {
				if(!core.hasTeam(pl)) {
					core.randomTeam(pl);
				}
			}

			for (Entry<Player, Team> entry : core.players.entrySet()) {
				core.title.sendTitle(entry.getKey(), "§eLancement du Jeu", "Téléportation...", 30);
				entry.getKey().teleport(entry.getValue().getSpawn());
				BPlayer bP = core.getBPlayerFromPlayer(entry.getKey());
				bP.equip();
				
				ScoreboardSign sb = core.boards.get(entry.getKey());
				
				sb.setLine("§e ", 0);
				sb.setLine("§2Joueurs : §a"+Bukkit.getOnlinePlayers().size(), 1);
				sb.setLine("§6 ", 2);
				sb.setLine("§3Equipe : §b"+entry.getValue().getChatColor()+entry.getValue().getName(), 3);
				sb.setLine("§b", 4);
				sb.setLine("§6Morts: §e0", 5);
				sb.setLine("§8Kills: §70", 6);
				sb.setLine("§5Lits cassés: §d0", 7);
				
				core.deaths.put(entry.getKey(), 0);
				core.kills.put(entry.getKey(), 0);
				core.beds.put(entry.getKey(), 0);

			}
			core.setState(BedStates.PLAYING);
			
			for(String s : core.getConfig().getConfigurationSection("BedWars.Config.ActiveMap").getKeys(false)) {
				if(s.contains("Team")) {
					for(String loc : core.getConfig().getStringList("BedWars.Config.ActiveMap."+s+".PNJSpawn")) {
						String[] parts = loc.split("_");
						System.out.println(parts[0]);
						Location l = Utils.getLocationString(parts[0], core.activeMap.getWorld());
						NpcAPI.createNPC(ChatColor.translateAlternateColorCodes('&', parts[1]), l);
					}
				}
			}

			SpawnTask task = new SpawnTask(core);
			task.runTaskTimer(core, 20, 20);

		}

		timer--;
	}

}
