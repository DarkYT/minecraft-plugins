package com.endwork.api.chat.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.ArrayList;

/**
 * @author thephoenix2feu, for EndWork©
 */
public class CommandChat extends BukkitCommand {

    private ChatCommandExecutor chatCommandExecutor;

    public CommandChat(String name, String desc, ArrayList<String> aliases, String perm,ChatCommandExecutor chatCommandExecutor) {
        super(name);
        this.chatCommandExecutor = chatCommandExecutor;
        this.description = desc;
        this.setPermission(perm);
        this.setAliases(aliases);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(chatCommandExecutor != null)
            chatCommandExecutor.execute(commandSender, s, strings);
        return false;
    }
}
