package fr.dark.bedwars.utils;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Utils {
	
	public static String prettifyText(String ugly) {
        if (!ugly.contains("_") && (!ugly.equals(ugly.toUpperCase())))
            return ugly;
        String fin = "";
        ugly = ugly.toLowerCase();
        if (ugly.contains("_")) {
            String[] splt = ugly.split("_");
            int i = 0;
            for (String s : splt) {
                i += 1;
                fin += Character.toUpperCase(s.charAt(0)) + s.substring(1);
                if (i < splt.length)
                    fin += " ";
            }
        } else {
            fin += Character.toUpperCase(ugly.charAt(0)) + ugly.substring(1);
        }
        return fin;
    }
	
	/**
	 * Convert a random number between 0 and 2 into a random death message
	 * 
	 * @param rand - random integer
	 * @return String
	 */
	
	static public String getRandomKillMessage(final int rand, final Player k, final Player v) {
		switch(rand) {
		case 0:
			return "§e[§6Bed§eWars] §6§l"+v.getName()+" §r§6est tué par §l"+k.getName();
		case 1:
			return "§e[§6Bed§eWars] §6§l"+v.getName()+"§r§6 n'a pas survécu face à §l"+k.getName();
		case 2:
			return "§e[§6Bed§eWars] §6§l"+k.getName()+" §r§6a démonté §l"+v.getName();
		default:
			return null;
		}
	}
	
	/**
	 * Convert a random number between 0 and 2 into a random death message
	 * 
	 * @param rand - random integer
	 * @return String
	 */
	
	static public String getRandomDeathMessage(final int rand, final Player p) {
		switch(rand) {
		case 0:
			return "§e[§6Bed§eWars] §6La mort de §l"+p.getName()+" §r§6nous touche profondément..";
		case 1:
			return "§e[§6Bed§eWars] §6§l"+p.getName()+" §r§6est mort.. R.I.P";
		case 2:
			return "§e[§6Bed§eWars] §6"+p.getName()+" §eest mort !";
		default:
			return null;
		}
	}
	
	/**
	 * Converts a string color to a Color
	 * 
	 * @param s - color in string format
	 * @return Color
	 */
	
	static public Color getColorString(final String s) {
		if(s == null ||  s.trim() == "") {
			return null;
		}
		final String string = s.toLowerCase();
		switch(string) {
		case "aqua":
			return Color.AQUA;
		case "black":
			return Color.BLACK;
		case "blue":
			return Color.BLUE;
		case "fushia":
			return Color.FUCHSIA;
		case "gray":
			return Color.GRAY;
		case "green":
			return Color.GREEN;
		case "lime":
			return Color.LIME;
		case "maroon":
			return Color.MAROON;
		case "navy":
			return Color.NAVY;
		case "olive":
			return Color.OLIVE;
		case "orange":
			return Color.ORANGE;
		case "purple":
			return Color.PURPLE;
		case "red":
			return Color.RED;
		case "silver":
			return Color.SILVER;
		case "teal":
			return Color.TEAL;
		case "white":
			return Color.WHITE;
		case "yellow":
			return Color.YELLOW;
		}
		return null;
	}
	
	/**
     * Converts a serialized location to a Location. Returns null if string is
     * empty
     * 
     * @param s - serialized location in format "world:x:y:z"
     * @return Location
     */
    static public Location getLocationString(final String s, final World w) {
        if (s == null || s.trim() == "") {
            return null;
        }
        final String[] parts = s.split(":");
        if (parts.length == 3) {
            if (w == null) {
                return null;
            }
            final double x = Double.valueOf(parts[0]);
            final double y = Double.valueOf(parts[1]);
            final double z = Double.valueOf(parts[2]);
            return new Location(w, x, y, z);
        } else if (parts.length == 5) {
            if (w == null) {
                return null;
            }
            final double x = Double.valueOf(parts[0]);
            final double y = Double.valueOf(parts[1]);
            final double z = Double.valueOf(parts[2]);
            final float yaw = Float.valueOf(parts[3]);
            final float pitch = Float.valueOf(parts[4]);
            return new Location(w, x, y, z, yaw, pitch);
        }
        return null;
    }

    /**
     * Converts a location to a simple string representation
     * If location is null, returns empty string
     * 
     * @param location
     * @return String of location
     */
    static public String getStringLocation(final Location location) {
        if (location == null || location.getWorld() == null) {
            return "";
        }
        return location.getWorld().getName() + ":" + location.getBlockX() + ":" + location.getBlockY() + ":" + location.getBlockZ() + ":" + Float.floatToIntBits(location.getYaw()) + ":" + Float.floatToIntBits(location.getPitch());
    }

}
