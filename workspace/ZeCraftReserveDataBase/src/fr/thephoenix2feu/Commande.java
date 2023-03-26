package fr.thephoenix2feu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Commande implements Listener {
	public plugin pl;
	
	public Commande(plugin plugin) {
		this.pl = plugin;
	}

	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String args[] = e.getMessage().split(" ");
		if(args[0].equalsIgnoreCase("/getInv")) {
			if(pl.getConfig().getBoolean("serveur.faction") == false) {
				p.sendMessage("§cVous ne pouvez pas récupérer votre inventaire dans le monde minage");
				return;
			}
			Inventory inv = plugin.sql.getInv(p);
			

			if(p.getInventory().firstEmpty() == -1) {
				p.sendMessage(ChatColor.DARK_RED+"Vous n'avez pas reçu votre réserve car votre inventaire est full");
				return;
			}
			for(ItemStack i : inv.getContents()) {
				if(i != null) {
					if(p.getInventory().firstEmpty() == -1) {
						plugin.sql.setInv(p, inv);
						return;
					}
					p.getInventory().addItem(i);
					inv.remove(i);
				}
			
			}
			plugin.sql.setInv(p, inv);
			p.sendMessage(ChatColor.GREEN+"Vous avez recu la totalité de votre reserve");
		}
		
		if(args[0].equalsIgnoreCase("/reserve")) {
			if(args.length == 1) {
				if(plugin.sql.hasInv(p)) {
					Inventory inv = plugin.sql.getInv(p);
					p.openInventory(inv);
					p.sendMessage(org.bukkit.ChatColor.DARK_GREEN+"Vous avez ouvert votre inventaire de réserve");
				}else {
					p.sendMessage(ChatColor.RED+"Vous n'avez pas de réserve !");
					return;
				}
				
			}
			else {
				if(!p.isOp()) {
					p.sendMessage(ChatColor.DARK_RED+"Vous n'avez pas les permissions !");
					return;
				}
				
				OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
				
				if(!plugin.sql.hasInv(player)) {
					p.sendMessage(ChatColor.RED+"Ce joueur n'a pas de réserve");
				}else {
					Inventory inv = plugin.sql.getInv(player);
					p.openInventory(inv);
					p.sendMessage(org.bukkit.ChatColor.DARK_GREEN+"Vous avez ouvert l'inventaire de réserve de "+player.getName());
				}
			}
		}
	}
	
}
