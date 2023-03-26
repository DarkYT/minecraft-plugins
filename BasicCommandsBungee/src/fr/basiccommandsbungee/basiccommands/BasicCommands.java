package fr.basiccommandsbungee.basiccommands;

import fr.basiccommandsbungee.basiccommands.commands.siteCommand;
import fr.basiccommandsbungee.basiccommands.commands.teamspeakCommand;
import fr.basiccommandsbungee.basiccommands.commands.twitterCommand;
import fr.basiccommandsbungee.basiccommands.commands.youtubeCommand;
import net.md_5.bungee.api.plugin.Plugin;

public class BasicCommands extends Plugin {
	
	@Override
	public void onEnable(){
		getProxy().getPluginManager().registerCommand(this, new teamspeakCommand("teamspeak", this));
		getProxy().getPluginManager().registerCommand(this, new siteCommand("site", this));
		getProxy().getPluginManager().registerCommand(this, new twitterCommand("twitter", this));
		getProxy().getPluginManager().registerCommand(this, new youtubeCommand("youtube", this));
	}
	
}
