package fr.stellaria.hdv.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ScrollerInventory {

	public ArrayList<Inventory> pages = new ArrayList<Inventory>();
	public UUID id;
	public int currpage = 0;
	public static HashMap<UUID, ScrollerInventory> users = new HashMap<UUID, ScrollerInventory>();

	// Running this will open a paged inventory for the specified player, with
	// the items in the arraylist specified.
	public ScrollerInventory(ArrayList<ItemStack> items, String name, Player p) {
		this.id = UUID.randomUUID();
		// create new blank page
		Inventory page = getBlankPage(name + " (Page " + (pages.size() + 1) + ")");
		// According to the items in the arraylist, add items to the
		// ScrollerInventory
		for (int i = 0; i < items.size(); i++) {
			// If the current page is full, add the page to the inventory's
			// pages arraylist, and create a new page to add the items.
			if (page.firstEmpty() == 46) {
				pages.add(page);
				page = getBlankPage(name + " (Page " + (pages.size() + 1) + ")");
				if(items.get(i) != null){
					page.addItem(items.get(i));
				}
			} else {
				// Add the item to the current page as per normal
				if(items.get(i) != null){
					page.addItem(items.get(i));
				}
				
			}
		}
		pages.add(page);
		// open page 0 for the specified player
		p.openInventory(pages.get(currpage));
		users.put(p.getUniqueId(), this);
	}

	public static final String nextPageName = ChatColor.WHITE + "Page Suivante";
	public static final String previousPageName = ChatColor.WHITE + "Page Précédente";

	// This creates a blank page with the next and prev buttons
	public Inventory getBlankPage(String name) {
		Inventory page = Bukkit.createInventory(null, 54, name);

		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(nextPageName);
		arrowM.setLore(Arrays.asList(""));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);

		ItemStack arrowP = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowPM = arrowP.getItemMeta();
		arrowPM.setDisplayName(previousPageName);
		arrowM.setLore(Arrays.asList("",""));
		arrowPM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowPM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrowP.setItemMeta(arrowPM);

		page.setItem(53, arrow);
		page.setItem(45, arrowP);
		return page;
	}
}
