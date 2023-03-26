package fr.dark.ranks.events;

import com.endwork.api.APICore;
import com.endwork.api.ranks.RankAPI;
import fr.dark.ranks.RankCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(!RankCore.displayActiveRank.containsKey(player))
            RankCore.displayActiveRank.put(player, true);

        if(!APICore.getInstance().getDatabase().isRegistered(player)){
            APICore.getInstance().getDatabase().register(player);
            RankAPI.addRank(player, "member");
        }

        RankCore.refreshPlayer(player);
    }

}
