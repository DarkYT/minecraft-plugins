package fr.dark.ctfcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.dark.ctfcore.CTFCore;

public class playerJoinListener implements Listener {

	CTFCore core;
	public playerJoinListener(CTFCore ctfCore) {this.core = ctfCore;}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.teleport(core.getSpawn());
		String joinMess = core.utils.colorMessage(core.getConfig().getString("CTFCore.Join.Message"));
		joinMess = joinMess.replace("<player>", p.getName());
		Bukkit.broadcastMessage(joinMess);
		boolean setCrea = core.getConfig().getBoolean("CTFCore.Join.setCreaGMtoOp");
		if(setCrea){
			if(p.isOp()){
				p.setGameMode(GameMode.CREATIVE);
			}else{
				p.setGameMode(GameMode.SURVIVAL);
			}
		}else{
			p.setGameMode(GameMode.SURVIVAL);
		}
	}

}
