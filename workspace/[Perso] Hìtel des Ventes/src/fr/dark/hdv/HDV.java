package fr.dark.hdv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.cloutteam.samjakob.gui.buttons.GUIButton;
import com.cloutteam.samjakob.gui.types.PaginatedGUI;

import fr.dark.hdv.commands.menuCommand;
import fr.dark.hdv.tasks.ClearTask;
import fr.dark.hdv.utils.CardboardBox;

public class HDV extends JavaPlugin implements Listener {
	
	public static final long days = 7 * 24 * 60 * 60 * 1000;
	
	public Map<UUID, ItemStack> items = new HashMap<>();
	
	public List<Player> players = new ArrayList<>();
	public static File confFile;
	
	private static HDV instance;
	
	public File customYml = new File(getDataFolder() + "/Sells.yml");
	public FileConfiguration customConfig = YamlConfiguration.loadConfiguration(this.customYml);
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		confFile = customYml;
		saveCustomYml(customConfig, customYml);
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		if (yc.getConfigurationSection("Players") == null) {
			yc.createSection("Players");
		}
		saveCustomYml(yc, HDV.confFile);
		
		if (yc.getConfigurationSection("Sells") == null) {
			yc.createSection("Sells");
		}
		saveCustomYml(yc, HDV.confFile);
		
		for(String s : yc.getConfigurationSection("Players").getKeys(false))
		{
			if(!yc.getConfigurationSection("Players."+s).contains("Date")) 
			{
				yc.set("Players."+s+".Date", System.currentTimeMillis());
				saveCustomYml(yc, confFile);
			}
		}
		
		ClearTask task = new ClearTask();
		task.runTaskTimer(this, 0, 20L * 20);
		
		getServer().getPluginManager().registerEvents(this, this);
		PaginatedGUI.prepare(this);
		
		getCommand("hdv").setExecutor(new menuCommand(this));
		
