package fr.shiick.staffchat;

import fr.shiick.staffchat.listeners.staffChat;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class StaffChat extends Plugin {

	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerListener(this, new staffChat(this));
	}
	
	public static String getServer(ProxiedPlayer p){
		String server = ProxyServer.getInstance().getPlayer(p.getName()).getServer().getInfo().getName();
		if(server.equalsIgnoreCase("hub")){
			String serv = "Hub";
			return serv;
		} else if(server.equalsIgnoreCase("skyblock")){
			String serv = "SB1";
			return serv;
		} else if(server.equalsIgnoreCase("skyblock2")){
			String serv = "SB2";
			return serv;
		} else if(server.equalsIgnoreCase("SBBuild")){
			String serv = "SBB";
			return serv;
		} else if(server.equalsIgnoreCase("waterblock")){
			String serv = "WB";
			return serv;
		} else if(server.equalsIgnoreCase("skywars")){
			String serv = "SW";
			return serv;
		} else {
			return "LAG";
		}
	}

}
