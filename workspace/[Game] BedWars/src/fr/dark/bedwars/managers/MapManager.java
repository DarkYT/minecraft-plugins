package fr.dark.bedwars.managers;

import org.bukkit.Bukkit;
import org.bukkit.World;

import fr.dark.bedwars.BedWars;
import fr.dark.bedwars.others.BedMap;
import fr.dark.bedwars.others.WaitMap;
import fr.dark.bedwars.utils.Utils;

public class MapManager {

	BedWars core;

	public MapManager(BedWars core) {
		this.core = core;
	}

	public void loadMaps() {
		World m = Bukkit.getWorld(core.getConfig().getString("BedWars.Config.ActiveMap.World"));
		BedMap map = new BedMap(core.getConfig().getString("BedWars.Config.ActiveMap.World"),Utils.getLocationString(core.getConfig().getString("BedWars.Config.ActiveMap.Spawn"),m));
		
		core.activeMap = map;
		
		World l = Bukkit.getWorld(core.getConfig().getString("BedWars.Config.Lobby.World"));
		WaitMap lobby = new WaitMap(core.getConfig().getString("BedWars.Config.Lobby.World"),Utils.getLocationString(core.getConfig().getString("BedWars.Config.Lobby.Spawn"), l));
		
		core.lobby = lobby;
	}

}
