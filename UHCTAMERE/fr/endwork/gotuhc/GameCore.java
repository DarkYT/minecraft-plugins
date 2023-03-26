package fr.endwork.gotuhc;

import fr.endwork.gotuhc.command.GameCommands;
import fr.endwork.gotuhc.command.TeamCommands;
import fr.endwork.gotuhc.game.Game;
import fr.endwork.gotuhc.module.ModuleData;
import fr.endwork.gotuhc.module.ModuleFactory;
import fr.endwork.gotuhc.module.ModuleHandler;
import fr.endwork.gotuhc.module.ModuleRegistry;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@Getter
public class GameCore extends JavaPlugin {

    public static final String WORLD_DIR_PREFIX = "GoTUHC_worlds/";

    @Getter private static GameCore instance;

    private ModuleHandler moduleHandler;
    private Game game;

    /**
     * Enable GoT UHC
     */
    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Game of Throne UHC has started.");

        //Load modules
        final ModuleFactory loader = new ModuleFactory();
        loader.findEntries(getFile());
        ModuleRegistry.registerModules(loader);
        moduleHandler = new ModuleHandler();
        ModuleRegistry.getServerModules().forEach(data -> {
            moduleHandler.enableModule(data.getClazz());
        });
        // Load default modules
        for (ModuleData data : ModuleRegistry.getServerModules()) {
            if (data.isEnabledOnStart()) {
                moduleHandler.enableModule(data.getClazz());
            }
        }

        registerCommands();

        // Create first game.
        game = new Game();
        game.initialize();

    }

    @Override
    public void onDisable() {
        moduleHandler.disableAllModules();
    }

    public static void registerEvents(Listener events) {
        Bukkit.getPluginManager().registerEvents(events, instance);
    }

    public static Game getCurrentGame() {
        return getInstance().getGame();
    }

    public static Logger getPluginLogger() {
        return instance.getLogger();
    }

    private void registerCommands() {
        getCommand("team").setExecutor(new TeamCommands());
        getCommand("game").setExecutor(new GameCommands());
    }

}
