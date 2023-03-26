package fr.darkyt.dark.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.darkyt.dark.Main;


public class PluginListeners implements Listener {
	
	private Main main;

	public PluginListeners(Main main) {
		
		this.main = main;
		
	}
	
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent event){
		
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		

			
		if (block.getType() == Material.WALL_SIGN || block.getType() == Material.SIGN_POST) {
			  Sign sign = (Sign) block.getState();
			  
			  String text = sign.getLine(0);
			  
			  if(text.equals("[REPAIR]")){
				  
					ItemStack it = player.getInventory().getItemInMainHand();
					ItemStack newItem = new ItemStack(it.getType(), it.getAmount());
					
					if(player.getInventory().getItemInMainHand().getDurability() == 0){
						
						String messageError = main.getConfig().getString("listeners.repair.error1");
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', messageError));
						return;
						
					}
					
					if(player.getInventory().contains(Material.DIAMOND)){
						
						ItemStack money = new ItemStack(Material.DIAMOND, (int) Math.pow(2, 5));
						
						ItemMeta meta = it.getItemMeta();
						if(it.hasItemMeta()){
							if(it.getItemMeta().hasDisplayName()) meta.setDisplayName(it.getItemMeta().getDisplayName());
							if(it.getItemMeta().hasLore()) meta.setLore(it.getItemMeta().getLore());
							if(it.getItemMeta().hasEnchants()) newItem.addUnsafeEnchantments(it.getEnchantments());
						}
						
						newItem.setItemMeta(meta);
						player.getInventory().setItemInMainHand(newItem);
						
						player.getInventory().removeItem(money);
						
						String messageError = main.getConfig().getString("listeners.repair.validate");
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', messageError));
						
					}else{
						
						String messageError = main.getConfig().getString("listeners.repair.error2");
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', messageError));
						
					}
				  
				  
			  }
			  
		}else{
			
			return;
		}
		
		
	}
	
	@EventHandler
	public void onCall(AsyncPlayerChatEvent e){
		
		  String message = e.getMessage();
		  Player player = e.getPlayer();
		  

		  for(Player on:Bukkit.getOnlinePlayers()){ 
			  
				  
			  if(on.equals(player))continue;
				  

		    
			  if(message.contains(on.getName())){ 
				  
			      on.playSound(on.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
			      String messageValidate = main.getConfig().getString("listeners.tagchat.tag");
			      
				  String msgValidate = messageValidate.replace("{0}", player.getName());
				  
				  on.sendMessage(ChatColor.translateAlternateColorCodes('&', msgValidate).replaceAll("ey", "é"));
			      

		      
		    }else{
		    	
		    	  e.setMessage(message); 
		      
		    }
			  
		  }
		
	}

}
