package fr.blizzrpg.classsystem.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.blizzrpg.classsystem.ClassAPI;

public class menuClassEvent implements Listener {

	ClassAPI classapi;
	
	public menuClassEvent(ClassAPI capi){
		classapi = capi;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		World map = (World) classapi.getConfig().get("Spawn.World");
		int xEpeiste = classapi.getConfig().getInt("Spawn.Epeiste.x");
		int yEpeiste = classapi.getConfig().getInt("Spawn.Epeiste.y");
		int zEpeiste = classapi.getConfig().getInt("Spawn.Epeiste.z");
		int yawEpeiste = classapi.getConfig().getInt("Spawn.Epeiste.yaw");
		int pitchEpeiste = classapi.getConfig().getInt("Spawn.Epeiste.pitch");
		Location locEpeiste = new Location(map, xEpeiste, yEpeiste, zEpeiste, yawEpeiste, pitchEpeiste);
		int xArcher = classapi.getConfig().getInt("Spawn.Archer.x");
		int yArcher = classapi.getConfig().getInt("Spawn.Archer.y");
		int zArcher = classapi.getConfig().getInt("Spawn.Archer.z");
		int yawArcher = classapi.getConfig().getInt("Spawn.Archer.yaw");
		int pitchArcher = classapi.getConfig().getInt("Spawn.Archer.pitch");
		Location locArcher = new Location(map, xArcher, yArcher, zArcher, yawArcher, pitchArcher);
		int xBarbare = classapi.getConfig().getInt("Spawn.Barbare.x");
		int yBarbare = classapi.getConfig().getInt("Spawn.Barbare.y");
		int zBarbare = classapi.getConfig().getInt("Spawn.Barbare.z");
		int yawBarbare = classapi.getConfig().getInt("Spawn.Barbare.yaw");
		int pitchBarbare = classapi.getConfig().getInt("Spawn.Barbare.pitch");
		Location locBarbare = new Location(map, xBarbare, yBarbare, zBarbare, yawBarbare, pitchBarbare);
		int xTravailleur = classapi.getConfig().getInt("Spawn.Invocateur.x");
		int yTravailleur = classapi.getConfig().getInt("Spawn.Travailleur.y");
		int zTravailleur = classapi.getConfig().getInt("Spawn.Travailleur.z");
		int yawTravailleur = classapi.getConfig().getInt("Spawn.Travailleur.yaw");
		int pitchTravailleur = classapi.getConfig().getInt("Spawn.Travailleur.pitch");
		Location locTravailleur = new Location(map, xTravailleur, yTravailleur, zTravailleur, yawTravailleur, pitchTravailleur);
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6&lClasse"))) {
			e.setCancelled(true);
			switch (e.getCurrentItem().getType()) {
			case IRON_SWORD:
				classapi.setPlayerClass(p, "Epeiste");
				p.teleport(locEpeiste);
				classapi.checkLevel(p);
				classapi.giveStartup(p, "Epeiste");
				break;
			case BOW:
				classapi.setPlayerClass(p, "Archer");
				p.teleport(locArcher);
				classapi.checkLevel(p);
				classapi.giveStartup(p, "Archer");
				break;
			case GOLD_AXE:
				classapi.setPlayerClass(p, "Barbare");
				p.teleport(locBarbare);
				classapi.checkLevel(p);
				classapi.giveStartup(p, "Barbare");
				break;
			case DIAMOND_SPADE:
				classapi.setPlayerClass(p, "Travailleur");
				p.teleport(locTravailleur);
				classapi.checkLevel(p);
				classapi.giveStartup(p, "Travailleur");
				break;
			default:
				break;
			}
		}
	}
	
}
