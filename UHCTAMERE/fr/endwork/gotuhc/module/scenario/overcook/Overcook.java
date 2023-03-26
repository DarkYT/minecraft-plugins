package fr.endwork.gotuhc.module.scenario.overcook;

import fr.endwork.gotuhc.GameCore;
import fr.endwork.gotuhc.module.IModule;
import fr.endwork.gotuhc.module.LifeCycle;
import fr.endwork.gotuhc.module.Module;
import fr.endwork.gotuhc.module.Scenario;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;

@Module(lifeCycle = LifeCycle.GAME)
@Scenario(name = "Overcook", desc = "A furnace smelts all items at once but explodes when it is finished.")
public class Overcook implements IModule, Listener {

  @Override
  public void onEnable() {
    GameCore.registerEvents(this);
  }

  @Override
  public void onDisable() {
    HandlerList.unregisterAll(this);
  }

  /**
   * Handles the action of smelting all items at once, exploding the furnace, and dropping the smelted items on the
   * ground.
   *
   * @param event The event
   */
  @EventHandler(ignoreCancelled = true)
  public void onFurnaceSmelt(FurnaceSmeltEvent event) {
    ItemStack resultItem = event.getResult();
    final Material result = resultItem.getType();
    //TODO: Verify that the "smelting amount" contains any extra ingredients
    final int amount = ((Furnace) event.getBlock().getState()).getInventory().getSmelting().getAmount();

    event.getSource().setType(Material.AIR);
    resultItem.setType(Material.AIR);

    Block block = event.getBlock();
    block.setType(Material.AIR);
    Location location = block.getLocation().add(0.5, 0.5, 0.5);
    World world = location.getWorld();
    world.createExplosion(location, 7);
    world.dropItem(location, new ItemStack(result, amount));
  }

}
