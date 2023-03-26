package fr.dark.ram;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Ram {

	Block body1, body2, body3, tail;
	int nbrHit;
	BlockFace bf;
	Core core;

	public Ram(Core core, Block body1, Block body2, Block body3, Block tail, int nbrHit, BlockFace bf) {
		this.core = core;
		this.body1 = body1;
		this.body2 = body2;
		this.body3 = body3;
		this.tail = tail;
		this.nbrHit = nbrHit;
		this.bf = bf;
	}

	@SuppressWarnings("deprecation")
	public void openDoor() {
		Material m1 = body1.getType();
		byte B1 = body1.getData();
		Material m2 = body2.getType();
		byte B2 = body2.getData();
		Material m3 = body3.getType();
		byte B3 = body3.getData();
		Material m4 = tail.getType();
		byte B4 = tail.getData();
		World w = body1.getWorld();
		Location base1 = new Location(body1.getWorld(), body1.getX(), body1.getY() + 1, body1.getZ());
		Location base2 = new Location(body2.getWorld(), body2.getX(), body2.getY() + 1, body2.getZ());
		Location base3 = new Location(body3.getWorld(), body3.getX(), body3.getY() + 1, body3.getZ());
		Location base4 = new Location(tail.getWorld(), tail.getX(), tail.getY() + 1, tail.getZ());
		
		Location socle1 = new Location(body1.getWorld(), body1.getX(), body1.getY(), body1.getZ());
		Location socle2 = new Location(body2.getWorld(), body2.getX(), body2.getY(), body2.getZ());
		Location socle3 = new Location(body3.getWorld(), body3.getX(), body3.getY(), body3.getZ());
		Location socle4 = new Location(tail.getWorld(), tail.getX(), tail.getY(), tail.getZ());

		core.setBlockLocation(m1, B1, body1, base1);
		core.setBlockLocation(m2, B2, body2, base2);
		core.setBlockLocation(m3, B3, body3, base3);
		core.setBlockLocation(m4, B4, tail, base4);


		switch (bf) {
		case NORTH:
			Location sFo1 = new Location(body1.getWorld(), body1.getX(), body1.getY() + 1, body1.getZ()-1);
			Location sFo2 = new Location(body2.getWorld(), body2.getX(), body2.getY() + 1, body2.getZ()-1);
			Location sFo3 = new Location(body3.getWorld(), body3.getX(), body3.getY() + 1, body3.getZ()-1);
			Location sFo4 = new Location(tail.getWorld(), tail.getX(), tail.getY() + 1, tail.getZ()-1);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, sFo1);
					core.setBlockLocation(m2, B2, body2, sFo2);
					core.setBlockLocation(m3, B3, body3, sFo3);
					core.setBlockLocation(m4, B4, tail, sFo4);
					w.playSound(sFo1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 20);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					sFo1.getBlock().setType(Material.AIR);
					sFo2.getBlock().setType(Material.AIR);
					sFo3.getBlock().setType(Material.AIR);
					sFo4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, base1);
					core.setBlockLocation(m2, B2, body2, base2);
					core.setBlockLocation(m3, B3, body3, base3);
					core.setBlockLocation(m4, B4, tail, base4);
				}
			}, 40);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, sFo1);
					core.setBlockLocation(m2, B2, body2, sFo2);
					core.setBlockLocation(m3, B3, body3, sFo3);
					core.setBlockLocation(m4, B4, tail, sFo4);
					w.playSound(sFo1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 60);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					sFo1.getBlock().setType(Material.AIR);
					sFo2.getBlock().setType(Material.AIR);
					sFo3.getBlock().setType(Material.AIR);
					sFo4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, base1);
					core.setBlockLocation(m2, B2, body2, base2);
					core.setBlockLocation(m3, B3, body3, base3);
					core.setBlockLocation(m4, B4, tail, base4);
				}
			}, 80);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, sFo1);
					core.setBlockLocation(m2, B2, body2, sFo2);
					core.setBlockLocation(m3, B3, body3, sFo3);
					core.setBlockLocation(m4, B4, tail, sFo4);

				}
			}, 100);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					sFo1.getBlock().setType(Material.AIR);
					sFo2.getBlock().setType(Material.AIR);
					sFo3.getBlock().setType(Material.AIR);
					sFo4.getBlock().setType(Material.AIR);
					socle1.getBlock().setType(m1);
					socle1.getBlock().setData(B1);
					socle2.getBlock().setType(m2);
					socle2.getBlock().setData(B2);
					socle3.getBlock().setType(m3);
					socle3.getBlock().setData(B3);
					socle4.getBlock().setType(m4);
					socle4.getBlock().setData(B4);
				}
			}, 120);
			
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					Location door1 = new Location(body1.getWorld(), body1.getX(), body1.getY() + 1, body1.getZ()-2);
					Location door2 = new Location(body1.getWorld(), body1.getX(), body1.getY() + 2, body1.getZ()-2);
					World world = door1.getWorld();
					door1.getBlock().setType(Material.AIR);
					door2.getBlock().setType(Material.AIR);
					world.playSound(door1, Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1, 5);
				}
			}, 100);
			break;
		case SOUTH:
			Location nFo1 = new Location(body1.getWorld(), body1.getX(), body1.getY() + 1, body1.getZ()+1);
			Location nFo2 = new Location(body2.getWorld(), body2.getX(), body2.getY() + 1, body2.getZ()+1);
			Location nFo3 = new Location(body3.getWorld(), body3.getX(), body3.getY() + 1, body3.getZ()+1);
			Location nFo4 = new Location(tail.getWorld(), tail.getX(), tail.getY() + 1, tail.getZ()+1);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, nFo1);
					core.setBlockLocation(m2, B2, body2, nFo2);
					core.setBlockLocation(m3, B3, body3, nFo3);
					core.setBlockLocation(m4, B4, tail, nFo4);
					w.playSound(nFo1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 20);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					nFo1.getBlock().setType(Material.AIR);
					nFo2.getBlock().setType(Material.AIR);
					nFo3.getBlock().setType(Material.AIR);
					nFo4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, base1);
					core.setBlockLocation(m2, B2, body2, base2);
					core.setBlockLocation(m3, B3, body3, base3);
					core.setBlockLocation(m4, B4, tail, base4);
				}
			}, 40);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, nFo1);
					core.setBlockLocation(m2, B2, body2, nFo2);
					core.setBlockLocation(m3, B3, body3, nFo3);
					core.setBlockLocation(m4, B4, tail, nFo4);
					w.playSound(nFo1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 60);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					nFo1.getBlock().setType(Material.AIR);
					nFo2.getBlock().setType(Material.AIR);
					nFo3.getBlock().setType(Material.AIR);
					nFo4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, base1);
					core.setBlockLocation(m2, B2, body2, base2);
					core.setBlockLocation(m3, B3, body3, base3);
					core.setBlockLocation(m4, B4, tail, base4);
				}
			}, 80);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, nFo1);
					core.setBlockLocation(m2, B2, body2, nFo2);
					core.setBlockLocation(m3, B3, body3, nFo3);
					core.setBlockLocation(m4, B4, tail, nFo4);

				}
			}, 100);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					nFo1.getBlock().setType(Material.AIR);
					nFo2.getBlock().setType(Material.AIR);
					nFo3.getBlock().setType(Material.AIR);
					nFo4.getBlock().setType(Material.AIR);
					socle1.getBlock().setType(m1);
					socle1.getBlock().setData(B1);
					socle2.getBlock().setType(m2);
					socle2.getBlock().setData(B2);
					socle3.getBlock().setType(m3);
					socle3.getBlock().setData(B3);
					socle4.getBlock().setType(m4);
					socle4.getBlock().setData(B4);
				}
			}, 120);
			
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					Location door1 = new Location(body1.getWorld(), body1.getX(), body1.getY() + 1, body1.getZ()+2);
					Location door2 = new Location(body1.getWorld(), body1.getX(), body1.getY() + 2, body1.getZ()+2);
					World world = door1.getWorld();
					door1.getBlock().setType(Material.AIR);
					door2.getBlock().setType(Material.AIR);
					world.playSound(door1, Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1, 5);
				}
			}, 100);
			break;
		case EAST:
			Location eFo1 = new Location(body1.getWorld(), body1.getX()+1, body1.getY() + 1, body1.getZ());
			Location eFo2 = new Location(body2.getWorld(), body2.getX()+1, body2.getY() + 1, body2.getZ());
			Location eFo3 = new Location(body3.getWorld(), body3.getX()+1, body3.getY() + 1, body3.getZ());
			Location eFo4 = new Location(tail.getWorld(), tail.getX()+1, tail.getY() + 1, tail.getZ());
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, eFo1);
					core.setBlockLocation(m2, B2, body2, eFo2);
					core.setBlockLocation(m3, B3, body3, eFo3);
					core.setBlockLocation(m4, B4, tail, eFo4);
					w.playSound(eFo1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 20);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					eFo1.getBlock().setType(Material.AIR);
					eFo2.getBlock().setType(Material.AIR);
					eFo3.getBlock().setType(Material.AIR);
					eFo4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, base1);
					core.setBlockLocation(m2, B2, body2, base2);
					core.setBlockLocation(m3, B3, body3, base3);
					core.setBlockLocation(m4, B4, tail, base4);
				}
			}, 40);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, eFo1);
					core.setBlockLocation(m2, B2, body2, eFo2);
					core.setBlockLocation(m3, B3, body3, eFo3);
					core.setBlockLocation(m4, B4, tail, eFo4);
					w.playSound(eFo1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 60);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					eFo1.getBlock().setType(Material.AIR);
					eFo2.getBlock().setType(Material.AIR);
					eFo3.getBlock().setType(Material.AIR);
					eFo4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, base1);
					core.setBlockLocation(m2, B2, body2, base2);
					core.setBlockLocation(m3, B3, body3, base3);
					core.setBlockLocation(m4, B4, tail, base4);
				}
			}, 80);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, eFo1);
					core.setBlockLocation(m2, B2, body2, eFo2);
					core.setBlockLocation(m3, B3, body3, eFo3);
					core.setBlockLocation(m4, B4, tail, eFo4);

				}
			}, 100);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					eFo1.getBlock().setType(Material.AIR);
					eFo2.getBlock().setType(Material.AIR);
					eFo3.getBlock().setType(Material.AIR);
					eFo4.getBlock().setType(Material.AIR);
					socle1.getBlock().setType(m1);
					socle1.getBlock().setData(B1);
					socle2.getBlock().setType(m2);
					socle2.getBlock().setData(B2);
					socle3.getBlock().setType(m3);
					socle3.getBlock().setData(B3);
					socle4.getBlock().setType(m4);
					socle4.getBlock().setData(B4);
				}
			}, 120);
			
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					Location door1 = new Location(body1.getWorld(), body1.getX()+2, body1.getY() + 1, body1.getZ());
					Location door2 = new Location(body1.getWorld(), body1.getX()+2, body1.getY() + 2, body1.getZ());
					World world = door1.getWorld();
					door1.getBlock().setType(Material.AIR);
					door2.getBlock().setType(Material.AIR);
					world.playSound(door1, Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1, 5);
				}
			}, 100);
			break;
		case WEST:
			Location wFo1 = new Location(body1.getWorld(), body1.getX()-1, body1.getY() + 1, body1.getZ());
			Location wFo2 = new Location(body2.getWorld(), body2.getX()-1, body2.getY() + 1, body2.getZ());
			Location wFo3 = new Location(body3.getWorld(), body3.getX()-1, body3.getY() + 1, body3.getZ());
			Location wFo4 = new Location(tail.getWorld(), tail.getX()-1, tail.getY() + 1, tail.getZ());
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, wFo1);
					core.setBlockLocation(m2, B2, body2, wFo2);
					core.setBlockLocation(m3, B3, body3, wFo3);
					core.setBlockLocation(m4, B4, tail, wFo4);
					w.playSound(wFo1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);

				}
			}, 20);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					wFo1.getBlock().setType(Material.AIR);
					wFo2.getBlock().setType(Material.AIR);
					wFo3.getBlock().setType(Material.AIR);
					wFo4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, base1);
					core.setBlockLocation(m2, B2, body2, base2);
					core.setBlockLocation(m3, B3, body3, base3);
					core.setBlockLocation(m4, B4, tail, base4);
				}
			}, 40);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, wFo1);
					core.setBlockLocation(m2, B2, body2, wFo2);
					core.setBlockLocation(m3, B3, body3, wFo3);
					core.setBlockLocation(m4, B4, tail, wFo4);
					w.playSound(wFo1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 60);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					wFo1.getBlock().setType(Material.AIR);
					wFo2.getBlock().setType(Material.AIR);
					wFo3.getBlock().setType(Material.AIR);
					wFo4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, base1);
					core.setBlockLocation(m2, B2, body2, base2);
					core.setBlockLocation(m3, B3, body3, base3);
					core.setBlockLocation(m4, B4, tail, base4);
				}
			}, 80);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					base1.getBlock().setType(Material.AIR);
					base2.getBlock().setType(Material.AIR);
					base3.getBlock().setType(Material.AIR);
					base4.getBlock().setType(Material.AIR);
					core.setBlockLocation(m1, B1, body1, wFo1);
					core.setBlockLocation(m2, B2, body2, wFo2);
					core.setBlockLocation(m3, B3, body3, wFo3);
					core.setBlockLocation(m4, B4, tail, wFo4);
				}
			}, 100);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					wFo1.getBlock().setType(Material.AIR);
					wFo2.getBlock().setType(Material.AIR);
					wFo3.getBlock().setType(Material.AIR);
					wFo4.getBlock().setType(Material.AIR);
					socle1.getBlock().setType(m1);
					socle1.getBlock().setData(B1);
					socle2.getBlock().setType(m2);
					socle2.getBlock().setData(B2);
					socle3.getBlock().setType(m3);
					socle3.getBlock().setData(B3);
					socle4.getBlock().setType(m4);
					socle4.getBlock().setData(B4);
				}
			}, 120);
			
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					Location door1 = new Location(body1.getWorld(), body1.getX()-2, body1.getY() + 1, body1.getZ());
					Location door2 = new Location(body1.getWorld(), body1.getX()-2, body1.getY() + 2, body1.getZ());
					World world = door1.getWorld();
					door1.getBlock().setType(Material.AIR);
					door2.getBlock().setType(Material.AIR);
					world.playSound(door1, Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1, 5);
				}
			}, 100);
			
			
			break;
		default:
			break;
		}
	}
}
