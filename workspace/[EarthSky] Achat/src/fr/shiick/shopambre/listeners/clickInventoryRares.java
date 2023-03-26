package fr.shiick.shopambre.listeners;

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

public class clickInventoryRares implements Listener {

	Core core;
	public clickInventoryRares(Core c) {
		core = c;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e){
		
		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta ambrem = ambre.getItemMeta();
		ambrem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambrem);
		
		ItemStack ambre16 = new ItemStack(Material.DOUBLE_PLANT, 16);
		ItemMeta ambre16m = ambre16.getItemMeta();
		ambre16m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre16.setItemMeta(ambre16m);
		
		ItemStack platine = new ItemStack(Material.NETHER_STAR);
		ItemMeta platinem = platine.getItemMeta();
		platinem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
		platine.setItemMeta(platinem);
		
		ItemStack platine8 = new ItemStack(Material.NETHER_STAR, 8);
		ItemMeta platine8m = platine8.getItemMeta();
		platine8m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
		platine8.setItemMeta(platine8m);
		
		ItemStack platine16 = new ItemStack(Material.NETHER_STAR, 16);
		ItemMeta platine16m = platine16.getItemMeta();
		platine16m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
		platine16.setItemMeta(platine16m);
		
		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta topazem = topaze.getItemMeta();
		topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazem.addEnchant(Enchantment.DURABILITY, 1, true);
		topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazem);
		
		ItemStack topaze30 = new ItemStack(Material.DOUBLE_PLANT, 30);
		ItemMeta topaze30m = topaze30.getItemMeta();
		topaze30m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze30m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze30m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze30.setItemMeta(topaze30m);
		
		ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 1);		
		ItemStack diamond = new ItemStack(Material.DIAMOND_ORE, 1);		
		ItemStack chorus = new ItemStack(Material.CHORUS_FLOWER, 1);	
		ItemStack elytra = new ItemStack(Material.ELYTRA, 1);
		
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fObjets Rares"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case ARROW:
					p.closeInventory();
					core.openMain(p);
					break;
				case ENDER_PEARL:
					if (p.getInventory().containsAtLeast(ambre, 16)) {
						p.getInventory().removeItem(ambre16);
						p.getInventory().addItem(pearl);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 ender pearl !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				case DIAMOND_ORE:
					if (p.getInventory().containsAtLeast(topaze, 30)) {
						p.getInventory().removeItem(topaze30);
						p.getInventory().addItem(diamond);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 minerai de diamant !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
					}
					break;
				case CHORUS_FLOWER:
					if (p.getInventory().containsAtLeast(platine, 8)) {
						p.getInventory().removeItem(platine8);
						p.getInventory().addItem(chorus);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 fleur de chorus !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de platine !"));
					}
					break;
				case ELYTRA:
					if (p.getInventory().containsAtLeast(platine, 16)) {
						p.getInventory().removeItem(platine16);
						p.getInventory().addItem(elytra);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 elytra !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de platine !"));
					}
					break;
				default:
					break;
				}
			}
		}
	}
}
