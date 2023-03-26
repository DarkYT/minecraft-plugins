package fr.blizzrpg.chest.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import fr.blizzrpg.chest.Main;

public class onLeave implements Listener {
	
	Main main;
	public onLeave(Main m) {
		main = m;
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		if (!main.getConfig().contains("chests." + e.getPlayer().getUniqueId())) {
			main.getConfig().createSection("chests." + e.getPlayer().getUniqueId());
		}
		
		char c = '1';
		for (ItemStack itemStack : main.chests.get(e.getPlayer().getUniqueId())) {
			if (itemStack != null) {
				main.saveItem(main.getConfig().createSection("chests." + e.getPlayer().getUniqueId() + "." + c++), itemStack);
			}
		}
		
		main.saveConfig();
	}

}
