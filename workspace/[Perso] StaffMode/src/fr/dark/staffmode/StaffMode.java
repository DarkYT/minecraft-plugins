package fr.dark.staffmode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dark.staffmode.commands.staffCommand;
import fr.dark.staffmode.events.modEvent;
import fr.dark.staffmode.utils.Utils;

public class StaffMode extends JavaPlugin {
	
	public Map<Player, ItemStack[]> modeUsers = new HashMap<>();
	public Map<String, Long> cooldown = new HashMap<>();
	
	public List<Player> fly = new ArrayList<>();
	public List<Player> freeze = new ArrayList<>();
	public List<Player> vanish = new ArrayList<>();
	
	@Override
	public void onEnable() {
		getLogger().info("Pour activer l'interface de modération, faites /sm");
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new modEvent(this), this);
		
		getCommand("staffmode").setExecutor(new staffCommand(this));
		
		saveDefaultConfig();
	}
	
	public void giveModInterface(Player p) {
		modeUsers.put(p, p.getInventory().getContents());
		p.getInventory().clear();
		
		p.getInventory().setItem(0, Utils.getModItem(Material.valueOf(getConfig().getString("StaffMode.Inventory.InvSee.Type")), 
				(byte) getConfig().getInt("StaffMode.Inventory.InvSee.Data"), Utils.color(getConfig().getString("StaffMode.Inventory.InvSee.DispName")), 
				getConfig().getBoolean("StaffMode.Inventory.InvSee.Enchant"), getConfig().getString("StaffMode.Inventory.InvSee.Owner")));
		
		p.getInventory().setItem(1, Utils.getModItem(Material.valueOf(getConfig().getString("StaffMode.Inventory.Vanish.Disable.Type")), 
				(byte) getConfig().getInt("StaffMode.Inventory.Vanish.Disable.Data"), Utils.color(getConfig().getString("StaffMode.Inventory.Vanish.Disable.DispName")), 
				getConfig().getBoolean("StaffMode.Inventory.Vanish.Disable.Enchant"), null));
		
		p.getInventory().setItem(3, Utils.getModItem(Material.valueOf(getConfig().getString("StaffMode.Inventory.EnderChest.Type")), 
				(byte) getConfig().getInt("StaffMode.Inventory.EnderChest.Data"), Utils.color(getConfig().getString("StaffMode.Inventory.EnderChest.DispName")), 
				getConfig().getBoolean("StaffMode.Inventory.EnderChest.Enchant"), null));
		
		p.getInventory().setItem(4, Utils.getModItem(Material.valueOf(getConfig().getString("StaffMode.Inventory.Report.Type")), 
				(byte) getConfig().getInt("StaffMode.Inventory.Report.Data"), Utils.color(getConfig().getString("StaffMode.Inventory.Report.DispName")), 
				getConfig().getBoolean("StaffMode.Inventory.Report.Enchant"), null));
		
		p.getInventory().setItem(5, Utils.getModItem(Material.valueOf(getConfig().getString("StaffMode.Inventory.Freeze.Type")), 
				(byte) getConfig().getInt("StaffMode.Inventory.Freeze.Data"), Utils.color(getConfig().getString("StaffMode.Inventory.Freeze.DispName")), 
				getConfig().getBoolean("StaffMode.Inventory.Freeze.Enchant"), null));
		
		p.getInventory().setItem(7, Utils.getModItem(Material.valueOf(getConfig().getString("StaffMode.Inventory.Fly.Disable.Type")), 
				(byte) getConfig().getInt("StaffMode.Inventory.Fly.Disable.Data"), Utils.color(getConfig().getString("StaffMode.Inventory.Fly.Disable.DispName")), 
				getConfig().getBoolean("StaffMode.Inventory.Fly.Disable.Enchant"), null));
		
		p.getInventory().setItem(8, Utils.getModItem(Material.valueOf(getConfig().getString("StaffMode.Inventory.RandTP.Type")), 
				(byte) getConfig().getInt("StaffMode.Inventory.RandTP.Data"), Utils.color(getConfig().getString("StaffMode.Inventory.RandTP.DispName")), 
				getConfig().getBoolean("StaffMode.Inventory.RandTP.Enchant"), null));
	}
	
	public void removeModInterface(Player p) {
		if(modeUsers.containsKey(p)) {
			p.getInventory().setContents(modeUsers.get(p));
			modeUsers.remove(p);
		}
	}

}
