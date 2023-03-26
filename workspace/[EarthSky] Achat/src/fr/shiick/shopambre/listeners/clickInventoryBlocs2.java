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

public class clickInventoryBlocs2 implements Listener {

	Core core;

	public clickInventoryBlocs2(Core c) {
		core = c;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta ambreM = ambre.getItemMeta();
		ambreM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambreM);

		ItemStack ambre4 = new ItemStack(Material.DOUBLE_PLANT, 4);
		ItemMeta ambre4M = ambre4.getItemMeta();
		ambre4M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre4.setItemMeta(ambre4M);

		ItemStack dirt = new ItemStack(Material.DIRT, 4);
		ItemStack sand = new ItemStack(Material.SAND, 4);
		ItemStack redSand = new ItemStack(Material.SAND, 4, (byte) 1);
		ItemStack gravel = new ItemStack(Material.GRAVEL, 4);
		ItemStack clay = new ItemStack(Material.CLAY, 4);
		ItemStack oak = new ItemStack(Material.LOG, 4);
		ItemStack brick = new ItemStack(Material.BRICK, 4);
		ItemStack enderstone = new ItemStack(Material.ENDER_STONE, 1);

		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fBlocs 2"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case DIRT:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(dirt);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 bloc de terre !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case SAND:
					short durability = e.getCurrentItem().getDurability();
					if (durability == 0) {
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(sand);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 bloc de sable !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
						break;
					} else if (durability == 1) {
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(redSand);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 bloc de sable rouge !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
						break;
					}
				case GRAVEL:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(gravel);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 bloc de gravier !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case CLAY:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(clay);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 bloc d'argile !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case LOG:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(oak);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 buche de chêne !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case BRICK:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(brick);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 bloc de brique !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case ENDER_STONE:
					if (p.getInventory().containsAtLeast(ambre, 4)) {
						p.getInventory().removeItem(ambre4);
						p.getInventory().addItem(enderstone);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 de pierre de l'end !"));
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
			}
		}
	}

}
