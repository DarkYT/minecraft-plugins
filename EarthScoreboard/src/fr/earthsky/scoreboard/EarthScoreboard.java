package fr.earthsky.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import fr.earthsky.scoreboard.listeners.joinEvent;
import net.milkbowl.vault.economy.Economy;

public class EarthScoreboard extends JavaPlugin {
	
	public static Economy econ = null;
	
	@Override
	public void onEnable(){
		if (!setupEconomy()){
			getLogger().severe(String.format("Vault est absent ! Désactivation...", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new joinEvent(this), this);
		for(Player p : Bukkit.getOnlinePlayers()){
			scoreboard(p);
			UpdateSB iu = new UpdateSB(p, this);
			iu.runTaskTimer(this, 0L, 100L);
		}
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

	public void scoreboard(Player p){
		Objective objective = joinEvent.pb.registerNewObjective("title", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lEarth&f&lSky"));
        Score name = objective.getScore(p.getName());
        name.setScore(10);
        p.setScoreboard(joinEvent.pb);
	}
	
	public Scoreboard getScoreboard(){
		return joinEvent.pb;
	}
	
}
