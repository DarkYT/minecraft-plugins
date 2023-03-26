package fr.basiccommandsbungee.basiccommands.commands;

import fr.basiccommandsbungee.basiccommands.BasicCommands;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class youtubeCommand extends Command{
	
	BasicCommands basiccommands;
	public youtubeCommand(String name, BasicCommands basiccommands) {
		super(name);
		
		this.basiccommands = basiccommands;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer p = (ProxiedPlayer) sender;
		p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7Chaîne YouTube &8&l» &chttps://www.youtube.com/channel/UCO2vMbqM-CI5RXId0QQaW_w")));
	}


}
