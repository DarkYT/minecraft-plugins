package com.endwork.api.party;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.HashSet;
import java.util.Set;

public class WaitForConfirm implements Listener {

    public Set<Player> players = new HashSet<Player>();;

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(PlayerChatEvent e){
        Player p = e.getPlayer();
        if(!players.contains(p))
            return;

        e.setCancelled(true);

        switch (e.getMessage()){

            case "OUI":
                APIParty.getInstance().getParty(p).playerRemove(p);
                APIParty.getInstance().joinParty(p, APIParty.getInstance().invites.get(p));
                break;

            case "NON":
                players.remove(p);
                APIParty.getInstance().msg.sendSuccessCancelChat(p);
                break;
            default:
                APIParty.getInstance().msg.notCorrectEnterValue(p);
        }

    }

    public void addWaitingPlayer(Player p) {
        players.add(p);
    }
}
