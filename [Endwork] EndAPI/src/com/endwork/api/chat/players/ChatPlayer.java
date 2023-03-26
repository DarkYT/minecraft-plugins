package com.endwork.api.chat.players;

import com.endwork.api.chat.ChatAPI;
import com.endwork.api.chat.Scoreboard.ChatScoreboard;
import com.endwork.api.chat.channel.Channel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author thephoenix2feu, for EndWork©
 */
public class ChatPlayer {

    public String name;
    public Player player;
    public UUID uuid;
    public boolean isMuted;
    public boolean isVanished;
    public int secondsMuted;
    public ChatScoreboard scoreboard;
    public ArrayList<Player> tabPlayers = new ArrayList<>();

    /**
     * Cette class est utilisé pour gérer le chat d'un joueur
     *
     * @param player Le joeueur qui est ciblé par la class
     */
    public ChatPlayer(Player player) {
        this.player = player;
        uuid = player.getUniqueId();
        name = player.getName();
        isMuted = false;
        resetTab();
    }

    public void sendEmptyLigne(int numberLigne) {
        for (int i = 0; i < numberLigne; i++)
            player.sendMessage("\n");
    }

    /**
     * Cela envoie une erreur au joueur sous la forme [<b>nom de l'événement</b>]Contenu du message
     *
     * @param nameError     Le nom de l'événement
     * @param containsError Le contenu du message
     */
    public void sendErrorMessage(String nameError, String containsError) {
        player.sendMessage(ChatColor.DARK_RED + "[" + nameError + "]" + ChatColor.RED + containsError);
    }

    /**
     * Cela envoie une information de réussite 'd'une opération par exemple) au joueur sous la forme [<b>nom de l'événement</b>]Contenu du message
     *
     * @param nameSuccess     Le nom de l'événement
     * @param containsSuccess Le contenu du message
     */
    public void sendSuccessMessage(String nameSuccess, String containsSuccess) {
        player.sendMessage(ChatColor.DARK_GREEN + "[" + nameSuccess + "]" + ChatColor.GREEN + containsSuccess);
    }

    /**
     * Cela envoie une information au joueur sous la forme [<b>nom de l'événement</b>]Contenu du message
     *
     * @param nameSender      Le nom de l'événement
     * @param containsSuccess Le contenu du message
     */
    public void sendInfoMessage(String nameSender, String containsSuccess) {
        player.sendMessage(ChatColor.GREEN + "[" + nameSender + "]" + ChatColor.GRAY + containsSuccess);
    }


    /**
     * Cela envoie une description d'une commande au joueur sous la forme [<b>nom de l'événement</b>]/Commande : Description
     *
     * @param nameSender  Le nom de APICore.getInstance().chatPlugin qui envoie l'information
     * @param nameCommand Le nom de la commande
     * @param descCommand la description de la commande
     */
    public void sendCommandInfo(String nameSender, String nameCommand, String descCommand) {
        player.sendMessage(ChatColor.DARK_GREEN + "[" + nameSender + "]" + ChatColor.GREEN + nameCommand + ChatColor.GRAY + " :" + descCommand);
    }

    private int muteTask;

    public void mute(int secondes) {
        isMuted = true;
        secondsMuted = secondes;
        sendErrorMessage("MUTE", "Vous êtes mainetnant silencieux pour " + secondes + " secondes");
        muteTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(ChatAPI.getInstance().chatPlugin, new Runnable() {
            @Override
            public void run() {
                secondsMuted--;
                if (secondsMuted == 0) {
                    unMute();
                }
            }
        }, 20, 20);

    }

    public void unMute() {
        if (isMuted) {
            Bukkit.getScheduler().cancelTask(muteTask);
            secondsMuted = 0;
            isMuted = false;
            sendSuccessMessage("MUTE", "Vous n'êtes plus silencieux !");
        }
    }


    /**
     * Renvoie une liste des tout les channels qui contiennent le joueur
     *
     * @return Tout les channels qui contiennent le joueur
     */
    public ArrayList<Channel> getPlayersChannels() {
        ArrayList<Channel> toReturn = getPlayersHighestChannels();
        for (Channel channel : ChatAPI.getInstance().getChannels()) {
            if (channel.isAlwaysSend() && !toReturn.contains(channel) && channel.canRead(channel.getPlayerChat(player)))
                toReturn.add(channel);
        }
        return toReturn;
    }

    /**
     * Renvoie une liste des channels <b>prioritaire</b> du joueurs (en ne tenant pas compte des Channel qui n'utilise pas les priorités)
     *
     * @return out les channels prioritaire qui contiennent le joueur
     */
    public ArrayList<Channel> getPlayersHighestChannels() {
        ArrayList<Channel> toReturn = new ArrayList<>();
        int max = 0;
        for (Channel channel : ChatAPI.getInstance().getChannels()) {
            if (channel.containsPlayer(player)) {
                if (channel.getPriority() >= max && channel.canRead(channel.getPlayerChat(player))) {
                    if (channel.getPriority() > max) {
                        toReturn.clear();
                        max = channel.getPriority();
                    }
                    toReturn.add(channel);
                }
            }
        }
        return toReturn;
    }


    /**
     * Définie une liste de joueur que le joueur peut voir dans le tab
     *
     * @param players La liste des joueurs visible par ce joueur
     */
    public void setTabPlayers(ArrayList<Player> players) {
        resetTab();
        tabPlayers = players;
        for (Player pls : Bukkit.getOnlinePlayers())
            if (!players.contains(pls))
                player.hidePlayer(ChatAPI.getInstance().chatPlugin, pls);

    }

    /**
     * Définie une liste de joueur que le joueur peut voir dans le tab
     */
    public void setTabChatPlayers(ArrayList<ChatPlayer> chatPlayers) {
        ArrayList<Player> players = new ArrayList<>();
        for (ChatPlayer pls : chatPlayers) {
            players.add(pls.player);
        }
        setTabPlayers(players);
    }

    /**
     * Affiche tout les joueurs dans le tab du joueur
     */
    public void resetTab() {
        tabPlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (Player pls : Bukkit.getOnlinePlayers())
            player.showPlayer(ChatAPI.getInstance().chatPlugin, pls);
    }

    /**
     * Définie le pseudo du joueur dans le tab
     */
    public void setTabPseudo(String pseudo) {
        player.setPlayerListName(pseudo);
    }

    public void setTabHeader(String tabHeader) {
        tabHeader = tabHeader.replaceAll("&", "§");
        player.setPlayerListHeader(tabHeader);
    }

    public void setTabFooter(String tabFooter) {
        tabFooter = tabFooter.replaceAll("&", "§");
        player.setPlayerListFooter(tabFooter);
    }
}