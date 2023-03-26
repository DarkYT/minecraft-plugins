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

public class clickInventoryVampire implements Listener {
	
	Core core;
	public clickInventoryVampire(Core c) {
		core = c;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		
		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta ambrem = ambre.getItemMeta();
		ambrem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambrem);
		
		ItemStack ambre2 = new ItemStack(Material.DOUBLE_PLANT, 2);
		ItemMeta ambre2m = ambre2.getItemMeta();
		ambre2m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre2.setItemMeta(ambre2m);
		
		ItemStack ambre8 = new ItemStack(Material.DOUBLE_PLANT, 8);
		ItemMeta ambre8m = ambre8.getItemMeta();
		ambre8m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre8.setItemMeta(ambre8m);
		
		ItemStack platine = new ItemStack(Material.NETHER_STAR);
		ItemMeta platinem = platine.getItemMeta();
		platinem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
		platine.setItemMeta(platinem);
		
		ItemStack platine64 = new ItemStack(Material.NETHER_STAR, 64);
		ItemMeta platine64m = platine64.getItemMeta();
		platine64m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
		platine64.setItemMeta(platine64m);
		
		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta topazem = topaze.getItemMeta();
		topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazem.addEnchant(Enchantment.DURABILITY, 1, true);
		topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazem);
		
		ItemStack topaze26 = new ItemStack(Material.DOUBLE_PLANT, 26);
		ItemMeta topaze26m = topaze26.getItemMeta();
		topaze26m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze26m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze26m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze26.setItemMeta(topaze26m);
		
		ItemStack blue = new ItemStack(Material.RED_ROSE, 4,(byte)1);		
		ItemStack pink = new ItemStack(Material.RED_ROSE, 4,(byte)2);		
		ItemStack white = new ItemStack(Material.RED_ROSE, 4,(byte)3);		
		ItemStack red = new ItemStack(Material.RED_ROSE, 4,(byte)4);		
		ItemStack orange = new ItemStack(Material.RED_ROSE, 4,(byte)5);		
		ItemStack white2 = new ItemStack(Material.RED_ROSE, 4,(byte)6);		
		ItemStack beige = new ItemStack(Material.RED_ROSE, 4,(byte)7);		
		ItemStack tulip = new ItemStack(Material.RED_ROSE, 4,(byte)8);
		
		ItemStack diamond = new ItemStack(Material.DIAMOND_ORE, 1);		
		ItemStack endrode = new ItemStack(Material.END_ROD, 1);		
		ItemStack endstone = new ItemStack(Material.ENDER_STONE, 4);		
		ItemStack purpur = new ItemStack(Material.PURPUR_BLOCK, 4);		
		ItemStack pPillar = new ItemStack(Material.PURPUR_PILLAR, 4);				
		ItemStack fruit = new ItemStack(Material.CHORUS_FRUIT_POPPED, 1);		
		ItemStack chorus = new ItemStack(Material.CHORUS_FRUIT, 1);		
		ItemStack endportal = new ItemStack(Material.ENDER_PORTAL_FRAME, 1);
		
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fVampire"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case ARROW:
					p.closeInventory();
					core.openWarps(p);
					break;
				case RED_ROSE:
					p.closeInventory();
					core.openFlower(p);
					break;
				case ENDER_STONE:
					p.closeInventory();
					core.openEnd(p);
					break;
				default:
					break;
				}
			}
		}else if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fFleurs"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case ARROW:
					p.closeInventory();
					core.openVampire(p);
					break;
				case RED_ROSE:
					short durability = e.getCurrentItem().getDurability();
					if(durability == 1){
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(blue);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 tulipes bleues !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
					}else if(durability == 2){
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(pink);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 alliums !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
					}else if(durability == 3){
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(white);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 fleurs blanches !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
					}else if(durability == 4){
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(red);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 tulipes rouges !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
					}else if(durability == 5){
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(orange);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 tulipes oranges !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
					}else if(durability == 6){
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(white2);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 tulipes blanches !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
					}else if(durability == 7){
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(beige);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 tulipes roses !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
					}else if(durability == 8){
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(tulip);
							p.updateInventory();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 oxeye daisy !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
					}
					break;
				default:
					break;
				}
			}
		}else if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fEnd"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case ARROW:
					p.closeInventory();
					core.openVampire(p);
					break;
				case DIAMOND_ORE:
					if (p.getInventory().containsAtLeast(topaze, 26)) {
						p.getInventory().removeItem(topaze26);
						p.getInventory().addItem(diamond);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 minerai de diamant !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
					}
					break;
				case END_ROD:
					if (p.getInventory().containsAtLeast(ambre, 2)) {
						p.getInventory().removeItem(ambre2);
						p.getInventory().addItem(endrode);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 endrode !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				case ENDER_STONE:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(endstone);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 endstones !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case PURPUR_BLOCK:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(purpur);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 blocks violets !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case PURPUR_PILLAR:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(pPillar);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 4 pilliers violets !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case CHORUS_FRUIT_POPPED:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(fruit);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 fruit de chorus !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case CHORUS_FRUIT:
					if (p.getInventory().containsAtLeast(ambre, 1)) {
						p.getInventory().removeItem(ambre);
						p.getInventory().addItem(chorus);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 chorus !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
					}
					break;
				case ENDER_PORTAL_FRAME:
					if (p.getInventory().containsAtLeast(platine, 64)) {
						p.getInventory().removeItem(platine64);
						p.getInventory().addItem(endportal);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 portail de l'end !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de platines !"));
					}
					break;
				default:
					break;
				}
			}
		}
	}

}
