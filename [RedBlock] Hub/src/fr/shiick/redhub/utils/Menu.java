package fr.shiick.redhub.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.shiick.redhub.RedHub;

public class Menu {

	Utils utils;
	Items items;
	RedHub redhub;

	public Menu(RedHub redhub) {
		this.redhub = redhub;
		utils = new Utils(this.redhub);
		items = new Items(this.redhub);
	}

	public void openServers(Player p) {
		int size = redhub.getConfig().getInt("Menu.Size");
		String name = redhub.getConfig().getString("Menu.Name");
		Inventory inv = Bukkit.createInventory(null, size, utils.colorMessage(name));

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
			
			int position = redhub.getConfig().getInt("Menu.Servers." + item + ".Position") - 1;
			inv.setItem(position, itemStack);
		}


		for (int i = 0; i < size; i++) {
			if (inv.getItem(i) == null) {
				if (!(i % 2 == 0)) {
					int data = redhub.getConfig().getInt("Menu.Even");
					inv.setItem(i, items.glass(data));
				} else {
					int data = redhub.getConfig().getInt("Menu.Odd");
					inv.setItem(i, items.glass(data));
				}
			}
		}

		p.openInventory(inv);
	}

}
