package fr.blizzrpg.classsystem;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.blizzrpg.classsystem.commands.classe;
import fr.blizzrpg.classsystem.listeners.deathEvent;
import fr.blizzrpg.classsystem.listeners.foodEvent;
import fr.blizzrpg.classsystem.listeners.joinEvent;
import fr.blizzrpg.classsystem.listeners.menuClassEvent;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

public class ClassAPI extends JavaPlugin {
	
	public static Chat chat = null;
	public static Permission perms = null;
	public static File confFile;
	
	public File customYml = new File(this.getDataFolder() + "/Players.yml");
	public FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customYml);
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onEnable(){
		if (!setupChat()){
			getLogger().severe(String.format("Vault est absent ! Désactivation...", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		setupPermissions();
		confFile = customYml;
		saveCustomYml(customConfig, customYml);
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new menuClassEvent(this), this);
		pm.registerEvents(new deathEvent(this), this);
		pm.registerEvents(new joinEvent(this), this);
		getCommand("classe").setExecutor(new classe(this));
		saveDefaultConfig();
	}
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
	private boolean setupChat() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp == null) {
            return false;
        }
        chat = rsp.getProvider();
        return chat != null;
    }
	
	public void checkLevel(Player p) {
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		int xp = yc.getInt(p.getName() + ".XP");
		int level = yc.getInt(p.getName() + ".Niveau");
		if(xp >= 1000 + (level*250)){
			yc.set(p.getName() + ".Niveau", level + 1);
			try {
				yc.save(confFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			yc.set(p.getName() + ".XP", 0);
			try {
				yc.save(confFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lNiveau supérieur : &6" + (level + 1)));
		}
	}
	
	public void menuClass(Player p){
		Inventory classes = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6&lClasse"));
		
		ItemStack swordman = new ItemStack(Material.IRON_SWORD);
		ItemMeta meta1 = swordman.getItemMeta();
		meta1.setDisplayName(ChatColor.DARK_AQUA + "Epéiste");
		swordman.setItemMeta(meta1);
		
		ItemStack archer = new ItemStack(Material.BOW);
		ItemMeta meta2 = archer.getItemMeta();
		meta2.setDisplayName(ChatColor.DARK_AQUA + "Archer");
		archer.setItemMeta(meta2);
		
		ItemStack barbarian = new ItemStack(Material.GOLD_AXE);
		ItemMeta meta3 = barbarian.getItemMeta();
		meta3.setDisplayName(ChatColor.DARK_AQUA + "Barbare");
		barbarian.setItemMeta(meta3);
		
		ItemStack worker = new ItemStack(Material.DIAMOND_SPADE);
		ItemMeta meta4 = worker.getItemMeta();
		meta4.setDisplayName(ChatColor.DARK_AQUA + "Travailleur");
		worker.setItemMeta(meta4);
		
		for (int i = 0; i < 9; i++) {
			if (classes.getItem(i) == null) {
				ItemStack wall = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
				ItemMeta meta = wall.getItemMeta();
				meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
				wall.setItemMeta(meta);
				classes.setItem(i, wall);
			}
		}
		
		classes.setItem(1, swordman);
		classes.setItem(3, archer);
		classes.setItem(5, barbarian);
		classes.setItem(7, worker);
		
		p.openInventory(classes);
	}
	
	public void giveStartup(Player p, String classe){
		if(classe.equals("Epeiste"
			ItemStack sword = new ItemStack(Material.WOOD_SWORD);
			ItemMeta swordM = sword.getItemMeta();
			swordM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Ep�e du novice &8(&aI&8)"));
			sword.setItemMeta(swordM);)){
			p.getInventory().clear
			
			p.getInventory().setItem(0, sword);
		}else if(classe.equals("Archerr();
			ItemStack bow = new ItemStack(Material.BOW);
			ItemMeta bowM = bow.getItemMeta();
			bowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Arc du novice &8(&aI&8"));
			bow.setItemMeta(bowM			p.getInventory().clear();
			
			p.getInventory().setItem(0, bow);
		}else if(classe.equals("Barbarer();
			ItemStack axe = new ItemStack(Material.IRON_AXE);
			Meta axeM = axe.getItemMeta();
			axeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Hache du novice &8(&aI&8"));
			axe.setItemMeta(axeM);
			p.getInventory().clear();
			p.getInventory().setItem(0, axe);ta a
		}else if(classe.equals("Travailleur")){
			
		}
	}
	
	public String getPlayerClass(Player p){
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		return yc.getString(p.getName() + ".Class");
	}
	
	public void setPlayerClass(Player p, String rank){
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		yc.set(p.getName() + ".Class", rank);
		try {
			yc.save(confFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		perms.playerAddGroup(null, p, rank);
		if(rank.equals("Epeiste")){
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Vous rejoignez la classe épéiste."));
		} else if(rank.equals("Archer")){
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Vous rejoignez la classe archer."));
		} else if(rank.equals("Barbare")){
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Vous rejoignez la classe barbare."));
		} else if(rank.equals("Travailleur")){
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Vous rejoignez la classe travailleur."));
		}
	}
	
	public static int getLevel(String p){
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		return yc.getInt(p + ".Niveau");
	}
	
	public static int getXP(String p){
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		return yc.getInt(p + ".XP");
	}

}
