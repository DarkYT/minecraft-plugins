package fr.dark.patch;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Patch extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e){
		if(e.getInventory().getType().equals(InventoryType.MERCHANT)){
			if(e.getRawSlot() == 2){
				if(e.getCurrentItem().getType().equals(Material.DIAMOND_CHESTPLATE)){
					ItemMeta mt = e.getCurrentItem().getItemMeta();
					mt.setLore(Arrays.asList("§6NonRecyclable"));
					e.getCurrentItem().setItemMeta(mt);
				}
				if(e.getCurrentItem().getType().equals(Material.DIAMOND_PICKAXE)){
					ItemMeta mt = e.getCurrentItem().getItemMeta();
					mt.setLore(Arrays.asList("§6NonRecyclable"));
					e.getCurrentItem().setItemMeta(mt);
				}
				if(e.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)){
					ItemMeta mt = e.getCurrentItem().getItemMeta();
					mt.setLore(Arrays.asList("§6NonRecyclable"));
					e.getCurrentItem().setItemMeta(mt);
				}
				if(e.getCurrentItem().getType().equals(Material.DIAMOND_AXE)){
					ItemMeta mt = e.getCurrentItem().getItemMeta();
					mt.setLore(Arrays.asList("§6NonRecyclable"));
					e.getCurrentItem().setItemMeta(mt);
				}
			}
		}
	}
}
