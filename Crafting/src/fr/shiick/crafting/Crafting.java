package fr.shiick.crafting;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.shiick.crafting.commands.craft;
import fr.shiick.crafting.listeners.inventoryCraft;
import fr.shiick.crafting.listeners.inventoryCraft2;
import fr.shiick.crafting.listeners.inventoryCraft3;
import fr.shiick.crafting.listeners.inventoryCraft4;

public class Crafting extends JavaPlugin implements Listener {

	private ShapelessRecipe plantRecipe;
	private ShapelessRecipe plantRecipe2;
	private ShapelessRecipe plantRecipe3;

	@Override
	public void onEnable() {
		getCommand("crafting").setExecutor(new craft(this));
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new inventoryCraft(this), this);
		pm.registerEvents(new inventoryCraft2(this), this);
		pm.registerEvents(new inventoryCraft3(this), this);
		pm.registerEvents(new inventoryCraft4(this), this);
		pm.registerEvents(this, this);
		

		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta ambrem = ambre.getItemMeta();
		ambrem.setDisplayName("§6§lAmbre");
		ambre.setItemMeta(ambrem);

		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta topazem = topaze.getItemMeta();
		topazem.setDisplayName("§c§lTopaze");
		topazem.addEnchant(Enchantment.DURABILITY, 1, true);
		topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazem);

		ItemStack platine = new ItemStack(Material.NETHER_STAR);
		ItemMeta platinem = platine.getItemMeta();
		platinem.setDisplayName("§4§lPlatine");
		platine.setItemMeta(platinem);

		this.plantRecipe = new ShapelessRecipe(new ItemStack(Material.DOUBLE_PLANT, 1));
		this.plantRecipe.addIngredient(9, Material.DOUBLE_PLANT);

		Bukkit.addRecipe(this.plantRecipe);

		this.plantRecipe2 = new ShapelessRecipe(new ItemStack(Material.DOUBLE_PLANT, 1));
		this.plantRecipe2.addIngredient(2, Material.DOUBLE_PLANT);

		Bukkit.addRecipe(this.plantRecipe2);

		this.plantRecipe3 = new ShapelessRecipe(new ItemStack(Material.DOUBLE_PLANT, 9));
		this.plantRecipe3.addIngredient(1, Material.NETHER_STAR);

		Bukkit.addRecipe(this.plantRecipe3);
	}

	@EventHandler
	public void onPrepareCraft(PrepareItemCraftEvent e) {
		if (!(e.getRecipe() instanceof ShapelessRecipe)) {
			return;
		}
		ShapelessRecipe recipe = (ShapelessRecipe) e.getInventory().getRecipe();

		if (recipeEquals(recipe, this.plantRecipe)) {
			ItemStack plant = null;

			int ambren = 9;
			int topazen = 9;

			for (int i = 0; i < e.getInventory().getMatrix().length; i++) {
				if ((e.getInventory().getMatrix()[i] != null) && (e.getInventory().getMatrix()[i].hasItemMeta())) {
					plant = e.getInventory().getMatrix()[i];

					if (plant.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAmbre")) {
						ambren--;
					}

					if (plant.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lTopaze")) {
						topazen--;
					}
				}
			}

			ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT);
			ItemMeta topazem = topaze.getItemMeta();
			topazem.setDisplayName("§c§lTopaze");
			topazem.addEnchant(Enchantment.DURABILITY, 1, true);
			topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
			topaze.setItemMeta(topazem);

			ItemStack platine = new ItemStack(Material.NETHER_STAR);
			ItemMeta platinem = platine.getItemMeta();
			platinem.setDisplayName("§4§lPlatine");
			platine.setItemMeta(platinem);

			if (plant == null) {
				e.getInventory().setResult(null);
				return;
			}

			if (!plant.getItemMeta().hasDisplayName()) {
				e.getInventory().setResult(null);
				return;
			}

			if (ambren == 0) {
				e.getInventory().setResult(topaze);
				return;
			}
			if (topazen == 0) {
				e.getInventory().setResult(platine);
				return;
			}
			e.getInventory().setResult(null);
			return;
		}

		if (recipeEquals(recipe, this.plantRecipe2)) {
			ItemStack plant = null;

			int topazen = 2;

			for (int i = 0; i < e.getInventory().getMatrix().length; i++) {
				if ((e.getInventory().getMatrix()[i] != null) && (e.getInventory().getMatrix()[i].hasItemMeta())) {
					plant = e.getInventory().getMatrix()[i];

					if (plant.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lTopaze")) {
						topazen--;
					}
				}
			}

			ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT, 18);
			ItemMeta ambrem = ambre.getItemMeta();
			ambrem.setDisplayName("§6§lAmbre");
			ambre.setItemMeta(ambrem);

			if (plant == null) {
				e.getInventory().setResult(null);
				return;
			}
			if (!plant.getItemMeta().hasDisplayName()) {
				e.getInventory().setResult(null);
				return;
			}

			if (topazen == 0)
				e.getInventory().setResult(ambre);
			else
				e.getInventory().setResult(null);
		} else if (recipeEquals(recipe, this.plantRecipe3)) {
			ItemStack plant = null;

			ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT, 9);
			ItemMeta topazem = topaze.getItemMeta();
			topazem.setDisplayName("§c§lTopaze");
			topazem.addEnchant(Enchantment.DURABILITY, 1, true);
			topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
			topaze.setItemMeta(topazem);

			for (int i = 0; i < e.getInventory().getMatrix().length; i++)
				if ((e.getInventory().getMatrix()[i] != null) && (e.getInventory().getMatrix()[i].hasItemMeta())) {
					plant = e.getInventory().getMatrix()[i];

					if (plant.getItemMeta().getDisplayName().equalsIgnoreCase("§4§lPlatine"))
						e.getInventory().setResult(topaze);
				}
		}
	}

	private boolean recipeEquals(Recipe recipe, Recipe other) {
		if (recipe.equals(other)) {
			return true;
		}
		if (((recipe instanceof ShapelessRecipe)) && ((other instanceof ShapelessRecipe))) {
			ShapelessRecipe sr = (ShapelessRecipe) recipe;
			ShapelessRecipe sr2 = (ShapelessRecipe) other;

			return (sr.getIngredientList().equals(sr2.getIngredientList())) && (sr.getResult().equals(sr2.getResult()));
		}
		if (((recipe instanceof ShapedRecipe)) && ((other instanceof ShapedRecipe))) {
			ShapedRecipe sr = (ShapedRecipe) recipe;
			ShapedRecipe sr2 = (ShapedRecipe) other;

			return (sr.getIngredientMap().equals(sr2.getIngredientMap())) && (sr.getResult().equals(sr2.getResult())) && (Arrays.equals(sr.getShape(), sr2.getShape()));
		}
		if (((recipe instanceof FurnaceRecipe)) && ((other instanceof FurnaceRecipe))) {
			FurnaceRecipe fr = (FurnaceRecipe) recipe;
			FurnaceRecipe fr2 = (FurnaceRecipe) other;

			return (fr.getExperience() == fr2.getExperience()) && (fr.getInput().equals(fr2.getInput())) && (fr.getResult().equals(fr2.getResult()));
		}
		if (((recipe instanceof MerchantRecipe)) && ((other instanceof MerchantRecipe))) {
			MerchantRecipe mr = (MerchantRecipe) recipe;
			MerchantRecipe mr2 = (MerchantRecipe) other;

			return (mr.getIngredients().equals(mr2.getIngredients())) && (mr.getMaxUses() == mr2.getMaxUses()) && (mr.getUses() == mr2.getUses()) && (mr.getResult().equals(mr2.getResult()));
		}

		return recipe.getResult().equals(other.getResult());
	}
	
	public void openCraft(Player p){
		Inventory craft = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCraft Topaze"));
		
		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta ambreM = ambre.getItemMeta();
		ambreM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambreM);
		
		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta topazeM = topaze.getItemMeta();
		topazeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazeM.addEnchant(Enchantment.DURABILITY, 1, true);
		topazeM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazeM);
		
		ItemStack next = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta nextM = next.getItemMeta();
		nextM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lSuivant"));
		next.setItemMeta(nextM);
		
		craft.setItem(10, ambre);
		craft.setItem(11, ambre);
		craft.setItem(12, ambre);
		craft.setItem(19, ambre);
		craft.setItem(20, ambre);
		craft.setItem(21, ambre);
		craft.setItem(28, ambre);
		craft.setItem(29, ambre);
		craft.setItem(30, ambre);
		craft.setItem(24, topaze);
		craft.setItem(44, next);
		
		for (int i = 0; i < 45; i++) {
			if (craft.getItem(i) == null) {
				ItemStack wall = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte)15);
				ItemMeta meta7 = wall.getItemMeta();
				meta7.setDisplayName(" ");
				wall.setItemMeta(meta7);
				craft.setItem(i, wall);
			}
		}
		
		p.openInventory(craft);
	}
	
	public void openCraft2(Player p){
		Inventory craft2 = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&6&lCraft Ambre"));
		
		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT, 18);
		ItemMeta ambreM = ambre.getItemMeta();
		ambreM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambreM);
		
		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta topazeM = topaze.getItemMeta();
		topazeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazeM.addEnchant(Enchantment.DURABILITY, 1, true);
		topazeM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazeM);
		
		ItemStack next = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta nextM = next.getItemMeta();
		nextM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lSuivant"));
		next.setItemMeta(nextM);
		
		ItemStack previous = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta previousM = previous.getItemMeta();
		previousM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lPrécédent"));
		previous.setItemMeta(previousM);
		
		ItemStack air = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta airM = air.getItemMeta();
		airM.setDisplayName(" ");
		air.setItemMeta(airM);
		
		craft2.setItem(10, air);
		craft2.setItem(11, air);
		craft2.setItem(12, air);
		craft2.setItem(19, air);
		craft2.setItem(28, air);
		craft2.setItem(29, air);
		craft2.setItem(30, air);
		craft2.setItem(20, topaze);
		craft2.setItem(21, topaze);
		craft2.setItem(24, ambre);
		craft2.setItem(36, previous);
		craft2.setItem(44, next);
		
		
		for (int i = 0; i < 45; i++) {
			if (craft2.getItem(i) == null) {
				ItemStack wall = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte)15);
				ItemMeta meta = wall.getItemMeta();
				meta.setDisplayName(" ");
				wall.setItemMeta(meta);
				craft2.setItem(i, wall);
			}
		}
		
		p.openInventory(craft2);
	}
	
	public void openCraft3(Player p){
		Inventory craft3 = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&4&lCraft Platine"));
		
		ItemStack platine = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta platineM = platine.getItemMeta();
		platineM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
		platine.setItemMeta(platineM);
		
		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta topazeM = topaze.getItemMeta();
		topazeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazeM.addEnchant(Enchantment.DURABILITY, 1, true);
		topazeM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazeM);
		
		ItemStack next = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta nextM = next.getItemMeta();
		nextM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lSuivant"));
		next.setItemMeta(nextM);
		
		ItemStack previous = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta previousM = previous.getItemMeta();
		previousM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lPrécédent"));
		previous.setItemMeta(previousM);
		
		ItemStack air = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta airM = air.getItemMeta();
		airM.setDisplayName(" ");
		air.setItemMeta(airM);
		
		craft3.setItem(10, topaze);
		craft3.setItem(11, topaze);
		craft3.setItem(12, topaze);
		craft3.setItem(19, topaze);
		craft3.setItem(28, topaze);
		craft3.setItem(29, topaze);
		craft3.setItem(30, topaze);
		craft3.setItem(20, topaze);
		craft3.setItem(21, topaze);
		craft3.setItem(24, platine);
		craft3.setItem(36, previous);
		craft3.setItem(44, next);
		
		
		for (int i = 0; i < 45; i++) {
			if (craft3.getItem(i) == null) {
				ItemStack wall = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte)15);
				ItemMeta meta = wall.getItemMeta();
				meta.setDisplayName(" ");
				wall.setItemMeta(meta);
				craft3.setItem(i, wall);
			}
		}
		
		p.openInventory(craft3);
	}
	
	public void openCraft4(Player p){
		Inventory craft4 = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCraft Topaze"));
		
		ItemStack platine = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta platineM = platine.getItemMeta();
		platineM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
		platine.setItemMeta(platineM);
		
		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT, 9);
		ItemMeta topazeM = topaze.getItemMeta();
		topazeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazeM.addEnchant(Enchantment.DURABILITY, 1, true);
		topazeM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazeM);
		
		ItemStack previous = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta previousM = previous.getItemMeta();
		previousM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lPrécédent"));
		previous.setItemMeta(previousM);
		
		ItemStack air = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta airM = air.getItemMeta();
		airM.setDisplayName(" ");
		air.setItemMeta(airM);
		
		craft4.setItem(10, air);
		craft4.setItem(11, air);
		craft4.setItem(12, air);
		craft4.setItem(19, air);
		craft4.setItem(28, air);
		craft4.setItem(29, air);
		craft4.setItem(30, air);
		craft4.setItem(20, air);
		craft4.setItem(21, platine);
		craft4.setItem(24, topaze);
		craft4.setItem(36, previous);
		
		
		for (int i = 0; i < 45; i++) {
			if (craft4.getItem(i) == null) {
				ItemStack wall = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte)15);
				ItemMeta meta = wall.getItemMeta();
				meta.setDisplayName(" ");
				wall.setItemMeta(meta);
				craft4.setItem(i, wall);
			}
		}
		
		p.openInventory(craft4);
	}
	
}