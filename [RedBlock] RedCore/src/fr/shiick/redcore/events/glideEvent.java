package fr.shiick.redcore.events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;

import fr.shiick.redcore.RedCore;
import fr.shiick.redcore.utils.Utils;

public class glideEvent implements Listener {

	RedCore core;
	Utils utils;
	
	public glideEvent(RedCore core) {
		this.core = core;
		utils = new Utils(core);
	}
	
	@EventHandler
	public void onElytra(EntityToggleGlideEvent e) {
		Entity entity = e.getEntity();
		if(entity instanceof Player) {
			Player p = (Player) entity;
			World world = p.getWorld();
			if(world.equals(Bukkit.getWorld("world")) || world.equals(Bukkit.getWorld("world_the_end")) || world.equals(Bukkit.getWorld("world_iles_the_end"))) {
				if (!p.isOp()) {
					e.setCancelled(true);
					p.sendMessage(utils.colorMessage("&c&lHey ! &7Sorry, but you can't glide here."));
				}
			}
		}
	}
	
}
