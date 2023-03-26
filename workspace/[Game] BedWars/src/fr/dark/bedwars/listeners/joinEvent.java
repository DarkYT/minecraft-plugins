package fr.dark.bedwars.listeners;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.dark.bedwars.BedStates;
import fr.dark.bedwars.BedWars;
import fr.dark.bedwars.others.ScoreboardSign;
import fr.dark.bedwars.others.Team;
import fr.dark.bedwars.tasks.GameAutoStart;

public class joinEvent implements Listener {
	
	BedWars core;
	public joinEvent(BedWars core) {this.core = core;}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		for(Entry<Player, ScoreboardSign> boards : core.boards.entrySet()){
			boards.getValue().setLine("§2Joueurs : §a"+Bukkit.getOnlinePlayers().size(), 1);
		}
		
		ScoreboardSign sb = new ScoreboardSign(p, "§6Bed§eWars");
		sb.create();
		
		sb.setLine("§e ", 0);
		sb.setLine("§2Joueurs : §a"+Bukkit.getOnlinePlayers().size(), 1);
		sb.setLine("§6 ", 2);
		sb.setLine("§3Equipe : §bAucune", 3);
		
		core.boards.put(p, sb);
		
		if(core.isState(BedStates.WAITING)) {
			System.out.println(core.lobby.getWorld().getName());
			p.teleport(core.lobby.getSpawn());
			Bukkit.broadcastMessage("§7[§a+§7] §a"+p.getName()+" vient de rejoindre le serveur Bed§2Wars");
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			ItemStack it = new ItemStack(Material.WOOL, 1);
			ItemMeta itm = it.getItemMeta();
			itm.setDisplayName("§8Choisissez votre équipe !");
			it.setItemMeta(itm);
			p.getInventory().setItem(4,it);
		}else {
			core.spectators.add(p);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gm 3 "+p.getName());
				}
				
			}, 20);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
			for(int i = 0; i < core.spectators.size(); i++) {
				core.spectators.get(i).sendMessage("§o§7"+p.getName()+" vient de se connecter");
			}
			p.teleport(core.activeMap.getSpawn());
		}
		
		if(Bukkit.getOnlinePlayers().size() == core.getMaxPlayersSize() && core.isState(BedStates.WAITING)) {
			GameAutoStart start = new GameAutoStart(core, false);
			start.runTaskTimer(core, 20, 20);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(core.isState(BedStates.WAITING)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(core.isState(BedStates.WAITING)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		for(Entry<Player, ScoreboardSign> boards : core.boards.entrySet()){
			boards.getValue().setLine("§2Joueurs : §a"+Bukkit.getOnlinePlayers().size(), 1);
		}
		if(core.boards.containsKey(p)) {
			core.boards.remove(p);
		}
		if(core.hasTeam(p)) {
			core.getTeam(p).removePlayer(p);
		}
		Bukkit.broadcastMessage("§7[§c-§7] §c"+p.getName()+" vient de quitter le serveur Bed§4Wars");
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if(e.getInventory().getName().equals("§8Choix des équipes")) {
				if(e.getCurrentItem().getType() == Material.WOOL) {
					e.setCancelled(true);
					byte data = e.getCurrentItem().getData().getData();
					for(Team t : core.teams) {
						if(data == t.getWoolData()) {
							if(t.getMaxPlayerSize() > t.getPlayersSize()) {
								if(!core.hasTeam(p)) {
									t.addPlayer(p);
									core.players.put(p, t);
									p.sendMessage("§a[§2Bed§aWars] §fVous venez de rejoindre l'équipe des "+t.getChatColor()+t.getName());
									core.loadTeamWaiting(p, t);
									core.boards.get(p).setLine("§3Equipe : "+t.getChatColor()+t.getName(), 3);
									p.closeInventory();
									break;
								}else {
									p.sendMessage("§c[§4Bed§cWars] §4Vous avez déjà une équipe !");
									break;
								}
							}else {
								p.sendMessage("§c[§4Bed§cWars] §4Cette équipe est au complet !");
								break;
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void prettifyChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(core.hasTeam(p)) {
			Team t = core.getTeam(p);
			if(!(e.getMessage().startsWith("*"))) {
				e.setFormat(t.getChatColor()+"["+p.getName()+"] §f"+e.getMessage());
			}else {
				String teamM = e.getMessage().replace("*", t.getChatColor()+"[TeamChat] "+p.getName()+" » ");
				for(Player player : t.getPlayers()) {
					player.sendMessage(teamM);
				}
				e.setCancelled(true);
			}
		}else {
			if(!(e.getMessage().startsWith("*"))) {
				e.setFormat("§7["+p.getName()+"] §f"+e.getMessage());
			}else {
				p.sendMessage("§cVous ne pouvez pas accéder au chat d'équipe, vous n'en avez pas !");
				e.setFormat("§7["+p.getName()+"] §f"+e.getMessage());
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getItem() == null) return;
			if(e.getItem().getType() == Material.WOOL) {
				if(!(e.getItem().hasItemMeta())) return;
				if(e.getItem().getItemMeta().getDisplayName().equals("§8Choisissez votre équipe !")) {
					core.openTeamChoice(p);
				}
			}else {
				return;
			}
		}else {
			return;
		}
	}

}
