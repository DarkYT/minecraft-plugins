package fr.dark.ram.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.PressurePlate;

import fr.dark.ram.Core;
import fr.dark.ram.Ram;

public class mainListener implements Listener {

	Core core;
	Map<Location, PressurePlate> plates = new HashMap<>();

	public mainListener(Core core) {
		this.core = core;
	}

	@EventHandler
	public void onPressure(PlayerInteractEvent e){
		List<Location> pressLocs = new ArrayList<>();
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(Core.confFile);
		if (e.getAction().equals(Action.PHYSICAL)) {
			if (e.getClickedBlock().getType().equals(Material.STONE_PLATE)) {
				String o = null;
				for (String plates : yc.getConfigurationSection("Plates").getKeys(false)) {
					int X = (int) e.getClickedBlock().getLocation().getBlockX();
					int Y = (int) e.getClickedBlock().getLocation().getBlockY();
					int Z = (int) e.getClickedBlock().getLocation().getBlockZ();
					String c = X + ";" + Y + ";" + Z;
					if (yc.getString("Plates."+plates+".first").equals(c) || yc.getString("Plates."+plates+".second").equals(c) || yc.getString("Plates."+plates+".third").equals(c) || yc.getString("Plates."+plates+".fourth").equals(c)) {
						o = plates;
						for(String coords : yc.getConfigurationSection("Plates."+plates).getKeys(false)){
							if(!(coords.equals("world"))){
								String[] coord = yc.getString("Plates."+plates+"."+coords).split(";");
								World world = Bukkit.getWorld(yc.getString("Plates."+plates+".world"));
								double x = Double.parseDouble(coord[0]);
								double y = Double.parseDouble(coord[1]);
								double z = Double.parseDouble(coord[2]);
								pressLocs.add(new Location(world, x,y,z));
							}
						}
					}
				}
				for(int i = 0; i<pressLocs.size();i++){
					if(pressLocs.get(i).getBlock().getType().equals(Material.STONE_PLATE)){
						PressurePlate plate = (PressurePlate) pressLocs.get(i).getBlock().getState().getData();
						if(plate.isPressed()){
							plates.put(pressLocs.get(i), plate);
						}
					}
				}
				if(plates.size() == 4){
					String[] coords = yc.getString("Rams."+o+".body1.loc").split(";");
					String[] coords2 = yc.getString("Rams."+o+".body2.loc").split(";");
					String[] coords3 = yc.getString("Rams."+o+".body3.loc").split(";");
					String[] coords4 = yc.getString("Rams."+o+".tail.loc").split(";");
					World world = Bukkit.getWorld(yc.getString("Rams."+o+".world"));
					double x = Double.parseDouble(coords[0]);
					double y = Double.parseDouble(coords[1]);
					double z = Double.parseDouble(coords[2]);
					double x1 = Double.parseDouble(coords2[0]);
					double y1 = Double.parseDouble(coords2[1]);
					double z1 = Double.parseDouble(coords2[2]);
					double x2 = Double.parseDouble(coords3[0]);
					double y2 = Double.parseDouble(coords3[1]);
					double z2 = Double.parseDouble(coords3[2]);
					double x3 = Double.parseDouble(coords4[0]);
					double y3 = Double.parseDouble(coords4[1]);
					double z3 = Double.parseDouble(coords4[2]);
					Location un = new Location(world,x,y,z);
					Location deux = new Location(world,x1,y1,z1);
					Location trois = new Location(world,x2,y2,z2);
					Location quatre = new Location(world,x3,y3,z3);
					Block b1 = un.getBlock();
					Block b2 = deux.getBlock();
					Block b3 = trois.getBlock();
					Block b4 = quatre.getBlock();
					Ram ram = new Ram(core,b1, b2, b3, b4, 5, BlockFace.valueOf(yc.getString("Rams."+o+".face")));
					ram.openDoor();
				}
			}
		}
	}

}
