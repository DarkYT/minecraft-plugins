package fr.endwork.gotuhc.module.queue;

import fr.endwork.gotuhc.GameCore;
import fr.endwork.gotuhc.event.game.GameStartEvent;
import fr.endwork.gotuhc.event.player.PlayerInitEvent;
import fr.endwork.gotuhc.game.GameState;
import fr.endwork.gotuhc.module.IModule;
import fr.endwork.gotuhc.module.LifeCycle;
import fr.endwork.gotuhc.module.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@Module(lifeCycle = LifeCycle.GAME)
public class QueueModule implements IModule, Listener {

    private final List<UUID> initialized = new ArrayList<>();

    @Override
    public void onEnable() {
        GameCore.registerEvents(this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    /**
     * Calls a {@link PlayerInitEvent} for all online players at the start of the game.
     *
     * @param event The event
     */
    @EventHandler
    public void onGameStart(GameStartEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            //TODO: Only run if the player is playing in the game (not spectating)
            Bukkit.getPluginManager().callEvent(new PlayerInitEvent(player, false));
            initialized.add(player.getUniqueId());
        }
    }

    /**
     * Calls a {@link PlayerInitEvent} for any player that joins after the game starts and hasn't been initialized yet.
     *
     * @param event The event
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        //TODO: Only run if the player is playing in the game (not spectating)
        if (GameCore.getCurrentGame().getState().equals(GameState.PLAYING) && !initialized.contains(uuid)) {
            initialized.add(uuid);
            Bukkit.getPluginManager().callEvent(new PlayerInitEvent(player, true));
        }
    }

}
