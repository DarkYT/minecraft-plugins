package com.endwork.api.ranks.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerRemoveRankEvent extends Event implements Cancellable {

    private static final HandlerList handlerList = new HandlerList();
    private boolean isCancelled;
    private final Player player;
    private final String playerName, removedRank;

    public PlayerRemoveRankEvent(Player player, String removedRank) {
        this.player = player;
        this.playerName = player.getName();
        this.removedRank = removedRank;
        this.isCancelled = false;
    }

    public PlayerRemoveRankEvent(String playerName, String removedRank) {
        this.playerName = playerName;
        this.player = null;
        this.removedRank = removedRank;
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

    public Player getPlayer() {
        return player;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getRemovedRank() {
        return removedRank;
    }
}
