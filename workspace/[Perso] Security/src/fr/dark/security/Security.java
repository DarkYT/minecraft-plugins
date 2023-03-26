package fr.dark.security;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Security extends JavaPlugin implements Listener {
	
	List<Player> notVerificatedPlayers = new ArrayList<>();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
	}
	
	@EventHandler
	public void onCick(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if(notVerificatedPlayers.contains(p)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(e.getPlayer().isOp()) {
			notVerificatedPlayers.add(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(notVerificatedPlayers.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(notVerificatedPlayers.contains(e.getPlayer())) {
			String password = getConfig().getString("Security.SecureWord");
			if(e.getMessage().equalsIgnoreCase(password)) {
				notVerificatedPlayers.remove(e.getPlayer());
				e.getPlayer().sendMessage("§aVous avez été vérifié. Bon jeu !");
				e.setCancelled(true);
			}else {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if(notVerificatedPlayers.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(notVerificatedPlayers.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteractAtEntity(PlayerInteractAtEntityEvent e) {
		if(notVerificatedPlayers.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		if(notVerificatedPlayers.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}

}
