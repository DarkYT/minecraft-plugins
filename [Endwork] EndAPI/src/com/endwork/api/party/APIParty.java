package com.endwork.api.party;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class APIParty {

    public ArrayList<Party> partys = new ArrayList<Party>();
    public HashMap<Player, Party> invites = new HashMap<>();

    public WaitForConfirm waitForConfirm = new WaitForConfirm();
    public MessagesManage msg = new MessagesManage();

    private static APIParty instance = null;

    private static APIParty getAPI(){
        return new APIParty();
    }

    public static APIParty getInstance(){
        if(instance == null){
            instance = getAPI();
        }
        return instance;
    }

    public Party getParty(Player p){
        for(Party party : partys)
            if(party.isInParty(p))
                return party;

            return null;
    }

    public boolean isInParty(Player p){
        return getParty(p) != null;
    }

    public void kick(Player p){
        Party party = getParty(p);
        if(party != null)
            party.kick(p);
    }

    public void joinParty(Player p, Party party){
        if(getParty(p) == null)
            party.addPlayer(p);
    }

}
