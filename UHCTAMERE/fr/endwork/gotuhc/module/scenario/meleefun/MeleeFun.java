package fr.endwork.gotuhc.module.scenario.meleefun;

import fr.endwork.gotuhc.GameCore;
import fr.endwork.gotuhc.module.IModule;
import fr.endwork.gotuhc.module.LifeCycle;
import fr.endwork.gotuhc.module.Module;
import fr.endwork.gotuhc.module.Scenario;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

@Module(lifeCycle = LifeCycle.GAME)
@Scenario(name = "Melee Fun", desc = "There is no hit cooldown, but damage is reduced.")
public class MeleeFun implements IModule, Listener {

    //TODO: Implement as scenario setting
    public double damageReduction = 0.1;

    @Override
    public void onEnable() {
        GameCore.registerEvents(this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    /**
     * Removes the hit cooldown upon a player attack and reduces the amount of damage dealt according to a modifier.
     *
     * @param event The event
     */
    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player
                && event.getDamager() instanceof Player
                && event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            event.setDamage(event.getDamage() * damageReduction);
            Bukkit.getScheduler().runTask(GameCore.getInstance(), () -> ((Player) entity).setNoDamageTicks(0));
        }
    }

}
