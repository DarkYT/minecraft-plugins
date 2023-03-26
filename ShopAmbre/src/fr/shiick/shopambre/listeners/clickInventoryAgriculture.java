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

public class clickInventoryAgriculture implements Listener {

	Core core;

	public clickInventoryAgriculture(Core c) {
		core = c;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		ItemStack ambre2 = new ItemStack(Material.DOUBLE_PLANT, 2);
		ItemMeta ambre2M = ambre2.getItemMeta();
		ambre2M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre2.setItemMeta(ambre2M);

		ItemStack carrot = new ItemStack(Material.CARROT_ITEM, 1);
		ItemStack potato = new ItemStack(Material.POTATO_ITEM, 1);
		ItemStack seeds = new ItemStack(Material.SEEDS, 1);
		ItemStack sugarcane = new ItemStack(Material.SUGAR_CANE, 1);
		ItemStack melon = new ItemStack(Material.MELON_SEEDS, 1);
		ItemStack pumpkin = new ItemStack(Material.PUMPKIN_SEEDS, 1);
		ItemStack cactus = new ItemStack(Material.CACTUS, 1);

		Dye cocoaD = new Dye();
		cocoaD.setColor(DyeColor.BROWN);
		ItemStack cocoa = cocoaD.toItemStack(1);

		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fAgriculture"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case CARROT_ITEM:
					if (p.getInventory().containsAtLeast(ambre2, 2)) {
						p.getInventory().removeItem(ambre2);
						p.getInventory().addItem(carrot);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 carotte !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case POTATO_ITEM:
					if (p.getInventory().containsAtLeast(ambre2, 2)) {
						p.getInventory().removeItem(ambre2);
						p.getInventory().addItem(potato);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&aVous venez d'acheter 1 pomme de terre !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case SEEDS:
					if (p.getInventory().containsAtLeast(ambre2, 2)) {
						p.getInventory().removeItem(ambre2);
						p.getInventory().addItem(seeds);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 graine !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case SUGAR_CANE:
					if (p.getInventory().containsAtLeast(ambre2, 2)) {
						p.getInventory().removeItem(ambre2);
						p.getInventory().addItem(sugarcane);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 canne à sucre !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case MELON_SEEDS:
					if (p.getInventory().containsAtLeast(ambre2, 2)) {
						p.getInventory().removeItem(ambre2);
						p.getInventory().addItem(melon);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 carotte !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case PUMPKIN_SEEDS:
					if (p.getInventory().containsAtLeast(ambre2, 2)) {
						p.getInventory().removeItem(ambre2);
						p.getInventory().addItem(pumpkin);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 graine de citrouille !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case CACTUS:
					if (p.getInventory().containsAtLeast(ambre2, 2)) {
						p.getInventory().removeItem(ambre2);
						p.getInventory().addItem(cactus);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 cactus !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case INK_SACK:
					short durability = e.getCurrentItem().getDurability();
					if (durability == 3) {
						if (p.getInventory().containsAtLeast(ambre2, 2)) {
							p.getInventory().removeItem(ambre2);
							p.getInventory().addItem(cocoa);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 cacao !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
						break;
					}
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
