package fr.dark.ranks;

import com.endwork.api.APICore;
import com.endwork.api.inventory.GUI;
import com.endwork.api.inventory.PaginatedGUI;
import com.endwork.api.ranks.Rank;
import com.endwork.api.ranks.RankAPI;
import fr.dark.ranks.commands.RankCommand;
import fr.dark.ranks.events.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RankCore extends JavaPlugin {

    private static RankCore instance;

    public static Map<Player, Boolean> displayActiveRank = new HashMap<>();

    @Override
    public void onEnable() {
        //Instantiate global variables
        instance = this;
        CreateBasics();

        for(Player player : Bukkit.getOnlinePlayers()) displayActiveRank.put(player, true);

        EnableEvents();
        EnableCommands();
    }

    //Getters and Setters
    public static RankCore getInstance() {
        return instance;
    }

    //Enabling functions
    private void EnableEvents(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new JoinEvent(), this);

        PaginatedGUI.prepare(this);
        GUI.prepare(this);
    }

    private void EnableCommands(){
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("ranks").setExecutor(new RankCommand());
        getCommand("permission").setExecutor(new RankCommand());
    }

    private void CreateBasics() {
        RankAPI.createRank(255, "member", "", "7");
        RankAPI.createPerm("*");
        for(Player player : Bukkit.getOnlinePlayers()){
            if(!APICore.getInstance().getDatabase().isRegistered(player)){
                APICore.getInstance().getDatabase().register(player);
                RankAPI.addRank(player, "member");
            }
        }
    }

    //Functions
    public static void refreshPlayer(Player player){
        Rank rank = RankAPI.getActiveRank(player);
        player.setPlayerListName("§7" + player.getName());
        if(displayActiveRank.get(player)){
            if(rank.getName().equals("member")){
                player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&"+rank.getColor()+" "+player.getName()));
            }else{
                player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&"+rank.getColor()+rank.getInitials()+" "+player.getName()));
            }
        }
    }

    public static void refreshPlayers(){
        for(Player player : Bukkit.getOnlinePlayers()) refreshPlayer(player);
    }

    public static boolean isNumeric(String number){
        try {
            double d = Double.parseDouble(number);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static ItemStack getHead(Player player) {
        Rank rank = RankAPI.getActiveRank(player);
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName("§cJoueur: " + player.getName());
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§cGrade: §" + rank.getColor() + rank.getName());
        skull.setLore(lore);
        skull.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        item.setItemMeta(skull);
        return item;
    }


}
