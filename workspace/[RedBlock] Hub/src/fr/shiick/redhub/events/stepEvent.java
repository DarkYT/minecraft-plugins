package fr.shiick.redhub.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import fr.shiick.redhub.RedHub;
import fr.shiick.redhub.utils.Utils;

public class stepEvent implements Listener {

	RedHub redhub;
	Utils utils;
	
	public stepEvent(RedHub redhub) {
		this.redhub = redhub;
		utils = new Utils(redhub);
	}
	
	@EventHandler
	public void onStep(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		double velocity = redhub.getConfig().getDouble("Launchpads.Velocity");
		String msg = redhub.getConfig().getString("Launchpads.Message");
		String sound = redhub.getConfig().getString("Launchpads.Sound");
		if(e.getTo().getBlock().getType() == Material.STONE_PLATE) {
			p.setVelocity(p.getLocation().getDirection().multiply(velocity));
			p.setVelocity(new Vector(p.getVelocity().getX(), 1.0D, p.getVelocity().getZ()));
			p.getWorld().playSound(p.getLocation(), Sound.valueOf(sound), 10.0F, 1.0F);
			boolean msgBool = redhub.getConfig().getBoolean("Launchpads.MsgEnabled");
			if(msgBool == true) {
				p.sendMessage(utils.colorMessage(msg));
			}
		}
	}
	
}
