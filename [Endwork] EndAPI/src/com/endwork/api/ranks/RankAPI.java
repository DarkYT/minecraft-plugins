package com.endwork.api.ranks;

import com.endwork.api.APICore;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankAPI {

    private static final String RANK = "t_rank";
    private static final String PLAYER = "t_player";
    private static final String PERMISSIONS = "t_permission";
    private static final String PLAYER_RANK = "link_player_rank";
    private static final String PERMS_RANK = "link_rank_perm";

    //These functions wouldn't be used with the API

    public static Rank getRank(String rankName){
        if(rankExists(rankName)){
            return (Rank) APICore.getInstance().getDatabase().query("SELECT * FROM " + RANK + " WHERE name='" + rankName + "'", rs -> {
                try {
                    if(rs.next()){
                        int id = rs.getInt("id");
                        int strength = rs.getInt("strength");
                        String name = rs.getString("name");
                        String initials = rs.getString("initials");
                        String color = rs.getString("color");

                        return new Rank(strength, name, initials, color);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            });
        }else{
            return null;
        }
    }

    public static boolean rankExists(String rankName){
        return (boolean) APICore.getInstance().getDatabase().query("SELECT * FROM " + RANK + " WHERE name='" + rankName + "'", rs -> {
            try{
                if(rs.next()){
                    return true;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        });
    }

    public static void deleteRank(String rankName){
        if(!rankExists(rankName)) return;
        APICore.getInstance().getDatabase().update("DELETE FROM " + PLAYER_RANK + " WHERE rank_id=" + getRankId(rankName) + "");
        APICore.getInstance().getDatabase().update("DELETE FROM " + PERMS_RANK + " WHERE rank_id=" + getRankId(rankName) + "");
        APICore.getInstance().getDatabase().update("DELETE FROM " + RANK + " WHERE name='" + rankName + "'");
    }

    public static void editRankName(String oldName, String newName){
        if(!rankExists(oldName)) return;
        int id = getRankId(oldName);
        APICore.getInstance().getDatabase().update("UPDATE " + RANK + " SET name='" + newName + "' WHERE id=" + id + "");
    }

    public static void editRankInitials(String rankName, String newInitials){
        if(!rankExists(rankName)) return;
        APICore.getInstance().getDatabase().update("UPDATE " + RANK + " SET initials='" + newInitials + "' WHERE name='" + rankName + "'");
    }

    public static void editRankColor(String rankName, String newColor){
        if(!rankExists(rankName)) return;
        APICore.getInstance().getDatabase().update("UPDATE " + RANK + " SET color='" + newColor + "' WHERE name='" + rankName + "'");
    }

    public static void editRankStrength(String rankName, int newStrength){
        if(!rankExists(rankName)) return;
        APICore.getInstance().getDatabase().update("UPDATE " + RANK + " SET strength='" + newStrength + "' WHERE name='" + rankName + "'");
    }

    public static int getRankId(String rankName){
        return (int) APICore.getInstance().getDatabase().query("SELECT * FROM " + RANK + " WHERE name='" + rankName + "'", rs -> {
            try {
                if(rs.next()){
                    return rs.getInt("id");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return 0;
        });
    }

    public static void addPerm(String rankName, String permName){
        if(!rankExists(rankName)) return;
        if(!permExists(permName)) return;
        if(hasPerm(rankName, permName)) return;
        APICore.getInstance().getDatabase().update("INSERT INTO " + PERMS_RANK + " (rank_id, perm_id) " +
                "VALUES ((SELECT id FROM " + RANK + " WHERE name='" + rankName + "'), " +
                "(SELECT id FROM " + PERMISSIONS + " WHERE name='" + permName + "'))");
    }

    public static void removePerm(String rankName, String permName){
        if(!rankExists(rankName)) return;
        if(!permExists(permName)) return;
        if(!hasPerm(rankName, permName)) return;
        APICore.getInstance().getDatabase().update("DELETE FROM " + PERMS_RANK + " WHERE rank_id="+getRankId(rankName)+" AND perm_id="+getPermId(permName)+"");
    }

    public static void createPerm(String permName){
        if(permExists(permName)) return;
        APICore.getInstance().getDatabase().update("INSERT INTO " + PERMISSIONS + " (name) VALUES ('" + permName + "')");
    }

    public static void deletePerm(String permName){
        if(!permExists(permName)) return;
        APICore.getInstance().getDatabase().update("DELETE FROM " + PERMS_RANK + " WHERE perm_id=" + getPermId(permName) + "");
        APICore.getInstance().getDatabase().update("DELETE FROM " + PERMISSIONS + " WHERE id=" + getPermId(permName) + "");
    }

    public static boolean hasPerm(String rankName, String permName){
        if(!rankExists(rankName)) return false;
        if(!permExists(permName)) return false;

        return (boolean) APICore.getInstance().getDatabase().query("SELECT * FROM " + PERMS_RANK + " WHERE rank_id=" + getRankId(rankName) + " AND perm_id=" + getPermId(permName) + "", rs -> {
            try {
                if(rs.next()) return true;
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        });
    }

    public static int getPermId(String permName){
        return (int) APICore.getInstance().getDatabase().query("SELECT * FROM " + PERMISSIONS + " WHERE name='" + permName + "'", rs -> {
            try {
                if(rs.next()){
                    return rs.getInt("id");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return 0;
        });
    }

    /**
     * This function is used to verify if a specified permission exist
     * @param permName The name of the permission you want to verify the existence
     * @return True if the permission exists, false otherwise
     *
     * @author Dark, for EndWork©
     */
    public static boolean permExists(String permName){
        return (boolean) APICore.getInstance().getDatabase().query("SELECT * FROM " + PERMISSIONS + " WHERE name='" + permName + "'", rs -> {
            try {
                if(rs.next()) return true;
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        });
    }

    /**
     * This function is used to know if a player has any rank
     * @param player The Player object of the player you want to verify ranks
     * @return True if the player has a rank, false otherwise
     *
     * @author Dark, for EndWork©
     */
    public static boolean hasAnyRank(Player player){
        String uuid = player.getUniqueId().toString().replace("-", "");
        return (boolean) APICore.getInstance().getDatabase().query("SELECT * FROM " + PLAYER_RANK + " WHERE player_uuid=UNHEX('" + uuid + "')", rs -> {
            try{
                if(rs.next()){
                    return true;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        });
    }

    /**
     * This function is used to know if a player has a specified rank
     * @param player The Player object of the player you want to verify the ranks
     * @param rankName The name of the rank you want to verify
     * @return True if the player has the rank, false otherwise
     *
     * @author Dark, for EndWork©
     */
    public static boolean hasRank(Player player, String rankName){
        String uuid = player.getUniqueId().toString();
        Rank rank = getRank(rankName);
        return (boolean) APICore.getInstance().getDatabase().query("SELECT * FROM " + PLAYER_RANK + " WHERE " +
                "player_uuid=UNHEX('" + uuid.replace("-", "") + "') " +
                "AND rank_id=" + rank.getId() + "", rs -> {
            try{
                if(rs.next()){
                    return true;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        });
    }

    /**
     * This function is used to know if a player has a specified rank
     * @param player The name of the player you want to verify the ranks
     * @param rankName The name of the rank you want to verify
     * @return True if the player has the rank, false otherwise
     *
     * @author Dark, for EndWork©
     */
    public static boolean hasRank(String player, String rankName){
        Rank rank = getRank(rankName);
        return (boolean) APICore.getInstance().getDatabase().query("SELECT * FROM " + PLAYER_RANK + " WHERE " +
                "player_uuid=(SELECT uuid FROM t_player WHERE pseudo='" + player + "') " +
                "AND rank_id=" + rank.getId() + "", rs -> {
            try{
                if(rs.next()){
                    return true;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        });
    }

    /**
     * This function is used to add a rank to a player, by his object
     * @param player The Player object of the player you want to add the rank
     * @param rankName The name of the rank you want to add to the player
     *
     * @author Dark, for EndWork©
     */
    public static void addRank(Player player, String rankName){
        if(!rankExists(rankName)) return;
        if(hasRank(player, rankName)) return;
        String uuid = player.getUniqueId().toString().replace("-", "");
        APICore.getInstance().getDatabase().update("INSERT INTO " + PLAYER_RANK + " (player_uuid, rank_id) " +
                "VALUES (UNHEX('" + uuid + "'), " +
                "(SELECT id FROM " + RANK + " WHERE name='" + rankName + "'))");
    }

    /**
     * This function is used to add a rank to a player, by his name
     * @param player The name of the player you want to add the rank
     * @param rankName The name of the rank you want to add to the player
     *
     * @author Dark, for EndWork©
     */
    public static void addRank(String player, String rankName){
        if(!rankExists(rankName)) return;
        if(hasRank(player, rankName)) return;
        APICore.getInstance().getDatabase().update("INSERT INTO " + PLAYER_RANK + " (player_uuid, rank_id) " +
                "VALUES ((SELECT uuid FROM t_player WHERE pseudo='" + player + "'), " +
                "(SELECT id FROM " + RANK + " WHERE name='" + rankName + "'))");
    }

    /**
     * This function is used to remove a rank to a player, by his object
     * @param player The Player object of the player you want to remove the rank
     * @param rankName The name of the rank you want to remove to the player
     *
     * @author Dark, for EndWork©
     */
    public static void removeRank(Player player, String rankName){
        if(!rankExists(rankName)) return;
        if(!hasRank(player, rankName)) return;
        String uuid = player.getUniqueId().toString().replace("-", "");
        APICore.getInstance().getDatabase().update("DELETE FROM " + PLAYER_RANK + " WHERE player_uuid=UNHEX('" + uuid + "') AND rank_id=" + getRankId(rankName) + "");
    }

    /**
     * This function is used to remove a rank to a player, by his name
     * @param player The name of the player you want to add the rank
     * @param rankName The name of the rank you want to add to the player
     *
     * @author Dark, for EndWork©
     */
    public static void removeRank(String player, String rankName){
        if(!rankExists(rankName)) return;
        if(!hasRank(player, rankName)) return;
        APICore.getInstance().getDatabase().update("DELETE FROM " + PLAYER_RANK + " WHERE player_uuid=(SELECT uuid FROM t_player WHERE pseudo='" + player + "') AND rank_id=" + getRankId(rankName) + "");
    }

    /**
     * This function is used to check if a player has a specific permission
     * @param player The player you want to verify the permissions
     * @param permission The permission you want to check
     * @return A boolean: true if the player's rank has the asked for permission, a more global permission or if the player is an operator. False otherwise
     *
     * @author Dark, for EndWork©
     */
    public static boolean hasPermission(Player player, String permission){
        if(!permExists(permission)) return false;
        if(!hasAnyRank(player)) return false;
        Rank rank = getActiveRank(player);

        String globalPermission = permission.split("\\.")[0] + ".*";
        if(!permExists(globalPermission)) return false;
        return hasPerm(rank.getName(), permission) || hasPerm(rank.getName(), "*") || hasPerm(rank.getName(), globalPermission) || player.isOp();
    }

    /**
     * This function is used to get all registered ranks of a player
     * @param player The player you want to get all the registered ranks
     * @return A rank list which contains all player's registered ranks
     *
     * @author Dark, for EndWork©
     */
    public static List<Rank> getAllRanks(Player player){
        String uuid = player.getUniqueId().toString();
        List<Integer> ranksID = new ArrayList<>();
        APICore.getInstance().getDatabase().query("SELECT * FROM " + PLAYER_RANK + " WHERE player_uuid=UNHEX('" + uuid.replace("-", "") + "')", rs -> {
            try {
                while (rs.next()){
                    ranksID.add(rs.getInt("rank_id"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });

        List<Rank> ranks = new ArrayList<>();
        for(int i : ranksID){
            APICore.getInstance().getDatabase().query("SELECT * FROM " + RANK + " WHERE id="+i, rs -> {
                try {
                    if(rs.next()){
                        int strength = rs.getInt("strength");
                        String name = rs.getString("name");
                        String initials = rs.getString("initials");
                        String color = rs.getString("color");

                        ranks.add(new Rank(strength, name, initials, color));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            });
        }

        return ranks;
    }

    /**
     * This function is used to get all registered ranks of a player
     * @param player The name of the player you want to get all the registered ranks
     * @return A rank list which contains all player's registered ranks
     *
     * @author Dark, for EndWork©
     */
    public static List<Rank> getAllRanks(String player){
        List<Integer> ranksID = new ArrayList<>();
        APICore.getInstance().getDatabase().query("SELECT * FROM " + PLAYER_RANK + " WHERE player_uuid=(SELECT uuid FROM " + PLAYER + " WHERE pseudo='" + player + "')", rs -> {
            try {
                while (rs.next()){
                    ranksID.add(rs.getInt("rank_id"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });

        List<Rank> ranks = new ArrayList<>();
        for(int i : ranksID){
            APICore.getInstance().getDatabase().query("SELECT * FROM " + RANK + " WHERE id="+i, rs -> {
                try {
                    if(rs.next()){
                        int strength = rs.getInt("strength");
                        String name = rs.getString("name");
                        String initials = rs.getString("initials");
                        String color = rs.getString("color");

                        ranks.add(new Rank(strength, name, initials, color));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            });
        }

        return ranks;
    }

    /**
     * This function is used to get the most powerful rank of a player
     * @param player The player you want to get the rank which is the most powerful on the server
     * @return The most powerful rank of the player
     *
     * @author Dark, for EndWork©
     */
    public static Rank getActiveRank(Player player){
        List<Rank> allRanks = getAllRanks(player);

        Rank highestRank = null;
        for(Rank rank : allRanks){
            if(highestRank == null){
                highestRank = rank;
                continue;
            }
            if(highestRank.getStrength() > rank.getStrength()){
                highestRank = rank;
            }
        }

        return highestRank;
    }

    /**
     * This function is used to get the most powerful rank of a player
     * @param player The name of the player you want to get the rank which is the most powerful on the server
     * @return The most powerful rank of the player
     *
     * @author Dark, for EndWork©
     */
    public static Rank getActiveRank(String player){
        List<Rank> allRanks = getAllRanks(player);

        Rank highestRank = null;
        for(Rank rank : allRanks){
            if(highestRank == null){
                highestRank = rank;
                continue;
            }
            if(highestRank.getStrength() > rank.getStrength()){
                highestRank = rank;
            }
        }

        return highestRank;
    }

    /**
     * This function is used to register a rank in the database
     * @param strength The strength of the rank (most powerful rank has 0 in strength)
     * @param name The name of the rank
     * @param initials The prefix of the rank (which will be displayed in the chat or tab list)
     * @param color The color of the prefix (respect minecraft color code)
     *
     * @author Dark, for EndWork©
     */
    public static void createRank(int strength, String name, String initials, String color){
        if(rankExists(name)) return;
        APICore.getInstance().getDatabase().update(
                "INSERT INTO " + RANK + " (name, initials, color, strength) " +
                     "VALUES ('" + name + "', '" + initials + "', '" + color + "', " + strength + ")");
    }

    /**
     * This function return the list of all registered ranks
     * @return The list of all registered ranks
     *
     * @author Dark, for EndWork©
     */
    public static List<Rank> getRegisteredRanks(){
        List<Rank> ranks = new ArrayList<>();
        APICore.getInstance().getDatabase().query("SELECT * FROM " + RANK, rs -> {
            try {
                while(rs.next()){
                    int strength = rs.getInt("strength");
                    String name = rs.getString("name");
                    String initials = rs.getString("initials");
                    String color = rs.getString("color");

                    ranks.add(new Rank(strength, name, initials, color));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        return ranks;
    }

    /**
     * This function return the list of all registered permissions
     * @return The list of all registered permissions
     *
     * @author Dark, for EndWork©
     */
    public static List<String> getRegisteredPermissions(){
        List<String> permissions = new ArrayList<>();
        APICore.getInstance().getDatabase().query("SELECT * FROM " + PERMISSIONS, rs -> {
            try {
                while(rs.next()){
                    permissions.add(rs.getString("name"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        return permissions;
    }


}
