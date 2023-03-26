package fr.shiick.redcore.utils;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;

public class Particles {

	public Particles(Location loc, EnumParticle type, float radiusX, float radiusY, float radiusZ, int speed, int amount, Player target) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type, true, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), radiusX, radiusY, radiusZ, speed, amount, null);
		((CraftPlayer) target).getHandle().playerConnection.sendPacket(packet);
	}
	
}
