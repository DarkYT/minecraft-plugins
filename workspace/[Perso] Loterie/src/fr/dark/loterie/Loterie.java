package fr.dark.loterie;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dark.loterie.commands.loterieCommand;
import fr.dark.loterie.listeners.loterieListener;
import net.md_5.bungee.api.ChatColor;
import task.CheckDateTask;

public class Loterie extends JavaPlugin {
	
	public static File confFile;
	private static Loterie instance;

	public File customYml = new File(getDataFolder() + "/Players.yml");
	public FileConfiguration customConfig = YamlConfiguration.loadConfiguration(this.customYml);
	
	@Override
	public void onEnable() {
		confFile = customYml;
		instance = this;
		saveCustomYml(customConfig, customYml);
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		if (yc.getConfigurationSection("Players") == null) {
			yc.createSection("Players");
		}
		saveCustomYml(yc, Loterie.confFile);
		
		if (yc.getConfigurationSection("Offlines") == null) {
			yc.createSection("Offlines");
		}
		saveCustomYml(yc, Loterie.confFile);
		
		getServer().getPluginManager().registerEvents(new loterieListener(this), this);
		
		getCommand("loterie").setExecutor(new loterieCommand(this));
		
		saveDefaultConfig();
		
		CheckDateTask task = new CheckDateTask(this);
		task.runTaskTimer(this, 0, 20*60);
	}
	
	public static Loterie getInstance() {
		return instance;
	}
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static public void lotteryDraw(final String message) {
		Bukkit.broadcastMessage(message);
		Bukkit.getScheduler().runTaskLater(Loterie.getInstance(), new Runnable() {

			@Override
			public void run() {
				YamlConfiguration yc = YamlConfiguration.loadConfiguration(Loterie.confFile);
				
				Random r = new Random();
				int randNum = r.nextInt(yc.getConfigurationSection("Players").getKeys(false).size());
				int confSize = yc.getConfigurationSection("Players").getKeys(false).size();
				int coef = Loterie.getInstance().getConfig().getInt("Loterie.Coef");
				int reward = confSize*coef;
				
				for(String s : yc.getConfigurationSection("Players").getKeys(false)) {
					int num = Integer.valueOf(s);
					if(randNum == num) {
						Player w = Bukkit.getPlayer(UUID.fromString(yc.getString("Players."+s)));
						Bukkit.broadcastMessage("§aLe gagnant de la loterie a été trouvé !");
						if(w != null) {
							ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT, reward);
							ItemMeta topazem = topaze.getItemMeta();
							topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
							topazem.addEnchant(Enchantment.DURABILITY, 1, true);
							topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
							topaze.setItemMeta(topazem);
							w.getInventory().addItem(topaze);
							w.sendMessage("§aTu viens de remporter "+reward+" topazes !");
						} else {
							OfflinePlayer oW = Bukkit.getOfflinePlayer(UUID.fromString(yc.getString("Players."+s)));
							yc.set("Offlines."+oW.getName(), reward);
							Loterie.getInstance().saveCustomYml(yc, Loterie.confFile);
						}
						yc.set("Players", null);
						Loterie.getInstance().saveCustomYml(yc, Loterie.confFile);
						yc.createSection("Players");
						Loterie.getInstance().saveCustomYml(yc, Loterie.confFile);
						break;
					}
				}
			}
			
		}, 40);
	}

}
