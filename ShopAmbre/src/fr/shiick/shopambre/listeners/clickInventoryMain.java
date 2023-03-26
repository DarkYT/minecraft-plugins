package fr.shiick.shopambre.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.shiick.shopambre.Core;

public class clickInventoryMain implements Listener {

	Core core;

	public clickInventoryMain(Core c) {
		core = c;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Shop"))){
			e.setCancelled(true);
			switch (e.getCurrentItem().getType()) {
			case GRASS:
				p.closeInventory();
				core.openBlock(p);
				break;
			case DIRT:
				p.closeInventory();
				core.openBlock2(p);
				break;
			case COBBLESTONE:
				p.closeInventory();
				core.openCailloux(p);
				break;
			case IRON_INGOT:
				p.closeInventory();
				core.openMinerais(p);
				break;
			case CARROT_ITEM:
				p.closeInventory();
				core.openAgriculture(p);
				break;
			case GHAST_TEAR:
				p.closeInventory();
				core.openNether(p);
				break;
			case EGG:
				p.closeInventory();
				Bukkit.dispatchCommand(p, "warp shop");
				break;
			default:
				break;
			}
		}
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
			return;
		}
	}

}
