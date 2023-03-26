package fr.shiick.shopambre.listeners;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import fr.shiick.shopambre.Core;

public class clickInventoryMinerais implements Listener {

	Core core;

	public clickInventoryMinerais(Core c) {
		core = c;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta ambreM = ambre.getItemMeta();
		ambreM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambreM);

		ItemStack ambre2 = new ItemStack(Material.DOUBLE_PLANT, 2);
		ItemMeta ambre2M = ambre2.getItemMeta();
		ambre2M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre2.setItemMeta(ambre2M);

		ItemStack ambre4 = new ItemStack(Material.DOUBLE_PLANT, 4);
		ItemMeta ambre4M = ambre4.getItemMeta();
		ambre4M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre4.setItemMeta(ambre4M);

		ItemStack ambre8 = new ItemStack(Material.DOUBLE_PLANT, 8);
		ItemMeta ambre8M = ambre8.getItemMeta();
		ambre8M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre8.setItemMeta(ambre8M);

		ItemStack ambre16 = new ItemStack(Material.DOUBLE_PLANT, 16);
		ItemMeta ambre16M = ambre8.getItemMeta();
		ambre16M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre16.setItemMeta(ambre16M);

		ItemStack redstone = new ItemStack(Material.REDSTONE, 8);
		ItemStack coal = new ItemStack(Material.COAL, 4);
		ItemStack quartz = new ItemStack(Material.QUARTZ, 4);
		Dye lapisD = new Dye();
		lapisD.setColor(DyeColor.BLUE);
		ItemStack lapis = lapisD.toItemStack(3);
		ItemStack ironingot = new ItemStack(Material.IRON_INGOT, 1);
		ItemStack diamant = new ItemStack(Material.DIAMOND, 1);
		ItemStack goldingot = new ItemStack(Material.GOLD_INGOT, 1);
		ItemStack emerald = new ItemStack(Material.EMERALD, 1);

		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fMinerais"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case REDSTONE:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(redstone);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 8 redstone !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case COAL:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(coal);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 charbons !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case QUARTZ:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(quartz);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 quartz !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case INK_SACK:
					short durability = e.getCurrentItem().getDurability();
					if (durability == 4) {
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(lapis);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&aVous venez d'acheter 3 lapis lazuli !"));
						} else {
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambres !"));
						}
						break;
					}
				case IRON_INGOT:
					if (p.getInventory().containsAtLeast(ambre8, 8)) {
						p.getInventory().removeItem(ambre8);
						p.getInventory().addItem(ironingot);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&aVous venez d'acheter 1 lingot de fer !"));
					} else {
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambres !"));
					}
					break;
				case DIAMOND:
					if (p.getInventory().containsAtLeast(ambre16, 16)) {
						p.getInventory().removeItem(ambre16);
						p.getInventory().addItem(diamant);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 diamant !"));
					} else {
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambres !"));
					}
					break;
				case GOLD_INGOT:
					if (p.getInventory().containsAtLeast(ambre4, 4)) {
						p.getInventory().removeItem(ambre4);
						p.getInventory().addItem(goldingot);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 lingot d'or !"));
					} else {
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambres !"));
					}
					break;
				case EMERALD:
					if (p.getInventory().containsAtLeast(ambre4, 4)) {
						p.getInventory().removeItem(ambre4);
						p.getInventory().addItem(emerald);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 émeraude !"));
					} else {
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambres !"));
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
