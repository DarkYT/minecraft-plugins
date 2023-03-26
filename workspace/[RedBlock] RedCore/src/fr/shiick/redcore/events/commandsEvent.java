package fr.shiick.redcore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import fr.shiick.redcore.RedCore;
import fr.shiick.redcore.utils.Utils;

public class commandsEvent implements Listener {

	RedCore core;
	Utils utils;
	
	public commandsEvent(RedCore core) {
		this.core = core;
		utils = new Utils(core);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String[] args = e.getMessage().split(" ");
		if (args[0].equalsIgnoreCase("/vanish") || args[0].equalsIgnoreCase("/v")) {
			e.setCancelled(true);
			if (p.hasPermission("vanish.use")) {
				if(core.vanished.contains(p)) {
					core.vanished.remove(p);
					String msg = core.getConfig().getString("Message.Vanish.Remove");
					p.sendMessage(utils.colorMessage(msg));
					for(Player pl : Bukkit.getOnlinePlayers()) {
						pl.showPlayer(p);
					}
				} else {
					core.vanished.add(p);
					String msg = core.getConfig().getString("Message.Vanish.Add");
					p.sendMessage(utils.colorMessage(msg));
					for(Player pl : Bukkit.getOnlinePlayers()) {
						pl.hidePlayer(p);
						if(pl.hasPermission("vanish.admin")) {
							pl.showPlayer(p);
						}
					}
				}
			} else {
				String noPerm = core.getConfig().getString("Message.NoPerm");
				p.sendMessage(utils.colorMessage(noPerm));
			}
		} else if(args[0].equalsIgnoreCase("/fly") || args[0].equalsIgnoreCase("/god") || args[0].equalsIgnoreCase("/speed") || args[0].equalsIgnoreCase("/feed") || args[0].equalsIgnoreCase("/heal")){
			if(p.getWorld().equals(Bukkit.getWorld("world_the_end")) || p.getWorld().equals(Bukkit.getWorld("world_iles_the_end"))) {
				if (!p.hasPermission("commands.world")) {
					e.setCancelled(true);
					String msg = core.getConfig().getString("Message.DisallowedCommand");
					p.sendMessage(utils.colorMessage(msg));
				}
			}
		} else if(args[0].equalsIgnoreCase("/sc") || args[0].equalsIgnoreCase("/staffchat")) {
			if (core.admin.contains(p)) {
				e.setCancelled(true);
				String msg = core.getConfig().getString("Message.Hide.Hidden");
				p.sendMessage(utils.colorMessage(msg));
			}
		} else if (args[0].equalsIgnoreCase("/plugins") || args[0].equalsIgnoreCase("/pl") || args[0].equalsIgnoreCase("/bukkit:plugins") || args[0].equalsIgnoreCase("/bukkit:pl") || args[0].equalsIgnoreCase("/bukkit:?") || args[0].equalsIgnoreCase("/?") || args[0].equalsIgnoreCase("/ver") || args[0].equalsIgnoreCase("/version") || args[0].equalsIgnoreCase("/gc") || args[0].equalsIgnoreCase("/icanhasbukkit") || args[0].equalsIgnoreCase("/a") || args[0].equalsIgnoreCase("/about") || args[0].equalsIgnoreCase("/bukkit:ver") || args[0].equalsIgnoreCase("/bukkit:version") || args[0].equalsIgnoreCase("/bukkit:about") || args[0].equalsIgnoreCase("/bukkit:a") || args[0].equalsIgnoreCase("/bukkit:help")) {
			if(!(p.isOp())) {
				String msg = core.getConfig().getString("Message.NoPlugins");
				p.sendMessage(utils.colorMessage(msg));
				e.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase("/minewebbridge") || args[0].equalsIgnoreCase("/mineweb") || args[0].equalsIgnoreCase("/minewebbridge:minewebbridge") || args[0].equalsIgnoreCase("/minewebbridge:mineweb")) {
			if(!(p.isOp())) {
				String msg = core.getConfig().getString("Message.NoBridge");
				p.sendMessage(utils.colorMessage(msg));
				e.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase("/warn")) {
			if (p.hasPermission("litebans.warn")) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target != null) {
					for(Player pl : Bukkit.getOnlinePlayers()) {
						utils.playSound(pl, "warn");
					}
				}
			}
		} else if (args[0].equalsIgnoreCase("/kick")) {
			if (p.hasPermission("litebans.kick")) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target != null) {
					for(Player pl : Bukkit.getOnlinePlayers()) {
						utils.playSound(pl, "kick");
					}
				}
			}
		} else if (args[0].equalsIgnoreCase("/ban") || args[0].equalsIgnoreCase("/ipban") || args[0].equalsIgnoreCase("/tempban") || args[0].equalsIgnoreCase("/tempipban")) {
			if (p.hasPermission("litebans.ban")) {
				if(!(args[1].equalsIgnoreCase("-n"))) {
					for(Player pl : Bukkit.getOnlinePlayers()) {
						utils.playSound(pl, "ban");
					}
				}
			}
		} else if (args[0].equalsIgnoreCase("/mute") || args[0].equalsIgnoreCase("/tempipmute") || args[0].equalsIgnoreCase("/tempmute")) {
			if (p.hasPermission("litebans.mute")) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target != null) {
					for(Player pl : Bukkit.getOnlinePlayers()) {
						utils.playSound(pl, "mute");
					}
				}
			}
		} else if (args[0].equalsIgnoreCase("/unban") || args[0].equalsIgnoreCase("/tempunban")) {
			if (p.hasPermission("litebans.unban")) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target != null) {
					for(Player pl : Bukkit.getOnlinePlayers()) {
						utils.playSound(pl, "unban");
					}
				}
			}
		}
	}
	
}
