package fr.earthsky.scoreboard;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.wasteofplastic.askyblock.ASkyBlock;
import fr.earthsky.scoreboard.listeners.joinEvent;
import net.milkbowl.vault.economy.Economy;

public class EarthScoreboard extends JavaPlugin {
	
	public static Economy econ = null;
	private ASkyBlock plugin;
	public Scoreboard board; 
	
	@Override
	public void onEnable(){
		if (!setupEconomy()){
			getLogger().severe(String.format("Vault est absent ! Désactivation...", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new joinEvent(this), this);
	}
	
	public void refresh(final Player p){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
             public void run() {
            	 scoreboard(p);
            	 p.setScoreboard(board)
             }    
        }, 20L, 20L);
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

	public int getIslandLevel(UUID playerUUID) {
        returnpluginn.getPlayers().getIslandLevel(playerUUID);
    }
	
	public void scoreboard(Player p){
		OfflinePlayer pl = p;
		double moneyP = econ.getBalance(pl);
		joinEvent.pb =  Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = joinEvent.pb.registerNewObjective("title", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lEarth&f&lSky"));
        Score name = objective.getScore("Level :  + getIslandLevel(p.getUniqueId())));
        Score money = objective.getScore("Money : " + moneyP);
        name.setScore(10);
        money.setScore(9);
        p.setScoreboard(joinEvent.pb);
	
		
}
