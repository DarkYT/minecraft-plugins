package fr.dark.catapulte.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.dark.catapulte.Catapulte;
import fr.dark.catapulte.Core;

public class mainListener implements Listener {

	Core core;
	List<Catapulte> catapultes = new ArrayList<>();
	public mainListener(Core core) {this.core = core;}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		if(e.getBlock().getType().equals(Material.STONE_PLATE)){
			if(e.getItemInHand().getItemMeta().getDisplayName().equals("§8Catapulte")){
				YamlConfiguration yc = YamlConfiguration.loadConfiguration(Core.confFile);
				String loc = e.getBlock().getLocation().getBlockX()+";"+e.getBlock().getLocation().getBlockY()+";"+e.getBlock().getLocation().getBlockZ();
				yc.set("Catapulte.Cat/"+e.getBlock().getLocation().getBlockX()+""+e.getBlock().getLocation().getBlockY()+""+e.getBlock().getLocation().getBlockZ(), loc);
				core.saveCustomYml(yc, Core.confFile);
				String s = e.getItemInHand().getItemMeta().getLore().get(0);
				s = s.toUpperCase();
				BlockFace bf = BlockFace.valueOf(s);
				Catapulte cat = new Catapulte(bf, e.getBlock().getLocation(), core);
				catapultes.add(cat);
				cat.spawn();
			}
		}
	}
	
	@EventHandler
	public void onDispawn(BlockBreakEvent e){
		e.setDropItems(false);
	}
	
	@EventHandler
	public void onHit(ProjectileHitEvent e){
		if(e.getEntity() instanceof Snowball){
			if(e.getHitBlock() instanceof Block){
				e.getHitBlock().getWorld().createExplosion(e.getHitBlock().getLocation(), 8.0F,true);
			}
		}
	}
	
	@EventHandler
	public void onLaunch(PlayerInteractEvent e){
		if(e.getClickedBlock().getType().equals(Material.LEVER) && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			for(int i = 0; i < catapultes.size(); i++){
				if(catapultes.get(i).getLever().equals(e.getClickedBlock().getLocation())){
					Catapulte c = catapultes.get(i);
					if(!(c.isLaunched())){
						c.launch();
						switch(c.getFacing()){
						case NORTH:
							Location l = new Location(c.getLaunching().getWorld(), c.getLaunching().getX(), c.getLaunching().getY(), c.getLaunching().getZ()-3, 180F, 0F);
							Zombie z = c.getLaunching().getWorld().spawn(l, Zombie.class);
							z.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
							int[] i2 = {0};
							core.shoot(z, z.getEyeLocation(), z.getEyeLocation().getDirection(), i2, 40.0D);
							z.setHealth(0.0D);
							break;
						case SOUTH:
							Location l1 = new Location(c.getLaunching().getWorld(), c.getLaunching().getX(), c.getLaunching().getY(), c.getLaunching().getZ()+3, 0F, 0F);
							Zombie z1 = c.getLaunching().getWorld().spawn(l1, Zombie.class);
							z1.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
							int[] i1 = {0};
							core.shoot(z1, z1.getEyeLocation(), z1.getEyeLocation().getDirection(), i1, 40.0D);
							z1.setHealth(0.0D);
							break;
						case EAST:
							Location l2 = new Location(c.getLaunching().getWorld(), c.getLaunching().getX()+3, c.getLaunching().getY(), c.getLaunching().getZ(), -90F, 0F);
							Zombie z2 = c.getLaunching().getWorld().spawn(l2, Zombie.class);
							z2.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
							int[] i3 = {0};
							core.shoot(z2, z2.getEyeLocation(), z2.getEyeLocation().getDirection(), i3, 40.0D);
							z2.setHealth(0.0D);
							break;
						case WEST:
							Location l3 = new Location(c.getLaunching().getWorld(), c.getLaunching().getX()-3, c.getLaunching().getY(), c.getLaunching().getZ(), 90F, 0F);
							Zombie z3 = c.getLaunching().getWorld().spawn(l3, Zombie.class);
							z3.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
							int[] i4 = {0};
							core.shoot(z3, z3.getEyeLocation(), z3.getEyeLocation().getDirection(), i4, 40.0D);
							z3.setHealth(0.0D);
							break;
						default:
							break;
						}
					}else{
						c.reset();
					}
					break;
				}
			}
		}else{
			return;
		}
	}

}
