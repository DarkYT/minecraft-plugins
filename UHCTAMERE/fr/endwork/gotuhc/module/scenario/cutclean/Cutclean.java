package fr.endwork.gotuhc.module.scenario.cutclean;

import fr.endwork.gotuhc.GameCore;
import fr.endwork.gotuhc.module.IModule;
import fr.endwork.gotuhc.module.LifeCycle;
import fr.endwork.gotuhc.module.Module;
import fr.endwork.gotuhc.module.Scenario;
import fr.endwork.gotuhc.module.blockdrop.BlockDrop;
import fr.endwork.gotuhc.module.blockdrop.BlockDropModule;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

@Module(lifeCycle = LifeCycle.GAME)
@Scenario(name = "Cutclean", desc = "Resources are harvested in their smelted form.")
public class Cutclean implements IModule, Listener {

    @Override
    public void onEnable() {
        GameCore.registerEvents(this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    /**
     * Drops the smelted form of items that spawn when a block is broken.
     *
     * @param event The event
     */
    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material type = block.getType();

        Location location = block.getLocation().add(0.5, 0.5, 0.5);
        World world = location.getWorld();
        if(world == null) return;

        if (type.equals(Material.IRON_ORE)) {
            event.setCancelled(true);
            block.setType(Material.AIR);
            world.dropItem(location, new ItemStack(Material.IRON_INGOT));

            ExperienceOrb xpOrb = world.spawn(location, ExperienceOrb.class);
            xpOrb.setExperience(4);
        } else if (type.equals(Material.GOLD_ORE)) {
            event.setCancelled(true);
            block.setType(Material.AIR);
            world.dropItem(location, new ItemStack(Material.GOLD_INGOT));

            ExperienceOrb xpOrb = world.spawn(location, ExperienceOrb.class);
            xpOrb.setExperience(8);
        } else if (type.equals(Material.POTATO)) {
            event.setCancelled(true);
            block.setType(Material.AIR);
            world.dropItem(location, new ItemStack(Material.BAKED_POTATO));

            ExperienceOrb xpOrb = world.spawn(location, ExperienceOrb.class);
            xpOrb.setExperience(2);
        }
    }

    /**
     * Drops the smelted form of items that spawn when a mob is killed.
     *
     * @param event The event
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Chicken) {
            for (ItemStack item : event.getDrops()) {
                if (item.getType().equals(Material.CHICKEN)) {
                    item.setType(Material.COOKED_CHICKEN);
                }
            }
        } else if (entity instanceof Cow) {
            for (ItemStack drop : event.getDrops()) {
                if (drop.getType().equals(Material.BEEF)) {
                    drop.setType(Material.COOKED_BEEF);
                }
            }
        } else if (entity instanceof Pig) {
            for (ItemStack item : event.getDrops()) {
                if (item.getType().equals(Material.PORKCHOP)) {
                    item.setType(Material.COOKED_PORKCHOP);
                }
            }
        } else if (entity instanceof Rabbit) {
            for (ItemStack item : event.getDrops()) {
                if (item.getType().equals(Material.RABBIT)) {
                    item.setType(Material.COOKED_RABBIT);
                }
            }
        } else if (entity instanceof Sheep) {
            for (ItemStack item : event.getDrops()) {
                if (item.getType().equals(Material.MUTTON)) {
                    item.setType(Material.COOKED_MUTTON);
                }
            }
        }
    }

    /**
     * Drops cooked fish whenever a player is fishing and receives raw fish.
     *
     * @param event The event
     */
    @EventHandler(ignoreCancelled = true)
    public void onPlayerFish(PlayerFishEvent event) {
        Entity caught = event.getCaught();
        if (caught instanceof ItemStack) {
            ItemStack fish = (ItemStack) caught;
            if (fish.getType().equals(Material.LEGACY_RAW_FISH)) {
                fish.setType(Material.LEGACY_COOKED_FISH);
            }
        }
    }

}
