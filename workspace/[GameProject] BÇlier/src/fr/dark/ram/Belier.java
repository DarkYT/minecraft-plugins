package fr.dark.ram;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Belier {
	
	Core core;
	Block body1, body2, body3, body4, body5, body6, body7, body8, body9, body10;
	BlockFace bF;
	
	public Belier(Core core, Block b1, Block b2, Block b3, Block b4, Block b5, Block b6, Block b7, Block b8, Block b9, Block b10, BlockFace bF){
		this.core = core;
		this.body1 = b1;
		this.body2 = b2;
		this.body3 = b3;
		this.body4 = b4;
		this.body5 = b5;
		this.body6 = b6;
		this.body7 = b7;
		this.body8 = b8;
		this.body9 = b9;
		this.body10 = b10;
		this.bF = bF;
	}
	
	@SuppressWarnings("deprecation")
	private void setLocation(Material m, byte d, Block b, Location nL){
		nL.getBlock().setType(m);
		nL.getBlock().setData(d);
		nL.getBlock().setBiome(b.getBiome());
		b.setType(Material.AIR);
	}
	
	@SuppressWarnings("deprecation")
	public void raise(){
		Material m1 = body1.getType();
		byte b1 = body1.getData();
		Material m2 = body2.getType();
		byte b2 = body2.getData();
		Material m3 = body3.getType();
		byte b3 = body3.getData();
		Material m4 = body4.getType();
		byte b4 = body4.getData();
		Material m5 = body5.getType();
		byte b5 = body5.getData();
		Material m6 = body6.getType();
		byte b6 = body6.getData();
		Material m7 = body7.getType();
		byte b7 = body7.getData();
		Material m8 = body8.getType();
		byte b8 = body8.getData();
		Material m9 = body9.getType();
		byte b9 = body9.getData();
		Material m10 = body10.getType();
		byte b10 = body10.getData();
		
		World w = body1.getWorld();
		
		Location up1 = new Location(body1.getWorld(), body1.getX(), body1.getY()+1, body1.getZ());
		Location up2 = new Location(body2.getWorld(), body2.getX(), body2.getY()+1, body2.getZ());
		Location up3 = new Location(body3.getWorld(), body3.getX(), body3.getY()+1, body3.getZ());
		Location up4 = new Location(body4.getWorld(), body4.getX(), body4.getY()+1, body4.getZ());
		Location up5 = new Location(body5.getWorld(), body5.getX(), body5.getY()+1, body5.getZ());
		Location up6 = new Location(body6.getWorld(), body6.getX(), body6.getY()+1, body6.getZ());
		Location up7 = new Location(body7.getWorld(), body7.getX(), body7.getY()+1, body7.getZ());
		Location up8 = new Location(body8.getWorld(), body8.getX(), body8.getY()+1, body8.getZ());
		Location up9 = new Location(body9.getWorld(), body9.getX(), body9.getY()+1, body9.getZ());
		Location up10 = new Location(body10.getWorld(), body10.getX(), body10.getY()+1, body10.getZ());
		
		Location socle1 = new Location(body1.getWorld(), body1.getX(), body1.getY(), body1.getZ());
		Location socle2 = new Location(body2.getWorld(), body2.getX(), body2.getY(), body2.getZ());
		Location socle3 = new Location(body3.getWorld(), body3.getX(), body3.getY(), body3.getZ());
		Location socle4 = new Location(body4.getWorld(), body4.getX(), body4.getY(), body4.getZ());
		Location socle5 = new Location(body5.getWorld(), body5.getX(), body5.getY(), body5.getZ());
		Location socle6 = new Location(body6.getWorld(), body6.getX(), body6.getY(), body6.getZ());
		Location socle7 = new Location(body7.getWorld(), body7.getX(), body7.getY(), body7.getZ());
		Location socle8 = new Location(body8.getWorld(), body8.getX(), body8.getY(), body8.getZ());
		Location socle9 = new Location(body9.getWorld(), body9.getX(), body9.getY(), body9.getZ());
		Location socle10 = new Location(body10.getWorld(), body10.getX(), body10.getY(), body10.getZ());
		
		setLocation(m1, b1, body1, up1);
		setLocation(m2, b2, body2, up2);
		setLocation(m3, b3, body3, up3);
		setLocation(m4, b4, body4, up4);
		setLocation(m5, b5, body5, up5);
		setLocation(m6, b6, body6, up6);
		setLocation(m7, b7, body7, up7);
		setLocation(m8, b8, body8, up8);
		setLocation(m9, b9, body9, up9);
		setLocation(m10, b10, body10, up10);
		
		switch(bF){
		case NORTH:
			Location n1 = new Location(body1.getWorld(), body1.getX(), body1.getY()+1, body1.getZ()-1);
			Location n2 = new Location(body2.getWorld(), body2.getX(), body2.getY()+1, body2.getZ()-1);
			Location n3 = new Location(body3.getWorld(), body3.getX(), body3.getY()+1, body3.getZ()-1);
			Location n4 = new Location(body4.getWorld(), body4.getX(), body4.getY()+1, body4.getZ()-1);
			Location n5 = new Location(body5.getWorld(), body5.getX(), body5.getY()+1, body5.getZ()-1);
			Location n6 = new Location(body6.getWorld(), body6.getX(), body6.getY()+1, body6.getZ()-1);
			Location n7 = new Location(body7.getWorld(), body7.getX(), body7.getY()+1, body7.getZ()-1);
			Location n8 = new Location(body8.getWorld(), body8.getX(), body8.getY()+1, body8.getZ()-1);
			Location n9 = new Location(body9.getWorld(), body9.getX(), body9.getY()+1, body9.getZ()-1);
			Location n10 = new Location(body10.getWorld(), body10.getX(), body10.getY()+1, body10.getZ()-1);
			
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, n1);
					setLocation(m2, b2, body2, n2);
					setLocation(m3, b3, body3, n3);
					setLocation(m4, b4, body4, n4);
					setLocation(m5, b5, body1, n5);
					setLocation(m6, b6, body2, n6);
					setLocation(m7, b7, body3, n7);
					setLocation(m8, b8, body4, n8);
					setLocation(m9, b9, body1, n9);
					setLocation(m10, b10, body10, n10);
					w.playSound(n1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 40);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					n1.getBlock().setType(Material.AIR);
					n2.getBlock().setType(Material.AIR);
					n3.getBlock().setType(Material.AIR);
					n4.getBlock().setType(Material.AIR);
					n5.getBlock().setType(Material.AIR);
					n6.getBlock().setType(Material.AIR);
					n7.getBlock().setType(Material.AIR);
					n8.getBlock().setType(Material.AIR);
					n9.getBlock().setType(Material.AIR);
					n10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 80);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, n1);
					setLocation(m2, b2, body2, n2);
					setLocation(m3, b3, body3, n3);
					setLocation(m4, b4, body4, n4);
					setLocation(m5, b5, body1, n5);
					setLocation(m6, b6, body2, n6);
					setLocation(m7, b7, body3, n7);
					setLocation(m8, b8, body4, n8);
					setLocation(m9, b9, body1, n9);
					setLocation(m10, b10, body10, n10);
					w.playSound(n1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 120);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					n1.getBlock().setType(Material.AIR);
					n2.getBlock().setType(Material.AIR);
					n3.getBlock().setType(Material.AIR);
					n4.getBlock().setType(Material.AIR);
					n5.getBlock().setType(Material.AIR);
					n6.getBlock().setType(Material.AIR);
					n7.getBlock().setType(Material.AIR);
					n8.getBlock().setType(Material.AIR);
					n9.getBlock().setType(Material.AIR);
					n10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 160);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, n1);
					setLocation(m2, b2, body2, n2);
					setLocation(m3, b3, body3, n3);
					setLocation(m4, b4, body4, n4);
					setLocation(m5, b5, body1, n5);
					setLocation(m6, b6, body2, n6);
					setLocation(m7, b7, body3, n7);
					setLocation(m8, b8, body4, n8);
					setLocation(m9, b9, body1, n9);
					setLocation(m10, b10, body10, n10);
					w.playSound(n1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 200);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					n1.getBlock().setType(Material.AIR);
					n2.getBlock().setType(Material.AIR);
					n3.getBlock().setType(Material.AIR);
					n4.getBlock().setType(Material.AIR);
					n5.getBlock().setType(Material.AIR);
					n6.getBlock().setType(Material.AIR);
					n7.getBlock().setType(Material.AIR);
					n8.getBlock().setType(Material.AIR);
					n9.getBlock().setType(Material.AIR);
					n10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 240);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, n1);
					setLocation(m2, b2, body2, n2);
					setLocation(m3, b3, body3, n3);
					setLocation(m4, b4, body4, n4);
					setLocation(m5, b5, body1, n5);
					setLocation(m6, b6, body2, n6);
					setLocation(m7, b7, body3, n7);
					setLocation(m8, b8, body4, n8);
					setLocation(m9, b9, body1, n9);
					setLocation(m10, b10, body10, n10);
					w.playSound(n1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 280);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					n1.getBlock().setType(Material.AIR);
					n2.getBlock().setType(Material.AIR);
					n3.getBlock().setType(Material.AIR);
					n4.getBlock().setType(Material.AIR);
					n5.getBlock().setType(Material.AIR);
					n6.getBlock().setType(Material.AIR);
					n7.getBlock().setType(Material.AIR);
					n8.getBlock().setType(Material.AIR);
					n9.getBlock().setType(Material.AIR);
					n10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 320);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, n1);
					setLocation(m2, b2, body2, n2);
					setLocation(m3, b3, body3, n3);
					setLocation(m4, b4, body4, n4);
					setLocation(m5, b5, body1, n5);
					setLocation(m6, b6, body2, n6);
					setLocation(m7, b7, body3, n7);
					setLocation(m8, b8, body4, n8);
					setLocation(m9, b9, body1, n9);
					setLocation(m10, b10, body10, n10);
					Location door1 = new Location(body1.getWorld(), body1.getX(), body1.getY(), body1.getZ()-2);
					Location door2 = new Location(body1.getWorld(), body1.getX(), body1.getY() + 1, body1.getZ()-2);
					Location door3 = new Location(body1.getWorld(), body1.getX(), body1.getY() + 2, body1.getZ()-2);
					Location door4 = new Location(body1.getWorld(), body1.getX()+1, body1.getY(), body1.getZ()-2);
					Location door5 = new Location(body1.getWorld(), body1.getX()+1, body1.getY() + 1, body1.getZ()-2);
					Location door6 = new Location(body1.getWorld(), body1.getX()+1, body1.getY() + 2, body1.getZ()-2);
					Location door7 = new Location(body1.getWorld(), body1.getX()-1, body1.getY(), body1.getZ()-2);
					Location door8 = new Location(body1.getWorld(), body1.getX()-1, body1.getY() + 1, body1.getZ()-2);
					Location door9 = new Location(body1.getWorld(), body1.getX()-1, body1.getY() + 2, body1.getZ()-2);
					World world = door1.getWorld();
					door1.getBlock().setType(Material.AIR);
					door2.getBlock().setType(Material.AIR);
					door3.getBlock().setType(Material.AIR);
					door4.getBlock().setType(Material.AIR);
					door5.getBlock().setType(Material.AIR);
					door6.getBlock().setType(Material.AIR);
					door7.getBlock().setType(Material.AIR);
					door8.getBlock().setType(Material.AIR);
					door9.getBlock().setType(Material.AIR);
					world.playSound(door1, Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1, 5);
				}
			}, 360);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					n1.getBlock().setType(Material.AIR);
					n2.getBlock().setType(Material.AIR);
					n3.getBlock().setType(Material.AIR);
					n4.getBlock().setType(Material.AIR);
					n5.getBlock().setType(Material.AIR);
					n6.getBlock().setType(Material.AIR);
					n7.getBlock().setType(Material.AIR);
					n8.getBlock().setType(Material.AIR);
					n9.getBlock().setType(Material.AIR);
					n10.getBlock().setType(Material.AIR);
					socle1.getBlock().setType(m1);
					socle1.getBlock().setData(b1);
					socle2.getBlock().setType(m2);
					socle2.getBlock().setData(b2);
					socle3.getBlock().setType(m3);
					socle3.getBlock().setData(b3);
					socle4.getBlock().setType(m4);
					socle4.getBlock().setData(b4);
					socle5.getBlock().setType(m5);
					socle5.getBlock().setData(b5);
					socle6.getBlock().setType(m6);
					socle6.getBlock().setData(b6);
					socle7.getBlock().setType(m7);
					socle7.getBlock().setData(b7);
					socle8.getBlock().setType(m8);
					socle8.getBlock().setData(b8);
					socle9.getBlock().setType(m9);
					socle9.getBlock().setData(b9);
					socle10.getBlock().setType(m10);
					socle10.getBlock().setData(b10);
				}
			}, 400);
			break;
		case SOUTH:
			Location s1 = new Location(body1.getWorld(), body1.getX(), body1.getY()+1, body1.getZ()+1);
			Location s2 = new Location(body2.getWorld(), body2.getX(), body2.getY()+1, body2.getZ()+1);
			Location s3 = new Location(body3.getWorld(), body3.getX(), body3.getY()+1, body3.getZ()+1);
			Location s4 = new Location(body4.getWorld(), body4.getX(), body4.getY()+1, body4.getZ()+1);
			Location s5 = new Location(body5.getWorld(), body5.getX(), body5.getY()+1, body5.getZ()+1);
			Location s6 = new Location(body6.getWorld(), body6.getX(), body6.getY()+1, body6.getZ()+1);
			Location s7 = new Location(body7.getWorld(), body7.getX(), body7.getY()+1, body7.getZ()+1);
			Location s8 = new Location(body8.getWorld(), body8.getX(), body8.getY()+1, body8.getZ()+1);
			Location s9 = new Location(body9.getWorld(), body9.getX(), body9.getY()+1, body9.getZ()+1);
			Location s10 = new Location(body10.getWorld(), body10.getX(), body10.getY()+1, body10.getZ()+1);
			
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, s1);
					setLocation(m2, b2, body2, s2);
					setLocation(m3, b3, body3, s3);
					setLocation(m4, b4, body4, s4);
					setLocation(m5, b5, body1, s5);
					setLocation(m6, b6, body2, s6);
					setLocation(m7, b7, body3, s7);
					setLocation(m8, b8, body4, s8);
					setLocation(m9, b9, body1, s9);
					setLocation(m10, b10, body10, s10);
					w.playSound(s1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 40);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					s1.getBlock().setType(Material.AIR);
					s2.getBlock().setType(Material.AIR);
					s3.getBlock().setType(Material.AIR);
					s4.getBlock().setType(Material.AIR);
					s5.getBlock().setType(Material.AIR);
					s6.getBlock().setType(Material.AIR);
					s7.getBlock().setType(Material.AIR);
					s8.getBlock().setType(Material.AIR);
					s9.getBlock().setType(Material.AIR);
					s10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 80);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, s1);
					setLocation(m2, b2, body2, s2);
					setLocation(m3, b3, body3, s3);
					setLocation(m4, b4, body4, s4);
					setLocation(m5, b5, body1, s5);
					setLocation(m6, b6, body2, s6);
					setLocation(m7, b7, body3, s7);
					setLocation(m8, b8, body4, s8);
					setLocation(m9, b9, body1, s9);
					setLocation(m10, b10, body10, s10);
					w.playSound(s1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 120);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					s1.getBlock().setType(Material.AIR);
					s2.getBlock().setType(Material.AIR);
					s3.getBlock().setType(Material.AIR);
					s4.getBlock().setType(Material.AIR);
					s5.getBlock().setType(Material.AIR);
					s6.getBlock().setType(Material.AIR);
					s7.getBlock().setType(Material.AIR);
					s8.getBlock().setType(Material.AIR);
					s9.getBlock().setType(Material.AIR);
					s10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 160);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, s1);
					setLocation(m2, b2, body2, s2);
					setLocation(m3, b3, body3, s3);
					setLocation(m4, b4, body4, s4);
					setLocation(m5, b5, body1, s5);
					setLocation(m6, b6, body2, s6);
					setLocation(m7, b7, body3, s7);
					setLocation(m8, b8, body4, s8);
					setLocation(m9, b9, body1, s9);
					setLocation(m10, b10, body10, s10);
					w.playSound(s1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 200);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					s1.getBlock().setType(Material.AIR);
					s2.getBlock().setType(Material.AIR);
					s3.getBlock().setType(Material.AIR);
					s4.getBlock().setType(Material.AIR);
					s5.getBlock().setType(Material.AIR);
					s6.getBlock().setType(Material.AIR);
					s7.getBlock().setType(Material.AIR);
					s8.getBlock().setType(Material.AIR);
					s9.getBlock().setType(Material.AIR);
					s10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 240);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, s1);
					setLocation(m2, b2, body2, s2);
					setLocation(m3, b3, body3, s3);
					setLocation(m4, b4, body4, s4);
					setLocation(m5, b5, body1, s5);
					setLocation(m6, b6, body2, s6);
					setLocation(m7, b7, body3, s7);
					setLocation(m8, b8, body4, s8);
					setLocation(m9, b9, body1, s9);
					setLocation(m10, b10, body10, s10);
					w.playSound(s1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 280);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					s1.getBlock().setType(Material.AIR);
					s2.getBlock().setType(Material.AIR);
					s3.getBlock().setType(Material.AIR);
					s4.getBlock().setType(Material.AIR);
					s5.getBlock().setType(Material.AIR);
					s6.getBlock().setType(Material.AIR);
					s7.getBlock().setType(Material.AIR);
					s8.getBlock().setType(Material.AIR);
					s9.getBlock().setType(Material.AIR);
					s10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 320);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, s1);
					setLocation(m2, b2, body2, s2);
					setLocation(m3, b3, body3, s3);
					setLocation(m4, b4, body4, s4);
					setLocation(m5, b5, body1, s5);
					setLocation(m6, b6, body2, s6);
					setLocation(m7, b7, body3, s7);
					setLocation(m8, b8, body4, s8);
					setLocation(m9, b9, body1, s9);
					setLocation(m10, b10, body10, s10);
					Location door1 = new Location(body1.getWorld(), body1.getX(), body1.getY(), body1.getZ()+2);
					Location door2 = new Location(body1.getWorld(), body1.getX(), body1.getY() + 1, body1.getZ()+2);
					Location door3 = new Location(body1.getWorld(), body1.getX(), body1.getY() + 2, body1.getZ()+2);
					Location door4 = new Location(body1.getWorld(), body1.getX()+1, body1.getY(), body1.getZ()+2);
					Location door5 = new Location(body1.getWorld(), body1.getX()+1, body1.getY() + 1, body1.getZ()+2);
					Location door6 = new Location(body1.getWorld(), body1.getX()+1, body1.getY() + 2, body1.getZ()+2);
					Location door7 = new Location(body1.getWorld(), body1.getX()-1, body1.getY(), body1.getZ()+2);
					Location door8 = new Location(body1.getWorld(), body1.getX()-1, body1.getY() + 1, body1.getZ()+2);
					Location door9 = new Location(body1.getWorld(), body1.getX()-1, body1.getY() + 2, body1.getZ()+2);
					World world = door1.getWorld();
					door1.getBlock().setType(Material.AIR);
					door2.getBlock().setType(Material.AIR);
					door3.getBlock().setType(Material.AIR);
					door4.getBlock().setType(Material.AIR);
					door5.getBlock().setType(Material.AIR);
					door6.getBlock().setType(Material.AIR);
					door7.getBlock().setType(Material.AIR);
					door8.getBlock().setType(Material.AIR);
					door9.getBlock().setType(Material.AIR);
					world.playSound(door1, Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1, 5);
				}
			}, 360);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					s1.getBlock().setType(Material.AIR);
					s2.getBlock().setType(Material.AIR);
					s3.getBlock().setType(Material.AIR);
					s4.getBlock().setType(Material.AIR);
					s5.getBlock().setType(Material.AIR);
					s6.getBlock().setType(Material.AIR);
					s7.getBlock().setType(Material.AIR);
					s8.getBlock().setType(Material.AIR);
					s9.getBlock().setType(Material.AIR);
					s10.getBlock().setType(Material.AIR);
					socle1.getBlock().setType(m1);
					socle1.getBlock().setData(b1);
					socle2.getBlock().setType(m2);
					socle2.getBlock().setData(b2);
					socle3.getBlock().setType(m3);
					socle3.getBlock().setData(b3);
					socle4.getBlock().setType(m4);
					socle4.getBlock().setData(b4);
					socle5.getBlock().setType(m5);
					socle5.getBlock().setData(b5);
					socle6.getBlock().setType(m6);
					socle6.getBlock().setData(b6);
					socle7.getBlock().setType(m7);
					socle7.getBlock().setData(b7);
					socle8.getBlock().setType(m8);
					socle8.getBlock().setData(b8);
					socle9.getBlock().setType(m9);
					socle9.getBlock().setData(b9);
					socle10.getBlock().setType(m10);
					socle10.getBlock().setData(b10);
				}
			}, 400);
			break;
		case EAST:
			Location e1 = new Location(body1.getWorld(), body1.getX()+1, body1.getY()+1, body1.getZ());
			Location e2 = new Location(body2.getWorld(), body2.getX()+1, body2.getY()+1, body2.getZ());
			Location e3 = new Location(body3.getWorld(), body3.getX()+1, body3.getY()+1, body3.getZ());
			Location e4 = new Location(body4.getWorld(), body4.getX()+1, body4.getY()+1, body4.getZ());
			Location e5 = new Location(body5.getWorld(), body5.getX()+1, body5.getY()+1, body5.getZ());
			Location e6 = new Location(body6.getWorld(), body6.getX()+1, body6.getY()+1, body6.getZ());
			Location e7 = new Location(body7.getWorld(), body7.getX()+1, body7.getY()+1, body7.getZ());
			Location e8 = new Location(body8.getWorld(), body8.getX()+1, body8.getY()+1, body8.getZ());
			Location e9 = new Location(body9.getWorld(), body9.getX()+1, body9.getY()+1, body9.getZ());
			Location e10 = new Location(body10.getWorld(), body10.getX()+1, body10.getY()+1, body10.getZ());
			
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, e1);
					setLocation(m2, b2, body2, e2);
					setLocation(m3, b3, body3, e3);
					setLocation(m4, b4, body4, e4);
					setLocation(m5, b5, body1, e5);
					setLocation(m6, b6, body2, e6);
					setLocation(m7, b7, body3, e7);
					setLocation(m8, b8, body4, e8);
					setLocation(m9, b9, body1, e9);
					setLocation(m10, b10, body10, e10);
					w.playSound(e1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 40);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					e1.getBlock().setType(Material.AIR);
					e2.getBlock().setType(Material.AIR);
					e3.getBlock().setType(Material.AIR);
					e4.getBlock().setType(Material.AIR);
					e5.getBlock().setType(Material.AIR);
					e6.getBlock().setType(Material.AIR);
					e7.getBlock().setType(Material.AIR);
					e8.getBlock().setType(Material.AIR);
					e9.getBlock().setType(Material.AIR);
					e10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 80);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, e1);
					setLocation(m2, b2, body2, e2);
					setLocation(m3, b3, body3, e3);
					setLocation(m4, b4, body4, e4);
					setLocation(m5, b5, body1, e5);
					setLocation(m6, b6, body2, e6);
					setLocation(m7, b7, body3, e7);
					setLocation(m8, b8, body4, e8);
					setLocation(m9, b9, body1, e9);
					setLocation(m10, b10, body10, e10);
					w.playSound(e1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 120);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					e1.getBlock().setType(Material.AIR);
					e2.getBlock().setType(Material.AIR);
					e3.getBlock().setType(Material.AIR);
					e4.getBlock().setType(Material.AIR);
					e5.getBlock().setType(Material.AIR);
					e6.getBlock().setType(Material.AIR);
					e7.getBlock().setType(Material.AIR);
					e8.getBlock().setType(Material.AIR);
					e9.getBlock().setType(Material.AIR);
					e10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 160);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, e1);
					setLocation(m2, b2, body2, e2);
					setLocation(m3, b3, body3, e3);
					setLocation(m4, b4, body4, e4);
					setLocation(m5, b5, body1, e5);
					setLocation(m6, b6, body2, e6);
					setLocation(m7, b7, body3, e7);
					setLocation(m8, b8, body4, e8);
					setLocation(m9, b9, body1, e9);
					setLocation(m10, b10, body10, e10);
					w.playSound(e1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 200);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					e1.getBlock().setType(Material.AIR);
					e2.getBlock().setType(Material.AIR);
					e3.getBlock().setType(Material.AIR);
					e4.getBlock().setType(Material.AIR);
					e5.getBlock().setType(Material.AIR);
					e6.getBlock().setType(Material.AIR);
					e7.getBlock().setType(Material.AIR);
					e8.getBlock().setType(Material.AIR);
					e9.getBlock().setType(Material.AIR);
					e10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 240);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, e1);
					setLocation(m2, b2, body2, e2);
					setLocation(m3, b3, body3, e3);
					setLocation(m4, b4, body4, e4);
					setLocation(m5, b5, body1, e5);
					setLocation(m6, b6, body2, e6);
					setLocation(m7, b7, body3, e7);
					setLocation(m8, b8, body4, e8);
					setLocation(m9, b9, body1, e9);
					setLocation(m10, b10, body10, e10);
					w.playSound(e1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 280);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					e1.getBlock().setType(Material.AIR);
					e2.getBlock().setType(Material.AIR);
					e3.getBlock().setType(Material.AIR);
					e4.getBlock().setType(Material.AIR);
					e5.getBlock().setType(Material.AIR);
					e6.getBlock().setType(Material.AIR);
					e7.getBlock().setType(Material.AIR);
					e8.getBlock().setType(Material.AIR);
					e9.getBlock().setType(Material.AIR);
					e10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 320);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, e1);
					setLocation(m2, b2, body2, e2);
					setLocation(m3, b3, body3, e3);
					setLocation(m4, b4, body4, e4);
					setLocation(m5, b5, body1, e5);
					setLocation(m6, b6, body2, e6);
					setLocation(m7, b7, body3, e7);
					setLocation(m8, b8, body4, e8);
					setLocation(m9, b9, body1, e9);
					setLocation(m10, b10, body10, e10);
					Location door1 = new Location(body1.getWorld(), body1.getX()+2, body1.getY(), body1.getZ());
					Location door2 = new Location(body1.getWorld(), body1.getX()+2, body1.getY() + 1, body1.getZ());
					Location door3 = new Location(body1.getWorld(), body1.getX()+2, body1.getY() + 2, body1.getZ());
					Location door4 = new Location(body1.getWorld(), body1.getX()+2, body1.getY(), body1.getZ()+1);
					Location door5 = new Location(body1.getWorld(), body1.getX()+2, body1.getY() + 1, body1.getZ()+1);
					Location door6 = new Location(body1.getWorld(), body1.getX()+2, body1.getY() + 2, body1.getZ()+1);
					Location door7 = new Location(body1.getWorld(), body1.getX()+2, body1.getY(), body1.getZ()-1);
					Location door8 = new Location(body1.getWorld(), body1.getX()+2, body1.getY() + 1, body1.getZ()-1);
					Location door9 = new Location(body1.getWorld(), body1.getX()+2, body1.getY() + 2, body1.getZ()-1);
					World world = door1.getWorld();
					door1.getBlock().setType(Material.AIR);
					door2.getBlock().setType(Material.AIR);
					door3.getBlock().setType(Material.AIR);
					door4.getBlock().setType(Material.AIR);
					door5.getBlock().setType(Material.AIR);
					door6.getBlock().setType(Material.AIR);
					door7.getBlock().setType(Material.AIR);
					door8.getBlock().setType(Material.AIR);
					door9.getBlock().setType(Material.AIR);
					world.playSound(door1, Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1, 5);
				}
			}, 360);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					e1.getBlock().setType(Material.AIR);
					e2.getBlock().setType(Material.AIR);
					e3.getBlock().setType(Material.AIR);
					e4.getBlock().setType(Material.AIR);
					e5.getBlock().setType(Material.AIR);
					e6.getBlock().setType(Material.AIR);
					e7.getBlock().setType(Material.AIR);
					e8.getBlock().setType(Material.AIR);
					e9.getBlock().setType(Material.AIR);
					e10.getBlock().setType(Material.AIR);
					socle1.getBlock().setType(m1);
					socle1.getBlock().setData(b1);
					socle2.getBlock().setType(m2);
					socle2.getBlock().setData(b2);
					socle3.getBlock().setType(m3);
					socle3.getBlock().setData(b3);
					socle4.getBlock().setType(m4);
					socle4.getBlock().setData(b4);
					socle5.getBlock().setType(m5);
					socle5.getBlock().setData(b5);
					socle6.getBlock().setType(m6);
					socle6.getBlock().setData(b6);
					socle7.getBlock().setType(m7);
					socle7.getBlock().setData(b7);
					socle8.getBlock().setType(m8);
					socle8.getBlock().setData(b8);
					socle9.getBlock().setType(m9);
					socle9.getBlock().setData(b9);
					socle10.getBlock().setType(m10);
					socle10.getBlock().setData(b10);
				}
			}, 400);
			break;
		case WEST:
			Location w1 = new Location(body1.getWorld(), body1.getX()-1, body1.getY()+1, body1.getZ());
			Location w2 = new Location(body2.getWorld(), body2.getX()-1, body2.getY()+1, body2.getZ());
			Location w3 = new Location(body3.getWorld(), body3.getX()-1, body3.getY()+1, body3.getZ());
			Location w4 = new Location(body4.getWorld(), body4.getX()-1, body4.getY()+1, body4.getZ());
			Location w5 = new Location(body5.getWorld(), body5.getX()-1, body5.getY()+1, body5.getZ());
			Location w6 = new Location(body6.getWorld(), body6.getX()-1, body6.getY()+1, body6.getZ());
			Location w7 = new Location(body7.getWorld(), body7.getX()-1, body7.getY()+1, body7.getZ());
			Location w8 = new Location(body8.getWorld(), body8.getX()-1, body8.getY()+1, body8.getZ());
			Location w9 = new Location(body9.getWorld(), body9.getX()-1, body9.getY()+1, body9.getZ());
			Location w10 = new Location(body10.getWorld(), body10.getX()-1, body10.getY()+1, body10.getZ());
			
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, w1);
					setLocation(m2, b2, body2, w2);
					setLocation(m3, b3, body3, w3);
					setLocation(m4, b4, body4, w4);
					setLocation(m5, b5, body1, w5);
					setLocation(m6, b6, body2, w6);
					setLocation(m7, b7, body3, w7);
					setLocation(m8, b8, body4, w8);
					setLocation(m9, b9, body1, w9);
					setLocation(m10, b10, body10, w10);
					w.playSound(w1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 40);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					w1.getBlock().setType(Material.AIR);
					w2.getBlock().setType(Material.AIR);
					w3.getBlock().setType(Material.AIR);
					w4.getBlock().setType(Material.AIR);
					w5.getBlock().setType(Material.AIR);
					w6.getBlock().setType(Material.AIR);
					w7.getBlock().setType(Material.AIR);
					w8.getBlock().setType(Material.AIR);
					w9.getBlock().setType(Material.AIR);
					w10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 80);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, w1);
					setLocation(m2, b2, body2, w2);
					setLocation(m3, b3, body3, w3);
					setLocation(m4, b4, body4, w4);
					setLocation(m5, b5, body1, w5);
					setLocation(m6, b6, body2, w6);
					setLocation(m7, b7, body3, w7);
					setLocation(m8, b8, body4, w8);
					setLocation(m9, b9, body1, w9);
					setLocation(m10, b10, body10, w10);
					w.playSound(w1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 120);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					w1.getBlock().setType(Material.AIR);
					w2.getBlock().setType(Material.AIR);
					w3.getBlock().setType(Material.AIR);
					w4.getBlock().setType(Material.AIR);
					w5.getBlock().setType(Material.AIR);
					w6.getBlock().setType(Material.AIR);
					w7.getBlock().setType(Material.AIR);
					w8.getBlock().setType(Material.AIR);
					w9.getBlock().setType(Material.AIR);
					w10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 160);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, w1);
					setLocation(m2, b2, body2, w2);
					setLocation(m3, b3, body3, w3);
					setLocation(m4, b4, body4, w4);
					setLocation(m5, b5, body1, w5);
					setLocation(m6, b6, body2, w6);
					setLocation(m7, b7, body3, w7);
					setLocation(m8, b8, body4, w8);
					setLocation(m9, b9, body1, w9);
					setLocation(m10, b10, body10, w10);
					w.playSound(w1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 200);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					w1.getBlock().setType(Material.AIR);
					w2.getBlock().setType(Material.AIR);
					w3.getBlock().setType(Material.AIR);
					w4.getBlock().setType(Material.AIR);
					w5.getBlock().setType(Material.AIR);
					w6.getBlock().setType(Material.AIR);
					w7.getBlock().setType(Material.AIR);
					w8.getBlock().setType(Material.AIR);
					w9.getBlock().setType(Material.AIR);
					w10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 240);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, w1);
					setLocation(m2, b2, body2, w2);
					setLocation(m3, b3, body3, w3);
					setLocation(m4, b4, body4, w4);
					setLocation(m5, b5, body1, w5);
					setLocation(m6, b6, body2, w6);
					setLocation(m7, b7, body3, w7);
					setLocation(m8, b8, body4, w8);
					setLocation(m9, b9, body1, w9);
					setLocation(m10, b10, body10, w10);
					w.playSound(w1, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 5);
				}
			}, 280);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					w1.getBlock().setType(Material.AIR);
					w2.getBlock().setType(Material.AIR);
					w3.getBlock().setType(Material.AIR);
					w4.getBlock().setType(Material.AIR);
					w5.getBlock().setType(Material.AIR);
					w6.getBlock().setType(Material.AIR);
					w7.getBlock().setType(Material.AIR);
					w8.getBlock().setType(Material.AIR);
					w9.getBlock().setType(Material.AIR);
					w10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, up1);
					setLocation(m2, b2, body2, up2);
					setLocation(m3, b3, body3, up3);
					setLocation(m4, b4, body4, up4);
					setLocation(m5, b5, body5, up5);
					setLocation(m6, b6, body6, up6);
					setLocation(m7, b7, body7, up7);
					setLocation(m8, b8, body8, up8);
					setLocation(m9, b9, body9, up9);
					setLocation(m10, b10, body10, up10);
				}
			}, 320);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					up1.getBlock().setType(Material.AIR);
					up2.getBlock().setType(Material.AIR);
					up3.getBlock().setType(Material.AIR);
					up4.getBlock().setType(Material.AIR);
					up5.getBlock().setType(Material.AIR);
					up6.getBlock().setType(Material.AIR);
					up7.getBlock().setType(Material.AIR);
					up8.getBlock().setType(Material.AIR);
					up9.getBlock().setType(Material.AIR);
					up10.getBlock().setType(Material.AIR);
					setLocation(m1, b1, body1, w1);
					setLocation(m2, b2, body2, w2);
					setLocation(m3, b3, body3, w3);
					setLocation(m4, b4, body4, w4);
					setLocation(m5, b5, body1, w5);
					setLocation(m6, b6, body2, w6);
					setLocation(m7, b7, body3, w7);
					setLocation(m8, b8, body4, w8);
					setLocation(m9, b9, body1, w9);
					setLocation(m10, b10, body10, w10);
					Location door1 = new Location(body1.getWorld(), body1.getX()-2, body1.getY(), body1.getZ());
					Location door2 = new Location(body1.getWorld(), body1.getX()-2, body1.getY() + 1, body1.getZ());
					Location door3 = new Location(body1.getWorld(), body1.getX()-2, body1.getY() + 2, body1.getZ());
					Location door4 = new Location(body1.getWorld(), body1.getX()-2, body1.getY(), body1.getZ()+1);
					Location door5 = new Location(body1.getWorld(), body1.getX()-2, body1.getY() + 1, body1.getZ()+1);
					Location door6 = new Location(body1.getWorld(), body1.getX()-2, body1.getY() + 2, body1.getZ()+1);
					Location door7 = new Location(body1.getWorld(), body1.getX()-2, body1.getY(), body1.getZ()-1);
					Location door8 = new Location(body1.getWorld(), body1.getX()-2, body1.getY() + 1, body1.getZ()-1);
					Location door9 = new Location(body1.getWorld(), body1.getX()-2, body1.getY() + 2, body1.getZ()-1);
					World world = door1.getWorld();
					door1.getBlock().setType(Material.AIR);
					door2.getBlock().setType(Material.AIR);
					door3.getBlock().setType(Material.AIR);
					door4.getBlock().setType(Material.AIR);
					door5.getBlock().setType(Material.AIR);
					door6.getBlock().setType(Material.AIR);
					door7.getBlock().setType(Material.AIR);
					door8.getBlock().setType(Material.AIR);
					door9.getBlock().setType(Material.AIR);
					world.playSound(door1, Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1, 5);
				}
			}, 360);

			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					w1.getBlock().setType(Material.AIR);
					w2.getBlock().setType(Material.AIR);
					w3.getBlock().setType(Material.AIR);
					w4.getBlock().setType(Material.AIR);
					w5.getBlock().setType(Material.AIR);
					w6.getBlock().setType(Material.AIR);
					w7.getBlock().setType(Material.AIR);
					w8.getBlock().setType(Material.AIR);
					w9.getBlock().setType(Material.AIR);
					w10.getBlock().setType(Material.AIR);
					socle1.getBlock().setType(m1);
					socle1.getBlock().setData(b1);
					socle2.getBlock().setType(m2);
					socle2.getBlock().setData(b2);
					socle3.getBlock().setType(m3);
					socle3.getBlock().setData(b3);
					socle4.getBlock().setType(m4);
					socle4.getBlock().setData(b4);
					socle5.getBlock().setType(m5);
					socle5.getBlock().setData(b5);
					socle6.getBlock().setType(m6);
					socle6.getBlock().setData(b6);
					socle7.getBlock().setType(m7);
					socle7.getBlock().setData(b7);
					socle8.getBlock().setType(m8);
					socle8.getBlock().setData(b8);
					socle9.getBlock().setType(m9);
					socle9.getBlock().setData(b9);
					socle10.getBlock().setType(m10);
					socle10.getBlock().setData(b10);
				}
			}, 400);
			break;
		default:
			break;
		}
	}
}
