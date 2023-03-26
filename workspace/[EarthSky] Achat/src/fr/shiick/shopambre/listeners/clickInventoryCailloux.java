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

public class clickInventoryCailloux implements Listener {

	Core core;

	public clickInventoryCailloux(Core c) {
		core = c;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta ambreM = ambre.getItemMeta();
		ambreM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambreM);

		ItemStack cobble = new ItemStack(Material.COBBLESTONE, 16);
		ItemStack andesite = new ItemStack(Material.STONE, 4, (byte) 5);
		ItemStack diorite = new ItemStack(Material.STONE, 4, (byte) 3);
		ItemStack granite = new ItemStack(Material.STONE, 4, (byte) 1);

		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fCailloux"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case COBBLESTONE:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(cobble);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&aVous venez d'acheter 16 cobblestones !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case STONE:
					short durability = e.getCurrentItem().getDurability();
					if (durability == 5) {
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(andesite);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&aVous venez d'acheter 4 bloc d'andésite !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
						break;
					} else if (durability == 3) {
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(diorite);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&aVous venez d'acheter 4 bloc de diorite !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
						break;
					} else if (durability == 1) {
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(granite);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&aVous venez d'acheter 4 bloc de granite !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
						break;
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
