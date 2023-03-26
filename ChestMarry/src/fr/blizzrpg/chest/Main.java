package fr.blizzrpg.chest;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import fr.blizzrpg.chest.commands.MarryCommand;
import fr.blizzrpg.chest.listeners.onJoin;
import fr.blizzrpg.chest.listeners.onLeave;

public class Main extends JavaPlugin {
	public HashMap<UUID, Inventory> chests = new HashMap<UUID, Inventory>();
	
	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		getCommand("marryme").setExecutor(new MarryCommand(this), this);
		pm.registerEvents(new onJoin(this), this);
		pm.registerEvents(new onLeave(this), this);
	}
	
	@Override
	public void onDisable() {
		for (java.util.Map.Entry<UUID, Inventory> entry : chests.entrySet()) {
			if (!getConfig().contains("chests." + entry.getKey())) {
				getConfig().createSection("chests." + entry.getKey());
			}
			
			char c = '1';
			for (ItemStack itemStack : entry.getValue()) {
				if (itemStack != null) {
					saveItem(getConfig().createSection("chests." + entry.getKey() + "." + c++), itemStack);
				}
			}
			
			saveConfig();
		}
	}
	
	public void saveItem(ConfigurationSection section, ItemStack itemStack) {
		section.set("type", itemStack.getType().name());
		section.set("amount", itemStack.getAmount());
		section.set("infos", itemStack);
	}
	
	public ItemStack loadItem(ConfigurationSection section) {
		//ItemStack item = new ItemStack(Material.valueOf(section.getString("type")), section.getInt("amount"));
		ItemStack item = section.getItemStack("infos");
		return item;
	}
}
