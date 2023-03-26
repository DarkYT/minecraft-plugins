package com.endwork.api.chat.commands;

import org.bukkit.command.CommandSender;

/**
 * @author thephoenix2feu, for EndWork©
 */
public interface ChatCommandExecutor {
    public boolean execute(CommandSender commandSender, String command, String[] args);
}
