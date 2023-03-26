package fr.dark.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import fr.dark.classes.command.classCommand;
import fr.dark.classes.events.mainListener;
import fr.dark.classes.mainclass.Vocations;

public class Core extends JavaPlugin {

	public Map<Player, Vocations> vocations = new HashMap<>();
	public Map<String, Long> cooldown = new HashMap<>();
	public Map<String, Long> cooldown2 = new HashMap<>();
	public Map<String, Long> cooldown3 = new HashMap<>();

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new mainListener(this), this);
		getCommand("class").setExecutor(new classCommand(this));
	}

	@Override
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (vocations.containsKey(p)) {
				Vocations v = vocations.get(p);
				v.delete();
				this.vocations.remove(p);
			}
		}
	}

	public BlockFace CardinalDirection(Player p) {
		float yaw = p.getLocation().getYaw();
		if (yaw < 0) {
			yaw += 360;
		}
		if (yaw >= 315 || yaw < 45) {
			return BlockFace.SOUTH;
		} else if (yaw < 135) {
			return BlockFace.WEST;
		} else if (yaw < 225) {
			return BlockFace.NORTH;
		} else if (yaw < 315) {
			return BlockFace.EAST;
		}
		return BlockFace.NORTH;
	}

	public ItemStack createItem(Material mat, String name, Enchantment ench, int enchLvl, List<String> lore) {
		if (ench == null) {
			ItemStack it = new ItemStack(mat, 1);
			ItemMeta itm = it.getItemMeta();
			itm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			itm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			itm.setLore(lore);
			itm.setDisplayName(name);
			it.setItemMeta(itm);
			return it;
		} else {
			ItemStack it = new ItemStack(mat, 1);
			ItemMeta itm = it.getItemMeta();
			itm.setDisplayName(name);
			itm.addEnchant(ench, enchLvl, true);
			itm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			itm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			itm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			itm.setLore(lore);
			it.setItemMeta(itm);
			return it;
		}
	}

	public Entity getNearestEntityInSight(Player player, int range) {
		ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(range, range, range);
		ArrayList<Block> sightBlock = (ArrayList<Block>) player.getLineOfSight((Set<Material>) null, range);
		ArrayList<Location> sight = new ArrayList<Location>();
		for (int i = 0; i < sightBlock.size(); i++)
			sight.add(sightBlock.get(i).getLocation());
		for (int i = 0; i < sight.size(); i++) {
			for (int k = 0; k < entities.size(); k++) {
				if (Math.abs(entities.get(k).getLocation().getX() - sight.get(i).getX()) < 1.3) {
					if (Math.abs(entities.get(k).getLocation().getY() - sight.get(i).getY()) < 1.5) {
						if (Math.abs(entities.get(k).getLocation().getZ() - sight.get(i).getZ()) < 1.3) {
							return entities.get(k);
						}
					}
				}
			}
		}
		return null; // Return null/nothing if no entity was found
	}

	public Vector rotateYAxis(Vector dir, double angleD) {
		double angleR = Math.toRadians(angleD);
		double x = dir.getX();
		double z = dir.getZ();
		double cos = Math.cos(angleR);
		double sin = Math.sin(angleR);
		return (new Vector(x * cos + z * (-sin), 0.0, x * sin + z * cos)).normalize();
	}

	public void shoot(Player player, Location location, Vector direction, int[] angles, double speed) {
		direction.normalize();
		Vector dirY = (new Location(location.getWorld(), 0, 0, 0, location.getYaw(), 0)).getDirection().normalize();
		for (int angle : angles) {
			Vector vec;
			if (angle != 0) {
				vec = rotateYAxis(dirY, angle);
				vec.multiply(Math.sqrt(vec.getX() * vec.getX() + vec.getZ() * vec.getZ())).subtract(dirY);
				vec = direction.clone().add(vec).normalize();
			} else {
				vec = direction.clone();
			}
			Arrow snowball = location.getWorld().spawn(location, Arrow.class);
			snowball.setShooter(player);
			snowball.setVelocity(vec.clone().multiply(speed));
		}
	}

	public void spawnFireworks(Location location) {
		Location loc = location;
		Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();

		fwm.setPower(2);
		fwm.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());

		fw.setFireworkMeta(fwm);
		Bukkit.getScheduler().runTaskLater(this, new Runnable() {

			@Override
			public void run() {
				fw.detonate();
			}

		}, 2);
	}

}
