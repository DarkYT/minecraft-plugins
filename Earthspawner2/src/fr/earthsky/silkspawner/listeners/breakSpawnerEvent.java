package fr.earthsky.silkspawner.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.earthsky.silkspawner.SilkSpawner;

public class breakSpawnerEvent implements Listener {

	SilkSpawner silkspawner;

	public breakSpawnerEvent(SilkSpawner ss) {
		silkspawner = ss;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSpawnerBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block block = e.getBlock();
		if(block.getType() == Material.MOB_SPAWNER){
			BlockState bState = block.getState();
			CreatureSpawner spawner = ((CreatureSpawner) bState);
			String mob = spawner.getCreatureTypeName();
			if(mob.equals("Cow")){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de casser un spawner à  vache."));
				if(p.getItemInHand().getType() == Material.WOOD_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE || p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.GOLD_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
					if(p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
						if (!(p.getInventory().firstEmpty() == -1)) {
							if(p.hasPermission("earthspawner.get")){
							silkspawner.getSpawner(p, "cow", 1);e."));
								e.setExpToDrop(0);
							}
						} else {
							e.setCancelled(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVotre inventaire est plein !"));
						}
					} else {
						e.setExpToDrop(0);
					}
				} else {
					e.setExpToDrop(0);
				}
			} else if(mob.equals("Pig")){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de casser un spawner à  cochon."));
				if(p.getItemInHand().getType() == Material.WOOD_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE || p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.GOLD_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
					if(p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
						if (!(p.getInventory().firstEmpty() == -1)) {
							if(p.hasPermission("earthspawner.get")){
	silkspawner.getSpawner(p, "pig", 1); cochon."));
								e.setExpToDrop(0);
							}
						} else {
							e.setCancelled(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVotre inventaire est plein !"));
						}
					} else {
						e.setExpToDrop(0);
					}
				} else {
					e.setExpToDrop(0); else if(mob.equals("creeper")){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de casser un spawner à creeper."));
				if(p.getItemInHand().getType() == Material.WOOD_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE || p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.GOLD_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
					if(p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
						if (!(p.getInventory().firstEmpty() == -1)) {
							if(p.hasPermission("earthspawner.get")){
								silkspawner.getSpawner(p, "creeper", 1);
								e.setExpToDrop(0);
							}
						} else {
							e.setCancelled(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVotre inventaire est plein !"));
						}
					} else {
						e.setExpToDrop(0);
					}
				} else {
					e.setExpToDrop(0);
				}
			} else if(mob.equals("zombie")){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de casser un spawner à zombie."));
				if(p.getItemInHand().getType() == Material.WOOD_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE || p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.GOLD_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
					if(p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
						if (!(p.getInventory().firstEmpty() == -1)) {
							if(p.hasPermission("earthspawner.get")){
								silkspawner.getSpawner(p, "zombie", 1);
								e.setExpToDrop(0);
							}
						} else {
							e.setCancelled(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVotre inventaire est plein !"));
						}
					} else {
						e.setExpToDrop(0);
					}
				} else {
					e.setExpToDrop(0);
				}
			} else if(mob.equals("spider")){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de casser un spawner à araignée."));
				if(p.getItemInHand().getType() == Material.WOOD_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE || p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.GOLD_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
					if(p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
						if (!(p.getInventory().firstEmpty() == -1)) {
							if(p.hasPermission("earthspawner.get")){
								silkspawner.getSpawner(p, "spider", 1);
								e.setExpToDrop(0);
							}
						} else {
							e.setCancelled(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVotre inventaire est plein !"));
						}
					} else {
						e.setExpToDrop(0);
					}
				} else {
					e.setExpToDrop(0);
				}
			} else if(mob.equals("skeleton")){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de casser un spawner à squelette."));
				if(p.getItemInHand().getType() == Material.WOOD_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE || p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.GOLD_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
					if(p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
						if (!(p.getInventory().firstEmpty() == -1)) {
							if(p.hasPermission("earthspawner.get")){
								silkspawner.getSpawner(p, "skeleton", 1);
								e.setExpToDrop(0);
							}
						} else {
							e.setCancelled(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVotre inventaire est plein !"));
						}
					} else {
						e.setExpToDrop(0);
					}
				} else {
					e.setExpToDrop(0);
				}
			} else if(mob.equals("slime")){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de casser un spawner à slime."));
				if(p.getItemInHand().getType() == Material.WOOD_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE || p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.GOLD_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
					if(p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
						if (!(p.getInventory().firstEmpty() == -1)) {
							if(p.hasPermission("earthspawner.get")){
								silkspawner.getSpawner(p, "slime", 1);
								e.setExpToDrop(0);
							}
						} else {
							e.setCancelled(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVotre inventaire est plein !"));
						}
					} else {
						e.setExpToDrop(0);
					}
				} else {
					e.setExpToDrop(0);
				}
			} else if(mob.equals("iron_golem")){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de casser un spawner à golem de fer."));
				if(p.getItemInHand().getType() == Material.WOOD_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE || p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.GOLD_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
					if(p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
						if (!(p.getInventory().firstEmpty() == -1)) {
							if(p.hasPermission("earthspawner.get")){
								silkspawner.getSpawner(p, "iron_golem", 1);
								e.setExpToDrop(0);
							}
						} else {
							e.setCancelled(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVotre inventaire est plein !"));
						}
					} else {
						e.setExpToDrop(0);
					}
				} else {
					e.setExpToDrop(0);
				}
			} else if(mob.equals("rabbit")){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de casser un spawner à lapin."));
				if(p.getItemInHand().getType() == Material.WOOD_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE || p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.GOLD_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
					if(p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
						if (!(p.getInventory().firstEmpty() == -1)) {
							if(p.hasPermission("earthspawner.get")){
								silkspawner.getSpawner(p, "rabbit", 1);
								e.setExpToDrop(0);
							}
						} else {
							e.setCancelled(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVotre inventaire est plein !"));
						}
					} else {
						e.setExpToDrop(0);
					}
				} else {
					e.setExpToDrop(0);
				}
			}
				}
			}
		}
	}

}
