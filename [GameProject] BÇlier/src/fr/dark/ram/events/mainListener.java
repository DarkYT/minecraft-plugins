package fr.dark.ram.events;

import java.util.HashMap;
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

import fr.dark.ram.Belier;
import fr.dark.ram.Core;

public class mainListener implements Listener {

	Core core;
	Map<Location, String> pressplates = new HashMap<>();
	public mainListener(Core core) {this.core = core;}
	

	@EventHandler
	public void onPressure(PlayerInteractEvent e){
		
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
								if(yc.getString("Plates."+plates+"."+coords).equals(c)){
									String[] coord = yc.getString("Plates."+plates+"."+coords).split(";");
									World world = Bukkit.getWorld(yc.getString("Plates."+plates+".world"));
									double x = Double.parseDouble(coord[0]);
									double y = Double.parseDouble(coord[1]);
									double z = Double.parseDouble(coord[2]);
									pressplates.put(new Location(world, x,y,z), plates);
								}
							}
						}
					}
				}
				int nbr = 0;
				for(Location loc : pressplates.keySet()){
					if(pressplates.get(loc).equals(o)){
						nbr++;
					}
				}
				System.out.println(nbr);
				if(nbr == 4){
					String[] coords = yc.getString("Rams."+o+".body1.loc").split(";");
					String[] coords2 = yc.getString("Rams."+o+".body2.loc").split(";");
					String[] coords3 = yc.getString("Rams."+o+".body3.loc").split(";");
					String[] coords4 = yc.getString("Rams."+o+".body4.loc").split(";");
					String[] coords5 = yc.getString("Rams."+o+".body5.loc").split(";");
					String[] tail = yc.getString("Rams."+o+".tail.loc").split(";");
					String[] handle1 = yc.getString("Rams."+o+".handle1.loc").split(";");
					String[] handle2 = yc.getString("Rams."+o+".handle2.loc").split(";");
					String[] handle3 = yc.getString("Rams."+o+".handle3.loc").split(";");
					String[] handle4 = yc.getString("Rams."+o+".handle4.loc").split(";");
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
					double x4 = Double.parseDouble(coords5[0]);
					double y4 = Double.parseDouble(coords5[1]);
					double z4 = Double.parseDouble(coords5[2]);
					double x5 = Double.parseDouble(tail[0]);
					double y5 = Double.parseDouble(tail[1]);
					double z5 = Double.parseDouble(tail[2]);
					double x6 = Double.parseDouble(handle1[0]);
					double y6 = Double.parseDouble(handle1[1]);
					double z6 = Double.parseDouble(handle1[2]);
					double x7 = Double.parseDouble(handle2[0]);
					double y7 = Double.parseDouble(handle2[1]);
					double z7 = Double.parseDouble(handle2[2]);
					double x8 = Double.parseDouble(handle3[0]);
					double y8 = Double.parseDouble(handle3[1]);
					double z8 = Double.parseDouble(handle3[2]);
					double x9 = Double.parseDouble(handle4[0]);
					double y9 = Double.parseDouble(handle4[1]);
					double z9 = Double.parseDouble(handle4[2]);
					Location body1 = new Location(world, x, y, z);
					Location body2 = new Location(world, x1, y1, z1);
					Location body3 = new Location(world, x2, y2, z2);
					Location body4 = new Location(world, x3, y3, z3);
					Location body5 = new Location(world, x4, y4, z4);
					Location ltail = new Location(world, x5, y5, z5);
					Location lhandle1 = new Location(world, x6, y6, z6);
					Location lhandle2 = new Location(world, x7, y7, z7);
					Location lhandle3 = new Location(world, x8, y8, z8);
					Location lhandle4 = new Location(world, x9, y9, z9);
					Block b1 = body1.getBlock();
					Block b2 = body2.getBlock();
					Block b3 = body3.getBlock();
					Block b4 = body4.getBlock();
					Block b5 = body5.getBlock();
					Block b6 = ltail.getBlock();
					Block b7 = lhandle1.getBlock();
					Block b8 = lhandle2.getBlock();
					Block b9 = lhandle3.getBlock();
					Block b10 = lhandle4.getBlock();

					Belier belier = new Belier(core, b1,b2,b3,b4,b5,b6,b7,b8,b9,b10, BlockFace.valueOf(yc.getString("Rams."+o+".face")));
					belier.raise();
				}
			}
		}
	}
}
