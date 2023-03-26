package fr.shiick.redshop.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;

import fr.shiick.redshop.RedShop;

public class Utils {

	RedShop shop;
	
	public Utils(RedShop shop) {
		this.shop = shop;
	}
	
	public String colorMessage(String msg) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		return message;
	}
	
	public List<String> lore(String path, int price, String product) {
		List<String> lore = new ArrayList<String>();
		if (shop.getConfig().contains(path + ".1")) {
			if (shop.getConfig().getString(path + ".1").equalsIgnoreCase("")) {
				lore.add(" ");
			} else {
				String lore1 = shop.getConfig().getString(path + ".1").replaceAll("%price%", price + "").replaceAll("%product%", product);
				lore.add(colorMessage(lore1));
			}
		}
		if (shop.getConfig().contains(path + ".2")) {
			if (shop.getConfig().getString(path + ".2").equalsIgnoreCase("")) {
				lore.add(" ");
			} else {
				String lore2 = shop.getConfig().getString(path + ".2").replaceAll("%price%", price + "").replaceAll("%product%", product);
				lore.add(colorMessage(lore2));
			}
		}
		if (shop.getConfig().contains(path + ".3")) {
			if (shop.getConfig().getString(path + ".3").equalsIgnoreCase("")) {
				lore.add(" ");
			} else {
				String lore3 = shop.getConfig().getString(path + ".3").replaceAll("%price%", price + "").replaceAll("%product%", product);
				lore.add(colorMessage(lore3));
			}
		}
		if (shop.getConfig().contains(path + ".4")) {
			if (shop.getConfig().getString(path + ".4").equalsIgnoreCase("")) {
				lore.add(" ");
			} else {
				String lore4 = shop.getConfig().getString(path + ".4").replaceAll("%price%", price + "").replaceAll("%product%", product);
				lore.add(colorMessage(lore4));
			}
		}
		return lore;
	}
	
	public Color getRGB(String path) {
		int red = shop.getConfig().getInt(path + ".R");
		int green = shop.getConfig().getInt(path + ".G");
		int blue = shop.getConfig().getInt(path + ".B");
		Color rgb = Color.fromRGB(red, green, blue);
		return rgb;
	}
	
	public int getID() {
		int id = shop.getConfig().getInt("MySQL.ID");
		return id;
	}
	
}
