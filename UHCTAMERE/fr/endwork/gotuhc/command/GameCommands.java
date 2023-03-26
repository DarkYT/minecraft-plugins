package fr.endwork.gotuhc.command;

import fr.endwork.gotuhc.GameCore;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GameCommands implements CommandExecutor {

  private static final String ERROR_NO_PERM = ChatColor.RED + "You do not have permission to execute this command.";

  @Override
  public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
    // TODO permissions > op
    if (args.length == 0) {
      // TODO game status?
      return false;
    } else {
      if (!sender.isOp()) {
        sender.sendMessage(ERROR_NO_PERM);
        return true;
      }
      GameCore.getPluginLogger().info(GameCore.getCurrentGame().getState().toString());
      GameCore.getPluginLogger().info("" + GameCore.getCurrentGame().isBusy());
      switch (args[0]) {
        case "generate":
        case "gen":
          try {
            GameCore.getCurrentGame().generate();
            sender.sendMessage(ChatColor.GREEN + "Generating chunks begun.");
          } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Unable to generate chunks at this time.");
          }
          break;
        case "scatter":
          try {
            GameCore.getCurrentGame().scatter();
            sender.sendMessage(ChatColor.GREEN + "Scattering players has begun.");
          } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Unable to scatter players at this time.");
            e.printStackTrace();
          }
          break;
        case "start":
          try {
            GameCore.getCurrentGame().start();
            sender.sendMessage(ChatColor.GREEN + "The game has successfully started.");
          } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Unable to start game at this time.");
          }
          break;
        case "stop":
        case "end":
          try {
            GameCore.getCurrentGame().end();
            sender.sendMessage(ChatColor.GREEN + "The game has successfully ended.");
          } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Unable to end game at this time.");
          }
          break;
        default:
          return false;
      }
    }
    return true;
  }

}
