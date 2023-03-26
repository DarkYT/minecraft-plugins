package fr.shiick.redhub.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.shiick.redhub.RedHub;
import fr.shiick.redhub.utils.Utils;

public class menuEvent implements Listener {

	RedHub redhub;
	Utils utils;

	public menuEvent(RedHub redhub) {
		this.redhub = redhub;
		utils = new Utils(redhub);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		String name = redhub.getConfig().getString("Menu.Name");
		if (e.getInventory().getName().equalsIgnoreCase(utils.colorMessage(name))) {
			for (String item : redhub.getConfig().getConfigurationSection("Menu.Servers").getKeys(false)) {
				Material mat = Material.valueOf(redhub.getConfig().getString("Menu.Servers." + item + ".Item"));
				int data = redhub.getConfig().getInt("Menu.Servers." + item + ".Data");
				String itemName = redhub.getConfig().getString("Menu.Servers." + item + ".Name");
				
				ItemStack itemStack = new ItemStack(mat, 1, (byte) data);
				ItemMeta meta = itemStack.getItemMeta();
				meta.setDisplayName(utils.colorMessage(itemName));
				if (redhub.getConfig().contains("Menu.Servers." + item + ".Lore")) {
					meta.setLore(utils.lore("Menu.Servers." + item + ".Lore"));
				}
				itemStack.setItemMeta(meta);
				if(e.getCurrentItem().getType() == itemStack.getType()) {
					utils.selectAction("Menu.Servers." + item, p);
					p.closeInventory();
				}
				e.setCancelled(true);
			}
		}
	}

}
