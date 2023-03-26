package fr.thephoenix2feu.shop;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Commande implements Listener {

	public plugin pl;
	
	public Commande(plugin plugin) {
		this.pl = plugin;
	}

	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		
		Player p = e.getPlayer();
		String args[] = e.getMessage().split(" ");
		
		if(args[0].equalsIgnoreCase("/site")) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Liens.Site")));
		}
		if(args[0].equalsIgnoreCase("/discord")) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Liens.Discord")));
		}	
		if(args[0].equalsIgnoreCase("/chaine")) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Liens.Chaine")));
		}
		if(args[0].equalsIgnoreCase("/boutique")) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Liens.Boutique")));
		}
		if(args[0].equalsIgnoreCase("/arena")) {
			
			Location loc = (Location) pl.getConfig().get("Arena");
			if(loc == null) {
				p.sendMessage(ChatColor.RED+"L'Arena n'est pas set");
			}else {
				p.teleport(loc);
				p.sendMessage("§4Vous avez été téléporté à l'arène");
			}
			
		}
		if(args[0].equalsIgnoreCase("/setspawnarena")) {
			if(p.isOp()) {
				pl.getConfig().set("Arena", p.getLocation());
				pl.saveConfig();
				p.sendMessage(ChatColor.GREEN+"Le spawn de l'arène est désormais à votre position !");
			}
			else {
				p.sendMessage(ChatColor.RED+"Vous n'avez pas la permissions !");
			}
		}		
}

	
}
