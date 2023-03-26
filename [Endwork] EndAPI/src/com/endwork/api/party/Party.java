package com.endwork.api.party;

import com.endwork.api.chat.ChatAPI;
import com.endwork.api.chat.channel.Channel;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Map;

public class Party {

    private Player owner;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> queue = new ArrayList<Player>();
    private boolean isOpen;
    private int sizeMax;
    private Channel channel;
    public MessagesManage msg = new MessagesManage();

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void addQueue(Player p){
        if(queue.contains(p)){
            msg.sendAlreadyInQueue(p);
            return;
        }
        if(isFull())
            msg.sendPutInQueu(p);
        else
            addPlayer(p);
    }

    public Party(Player owner){
        this.owner = owner;
        this.isOpen = false;
        ArrayList<String> prefix = new ArrayList<>();
        prefix.add("*");
        String name = "PARTY_"+owner.getDisplayName();
        channel = new Channel(prefix, 9999, true, name+"", name+"", "", "", "&5[Party] %pseudo%: &7", true, true, false);
        ChatAPI.getInstance().addChannel(channel);
    }

    public void addPlayer(Player p){
        if(isFull()) {
            msg.sendPartyFull(p);
            return;
        }

        if(!players.contains(p) && owner != p) {
            msg.sendJoin(this, p);
            players.add(p);
        }
    }

    private boolean isFull() {
        return sizeMax <= players.size();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void playerRemove(Player p){
        if(players.contains(p)){
            players.remove(p);
            msg.sendLeave(this, p);
        }
    }

    public boolean isOwner(Player p){
        return p == owner;
    }

    public boolean isInParty(Player p) {
        if(players.contains(p) || owner == p)
            return true;

        return false;
    }

    public Player getOwner() {
        return owner;
    }

    public void disband() {
        for (Map.Entry<Player, Party> invite : APIParty.getInstance().invites.entrySet()){
            if(invite.getValue() == this)
                APIParty.getInstance().invites.remove(invite.getKey());
        }
        msg.sendDisband(this);
        APIParty.getInstance().partys.remove(this);
        ChatAPI.getInstance().removeChannel(channel);
    }

    public void setOwner(Player leader) {
        players.remove(leader);
        owner = leader;
        msg.sendNewLeader(leader, this);
    }

    public void random() {

        for(Player pls : players) {
            boolean found = false;
            Player toAdd = null;

            while (found){
                int numeroRandom = getRandom(0, (queue.size()-1));
                toAdd = queue.get(numeroRandom);
                if(!APIParty.getInstance().isInParty(toAdd))
                    found = true;

                queue.remove(toAdd);
                if(queue.size() == 0 && !found)
                    break;
            }
            players.remove(pls);
            addPlayer(toAdd);
            msg.sendPartyRandom(pls);

            if(!found)
                break;
        }

        msg.sendSuccessRandom(owner);

    }

    public boolean kick(Player kick) {

        if(players.contains(kick)){
            this.playerRemove(kick);
            msg.sendKickMessage(owner, kick);
            return true;
        }else
            return false;

    }

    private int getRandom(int min, int max) {
        return (int) (Math.random() * max + min);
    }
}
