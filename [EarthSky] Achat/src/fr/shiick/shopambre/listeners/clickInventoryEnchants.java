package fr.shiick.shopambre.listeners;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.shiick.shopambre.Core;

public class clickInventoryEnchants implements Listener {

	Core core;
	
	public clickInventoryEnchants(Core c) {
		core = c;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta topazem = topaze.getItemMeta();
		topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazem.addEnchant(Enchantment.DURABILITY, 1, true);
		topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazem);
		
		ItemStack topaze2 = new ItemStack(Material.DOUBLE_PLANT, 2);
		ItemMeta topaze2m = topaze2.getItemMeta();
		topaze2m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze2m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze2m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze2.setItemMeta(topaze2m);
		
		ItemStack topaze4 = new ItemStack(Material.DOUBLE_PLANT, 4);
		ItemMeta topaze4m = topaze4.getItemMeta();
		topaze4m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze4m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze4m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze4.setItemMeta(topaze4m);
		
		ItemStack topaze6 = new ItemStack(Material.DOUBLE_PLANT, 6);
		ItemMeta topaze6m = topaze4.getItemMeta();
		topaze6m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze6m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze6m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze6.setItemMeta(topaze6m);
		
		ItemStack topaze8 = new ItemStack(Material.DOUBLE_PLANT, 8);
		ItemMeta topaze8m = topaze4.getItemMeta();
		topaze8m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze8m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze8m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze8.setItemMeta(topaze8m);
		
		ItemStack topaze64 = new ItemStack(Material.DOUBLE_PLANT, 64);
		ItemMeta topaze64m = topaze64.getItemMeta();
		topaze64m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze64m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze64m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze64.setItemMeta(topaze64m);
		
		ItemStack power = new ItemStack(Material.ENCHANTED_BOOK, 1);
		core.addBookEnchantment(power, Enchantment.ARROW_DAMAGE, 1);
		
		ItemStack sharpness = new ItemStack(Material.ENCHANTED_BOOK, 1);
		core.addBookEnchantment(sharpness, Enchantment.DAMAGE_ALL, 1);
		
		ItemStack efficiency = new ItemStack(Material.ENCHANTED_BOOK, 1);
		core.addBookEnchantment(efficiency, Enchantment.DIG_SPEED, 1);
		
		ItemStack protection = new ItemStack(Material.ENCHANTED_BOOK, 1);
		core.addBookEnchantment(protection, Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		
		ItemStack unbreaking = new ItemStack(Material.ENCHANTED_BOOK, 1);
		core.addBookEnchantment(unbreaking, Enchantment.DURABILITY, 1);
		
		ItemStack looting = new ItemStack(Material.ENCHANTED_BOOK, 1);
		core.addBookEnchantment(looting, Enchantment.LOOT_BONUS_MOBS, 1);
		
		ItemStack feather = new ItemStack(Material.ENCHANTED_BOOK, 1);
		core.addBookEnchantment(feather, Enchantment.PROTECTION_FALL, 1);
		
		ItemStack mending = new ItemStack(Material.ENCHANTED_BOOK, 1);
		core.addBookEnchantment(mending, Enchantment.MENDING, 1);
		
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fEnchants"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case ENCHANTED_BOOK:
					if(e.getCurrentItem().getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Power 1")))){
						if (p.getInventory().containsAtLeast(topaze, 2)) {
							p.getInventory().removeItem(topaze2);
							p.getInventory().addItem(power);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 livre Power 1 !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
						}
					}
					if(e.getCurrentItem().getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Sharpness 1")))){
						if (p.getInventory().containsAtLeast(topaze, 4)) {
							p.getInventory().removeItem(topaze4);
							p.getInventory().addItem(sharpness);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 livre Sharpness 1 !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
						}
					}
					if(e.getCurrentItem().getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Efficiency 1")))){
						if (p.getInventory().containsAtLeast(topaze, 4)) {
							p.getInventory().removeItem(topaze4);
							p.getInventory().addItem(efficiency);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 livre Efficiency 1 !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
						}
					}
					if(e.getCurrentItem().getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Protection 1")))){
						if (p.getInventory().containsAtLeast(topaze, 4)) {
							p.getInventory().removeItem(topaze4);
							p.getInventory().addItem(protection);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 livre Protection 1 !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
						}
					}
					if(e.getCurrentItem().getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Unbreaking 1")))){
						if (p.getInventory().containsAtLeast(topaze, 6)) {
							p.getInventory().removeItem(topaze6);
							p.getInventory().addItem(unbreaking);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 livre Unbreaking 1 !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
						}
					}
					if(e.getCurrentItem().getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Looting 1")))){
						if (p.getInventory().containsAtLeast(topaze, 8)) {
							p.getInventory().removeItem(topaze8);
							p.getInventory().addItem(looting);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 livre Looting 1 !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
						}
					}
					if(e.getCurrentItem().getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Feather Falling 1")))){
						if (p.getInventory().containsAtLeast(topaze, 8)) {
							p.getInventory().removeItem(topaze8);
							p.getInventory().addItem(feather);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 livre Feather Falling 1 !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
						}
					}
					if(e.getCurrentItem().getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Mending")))){
						if (p.getInventory().containsAtLeast(topaze, 64)) {
							p.getInventory().removeItem(topaze64);
							p.getInventory().addItem(mending);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 livre Mending !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
						}
					}
					break;
				case ARROW:
					p.closeInventory();
					core.openMain(p);
					break;
				default:
					break;
				}
			}
		}
	}

}