		saveDefaultConfig();
		
	}
	
	public static ItemStack create(EntityType type, int amount)
	{
		ItemStack item = new ItemStack(Material.MONSTER_EGG, amount);
		ItemMeta meta = item.getItemMeta();
		((SpawnEggMeta) meta).setSpawnedType(type);
		item.setItemMeta(meta);
		return item;
	}
	
	public static EntityType getType(ItemStack it)
	{
		EntityType result = null;
		ItemMeta meta = it.getItemMeta();
		if(meta instanceof SpawnEggMeta)
		{
			result = ((SpawnEggMeta) meta).getSpawnedType();
		}
		return result;
	}
	
	public static HDV getInstance(){
		return instance;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		if(getConfig().getConfigurationSection("ExpiredItems") == null)
		{
			return;
		}
		String uuid = getConfig().getConfigurationSection("ExpiredItems").getKeys(false).stream().filter(s -> getConfig().getString("ExpiredItems."+s+".Vendeur").equals(e.getPlayer().getUniqueId().toString())).findFirst().orElse("");
		if(!(uuid.equals("")))
		{
			ItemStack it = CardboardBox.unbox(getConfig().getString("ExpiredItems."+uuid+".Item"));
			items.put(e.getPlayer().getUniqueId(), it);
			e.getPlayer().sendMessage("§cL'un des items que vous avez mit en vente a expiré, veuillez le récupérer via le /hdv recup");
			getConfig().set("ExpiredItems."+uuid, null);
			saveConfig();
		}
	}
	
	@EventHandler
	public void onClick(PlayerInteractAtEntityEvent e) {
		if(e.getRightClicked().getName().equals("§8HDV")) {
			e.getPlayer().chat("/hdv");
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if(e.getInventory().getName().equals("§8Hôtel des Ventes - EarthSky")) {
			if(e.getPlayer() instanceof Player) {
				Player p = (Player) e.getPlayer();
				if(players.contains(p)) {
					players.remove(p);
				}
			}
		}
	}
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public ItemStack getSalableItem(String serial, int amount, String money, String uuid, String id) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
		
		ItemStack it = CardboardBox.getBaseItem(serial);
		ItemMeta meta = it.getItemMeta();
		meta.setLore(Arrays.asList(getColorFromMoney(money)+amount+" "+money+plural(amount),"§8Vendu par: §c"+p.getName(), "§7#"+id));
		it.setItemMeta(meta);
		
		return it;
	}
	
	public void open(Player pl) {
		players.add(pl);
		ArrayList<ItemStack> items = new ArrayList<>();
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(confFile);
		
		for(String s : yc.getConfigurationSection("Players").getKeys(false)) {
			String serial = yc.getString("Players."+s+".Item");
			int amount = yc.getInt("Players."+s+".Prix");
			String money = yc.getString("Players."+s+".Monnaie");
			String uuid = yc.getString("Players."+s+".Vendeur");
			items.add(getSalableItem(serial, amount, money, uuid, s));
		}
		
		PaginatedGUI menu = new PaginatedGUI("§8Hôtel des Ventes - EarthSky");
		menu.setDisplayName("§8Hôtel des Ventes - EarthSky");
		
		for(ItemStack item : items){
		    GUIButton button = new GUIButton(item);
		    button.setListener(e -> {
		        e.setCancelled(true);
		        if(e.getCurrentItem().getType() == Material.AIR) return;
		        YamlConfiguration yconf = YamlConfiguration.loadConfiguration(HDV.confFile);
		        String tag = e.getCurrentItem().getItemMeta().getLore().get(button.getItem().getItemMeta().getLore().size()-1);
		        int id = Integer.valueOf(ChatColor.stripColor(tag.replace("#", "")));
		        int price = yc.getInt("Players."+id+".Prix");
				String money = yc.getString("Players."+id+".Monnaie");
				String uuid = yc.getString("Players."+id+".Vendeur");
				String serial = yc.getString("Players."+id+".Item");
				
				if(!(e.getWhoClicked() instanceof Player)) return;
				Player p = (Player) e.getWhoClicked();
				
				if(p.getInventory().firstEmpty() != -1) {
					if(p.getInventory().containsAtLeast(getPrice(money), price)) {
						p.getInventory().removeItem(takeItem(money, price));
						p.getInventory().addItem(CardboardBox.unbox(serial));
						p.sendMessage("§aTu as bien acheté cet item !");
						yconf.set("Players."+id, null);
						saveCustomYml(yconf, HDV.confFile);
						p.closeInventory();
						open(p);
						yconf.set("Sells."+id+".Vendeur", uuid);
						yconf.set("Sells."+id+".Prix", price);
						yconf.set("Sells."+id+".Monnaie", money);
						saveCustomYml(yconf, HDV.confFile);
						for(Player player : players) {
							player.closeInventory();
							open(player);
						}
						OfflinePlayer seller = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
						if(seller.isOnline()) {
							Player sel = (Player) seller;
							sel.sendMessage("§6§l"+p.getName()+" §r§evient de t'acheter un item !");
						}
					}else {
						p.sendMessage("§cT'as pas de quoi acheter cet item ! Noob...");
						return;
					}
				}else {
					p.sendMessage("§cTon inventaire est plein ! Vide le avant d'acheter !");
					return;
				}
				
		    });
		    menu.addButton(button);
		}
		
		pl.openInventory(menu.getInventory());
	}

	public String plural(int amount) {
		if(amount == 1) {
			return "";
		}else if(amount > 1) {
			return "s";
		}
		return "";
	}
	
	public String getColorFromMoney(String money) {
		switch(money) {
		case "Ambre":
			return "§6§l";
		case "Topaze":
			return "§c§l";
		case "Platine":
			return "§4§l";
		default:
			return "";
		}
	}
	
	public ItemStack getPrice(String money) {
		switch(money) {
		case "Ambre":
			ItemStack ambre1 = new ItemStack(Material.DOUBLE_PLANT);
			ItemMeta ambre1M = ambre1.getItemMeta();
			ambre1M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
			ambre1.setItemMeta(ambre1M);
			
			return ambre1;
		case "Topaze":
			ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT);
			ItemMeta topazem = topaze.getItemMeta();
			topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
			topazem.addEnchant(Enchantment.DURABILITY, 1, true);
			topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
			topaze.setItemMeta(topazem);
			
			return topaze;
		case "Platine":
			ItemStack platine = new ItemStack(Material.NETHER_STAR);
			ItemMeta platinem = platine.getItemMeta();
			platinem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
			platine.setItemMeta(platinem);
			
			return platine;
		default:
			return null;
		}
	}
	
	public ItemStack takeItem(String money, int amount) {
		switch(money) {
		case "Ambre":
			ItemStack ambre1 = new ItemStack(Material.DOUBLE_PLANT, amount);
			ItemMeta ambre1M = ambre1.getItemMeta();
			ambre1M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
			ambre1.setItemMeta(ambre1M);
			
			return ambre1;
		case "Topaze":
			ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT, amount);
			ItemMeta topazem = topaze.getItemMeta();
			topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
			topazem.addEnchant(Enchantment.DURABILITY, 1, true);
			topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
			topaze.setItemMeta(topazem);
			
			return topaze;
		case "Platine":
			ItemStack platine = new ItemStack(Material.NETHER_STAR, amount);
			ItemMeta platinem = platine.getItemMeta();
			platinem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
			platine.setItemMeta(platinem);
			
			return platine;
		default:
			return null;
		}
	}
	
	public void give(Player p, String money, int amount) {
		switch(money) {
		case "Ambre":
			ItemStack ambre1 = new ItemStack(Material.DOUBLE_PLANT, amount);
			ItemMeta ambre1M = ambre1.getItemMeta();
			ambre1M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
			ambre1.setItemMeta(ambre1M);
			
			p.getInventory().addItem(ambre1);
			p.updateInventory();
			break;
		case "Topaze":
			ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT, amount);
			ItemMeta topazem = topaze.getItemMeta();
			topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
			topazem.addEnchant(Enchantment.DURABILITY, 1, true);
			topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
			topaze.setItemMeta(topazem);
			
			p.getInventory().addItem(topaze);
			p.updateInventory();
			break;
		case "Platine":
			ItemStack platine = new ItemStack(Material.NETHER_STAR, amount);
			ItemMeta platinem = platine.getItemMeta();
			platinem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
			platine.setItemMeta(platinem);
			
			p.getInventory().addItem(platine);
			p.updateInventory();
			break;
		default:
			break;
		}
	}

	public String getMoney(String string) {
		switch(string) {
		case "a":
			return "Ambre";
		case "t":
			return "Topaze";
		case "p":
			return "Platine";
		default:
			return "";
		}
	}

}
