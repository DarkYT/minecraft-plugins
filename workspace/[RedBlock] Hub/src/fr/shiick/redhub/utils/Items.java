package fr.shiick.redhub.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.shiick.redhub.RedHub;

public class Items {

	RedHub redhub;
	Utils utils;

	public Items(RedHub redhub) {
		this.redhub = redhub;
		utils = new Utils(redhub);
	}

	public ItemStack glass(int color) {
		ItemStack itemstack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) color);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(" ");
		itemstack.setItemMeta(meta);
		return itemstack;
	}

	public void giveItems(Player p) {
		for (String item : redhub.getConfig().getConfigurationSection("Items").getKeys(false)) {
			Material mat = Material.valueOf(redhub.getConfig().getString("Items." + item + ".Item"));
			int data = redhub.getConfig().getInt("Items." + item + ".Data");
			String itemName = redhub.getConfig().getString("Items." + item + ".Name");
			
			ItemStack itemStack = new ItemStack(mat, 1, (byte) data);
			ItemMeta meta = itemStack.getItemMeta();
			meta.setDisplayName(utils.colorMessage(itemName));
			if (redhub.getConfig().contains("Items." + item + ".Lore")) {
				meta.setLore(utils.lore("Items." + item + ".Lore"));
			}
			itemStack.setItemMeta(meta);
			
			int position = redhub.getConfig().getInt("Items." + item + ".Position") - 1;
			p.getInventory().setItem(position, itemStack);
		}
	}

}
