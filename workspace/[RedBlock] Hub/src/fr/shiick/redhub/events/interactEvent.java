package fr.shiick.redhub.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.shiick.redhub.RedHub;
import fr.shiick.redhub.utils.Menu;
import fr.shiick.redhub.utils.Utils;

public class interactEvent implements Listener {

	RedHub redhub;
	Menu menu;
	Utils utils;

	public interactEvent(RedHub redhub) {
		this.redhub = redhub;
		menu = new Menu(redhub);
		utils = new Utils(redhub);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getItem() != null) {
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

					if (e.getItem().getType() == itemStack.getType()) {
						selectItem("Items." + item, p);
					}
				}
			}
		}
	}
	
	public void selectItem(String path, Player p) {
		String type = redhub.getConfig().getString(path + ".Type");
		if (type.equalsIgnoreCase("MENU")) {
			String menuType = redhub.getConfig().getString(path + ".Menu");
			if (menuType.equalsIgnoreCase("SERVERS")) {
				menu.openServers(p);
			} else if (menuType.equalsIgnoreCase("SHOP")) {
				redhub.shop.menu.openMain(p);
			}
		} else if (type.equalsIgnoreCase("MESSAGE")) {
			String message = redhub.getConfig().getString(path + ".Message");
			p.sendMessage(utils.colorMessage(message));
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Material menuMat = Material.valueOf(redhub.getConfig().getString("Items.Menu.Item"));
		if (e.getBlockPlaced().getType() == menuMat) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (!(e.getPlayer().isOp())) {
			e.setCancelled(true);
		}
	}

}
