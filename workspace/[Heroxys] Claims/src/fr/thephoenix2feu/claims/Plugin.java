package fr.thephoenix2feu.claims;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.dark.guilds.api.GuildAPI;

public class Plugin extends JavaPlugin{

	private HashMap<Player, Location> point1 = new HashMap<>();	
	private HashMap<Player, Location> point2 = new HashMap<>();
	
	public static ArrayList<Player> click = new ArrayList<>();
	private GuildAPI gAPI;
	
	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Commands(this), this);
		pm.registerEvents(new SelectZone(this), this);
		pm.registerEvents(new ProtectZone(this), this);
		
		this.gAPI = new GuildAPI();
		
		getConfig().options().copyDefaults(true);
		
		if(getConfig().get("list") instanceof ArrayList<?> == false) 
			getConfig().set("list", new ArrayList<String>());

		saveConfig();
	}
	
	public GuildAPI getAPI() {
		return gAPI;
	}


	public HashMap<Player, Location> getPoint1() {
		return point1;
	}


	public void setPoint1(HashMap<Player, Location> point1) {
		this.point1 = point1;
	}


	public HashMap<Player, Location> getPoint2() {
		return point2;
	}


	public void setPoint2(HashMap<Player, Location> point2) {
		this.point2 = point2;
	}
	
	
	public boolean isInto(Location loc, UUID owner, String parcelle) {
		int x1 = 0;
		int x2 = 0;
		int z1 = 0;
		int z2 = 0;
		if(parcelle.equalsIgnoreCase("null")) {
			x1 = getConfig().getInt("zone."+owner.toString()+".x1");
			x2 = getConfig().getInt("zone."+owner.toString()+".x2");
			z1 = getConfig().getInt("zone."+owner.toString()+".z1");
			z2 = getConfig().getInt("zone."+owner.toString()+".z2");
		}else {
			x1 = getConfig().getInt("zone."+owner.toString()+"."+parcelle+".x1");
			x2 = getConfig().getInt("zone."+owner.toString()+"."+parcelle+".x2");
			z1 = getConfig().getInt("zone."+owner.toString()+"."+parcelle+".z1");
			z2 = getConfig().getInt("zone."+owner.toString()+"."+parcelle+".z2");
		}
		boolean X = false;
		boolean Y = false;
		
		int xPlayer = loc.getBlockX();
		int zPlayer = loc.getBlockZ();
		
		if(x1 > x2) {
			if(x1 >= xPlayer && xPlayer >= x2) {
				X = true;
			}
		}else {
			if(x1 <= xPlayer && xPlayer <= x2) {
				X = true;
			}
		}
		
		if(z1 > z2) {
			if(z1 >= zPlayer && zPlayer >= z2) {
				Y = true;
			}
		}else {
			if(z1 <= zPlayer && zPlayer <= z2) {
				Y = true;
			}
		}
		
		if(Y == true && X == true) {
		return true;
		}
		return false;
		
		
	}
	
}
