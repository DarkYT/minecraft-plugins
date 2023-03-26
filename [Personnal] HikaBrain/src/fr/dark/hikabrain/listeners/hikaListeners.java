package fr.dark.hikabrain.listeners;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;

import fr.dark.hikabrain.Core;
import fr.dark.hikabrain.HStates;
import fr.dark.hikabrain.api.ScoreboardSign;
import fr.dark.hikabrain.tasks.HAutoStart;
import fr.dark.hikabrain.teams.Team;

public class hikaListeners implements Listener {

	Core core;
	public hikaListeners(Core core){
		this.core = core;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.teleport(core.getParseLoc(core.getConfig().getString("spawn"), "world"));
		if(!core.isState(HStates.WAITING)){
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage("�8[�c!�8] �8Le jeu a d�j� commenc� !");
			e.setJoinMessage(null);
			return;
		}
		for(Entry<Player, ScoreboardSign> boards : core.boards.entrySet()){
			boards.getValue().setLine(2, "�a�l>> �r�fJoueurs: �e" + Bukkit.getServer().getOnlinePlayers().size());
		}
		e.setJoinMessage("�8[�a+�8] �8" + p.getName() + " a rejoint la partie");
		core.randomTeam(p);
		
		p.getInventory().clear();
		p.setGameMode(GameMode.SURVIVAL);
		p.setHealth(p.getMaxHealth());
		p.setFoodLevel(20);
		
		if(Bukkit.getServer().getOnlinePlayers().size() == 2){
			HAutoStart task = new HAutoStart(core);
			task.runTaskTimer(core, 20, 20);
			core.setState(HStates.GAME);
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		for(Entry<Player, ScoreboardSign> boards : core.boards.entrySet()){
			boards.getValue().setLine(2, "�a�l>> �r�fJoueurs: �e" + Bukkit.getServer().getOnlinePlayers().size());
		}
		e.setQuitMessage("�8[�c-�8] �8" + p.getName() + " a quitt� la partie");
	}
	
	@EventHandler
	public void onFoodLevel(FoodLevelChangeEvent e){
		e.setFoodLevel(20);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {

		Block b = event.getBlock();
		b.setMetadata("placed", new FixedMetadataValue(core, event.getPlayer().getName()));
		
		if(!core.isState(HStates.GAME)){
			//event.setCancelled(true);
			return;
		}

		if (b.getLocation().getY() >= core.getConfig().getInt("maxblocks")) {
			event.setCancelled(true);
			return;
		}

		if (!core.placedblocks.contains(b)) {
			core.placedblocks.add(b);
		}

	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {

		Block b = event.getBlock();
		
		if(!core.isState(HStates.GAME)){
			//event.setCancelled(true);
			return;
		}

		if (b.getType() != null && b.getType() == Material.WOOL || b.getType() == Material.BED_BLOCK) {
			event.setCancelled(true);
			return;
		}

		if (!b.hasMetadata("placed") && !core.destroyedBlocks.containsKey(b)) {
			core.destroyedBlocks.put(b.getLocation(), b.getType());
		}

		if (!core.placedblocks.contains(b) && core.destroyedBlocks.containsKey(b)) {
			core.destroyedBlocks.put(b.getLocation(), b.getType());
		}

	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		Location l = p.getLocation();

		if(core.isState(HStates.WAIT)){
			e.setCancelled(true);
		}
		if (l.getY() < 0) {
			
			if (p.getGameMode() != GameMode.SPECTATOR && core.isState(HStates.GAME)) {
				core.deaths.put(p, 1);
				core.respawn(p);
				p.setHealth(p.getMaxHealth());
			}

			if(core.isState(HStates.WAITING)){
				p.teleport(core.getParseLoc(core.getConfig().getString("spawn"), "world"));
			}
		}

		if (l.add(0, -0.5, 0).getBlock().getType() == Material.BED_BLOCK) {

			Block wool = l.add(0, -0.7, 0).getBlock();
			if (wool.getType() == Material.WOOL) {

				Team team = core.team.get(p);
				byte data = wool.getData();

				if (team.getWoolData() != data) {

					team.addPoint();
					core.boards.get(p).setLine(3, "�a�l>> �r�fPoints: �e" + team.getPoints());
					core.reset();

					Bukkit.broadcastMessage("�8[�c!�8] �8L'�quipe " + team.getTag() + team.getName() + " �8marque un point ! (" + team.getPoints() + "/5)");

					if (team.getPoints() >= 5) {

						Bukkit.broadcastMessage("�8[�c!�8] �8L'�quipe " + team.getTag() + team.getName() + " �8gagne la partie !");
						core.setState(HStates.FINISHING);

						for (Player pls : Bukkit.getOnlinePlayers()) {
							pls.setGameMode(GameMode.SPECTATOR);
						}

						return;
					}

				}

			}

		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			if(core.deaths.containsKey((Player)e.getEntity())){
				e.setCancelled(true);
				core.deaths.remove((Player)e.getEntity());
			}
		}
	}
}
