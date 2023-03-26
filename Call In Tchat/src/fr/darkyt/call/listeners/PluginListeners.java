package fr.darkyt.call.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PluginListeners implements Listener {

	@EventHandler
	public void onCall(AsyncPlayerChatEvent e){
		
		  String message = e.getMessage(); 
		  Player p = e.getPlayer();

		  for(Player on : Bukkit.getOnlinePlayers()){ 
			  
			  if(on.equals(e.getPlayer())){
				  
				  p.chat(message);
				  continue;
				  
			  }
		    
			  if(message.contains(on.getName())){ 
				  
		      String newMessage = message.replaceAll(on.getName(), "§4@" + on.getName() + "§f");
		      p.chat(newMessage); 
		      on.playSound(on.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1); 
		      
		    }else{
		    	
		      p.chat(message); 
		      
		    }
		  }
		
	}
		
	
	
}
