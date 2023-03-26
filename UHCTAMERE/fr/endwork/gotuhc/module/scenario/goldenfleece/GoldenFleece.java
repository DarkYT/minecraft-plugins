package fr.endwork.gotuhc.module.scenario.goldenfleece;

import fr.endwork.gotuhc.GameCore;
import fr.endwork.gotuhc.module.IModule;
import fr.endwork.gotuhc.module.LifeCycle;
import fr.endwork.gotuhc.module.Module;

import fr.endwork.gotuhc.module.Scenario;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

@Module(lifeCycle = LifeCycle.GAME)
@Scenario(
    name = "Golden Fleece",
    desc = "When a sheep is killed, either resources will be dropped, or a skeleton in gold armor will be spawned."
)
public class GoldenFleece implements IModule, Listener {

  @Override
  public void onEnable() {
    GameCore.registerEvents(this);
  }

  @Override
  public void onDisable() {
    HandlerList.unregisterAll(this);
  }

  /**
   * Utilizes a random chance to either spawn a skeleton with gold armor or resources.
   *
   * @param event The event
   */
  @EventHandler
  public void onEntityDeath(EntityDeathEvent event) {
    Entity entity = event.getEntity();
    if (entity instanceof Sheep) {
      double chance = Math.random();
      if (0.25 > chance) {
        Skeleton skeleton = entity.getWorld().spawn(entity.getLocation(), Skeleton.class);
        skeleton.getEquipment().setArmorContents(
            new ItemStack[]{
                new ItemStack(Material.GOLDEN_HELMET),
                new ItemStack(Material.GOLDEN_CHESTPLATE),
                new ItemStack(Material.GOLDEN_LEGGINGS),
                new ItemStack(Material.GOLDEN_BOOTS)
            }
        );
      } else if (0.5 > chance) {
        event.getDrops().add(new ItemStack(Material.IRON_INGOT));
      } else if (0.75 > chance) {
        event.getDrops().add(new ItemStack(Material.GOLD_INGOT));
      } else {
        event.getDrops().add(new ItemStack(Material.DIAMOND));
      }
    }
  }

}
