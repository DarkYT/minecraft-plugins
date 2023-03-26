package fr.darkyt.help.commands;

import fr.darkyt.help.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandHelp extends Command {
	
	Main main;
	
	public CommandHelp(String name, Main main) {
		super(name);
		
		this.main = main;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = (ProxiedPlayer) sender;
		String plname = player.getName();
		
		if(args.length == 0){
			player.sendMessage(new TextComponent("§cLa commande est: /helpme <votre demande>"));
		}
		
		if(args.length >= 1){
			StringBuilder bc = new StringBuilder();
			for(String part : args){
				bc.append(part + " ");
			}
			for (ProxiedPlayer pl : main.getProxy().getPlayers()){
				if(pl.hasPermission("staffchat.mod")){
					ProxyServer.getInstance().broadcast(new TextComponent("§l§a" + plname + " demande de l'aide >> §f " + bc.toString() ));
				}
			}
			player.sendMessage(new TextComponent("§l§fVous avez demandé de l'aide !"));
		}
	}

}
