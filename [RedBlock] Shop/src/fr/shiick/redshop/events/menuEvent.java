package fr.shiick.redshop.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import fr.shiick.redshop.RedShop;
import fr.shiick.redshop.utils.Menu;
import fr.shiick.redshop.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class menuEvent implements Listener {

	RedShop shop;
	Utils utils;
	Menu menu;
	
	public menuEvent(RedShop shop) {
		this.shop = shop;
		utils = new Utils(shop);
		menu = new Menu(shop);
	}
	
	@EventHandler
	public void onMenu(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		String mainName = shop.getConfig().getString("Menu.Main.Name");
		String rankName = shop.getConfig().getString("Menu.Ranks.Name");
		String confirmRankName = shop.getConfig().getString("Menu.BuyableRanks.Name");
		String infosName = shop.getConfig().getString("Menu.Infos.Name");
		if (e.getInventory().getName().equalsIgnoreCase(utils.colorMessage(mainName))) {
			e.setCancelled(true);
			String path = "Menu.Main.Categories.";
			for (String item : shop.getConfig().getConfigurationSection("Menu.Main.Categories").getKeys(false)) {
				Material mat = Material.valueOf(shop.getConfig().getString(path + item + ".Item"));
				int data = shop.getConfig().getInt(path + item + ".Data");
				String itemName = shop.getConfig().getString(path + item + ".Name");
				
				ItemStack itemStack = new ItemStack(mat, 1, (byte) data);
				ItemMeta meta = itemStack.getItemMeta();
				meta.setDisplayName(utils.colorMessage(itemName));
				if (shop.getConfig().contains(path + item + ".Lore")) {
					meta.setLore(utils.lore(path + item + ".Lore", 0, ""));
				}
				itemStack.setItemMeta(meta);
				if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
					String name = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
					String nameIS = ChatColor.stripColor(itemStack.getItemMeta().getDisplayName());
					if (name.equalsIgnoreCase(nameIS)) {
						p.closeInventory();
						selectAction(path + item, p, e.getClick(), itemStack, "");
					}
				}
			}
		} else if (e.getInventory().getName().equalsIgnoreCase(utils.colorMessage(rankName))) {
			e.setCancelled(true);
			String path = "Menu.Ranks.Categories.";
			for (String item : shop.getConfig().getConfigurationSection("Menu.Ranks.Categories").getKeys(false)) {
				Material mat = Material.valueOf(shop.getConfig().getString(path + item + ".Item"));
				int data = shop.getConfig().getInt(path + item + ".Data");
				String itemName = shop.getConfig().getString(path + item + ".Name");
				
				ItemStack itemStack = new ItemStack(mat, 1, (byte) data);
				if(itemStack.getType() == Material.LEATHER_BOOTS || itemStack.getType() == Material.LEATHER_LEGGINGS || itemStack.getType() == Material.LEATHER_CHESTPLATE || itemStack.getType() == Material.LEATHER_HELMET) {
					LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
					meta.setDisplayName(utils.colorMessage(itemName));
					meta.setColor(utils.getRGB(path + item + ".Color"));
					if (shop.getConfig().contains(path + item + ".Lore")) {
						meta.setLore(utils.lore(path + item + ".Lore", 0, ""));
					}
					itemStack.setItemMeta(meta);
					if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
						String name = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
						String nameIS = ChatColor.stripColor(itemStack.getItemMeta().getDisplayName());
						if (name.equalsIgnoreCase(nameIS)) {
							p.closeInventory();
							selectAction(path + item, p, e.getClick(), itemStack, "");
						}
					}
				} else {
					ItemMeta meta = itemStack.getItemMeta();
					meta.setDisplayName(utils.colorMessage(itemName));
					if (shop.getConfig().contains(path + item + ".Lore")) {
						meta.setLore(utils.lore(path + item + ".Lore", 0, ""));
					}
					itemStack.setItemMeta(meta);
					if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
						String name = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
						String nameIS = ChatColor.stripColor(itemStack.getItemMeta().getDisplayName());
						if (name.equalsIgnoreCase(nameIS)) {
							p.closeInventory();
							selectAction(path + item, p, e.getClick(), itemStack, "");
						}
					}
				}
			}
		} else if (e.getInventory().getName().equalsIgnoreCase(utils.colorMessage(confirmRankName))) {
			e.setCancelled(true);
			String path = "Menu.BuyableRanks.Categories.";
			String pathPrice = "Menu.Ranks.Categories.";
			for (String item : shop.getConfig().getConfigurationSection("Menu.BuyableRanks.Categories").getKeys(false)) {
				Material mat = Material.valueOf(shop.getConfig().getString(path + item + ".Item"));
				int data = shop.getConfig().getInt(path + item + ".Data");
				int price = shop.getConfig().getInt(pathPrice + shop.lastProductViewed.get(p.getName()) + ".Price");
				String itemName = shop.getConfig().getString(path + item + ".Name").replaceAll("%price%", price + "").replaceAll("%product%", shop.lastProductViewed.get(p.getName()));
				
				ItemStack itemStack = new ItemStack(mat, 1, (byte) data);
				ItemMeta meta = itemStack.getItemMeta();
				meta.setDisplayName(utils.colorMessage(itemName));
				if (shop.getConfig().contains(path + item + ".Lore")) {
					meta.setLore(utils.lore(path + item + ".Lore", price, item));
				}
				itemStack.setItemMeta(meta);
				if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
					String name = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
					String nameIS = ChatColor.stripColor(itemStack.getItemMeta().getDisplayName());
					if (name.equalsIgnoreCase(nameIS)) {
						p.closeInventory();
						selectAction(path + item, p, e.getClick(), itemStack, pathPrice + shop.lastProductViewed.get(p.getName()));
					}
				}
			}
		} else if (e.getInventory().getName().equalsIgnoreCase(utils.colorMessage(infosName))) {
			e.setCancelled(true);
			String path = "Menu.Infos.Categories.";
			for (String item : shop.getConfig().getConfigurationSection("Menu.Infos.Categories").getKeys(false)) {
				Material mat = Material.valueOf(shop.getConfig().getString(path + item + ".Item"));
				int data = shop.getConfig().getInt(path + item + ".Data");
				String itemName = shop.getConfig().getString(path + item + ".Name");
				
				ItemStack itemStack = new ItemStack(mat, 1, (byte) data);
				ItemMeta meta = itemStack.getItemMeta();
				meta.setDisplayName(utils.colorMessage(itemName));
				if (shop.getConfig().contains(path + item + ".Lore")) {
					meta.setLore(utils.lore(path + item + ".Lore", 0, ""));
				}
				itemStack.setItemMeta(meta);
				if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
					String name = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
					String nameIS = ChatColor.stripColor(itemStack.getItemMeta().getDisplayName());
					if (name.equalsIgnoreCase(nameIS)) {
						p.closeInventory();
						selectAction(path + item, p, e.getClick(), itemStack, "");
					}
				}
			}
		}
	}
	
	public void selectAction(String path, Player p, ClickType click, ItemStack is, String pathPrice) {
		String type = shop.getConfig().getString(path + ".Type");
		if (type.equalsIgnoreCase("MENU")) {
			String menuAction = shop.getConfig().getString(path + ".Menu");
			if (menuAction.equalsIgnoreCase("RANKS")) {
				menu.openRanks(p);
			}
		} else if (type.equalsIgnoreCase("MENUITEM")) {
			String menuAction = shop.getConfig().getString(path + ".Menu");
			if (menuAction.equalsIgnoreCase("INFOS")) {
				String product = shop.lastProductViewed.get(p.getName());
				menu.openInfo(p, product);
			}
		} else if (type.equalsIgnoreCase("MENUDUO")) {
			if (click == ClickType.LEFT) {
				String product = ChatColor.stripColor(is.getItemMeta().getDisplayName());
				menu.openInfo(p, product);
			} else if (click == ClickType.RIGHT) {
				int price = shop.getConfig().getInt(path + ".Price");
				String product = ChatColor.stripColor(is.getItemMeta().getDisplayName());
				shop.lastProductViewed.put(p.getName(), product);
				menu.openBuy(p, price, product);
			}
		} else if (type.equalsIgnoreCase("BUYRANK")) {
			int price = shop.getConfig().getInt(pathPrice + ".Price");
			String product = shop.lastProductViewed.get(p.getName());
			int coins = shop.coins.coinsAPI.getCoins(p);
			if (shop.coins.coinsAPI.canAfford(p, price)) {
				shop.coins.coinsAPI.withdrawCoins(p, price);
				int id = utils.getID();
				RedShop.sql.addTransaction(p, product, price, id++);
				String rank = shop.getConfig().getString(pathPrice + ".Rank");
				String cmd = "pp user " + p.getName() + " setrank " + rank;
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
				String msg = shop.getConfig().getString("Message.Buy.Broadcast").replaceAll("%product%", product).replaceAll("%player%", p.getName());
				Bukkit.broadcastMessage(utils.colorMessage(msg));
				String msgPl = shop.getConfig().getString("Message.Buy.Player").replaceAll("%product%", product).replaceAll("%price%", price + "");
				p.sendMessage(utils.colorMessage(msgPl));
				String msgTrans = shop.getConfig().getString("Message.Buy.Transaction").replaceAll("%id%", id++ + "");
				p.sendMessage(utils.colorMessage(msgTrans));
			} else {
				String msg = shop.getConfig().getString("Message.Buy.CoinsMissing").replaceAll("%coins%", price - coins + "").replaceAll("%price%", price + "");
				p.sendMessage(utils.colorMessage(msg));
			}
		}
	}
	
}
