package fr.dark.staffmode.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;

public class Utils {
	
	static public void sendColorMessage(final Player p, final String s) {
		final String message = ChatColor.translateAlternateColorCodes('&', s);
		p.sendMessage(message);
	}
	
	static public String color(final String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	@SuppressWarnings("deprecation")
	static public ItemStack getModItem(final Material mat, final byte data, final String name, final boolean enchant, final String owner) {
		if(mat == Material.SKULL_ITEM) {
			ItemStack it = new ItemStack(mat,1,data);
			SkullMeta itM = (SkullMeta) it.getItemMeta();
			itM.setOwner(owner);
			itM.setDisplayName(name);
				itM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
				itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			
			it.setItemMeta(itM);
			return it;
		}else {
			ItemStack it = new ItemStack(mat,1,data);
			ItemMeta itM = it.getItemMeta();
			itM.setDisplayName(name);
				itM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, false);
				itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			
			it.setItemMeta(itM);
			return it;
		}
		
	}
}
