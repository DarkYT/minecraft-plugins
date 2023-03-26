package com.endwork.api.chat.commands;

import com.endwork.api.chat.ChatAPI;
import org.bukkit.craftbukkit.v1_13_R2.CraftServer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette class est utilisé pour gérer toute les commandes du chat, aussi bien en ajouter que en enlever et définir les exucutions des commandes
 * @author thephoenix2feu, for EndWork©
 */
public class ChatCommandManager {

    private com.endwork.api.chat.ChatAPI ChatAPI;
    private HashMap<String ,ChatCommandExecutor> cmdInvisible = new HashMap<>();

    /**
     * @param ChatAPI Le ChatAPI utilisé contiendra <b>toute</b> les commandes register dans cette class
     */
    public ChatCommandManager(ChatAPI ChatAPI) {
        this.ChatAPI = ChatAPI;
    }

    public void registerCommand(String name, ChatCommandExecutor chatCommandExecutor, String perm){
        ((CraftServer) ChatAPI.chatPlugin.getServer()).getCommandMap().register(name, new CommandChat(name, "", new ArrayList<String>(), perm, chatCommandExecutor));
    }
    public void registerCommand(String name, ChatCommandExecutor chatCommandExecutor,String perm ,String description){
        ((CraftServer) ChatAPI.chatPlugin.getServer()).getCommandMap().register(name, new CommandChat(name, description, new ArrayList<String>(),perm, chatCommandExecutor));
    }

    /**
     *Cette fonction vas ajouter une commande au serveur ainsi que dans le help, elle est utilisée pour créer des commandes <b>publique</b>
     * @param name nom de la commande
     * @param chatCommandExecutor gère l'éxécution de la commande
     * @param perm gère les permissions nécessaire à l'éxecution de la commande par le joueur
     * @param description la description de la commande dans le /help
     * @param aliases tout les différents surnom/noms de la commande
     * @see ChatCommandExecutor
     */
    public void registerCommand(String name, ChatCommandExecutor chatCommandExecutor,String perm ,String description, ArrayList<String> aliases){
        ((CraftServer) ChatAPI.chatPlugin.getServer()).getCommandMap().register(name, new CommandChat(name, description, aliases,perm, chatCommandExecutor));
    }

    /**
     * Cette fonction vas ajouter une commande sans que celle-ci ne puissent être visible ni dans le help ni en auto-completition, elle est utilisée pour créer des commandes <b>cachées</b>
     * <br><u>Si cette commande requiere une permissions, elle doit être tester dans le {@link ChatCommandExecutor}</u>
     * @param name Le nom de la commande
     * @param chatCommandExecutor L'éxécution de la commande
     * @see ChatCommandExecutor
     */
    public void registerCommandNotVisible(String name, ChatCommandExecutor chatCommandExecutor){
        if(!cmdInvisible.containsKey(name))
            cmdInvisible.put(name, chatCommandExecutor);
    }

    /**
     * Cette commande supprime une commande <b>publique</b>, il faut ainsi que la commande soit register dans le serveur
     * @param name Le nom de la commande à supprimer
     * @return Retourne true si l'execution s'est correctement déroulée
     * @see registerCommand
     */
    public boolean unregisterCommand(String name){
        if(!isRegisteredCommand(name))
            return false;
        ChatAPI.chatPlugin.getCommand(name).unregister(((CraftServer) ChatAPI.chatPlugin.getServer()).getCommandMap());
        return true;
    }
    /**
     * Cette commande supprime une commande <b>cachée</b>, il ne faut <u>pas</u> que la commande soit register dans le server
     * @param name Le nom de la commande à supprimer
     * @return Retourne true si l'execution s'est correctement déroulée
     * @see registerCommandNotVisible
     */
    public void unregisterCommandNotVisible(String name){
        cmdInvisible.remove(name);
    }

    /**
     * Vérifie si une commande a été ajouté dans le server, et est donc <b>publique</b>
     * @param name Le nom de la commande a vérifié
     * @return Cela return true si la commande est effectivement présente dans le serveur
     */
    public boolean isRegisteredCommand(String name){
        return ChatAPI.chatPlugin.getCommand(name).isRegistered();
    }

    /**
     * Vérifie si une commande qui est <b>cahcé</b> existe
     * @param name Le nom de la commande a vérifié
     * @return Cela return true si la commande existe
     */
    public boolean isRegisteredNotVisibleCommand(String name){
        return cmdInvisible.containsKey(name);
    }

    /**
     *Cetet fonction retourne la class d'éxécution d'une commande
     * @param name Le nom de la commande
     * @return Le @{@link ChatCommandExecutor} de la commande
     */
    public ChatCommandExecutor getChatCommandeExecutor(String name){
        if(isRegisteredNotVisibleCommand(name))
            return cmdInvisible.get(name);
        else
            return null;
    }

}
