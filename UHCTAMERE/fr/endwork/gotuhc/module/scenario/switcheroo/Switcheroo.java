package fr.endwork.gotuhc.module.scenario.switcheroo;

import fr.endwork.gotuhc.GameCore;
import fr.endwork.gotuhc.module.IModule;
import fr.endwork.gotuhc.module.LifeCycle;
import fr.endwork.gotuhc.module.Module;
import fr.endwork.gotuhc.module.Scenario;

import fr.endwork.gotuhc.module.Setting;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

@Module(lifeCycle = LifeCycle.GAME)
@Scenario(name = "Switcheroo", desc = "Players swap locations when one of them is shot by the other.")
public class Switcheroo implements IModule, Listener {

  @Setting("Swap velocity")
  private boolean swapVelocity = true;

  @Override
  public void onEnable() {
    GameCore.registerEvents(this);
  }

  @Override
  public void onDisable() {
    HandlerList.unregisterAll(this);
  }

  /**
   * Swaps player position when a player shoots another player and swaps their velocities as well, if enabled.
   *
   * @param event The event
   */
  @EventHandler(ignoreCancelled = true)
  public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
    Entity entity = event.getEntity();
    Entity arrow = event.getDamager();
    if (entity instanceof Player && arrow instanceof Arrow) {
      ProjectileSource source = ((Arrow) arrow).getShooter();
      if (source instanceof Player) {
        Player shooter = (Player) source;

        Bukkit.getScheduler().runTask(GameCore.getInstance(), () -> {
          final Location location = entity.getLocation();
          entity.teleport(shooter);
          shooter.teleport(location);

          if (swapVelocity) {
            final Vector velocity = entity.getVelocity();
            entity.setVelocity(shooter.getVelocity());
            shooter.setVelocity(velocity);
          } else {
            Vector velocity = new Vector(0, 0, 0);
            entity.setVelocity(velocity);
            shooter.setVelocity(velocity);
          }
        });
      }
    }
  }

}
