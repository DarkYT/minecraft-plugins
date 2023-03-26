package fr.dark.ranks.managers;

import com.endwork.api.inventory.GUI;
import com.endwork.api.inventory.PaginatedGUI;
import com.endwork.api.inventory.buttons.GUIButton;
import com.endwork.api.items.ItemBuilder;
import com.endwork.api.ranks.Rank;
import com.endwork.api.ranks.RankAPI;
import fr.dark.ranks.RankCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

    public static void displayPlayersInventory(Player player){
        PaginatedGUI playersInventory = new PaginatedGUI("&8Joueurs connectés");
        for(Player pl : Bukkit.getOnlinePlayers()){
            GUIButton button = new GUIButton(RankCore.getHead(pl));
            button.setListener(e -> {
                e.setCancelled(true);
                ItemStack current = e.getCurrentItem();
                if(current == null || current.getType() == Material.AIR) return;
                if(!current.hasItemMeta()) return;
                if(!current.getItemMeta().hasDisplayName()) return;
                String playerName = ChatColor.stripColor(current.getItemMeta().getDisplayName().split(" ")[1]);

                e.getWhoClicked().closeInventory();
                displayPlayerRanksManager(playerName, (Player) e.getWhoClicked());
            });
            playersInventory.addButton(button);
        }
        player.openInventory(playersInventory.getInventory());
    }

    public static void displayRanksInventory(Player player){
        PaginatedGUI ranksInventory = new PaginatedGUI("&8Grades existants");
        if(RankAPI.getRegisteredRanks().isEmpty()){
            player.sendMessage("§4[Ranks]§c Il n'y a pas de grades enregistrés dans la base de données");
            return;
        }
        for(Rank rank : RankAPI.getRegisteredRanks()){
            GUIButton button = new GUIButton(ItemBuilder.start(Material.CHEST).name("&8" + rank.getName()).lore("", "&8Cliquez pour modifier le grade").build());
            button.setListener(e -> {
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
                displayRankPermissionsManager(rank.getName(), (Player) e.getWhoClicked());
            });
            ranksInventory.addButton(button);
        }
        player.openInventory(ranksInventory.getInventory());
    }

    public static void displayRankPermissionsManager(String rankName, Player player){
        PaginatedGUI ranksPermissions = new PaginatedGUI("&8Permissions de: " + rankName);
        if(RankAPI.getRegisteredPermissions().isEmpty()){
            player.sendMessage("§4[Ranks]§c Il n'y a pas de permissions enregistrées dans la base de données");
            return;
        }
        for(String permission : RankAPI.getRegisteredPermissions()){
            if((RankAPI.hasPerm(rankName, getGlobalPermission(permission)) || RankAPI.hasPerm(rankName, "*")) && !(permission.equalsIgnoreCase("*"))) continue;
            if(RankAPI.hasPerm(rankName, permission)){
                GUIButton button = new GUIButton(ItemBuilder.start(Material.GREEN_TERRACOTTA).name("&2"+permission).lore("","&aPermission possédée", "&8Cliquez pour l'enlever").build());
                button.setListener(e -> {
                    e.setCancelled(true);
                    RankAPI.removePerm(rankName, permission);
                    displayRankPermissionsManager(rankName, player);
                });
                ranksPermissions.addButton(button);
            }else{
                GUIButton button = new GUIButton(ItemBuilder.start(Material.RED_TERRACOTTA).name("&4"+permission).lore("","&cPermission non possédée", "&8Cliquez pour l'ajouter").build());
                button.setListener(e -> {
                    e.setCancelled(true);
                    RankAPI.addPerm(rankName, permission);
                    displayRankPermissionsManager(rankName, player);
                });
                ranksPermissions.addButton(button);
            }
        }

        player.openInventory(ranksPermissions.getInventory());
    }

    public static void displayPlayerRanksManager(String playerName, Player player) {
        PaginatedGUI playerRanks = new PaginatedGUI("&8Grades de: " + playerName);
        if(RankAPI.getRegisteredRanks().isEmpty()){
            player.sendMessage("§4[Ranks]§c Il n'y a pas de grades enregistrés dans la base de données");
            return;
        }
        for(Rank rank : RankAPI.getRegisteredRanks()){
            if(rank.getName().equals("member")) continue;
            if(RankAPI.hasRank(playerName, rank.getName())){
                GUIButton button = new GUIButton(ItemBuilder.start(Material.GREEN_TERRACOTTA).name("&2" + rank.getName()).lore("","&aGrade possédé", "&8Cliquez pour l'enlever").build());
                button.setListener(e -> {
                    e.setCancelled(true);
                    RankAPI.removeRank(playerName, rank.getName());
                    displayPlayerRanksManager(playerName, player);
                    RankCore.refreshPlayers();
                });
                playerRanks.addButton(button);
            }else{
                GUIButton button = new GUIButton(ItemBuilder.start(Material.RED_TERRACOTTA).name("&4" + rank.getName()).lore("","&cGrade non possédé", "&8Cliquez pour l'ajouter").build());
                button.setListener(e -> {
                    e.setCancelled(true);
                    RankAPI.addRank(playerName, rank.getName());
                    displayPlayerRanksManager(playerName, player);
                    RankCore.refreshPlayers();
                });
                playerRanks.addButton(button);
            }
        }

        player.openInventory(playerRanks.getInventory());
    }

    public static void displayChoosingScreen(Player player){
        GUI inventory = new GUI("&8Gestion des grades", 9);
        GUIButton players = new GUIButton(ItemBuilder.start(Material.PLAYER_HEAD).name("&8Joueurs").lore("","&8Gestion des grades attribués aux", "&8différents joueurs sur le serveur").build());
        players.setListener(e -> {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
            displayPlayersInventory((Player) e.getWhoClicked());
        });
        GUIButton ranks = new GUIButton(ItemBuilder.start(Material.CHEST).name("&8Grades").lore("","&8Gestion des grades et de leurs ", "&8différentes permissions.").build());
        ranks.setListener(e -> {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
            displayRanksInventory((Player) e.getWhoClicked());
        });
        GUIButton glass = new GUIButton(ItemBuilder.start(Material.BLACK_STAINED_GLASS_PANE).name(" ").build());
        glass.setListener(e -> e.setCancelled(true));
        inventory.setButton(3, players);
        inventory.setButton(5, ranks);
        for(int i = 0; i < 9; i++){
            if(i!=3 && i!=5) inventory.setButton(i,glass);
        }
        player.openInventory(inventory.getInventory());
    }

    private static String getGlobalPermission(String permission){
        if(permission.contains("*")) return "*";
        return permission.split("\\.")[0]+".*";
    }
}
