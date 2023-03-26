package fr.shiick.redshop.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import fr.shiick.redshop.RedShop;

public class Menu {
	
	RedShop shop;
	Utils utils;
	
	public Menu(RedShop shop) {
		this.shop = shop;
		utils = new Utils(shop);
	}

	public void openMain(Player p) {
		String name = shop.getConfig().getString("Menu.Main.Name");
		int size = shop.getConfig().getInt("Menu.Main.Size");
		Inventory inv = Bukkit.createInventory(null, size, utils.colorMessage(name));
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
			
			int position = shop.getConfig().getInt(path + item + ".Position") - 1;
			inv.setItem(position, itemStack);
		}
		
		p.openInventory(inv);
	}
	
	public void openRanks(Player p) {
		String name = shop.getConfig().getString("Menu.Ranks.Name");
		int size = shop.getConfig().getInt("Menu.Ranks.Size");
		Inventory inv = Bukkit.createInventory(null, size, utils.colorMessage(name));
		String path = "Menu.Ranks.Categories.";

		for (String item : shop.getConfig().getConfigurationSection("Menu.Ranks.Categories").getKeys(false)) {
			Material mat = Material.valueOf(shop.getConfig().getString(path + item + ".Item"));
			int data = shop.getConfig().getInt(path + item + ".Data");
			String itemName = shop.getConfig().getString(path + item + ".Name");
			
			ItemStack itemStack = new ItemStack(mat, 1, (byte) data);
			if(mat == Material.LEATHER_BOOTS || mat == Material.LEATHER_LEGGINGS || mat == Material.LEATHER_CHESTPLATE || mat == Material.LEATHER_HELMET) {
				LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
				meta.setDisplayName(utils.colorMessage(itemName));
				meta.setColor(utils.getRGB(path + item + ".Color"));
				if (shop.getConfig().contains(path + item + ".Lore")) {
					meta.setLore(utils.lore(path + item + ".Lore", 0, ""));
				}
				itemStack.setItemMeta(meta);
			} else {
				ItemMeta meta = itemStack.getItemMeta();
				meta.setDisplayName(utils.colorMessage(itemName));
				if (shop.getConfig().contains(path + item + ".Lore")) {
					meta.setLore(utils.lore(path + item + ".Lore", 0, ""));
				}
				itemStack.setItemMeta(meta);
			}
			
			int position = shop.getConfig().getInt(path + item + ".Position") - 1;
			inv.setItem(position, itemStack);
		}
		
		p.openInventory(inv);
	}
	
	public void openBuy(Player p, int price, String product) {
		String name = shop.getConfig().getString("Menu.BuyableRanks.Name");
		int size = shop.getConfig().getInt("Menu.BuyableRanks.Size");
		Inventory inv = Bukkit.createInventory(null, size, utils.colorMessage(name));
		String path = "Menu.BuyableRanks.Categories.";
		
		for (String item : shop.getConfig().getConfigurationSection("Menu.BuyableRanks.Categories").getKeys(false)) {
			Material mat = Material.valueOf(shop.getConfig().getString(path + item + ".Item"));
			int data = shop.getConfig().getInt(path + item + ".Data");
			String itemName = shop.getConfig().getString(path + item + ".Name").replaceAll("%price%", price + "").replaceAll("%product%", product);
			
			ItemStack itemStack = new ItemStack(mat, 1, (byte) data);
			ItemMeta meta = itemStack.getItemMeta();
			meta.setDisplayName(utils.colorMessage(itemName));
			if (shop.getConfig().contains(path + item + ".Lore")) {
				meta.setLore(utils.lore(path + item + ".Lore", price, product));
			}
			itemStack.setItemMeta(meta);
			
			int position = shop.getConfig().getInt(path + item + ".Position") - 1;
			inv.setItem(position, itemStack);
		}
		
		p.openInventory(inv);
	}
	
	public void openInfo(Player p, String product) {
		String name = shop.getConfig().getString("Menu.Infos.Name");
		int size = shop.getConfig().getInt("Menu.Infos.Size");
		Inventory inv = Bukkit.createInventory(null, size, utils.colorMessage(name));
		String path = "Menu.Infos.Categories.";
		
		for (String item : shop.getConfig().getConfigurationSection("Menu.Infos.Categories").getKeys(false)) {
			Material mat = Material.valueOf(shop.getConfig().getString(path + item + ".Item"));
			int data = shop.getConfig().getInt(path + item + ".Data");
			String itemName = shop.getConfig().getString(path + item + ".Name").replaceAll("%product%", product);
			
			ItemStack itemStack = new ItemStack(mat, 1, (byte) data);
			ItemMeta meta = itemStack.getItemMeta();
			meta.setDisplayName(utils.colorMessage(itemName));
			if (shop.getConfig().contains(path + item + ".Lore")) {
				meta.setLore(utils.lore(path + item + ".Lore", 0, ""));
			}
			itemStack.setItemMeta(meta);
			
			int position = shop.getConfig().getInt(path + item + ".Position") - 1;
			inv.setItem(position, itemStack);
		}
		
		p.openInventory(inv);
	}
	
}
