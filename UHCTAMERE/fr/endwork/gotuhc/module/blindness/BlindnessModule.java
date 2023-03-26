package fr.endwork.gotuhc.module.blindness;

import fr.endwork.gotuhc.GameCore;
import fr.endwork.gotuhc.event.game.GameScatterEvent;
import fr.endwork.gotuhc.event.game.GameStartEvent;
import fr.endwork.gotuhc.module.IModule;
import fr.endwork.gotuhc.module.LifeCycle;
import fr.endwork.gotuhc.module.Module;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Module(lifeCycle = LifeCycle.GAME, enableOnStart = true)
public class BlindnessModule implements IModule, Listener {

    private static final PotionEffect[] EFFECTS = {
            new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 127, false),
            new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 127, false),
            new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, -128, false),
            new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 127, false),
            new PotionEffect(PotionEffectType.HEAL, Integer.MAX_VALUE, 127, false),
    };

    @Override
    public void onEnable() {
        GameCore.registerEvents(this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void onScatter(GameScatterEvent event) {
        for (UUID uuid : GameCore.getCurrentGame().getAlivePlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                for (PotionEffect effect : EFFECTS) {
                    player.addPotionEffect(effect);
                }
            }
        }
    }

    @EventHandler
    public void onStart(GameStartEvent event) {
        for (UUID uuid : GameCore.getCurrentGame().getAlivePlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                for (PotionEffect effect : EFFECTS) {
                    player.removePotionEffect(effect.getType());
                }
            }
        }

    }

}
