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

public class clickInventoryDemon implements Listener {

	Core core;
	public clickInventoryDemon(Core c) {
		core = c;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e){
		
		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta topazem = topaze.getItemMeta();
		topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazem.addEnchant(Enchantment.DURABILITY, 1, true);
		topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazem);
		
		ItemStack topaze8 = new ItemStack(Material.DOUBLE_PLANT, 8);
		ItemMeta topaze8m = topaze8.getItemMeta();
		topaze8m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze8m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze8m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze8.setItemMeta(topaze8m);
		
		ItemStack topaze16 = new ItemStack(Material.DOUBLE_PLANT, 16);
		ItemMeta topaze16m = topaze16.getItemMeta();
		topaze16m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze16m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze16m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze16.setItemMeta(topaze16m);
		
		ItemStack topaze24 = new ItemStack(Material.DOUBLE_PLANT, 24);
		ItemMeta topaze24m = topaze24.getItemMeta();
		topaze24m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze24m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze24m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze24.setItemMeta(topaze24m);
		
		ItemStack coal = new ItemStack(Material.COAL_ORE, 1);
		ItemStack iron = new ItemStack(Material.IRON_ORE, 1);
		ItemStack gold = new ItemStack(Material.GOLD_ORE, 1);
		ItemStack lapis = new ItemStack(Material.LAPIS_ORE, 1);
		ItemStack redstone = new ItemStack(Material.REDSTONE_ORE, 1);
		ItemStack emerald = new ItemStack(Material.EMERALD_ORE, 1);
		
		ItemStack diamond = new ItemStack(Material.DIAMOND_ORE, 1);
			
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fDémon"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case COAL_ORE:
					p.closeInventory();
					core.openOres(p);
					break;
				case DIAMOND_ORE:
					p.closeInventory();
					core.openRares(p);
					break;
				case ARROW:
					p.closeInventory();
					core.openWarps(p);
					break;
				default:
					break;
				}
			}
		}else if(e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fMinerais Démon"))){
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case COAL_ORE:
					if (p.getInventory().containsAtLeast(topaze, 8)) {
						p.getInventory().removeItem(topaze8);
						p.getInventory().addItem(coal);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 minerai de charbon !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
					}
					break;
				case IRON_ORE:
					if (p.getInventory().containsAtLeast(topaze, 8)) {
						p.getInventory().removeItem(topaze8);
						p.getInventory().addItem(iron);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 minerai de fer !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
					}
					break;
				case GOLD_ORE:
					if (p.getInventory().containsAtLeast(topaze, 16)) {
						p.getInventory().removeItem(topaze16);
						p.getInventory().addItem(gold);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 minerai d'or !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
					}
					break;
				case LAPIS_ORE:
					if (p.getInventory().containsAtLeast(topaze, 16)) {
						p.getInventory().removeItem(topaze16);
						p.getInventory().addItem(lapis);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 minerai de lapis lazuli !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
					}
					break;
				case REDSTONE_ORE:
					if (p.getInventory().containsAtLeast(topaze, 16)) {
						p.getInventory().removeItem(topaze16);
						p.getInventory().addItem(redstone);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 minerai de redstone !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
					}
					break;
				case EMERALD_ORE:
					if (p.getInventory().containsAtLeast(topaze, 16)) {
						p.getInventory().removeItem(topaze16);
						p.getInventory().addItem(emerald);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 minerai d'émeraude !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
					}
					break;
				case ARROW:
					p.closeInventory();
					core.openDemon(p);
					break;
				default:
					break;
				}
			}
		}else if(e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fRare"))){
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case DIAMOND_ORE:
					if(p.getInventory().containsAtLeast(topaze, 24)){
						p.getInventory().removeItem(topaze24);
						p.getInventory().addItem(diamond);
						p.updateInventory();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 minerai de diamant !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
					}
					break;
				case ARROW:
					p.closeInventory();
					core.openDemon(p);
					break;
				default:
					break;
				}
			}
		}
	}
		
}
