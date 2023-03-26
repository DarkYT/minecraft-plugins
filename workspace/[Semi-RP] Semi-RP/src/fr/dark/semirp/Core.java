package fr.dark.semirp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import fr.dark.semirp.commands.rpCommand;
import fr.dark.semirp.listeners.inventoryListener;
import fr.dark.semirp.listeners.joinListener;
import fr.dark.semirp.utils.Utils;

public class Core extends JavaPlugin{

	public Utils utils = new Utils();
	public List<RPPlayer> players = new ArrayList<>();
	public static SQLConnection sql;
	public static File confFile;

	public File customYml = new File(getDataFolder() + "/Players.yml");
	public FileConfiguration customConfig = YamlConfiguration.loadConfiguration(this.customYml);
	
	@Override
	public void onEnable() {	
		confFile = customYml;
		saveCustomYml(customConfig, customYml);
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		if (yc.getConfigurationSection("Players") == null) {
			yc.createSection("Players");
		}
		saveCustomYml(yc, Core.confFile);
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new joinListener(this), this);
		pm.registerEvents(new inventoryListener(this), this);
		
		getCommand("semirp").setExecutor(new rpCommand(this));
		
		saveDefaultConfig();
		
		String protocol = getConfig().getString("MySQL.Protocol");
		String host = getConfig().getString("MySQL.Host");
		String database = getConfig().getString("MySQL.Database");
		String username = getConfig().getString("MySQL.Username");
		String password = getConfig().getString("MySQL.Password");

		sql = new SQLConnection(protocol, host, database, username, password, this);
		sql.connection();
	}
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setLastLocation(Player p) {
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		String uuid = p.getUniqueId().toString();
		
		yc.set("Players."+uuid+".LastLoc.World", p.getLocation().getWorld().getName());
		yc.set("Players."+uuid+".LastLoc.X", p.getLocation().getX());
		yc.set("Players."+uuid+".LastLoc.Y", p.getLocation().getY());
		yc.set("Players."+uuid+".LastLoc.Z", p.getLocation().getZ());
		yc.set("Players."+uuid+".LastLoc.Yaw", p.getLocation().getYaw());
		yc.set("Players."+uuid+".LastLoc.Pitch", p.getLocation().getPitch());
		saveCustomYml(yc, Core.confFile);
	}
	
	public ItemStack newItem(Material mat, String desc, int nbr, String... lore) {
		ItemStack it = new ItemStack(mat,nbr);
		ItemMeta itm = it.getItemMeta();
		itm.setDisplayName(desc);
		itm.setLore(Arrays.asList(lore));
		it.setItemMeta(itm);
		return it;
	}
	
	public Location getLastLocation(Player p) {
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		String uuid = p.getUniqueId().toString();
		
		World w = Bukkit.getWorld(yc.getString("Players."+uuid+".LastLoc.World"));
		double x = yc.getDouble("Players."+uuid+".LastLoc.X");
		double y = yc.getDouble("Players."+uuid+".LastLoc.Y");
		double z = yc.getDouble("Players."+uuid+".LastLoc.Z");
		float yaw = (float) yc.getInt("Players."+uuid+".LastLoc.Yaw");
		float pitch = (float) yc.getInt("Players."+uuid+".LastLoc.Pitch");
		
		return new Location(w,x,y,z,yaw,pitch);
	}
	
	public Location getSpawn(){
		World w = Bukkit.getWorld(getConfig().getString("SemiRP.Spawn.World"));
		double x = getConfig().getDouble("SemiRP.Spawn.X");
		double y = getConfig().getDouble("SemiRP.Spawn.Y");
		double z = getConfig().getDouble("SemiRP.Spawn.Z");
		float yaw = (float) getConfig().getInt("SemiRP.Spawn.Yaw");
		float pitch = (float) getConfig().getInt("SemiRP.Spawn.Pitch");
		
		return new Location(w,x,y,z,yaw,pitch);
	}
	
	public void setSpawn(Location loc){
		getConfig().set("SemiRP.Spawn.World", loc.getWorld().getName());
		getConfig().set("SemiRP.Spawn.X", loc.getX());
		getConfig().set("SemiRP.Spawn.Y", loc.getY());
		getConfig().set("SemiRP.Spawn.Z", loc.getZ());
		getConfig().set("SemiRP.Spawn.Yaw", loc.getYaw());
		getConfig().set("SemiRP.Spawn.Pitch", loc.getPitch());
		saveConfig();
	}

	public void openJobs(Player p) {
		Inventory inv = Bukkit.createInventory(null, 18, utils.colorMessage(getConfig().getString("SemiRP.Jobs.InventoryName")));
		
		ItemStack potion = new ItemStack(Material.POTION, 1);
		PotionMeta meta = (PotionMeta) potion.getItemMeta();
		meta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
		meta.setDisplayName("§8Alchimiste");
		meta.setLore(Arrays.asList("§7Fabrique toutes sortes de breuvages","§7pour soigner les habitants"));
		potion.setItemMeta(meta);
		
		inv.setItem(1, newItem(Material.IRON_AXE, "§8Bûcheron", 1, "§7Fournit les habitants","§7en bois"));
		inv.setItem(2, newItem(Material.IRON_PICKAXE, "§8Mineur", 1, "§7Extrait les minerais précieux","§7pour les équipements"));
		inv.setItem(3, newItem(Material.CARROT_ITEM, "§8Fermier", 1, "§7Cultive ses champs pour fournir","§7les habitants en nourriture"));
		inv.setItem(4, newItem(Material.STONE, "§8Constructeur", 1, "§7Construit les bâtiments et les","§7complexes de la ville"));
		inv.setItem(5, potion);
		inv.setItem(6, newItem(Material.COOKED_BEEF, "§8Chasseur", 1, "§7Procure de la viande","§7aux habitants"));
		inv.setItem(7, newItem(Material.COOKED_FISH, "§8Pêcheur", 1, "§7Procure du poisson aux","§7habitants"));
		inv.setItem(13, newItem(Material.IRON_CHESTPLATE, "§8Forgeron", 1, "§7Fabrique l'équipement nécessaire","§7aux habitants de Zénor"));
		
		p.openInventory(inv);
	}
	
}
