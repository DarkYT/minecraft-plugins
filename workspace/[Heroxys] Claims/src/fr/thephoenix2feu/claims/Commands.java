package fr.thephoenix2feu.claims;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.dark.guilds.players.GuildPermission;
import com.dark.guilds.players.GuildPlayer;

public class Commands implements Listener {

	public Plugin pl;
	
	public Commands(Plugin plugin) {
		this.pl = plugin;
	}
	
	@EventHandler
	@SuppressWarnings("unchecked")
	public void onCmd(PlayerCommandPreprocessEvent e) {
		
		Player p = e.getPlayer();
		String args[] = e.getMessage().split(" ");
		
		if(args[0].equalsIgnoreCase("/zone")) {
			
			if(!pl.getAPI().getGuildPlayer(p).hasPermission(GuildPermission.CHANGE_CLAIM)) {
				p.sendMessage("§c[§4Guildes§c] §4Vous n'avez pas les permissions de définir le claim de la guilde");
				return;
			}
			
			if(args.length == 1){
				helpZone(p);
				return;
			}
			
			if(args[1].equalsIgnoreCase("help")) {
			helpZone(p);
			return;
			}
			else if(args[1].equalsIgnoreCase("create")){
			
				HashMap<Player, Location> hashmap1 = pl.getPoint1();
				HashMap<Player, Location> hashmap2 = pl.getPoint2();
				
				if(hashmap1.containsKey(p) == false || hashmap2.containsKey(p) == false) {
					p.sendMessage(ChatColor.RED+"Veuillez séléctionner une zone");
					return;
				}

				int h1X = hashmap1.get(p).getBlockZ();
				int h2X = hashmap2.get(p).getBlockZ();
				int h1Z = hashmap1.get(p).getBlockZ();
				int h2Z = hashmap2.get(p).getBlockZ();
				
			    int size = 0;
				if(h1Z > h2Z) {
					if(h1X > h2X) {
						size = (h1Z - h2Z+1) * (h1X - h2X+1);
					}
					else {
						size = (h1Z-h2Z+1) * (h2X- h1X+1);
					}
				}else {
					if(h1X > h1X) {
						size = (h2Z - h1Z+1) * (h1X - h2X+1);
					}
					else {
						size = (h2Z - h1Z+1) * (h2X - h1X+1);
					}
				}
				
				if(size > (pl.getAPI().getGuild(p).getFieldSide()*pl.getAPI().getGuild(p).getFieldSide())) {
					p.sendMessage("§c[§4Guildes§c] §4Le claim séléctionné est supérieur à la taille maximale que vous possédez");
					return;
				}

				pl.getConfig().set("zone."+pl.getAPI().getGuild(p).getUniqueId().toString()+".x1", hashmap1.get(p).getBlockX());
				pl.getConfig().set("zone."+pl.getAPI().getGuild(p).getUniqueId().toString()+".x2", hashmap2.get(p).getBlockX());
				pl.getConfig().set("zone."+pl.getAPI().getGuild(p).getUniqueId().toString()+".z1", hashmap1.get(p).getBlockZ());
				pl.getConfig().set("zone."+pl.getAPI().getGuild(p).getUniqueId().toString()+".z2", hashmap2.get(p).getBlockZ());
				ArrayList<String> arrayUUID = (ArrayList<String>) pl.getConfig().get("list");
				for(GuildPlayer guildPlayer : pl.getAPI().getGuild(p).getGuildMembers()) {
					arrayUUID.add(guildPlayer.getPlayer().getUniqueId().toString());
				}
				pl.getConfig().set("list", arrayUUID);
				pl.saveConfig();
				p.sendMessage("§a[§2Guildes§a] §2Vous venez de claim une zone de "+size+" blocs de surface !");
				return;
			}
			else if(args[1].equalsIgnoreCase("remove")){
				ArrayList<String> arrayUUID = (ArrayList<String>) pl.getConfig().get("list");
				if(arrayUUID.contains(p.getUniqueId().toString())) {
					arrayUUID.remove(p.getUniqueId().toString());
				}
				else {
					p.sendMessage(ChatColor.DARK_RED+"Vous n'avez pas de parcelle !");
					return;
				}
				pl.getConfig().set("zone."+pl.getAPI().getGuild(p).getUniqueId().toString(), "no zone");
				pl.saveConfig();
				p.sendMessage("§a[§2Guildes§a] §2Le claim a été supprimé");
				return;
			}
			else if(args[1].equalsIgnoreCase("redimention")){
			
				HashMap<Player, Location> hashmap1 = pl.getPoint1();
				HashMap<Player, Location> hashmap2 = pl.getPoint2();

				if(hashmap1.containsKey(p) == false || hashmap2.containsKey(p) == false) {
					p.sendMessage(ChatColor.RED+"Veuillez séléctionner une zone");
					return;
				}

				pl.getConfig().set("zone."+pl.getAPI().getGuild(p).getUniqueId().toString()+".x1", hashmap1.get(p).getBlockX());
				pl.getConfig().set("zone."+pl.getAPI().getGuild(p).getUniqueId().toString()+".x2", hashmap2.get(p).getBlockX());
				pl.getConfig().set("zone."+pl.getAPI().getGuild(p).getUniqueId().toString()+".z1", hashmap1.get(p).getBlockZ());
				pl.getConfig().set("zone."+pl.getAPI().getGuild(p).getUniqueId().toString()+".z2", hashmap2.get(p).getBlockZ());
				p.sendMessage(ChatColor.GREEN+"Zone redimentionnée !");
				return;
			}
			else if(args[1].equalsIgnoreCase("list")){
								
				@SuppressWarnings("unused")
				String section = args[1];
								
				for(String s : (ArrayList<String>) pl.getConfig().get("zone."+p.getUniqueId()+".sections")){
					p.sendMessage(ChatColor.GREEN+s);
				}
				return;
	
			}
			else {
				helpZone(p);
				return;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if(args[0].equalsIgnoreCase("/section")) {
			
			if(args.length == 1){
				helpSection(p);
				return;
			}
			
			if(args[1].equalsIgnoreCase("help")) {
			helpSection(p);
			return;
			}
			else if(args[1].equalsIgnoreCase("create")){
			
				HashMap<Player, Location> hashmap1 = pl.getPoint1();
				HashMap<Player, Location> hashmap2 = pl.getPoint2();
			
				if(hashmap1.containsKey(p) == false || hashmap2.containsKey(p) == false) {
					p.sendMessage(ChatColor.RED+"Veuillez séléctionner une zone");
					return;
				}
				if(args.length == 2) {
					p.sendMessage(ChatColor.RED+"Choisissez un nom de section a créer");
					
					return;
				}

				String section = args[2];
				
				boolean test = false;
				for(String s : (ArrayList<String>) pl.getConfig().get("list")) {
					UUID uuid = UUID.fromString(s);
					if(pl.isInto(hashmap1.get(p), uuid, "null") && pl.isInto(hashmap2.get(p), uuid, "null")) {
						test = true;
					}
				}
				if(!test) {
					
					p.sendMessage(ChatColor.RED+"La section n'est pas dans une zone");
					return;
					
				}
				
				pl.getConfig().set("zone."+p.getUniqueId()+"."+section+".x1", hashmap1.get(p).getBlockX());
				pl.getConfig().set("zone."+p.getUniqueId()+"."+section+".x2", hashmap2.get(p).getBlockX());
				pl.getConfig().set("zone."+p.getUniqueId()+"."+section+".z1", hashmap1.get(p).getBlockZ());
				pl.getConfig().set("zone."+p.getUniqueId()+"."+section+".z2", hashmap2.get(p).getBlockZ());
				
				if(pl.getConfig().get("zone."+p.getUniqueId()+".sections") instanceof ArrayList<?> == false) 
					pl.getConfig().set("zone."+p.getUniqueId()+".sections", new ArrayList<String>());

				
				ArrayList<String> arraySection = (ArrayList<String>) pl.getConfig().get("zone."+p.getUniqueId()+".sections");
				arraySection.add(section);
				pl.getConfig().set("zone."+p.getUniqueId()+".sections", arraySection);
				pl.saveConfig();
				p.sendMessage(ChatColor.GREEN+"Zone set !");
				return;
			}
			else if(args[1].equalsIgnoreCase("remove")){
				
				if(args.length == 2) {
					p.sendMessage(ChatColor.RED+"Choisissez un nom de section a supprimer");
					return;
				}
				
				ArrayList<String> array = (ArrayList<String>) pl.getConfig().get("zone."+p.getUniqueId().toString()+".sections");
				boolean test = false;
				for(String s : array) {
					if(s.equalsIgnoreCase(args[2]))
						test = true;
				}
				if(!test) {
					p.sendMessage(ChatColor.RED+"Cette section n'existe pas");
					return;
				}
				
				
				array.remove(args[2]);
				pl.getConfig().set("zone."+p.getUniqueId().toString()+".sections", array);
				pl.getConfig().set("zone."+p.getUniqueId()+"."+args[2], "no section");
				pl.saveConfig();
				p.sendMessage(ChatColor.RED+"section "+args[2]+" remove");
				return;
			}
			
			
			
			else if(args[1].equalsIgnoreCase("addplayer")){
				
				if(pl.getConfig().get("zone."+p.getUniqueId()) instanceof ConfigurationSection == false) {
					p.sendMessage(ChatColor.RED+"Vous n'avez pas de plot !");
					return;
				}
				
				boolean owner = false;
				for(String s : (ArrayList<String>) pl.getConfig().get("list")) {					
					UUID uuid = UUID.fromString(s);
					if(p.getUniqueId().equals(uuid)) {
						owner = true;
					}
				}
				if(!owner)return;
				
				if(args.length == 2) {
					p.sendMessage(ChatColor.RED+"Choisissez un nom de section");
					return;
				}
				
				String section = args[2];
				
				if(pl.getConfig().get("zone."+p.getUniqueId()+".sections") instanceof ArrayList<?> == false) 
					pl.getConfig().set("zone."+p.getUniqueId()+".sections", new ArrayList<String>());

				ArrayList<String> sections = (ArrayList<String>) pl.getConfig().get("zone."+p.getUniqueId()+".sections");
				
					if(sections.contains(section) == false) {
						p.sendMessage(ChatColor.RED+"Cette section n'existe pas");
						return;
					}
				
				if(args.length == 3) {
					p.sendMessage(ChatColor.RED+"Choisissez un nom de joueur");
					return;
				}
				
				String joueur = args[3];
				
				
				if(pl.getConfig().get("zone."+p.getUniqueId()+"."+section+".players") instanceof ArrayList<?> == false) 
					pl.getConfig().set("zone."+p.getUniqueId()+"."+section+".players", new ArrayList<String>());

				ArrayList<String> joueurs = (ArrayList<String>) pl.getConfig().get("zone."+p.getUniqueId()+"."+section+".players");
				joueurs.add(joueur);
				pl.getConfig().set("zone."+p.getUniqueId()+"."+section+"players", joueurs);
				pl.saveConfig();
				p.sendMessage(ChatColor.GREEN+"Joueur "+args[3]+" ajouté à la section "+args[2]);
				
			}
			
			
			
			
			
			else if(args[1].equalsIgnoreCase("removeplayer")){
				if(pl.getConfig().get("zone."+p.getUniqueId()) instanceof ConfigurationSection == false) {
					p.sendMessage(ChatColor.RED+"Vous n'avez pas de plot !");
					return;
				}
				
				boolean owner = false;
				for(String s : (ArrayList<String>) pl.getConfig().get("list")) {					
					UUID uuid = UUID.fromString(s);
					if(p.getUniqueId().equals(uuid)) {
						owner = true;
					}
				}
				if(!owner)return;
				
				if(args.length == 2) {
					p.sendMessage(ChatColor.RED+"Choisissez un nom de section");
					return;
				}
				
				String section = args[2];
				
				if(pl.getConfig().get("zone."+p.getUniqueId()+".sections") instanceof ArrayList<?> == false) 
					pl.getConfig().set("zone."+p.getUniqueId()+".sections", new ArrayList<String>());

				ArrayList<String> sections = (ArrayList<String>) pl.getConfig().get("zone."+p.getUniqueId()+".sections");
				
					if(sections.contains(section) == false) {
						p.sendMessage(ChatColor.RED+"Cette section n'existe pas");
						return;
					}
				
				if(args.length == 3) {
					p.sendMessage(ChatColor.RED+"Choisissez un nom de joueur");
					return;
				}
				
				String joueur = args[3];
				
				
				if(pl.getConfig().get("zone."+p.getUniqueId()+"."+section+".players") instanceof ArrayList<?> == false) 
					pl.getConfig().set("zone."+p.getUniqueId()+"."+section+".players", new ArrayList<String>());

				ArrayList<String> joueurs = (ArrayList<String>) pl.getConfig().get("zone."+p.getUniqueId()+"."+section+".players");
				
				if(!joueurs.contains(p.getName())) {
					p.sendMessage(ChatColor.RED+"Ce joueur ne fait pas partie de section");
				}
				
				joueurs.remove(joueur);
				pl.getConfig().set("zone."+p.getUniqueId()+"."+section+"players", joueurs);
				pl.saveConfig();
				p.sendMessage(ChatColor.GREEN+"Joueur "+args[3]+" enlvé à la section "+args[2]);
				
			}
			
			
			
			
			else if(args[1].equalsIgnoreCase("listplayer")){
	
				if(args.length == 2) {
					p.sendMessage(ChatColor.RED+"Choisissez un nom de section");
					return;
				}
				
				String section = args[2];
				
				if(pl.getConfig().getInt("zone."+p.getUniqueId()+"."+section+".x1") == 0) {
					p.sendMessage(ChatColor.RED+"Cette section n'existe pas");
					return;
				}
				
				for(String pls : (ArrayList<String>) pl.getConfig().get("zone."+p.getUniqueId()+"."+section+".players")){
				
					p.sendMessage(ChatColor.GREEN+pls);
				}
				return;
	
			}
			else if(args[1].equalsIgnoreCase("redimention")){
			
				HashMap<Player, Location> hashmap1 = pl.getPoint1();
				HashMap<Player, Location> hashmap2 = pl.getPoint2();

				if(hashmap1.containsKey(p) == false || hashmap2.containsKey(p) == false) {
					p.sendMessage(ChatColor.RED+"Veuillez séléctionner une zone");
					return;
				}
				
				if(args.length == 2) {
					p.sendMessage(ChatColor.RED+"Choisissez un nom de section a redimention");
					return;
				}
				
				String section = args[2];
				
				if(pl.getConfig().getInt("zone."+p.getUniqueId()+"."+section+".x1") == 0) {
					p.sendMessage(ChatColor.RED+"Cette section n'existe pas");
					return;
				}
				
				boolean test = false;
				for(String s : (ArrayList<String>) pl.getConfig().get("list")) {
					UUID uuid = UUID.fromString(s);
					if(pl.isInto(hashmap1.get(p), uuid, "null") && pl.isInto(hashmap2.get(p), uuid, "null")) {
						test = true;
					}
				}
				if(!test) {
					
					p.sendMessage(ChatColor.RED+"La section n'est pas dans une zone");
					return;
					
				}

				pl.getConfig().set("zone."+p.getUniqueId()+"."+section+".x1", hashmap1.get(p).getBlockX());
				pl.getConfig().set("zone."+p.getUniqueId()+"."+section+".x2", hashmap2.get(p).getBlockX());
				pl.getConfig().set("zone."+p.getUniqueId()+"."+section+".z1", hashmap1.get(p).getBlockZ());
				pl.getConfig().set("zone."+p.getUniqueId()+"."+section+".z2", hashmap2.get(p).getBlockZ());
				p.sendMessage(ChatColor.GREEN+"Section "+section+" redimentionnée !");
				return;
			}else {
				helpSection(p);
				return;
			}
			
		}
		
	}

	private void helpSection(Player p) {

		p.sendMessage(ChatColor.RED+"/section");
		p.sendMessage(ChatColor.RED+"create <nom>");
		p.sendMessage(ChatColor.RED+"addplayer <nom> player");
		p.sendMessage(ChatColor.RED+"listplayer <nom>");
		p.sendMessage(ChatColor.RED+"removeplayer <nom> player");
		p.sendMessage(ChatColor.RED+"remove <nom>");
		p.sendMessage(ChatColor.RED+"redimention <nom>");
		
	}
	private void helpZone(Player p) {
		p.sendMessage(ChatColor.RED+"/zone");
		p.sendMessage(ChatColor.RED+"create");
		p.sendMessage(ChatColor.RED+"setpermission");
		p.sendMessage(ChatColor.RED+"remove");
		p.sendMessage(ChatColor.RED+"redimention");
		p.sendMessage(ChatColor.RED+"list");
	}

	
}
