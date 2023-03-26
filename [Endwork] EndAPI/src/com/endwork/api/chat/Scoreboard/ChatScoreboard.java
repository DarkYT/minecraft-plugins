package com.endwork.api.chat.Scoreboard;

import com.endwork.api.chat.ChatAPI;
import com.endwork.api.chat.players.ChatPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author thephoenix2feu, for EndWork©
 */
public class ChatScoreboard {

    private final ScoreboardManager scoreboardManager;
    private Scoreboard scoreboard;

    private Objective objective;
    private String name;
    private HashMap<Integer, String> lignes = new HashMap<>();

    private ArrayList<ChatPlayer> players = new ArrayList<>();

    /**
     * Créer un Scoreboard et lui attribu un nom
     * @param name Le nom du scoreboard
     */
    public ChatScoreboard(String name) {
        scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        objective = scoreboard.registerNewObjective(name, "dummy");
    }

    /**
     * Défini la ligne du scoreboard
     * @param number Le numéro de position de la ligne dans le scoreboard (plus bas il sera plus bas le message sera)
     * @param text Le message en lui même (les <b>&</b> seront changé en couleur)
     */
    public void setLine(int number, String text){
        text=text.replaceAll("&", "§");
        objective.getScore(text).setScore(number);
    }

    /**
     * Créer une ligne vide à la position donné
     * @param number Le numéro de position de la ligne dans le scoreboard (plus bas il sera plus bas la ligne vide sera)
     */
    public void setEmptyLiigne(int number){
        String text = "§e";
        int ligneBefore = (number-1);
        if(lignes.containsKey(ligneBefore))

        if(lignes.get(ligneBefore).equals("§e"))
            text = "§1";

        setLine(number, text);
    }

    /**
     * Retourn le texte d'une ligne donnée
     * @param number La ligne demander
     * @return Le texte de la ligne du scoreboard
     */
    public String getLine(int number){
        if(!lignes.containsKey(number)){
            return "";
        }
        return lignes.get(number);
    }

    /**
     * Ajoute un le scoreboard au joueur
     * @param p Le jouur ciblé
     */
    public void addPlayer(Player p){
        addPlayer(ChatAPI.getInstance().getChatPlayer(p));
    }

    /**
     * Ajoute un le scoreboard au joueur
     * @param p Le joueur ciblé
     */
    public void addPlayer(ChatPlayer p){
        if(players.contains(p))
            return;
        p.scoreboard = this;
        players.add(p);
        p.player.setScoreboard(scoreboard);
    }

    /**
     * Réafiche le scoreboard a tout les joueurs qui ont été ajouté au scoreboard via addPlayer en attribuant les changements effectués sur celui-ci
     */
    public void refreshPlayer(){
        for(ChatPlayer pls : players){
            pls.player.setScoreboard(scoreboard);
        }
    }

    /**
     * Retire le scoreboard à un joueur
     * @param p Le joueur ciblé
     */
    public void removePlayer(Player p){
        removePlayer(ChatAPI.getInstance().getChatPlayer(p));
    }

    /**
     * Retire le scoreboard à un joueur
     * @param p Le joueur ciblé
     */
    public void removePlayer(ChatPlayer p){
        p.scoreboard = null;
        p.player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        players.remove(p);
    }

    /**
     * Retourne true si le scoreboard est bien attribué au joueur
     * @param p Le joueur ciblé
     * @return
     */
    public boolean containsPlayer(Player p){
        return containsPlayer(ChatAPI.getInstance().getChatPlayer(p));
    }

    /**
     * Retourne true si le scoreboard est bien attribué au joueur
     * @param p Le joueur ciblé
     * @return
     */
    public boolean containsPlayer(ChatPlayer p){
        return players.contains(p);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        objective = Bukkit.getScoreboardManager().getNewScoreboard().registerNewObjective(name, "dummy");

        this.name = name;
    }
}
