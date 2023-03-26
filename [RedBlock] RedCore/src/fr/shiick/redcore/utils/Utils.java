package fr.shiick.redcore.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.shiick.redcore.RedCore;
import net.md_5.bungee.api.ChatColor;

public class Utils {
	
	RedCore core;
	
	public Utils(RedCore core) {
		this.core = core;
	}
	
	public String colorMessage(String msg) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		return message;
	}
	
	public List<String> lore(String path) {
		List<String> lore = new ArrayList<String>();
		for (String lores : core.getConfig().getConfigurationSection(path).getKeys(false)) {
			lore.add(colorMessage(core.getConfig().getString(path + "." + lores)));
		}
		return lore;
	}
	
	public void playSound(Player p, String command) {
		switch (command) {
		case "warn":
			Sound sound = Sound.valueOf(core.getConfig().getString("Sounds.WARN"));
			p.playSound(p.getLocation(), sound, 1, 1);
			break;
		case "kick":
			Sound sound2 = Sound.valueOf(core.getConfig().getString("Sounds.KICK"));
			p.playSound(p.getLocation(), sound2, 1, 1);
			break;
		case "ban":
			Sound sound3 = Sound.valueOf(core.getConfig().getString("Sounds.BAN"));
			p.playSound(p.getLocation(), sound3, 1, 1);
			break;
		case "mute":
			Sound sound4 = Sound.valueOf(core.getConfig().getString("Sounds.MUTE"));
			p.playSound(p.getLocation(), sound4, 1, 1);
			break;
		case "unban":
			Sound sound5 = Sound.valueOf(core.getConfig().getString("Sounds.UNBAN"));
			p.playSound(p.getLocation(), sound5, 1, 1);
			break;
		case "unmute":
			Sound sound6 = Sound.valueOf(core.getConfig().getString("Sounds.UNMUTE"));
			p.playSound(p.getLocation(), sound6, 1, 1);
			break;
		default:
			break;
		}
	}
	
	public boolean isFull(Player p) {
		Inventory inv = p.getInventory();
		if(!(inv.firstEmpty() == -1)) {
			return false;
		} else {
			return true;
		}
	}
	
	public ItemStack getKillONoob() {
		Material mat = Material.getMaterial(core.getConfig().getString("Items.KillONoob.Material"));
		int data = core.getConfig().getInt("Items.KillONoob.Data");
		String name = core.getConfig().getString("Items.KillONoob.Name");
		ItemStack item = new ItemStack(mat, 1, (byte) data);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(colorMessage(name));
		if (core.getConfig().contains("Items.KillONoob.Lore")) {
			itemM.setLore(lore("Items.KillONoob.Lore"));
		}
		itemM.addEnchant(Enchantment.DURABILITY, 1, true);
		itemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(itemM);
		return item;
	}

}
