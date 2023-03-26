package fr.shiick.shopambre.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.shiick.shopambre.Core;

public class clickInventoryCollection implements Listener {

	Core core;
	public clickInventoryCollection(Core c) {
		core = c;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta ambrem = ambre.getItemMeta();
		ambrem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambrem);
		
		ItemStack ambre2 = new ItemStack(Material.DOUBLE_PLANT, 2);
		ItemMeta ambre2m = ambre2.getItemMeta();
		ambre2m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre2.setItemMeta(ambre2m);
		
		ItemStack ambre4 = new ItemStack(Material.DOUBLE_PLANT, 4);
		ItemMeta ambre4m = ambre4.getItemMeta();
		ambre4m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre4.setItemMeta(ambre4m);
		
		ItemStack ambre8 = new ItemStack(Material.DOUBLE_PLANT, 8);
		ItemMeta ambre8m = ambre8.getItemMeta();
		ambre8m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre8.setItemMeta(ambre8m);
		
		ItemStack ambre64 = new ItemStack(Material.DOUBLE_PLANT, 64);
		ItemMeta ambre64m = ambre64.getItemMeta();
		ambre64m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre64.setItemMeta(ambre64m);
		
		ItemStack ambre128 = new ItemStack(Material.DOUBLE_PLANT, 128);
		ItemMeta ambre128m = ambre128.getItemMeta();
		ambre128m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre128.setItemMeta(ambre128m);
		
		ItemStack sea = new ItemStack(Material.SEA_LANTERN, 1);
		ItemStack wool = new ItemStack(Material.WOOL, 4);
		ItemStack saddle = new ItemStack(Material.SADDLE, 1);
		ItemStack xp = new ItemStack(Material.EXP_BOTTLE, 4);
		ItemStack anvil = new ItemStack(Material.ANVIL, 1);
		ItemStack slime = new ItemStack(Material.SLIME_BALL, 4);
		ItemStack ec = new ItemStack(Material.ENDER_CHEST, 1);
		ItemStack beetroot = new ItemStack(Material.BEETROOT_SEEDS, 1);
		
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fCollection"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case SEA_LANTERN:
					if (p.getInventory().containsAtLeast(ambre, 4)) {
						p.getInventory().removeItem(ambre4);
						p.getInventory().addItem(sea);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 Sea Lantern !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				case WOOL:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(wool);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 wool !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case SADDLE:
					if (p.getInventory().containsAtLeast(ambre, 64)) {
						p.getInventory().removeItem(ambre64);
						p.getInventory().addItem(saddle);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 selle !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				case EXP_BOTTLE:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(xp);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 bouteilles d'xp !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				case ANVIL:
					if (p.getInventory().containsAtLeast(ambre, 128)) {
						p.getInventory().removeItem(ambre128);
						p.getInventory().addItem(anvil);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 enclume !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				case SLIME_BALL:
					if (p.getInventory().containsAtLeast(ambre, 8)) {
						p.getInventory().removeItem(ambre8);
						p.getInventory().addItem(slime);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 slime ball !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				case ENDER_CHEST:
					if (p.getInventory().containsAtLeast(ambre, 64)) {
						p.getInventory().removeItem(ambre64);
						p.getInventory().addItem(ec);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 ender chest !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				case BEETROOT_SEEDS:
					if (p.getInventory().containsAtLeast(ambre, 2)) {
						p.getInventory().removeItem(ambre2);
						p.getInventory().addItem(beetroot);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 graine de bettrave !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
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
