package fr.redblock.doors.events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Lever;

import fr.redblock.doors.Core;
import fr.redblock.doors.Doors;
import fr.redblock.doors.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class mainListeners implements Listener {

	Core core;
	Utils utils;

	public mainListeners(Core core) {
		this.core = core;
		utils = new Utils(core);
	}
	
	@EventHandler
	public void onActivate(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getClickedBlock().getType().equals(Material.LEVER)) {
				BlockState state = e.getClickedBlock().getState();
				Lever block = (Lever) state.getData();
				Location loc = state.getLocation();
				Doors type = null;
				String door = "";
				for(String doors : core.getConfig().getConfigurationSection("Doors").getKeys(false)) {
					if(isDoor("Doors." + doors + ".Location", loc) == true) {
						type = Doors.valueOf(core.getConfig().getString("Doors." + doors + ".Type"));
						door = doors;
					}
				}
				if(type != null) {
					BlockFace face = block.getFacing();
					int size;
					int high;
					size = core.getConfig().getInt("Doors." + door + ".Size");
					high = core.getConfig().getInt("Doors." + door + ".High");
					if (block.isPowered()) {
						core.close(p, e.getClickedBlock(), type, face, size, high);
					} else {
						core.open(p, e.getClickedBlock(), type, face, size, high);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		ItemStack it = e.getItemInHand();
		if (it.getType().equals(Material.LEVER)) {
			if (it.getItemMeta().hasLore()) {
				String id = ChatColor.stripColor(it.getItemMeta().getDisplayName());
				Doors type = core.type.get(p.getName());
				if(type != null) {
					List<String> lore = it.getItemMeta().getLore();
					int size = Integer.parseInt(ChatColor.stripColor(lore.get(1)));
					int high = Integer.parseInt(ChatColor.stripColor(lore.get(3)));
					core.getConfig().set("Doors." + id + ".Type", type.toString());
					core.getConfig().set("Doors." + id + ".Size", size);
					core.getConfig().set("Doors." + id + ".High", high);
					core.getConfig().set("Doors." + id + ".Location.World", e.getBlock().getWorld().getName());
					core.getConfig().set("Doors." + id + ".Location.X", e.getBlock().getLocation().getX());
					core.getConfig().set("Doors." + id + ".Location.Y", e.getBlock().getLocation().getY());
					core.getConfig().set("Doors." + id + ".Location.Z", e.getBlock().getLocation().getZ());
					core.saveConfig();
					core.type.remove(p.getName());
					p.getInventory().getItemInMainHand().setAmount(0);
				}
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Location loc = e.getBlock().getLocation();
		for(String doors : core.getConfig().getConfigurationSection("Doors").getKeys(false)) {
			if(isDoor("Doors." + doors + ".Location", loc) == true) {
				core.getConfig().set("Doors." + doors, null);
				core.saveConfig();
			}
		}
	}
	
	public boolean isDoor(String path, Location loc) {
		World world = Bukkit.getWorld(core.getConfig().getString(path + ".World"));
		int x = core.getConfig().getInt(path + ".X");
		int y = core.getConfig().getInt(path + ".Y");
		int z = core.getConfig().getInt(path + ".Z");
		if(world.equals(loc.getWorld()) && x == loc.getX() && y == loc.getY() && z == loc.getZ()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	

	/*@SuppressWarnings("deprecation")
	*@EventHandler
	*public void onActivate(PlayerInteractEvent e) {
	*	World world = e.getPlayer().getWorld();
	*	if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
	*		if (e.getClickedBlock().getType().equals(Material.LEVER)) {
	*			BlockState state = e.getClickedBlock().getState();
	*			Lever block = (Lever) state.getData();
	*			if (block.isPowered() == true) {
	*				BlockFace face = block.getFacing();
	*				Location loc = e.getClickedBlock().getLocation();
	*				int size = (int) core.getConfig().getConfigurationSection("Doors").getKeys(false).size();
	*				switch (face) {
	*				case NORTH:
	*					loc.add(0,0,1);
	*					for(int i=0;i<size;i++){
	*						String t = core.getConfig().getDouble("Doors.Door"+i+".X")+";"+core.getConfig().getDouble("Doors.Door"+i+".Y")+";"+core.getConfig().getDouble("Doors.Door"+i+".Z");
	*						String r = loc.getX()+";"+loc.getY()+";"+(loc.getZ()-1);
	*						if(t.equals(r)){
	*							int L = core.getConfig().getInt("Doors.Door"+i+".L");
	*							int H = core.getConfig().getInt("Doors.Door"+i+".H");
	*							Double y = loc.getY()-2;
	*							String M = core.getConfig().getString("Doors.Door"+i+".M");
	*							Map<Location, Material> m = new HashMap<>();
	*							Map<Location, Material> m1 = new HashMap<>();
	*							switch(M){
	*							case "herse":
	*								if(core.cooldown.containsKey(e.getPlayer().getName())){
	*									
										int secondes = 15;
	*									long timeleft = ((core.cooldown.get(e.getPlayer().getName()) / 1000) + secondes) - (System.currentTimeMillis() / 1000);
	*									if(timeleft > 0){
	*										 e.getPlayer().sendMessage("�cIl vous reste �e"+timeleft+"s �cavant de pouvoir r�-ouvrir la porte");
	*										 return;
	*									}
	*									
	*								}
	*								
	*								core.cooldown.put(e.getPlayer().getName(), System.currentTimeMillis());
	*								for (int l = 0; l < L; l++) {
										for (int k = -2; k < H - 3; k++) {
	*										Block b = e.getClickedBlock().getLocation().add(l+1,k+1,1).getBlock();
	*										m.put(b.getLocation(), b.getType());
	*										core.data.put(b.getLocation(), b.getData());
											b.setType(Material.AIR);
	*									}
	*								}
	*								world.playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 5);
	*								break;
	*							case "levis":
									if(core.cooldown.containsKey(e.getPlayer().getName())){
	*									
	*									int secondes = 15;
	*									long timeleft = ((core.cooldown.get(e.getPlayer().getName()) / 1000) + secondes) - (System.currentTimeMillis() / 1000);
	*									if(timeleft > 0){
	*										 e.getPlayer().sendMessage("�cIl vous reste �e"+timeleft+"s �cavant de pouvoir r�-ouvrir la porte");
											 return;
	*									}
	*									
	*								}
	*								
	*								core.cooldown.put(e.getPlayer().getName(), System.currentTimeMillis());
									for (int l = 0; l < L; l++) {
										for (int k = -2; k < H - 2; k++) {
	*										Block b = e.getClickedBlock().getLocation().add(l+1,k+1,1).getBlock();
	*										m.put(b.getLocation(), b.getType());
	*										Location lo = b.getLocation().add(0,0,-H+(k+2));
	*										lo.setY(y);
	*										Block a = lo.getBlock();
	*										m1.put(a.getLocation(), a.getType());
	*										core.data.put(b.getLocation(), b.getData());
											core.data.put(a.getLocation(), a.getData());
	*										a.setType(b.getType());
	*										a.setData(b.getData());
	*										b.setType(Material.AIR);
	*									}
	*								}
	*								
									world.playSound(e.getPlayer().getLocation(), Sound.BLOCK_WOODEN_DOOR_OPEN, 1, 5);
	*								break;
	*							case "battante":
	*								break;
	*							default:
	*								break;
								}
	*							
	*							core.map.put(e.getClickedBlock().getLocation(), (HashMap<Location, Material>) m);
	*							core.levis.put(e.getClickedBlock().getLocation(), (HashMap<Location, Material>) m1);
	*							break;
	*						}
	*					}
	*					break;
					case SOUTH:
	*					loc.add(0,0,-1);
	*					for(int i=0;i<size;i++){
	*						String t = core.getConfig().getDouble("Doors.Door"+i+".X")+";"+core.getConfig().getDouble("Doors.Door"+i+".Y")+";"+core.getConfig().getDouble("Doors.Door"+i+".Z");
	*						String r = loc.getX()+";"+loc.getY()+";"+(loc.getZ()+1);
	*						if(t.equals(r)){
								int L = core.getConfig().getInt("Doors.Door"+i+".L");
	*							int H = core.getConfig().getInt("Doors.Door"+i+".H");
	*							Map<Location, Material> m = new HashMap<>();
	*							for (int l = 0; l < L; l++) {
	*								for (int k = -2; k < H - 2; k++) {
	*									Block b = e.getClickedBlock().getLocation().add(-1-l,k+1,-1).getBlock();
										m.put(b.getLocation(), b.getType());
	*									b.setType(Material.AIR);
									}
	*							}
	*							core.map.put(e.getClickedBlock().getLocation(), (HashMap<Location, Material>) m);
	*							break;
	*						}
						}
	*					break;
	*				case EAST:
	*					loc.add(-1,0,0);
						for(int i=0;i<size;i++){
	*						String t = core.getConfig().getDouble("Doors.Door"+i+".X")+";"+core.getConfig().getDouble("Doors.Door"+i+".Y")+";"+core.getConfig().getDouble("Doors.Door"+i+".Z");
	*						String r = (loc.getX()+1)+";"+loc.getY()+";"+loc.getZ();
	*						if(t.equals(r)){
	*							int L = core.getConfig().getInt("Doors.Door"+i+".L");
								int H = core.getConfig().getInt("Doors.Door"+i+".H");
	*							Map<Location, Material> m = new HashMap<>();
	*							for (int l = 0; l < L; l++) {
	*								for (int k = -2; k < H - 2; k++) {
	*									Block b = e.getClickedBlock().getLocation().add(-1,k+1,l+1).getBlock();
	*									m.put(b.getLocation(), b.getType());
	*									b.setType(Material.AIR);
	*								}
	*							}
								core.map.put(e.getClickedBlock().getLocation(), (HashMap<Location, Material>) m);
	*							break;
	*						}
	*					}
	*					break;
	*				case WEST:
	*					loc.add(1,0,0);
	*					for(int i=0;i<size;i++){
							String t = core.getConfig().getDouble("Doors.Door"+i+".X")+";"+core.getConfig().getDouble("Doors.Door"+i+".Y")+";"+core.getConfig().getDouble("Doors.Door"+i+".Z");
	*						String r = (loc.getX()-1)+";"+loc.getY()+";"+loc.getZ();
	*						if(t.equals(r)){
	*							int L = core.getConfig().getInt("Doors.Door"+i+".L");
	*							int H = core.getConfig().getInt("Doors.Door"+i+".H");
	*							Map<Location, Material> m = new HashMap<>();
								for (int l = 0; l < L; l++) {
	*								for (int k = -2; k < H - 2; k++) {
	*									Block b = e.getClickedBlock().getLocation().add(1,k+1,-1-l).getBlock();
	*									m.put(b.getLocation(), b.getType());
	*									b.setType(Material.AIR);
									}
	*							}
	*							core.map.put(e.getClickedBlock().getLocation(), (HashMap<Location, Material>) m);
								break;
	*						}
	*					}
	*					break;
	*				default:
	*					break;
	*				}
    *
	*}else{
	*				if(core.map.containsKey(e.getClickedBlock().getLocation())){
	*					Map<Location, Material> mp = core.map.get(e.getClickedBlock().getLocation());
	*					Map<Location, Material> mp1 = core.levis.get(e.getClickedBlock().getLocation());
	*					for(Location loc : mp.keySet()){
	*						Material mat = mp.get(loc);
	*						loc.getBlock().setType(mat);
	*						loc.getBlock().setData(core.data.get(loc));
	*						core.data.remove(loc);
	*					}
	*					for(Location loc : mp1.keySet()){
	*						Material mat = mp1.get(loc);
	*						loc.getBlock().setType(mat);
	*						loc.getBlock().setData(core.data.get(loc));
	*						core.data.remove(loc);
	*					}
	*					core.map.remove(e.getClickedBlock().getLocation());
	*					core.levis.remove(e.getClickedBlock().getLocation());
	*				}
	*			}
	*		}
	*	}else{
	*		return;
	*	}
	*}
	*/
	

	/*
	 * @EventHandler public void onPlace(BlockPlaceEvent e) { ItemStack it =
	 * e.getItemInHand(); if (it.getType().equals(Material.LEVER)) { if
	 * (it.getItemMeta().hasLore()) { List<String> lore =
	 * it.getItemMeta().getLore(); String l = lore.get(0);
	 * core.getConfig().set("Doors." + l + ".X", e.getBlock().getLocation().getX());
	 * core.getConfig().set("Doors." + l + ".Y", e.getBlock().getLocation().getY());
	 * core.getConfig().set("Doors." + l + ".Z", e.getBlock().getLocation().getZ());
	 * core.saveConfig();
	 * e.getPlayer().getInventory().getItemInMainHand().setType(Material.AIR); } }
	 *
	 *}
	 */
	
	/*@EventHandler
	*public void onBreak(BlockBreakEvent e){
	*	int size;
	*	if(core.isEmptyConfig()){
	*		size = 0;
	*	}else{
	*		size = (int) core.getConfig().getConfigurationSection("Doors").getKeys(false).size();
	*	}
	*	for(int i=0;i<size;i++){
	*		String t = core.getConfig().getDouble("Doors.Door"+i+".X")+";"+core.getConfig().getDouble("Doors.Door"+i+".Y")+";"+core.getConfig().getDouble("Doors.Door"+i+".Z");
	*		String r = e.getBlock().getLocation().getX()+";"+e.getBlock().getLocation().getY()+";"+e.getBlock().getLocation().getZ();
	*		if(t.equals(r)){
	*			core.getConfig().set("Doors.Door"+i, null);
	*			core.saveConfig();
	*			break;
	*		}
	*	}
	*}
	*/
}
