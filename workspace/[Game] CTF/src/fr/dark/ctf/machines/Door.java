package fr.dark.ctf.machines;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import fr.dark.ctf.enums.Doors;


public class Door {
	
	public Door() {
		
	}
	
	@SuppressWarnings("deprecation")
	public void open(Player p, Block block, Doors door, BlockFace face, int size, int high) {
		Location loc = block.getLocation();
		Double y = loc.getY() - 2;
		switch(door) {
		case Herse:
			switch(face) {
			case NORTH:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 3; k++) {
						Block b = block.getLocation().add(l + 1, k + 1, 1).getBlock();
						b.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 5);
				break;
			case SOUTH:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 3; k++) {
						Block b = block.getLocation().add(- l - 1, k + 1, -1).getBlock();
						b.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 5);
				break;
			case EAST:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 3; k++) {
						Block b = block.getLocation().add(-1, k + 1, l + 1).getBlock();
						b.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 5);
				break;
			case WEST:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 3; k++) {
						Block b = block.getLocation().add(1, k + 1, - l - 1).getBlock();
						b.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 5);
				break;
			default:
				break;
			}
			break;
		case Battante:
			break;
		case Levis:
			switch(face) {
			case NORTH:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 2; k++) {
						Block b = block.getLocation().add(l + 1, k + 1, 1).getBlock();
						Location lo = b.getLocation().add(0, 0, high + k);
						lo.setY(y);
						Block a = lo.getBlock();
						a.setType(b.getType());
						a.setData(b.getData());
						b.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, 3, 5);
				break;
			case SOUTH:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 2; k++) {
						Block b = block.getLocation().add(- l - 1, k + 1, -1).getBlock();
						Location lo = b.getLocation().add(0, 0, high + (k - 2) - 2);
						lo.setY(y);
						Block a = lo.getBlock();
						a.setType(b.getType());
						a.setData(b.getData());
						b.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, 3, 5);
				break;
			case EAST:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 2; k++) {
						Block b = block.getLocation().add(-1, k + 1, l + 1).getBlock();
						Location lo = b.getLocation().add(- high + (k + 2), 0, 0);
						lo.setY(y);
						Block a = lo.getBlock();
						a.setType(b.getType());
						a.setData(b.getData());
						b.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, 3, 5);
				break;
			case WEST:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 2; k++) {
						Block b = block.getLocation().add(1, k + 1, - l - 1).getBlock();
						Location lo = b.getLocation().add(high - (k + 2), 0, 0);
						lo.setY(y);
						Block a = lo.getBlock();
						a.setType(b.getType());
						a.setData(b.getData());
						b.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, 3, 5);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void close(Player p, Block block, Doors door, BlockFace face, int size, int high) {
		Location loc = block.getLocation();
		Double y = loc.getY() - 2;
		switch (door) {
		case Herse:
			switch (face) {
			case NORTH:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 3; k++) {
						Block b = block.getLocation().add(l + 1, k + 1, 1).getBlock();
						b.setType(Material.IRON_FENCE);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 5);
				break;
			case SOUTH:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 3; k++) {
						Block b = block.getLocation().add(- l - 1, k + 1, -1).getBlock();
						b.setType(Material.IRON_FENCE);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 5);
				break;
			case EAST:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 3; k++) {
						Block b = block.getLocation().add(-1, k + 1, l + 1).getBlock();
						b.setType(Material.IRON_FENCE);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 5);
				break;
			case WEST:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 3; k++) {
						Block b = block.getLocation().add(1, k + 1, - l - 1).getBlock();
						b.setType(Material.IRON_FENCE);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 5);
				break;
			default:
				break;
			}
		case Battante:
			break;
		case Levis:
			switch (face) {
			case NORTH:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 2; k++) {
						Block b = block.getLocation().add(l + 1, k + 1, 1).getBlock();
						Location lo = b.getLocation().add(0, 0, high + k);
						lo.setY(y);
						Block a = lo.getBlock();
						b.setType(a.getType());
						b.setData(a.getData());
						a.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_CLOSE, 3, 5);
				break;
			case SOUTH:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 2; k++) {
						Block b = block.getLocation().add(- l - 1, k + 1, -1).getBlock();
						Location lo = b.getLocation().add(0, 0, high + (k - 2) - 2);
						lo.setY(y);
						Block a = lo.getBlock();
						b.setType(a.getType());
						b.setData(a.getData());
						a.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_CLOSE, 3, 5);
				break;
			case EAST:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 2; k++) {
						Block b = block.getLocation().add(-1, k + 1, l + 1).getBlock();
						Location lo = b.getLocation().add(- high + (k + 2), 0, 0);
						lo.setY(y);
						Block a = lo.getBlock();
						b.setType(a.getType());
						b.setData(a.getData());
						a.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_CLOSE, 3, 5);
				break;
			case WEST:
				for (int l = 0; l < size; l++) {
					for (int k = -2; k < high - 2; k++) {
						Block b = block.getLocation().add(1, k + 1, - l - 1).getBlock();
						Location lo = b.getLocation().add(high - (k + 2), 0, 0);
						lo.setY(y);
						Block a = lo.getBlock();
						b.setType(a.getType());
						b.setData(a.getData());
						a.setType(Material.AIR);
					}
				}
				loc.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_CLOSE, 3, 5);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

}
