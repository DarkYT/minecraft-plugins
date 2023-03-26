package fr.earthsky.shop.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.earthsky.shop.Core;

public class clickEventConfirm {
	
	Core core;

	public clickEventConfirm(Core c) {
		core = c;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		OfflinePlayer pl = p;
		
		ItemStack confirm = new ItemStack(Material.WOOL, 1, (byte) 5);
		ItemMeta confirmM = confirm.getItemMeta();
		confirmM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lConfirmer"));
		confirm.setItemMeta(confirmM);
		
		ItemStack denied = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta deniedM = confirm.getItemMeta();
		deniedM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lAnnuler"));
		denied.setItemMeta(deniedM);
		
		if(e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&8&lAcheter le grade Corrompu"))){
			e.setCancelled(true);
			if(e.getCurrentItem() == confirm){
				core.buyRank(p, "corrompu");
			}
			if(e.getCurrentItem() == denied){
				p.closeInventory();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lVous avez annulé votre achat !"));
			}
		}else if(e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&8&lAcheter le grade Vampire"))){
			e.setCancelled(true);
			if(e.getCurrentItem() == confirm){
				core.buyRank(p, "vampire");
			}
			if(e.getCurrentItem() == denied){
				p.closeInventory();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lVous avez annulé votre achat !"));
			}
			
		}else if(e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&8&lAcheter le grade Démon"))){
			e.setCancelled(true);
			if(e.getCurrentItem() == confirm){
				core.buyRank(p, "demon");
			}
			if(e.getCurrentItem() == denied){
				p.closeInventory();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lVous avez annulé votre achat !"));
			}
			
		}
	}

}
