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

public class clickInventoryJardinier implements Listener {
	
	Core core;

	public clickInventoryJardinier(Core c) {
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
		
		ItemStack ambre4 = new ItemStack(Material.DOUBLE_PLANT, 4);
		ItemMeta ambre4m = ambre4.getItemMeta();
		ambre4m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre4.setItemMeta(ambre4m);
		
		ItemStack ambre8 = new ItemStack(Material.DOUBLE_PLANT, 8);
		ItemMeta ambre8m = ambre8.getItemMeta();
		ambre8m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre8.setItemMeta(ambre8m);
		
		ItemStack ambre16 = new ItemStack(Material.DOUBLE_PLANT, 16);
		ItemMeta ambre16m = ambre16.getItemMeta();
		ambre16m.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre16.setItemMeta(ambre16m);
		
		ItemStack lava = new ItemStack(Material.LAVA_BUCKET, 1);
		ItemStack oak = new ItemStack(Material.SAPLING, 1);
		ItemStack spruce = new ItemStack(Material.SAPLING, 1,(byte)1);
		ItemStack birch = new ItemStack(Material.SAPLING, 1,(byte)2);
		ItemStack jungle = new ItemStack(Material.SAPLING, 1,(byte)3);
		ItemStack acacia = new ItemStack(Material.SAPLING, 1,(byte)4);
		ItemStack darkoak = new ItemStack(Material.SAPLING, 1,(byte)5);
		ItemStack meal = new ItemStack(Material.INK_SACK, 64,(byte)15);
		

		
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop &fJardinier"))) {
			e.setCancelled(true);
			if (!(p.getInventory().firstEmpty() == -1)) {
				switch (e.getCurrentItem().getType()) {
				case LAVA_BUCKET:
					if (p.getInventory().containsAtLeast(ambre, 2)) {
						p.getInventory().removeItem(ambre2);
						p.getInventory().addItem(lava);
						p.updateInventory();
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 seau de lave !"));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
					}
					break;
				case SAPLING:
					short durability = e.getCurrentItem().getDurability();
					if(durability == 0){
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(oak);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 pousse d'oak !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
					}else if(durability == 1){
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(spruce);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 pousse de spruce !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
					}else if(durability == 2){
						if (p.getInventory().containsAtLeast(ambre, 2)) {
							p.getInventory().removeItem(ambre2);
							p.getInventory().addItem(birch);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 pousse de birch !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
						}
					}else if(durability == 3){
						if (p.getInventory().containsAtLeast(ambre, 4)) {
							p.getInventory().removeItem(ambre4);
							p.getInventory().addItem(jungle);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 pousse de jungle !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
						}
					}else if(durability == 4){
						if (p.getInventory().containsAtLeast(ambre, 8)) {
							p.getInventory().removeItem(ambre8);
							p.getInventory().addItem(acacia);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 pousse d'acacia !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
						}
					}else if(durability == 5){
						if (p.getInventory().containsAtLeast(ambre, 16)) {
							p.getInventory().removeItem(ambre16);
							p.getInventory().addItem(darkoak);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 pousse de dark oak !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez d'ambre !"));
						}
					}
					break;
				case INK_SACK:
					short durability1 = e.getCurrentItem().getDurability();
					if(durability1 == 15){
						if (p.getInventory().containsAtLeast(ambre, 1)) {
							p.getInventory().removeItem(ambre);
							p.getInventory().addItem(meal);
							p.updateInventory();
							p.sendMessage(
									ChatColor.translateAlternateColorCodes('&', "&aVous venez d'acheter 1 stack de bone meal !"));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'ambre !"));
						}
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
