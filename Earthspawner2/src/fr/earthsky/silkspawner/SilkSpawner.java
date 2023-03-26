package fr.earthsky.silkspawner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
			spawnerM.setDisplayName("§7Spawner    vache");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);
			p.sendMessagChatColor.translateAlternateColorCodes('&', e("&aAjout d'un spawner v  vache dans votre inventaire).");
		} else if(mob.equalsIgnoreCase("pig")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("p§7Spawner c  cochon");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);
			p.sendMesChatColor.translateAlternateColorCodes('&', sage("&aAjout d'un spawner h  cochon dans votre invent)aire.");
		} else if(mob.equalsIgnoreCase("zombie")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("n§7Spawner e  zombie");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);
			p.sendChatColor.translateAlternateColorCodes('&', Message("&aAjout d'un spawner    zombie dans votre inve)ntaire.");
		} else if(mob.equalsIgnoreCase("skeleton")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName(" §7Spawner e  squelette");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);
			p.sChatColor.translateAlternateColorCodes('&', endMessage("&aAjout d'un spawner    squelette dans votre i)nventaire.");
		} else if(mob.equalsIgnoreCase("spider")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("a§7Spawner;  araigwnee");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);
		ChatColor.translateAlternateColorCodes('&', 	p.sendMessage("&aAjout d'un spawner    araignv©e dans v)otre inventaire.");
		} else if(mob.equalsIgnoreCase("creeper")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("§7Spawner p  creeper");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);ChatColor.translateAlternateColorCodes('&', 
			p.sendMessage("&aAjout d'un spawner e  creeper dans) votre inventaire.");
		} else if(mob.equalsIgnoreCase("slime")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("§7Spawner r  slime");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawneChatColor.translateAlternateColorCodes('&', r);
			p.sendMessage("&aAjout d'un spawner à  slime )dans votre inventaire.");
		} else if(mob.equalsIgnoreCase("iron_golem")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("r§7Spawner awngolem de fer");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spaChatColor.translateAlternateColorCodes('&', wner);
			p.sendMessage("&aAjout d'un spawner    golem de fe)r dans else if(mob.equalsIgnoreCase("rabbit")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("§7Spawner à lapin");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aAjout d'un spawner à lapin dans votre inventaire."));
		} votre inventaire.");
		} else if(mob.equalsIgnoreCase("rabbit")){
			ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
			ItemMeta spawnerM = spawner.getItemMeta();
			spawnerM.setDisplayName("§7Spawner à  lapin");
			spawner.setItemMeta(spawnerM);
			
			p.getInventory().addItem(spawner);
			p.sendMessage("&aAjout d'un spawner à  lapin dans votre inventaire.");
		}
	}
	
}
