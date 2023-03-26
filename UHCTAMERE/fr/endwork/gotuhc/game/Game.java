package fr.endwork.gotuhc.game;

import fr.endwork.gotuhc.GameCore;
import fr.endwork.gotuhc.event.game.GameEndEvent;
import fr.endwork.gotuhc.event.game.GameScatterEvent;
import fr.endwork.gotuhc.event.game.GameStartEvent;
import fr.endwork.gotuhc.game.exception.BusyGameStateException;
import fr.endwork.gotuhc.game.exception.InvalidGameStateException;
import fr.endwork.gotuhc.module.ModuleHandler;
import fr.endwork.gotuhc.module.ModuleRegistry;
import fr.endwork.gotuhc.team.Team;
import fr.endwork.gotuhc.util.Numbers;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

@Getter
public class Game {

    private World world;
    private final ModuleHandler moduleHandler = new ModuleHandler();
    private final List<UUID> alivePlayers = new ArrayList<>();
    private final List<UUID> spectators = new ArrayList<>();

    private final List<Team> teams = new ArrayList<>();

    private GameState state = GameState.NEW;

    private boolean busy = false;

    @Getter(AccessLevel.NONE) private int chunkX;
    @Getter(AccessLevel.NONE) private int chunkY;

    private int worldRadius = 100; // TODO: configuration

    /**
     * Initializes the game object.
     */
    public void initialize() {
        ModuleRegistry.getGameModules().forEach(moduleData -> {
            if (moduleData.isEnabledOnStart()) {
                moduleHandler.enableModule(moduleData.getClazz());
            }
        });
    }

    /**
     * De-initializes the game object.
     */
    public void terminate() {
        moduleHandler.disableAllModules();
    }

    /**
     * Generates the chunks in the game world with a given radius.
     */
    public void generate() throws BusyGameStateException, InvalidGameStateException {
        if (busy) {
            throw new BusyGameStateException();
        } else if (state != GameState.NEW) {
            throw new InvalidGameStateException(state, GameState.NEW);
        }
        busy = true;

        world = Bukkit.createWorld(new WorldCreator(
                GameCore.WORLD_DIR_PREFIX + String.valueOf(System.currentTimeMillis())));

        world.setGameRuleValue("naturalRegeneration", "false");

        final int chunkRadius = (worldRadius / 16) + 4;
        chunkX = -1 * chunkRadius;
        chunkY = -1 * chunkRadius;
        final CompletableFuture<BukkitTask> task = new CompletableFuture<>();
        task.complete(Bukkit.getScheduler().runTaskTimer(GameCore.getInstance(), () -> {
            for (int i = 0; i < 25; ++i) {
                world.loadChunk(chunkX, chunkY);
                if (chunkX == chunkRadius && chunkY == chunkRadius) {
                    try {
                        // Task completed!
                        busy = false;
                        state = GameState.GENERATED;
                        GameCore.getPluginLogger().info("World generation compete!");
                        task.get().cancel();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                } else if (chunkX == chunkRadius) {
                    chunkX = -1 * chunkRadius;
                    ++chunkY;
                    GameCore.getPluginLogger().info(chunkY + chunkRadius  + "/" + chunkRadius * 2);
                } else {
                    ++chunkX;
                }
            }
        }, 0, 1));
    }

    /**
     * Scatters players before the start of the game.
     */
    public void scatter() {
        if (busy) {
            throw new BusyGameStateException();
        } else if (state != GameState.GENERATED) {
            throw new InvalidGameStateException(state, GameState.GENERATED);
        }
        finalizeTeams();
        GameCore.getPluginLogger().info("Teams: " + teams.size());
        busy = true;

        GameScatterEvent event = new GameScatterEvent(this);
        Bukkit.getPluginManager().callEvent(event);

        Comparator<Location> locationComparator = (o1, o2) -> (int) Math.round(o1.getX() - o2.getX());

        // Bad random scatter. Should be more equal in the future
        final Queue<Location> locs = new PriorityQueue<>(locationComparator);
        for (int i = 0; i < teams.size(); ++i) {
            Location loc = new Location(world, Numbers.random(-1 * worldRadius, worldRadius), 0, Numbers.random(-1 * worldRadius, worldRadius));
            loc.setY(world.getHighestBlockYAt(loc));
            locs.add(loc);
        }

        // Teleport players over an interval. We'll want to handle offline players too TODO
        int delay = 10;
        for (Team team : teams) {
            Bukkit.getScheduler().runTaskLater(GameCore.getInstance(), () -> {
                Location loc = locs.remove();
                boolean last = locs.isEmpty();
                for (Player player : team.getOnlinePlayers()) {
                    player.teleport(loc);
                }
                if (last) {
                    busy = false;
                    state = GameState.SCATTERED;
                }
            }, delay);
            delay += 10;
        }
    }

    /**
     * Starts the game.
     */
    public void start() {
        if (busy) {
            throw new BusyGameStateException();
        } else if (state != GameState.SCATTERED) {
            throw new InvalidGameStateException(state, GameState.GENERATED);
        }

        GameStartEvent event = new GameStartEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(event);

        state = GameState.PLAYING;
    }

    /**
     * Ends the game.
     */
    public void end() {
        if (state != GameState.PLAYING) {
            throw new InvalidGameStateException(state, GameState.PLAYING);
        }

        GameEndEvent event = new GameEndEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(event);

        state = GameState.COMPLETED;
    }

    private void finalizeTeams() {
        // TODO handle spectators
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (getTeam(player) == null) {
                Team team = new Team(player);
            }
        }
        for (Team team : teams) {
            alivePlayers.addAll(team.getPlayers());
        }
    }

    /**
     * Gets the team of a player, null if the player is not on a team.
     *
     * @param player The player
     * @return The team that has the player on it
     */
    public Team getTeam(Player player) {
        for (Team team : teams) {
            if (team.hasPlayer(player)) {
                return team;
            }
        }
        return null;
    }

    /**
     * Gets the team of a player, null if the player is not on a team.
     *
     * @param player The player
     * @return The team that has the player on it
     */
    public Team getTeam(UUID player) {
        for (Team team : teams) {
            if (team.hasPlayer(player)) {
                return team;
            }
        }
        return null;
    }

}