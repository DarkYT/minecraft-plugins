package fr.shiick.redcore.events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.shiick.redcore.RedCore;
import fr.shiick.redcore.utils.Particles;
import fr.shiick.redcore.utils.Utils;
import net.minecraft.server.v1_12_R1.EnumParticle;

public class interactEvent implements Listener {

	RedCore core;
	Utils utils;

	public interactEvent(RedCore core) {
		this.core = core;
		utils = new Utils(core);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action action = e.getAction();
		if (action.equals(Action.LEFT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			Material mat = Material.getMaterial(core.getConfig().getString("Items.KillONoob.Material"));
			int data = core.getConfig().getInt("Items.KillONoob.Data");
			String name = core.getConfig().getString("Items.KillONoob.Name");
			ItemStack item = p.getInventory().getItemInMainHand();
			if (item.getType() == mat && item.getData().getData() == data && item.getItemMeta().getDisplayName().equalsIgnoreCase(utils.colorMessage(name))) {
				UUID uuid = e.getPlayer().getUniqueId();
				e.setCancelled(true);
				if (core.cooldown.containsKey(uuid)) {
					float time = (System.currentTimeMillis() - core.cooldown.get(uuid)) / 1000;
					if (!(time < 0.1f)) {
						p.getWorld().strikeLightning(e.getClickedBlock().getLocation());
						String msg = core.getConfig().getString("Message.KillONoob.Block");
						p.sendMessage(utils.colorMessage(msg));
						core.cooldown.put(uuid, System.currentTimeMillis());
					}
				} else {
					core.cooldown.put(uuid, System.currentTimeMillis());
				}
			}
		} else {
			World world = p.getWorld();
			if(world.equals(Bukkit.getWorld("world"))) {
				if(p.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD || p.getInventory().getItemInOffHand().getType() == Material.FISHING_ROD) {
					if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						if (!p.isOp()) {
							e.setCancelled(true);
							p.sendMessage(utils.colorMessage("&c&lHey ! &7Sorry, but you can't use that here."));
						}
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteractonEntity(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		Entity entity = e.getRightClicked();
		Location thunder = entity.getLocation();
		Material mat = Material.getMaterial(core.getConfig().getString("Items.KillONoob.Material"));
		int data = core.getConfig().getInt("Items.KillONoob.Data");
		String name = core.getConfig().getString("Items.KillONoob.Name");
		if (entity instanceof Player) {
			Player target = (Player) entity;
			ItemStack item = p.getInventory().getItemInMainHand();
			if (item.getType() == mat && item.getData().getData() == data && item.getItemMeta().getDisplayName().equalsIgnoreCase(utils.colorMessage(name))) {
				UUID uuid = e.getPlayer().getUniqueId();
				if (core.cooldown.containsKey(uuid)) {
					float time = (System.currentTimeMillis() - core.cooldown.get(uuid)) / 1000;
					if (!(time < 0.1f)) {
						e.setCancelled(true);
						core.noob.add(p);
						new Particles(thunder, EnumParticle.MOB_APPEARANCE, 1, 1, 1, 5, 1, target);
						target.playSound(thunder, Sound.ENTITY_GHAST_SCREAM, 1, 1);
						Bukkit.getScheduler().runTaskLater(core, new Runnable() {

							@Override
							public void run() {
								target.setHealth(0);
								p.getWorld().strikeLightningEffect(thunder);
								Bukkit.broadcastMessage(utils.colorMessage(core.getConfig().getString("Message.KillONoob.Broadcast").replace("%target%", target.getName())));
								for (Player p1 : Bukkit.getOnlinePlayers()) {
									p1.playSound(p1.getLocation(), Sound.ENTITY_BLAZE_DEATH, 1, 1);
								}
							}
						}, 70L);
						core.cooldown.put(uuid, System.currentTimeMillis());
					}
				} else {
					core.cooldown.put(uuid, System.currentTimeMillis());
				}
			}
		}
	}

}
