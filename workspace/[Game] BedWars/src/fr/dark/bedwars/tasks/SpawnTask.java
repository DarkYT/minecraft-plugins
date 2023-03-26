package fr.dark.bedwars.tasks;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.gmail.filoghost.holograms.api.HolographicDisplaysAPI;
import com.gmail.filoghost.holographicdisplays.api.Hologram;

import fr.dark.bedwars.BedWars;
import fr.dark.bedwars.utils.Utils;

@SuppressWarnings("deprecation")
public class SpawnTask extends BukkitRunnable{

	BedWars core;
	
	int dTimer, eTimer, sTimer, bTimer;
	int dWait, eWait;
	int counter;
	
	public SpawnTask(BedWars core) {
		this.core = core;
		
		counter = 0;
		
		dWait = 25;
		eWait = 50;
		
		dTimer = dWait;
		eTimer = eWait;
		sTimer = 0;
		bTimer = 0;
	}
	
	@Override
	public void run() {
		if(counter == 1200) {
			Bukkit.broadcastMessage("§a[§2Bed§aWars] §fSpawn des diamants et des émeraudes au stade 2 !");
			dWait = 20;
			eWait = 45;
		}
		
		//Basic Spawn
		for(String s : core.getConfig().getConfigurationSection("BedWars.Config.ActiveMap").getKeys(false)) {
			if(s.contains("Team")) {
				Location tL = Utils.getLocationString(core.getConfig().getString("BedWars.Config.ActiveMap."+s+".OresSpawn"), core.activeMap.getWorld());
				spawn(Material.CLAY_BRICK, "§eBronze", tL);
				if(bTimer >= 8) spawn(Material.IRON_INGOT, "§7Argent", tL);
				if(sTimer >= 3) spawn(Material.GOLD_INGOT, "§6Or", tL);
			}
		}
		
		//Diamond
		for(String s : core.getConfig().getStringList("BedWars.Config.ActiveMap.Diamond")) {
			Location l = Utils.getLocationString(s, core.activeMap.getWorld());
			if(dTimer == 0) {
				spawn(Material.DIAMOND, "§bDiamant", l);
				dTimer = dWait;
			}
			Location holo = new Location(l.getWorld(), l.getX(), l.getY()+2,l.getZ());
			Hologram h = (Hologram) HolographicDisplaysAPI.createHologram(core, holo, "§bDiamant dans:", "§b"+dTimer+"s");
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {
				@Override
				public void run() {
					h.delete();
				}
			}, 19);
		}
		
		//Emerald
		for(String s : core.getConfig().getStringList("BedWars.Config.ActiveMap.Emerald")) {
			Location l = Utils.getLocationString(s, core.activeMap.getWorld());
			if(eTimer == 0) {
				spawn(Material.EMERALD, "§aEmeraude", l);
				eTimer = eWait;
			}
			Location holo = new Location(l.getWorld(), l.getX(), l.getY()+2,l.getZ());
			Hologram h = (Hologram) HolographicDisplaysAPI.createHologram(core, holo, "§aEmeraude dans:", "§b"+eTimer+"s");
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {
				@Override
				public void run() {
					h.delete();
				}
			}, 19);
		}
		
		if(sTimer >= 3) {
			sTimer = 0;
		}
		
		if(bTimer >= 8) {
			bTimer = 0;
			sTimer++;
		}
		
		//Timers
		dTimer--; eTimer--; bTimer++;
		counter++;
	}
	
	public void spawn(Material mat, String name, Location loc) {
		ItemStack it = new ItemStack(mat);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName(name);
		it.setItemMeta(itM);
		
		Location l = loc.clone().add(0.5,0.9,0.5);
		Item item = l.getWorld().dropItem(l, it);
		item.setVelocity(new Vector(0,0.1,0));
		item.teleport(l);
	}
	

}
