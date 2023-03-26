package fr.dark.sellstick.events;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;

import fr.dark.sellstick.Core;
import fr.dark.sellstick.SellStick;

public class clickListener implements Listener {

	Core core;
	public clickListener(Core core) {this.core = core;}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		
		if(e.getItem() == null) {
			return;
		}
		
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getClickedBlock().getType().equals(Material.CHEST) || e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)) {
				Chest chest = (Chest) e.getClickedBlock().getState();
				if(e.getItem().getType().equals(Material.valueOf(core.getConfig().getString("SellStick.Item.Type")))) {
					if(e.getItem().getItemMeta().hasEnchants()) {
						if(e.getItem().getItemMeta().getDisplayName().equals(core.utils.colorMessage(core.getConfig().getString("SellStick.Item.Name")))) {
							SellStick stick = new SellStick(core, e.getPlayer());
							
							Island island = null;
                            island = ASkyBlockAPI.getInstance().getIslandAt(e.getPlayer().getLocation());
							
							if(island.getMembers().contains(e.getPlayer().getUniqueId())) {
								if(stick.getDura(e.getItem()) == 1) {
									int price = core.sellPrice(chest, e.getPlayer());
									List<ItemStack> items = core.sellAll(chest, e.getPlayer());
									if(price == 0) {
										core.sendActionBar(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.EmptyChest")), e.getPlayer());
										e.setCancelled(true);
										return;
									}
									Inventory inventory = chest.getInventory();
									for(ItemStack it : items) {
										for (int i = 0; i < inventory.getSize(); i++) {
										    ItemStack item = inventory.getItem(i);
										    if (item != null && item.getType() == it.getType() && item.getData().getData() == it.getData().getData()) {
										        ItemStack newItem = null;
										        
										        inventory.setItem(i, newItem);
										    }
										}
									}
									String message = core.utils.modifyMessage(core.getConfig().getString("SellStick.Messages.SellAll"), "<price>", ""+price);
									core.sendActionBar(message, e.getPlayer());
									stick.deleteStick(e.getPlayer().getInventory().getHeldItemSlot(), e.getPlayer());								
									e.setCancelled(true);
									e.getPlayer().sendMessage(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.UsagesNull")));
									return;
								}
								
								if(stick.getDura(e.getItem()) == -1) {
									int price = core.sellPrice(chest, e.getPlayer());
									List<ItemStack> items = core.sellAll(chest, e.getPlayer());
									if(price == 0) {
										core.sendActionBar(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.EmptyChest")), e.getPlayer());
										e.setCancelled(true);
										return;
									}
									Inventory inventory = chest.getInventory();
									for(ItemStack it : items) {
										for (int i = 0; i < inventory.getSize(); i++) {
										    ItemStack item = inventory.getItem(i);
										    if (item != null && item.getType() == it.getType() && item.getData().getData() == it.getData().getData()) {
										        ItemStack newItem = null;
										        
										        inventory.setItem(i, newItem);
										    }
										}
									}
									String message = core.utils.modifyMessage(core.getConfig().getString("SellStick.Messages.SellAll"), "<price>", ""+price);
									core.sendActionBar(message, e.getPlayer());
									e.setCancelled(true);
									return;
								}
								int price = core.sellPrice(chest, e.getPlayer());
								List<ItemStack> items = core.sellAll(chest, e.getPlayer());
								if(price == 0) {
									core.sendActionBar(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.EmptyChest")), e.getPlayer());
									e.setCancelled(true);
									return;
								}
								stick.reloadStick(e.getPlayer(), e.getPlayer().getInventory().getHeldItemSlot(), e.getItem());
								
								
								Inventory inventory = chest.getInventory();
								for(ItemStack it : items) {
									for (int i = 0; i < inventory.getSize(); i++) {
									    ItemStack item = inventory.getItem(i);
									    if (item != null && item.getType() == it.getType() && item.getData().getData() == it.getData().getData()) {
									        ItemStack newItem = null;
									        
									        inventory.setItem(i, newItem);
									    }
									}
								}
								String message = core.utils.modifyMessage(core.getConfig().getString("SellStick.Messages.SellAll"), "<price>", ""+price);
								core.sendActionBar(message, e.getPlayer());
								e.setCancelled(true);
							}else {
								e.getPlayer().sendMessage(core.utils.colorMessage(core.getConfig().getString("SellStick.Messages.wrongIsland")));
								e.setCancelled(true);
								return;
							}
							
						}else {
							return;
						}
					}else {
						return;
					}
				}else {
					return;
				}
			}else {
				return;
			}
		}else {
			return;
		}
	}

}
