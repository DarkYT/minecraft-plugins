package com.endwork.api.ranks.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerAddRankEvent extends Event implements Cancellable {

    private static final HandlerList handlerList = new HandlerList();
    private boolean isCancelled;
    private final Player player;
    private final String playerName, addedRank;

    public PlayerAddRankEvent(Player player, String addedRank) {
        this.player = player;
        this.playerName = player.getName();
        this.addedRank = addedRank;
        this.isCancelled = false;
    }

    public PlayerAddRankEvent(String playerName, String addedRank) {
        this.player = null;
        this.playerName = playerName;
        this.addedRank = addedRank;
        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public Player getPlayer() {
        return player;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getAddedRank() {
        return addedRank;
    }
}
