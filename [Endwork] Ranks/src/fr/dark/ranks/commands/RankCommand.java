package fr.dark.ranks.commands;

import com.endwork.api.ranks.Rank;
import com.endwork.api.ranks.RankAPI;
import fr.dark.ranks.RankCore;
import fr.dark.ranks.managers.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String message, String[] args) {
        if(command.getName().equalsIgnoreCase("ranks")){
            if(!(sender instanceof Player)){
                sender.sendMessage("§4[Ranks]§c Vous devez etre un joueur connecte pour acceder a l'interface utilisateur");
                return false;
            }
            Player executor = (Player) sender;
            if(args.length == 0){
                if(RankAPI.hasPermission(executor, "rank.manage")){
                    InventoryManager.displayChoosingScreen(executor);
                    return true;
                }else{
                    executor.sendMessage("§4[Ranks]§c Vous n'avez pas la permission d'exécuter cette commande");
                    return false;
                }
            }
            if(args.length == 1){
                Player player = Bukkit.getPlayer(args[0]);
                if(player == null){
                    //The executor may wants to display rank's permissions
                    if(RankAPI.rankExists(args[0])){
                        //The executor wants to display rank's permissions
                        InventoryManager.displayRankPermissionsManager(args[0], executor);
                        return true;
                    }else{
                        executor.sendMessage("§4[Ranks]§c Votre commande est erronée (/ranks [player/rank])");
                        return false;
                    }
                }else{
                    //The executor wants to display player's ranks
                    InventoryManager.displayPlayerRanksManager(args[0], executor);
                    return true;
                }
            }
        }else if(command.getName().equalsIgnoreCase("rank")){
            if(args.length == 1){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    if(args[0].equalsIgnoreCase("enable")){
                        if(RankAPI.hasPermission(player, "rank.hide")){
                            RankCore.displayActiveRank.replace(player, true);
                            RankCore.refreshPlayer(player);
                            player.sendMessage("§2[Ranks]§a Vous venez d'activer la visibilité de votre grade");
                            return true;
                        }else{
                            player.sendMessage("§4[Ranks]§c Vous n'avez pas le droit d'exécuter cette commande");
                            return false;
                        }
                    }else if(args[0].equalsIgnoreCase("disable")){
                        if(RankAPI.hasPermission(player, "rank.hide")){
                            RankCore.displayActiveRank.replace(player, false);
                            RankCore.refreshPlayer(player);
                            player.sendMessage("§2[Ranks]§a Vous venez de désactiver la visibilité de votre grade");
                            return true;
                        }else{
                            player.sendMessage("§4[Ranks]§c Vous n'avez pas le droit d'exécuter cette commande");
                            return false;
                        }
                    }else{
                        player.sendMessage("§4[Ranks]§c La commande entrée n'est pas valide");
                        sendRankHelp(sender);
                        return false;
                    }
                }
            }
            if(args.length == 2){
                if(args[0].equalsIgnoreCase("delete")){
                    String rankName = args[1];
                    if(!RankAPI.rankExists(rankName)){
                        sender.sendMessage("§4[Ranks]§c Le grade indiqué n'existe pas");
                        sendRankList(sender);
                        return false;
                    }
                    if(sender instanceof Player){
                        if(!RankAPI.hasPermission((Player) sender, "rank.delete")){
                            sender.sendMessage("§4[Ranks]§c Vous n'avez pas la permission d'exécuter cette commande");
                            return false;
                        }
                    }

                    RankAPI.deleteRank(rankName);
                    sender.sendMessage("§2[Ranks]§a Vous avez supprimer le grade §2" + rankName + "§a avec succès");
                }
            }
            if(args.length == 5){
                if(args[0].equalsIgnoreCase("create")){
                    String name = args[1];
                    String prefix = args[2];
                    String color = args[3];
                    String strength = args[4];

                    if(sender instanceof Player){
                        if(!RankAPI.hasPermission((Player) sender, "rank.create")){
                            sender.sendMessage("§4[Rank] §cVous n'avez pas la permission d'exécuter cette commande");
                            return false;
                        }
                    }

                    if(RankCore.isNumeric(strength)){
                        RankAPI.createRank(Integer.parseInt(strength), name, prefix, color);
                        sender.sendMessage("§2[Ranks]§a Vous avez créé le grade avec succès");
                        return true;
                    }else{
                        sender.sendMessage("§4[Ranks]§c Il y a une erreur dans la commande");
                        sendRankHelp(sender);
                        return false;
                    }
                }
            }
            sendRankHelp(sender);
        }else if(command.getName().equalsIgnoreCase("permission")){
            if(args.length == 2){
                if(args[0].equalsIgnoreCase("create")){
                    if(sender instanceof Player){
                        if(!RankAPI.hasPermission((Player) sender, "rank.create")){
                            sender.sendMessage("§4[Ranks]§c Vous n'avez pas la permission d'exécuter cette commande");
                            return false;
                        }
                    }
                    if(args[1].contains(".")){
                        String perm = args[1].toLowerCase();
                        if(RankAPI.permExists(perm)){
                            sender.sendMessage("§4[Ranks]§c La permission existe déjà");
                            return false;
                        }
                        RankAPI.createPerm(perm);
                        sender.sendMessage("§2[Ranks]§a La permission a été créée avec succès");
                        return true;
                    }else{
                        sender.sendMessage("§4[Ranks]§c Veuillez respecter le format: §c§ldomaine.action");
                        return false;
                    }
                }else if(args[0].equalsIgnoreCase("delete")){
                    if(sender instanceof Player){
                        if(!RankAPI.hasPermission((Player) sender, "rank.delete")){
                            sender.sendMessage("§4[Ranks]§c Vous n'avez pas la permission d'exécuter cette commande");
                            return false;
                        }
                    }
                    if(RankAPI.permExists(args[1])){
                        RankAPI.deletePerm(args[1]);
                        sender.sendMessage("§2[Ranks]§a La permission a été supprimée avec succès");
                        return true;
                    }else{
                        sender.sendMessage("§4[Ranks]§c La permission que vous souhaitez supprimer n'existe pas");
                        sendPermList(sender);
                        return false;
                    }
                }
                sendRankHelp(sender);
            }
        }
        return false;
    }

    private void sendPermList(CommandSender sender) {
        StringBuilder allPerms = new StringBuilder();
        for(String perm : RankAPI.getRegisteredPermissions()){
            allPerms.append(perm).append(", ");
        }
        sender.sendMessage("§4[Ranks]§c Permissions: " + allPerms.substring(0, allPerms.length()-2));
    }

    private void sendRankList(CommandSender sender) {
        StringBuilder allRanks = new StringBuilder();
        for(Rank rank : RankAPI.getRegisteredRanks()){
            allRanks.append(rank.getName()).append(", ");
        }
        sender.sendMessage("§4[Ranks]§c Grades: "+allRanks.substring(0, allRanks.length()-2));
    }

    private void sendRankHelp(CommandSender sender) {
        sender.sendMessage("§6←--- Ranks ---→");
        sender.sendMessage("§6/rank enable/disable §eActive/Désactive la visibilité de votre grade");
        sender.sendMessage("§6/rank create [name] [prefix] [color (without &)] [strentgh] §eCrée un grade avec les caractéristiques demandés");
        sender.sendMessage("§6/rank delete [name] §eSupprime le grade indiqué");
        sender.sendMessage("§6/ranks §eOuvre l'interface de gestion générale");
        sender.sendMessage("§6/ranks [player/rank] §eOuvre l'interface de gestion du joueur/grade");
        sender.sendMessage("§6/permission create [name] §eCrée la permission indiquée");
        sender.sendMessage("§6/permission delete [name] §eSupprime la permission indiquée");
        sender.sendMessage("§6←-------------→");
    }
}
