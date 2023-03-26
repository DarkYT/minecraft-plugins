package com.endwork.api.party;

import com.endwork.api.chat.ChatAPI;
import com.endwork.api.chat.players.ChatPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MessagesManage {

    private String nameSender = "Party";

    public void sendHelp(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendInfoMessage(nameSender,"--------");

        chatPlayer.sendInfoMessage(nameSender, ChatColor.GRAY+""+ChatColor.BOLD+"Commandes pour tout les joueurs:");
        chatPlayer.sendCommandInfo(nameSender,"/party help","Affiche cette page");
        chatPlayer.sendCommandInfo(nameSender,"/party create","Créer votre groupe en tant que chef");
        chatPlayer.sendCommandInfo(nameSender,"/party join","Rejoins le dernier groupe qui vous a invité");
        chatPlayer.sendCommandInfo(nameSender,"/party join <player>","Rejoins le groupe dont le chef est le joueur ciblé");
        chatPlayer.sendCommandInfo(nameSender,"/party join <player>","Rejoins le groupe dont le chef est le joueur ciblé");

        chatPlayer.sendInfoMessage(nameSender, ChatColor.GREEN+""+ChatColor.BOLD+"Commandes des membres de groupe:");
        chatPlayer.sendCommandInfo(nameSender,"/party list ", " Afficher la liste des personnes dans votre groupe");
        chatPlayer.sendCommandInfo(nameSender,"/party leave ", " Quitter votre groupe");

        chatPlayer.sendInfoMessage(nameSender, ChatColor.GOLD+""+ChatColor.BOLD+"Commandes des chef de groupes:");
        chatPlayer.sendCommandInfo(nameSender, "/party invite ", " Inviter des joueurs dans votre groupe");
        chatPlayer.sendCommandInfo(nameSender, "/party kick ", " Ejecter des joueurs de votre groupe");
        chatPlayer.sendCommandInfo(nameSender, "/party disband ", " Supprimer votre groupe");
        chatPlayer.sendCommandInfo(nameSender, "/party lead ", " Définir un joueur de votre groupe en tant que chef");
        chatPlayer.sendCommandInfo(nameSender, "/party open ", " Ouvre le groupe au public");
        chatPlayer.sendCommandInfo(nameSender, "/party close ", " Ferme le groupe au public");
        chatPlayer.sendCommandInfo(nameSender, "/party random ", " Tire des joueurs en liste d'attente et les remplace par les joueurs actuels du groupe");


        chatPlayer.sendInfoMessage(nameSender,"--------");
    }

    public void sendSuccesCreateParty(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender, "Vous venez de créer votre groupe");
    }

    public void sendAlreadyInPartyMessage(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendErrorMessage(nameSender, "Vous êtes déjà dans un groupe");
    }

    public void sendInsertName(Player p, String cmd) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendErrorMessage(nameSender,"Erreur de syntaxe, utilisez plutôt : "+ChatColor.DARK_RED+"/party "+cmd+" <player>");
    }

    public void sendNotInParty(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendErrorMessage(nameSender,"Vous n'êtes pas dans un groupe");
    }

    public void sendNotOwner(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendErrorMessage(nameSender,"Tu ne peux pas faire cela car tu n'es pas le chef du groupe");
    }

    public void sendInvite(Player invite, Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendInfoMessage(nameSender, "Vous avez invité "+invite.getDisplayName()+" dans votre groupe");
        ChatPlayer inviteChatPlayer = ChatAPI.getInstance().getChatPlayer(invite);
        inviteChatPlayer.sendInfoMessage(nameSender, "Vous invite à rejoindre son groupe");
        inviteChatPlayer.sendInfoMessage(nameSender, "Faites "+ChatColor.GREEN+"/party join"+ChatColor.GRAY+" pour rejoindre");
    }

    public void sendAlreadyInvited(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
       chatPlayer.sendErrorMessage(nameSender, "Vous invitez déjà ce joueur dans votre groupe");
    }

    public void sendAlreadyInAParty(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendErrorMessage(nameSender,"Vous êtes déjà dans un groupe");
    }

    public void sendNotInvited(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendErrorMessage(nameSender,"Aucun groupe ne vous a invité");
    }

    public void sendPartyDoesntExist(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendErrorMessage(nameSender,"Le groupe qui vous a invité n'existe plus");
    }

    public void sendJoin(Party party, Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender, "Vous avez rejoins le groupe");
        for(Player player : party.getPlayers()){
            ChatPlayer playerChatPlayer = ChatAPI.getInstance().getChatPlayer(player);
            playerChatPlayer.sendSuccessMessage(nameSender, p.getDisplayName()+ChatColor.GRAY+" à rejoins le groupe");
        }
        ChatPlayer ownerChatPlayer = ChatAPI.getInstance().getChatPlayer(party.getOwner());
        ownerChatPlayer.sendSuccessMessage(nameSender, p.getDisplayName()+ChatColor.GRAY+" à rejoins le groupe");

    }

    public void sendNotSameParty(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Vous n'êtes pas dans le même groupe que ce joueur");
    }



    public void sendLeave(Party party, Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender, ChatColor.DARK_RED+"Vous avez quitté le groupe");
        for(Player player : party.getPlayers()){
            ChatPlayer plsChatPlayer = ChatAPI.getInstance().getChatPlayer(player);
            plsChatPlayer.sendInfoMessage(nameSender, ChatColor.RED+p.getDisplayName()+ChatColor.GRAY+" à quitté le groupe");
        }
        ChatPlayer ownerChatPlayer = ChatAPI.getInstance().getChatPlayer(party.getOwner());
        ownerChatPlayer.sendInfoMessage(nameSender, ChatColor.RED+p.getDisplayName()+ChatColor.GRAY+" à quitté le groupe");
    }

    public void sendKickMessage(Player p, Player kick) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Vous avez éjecté "+kick.getDisplayName()+" avec succé");
        ChatPlayer kickChatPlayer = ChatAPI.getInstance().getChatPlayer(kick);
        kickChatPlayer.sendErrorMessage(nameSender, "Le chef vous a exclu du groupe");
    }

    public void sendInfoParty(Player p, Party party) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        String toSend = ChatColor.BLUE+"Liste des joueurs :"+ChatColor.GRAY;
        ArrayList<Player> players = party.getPlayers();
         if(players.size() != 0)
            for(Player player : players){
                toSend+=player.getDisplayName();
                if(player != players.get(players.size()-1))
                    toSend+=" ,";

            }
        else
            toSend+=ChatColor.RED+"Personne";

        chatPlayer.sendInfoMessage(nameSender, toSend);
        chatPlayer.sendInfoMessage(nameSender,"Chef: "+party.getOwner().getDisplayName());
        if(party.isOpen())
            chatPlayer.sendSuccessMessage(nameSender,"Le groupe est OUVERT");
        else
            chatPlayer.sendSuccessMessage(nameSender,"Le groupe est FERME");

    }

    public void sendDisband(Party party) {
        for(Player pls : party.getPlayers()) {
            ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(pls);
            chatPlayer.sendErrorMessage(nameSender, "Votre groupe a été dissous");
        }
        ChatPlayer ownerChatPlayer = ChatAPI.getInstance().getChatPlayer(party.getOwner());
        ownerChatPlayer.sendSuccessMessage(nameSender, "Vous venez de dissoudre votre groupe");
    }

    public void sendOwnerCantLeave(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Vous ne pouvez pas quitter votre groupe en tant que chef");
    }

    public void sendCantSelectHimSelf(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Vous ne pouvez pas faire cela !");
    }

    public void sendConfirmMessage(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Voulez vous vraiment rejoindre ce groupe ?");
        sendConfirmOrNot(p);
    }

    public void notCorrectEnterValue(Player p){
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Cette valeur est incorect");
        sendConfirmOrNot(p);
    }

    private void sendConfirmOrNot(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Ecrivez "+ChatColor.DARK_GREEN+"\"OUI\""+ChatColor.GREEN+" pour confirmer ou "+ChatColor.DARK_RED+"\"NON\""+ChatColor.GREEN+" pour annuler");
    }

    public void sendSuccessCancelChat(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Voulez resterez donc dans votre groupe !");
    }

    public void sendNotInSameParty(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Ce joueur n'est pas dans votre groupe");
    }

    public void sendNewLeader(Player leader, Party party) {
        for(Player pls : party.getPlayers()){
            ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(pls);
            chatPlayer.sendInfoMessage(nameSender,leader+" est maintenant le nouveau chef du groupe");
        }
    }

    public void sendOpenParty(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Votre groupe est maintenant OUVERT au public");
    }

    public void sendCloseParty(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Votre groupe est maintenant FERME au public");
    }

    public void sendNotPlayer(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Ce joueur est invalide");
    }

    public void sendNotLeaderOfHisParty(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Ce joueur n'est pas le chef de son groupe");
    }

    public void sendPartyNotOpen(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Le groupe de ce joueur est fermé");
    }

    public void sendPartyFull(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Le groupe est complet");
    }

    public void sendPutInQueu(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendInfoMessage(nameSender,"Le groupe est complet, vous êtes donc en liste d'attente pour le rejoindre");
    }

    public void sendPartyRandom(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Le chef de votre groupe vous à expulsé suite à un renouvellement du groupe");
    }

    public void sendJoinParty(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Vous venez de rejoindre ");
    }

    public void sendSuccessRandom(Player owner) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(owner);
        chatPlayer.sendInfoMessage(nameSender, "Votre groupe est maintenant tout fraix !");
    }

    public void sendAlreadyInQueue(Player p) {
        ChatPlayer chatPlayer = ChatAPI.getInstance().getChatPlayer(p);
        chatPlayer.sendSuccessMessage(nameSender,"Vous êtes déjà en attente dans ce groupe");
    }
}
