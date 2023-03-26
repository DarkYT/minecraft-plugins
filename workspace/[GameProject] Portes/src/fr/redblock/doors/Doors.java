package fr.redblock.doors;

import java.util.HashMap;
import java.util.Map;

public enum Doors {

	Herse(0),
	Battante(1),
	Levis(2);
	
	private int power;
	public static Map<Integer, Doors> portes = new HashMap<>();
	
	Doors(int power) {
		this.power = power;
	}
	
	static {
		for (Doors r : Doors.values()) {
			portes.put(r.getPower(), r);
		}
	}
	
	public int getPower() {
		return power;
	}
	
	public static Doors powerToDoor(int power) {
		return portes.get(power);
	}
	
}
