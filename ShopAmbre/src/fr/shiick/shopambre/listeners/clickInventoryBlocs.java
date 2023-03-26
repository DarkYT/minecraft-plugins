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

public class clickInventoryBlocs implements Listener {

	Core core;

	public clickInventoryBlocs(Core c) {
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

		ItemStack ambre10 = new ItemStack(Material.DOUBLE_PLANT, 10);
		ItemMeta ambre10M = ambre10.getItemMeta();
		ambre10M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre10.setItemMeta(ambre10M);

		ItemStack ice = new ItemStack(Material.ICE, 16); // 1
		ItemStack packedIce = new ItemStack(Material.PACKED_ICE, 6); // 1
		ItemStack snow = new ItemStack(Material.SNOW_BLOCK, 4); // 1
		ItemStack grass = new ItemStack(Material.GRASS, 1); // 4
		ItemStack podzol = new ItemStack(Material.DIRT, (short) 1, (byte) 2); // 8
		ItemStack mycel = new ItemStack(Material.MYCEL, 1); // 10
		ItemStack sponge = new ItemStack(Material.SPONGE, 1); // 2
		ItemStack obsi = new ItemStack(Material.OBSIDIAN, 1); // 4

		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fBlocs"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case ICE:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(ice);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 16 glaces !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case PACKED_ICE:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(packedIce);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 6 glaces compactées !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case SNOW_BLOCK:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(snow);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 blocs de neiges !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case GRASS:
					if (p.getInventory().containsAtLeast(ambre4, 4)) {
						p.getInventory().removeItem(ambre4);
						p.getInventory().addItem(grass);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 blocs d'herbes !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambres !"));
					}
					break;
				case DIRT:
					short durability = e.getCurrentItem().getDurability();
					if (durability == 2) {
						if (p.getInventory().containsAtLeast(ambre8, 8)) {
							p.getInventory().removeItem(ambre8);
							p.getInventory().addItem(podzol);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 podzol !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambres !"));
						}
						p.closeInventory();
						break;
					}
				case MYCEL:
					if (p.getInventory().containsAtLeast(ambre10, 10)) {
						p.getInventory().removeItem(ambre10);
						p.getInventory().addItem(mycel);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 mycélium !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambres !"));
					}
					break;
				case SPONGE:
					if (p.getInventory().containsAtLeast(ambre2, 2)) {
						p.getInventory().removeItem(ambre2);
						p.getInventory().addItem(sponge);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 éponge !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambres !"));
					}
					break;
				case OBSIDIAN:
					if (p.getInventory().containsAtLeast(ambre4, 4)) {
						p.getInventory().removeItem(ambre4);
						p.getInventory().addItem(obsi);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 obsidienne !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambres !"));
					}
					break;
				case ARROW:
					p.closeInventory();
					core.openMain(p);
					break;
				default:
					break;
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez plus de place dans votre inventaire."));
			}
		}
	}

}
