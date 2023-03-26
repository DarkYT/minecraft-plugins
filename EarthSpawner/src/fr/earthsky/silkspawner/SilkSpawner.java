package fr.earthsky.silkspawner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.earthsky.silkspawner.commands.earthSpawner;
import fr.earthsky.silkspawner.listeners.breakSpawnerEvent;
import fr.earthsky.silkspawner.listeners.placeSpawnerEvent;

public class SilkSpawner extends JavaPlugin {
	
	@Override
	public void onEnable(){
		getCommand("earthspawner").setExecutor(new earthSpawner(this));
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new placeSpawnerEvent(this), this);
		pm.registerEvents(new breakSpawnerEvent(this), this);
	}
	
	public void getSpawner(Player p, String mob, int amount){
		if(mob.equalsIgnoreCase("cow")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("§7Spawner à vache");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);
			p.sendMessage("&aAjout d'un spawner à vache dans votre inventaire.");
		} else if(mob.equalsIgnoreCase("pig")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("§7Spawner à cochon");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);
			p.sendMessage("&aAjout d'un spawner à cochon dans votre inventaire.");
		} else if(mob.equalsIgnoreCase("zombie")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("§7Spawner à zombie");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);
			p.sendMessage("&aAjout d'un spawner à zombie dans votre inventaire.");
		} else if(mob.equalsIgnoreCase("skeleton")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("§7Spawner à vache");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);
			
		} else if(mob.equalsIgnoreCase("spider")){
			
		} else if(mob.equalsIgnoreCase("creeper")){
			
		} else if(mob.equalsIgnoreCase("slime")){
			
		} else if(mob.equalsIgnoreCase("iron_golem")){
			
		} 
	}
	
}
