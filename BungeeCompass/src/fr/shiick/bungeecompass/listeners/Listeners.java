package fr.shiick.bungeecompass.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.shiick.bungeecompass.Core;

public class Listeners implements Listener {

	Core core;
	
	public Listeners(Core c) {
		core = c;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		Location spawn = new Location(core.getServer().getWorld("world"), -181, 47, -171);
		
		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		ItemMeta meta1 = compass.getItemMeta();
		meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lTéléporteur"));
		compass.setItemMeta(meta1);
		
		ItemStack star = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta meta2 = star.getItemMeta();
		meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2&lBoutique"));
		star.setItemMeta(meta2);
		
		p.setHealth(20);
		p.setFoodLevel(20);
		p.teleport(spawn);
		p.getInventory().clear();
		p.getInventory().setItem(4, compass);
		p.getInventory().setItem(8, star);
		p.updateInventory();
		Bukkit.dispatchCommand(p, "actualisationscoreboard");
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6&lTéléporteur"))){
			if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				core.MenuMain(p);
			}
		} else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&2&lBoutique"))){
			if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLa boutique est temporairement indisponible."));
			}
		}
	}
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		Player p = e.getPlayer();
		if(!(p.hasPermission("staffchat.mod"))){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e){
		Player p = e.getPlayer();
		if(!(p.hasPermission("staffchat.mod"))){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if (!(e.getEntity() instanceof Player)){
			return;
		} else {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6&lTéléporteur"))){
			e.setCancelled(true);
			switch (e.getCurrentItem().getType()) {
			case GRASS:
				p.closeInventory();
				core.teleportServer(p, "skyblock");
				break;
			case MYCEL:
				p.closeInventory();
				core.teleportServer(p, "skyblock2");
				break;
			case DIAMOND_SWORD:
				p.closeInventory();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLe skywars est actuellement indisponible !"));
				break;
			case WATER_BUCKET:
				p.closeInventory();
				core.teleportServer(p, "waterblock");
				break;
			case BRICK:
				p.closeInventory();
				core.teleportServer(p, "sbbuild");
				break;
			case FEATHER:
				p.closeInventory();
				Bukkit.dispatchCommand(p, "warp jump");
				break;
			case BED:
				p.closeInventory();
				Bukkit.dispatchCommand(p, "spawn");
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
