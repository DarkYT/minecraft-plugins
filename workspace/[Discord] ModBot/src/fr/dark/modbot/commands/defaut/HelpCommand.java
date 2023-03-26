package fr.dark.modbot.commands.defaut;

import java.awt.Color;

import fr.dark.modbot.commands.BotCommand;
import fr.dark.modbot.commands.Command;
import fr.dark.modbot.commands.Command.ExecutorType;
import fr.dark.modbot.commands.CommandMap;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.UserImpl;

public class HelpCommand {
	
	private final CommandMap commandMap;
	
	public HelpCommand(CommandMap commandMap) {
		this.commandMap = commandMap;
	}
	
	@Command(name="help",type=ExecutorType.USER,description="affiche la liste des commandes.")
	private void help(User user, MessageChannel channel, Guild guild){
		
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle("Commandes de modération:");
		builder.setColor(Color.CYAN);
		
		for(BotCommand command : commandMap.getCommands()){
			if(command.getExecutorType() == ExecutorType.CONSOLE) continue;
			
			if(guild != null && command.getPower() > commandMap.getPowerUser(guild, user)) continue;
			
			builder.addField(command.getName(), command.getDescription(), false);
		}
		
		if(!user.hasPrivateChannel()) user.openPrivateChannel().complete();
		((UserImpl)user).getPrivateChannel().sendMessage(builder.build()).queue();
		
		channel.sendMessage(user.getAsMention()+", la liste des commandes a été envoyée en MP.").queue();
		
	}

	public CommandMap getCommandMap() {
		return commandMap;
	}

}
