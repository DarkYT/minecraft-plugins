package fr.earthsky.silkspawner.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.earthsky.silkspawner.SilkSpawner;
import net.md_5.bungee.api.ChatColor;

public class placeSpawnerEvent implements Listener {
	
	SilkSpawner silkspawner;
	
	public placeSpawnerEvent(SilkSpawner ss){
		silkspawner = ss;
	}
	
	@EventHandler
	public void onSpawnerPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		Block block = e.getBlock();
		if(block.getType() == Material.MOB_SPAWNER){
			if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7Spawner Ã  vache")){
				BlockState bState = block.getState();
				CreatureSpawner spawner = ((CreatureSpawner) bState);
				spawner.setSpawnedType(EntityType.COW);
				bState.update();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSpawner Ã  vache placÃ©."));
	 else if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7Spawner à cochon")){
				BlockState bState = block.getState();
				CreatureSpawner spawner = ((CreatureSpawner) bState);
				spawner.setSpawnedType(EntityType.PIG);
				bState.update();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSpawner à cochon placé."));
			} else if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7Spawner à zombie")){
				BlockState bState = block.getState();
				CreatureSpawner spawner = ((CreatureSpawner) bState);
				spawner.setSpawnedType(EntityType.ZOMBIE);
				bState.update();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSpawner à zombie placé."));
			} else if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7Spawner à squelette")){
				BlockState bState = block.getState();
				CreatureSpawner spawner = ((CreatureSpawner) bState);
				spawner.setSpawnedType(EntityType.SKELETON);
				bState.update();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSpawner à squelette placé."));
			} else if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7Spawner à araignée")){
				BlockState bState = block.getState();
				CreatureSpawner spawner = ((CreatureSpawner) bState);
				spawner.setSpawnedType(EntityType.SPIDER);
				bState.update();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSpawner à araignée placé."));
			} else if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7Spawner à creeper")){
				BlockState bState = block.getState();
				CreatureSpawner spawner = ((CreatureSpawner) bState);
				spawner.setSpawnedType(EntityType.CREEPER);
				bState.update();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSpawner à creeper placé."));
			} else if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7Spawner à slime")){
				BlockState bState = block.getState();
				CreatureSpawner spawner = ((CreatureSpawner) bState);
				spawner.setSpawnedType(EntityType.SLIME);
				bState.update();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSpawner à slime placé."));
			} else if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7Spawner à golem de fer")){
				BlockState bState = block.getState();
				CreatureSpawner spawner = ((CreatureSpawner) bState);
				spawner.setSpawnedType(EntityType.IRON_GOLEM);
				bState.update();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSpawner à golem de fer placé."));
			}		}
		}
	}

}
