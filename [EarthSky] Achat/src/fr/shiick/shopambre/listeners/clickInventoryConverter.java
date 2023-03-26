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

public class clickInventoryConverter implements Listener {

	Core core;

	public clickInventoryConverter(Core c) {
		core = c;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		

		ItemStack ambre9 = new ItemStack(Material.DOUBLE_PLANT, 9);
		ItemMeta ambre9M = ambre9.getItemMeta();
		ambre9M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre9.setItemMeta(ambre9M);
		
		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta ambrem = ambre.getItemMeta();
		ambrem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambrem);

		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta topazem = topaze.getItemMeta();
		topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazem.addEnchant(Enchantment.DURABILITY, 1, true);
		topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazem);
		
		ItemStack topaze9 = new ItemStack(Material.DOUBLE_PLANT, 9);
		ItemMeta topaze9m = topaze.getItemMeta();
		topaze9m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze9m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze9m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze9.setItemMeta(topaze9m);

		ItemStack platine = new ItemStack(Material.NETHER_STAR);
		ItemMeta platinem = platine.getItemMeta();
		platinem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
		platine.setItemMeta(platinem);
		

		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Convertisseur"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case ARROW:
					p.closeInventory();
					core.openMain(p);
					break;
				case DOUBLE_PLANT:
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6Convertir 9 ambres en 1 topaze"))){
						if (p.getInventory().containsAtLeast(ambre, 9)) {
							p.getInventory().removeItem(ambre9);
							p.getInventory().addItem(topaze);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez de convertir 9 ambres en 1 topaze !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
						}
					}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6Convertir 1 topaze en 9 ambres"))){
						if (p.getInventory().containsAtLeast(topaze, 1)) {
							p.getInventory().removeItem(topaze);
							p.getInventory().addItem(ambre9);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez de convertir 1 topaze en 9 ambres !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de topaze !"));
						}
					}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6Convertir 1 platine en 9 topazes"))){
						if (p.getInventory().containsAtLeast(platine, 1)) {
							p.getInventory().removeItem(platine);
							p.getInventory().addItem(topaze9);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez de convertir 1 platine en 9 topazes !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de platine !"));
						}
					}
					break;
				case NETHER_STAR:
					if (p.getInventory().containsAtLeast(topaze, 9)) {
						p.getInventory().removeItem(topaze9);
						p.getInventory().addItem(platine);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez de convertir 9 topaze en 1 platine !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
					}
					break;
				default:
					break;
				}
			}
		}
	}
	
}
