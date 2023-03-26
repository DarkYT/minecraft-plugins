package fr.endwork.gotuhc.module.scenario.baldchickens;



import java.util.ArrayList;
import java.util.List;

import fr.endwork.gotuhc.GameCore;
import fr.endwork.gotuhc.module.IModule;
import fr.endwork.gotuhc.module.LifeCycle;
import fr.endwork.gotuhc.module.Module;
import fr.endwork.gotuhc.module.Scenario;
import fr.endwork.gotuhc.util.Numbers;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

@Module(lifeCycle = LifeCycle.GAME)
@Scenario(
    name = "Bald Chickens",
    desc = "Chickens do not drop feathers, and arrows may be obtained by killing Skeletons."
)
public class BaldChickens implements IModule, Listener {

  @Override
  public void onEnable() {
    GameCore.registerEvents(this);
  }

  @Override
  public void onDisable() {
    HandlerList.unregisterAll(this);
  }

  /**
   * Removes feathers as drops from chickens, and ensure that skeletons always drop a few arrows.
   *
   * @param event The event
   */
  @EventHandler
  public void onEntityDeath(EntityDeathEvent event) {
    Entity entity = event.getEntity();
    if (entity instanceof Chicken) {
      List<ItemStack> drops = event.getDrops();
      List<ItemStack> feathers = new ArrayList<>();
      for (ItemStack drop : drops) {
        if (drop.getType().equals(Material.FEATHER)) {
          feathers.add(drop);
        }
      }
      drops.removeAll(feathers);
    }

    if (entity instanceof Skeleton) {
      List<ItemStack> drops = event.getDrops();
      List<ItemStack> arrows = new ArrayList<>();
      for (ItemStack drop : drops) {
        if (drop.getType().equals(Material.ARROW)) {
          arrows.add(drop);
        }
      }
      drops.removeAll(arrows);

      drops.add(new ItemStack(Material.ARROW, Numbers.random(3, 5)));
    }
  }

}
