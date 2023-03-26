package fr.darkyt.nightvision;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.darkyt.nightvision.commands.CommandNv;
import fr.darkyt.nightvision.listeners.joinAndLeftEvents;

public class Main extends JavaPlugin {
	
	public static ArrayList <Player> nightvision = new ArrayList<Player>();
	
	@Override
	public void onEnable() {
		getCommand("nightvision").setExecutor(new CommandNv());
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new joinAndLeftEvents(), this);
	}

	public static void addNV(Player p){
		p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100000, 1));
		p.sendMessage("§aVous possédez désormais l'effet NightVision");
		nightvision.add(p);
	}
	
	public static void removeNV(Player p){
		p.removePotionEffect(PotionEffectType.NIGHT_VISION);
		p.sendMessage("§aVous ne possédez désormais plus l'effet NightVision");
		nightvision.remove(p);
	}
	
}
