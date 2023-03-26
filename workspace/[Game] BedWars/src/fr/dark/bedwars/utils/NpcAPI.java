package fr.dark.bedwars.utils;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class NpcAPI {

	static public void createNPC(final String name, final Location loc) {
		System.out.println(loc.getWorld());
		Entity v = (Entity) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
		v.setCustomName(name);
		v.setCustomNameVisible(true);
		freezeEntity(v);
	}

	public static void freezeEntity(Entity en) {
		net.minecraft.server.v1_12_R1.Entity nmsEn = ((CraftEntity) en).getHandle();
		NBTTagCompound compound = new NBTTagCompound();
		nmsEn.c(compound);
		compound.setByte("NoAI", (byte) 1);
		nmsEn.f(compound);
	}

}
