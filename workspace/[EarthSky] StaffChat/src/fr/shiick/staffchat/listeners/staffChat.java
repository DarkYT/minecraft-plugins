package fr.shiick.staffchat.listeners;

import fr.shiick.staffchat.StaffChat;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class staffChat implements Listener {

	StaffChat staffchat;

	public staffChat(StaffChat staffchat) {
		this.staffchat = staffchat;
	}

	@EventHandler
	public void onChat(ChatEvent e) {
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		String msg = e.getMessage();
		String staffMsg = msg.replaceFirst("@", "");
		if (e.getMessage().startsWith("@")) {
			if (p.hasPermission("staffchat.mod")) {
				for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
					if (all.hasPermission("staffchat.mod")){
						e.setCancelled(true);
						String message = ChatColor.translateAlternateColorCodes('&', "&6[&7StaffChat&6] &6[&8&l" + StaffChat.getServer(p) + "&6] &6" + p.getName() + " &8» &7" + staffMsg);
						all.sendMessage(new TextComponent(message));
					}
				}
			}
		}
	}

}
