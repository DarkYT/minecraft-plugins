package fr.stellaria.hdv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.stellaria.hdv.api.ScrollerInventory;
import fr.stellaria.hdv.commands.sellCommand;
import fr.stellaria.hdv.listeners.sellListener;

public class Core extends JavaPlugin {

	public static File confFile;

	public File customYml = new File(getDataFolder() + "/Rewards.yml");
	public FileConfiguration customConfig = YamlConfiguration.loadConfiguration(this.customYml);
	public ArrayList<ItemStack> itemsloaded = new ArrayList<ItemStack>();
	public Map<Player, String> watchs = new HashMap<>();
	public Map<Integer, Entity> entities = new HashMap<>();

	@Override
	public void onEnable() {
		confFile = customYml;
		saveCustomYml(customConfig, customYml);
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		if (yc.getConfigurationSection("Rewards") == null) {
			yc.createSection("Rewards");
		}
		try {
			yc.save(Core.confFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new sellListener(this), this);
		getCommand("hdv").setExecutor(new sellCommand(this));
		saveDefaultConfig();
	}

	public void saveItem(String path, ItemStack itemStack) {
		getConfig().set(path, itemStack);
		saveConfig();
	}

	public void giveItem(String path, Player p) {
		ItemStack i = getConfig().getItemStack(path);
		p.getInventory().addItem(i);
		p.updateInventory();
	}

	public ItemStack loadItem(String path, String monnaie, int price, String seller, int hastag) {
		String money = monnaie;
		money = money.substring(0, 1).toUpperCase() + money.substring(1).toLowerCase();
		if (money.equals("Rubis")) {
			String name = "§4" + price + " " + money + " (#" + hastag + ")";
			ItemStack i = new ItemStack(getConfig().getItemStack(path));
			ItemMeta iM = i.getItemMeta();
			iM.setLore(Arrays.asList(name, "§fVendu par: " + seller));
			i.setItemMeta(iM);
			return i;
		} else if (money.equals("Saphirs")) {
			String name = "§1" + price + " " + money + " (#" + hastag + ")";
			ItemStack i = new ItemStack(getConfig().getItemStack(path));
			ItemMeta iM = i.getItemMeta();
			iM.setLore(Arrays.asList(name, "§fVendu par: " + seller));
			i.setItemMeta(iM);
			return i;
		} else if (money.equals("Péridots")) {
			String name = "§a" + price + " " + money + " (#" + hastag + ")";
			ItemStack i = new ItemStack(getConfig().getItemStack(path));
			ItemMeta iM = i.getItemMeta();
			iM.setLore(Arrays.asList(name, "§fVendu par: " + seller));
			i.setItemMeta(iM);
			return i;
		}
		return null;
	}
	
	public ItemStack newItem(Material mat, String name, int qtt, byte data){
		ItemStack i = new ItemStack(mat, qtt, data);
		ItemMeta iM = i.getItemMeta();
		iM.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		i.setItemMeta(iM);
		
		return i;
	}

	public void openSells(Player p, int multiplier) {
		Inventory inv = Bukkit.createInventory(null, 54, "§8Hôtel des Ventes (Page " + (multiplier + 1) + ")");
		int max = 53 * multiplier;
		int base = 0 + max;
		int maxSize = base + 53;
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fPage suivante"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);

		int place = 0;
		int count = 0;
		if (isEmptyConfig()) {
			p.openInventory(inv);
		} else {
			for (String sellers : getConfig().getConfigurationSection("Sells").getKeys(false)) {
				for (String items : getConfig().getConfigurationSection("Sells." + sellers).getKeys(false)) {
					if (count >= base && count <= maxSize) {
						inv.setItem(place,
								loadItem("Sells." + sellers + "." + items + ".item",
										getConfig().getString("Sells." + sellers + "." + items + ".money"),
										getConfig().getInt("Sells." + sellers + "." + items + ".price"),
										getConfig().getString("Sells." + sellers + "." + items + ".seller"),
										getConfig().getInt("Sells." + sellers + "." + items + ".hastag")));
					}
					place++;
					count++;
					maxSize--;
					if (place == 52) {
						place = 0;
					}
					if (maxSize == base) {
						break;
					}
				}
			}

			if (maxSize <= base) {
				inv.setItem(53, arrow);
			}
			p.openInventory(inv);
		}

	}

	public void opSell(Player p) {
		if(isEmptyConfig()){
			new ScrollerInventory(itemsloaded, "§8Hôtel des Ventes", p);
			return;
		}
		if(!itemsloaded.isEmpty()){
			new ScrollerInventory(itemsloaded, "§8Hôtel des Ventes", p);
			return;
		}
		for (String sellers : getConfig().getConfigurationSection("Sells").getKeys(false)) {
			for (String items : getConfig().getConfigurationSection("Sells." + sellers).getKeys(false)) {
				itemsloaded.add(loadItem("Sells." + sellers + "." + items + ".item", getConfig().getString("Sells." + sellers + "." + items + ".money"), getConfig().getInt("Sells." + sellers + "." + items + ".price"), getConfig().getString("Sells." + sellers + "." + items + ".seller"), getConfig().getInt("Sells." + sellers + "." + items + ".hastag")));
			}
		}
		new ScrollerInventory(itemsloaded, "§8Hôtel des Ventes", p);
	}
	
	public void openConfig(Player p){
		Inventory conf = Bukkit.createInventory(null, 9, "§8Suppression du villageois");
		conf.setItem(2, newItem(Material.WOOL, "§aSupprimer le PNJ", 1,(byte) 13));
		conf.setItem(6, newItem(Material.WOOL, "§cAnnuler l'opération", 1,(byte) 14));
		p.openInventory(conf);
	}

	public boolean isEmptyConfig() {
		return this.getConfig().getKeys(false).isEmpty();
	}

	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getMoney(String name, int nbr, Player p) {
		switch (name) {
		case "rubis":
			ItemStack rubis = new ItemStack(Material.FIREWORK_CHARGE, nbr);
			ItemMeta meta = rubis.getItemMeta();
			FireworkEffectMeta metaFw = (FireworkEffectMeta) meta;
			FireworkEffect effect = FireworkEffect.builder().withColor(Color.RED).build();
			metaFw.setEffect(effect);
			meta.setDisplayName("§4§lRubis");
			rubis.setItemMeta(metaFw);
			p.getInventory().addItem(rubis);
			p.updateInventory();
			break;
		case "saphirs":
			ItemStack saphir = new ItemStack(Material.FIREWORK_CHARGE, nbr);
			ItemMeta meta2 = saphir.getItemMeta();
			FireworkEffectMeta metaFw2 = (FireworkEffectMeta) meta2;
			FireworkEffect effect2 = FireworkEffect.builder().withColor(Color.BLUE).build();
			metaFw2.setEffect(effect2);
			meta2.setDisplayName("§1§lSaphir");
			saphir.setItemMeta(metaFw2);
			p.getInventory().addItem(saphir);
			p.updateInventory();
			break;
		case "péridots":
			ItemStack peridot = new ItemStack(Material.FIREWORK_CHARGE, nbr);
			ItemMeta meta3 = peridot.getItemMeta();
			FireworkEffectMeta metaFw3 = (FireworkEffectMeta) meta3;
			FireworkEffect effect3 = FireworkEffect.builder().withColor(Color.LIME).build();
			metaFw3.setEffect(effect3);
			meta3.setDisplayName("§a§lPéridot");
			peridot.setItemMeta(metaFw3);
			p.getInventory().addItem(peridot);
			p.updateInventory();
			break;
		default:
			break;
		}
	}
	
	public void spawnVillager(String name, Location loc) {
		Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
		villager.setCustomNameVisible(true);
		villager.setCustomName(name);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.setInvulnerable(true);
        villager.setCollidable(false);
	}
}
