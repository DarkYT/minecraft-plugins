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

public class clickInventoryCorrompu implements Listener {

	Core core;
	public clickInventoryCorrompu(Core c) {
		core = c;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		
		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta ambrem = ambre.getItemMeta();
		ambrem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambrem);
		
		ItemStack ambre4 = new ItemStack(Material.DOUBLE_PLANT, 4);
		ItemMeta ambre4m = ambre4.getItemMeta();
		ambre4m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre4.setItemMeta(ambre4m);
		
		ItemStack ambre8 = new ItemStack(Material.DOUBLE_PLANT, 8);
		ItemMeta ambre8m = ambre8.getItemMeta();
		ambre8m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre8.setItemMeta(ambre8m);
		
		ItemStack platine = new ItemStack(Material.NETHER_STAR);
		ItemMeta platinem = platine.getItemMeta();
		platinem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
		platine.setItemMeta(platinem);
		
		ItemStack platine32 = new ItemStack(Material.NETHER_STAR, 32);
		ItemMeta platine32m = platine32.getItemMeta();
		platine32m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
		platine32.setItemMeta(platine32m);
		
		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta topazem = topaze.getItemMeta();
		topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazem.addEnchant(Enchantment.DURABILITY, 1, true);
		topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazem);
		
		ItemStack topaze28 = new ItemStack(Material.DOUBLE_PLANT, 28);
		ItemMeta topaze28m = topaze28.getItemMeta();
		topaze28m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze28m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze28m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze28.setItemMeta(topaze28m);
		
		ItemStack prismarine = new ItemStack(Material.PRISMARINE, 1);	
		ItemStack pDark = new ItemStack(Material.PRISMARINE, 1,(byte)2);	
		ItemStack pBrick = new ItemStack(Material.PRISMARINE, 1,(byte)1);	
		ItemStack cobweb = new ItemStack(Material.WEB, 1);
		
		ItemStack diamond = new ItemStack(Material.DIAMOND_ORE, 1);
		ItemStack dragon = new ItemStack(Material.SKULL_ITEM, 1,(byte) 5);
		ItemStack skeleton = new ItemStack(Material.SKULL_ITEM, 1);
		ItemStack zombie = new ItemStack(Material.SKULL_ITEM, 1,(byte) 2);
		ItemStack creeper = new ItemStack(Material.SKULL_ITEM, 1,(byte) 4);
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1,(byte) 3);
		
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fCorrompu"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case ARROW:
					p.closeInventory();
					core.openWarps(p);
					break;
				case SEA_LANTERN:
					p.closeInventory();
					core.openMarin(p);
					break;
				case SKULL_ITEM:
					p.closeInventory();
					core.openHead(p);
					break;
				default:
					break;
				}
			}
		}else if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fMarin"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case ARROW:
					p.closeInventory();
					core.openCorrompu(p);
					break;
				case PRISMARINE:
					short durability = e.getCurrentItem().getDurability();
					if(durability == 0){
						if (p.getInventory().containsAtLeast(ambre, 4)) {
							p.getInventory().removeItem(ambre4);
							p.getInventory().addItem(prismarine);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 prismarine !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
						}
					}else if(durability == 1){
						if (p.getInventory().containsAtLeast(ambre, 4)) {
							p.getInventory().removeItem(ambre4);
							p.getInventory().addItem(pBrick);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 prismarine brick !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
						}
					}else if(durability == 2){
						if (p.getInventory().containsAtLeast(ambre, 4)) {
							p.getInventory().removeItem(ambre4);
							p.getInventory().addItem(pDark);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 dark prismarine !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
						}
					}
					break;
				case WEB:
					if (p.getInventory().containsAtLeast(ambre, 8)) {
						p.getInventory().removeItem(ambre8);
						p.getInventory().addItem(cobweb);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 toile d'araignée !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				default:
					break;
				}
			}
		}else if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fTêtes"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case ARROW:
					p.closeInventory();
					core.openCorrompu(p);
					break;
				case SKULL_ITEM:
					short durability = e.getCurrentItem().getDurability();
					if(durability == 0){
						if (p.getInventory().containsAtLeast(topaze, 1)) {
							p.getInventory().removeItem(topaze);
							p.getInventory().addItem(skeleton);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 tête de squelette !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de topaze !"));
						}
					}else if(durability == 2){
						if (p.getInventory().containsAtLeast(topaze, 1)) {
							p.getInventory().removeItem(topaze);
							p.getInventory().addItem(zombie);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 tête de zombie !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de topaze !"));
						}
					}else if(durability == 3){
						if (p.getInventory().containsAtLeast(topaze, 1)) {
							p.getInventory().removeItem(topaze);
							p.getInventory().addItem(head);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 tête !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de topaze !"));
						}
					}else if(durability == 4){
						if (p.getInventory().containsAtLeast(topaze, 1)) {
							p.getInventory().removeItem(topaze);
							p.getInventory().addItem(creeper);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 tête de creeper !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de topaze !"));
						}
					}else if(durability == 5){
						if (p.getInventory().containsAtLeast(platine, 32)) {
							p.getInventory().removeItem(platine32);
							p.getInventory().addItem(dragon);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 tête de dragon !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de platine !"));
						}
					}
					break;
				case DIAMOND_ORE:
					if (p.getInventory().containsAtLeast(topaze, 28)) {
						p.getInventory().removeItem(topaze28);
						p.getInventory().addItem(diamond);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 minerai de diamant !"));
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
