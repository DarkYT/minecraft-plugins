package fr.shiick.warpgui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.shiick.warpgui.listeners.clickInventory;
import net.md_5.bungee.api.ChatColor;

public class WarpGUI extends JavaPlugin {
	
	@Override
	public void onEnable(){
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new clickInventory(this), this);
	}
	
	public void openWarp(Player p){ 
		Inventory warp = Bukkit.createInventory(null, 36, ChatColor.translateAlternateColorCodes('&', "&9Warps"));
		
		ItemStack corrompu = new ItemStack(Material.IRON_HELMET);
		ItemMeta corrompuM = corrompu.getItemMeta();
		corrompuM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8Zone Corrompu"));
		corrompu.setItemMeta(corrompuM);
		
		ItemStack vampire = new ItemStack(Material.GOLD_HELMET);
		ItemMeta vampireM = vampire.getItemMeta();
		vampireM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eZone Vampire"));
		vampire.setItemMeta(vampireM);
		
		ItemStack demon = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta demonM = demon.getItemMeta();
		demonM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Zone Démon"));
		demon.setItemMeta(demonM);
		
		ItemStack jump = new ItemStack(Material.FEATHER);
		ItemMeta jumpM = jump.getItemMeta();
		jumpM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aJump"));
		jump.setItemMeta(jumpM);
		
		ItemStack laby = new ItemStack(Material.LEAVES, 1, (byte) 1);
		ItemMeta labyM = laby.getItemMeta();
		labyM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2Labyrinthe"));
		laby.setItemMeta(labyM);
		
		ItemStack farm = new ItemStack(Material.RAW_BEEF);
		ItemMeta farmM = farm.getItemMeta();
		farmM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&3Zone Farm"));
		farm.setItemMeta(farmM);
		
		ItemStack nether = new ItemStack(Material.NETHERRACK);
		ItemMeta netherM = nether.getItemMeta();
		netherM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Nether"));
		nether.setItemMeta(netherM);
		
		ItemStack shop = new ItemStack(Material.CHEST);
		ItemMeta shopM = shop.getItemMeta();
		shopM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cShop"));
		shop.setItemMeta(shopM);
		
		ItemStack generator = new ItemStack(Material.COBBLESTONE);
		ItemMeta generatorM = generator.getItemMeta();
		generatorM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Générateur à cobble"));
		generator.setItemMeta(generatorM);
		
		ItemStack vaudou = new ItemStack(Material.ENDER_CHEST);
		ItemMeta vaudouM = vaudou.getItemMeta();
		vaudouM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Caisse vaudou"));
		vaudou.setItemMeta(vaudouM);
		
		ItemStack staff = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		ItemMeta staffM = staff.getItemMeta();
		staffM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&1Salle du Staff"));
		staff.setItemMeta(staffM);
		
		ItemStack legendaire = new ItemStack(Material.NETHER_STAR);
		ItemMeta legendaireM = legendaire.getItemMeta();
		legendaireM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fCaisse légendaire"));
		legendaire.setItemMeta(legendaireM);
		
		warp.setItem(2, corrompu);
		warp.setItem(4, vampire);
		warp.setItem(6, demon);
		warp.setItem(11, jump);
		warp.setItem(13, laby);
		warp.setItem(15, farm);
		warp.setItem(20, nether);
		warp.setItem(22, shop);
		warp.setItem(24, generator);
		warp.setItem(29, vaudou);
		warp.setItem(31, staff);
		warp.setItem(33, legendaire);
		
		p.openInventory(warp);
	}

}
