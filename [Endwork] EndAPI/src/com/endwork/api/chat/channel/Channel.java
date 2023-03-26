package com.endwork.api.chat.channel;

import com.endwork.api.APICore;
import com.endwork.api.chat.players.ChatPlayer;
import com.endwork.api.ranks.RankAPI;
import org.bukkit.entity.Player;

import com.endwork.api.chat.ChatAPI;

import java.util.ArrayList;

/**
 *Cette class est faites pour gérer les interactions des joueurs dans le chats
 *<br>Tout les ChatEvent reconnu dans ce channel (par les prefix) seront gérer ici
 *<br>Cette class gère les permissions et les actions des joueurs pour les encadrer
 *<br>Cette class peut être sauvegarder dans la configurations pour la rendre persistante
 * @author thephoenix2feu, for EndWork©
 * @version 1.0
 */
public class Channel {

    private ArrayList<String> prefix = new ArrayList<>();
    private int priority = 0;
    private boolean isAlwaysSend = false;
    private String nameTableInDB = "null";
    private String name = "null";
    private String permissionForRead = "*";
    private String permissionForWrite = "*";
    private String msgChatPrefix = "null";
    private boolean acceptColor = false;

    private boolean usePrefix = false;
    private boolean isSave = false;

    private ArrayList<ChatPlayer> players = new ArrayList<>();

    private ChatAPI ChatAPI;

    /**
     *
     * @param prefix Ce paramètre correspond à la liste de prefix que le joueur pourra écrire dans le chat
     * @param priority Ce paramètre défini la priorité de ce channel en fonction des autres pour définir dans quel channel le joueur peux écrire
     * @param isAlwaysSend Ce paramètre doit être défini sur true si ce channel ne prend pas en compte la priorité
     * @param nameTableInDB Ce paramètre défini le nom de la table dans la database qui contiendra tout les messages envoyés à travers ce channel
     * @param name Ce paramètre défini le nom affiché du channel
     * @param permissionForRead Ce paramètre défini quel permissions est nécessaire pour pouvoir lire ce channel
     * @param permissionForWrite Ce paramètre défini quel permissions est nécessaire pour pouvoir écrire dans ce channel
     * @param msgChatPrefix Ce paramètre défini le texte écrit avant le message du joueur <b>les couleurs sont prise en compte et les caractères <u>"%pseudo%"</u> seront remplacer par le nom du joueur</b>
     * @param acceptColor Ce paramètre défini si les joueurs peuvent ou nom écrire en couleur dans le channel
     * @param usePrefix Ce paramètre défini ci à l'envoie du message, le channel doit ou non y ajouté le msgChatPreifx
     * @param isSave Ce paramètre défini si à l'arrêt du serveur, ce channel sera sauvegarder en config et donc sera perssistant ou non
     * @param keysConfig Ce paramètre défini la clé config pour sauvegarder les informations du channel à l'arrêt de celui-ci
     */
    public Channel(ArrayList<String> prefix, int priority, boolean isAlwaysSend, String nameTableInDB, String name, String permissionForRead, String permissionForWrite, String msgChatPrefix, boolean acceptColor, boolean usePrefix, boolean isSave) {
        this.prefix = prefix;
        this.priority = priority;
        this.isAlwaysSend = isAlwaysSend;
        this.nameTableInDB = nameTableInDB;
        this.name = name;
        this.permissionForRead = permissionForRead;
        this.permissionForWrite = permissionForWrite;
        this.usePrefix = usePrefix;
        this.isSave = isSave;
        this.msgChatPrefix = msgChatPrefix;
        this.acceptColor = acceptColor;
        ChatAPI = ChatAPI.getInstance();
        updatePlayersList();
    }
    private void addSQL(String msg, Player player) {
        APICore.getInstance().getDatabase().update("INSERT INTO `channel_chat_"+nameTableInDB+"` (`uuid`, `pseudo`, `msg`) VALUES(\""+player.getUniqueId().toString()+"\", \""+player.getName()+"\", \""+msg+"\");");
    }

    private String messageApplyModification(String msg, String nameSender) {
         if(usePrefix) {
             String msgPrefix = msgChatPrefix.replace("%pseudo%", nameSender);
             msg = (msgPrefix.replace("&", "§")) + msg;
         }
        if(acceptColor)
            msg = msg.replace("&", "§");

        return msg;
    }
    private String suppressChatChannelPrefix(String msg) {
        for(String prefix : prefix){
            if(msg.startsWith(prefix)) {
                msg = msg.replace(prefix, "");
                return msg;
            }
        }
        return msg;
    }

    /**
     *Envoie une information à tout les joueurs du channel
     *<br>ATTENTION: L'entité envoyant le message n'est <b>PAS</b> un joueur
     *@param msg Le contenu du message
     *@param nameInfo Le nom de l'entité envoyant le message (tels qu'un ChatAPI)
     *@see sendMessage
     */
    public void sendInfoMessage(String msg, String nameInfo){
        msg = messageApplyModification(msg, nameInfo);
        for(ChatPlayer pls : players)
            pls.player.sendMessage(msg);
    }

