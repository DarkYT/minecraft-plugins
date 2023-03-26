package fr.thephoenix2feu.shop;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;

public class AdminShop implements Listener {

public plugin pl;
	
	public AdminShop(plugin plugin) {
		this.pl = plugin;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		
		Player p = e.getPlayer();
		String args[] = e.getMessage().split(" ");
		
		

		if(args[0].equalsIgnoreCase("/adminshop") || args[0].equalsIgnoreCase("/ashop")) {

			if(!p.isOp()) {
				p.sendMessage("§c[§4Plots§2] §4Vous n'avez pas les permissions requises");
				return;
			}
			if(args.length == 1) {
				helpAdminShop(p);
				return;
			}
			
			if(args[1].equalsIgnoreCase("help")) {
				helpAdminShop(p);
				return;
			}


			//SETPLOTOWNER
			else if(args[1].equalsIgnoreCase("setowner")) {
				
				if(args.length < 3) {
					p.sendMessage("§c[§4Plots§2] §4Mauvais usage: §c/shop setowner <plot> (joueur) §4(ne pas mettre de joueur pour remettre le plot en vente)");
					return;
				}
				
				String name = new String();
				String owner = new String();
				
				
				if(args.length == 3) {
					name = "nobody";
					owner = "nobody";
					p.sendMessage(ChatColor.GREEN+"Parcelle remise à la vente");
				}else {
					name = args[3];
					owner = Bukkit.getOfflinePlayer(args[3]).getUniqueId().toString();
					p.sendMessage(ChatColor.GREEN+args[3]+" définie comme posseseur de la parcelle "+args[2]);
					
				}
				if(((ArrayList<String>) pl.getConfig().get("shops")).contains(args[2])) {
				
					pl.getConfig().set("shop."+args[2]+".owner",  owner);
					pl.getConfig().set("shop."+args[2]+".name",  name);
					pl.saveConfig();
				}else {
					p.sendMessage(ChatColor.RED+"Ce plot n'existe pas");
				}
				
			}

			
			//SETSPAWNPLOT
			else if(args[1].equalsIgnoreCase("setspawnplot")) {
				String s = null;
				
				if(args.length > 2)
					s = args[2];
				else
					s = plugin.getPlot(p);
				
				if((Location) pl.getConfig().get("shop."+s+".spawn") == null) {
					p.sendMessage(ChatColor.RED+"Problème de plot");
					return;
				}
				
					pl.getConfig().set("shop."+s+".spawn", p.getLocation());	
					pl.saveConfig();
					p.sendMessage(ChatColor.GREEN+"Spawn de la parcelle set avec succés !");
								
			}
			
			

			//REDIMENTION
			else if(args[1].equalsIgnoreCase("setprice")) {
				
				if(args.length < 3) {
					p.sendMessage(ChatColor.RED+"Veuillez choisir un nom de zone ainsi qu'un montant");
					return;
				}
				
				if(((ArrayList<String>) pl.getConfig().get("shops")).contains(args[2])) {
					
					pl.getConfig().set("shop."+args[2]+".price", Integer.parseInt(args[3]));
					p.sendMessage(ChatColor.GREEN+args[3]+" définie comme montant de la parcelle "+args[2]);
					pl.saveConfig();
				}else {
					p.sendMessage(ChatColor.RED+"Ce plot n'existe pas");
				}
				
			}
			
			//REDIMENTION
			else if(args[1].equalsIgnoreCase("redimention")) {
				
				if(args.length < 3) {
					p.sendMessage(ChatColor.RED+"Veuillez choisir un nom de zone a supprimer");
					return;
				}
				if(((ArrayList<String>) pl.getConfig().get("shops")).contains(args[2])) {
					
					plugin.putSection(args[2], (CuboidSelection) plugin.wep.getSelection(p));
					p.sendMessage(ChatColor.GREEN+"Plot redimentionné");
					
				}else {
					p.sendMessage(ChatColor.RED+"Ce plot n'existe pas");
				}
				
			}

			//LIST
			else if(args[1].equalsIgnoreCase("list")) {
				ArrayList<String> array = (ArrayList<String>) pl.getConfig().get("shops");
				String sb = new String();
				
				if(array.size() == 0) {
					p.sendMessage(ChatColor.RED+"Aucun plot trouvé");
					return;
				}
				
				for(String s : array) {
					
					sb = sb+s+";";
					
				}
				
				p.sendMessage(sb);
				
			}
			
			//REMOVE
			else if(args[1].equalsIgnoreCase("remove")) {
				
				if(args.length < 3) {
					p.sendMessage(ChatColor.RED+"Veuillez choisir un nom de zone a supprimer");
					return;
				}
				
				
				try {
					pl.getConfig().set("shop."+args[2], "");
					p.sendMessage(ChatColor.GREEN+"Plot "+args[2]+" correctement remove");
				} catch (Exception e2) {
					p.sendMessage(ChatColor.RED+"Une erreur s'est produite");
				}
				
			}
			
			
			//SET
			else if(args[1].equalsIgnoreCase("set")) {
				if(args.length < 3) {
					p.sendMessage(ChatColor.RED+"Veuillez choisir un nom pour la zone de shop");
					return;
				}
				if(plugin.wep.getSelection(p) == null) {
					p.sendMessage(ChatColor.RED+"Veuillez séléctionner une parcelle à l'aide de l'outil WorldEdit");
					return;
				}
				
				plugin.putSection(args[2], (CuboidSelection) plugin.wep.getSelection(p));
				
				p.sendMessage(ChatColor.GREEN+"Zone correctement créée");
				
			}
			else {
				helpAdminShop(p);
			}
			
			
		}
		
	}
	
	
	
	private void helpAdminShop(Player p) {
		
		p.sendMessage(ChatColor.YELLOW+"Commandes du Shop");
		p.sendMessage(ChatColor.DARK_RED+"(Attention à ne pas omettre le plot !)");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/adminshop set Set un nouveau plot");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/adminshop Remove <plot> Supprime un plot");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/adminshop list Montre la liste des plot");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/adminshop redimentionplot <plot> Redimentionne la parcelle");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/adminshop setplotowner <plot> <player> Définie le possesuer de la parcelle");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/adminshop setspawnplot <plot> Force le changement du spawn de la parcelle");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/adminshop setPrice <plot> <prix> Force le changement du montant de la parcelle (ne rentrez aucun joueur si vous souhaitez rendre cette parcelle à la vente)");
		
	}
	
}

