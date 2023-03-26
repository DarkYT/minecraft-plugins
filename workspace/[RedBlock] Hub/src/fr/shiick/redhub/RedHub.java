package fr.shiick.redhub;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import fr.shiick.redhub.commands.hubReloadCmd;
import fr.shiick.redhub.commands.hubSetSpawnCmd;
import fr.shiick.redhub.commands.hubSpawnCmd;
import fr.shiick.redhub.commands.menuCmd;
import fr.shiick.redhub.events.citizensEvent;
import fr.shiick.redhub.events.diversEvent;
import fr.shiick.redhub.events.interactEvent;
import fr.shiick.redhub.events.joinEvent;
import fr.shiick.redhub.events.menuEvent;
import fr.shiick.redhub.events.stepEvent;
import fr.shiick.redhub.utils.Utils;
import fr.shiick.redshop.RedShop;

public class RedHub extends JavaPlugin {

	Utils utils;
	
	public RedShop shop = (RedShop) Bukkit.getPluginManager().getPlugin("Shop");

	public static File spawnFile;
	public File spawnYml = new File(this.getDataFolder() + "/Spawn.yml");
	public FileConfiguration spawnConfig = YamlConfiguration.loadConfiguration(spawnYml);

	public void saveConfig(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onEnable() {
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		utils = new Utils(this);
		PluginManager pm = Bukkit.getPluginManager();
		// Events
		pm.registerEvents(new stepEvent(this), this);
		pm.registerEvents(new menuEvent(this), this);
		pm.registerEvents(new joinEvent(this), this);
		pm.registerEvents(new interactEvent(this), this);
		pm.registerEvents(new diversEvent(this), this);
		pm.registerEvents(new citizensEvent(this), this);
		// Commands
		getCommand("menu").setExecutor(new menuCmd(this));
		getCommand("hubspawn").setExecutor(new hubSpawnCmd(this));
		getCommand("hubsetspawn").setExecutor(new hubSetSpawnCmd(this));
		getCommand("hubreload").setExecutor(new hubReloadCmd(this));
		// Configs
		spawnFile = spawnYml;
		saveConfig(spawnConfig, spawnYml);
		saveDefaultConfig();
		// Antivoid
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(RedHub.spawnFile);
		World world = Bukkit.getWorld(yc.getString("Spawn.World"));
		antiVoid(world);
	}

	public void antiVoid(World world) {
		BukkitScheduler scheduler = Bukkit.getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				for (Player p : world.getPlayers()) {
					int y = getConfig().getInt("Spawn.Void");
					if (p.getLocation().getBlockY() <= y) {
						YamlConfiguration yc = YamlConfiguration.loadConfiguration(RedHub.spawnFile);
						Location loc = new Location(world, yc.getLong("Spawn.X"), yc.getLong("Spawn.Y"), yc.getLong("Spawn.Z"), yc.getLong("Spawn.YAW"), yc.getLong("Spawn.PITCH"));
						p.teleport(loc);
						String msg = getConfig().getString("Spawn.Grabbed");
						p.sendMessage(utils.colorMessage(msg));
					}
				}
			}
		}, 0L, 20L);
	}

}