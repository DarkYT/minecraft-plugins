package fr.dark.ctf.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.dark.ctf.CTF;

public class playerJoinListener implements Listener {
	
	CTF core;
	public playerJoinListener(CTF ctf) {
		this.core = ctf;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.teleport(core.getSpawn());
		String joinMess = core.utils.colorMessage(core.getConfig().getString("CTF.Join.Message"));
		joinMess = joinMess.replace("<player>", p.getName());
		Bukkit.broadcastMessage(joinMess);
		boolean setCrea = core.getConfig().getBoolean("CTF.Join.setCreaGMtoOp");
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
