package fr.shiick.redcore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.shiick.redcore.commands.hideCmd;
import fr.shiick.redcore.commands.inventoryCommand;
import fr.shiick.redcore.commands.killoNoobCmd;
import fr.shiick.redcore.commands.reloadCmd;
import fr.shiick.redcore.commands.sethomeCmd;
import fr.shiick.redcore.events.chatEvent;
import fr.shiick.redcore.events.commandsEvent;
import fr.shiick.redcore.events.connectEvent;
import fr.shiick.redcore.events.glideEvent;
import fr.shiick.redcore.events.interactEvent;
import fr.shiick.redcore.events.inventoryEvent;
import fr.shiick.redcore.events.worldChangeEvent;
import fr.shiick.redcore.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class RedCore extends JavaPlugin {
	
	public static File confFile;
	public Utils utils = new Utils(this);

	public HashMap<String, String> lastMessage = new HashMap<String, String>();
	public HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	
	public ArrayList<Player> vanished = new ArrayList<Player>();
	public ArrayList<Player> admin = new ArrayList<Player>();
	public ArrayList<Player> noob = new ArrayList<Player>();
	
	File file = new File("plugins/Essentials/config.yml");
	FileConfiguration configuration;
	
	public File yml = new File(this.getDataFolder() + "/Players.yml");
	public FileConfiguration config = YamlConfiguration.loadConfiguration(yml);
	
	@Override
	public void onEnable() {
		getCommand("shiickisdabest").setExecutor(new hideCmd(this));
		getCommand("killonoob").setExecutor(new killoNoobCmd(this));
		getCommand("nethersethome").setExecutor(new sethomeCmd(this));
		getCommand("corereload").setExecutor(new reloadCmd(this));
		getCommand("invcheck").setExecutor(new inventoryCommand(this));
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new chatEvent(this), this);
		pm.registerEvents(new commandsEvent(this), this);
		pm.registerEvents(new interactEvent(this), this);
		pm.registerEvents(new worldChangeEvent(this), this);
		pm.registerEvents(new connectEvent(this), this);
		pm.registerEvents(new glideEvent(this), this);
		pm.registerEvents(new inventoryEvent(this), this);
		
		saveDefaultConfig();
		loadConfig();
		confFile = yml;
		saveConfig(config, yml);
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		if (yc.getConfigurationSection("Preferences") == null) {
			yc.createSection("Preferences");
		}
		saveConfig(yc, confFile);
	}
	
	@Override
	public void onDisable() {
		for (Player p : vanished) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', (getConfig().getString("Message.Vanish.Reload"))));
			for (Player pl : Bukkit.getOnlinePlayers()) {
				pl.showPlayer(p);
			}
		}
	}
	
	public void saveConfig(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadConfig() {
		if (!file.exists()) {
		    return; // file is not exists
		}
		configuration = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileConfiguration getEssConfig() {
		return configuration;
	}
	
}
