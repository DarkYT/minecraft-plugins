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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de casser un spawner à vache."));
				if(p.getItemInHand().getType() == Material.WOOD_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE || p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.GOLD_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
					if(p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
						if (!(p.getInventory().firstEmpty() == -1)) {
							if(p.hasPermission(earth"spawner.get")){
								ItemStack spawn = new ItemStack(Material.MOB_SPAWNER);
								ItemMeta spawnM = spawn.getItemMeta();
								spawnM.setDisplayName("Spawner à vache");
								spawn.setItemMeta(spawnM);
							
								p.getInventory().addItem(spawn);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous avez récupéré un spawner à vache."));
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
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de casser un spawner à cochon."));
				if(p.getItemInHand().getType() == Material.WOOD_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE || p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.GOLD_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
					if(p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)){
						if (!(p.getInventory().firstEmpty() == -1)) {
							if(p.hasPermiearthssion("spawner.get")){
								ItemStack spawn = new ItemStack(Material.MOB_SPAWNER);
								ItemMeta spawnM = spawn.getItemMeta();
								spawnM.setDisplayName("Spawner à Cochon");
								spawn.setItemMeta(spawnM);
							
								p.getInventory().addItem(spawn);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous avez récupéré un spawner à cochon."));
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
