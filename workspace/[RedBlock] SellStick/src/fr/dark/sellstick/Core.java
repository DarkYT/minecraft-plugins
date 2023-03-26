package fr.dark.sellstick;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dark.sellstick.commands.sellstickCommand;
import fr.dark.sellstick.events.clickListener;
import fr.dark.sellstick.utils.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Core extends JavaPlugin {
	
	private static Economy econ = null;
	
	public Utils utils = new Utils();
	
	@Override
    public void onEnable() {
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new clickListener(this), this);
		
		getCommand("sellstick").setExecutor(new sellstickCommand(this));
		
		if (!setupEconomy() ) {
			Logger.getLogger("Minecraft").info(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		
        saveDefaultConfig();
    }
    
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	@SuppressWarnings("deprecation")
	public int sellPrice(Chest chest, Player p) {
		int price = 0;
		for(ItemStack item : chest.getInventory().getContents()) {
    		for(String s : getConfig().getConfigurationSection("SellStick.SalableItems").getKeys(false)) {
    			if(item == null) {
    				continue;
    			}
    			if(item.getType().equals(Material.valueOf(getConfig().getString("SellStick.SalableItems."+s+".Type")))) {
    				if(item.getData().getData() == (byte) getConfig().getInt("SellStick.SalableItems."+s+".Data")) {
    					int money = getConfig().getInt("SellStick.SalableItems."+s+".Price") * item.getAmount();
    					price += money;
    				}
    			}else {
    				continue;
    			}
    		}
    	}
    	return price;
	}
    
    @SuppressWarnings("deprecation")
	public List<ItemStack> sellAll(Chest chest, Player p) {
    	List<ItemStack> salableItems = new ArrayList<>();
    	for(ItemStack item : chest.getInventory().getContents()) {
    		for(String s : getConfig().getConfigurationSection("SellStick.SalableItems").getKeys(false)) {
    			if(item == null) {
    				continue;
    			}
    			if(item.getType().equals(Material.valueOf(getConfig().getString("SellStick.SalableItems."+s+".Type")))) {
    				if(item.getData().getData() == (byte) getConfig().getInt("SellStick.SalableItems."+s+".Data")) {
    					int money = getConfig().getInt("SellStick.SalableItems."+s+".Price") * item.getAmount();
    					EconomyResponse r = econ.depositPlayer(p, money);
        				salableItems.add(item);
        				if (r.transactionSuccess()) {
        					continue;
        				}else {
        					System.out.println("§4ERROR WITH ECONOMY");
        					break;
        				}
    				}
    			}else {
    				continue;
    			}
    		}
    	}
    	return salableItems;
    }
    
    public void sendActionBar(String text, Player p) {
    	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
    }
	
	public Economy getEconomy() {
		return econ;
	}
	
}