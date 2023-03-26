package fr.shiick.shopambre.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.shiick.shopambre.Core;

public class clickInventoryChasseur implements Listener {

	Core core;
	public clickInventoryChasseur(Core c) {
		core = c;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta ambreM = ambre.getItemMeta();
		ambreM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambreM);
		
		ItemStack eyes = new ItemStack(Material.SPIDER_EYE, 2);
		ItemStack rotten = new ItemStack(Material.ROTTEN_FLESH, 8);
		ItemStack creeper = new ItemStack(Material.SULPHUR, 8);
		ItemStack arrow = new ItemStack(Material.ARROW, 8);
		ItemStack string = new ItemStack(Material.STRING, 8);
		ItemStack bone = new ItemStack(Material.BONE, 8);
		
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fChasseur"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case SPIDER_EYE:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(eyes);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 2 yeux d'araignée !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case ROTTEN_FLESH:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(rotten);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 8 rotten flesh !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case SULPHUR:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(creeper);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 8 poudres de creeper !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case STRING:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(string);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 8 fils !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case BONE:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(bone);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 8 os !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case ARROW:
					if(e.getCurrentItem().containsEnchantment(Enchantment.DURABILITY)){
						p.closeInventory();
						core.openMain(p);
					}else{
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(arrow);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 8 flèches !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
					}
					break;
				default:
					break;
				}
			}
		}

	}

}
