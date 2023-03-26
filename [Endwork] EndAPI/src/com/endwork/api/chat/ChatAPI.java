package com.endwork.api.chat;

import com.endwork.api.APICore;
import com.endwork.api.chat.channel.Channel;
import com.endwork.api.chat.commands.ChatCommandManager;
import com.endwork.api.chat.players.ChatPlayer;
import com.endwork.api.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChatAPI {

    private static ChatAPI instance = null;

    private ArrayList<Channel> channels = new ArrayList<>();
    private ArrayList<ChatPlayer> players = new ArrayList<>();

    public String defaultHeaderTabList = "";
    public String defaultFooterTabList = "";
    public Channel defaultChannel = null;

    private ChatCommandManager chatCommandManager;
    public JavaPlugin chatPlugin;

    public ChatAPI() {
        chatCommandManager = new ChatCommandManager(this);
        chatPlugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin("chat");

        if(chatPlugin == null) {
            System.out.println("[ENDAPI]Disable due to no chat plugin founded");
            Bukkit.getPluginManager().disablePlugin(APICore.getInstance());
        }
    }

    /**
     * Retourne l'API actuelle et en créer une au besoin
     * @return
     */
    private static ChatAPI getAPI(){
        return new ChatAPI();
    }

    /**
     * Créer une class API qui donne accés aux fonctionnalité du plugin
     * @return Une nouvelle instance d'API
     */
    public static ChatAPI getInstance(){
        if (instance == null)
            instance = new ChatAPI();

        return instance;
    }

    /**
     * Retourne une class permettant de gérer toutes les commandes du serveur
     * @return La class {@link fr.phoenix.chat.commands.ChatCommands.ChatCommands}
     */
    public ChatCommandManager getCommandManager() {
        return chatCommandManager;
    }

    /**
     * Retourne un channel depuis son nom
     * @param name Le nom du channel
     * @return Retourne un {@link Channel}
     */
    public Channel getChannel(String name){
        for(Channel channel : channels)
            if(channel.getName().equals(name))
                return channel;
        return null;
    }

    /**
     * Retourne une liste de tout les channels présents sur le servuer
     * @return ArrayList {@link Channel}
     */
    public ArrayList<Channel> getChannels(){
        return channels;
    }

    /**
     * Retourne un {@link ChatPlayer} depuis un joueur
     * @param p Le joueur ciblé
     * @return Le {@link ChatPlayer} du joueur
     */
    public ChatPlayer getChatPlayer(Player p) {
        for(ChatPlayer chatPlayer : players)
            if(chatPlayer.player == p)
                return chatPlayer;

        return null;
    }

    /**
     * Retourne la lisye de tout les {@link ChatPlayer} du serveur
     * @return
     */
    public ArrayList<ChatPlayer> getChatPlayers() {
        return players;
    }

    /**
     * Enregistre dans la base de données les paramétres du channel
     * @param channel Le channel ciblé
     * @see Channel
     */
    public void saveChannel(Channel channel){
        Database db = APICore.getInstance().getDatabase();
        if(channel.isSave()) {
            AtomicBoolean exist = new AtomicBoolean(false);
            db.query("SELECT * FROM `chat_tables_list` WHERE `name`='"+channel.getName()+"';", answ->{
                try {
                    if(answ.next()){
                        exist.set(true);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            if(!exist.get()) {
                String prefixStr = convertArrayPrefixsToString(channel);
                String query = "INSERT INTO `chat_tables_list`(`prefix`, `priority`, `isAlwaysSend`, `nameTable`, `permissionForRead`, `permissionForWrite`, `name`, `msgChatPrefix`, `acceptColor`, `usePrefix`, `isSave`) VALUES ("
                        /*1*/+ "'" +  prefixStr + "'"
                        /*2*/+ ",'" + channel.getPriority() + "'"
                        /*3*/+ ",'" + convertBoolInInteger(channel.isAlwaysSend()) + "'"
                        /*4*/+ ",'" + channel.getNameTableInDB() + "'"
                        /*5*/+ ",'" + channel.getPermissionForRead() + "'"
                        /*6*/+ ",'" + channel.getPermissionForWrite() + "'"
                        /*7*/+ ",'" + channel.getName() + "'"
                        /*8*/+ ",'" + channel.getMsgChatPrefix() + "'"
                        /*9*/+ ",'" + convertBoolInInteger(channel.isAcceptColor()) + "'"
                        /*10*/+ ",'" + convertBoolInInteger(channel.isUsePrefix()) + "'"
                        /*11*/+ ",'" + convertBoolInInteger(channel.isSave()) + "'"
                        + ");";
                db.update(query);
            }else {
                String prefixStr = convertArrayPrefixsToString(channel);
                db.update("UPDATE `chat_tables_list` SET "
                        + "`prefix`='" + prefixStr + "`,"
                        + "`priority`='" + channel.getPriority() + "`,"
                        + "`isAlwaysSend`='" + channel.isAlwaysSend() + "`,"
                        + "`nameTable`='" + channel.getNameTableInDB() + "`,"
                        + "`name`='" + channel.getName() + "`,"
                        + "`permissionForRead`='" + channel.getPermissionForRead() + "`,"
                        + "`permissionForWrite`='" + channel.getPermissionForWrite() + "`,"
                        + "`msgChatPrefix`='" + channel.getMsgChatPrefix() + "`,"
                        + "`acceptColor`='" + channel.isAcceptColor() + "`,"
                        + "`usePrefix`='" + channel.isUsePrefix() + "`,"
                        + "`isSave`='" + channel.isSave() + "`)"
                        + "WHERE `name`=`" + channel.getName() + "`"
                );
            }
        }
    }

    /**
     * Enregistre dans la bdd les paramétres de tout les channels 1 par 1
     * @see Channel
     */
    public void saveChannels(){
        for(Channel channel : channels)
            saveChannel(channel);
    }

    /**
     * Récupére tout les channels de la bdd et les charge en tant que channel actif
     * @see Channel
     */
    public void loadChannels(){
        channels.clear();
        APICore.getInstance().getDatabase().query("SELECT * FROM `chat_tables_list`", rs -> {
            try {
                while(rs.next()){
                    String[] list = rs.getString("prefix").split(";");
                    ArrayList prefix = new ArrayList();
                    for(String str : list)
                        prefix.add(str);
                    int priority = rs.getInt("priority");
                    boolean isAlwaysSend = rs.getBoolean("isAlwaysSend");
                    String nameTableInDB = rs.getString("nameTable");
                    String name = rs.getString("name");
                    String permissionForRead = rs.getString("permissionForRead");
                    String permissionForWrite = rs.getString("permissionForWrite");
                    String msgChatPrefix = rs.getString("msgChatPrefix");
                    boolean acceptColor = rs.getBoolean("acceptColor");
                    boolean usePrefix = rs.getBoolean("usePrefix");
                    boolean isSave = rs.getBoolean("isSave");
                    Channel channel = new Channel(prefix, priority, isAlwaysSend, nameTableInDB, name, permissionForRead, permissionForWrite, msgChatPrefix, acceptColor, usePrefix, isSave);
                    addChannel(channel);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * ajoute un channel aux channels actif
     * <br><b>Le channel doit être créer auparavant car le channel ne sera ajouté seulement si il n'existe déjà pas</b>
     * @param channel Le channel ciblé
     * @see Channel
     */
    public void addChannel(Channel channel){
        if(getChannel(channel.getName()) != null)
            return;
        channel.updatePlayersList();
        channels.add(channel);
        Database db = APICore.getInstance().getDatabase();
        db.update("CREATE TABLE IF NOT EXISTS `channel_chat_"+channel.getName()+"`(id INT PRIMARY KEY AUTO_INCREMENT,uuid TEXT,pseudo TEXT, msg TEXT)");
    }

    private Integer convertBoolInInteger(boolean bool){
        if(bool)
            return 1;
        else
            return 2;
    }

    /**
     * Supprime un channel de la liste active
     * @param channel Le channel ciblé
     * @return True si la suppresion s'est correctement déroulé et False dans les autres cas
     * @see Channel
     */
    public boolean removeChannel(Channel channel){
        if(channels.contains(channel)) {
            channels.remove(channel);
            return true;
        }
        return false;
    }

    /**
     * Ceci est une vérification dans l'objectif de savoir si un channel fait parti de la liste active
     * @param channel Le channel ciblé
     * @return True si il fait effectivement partie de la liste active est False dans les autres cas
     * @see Channel
     */
    public boolean existChannel(Channel channel){
        return channels.contains(channel);
    }

    /**
     * Reload tout les {@link ChatPlayer}
     */
    public void updatePlayers() {
        players.clear();

        String header = defaultHeaderTabList;
        String footer = defaultFooterTabList;
        for(Player pls : Bukkit.getOnlinePlayers()){
            ChatPlayer player = new ChatPlayer(pls);
            players.add(player);
            player.setTabHeader(header);
            player.setTabFooter(footer);
        }
    }

    private String convertArrayPrefixsToString(Channel channel) {
        String prefixStr = "";
        for(String prefixs : channel.getPrefix())
            prefixStr+=prefixs+";";
        prefixStr = prefixStr.substring(0, prefixStr.length() - 1);
        return prefixStr;
    }
}
