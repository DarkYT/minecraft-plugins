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

public class clickInventoryNether implements Listener {

	Core core;

	public clickInventoryNether(Core c) {
		core = c;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta ambreM = ambre.getItemMeta();
		ambreM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambreM);

		ItemStack ambre3 = new ItemStack(Material.DOUBLE_PLANT, 3);
		ItemMeta ambre3M = ambre3.getItemMeta();
		ambre3M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre3.setItemMeta(ambre3M);

		ItemStack ambre4 = new ItemStack(Material.DOUBLE_PLANT, 4);
		ItemMeta ambre4M = ambre4.getItemMeta();
		ambre4M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre4.setItemMeta(ambre4M);

		ItemStack ambre32 = new ItemStack(Material.DOUBLE_PLANT, 32);
		ItemMeta ambre32M = ambre32.getItemMeta();
		ambre32M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre32.setItemMeta(ambre32M);

		ItemStack netherrack = new ItemStack(Material.NETHERRACK, 32);
		ItemStack netherbrick = new ItemStack(Material.NETHER_BRICK, 8);
		ItemStack quartz = new ItemStack(Material.QUARTZ_BLOCK, 4);
		ItemStack soulsand = new ItemStack(Material.SOUL_SAND, 4);
		ItemStack glowstone = new ItemStack(Material.GLOWSTONE, 2);
		ItemStack netherwart = new ItemStack(Material.NETHER_STALK, 1);
		ItemStack blazerod = new ItemStack(Material.BLAZE_ROD, 1);
		ItemStack ghasttear = new ItemStack(Material.GHAST_TEAR, 1);

		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fNether"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case NETHERRACK:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(netherrack);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 32 netherrack !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case NETHER_BRICK:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(netherbrick);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&aVous venez d'acheter 8 briques du nether !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case QUARTZ_BLOCK:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(quartz);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&aVous venez d'acheter 4 blocs de quartz !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case SOUL_SAND:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(soulsand);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 soulsand !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case GLOWSTONE:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(glowstone);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&aVous venez d'acheter 2 blocs de glowstone !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case NETHER_STALK:
					if (p.getInventory().containsAtLeast(ambre3, 3)) {
						p.getInventory().removeItem(ambre3);
						p.getInventory().addItem(netherwart);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&aVous venez d'acheter 1 verrue du nether !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				case BLAZE_ROD:
					if (p.getInventory().containsAtLeast(ambre4, 4)) {
						p.getInventory().removeItem(ambre4);
						p.getInventory().addItem(blazerod);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&aVous venez d'acheter 1 bâton de blaze !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				case GHAST_TEAR:
					if (p.getInventory().containsAtLeast(ambre32, 32)) {
						p.getInventory().removeItem(ambre32);
						p.getInventory().addItem(ghasttear);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&aVous venez d'acheter 1 larme de ghast !"));
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