    /**
     *Force un joueur a envoyer un message dans le channel
     * @param msg Le contenu du message
     * @param p Le joueur qui envoie le message
     */
    public void sendMessage(String msg, Player p) {

        ChatPlayer chatPlayer = getPlayerChat(p);

        if(!players.contains(chatPlayer) || chatPlayer == null)
            return;

        if(chatPlayer.isMuted){
            chatPlayer.sendErrorMessage("MUTE", "Vous êtes silencieux pendant encore "+chatPlayer.secondsMuted+" secondes");
            return;
        }

        if(!canWrite(chatPlayer)){
            chatPlayer.sendErrorMessage("chat", "Tu n'as pas les permissions d'écrire dans ce channel");
            return;
        }

        msg = suppressChatChannelPrefix(msg);
        addSQL(msg, p);
        msg = messageApplyModification(msg, p.getDisplayName());
        for(ChatPlayer pls : players)
            pls.player.sendMessage(msg);

    }
    /**
     *Cette fonction sert à redéfinir les joueurs ayant accés au channel
     *<u>Il est nécessaire de l'appeler après avoir changer certaine permissions</u>
     * Elle est d'ailleurs appelé à chaque connection ou déconnection d'un joueur
     * @see setPermissionForWrite
     * @see setPermissionForRead
     */
    public void updatePlayersList() {
        players.clear();
        for(ChatPlayer pls : ChatAPI.getChatPlayers())
            if(canWrite(pls) ||canRead(pls))
                players.add(pls);
    }


    /**
     * Vérifie si un joueur à les permissions pour écrire dans le channel
     * @param player Le joueur que l'ont souhaite vérifié
     * @return True si le joueur peut écrire et False si il ne peut pas
     */
    public boolean canWrite(ChatPlayer player) {
        if(player == null)
            return false;

        if(permissionForWrite.length() == 0)
            return true;

        return RankAPI.hasPermission(player.player, permissionForWrite);
    }

    /**
     * Vérifie si un joueur à les permissions pour lire le channel
     * @param player Le joueur que l'ont souhaite vérifié
     * @return True si le joueur peut lire et False si il ne peut pas
     */
    public boolean canRead(ChatPlayer player) {
        if(player == null)
            return false;

        if(permissionForRead.length() == 0)
            return true;

        return RankAPI.hasPermission(player.player, permissionForRead);
    }


    /**
     * Vérifie si un joueur fait parti du channel
     * @param player Le joueur que l'ont souhaite vérifié
     * @return True si le joueur est dans le channel et False si il ne l'est pas
     */
    public boolean containsPlayer(Player player){
        for(ChatPlayer pls : players){
            if(pls.player == player)
                return true;
        }
        return false;
    }


    //GETTER
    public int getPriority() {
        return priority;
    }
    public ArrayList<String> getPrefix() {
        return prefix;
    }
    public ChatPlayer getPlayerChat(Player p) {
        for(ChatPlayer chatPlayer : players)
            if (p == chatPlayer.player)
                return chatPlayer;
        return null;
    }
    public ArrayList<ChatPlayer> getPlayers() {
        return players;
    }
    public String getName() {
        return name;
    }
    public boolean isAlwaysSend() {
        return isAlwaysSend;
    }
    public String getNameTableInDB() {
        return nameTableInDB;
    }
    public String getPermissionForRead() {
        return permissionForRead;
    }
    public String getPermissionForWrite() {
        return permissionForWrite;
    }
    public String getMsgChatPrefix() {
        return msgChatPrefix;
    }
    public boolean isAcceptColor() {
        return acceptColor;
    }
    public boolean isUsePrefix() {
        return usePrefix;
    }
    public boolean isSave() {
        return isSave;
    }
    //SETTER
    public void setPrefix(ArrayList<String> prefix) {
        this.prefix = prefix;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void setAlwaysSend(boolean alwaysSend) {
        isAlwaysSend = alwaysSend;
    }
    public void setNameTableInDB(String nameTableInDB) {
        this.nameTableInDB = nameTableInDB;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPermissionForRead(String permissionForRead) {
        this.permissionForRead = permissionForRead;
    }
    public void setPermissionForWrite(String permissionForWrite) {
        this.permissionForWrite = permissionForWrite;
    }
    public void setMsgChatPrefix(String msgChatPrefix) {
        this.msgChatPrefix = msgChatPrefix;
    }
    public void setAcceptColor(boolean acceptColor) {
        this.acceptColor = acceptColor;
    }
    public void setUsePrefix(boolean usePrefix) {
        this.usePrefix = usePrefix;
    }
    public void setSave(boolean save) {
        isSave = save;
    }
    public void setPlayers(ArrayList<ChatPlayer> players) {
        this.players = players;
    }
}
