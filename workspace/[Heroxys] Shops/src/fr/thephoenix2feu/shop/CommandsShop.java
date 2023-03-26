package fr.thephoenix2feu.shop;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CommandsShop implements Listener {

	public plugin pl;
	
	public CommandsShop(plugin plugin) {
		this.pl = plugin;
	}

	@SuppressWarnings("unchecked")
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		
		Player p = e.getPlayer();
		String args[] = e.getMessage().split(" ");
		
		
		
		
		//SHOP
		if(args[0].equalsIgnoreCase("/shop")) {

			if(args.length == 1) {
				helpShop(p);
				return;
			}
			//HELP
			if(args[1].equalsIgnoreCase("help")) {
				helpShop(p);
				return;
			}
			
			//buyplot

			else if(args[1].equalsIgnoreCase("buyplot")) {
								
				String s = null;
				if(args.length > 2)
					s = args[2];
				else
					s = plugin.getPlot(p);
				
				boolean parcelle = false;
				for(String st : (ArrayList<String>) pl.getConfig().get("shops")) {
					
					
					if(pl.getConfig().getString("shop."+st+".owner").equals(p.getUniqueId().toString())) {
						parcelle = true;
					}
					
				}
				
				if(parcelle) {
					p.sendMessage(ChatColor.RED+"Vous avez déjà une parcelle");
					return;
				}
				
				if(pl.getConfig().getString("shop."+s+".owner").equalsIgnoreCase("nobody") == false) {
					p.sendMessage(ChatColor.RED+"Ce plot appartient déjà à quelqun");
					return;
				}
				
				if(plugin.economy.getBalance(p) > pl.getConfig().getInt("shop."+s+".price")){
					
					plugin.economy.withdrawPlayer(p, pl.getConfig().getInt("shop."+s+".price"));
					pl.getConfig().set("shop."+s+".owner", p.getUniqueId().toString());
					p.sendMessage(ChatColor.GREEN+"Vous avez correctement acheté ce plot pour un mois");
					pl.getConfig().set("shop."+s+".end", 60*24*30);
					pl.getConfig().set("shop."+s+".name", p.getName());
					pl.saveConfig();
					
				}else {
					p.sendMessage(ChatColor.RED+"Vous n'avez pas assez d'argent");
				}
				
			}

			//TIME
			else if(args[1].equalsIgnoreCase("time")) {
				
				String s = null;
				if(args.length > 2)
					s = args[2];
				else
					s = plugin.getPlot(p);
					if(args.length == 2 && s.equalsIgnoreCase("nobody")) {
						p.sendMessage(ChatColor.RED+"Vous n'êtes dans aucun plot");
						return;
					}
				
				
				if(pl.getConfig().getString("shop."+s+".owner").equalsIgnoreCase("nobody")) {
					p.sendMessage(ChatColor.DARK_GREEN+"Cette parcelle n'est pas encore louée, louez là en faisant : "+ChatColor.YELLOW+"/shop buyplot");
					return;
				}
				
				UUID uuid = UUID.fromString(pl.getConfig().getString("shop."+s+".owner"));
				
				if(p.getUniqueId().equals(uuid)){
					int time = pl.getConfig().getInt("shop."+s+".end");
					int jour = time/60/24;
					int heure = (time - (jour*60*24))/60;
					int min = time - (jour*60*24) - (heure*60);
					p.sendMessage(ChatColor.DARK_GREEN+"Cet achat sera expiré dans "+jour+" jours "+heure+" heures "+min+" minutes");
				}else {
					p.sendMessage(ChatColor.RED+"Ce plot ne vous appartient pas");
				}
				
			}
			//TPPLOT
			else if(args[1].equalsIgnoreCase("tpplot")) {
				String s = null;
				if(args.length > 2)
					s = args[2];
				else
					s = plugin.getPlot(p);
				
				Location loc = (Location) pl.getConfig().get("shop."+s+".spawn");
				
				if(loc == null) {
					p.sendMessage(ChatColor.RED+"Problème de plot");
					return;
				}else {
					p.teleport(loc);
					p.sendMessage(ChatColor.GREEN+"Vous avez été téléporté au spawn du plot : "+s);
				}
				
			}
			
			//SETSPAWNPLOT
			else if(args[1].equalsIgnoreCase("setspawnplot")) {
				
				String s = null;
				
				if(args.length > 2) {
					s = args[2];
				}else {
					s = plugin.getPlot(p);
					if(args.length == 2 && s.equalsIgnoreCase("notfound")) {
						p.sendMessage(ChatColor.RED+"Veuillez séléctionnez une parcelle");
						return;
					}
				}
				if(plugin.buildSection(s).contains(p.getLocation()) == false) {
					p.sendMessage(ChatColor.DARK_RED+"Vous n'êtes pas dans la parcelle désignée");
					return;
				}
				
				
				UUID uuid = UUID.fromString(pl.getConfig().getString("shop."+s+".owner"));
				
				if(p.getUniqueId().equals(uuid)) {
					pl.getConfig().set("shop."+s+".spawn", p.getLocation());	
					pl.saveConfig();
					p.sendMessage(ChatColor.GREEN+"Spawn de la parcelle set avec succés !");
				}else {
					p.sendMessage(ChatColor.RED+"Vous ne possédez pas cette parcelle");
				}
								
			}
			
			
			
			//MENU
			else if(args[1].equalsIgnoreCase("menu")) {
				Inventory inv = Bukkit.createInventory(null, 9*3, "Shop");			
				int i = 26;
				while(i != 17) {
					ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
					inv.setItem(i, item);
					i--;
				}
				
				addItem(inv, Material.BED, ChatColor.RED+"Quitter le menu", "Click pour quitter le menu", 22);
				
				ArrayList<String> array = (ArrayList<String>) pl.getConfig().get("shops");
				
				if(array == null || array.size() == 0) {
					p.openInventory(inv);
					return;
				}

				
				for(String s : array) {
					
				String name = pl.getConfig().getString("shop."+s+".name");

				if(name.equalsIgnoreCase("nobody") == false)
					SkullAdd(inv, name, name);
							
				}
				p.openInventory(inv);	
			}
			else {
				helpShop(p);
				return;
			}
		}
	
	}
	public static void addItem(Inventory inv, Material t, String name, String lore, int place) {
		
		ItemStack item = new ItemStack(t);
		
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(name);
		ArrayList<String> Lore = new ArrayList<>();
		Lore.add(lore);
		itemM.setLore(Lore);
		
		item.setItemMeta(itemM);
		
		if(place == -1) {
			inv.addItem(item);
		}else {
			inv.setItem(place, item);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static void SkullAdd(Inventory inv, String Owner,String Name){
		
		ItemStack i = new ItemStack(Material.SKULL_ITEM, 1 , (byte)3);
		i.setAmount(1);
		SkullMeta iM = (SkullMeta) i.getItemMeta();
		iM.setOwner(Owner);
		iM.setDisplayName(Name);
		
		i.setItemMeta(iM);
		inv.addItem(i);
		
	}
	
	
	
	private void helpShop(Player p) {
		
		p.sendMessage(ChatColor.YELLOW+"Commandes du Shop");
		p.sendMessage(ChatColor.GRAY+"(si vous omettez le plot, celui ou vous vous trouvez sera séléctionné par default)");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/shop menu Ouvre le menu");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/shop setspawnplot <plot> Set le spawn du plot");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/shop tpplot <plot> Teleport à un plot");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/shop buyplot <plot> Achetter à un plot");
		p.sendMessage("");
		p.sendMessage(ChatColor.RED+"/shop time <plot> Voir le temps restant avant l'expiration de votre achat");
		
	}
	
}

