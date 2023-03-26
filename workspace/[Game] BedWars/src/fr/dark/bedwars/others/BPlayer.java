package fr.dark.bedwars.others;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.dark.bedwars.BedWars;

public class BPlayer {

	Player p;
	Team t;
	boolean hasSwordEnchant, hasArmorEnchant;
	int armorLevel;
	String armorType;
	
	public BPlayer(Player p, Team t) {
		this.p = p;
		this.t = t;
		this.armorType = "leather";
		this.armorLevel = 0;
		this.hasSwordEnchant = false;
		this.hasArmorEnchant = false;
	}
	
	public Player getPlayer() {
		return p;
	}
	
	public Team getTeam() {
		return t;
	}
	
	public void equip() {
		p.getInventory().clear();
		giveArmor(armorType, hasArmorEnchant, armorLevel, p);
		
		p.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD));
		p.updateInventory();
	}
	
	public void giveArmor(String type, boolean hasEnchant, int armorLevel, Player p) {
		if(type.equals("leather")) {
			ItemStack helmet;
			ItemStack chest;
			ItemStack legs;
			ItemStack boots;
			
			if(hasEnchant) {
				helmet = BedWars.coloredArmour(Material.LEATHER_HELMET, 1, t.getColor(), Enchantment.PROTECTION_ENVIRONMENTAL, armorLevel);
				chest = BedWars.coloredArmour(Material.LEATHER_CHESTPLATE, 1, t.getColor(), Enchantment.PROTECTION_ENVIRONMENTAL, armorLevel);
				legs = BedWars.coloredArmour(Material.LEATHER_LEGGINGS, 1, t.getColor(), Enchantment.PROTECTION_ENVIRONMENTAL, armorLevel);
				boots = BedWars.coloredArmour(Material.LEATHER_BOOTS, 1, t.getColor(), Enchantment.PROTECTION_ENVIRONMENTAL, armorLevel);
			}else {
				helmet = BedWars.coloredArmour(Material.LEATHER_HELMET, 1, t.getColor(),null, 0);
				chest = BedWars.coloredArmour(Material.LEATHER_CHESTPLATE, 1, t.getColor(),null, 0);
				legs = BedWars.coloredArmour(Material.LEATHER_LEGGINGS, 1, t.getColor(),null, 0);
				boots = BedWars.coloredArmour(Material.LEATHER_BOOTS, 1, t.getColor(),null, 0);
			}
			
			p.getInventory().setHelmet(helmet);
			p.getInventory().setChestplate(chest);
			p.getInventory().setLeggings(legs);
			p.getInventory().setBoots(boots);
			
			p.updateInventory();
			
		}else if(type.equals("chain")) {
			
		}else if(type.equals("iron")) {
			
		}else if(type.equals("diamond")) {
			
		}
	}

}
