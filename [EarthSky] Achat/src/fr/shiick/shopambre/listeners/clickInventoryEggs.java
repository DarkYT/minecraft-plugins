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

public class clickInventoryEggs implements Listener{

	Core core;
	
	public clickInventoryEggs(Core c) {
		core = c;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent e){
		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta topazem = topaze.getItemMeta();
		topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazem.addEnchant(Enchantment.DURABILITY, 1, true);
		topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazem); 
		
		ItemStack topaze2 = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta topaze2m = topaze2.getItemMeta();
		topaze2m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topaze2m.addEnchant(Enchantment.DURABILITY, 1, true);
		topaze2m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze2.setItemMeta(topaze2m); 
		
		ItemStack pig = new ItemStack(Material.MONSTER_EGG, 1, (byte) 90);
		ItemStack cow = new ItemStack(Material.MONSTER_EGG, 1, (byte) 92);
		ItemStack sheep = new ItemStack(Material.MONSTER_EGG, 1, (byte) 91);
		ItemStack chicken = new ItemStack(Material.MONSTER_EGG, 1, (byte) 93);
		ItemStack wolf = new ItemStack(Material.MONSTER_EGG, 1, (byte) 95);
		ItemStack rabbit = new ItemStack(Material.MONSTER_EGG, 1, (byte) 101);
		ItemStack mushroom = new ItemStack(Material.MONSTER_EGG, 1, (byte) 96);
		ItemStack horse = new ItemStack(Material.MONSTER_EGG, 1, (byte) 100);
		
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fOeufs"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				if(e.getCurrentItem().getType() == Material.MONSTER_EGG){
					switch(e.getCurrentItem().getData().getData()) {
						case (byte)90:
							if (p.getInventory().containsAtLeast(topaze, 1)) {
								p.getInventory().removeItem(topaze);
								p.getInventory().addItem(pig);
								p.updateInventory();
								p.sendMessage(
										ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter un oeuf de cochon !"));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de topaze !"));
							}
							break;
						case (byte)92:
							if (p.getInventory().containsAtLeast(topaze, 1)) {
								p.getInventory().removeItem(topaze);
								p.getInventory().addItem(cow);
								p.updateInventory();
								p.sendMessage(
										ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter un oeuf de vache !"));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de topaze !"));
							}
							break;
						case (byte)91:
							if (p.getInventory().containsAtLeast(topaze, 1)) {
								p.getInventory().removeItem(topaze);
								p.getInventory().addItem(sheep);
								p.updateInventory();
								p.sendMessage(
										ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter un oeuf de mouton !"));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de topaze !"));
							}
							break;
						case (byte)93:
							if (p.getInventory().containsAtLeast(topaze, 1)) {
								p.getInventory().removeItem(topaze);
								p.getInventory().addItem(chicken);
								p.updateInventory();
								p.sendMessage(
										ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter un oeuf de poulet !"));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de topaze !"));
							}
							break;
						case (byte)95:
							if (p.getInventory().containsAtLeast(topaze, 1)) {
								p.getInventory().removeItem(topaze);
								p.getInventory().addItem(wolf);
								p.updateInventory();
								p.sendMessage(
										ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter un oeuf de loup !"));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de topaze !"));
							}
							break;
						case (byte)101:
							if (p.getInventory().containsAtLeast(topaze, 1)) {
								p.getInventory().removeItem(topaze);
								p.getInventory().addItem(rabbit);
								p.updateInventory();
								p.sendMessage(
										ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter un oeuf de lapin !"));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas de topaze !"));
							}
							break;
						case (byte)96:
							if (p.getInventory().containsAtLeast(topaze, 2)) {
								p.getInventory().removeItem(topaze2);
								p.getInventory().addItem(mushroom);
								p.updateInventory();
								p.sendMessage(
										ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter un oeuf de champimeuh !"));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
							}
							break;
						case (byte)100:
							if (p.getInventory().containsAtLeast(topaze, 2)) {
								p.getInventory().removeItem(topaze2);
								p.getInventory().addItem(horse);
								p.updateInventory();
								p.sendMessage(
										ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter un oeuf de cheval !"));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de topaze !"));
							}
							break;
						default:
							break;
				}

			}else if(e.getCurrentItem().getType() == Material.ARROW){
				p.closeInventory();
				core.openMain(p);
			}
		}
	}
	}
}
