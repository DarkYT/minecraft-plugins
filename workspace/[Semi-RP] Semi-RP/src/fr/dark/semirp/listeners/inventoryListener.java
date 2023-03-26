package fr.dark.semirp.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import fr.dark.semirp.Core;

public class inventoryListener implements Listener {
	
	Core core;
	public inventoryListener(Core core) {this.core = core;}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		Player p = (Player) e.getWhoClicked();
		
		if(inv.getName().equals(core.utils.colorMessage(core.getConfig().getString("SemiRP.Jobs.InventoryName")))) {
			e.setCancelled(true);
			switch(e.getCurrentItem().getType()) {
			case IRON_AXE:
				p.closeInventory();
				Core.sql.createAccount(p, 0, 0, "bucheron");
				break;
			case IRON_PICKAXE:
				p.closeInventory();
				Core.sql.createAccount(p, 0, 0, "mineur");
				break;
			case CARROT_ITEM:
				p.closeInventory();
				Core.sql.createAccount(p, 0, 0, "fermier");
				break;
			case STONE:
				p.closeInventory();
				Core.sql.createAccount(p, 0, 0, "constructeur");
				break;
			case POTION:
				p.closeInventory();
				Core.sql.createAccount(p, 0, 0, "bucheron");
				break;
			case COOKED_BEEF:
				p.closeInventory();
				Core.sql.createAccount(p, 0, 0, "bucheron");
				break;
			case COOKED_FISH:
				p.closeInventory();
				Core.sql.createAccount(p, 0, 0, "bucheron");
				break;
			case IRON_CHESTPLATE:
				p.closeInventory();
				Core.sql.createAccount(p, 0, 0, "bucheron");
				break;
			default:
				break;
			}
		}
	}

}
