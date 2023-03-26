package fr.shiick.warpgui.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import fr.shiick.warpgui.WarpGUI;

public class clickInventory implements Listener {

	WarpGUI warpgui;
	
	public clickInventory(WarpGUI wg) {
		warpgui = wg;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPreCommand(PlayerCommandPreprocessEvent e){
		Player p = e.getPlayer();
		String[] args = e.getMessage().split(" ");
		if(args[0].equalsIgnoreCase("/warp")){
			e.setCancelled(true);
			warpgui.openWarp(p);
		}	
	}
	
	@EventHandler
	public void clickInventoryWarp(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		World spawn = (World) warpgui.getConfig().get("Spawn");
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&9Warps"))){
			e.setCancelled(true);
			switch (e.getCurrentItem().getType()){
			case IRON_HELMET:
				int x = warpgui.getConfig().getInt("corrompu.x");
				int y = warpgui.getConfig().getInt("corrompu.y");
				int z = warpgui.getConfig().getInt("corrompu.z");
				Location warp = new Location(spawn, x, y, z, 0, 0);
				if(p.hasPermission("essentials.kits.corrompu")){
					p.closeInventory();
					p.teleport(warp);
				}
				break;
			case GOLD_HELMET:
				int x1 = warpgui.getConfig().getInt("vampire.x");
				int y1 = warpgui.getConfig().getInt("vampire.y");
				int z1 = warpgui.getConfig().getInt("vampire.z");
				Location warp1 = new Location(spawn, x1, y1, z1, 0, 0);
				if(p.hasPermission("essentials.kits.vampire")){
					p.closeInventory();
					p.teleport(warp1);
				}
				break;
			case DIAMOND_HELMET:
				int x2 = warpgui.getConfig().getInt("demon.x");
				int y2 = warpgui.getConfig().getInt("demon.y");
				int z2 = warpgui.getConfig().getInt("demon.z");
				Location warp2 = new Location(spawn, x2, y2, z2, 0, 0);
				if(p.hasPermission("essentials.kits.demon")){
					p.closeInventory();
					p.teleport(warp2);
				}
				break;
			case FEATHER:
				int x3 = warpgui.getConfig().getInt("jump.x");
				int y3 = warpgui.getConfig().getInt("jump.y");
				int z3 = warpgui.getConfig().getInt("jump.z");
				Location warp3 = new Location(spawn, x3, y3, z3, 0, 0);
				p.closeInventory();
				p.teleport(warp3);
				break;
			case LEAVES:
				short durability = e.getCurrentItem().getDurability();
				if (durability == 1) {
					int x4 = warpgui.getConfig().getInt("laby.x");
					int y4 = warpgui.getConfig().getInt("laby.y");
					int z4 = warpgui.getConfig().getInt("laby.z");
					Location warp4 = new Location(spawn, x4, y4, z4, 0, 0);
					p.closeInventory();
					p.teleport(warp4);
				}
				break;
			case RAW_BEEF:
				int x5 = warpgui.getConfig().getInt("farm.x");
				int y5 = warpgui.getConfig().getInt("farm.y");
				int z5 = warpgui.getConfig().getInt("farm.z");
				Location warp5 = new Location(spawn, x5, y5, z5, 0, 0);
				p.closeInventory();
				p.teleport(warp5);
				break;
			case NETHERRACK:
				int x6 = warpgui.getConfig().getInt("nether.x");
				int y6 = warpgui.getConfig().getInt("nether.y");
				int z6 = warpgui.getConfig().getInt("nether.z");
				World nether = (World) warpgui.getConfig().get("nether.world");
				Location warp6 = new Location(nether, x6, y6, z6, 0, 0);
				p.closeInventory();
				p.teleport(warp6);
				break;
			case CHEST:
				int x7 = warpgui.getConfig().getInt("shop.x");
				int y7 = warpgui.getConfig().getInt("shop.y");
				int z7 = warpgui.getConfig().getInt("shop.z");
				Location warp7 = new Location(spawn, x7, y7, z7, 0, 0);
				p.closeInventory();
				p.teleport(warp7);
				break;
			case COBBLESTONE:
				int x8 = warpgui.getConfig().getInt("generator.x");
				int y8 = warpgui.getConfig().getInt("generator.y");
				int z8 = warpgui.getConfig().getInt("generator.z");
				Location warp8 = new Location(spawn, x8, y8, z8, 0, 0);
				p.closeInventory();
				p.teleport(warp8);
				break;
			case ENDER_CHEST:
				int x9 = warpgui.getConfig().getInt("vaudou.x");
				int y9 = warpgui.getConfig().getInt("vaudou.y");
				int z9 = warpgui.getConfig().getInt("vaudou.z");
				Location warp9 = new Location(spawn, x9, y9, z9, 0, 0);
				p.closeInventory();
				p.teleport(warp9);
				break;
			case SKULL_ITEM:
				short durability2 = e.getCurrentItem().getDurability();
				if (durability2 == 3) {
					int x10 = warpgui.getConfig().getInt("staff.x");
					int y10 = warpgui.getConfig().getInt("staff.y");
					int z10 = warpgui.getConfig().getInt("staff.z");
					Location warp10 = new Location(spawn, x10, y10, z10, 0, 0);
					p.closeInventory();
					p.teleport(warp10);
				}
				break;
			case NETHER_STAR:
				int x11 = warpgui.getConfig().getInt("legendaire.x");
				int y11 = warpgui.getConfig().getInt("legendaire.y");
				int z11 = warpgui.getConfig().getInt("legendaire.z");
				Location warp11 = new Location(spawn, x11, y11, z11, 0, 0);
				p.closeInventory();
				p.teleport(warp11);
				break;
			default:
				break;
			}
		}
	}

}
