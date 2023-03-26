package fr.darkyt.help.commands;


import fr.darkyt.help.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandReport extends Command {
	
	Main main;

	public CommandReport(String name, Main main) {
		super(name);
		
		this.main = main;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = (ProxiedPlayer) sender;
		
		if(args.length == 0 || args.length == 1){			
			player.sendMessage(new TextComponent("§cLa commande est: /report <nom du joueur> <raison>"));
		}
		
		if(Main.cooldown.containsKey(player.getName())){
			
			int secondes = 300;
			long timeleft = ((Main.cooldown.get(player.getName()) / 1000) + secondes) - (System.currentTimeMillis() / 1000);
			if(timeleft > 0){
				player.sendMessage(new TextComponent("§l§fTu dois attendre encore §4" + timeleft + "s §fpour report un joueur"));
				return;
			}
			
		}
		
		Main.cooldown.put(player.getName(), System.currentTimeMillis());
		
		
		
		if(args.length == 2){
			
			String cheater = args[0];
			String cheat = args[1];
			for (ProxiedPlayer pl : main.getProxy().getPlayers()){
				if(pl.hasPermission("staffchat.mod")){
					ProxyServer.getInstance().broadcast(new TextComponent("§l§f"+player+" a report §4"+cheater+" §fpour: §4"+cheat));
				}
			}
			
		}
	}

}
