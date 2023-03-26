package fr.shiick.redcore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.shiick.redcore.RedCore;
import fr.shiick.redcore.utils.Utils;

public class chatEvent implements Listener {

	RedCore core;
	Utils utils;
	
	public chatEvent(RedCore core) {
		this.core = core;
		utils = new Utils(core);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String message = e.getMessage();
		if(core.admin.contains(p)) {
			e.setCancelled(true);
			String msg = core.getConfig().getString("Message.Hide.Hidden");
			p.sendMessage(utils.colorMessage(msg));
		} else {
			if (core.lastMessage.get(p.getName()) == null || !core.lastMessage.get(p.getName()).equalsIgnoreCase(message)){
				core.lastMessage.put(p.getName(), message);
			} else if (core.lastMessage.get(p.getName()).equalsIgnoreCase(message)) {
				e.setCancelled(true);
				String msg = core.getConfig().getString("Message.Spam");
				p.sendMessage(utils.colorMessage(msg));
			}
		}
	}
	
}
