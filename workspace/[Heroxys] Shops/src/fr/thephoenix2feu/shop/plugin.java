package fr.thephoenix2feu.shop;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;

import net.milkbowl.vault.economy.Economy;

public class plugin extends JavaPlugin implements Listener{

	public static Economy economy = null;
	public static plugin pl;
	public static WorldEditPlugin wep;
	
	@SuppressWarnings({ "static-access"})
	@Override
	public void onEnable() {
		System.out.println("Plugin Actif");
		this.pl = this;
		setupEconomy();
		
		org.bukkit.plugin.PluginManager pm = Bukkit.getPluginManager();
		getConfig().options().copyDefaults(true);
		saveConfig();
		wep = (WorldEditPlugin) pm.getPlugin("WorldEdit");
		pm.registerEvents(new Commande(this), this);
		pm.registerEvents(new ProtecShops(this), this);
		pm.registerEvents(new AdminShop(this), this);
		pm.registerEvents(new CommandsShop(this), this);
		pm.registerEvents(new MenuPlots(this), this);

		if(pl.getConfig().get("shops") instanceof ArrayList<?> == false) {
			ArrayList<String> a = new ArrayList<>();
			pl.getConfig().set("shops", a);
			pl.saveConfig();
		}
		
		schedule();
		
		super.onEnable();
	}
	
	
	
	   private boolean setupEconomy(){
	        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	        if (economyProvider != null) {
	            economy = economyProvider.getProvider();
	        }
			return (economy != null);
	   }
	   @SuppressWarnings("unused")
	public static CuboidSelection buildSection(String name) {
		   Location locA = (Location) pl.getConfig().get("shop."+name+".max");
			Location locB = (Location) pl.getConfig().get("shop."+name+".min");
			World w = Bukkit.getWorld(pl.getConfig().getString("shop."+name+".world"));
			CuboidSelection s = new CuboidSelection(w, locA, locB);
			if(s == null) {
				return null;
			}
			return s;
	   }
	  

		public static void putSection(String name, CuboidSelection c) {
			
			@SuppressWarnings("unchecked")
			ArrayList<String> a = (ArrayList<String>) pl.getConfig().get("shops");
			a.add(name);
			pl.getConfig().set("shops", a);
			pl.getConfig().set("shop."+name+".max",c.getMaximumPoint());
			pl.getConfig().set("shop."+name+".min",c.getMinimumPoint());
			pl.getConfig().set("shop."+name+".world",c.getWorld().getName());
			pl.getConfig().set("shop."+name+".owner", "nobody");
			pl.getConfig().set("shop."+name+".spawn", c.getMinimumPoint());
			pl.getConfig().set("shop."+name+".price", 1000);
			pl.getConfig().set("shop."+name+".name", "nobody");
			pl.getConfig().set("shop."+name+".name", "nobody");
			pl.saveConfig();
			
			return;
		}
		
		public static void schedule() {
			
			@SuppressWarnings("unused")
			int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
				
				@Override
				public void run() {
					@SuppressWarnings("unchecked")
					ArrayList<String> array = (ArrayList<String>) pl.getConfig().get("shops");
					if(array != null) {
						
						for(String string : array) {
							if(pl.getConfig().getString("shop."+string+".owner") != null) {
								pl.getConfig().set("shop."+string+".end", pl.getConfig().getInt("shop."+string+".end")-1);
								pl.saveConfig();
								if(pl.getConfig().getInt("shop."+string+".end") == 0) {
									pl.getConfig().set("shop."+string+".owner", "nobody");
									pl.getConfig().set("shop."+string+".name", "nobody");
									pl.saveConfig();
								}
							}
						}
						
					}
				
				}
			}, 1200, 1200);
			
		}
		
		
		@SuppressWarnings("unchecked")
		public static String getPlot(Player p){
			if((ArrayList<String>) pl.getConfig().get("shops") == null) {
				ArrayList<String> a = new ArrayList<>();
				pl.getConfig().set("shops", a);
				pl.saveConfig();
			}
			for(String strings : (ArrayList<String>) pl.getConfig().get("shops")) {
				
				CuboidSelection cube = buildSection(strings);
				if(cube.contains(p.getLocation())) {
					return strings;
				}
				
			}
						
			return "notfound";
		}

}
