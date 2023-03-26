package fr.dark.wm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dark.wm.tasks.DeleteTask;
import net.md_5.bungee.api.ChatColor;

public class WMessage extends JavaPlugin implements Listener{
	
	public List<OfflinePlayer> recentPlayers = new ArrayList<>();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		OfflinePlayer p = e.getPlayer();
		if(!p.hasPlayedBefore()) {
			recentPlayers.add(p);
			DeleteTask task = new DeleteTask(this, p);
			task.runTaskTimer(this, 0, 20);
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("bienvenue")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(recentPlayers.isEmpty()) {
					p.sendMessage("§cAucun joueur n'est arrivé dans les dernières 20 secondes !");
					return false;
				}
				for(OfflinePlayer pl : recentPlayers) {
					List<String> messages = getConfig().getStringList("WMessages.MessagesDeThomb");
					Random r = new Random();
					int mess = r.nextInt(messages.size());
					
					String message;
					if(messages.get(mess).contains("{PLAYER}")) {
						message = messages.get(mess).replace("{PLAYER}", pl.getName());
					}else {
						message = messages.get(mess);
					}
					p.chat(ChatColor.translateAlternateColorCodes('&', message));
				}
			}
		}
		return false;
	}

}
