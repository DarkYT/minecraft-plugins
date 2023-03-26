package fr.dark.modbot.commands.defaut;

import java.util.List;

import fr.dark.modbot.ModBot;
import fr.dark.modbot.commands.Command;
import fr.dark.modbot.commands.Command.ExecutorType;
import fr.dark.modbot.commands.CommandMap;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.exceptions.PermissionException;

public class ModCommands {

	private final ModBot ModBot;
	private final CommandMap commandMap;
	
	public ModCommands(ModBot ModBot, CommandMap commandMap){
		this.ModBot = ModBot;
		this.commandMap = commandMap;
	}
	
	@Command(name="stop",type=ExecutorType.CONSOLE)
	private void stop(){
		ModBot.setRunning(false);
	}
	
	@Command(name="power",power=150)
	private void power(User user, MessageChannel channel, Message message, String[] args)
	{
		if(args.length == 0 || message.getMentionedUsers().size() == 0)
		{
			channel.sendMessage("power <Power> <@User>").queue();
			return;
		}
		
		int power = 0;
		try{
			power = Integer.parseInt(args[0]);
		}catch(NumberFormatException nfe){
			channel.sendMessage("Le power doit être un nombre.").queue();
			return;
		}
		
		User target = message.getMentionedUsers().get(0);
		commandMap.addUserPower(target, power);
		channel.sendMessage("Le pouvoir de "+target.getAsMention()+" est maintenant de "+power).queue();
	}
	
	@SuppressWarnings("unused")
	@Command(name="kick",power=100)
	private void kick(User user, MessageChannel channel, Message message, String[] args, Guild guild) {
		if (message.isFromType(ChannelType.TEXT))
        {
            if (message.getMentionedUsers().isEmpty())
            {
                channel.sendMessage("Tu dois mentionner un ou des joueur(s) pour le(s) kick !").queue();
            }
            else
            {
                Member selfMember = guild.getSelfMember();

                if (!selfMember.hasPermission(Permission.KICK_MEMBERS))
                {
                    channel.sendMessage("Désolé, mais je n'ai pas les permissions pour kick !").queue();
                    return; 
                }

                List<User> mentionedUsers = message.getMentionedUsers();
                for (User us : mentionedUsers)
                {
                    Member member = guild.getMember(us);  

                    if (!selfMember.canInteract(member))
                    {
                        channel.sendMessage("Impossible de kick ")
                               .append(member.getEffectiveName())
                               .append(", il est plus gradé que moi !")
                               .queue();
                        continue; 
                    }

                    guild.getController().kick(member).queue(
                        success -> channel.sendMessage("").append(member.getEffectiveName()).append(" est kick ! Bye!").queue(),
                        error ->
                        {
                            if (error instanceof PermissionException)
                            {
                                PermissionException pe = (PermissionException) error;
                                Permission missingPermission = pe.getPermission();  

                                channel.sendMessage("PermissionError kick [")
                                       .append(member.getEffectiveName()).append("]: ")
                                       .append(error.getMessage()).queue();
                            }
                            else
                            {
                                channel.sendMessage("Erreur inconnue durant le kick [")
                                       .append(member.getEffectiveName())
                                       .append("]: <").append(error.getClass().getSimpleName()).append(">: ")
                                       .append(error.getMessage()).queue();
                            }
                        });
                }
            }
        }
	}
	
	@SuppressWarnings("unused")
	@Command(name="ban",power=120)
	private void ban(User user, MessageChannel channel, Message message, String[] args, Guild guild) {
		if (message.isFromType(ChannelType.TEXT))
        {
            if (message.getMentionedUsers().isEmpty())
            {
                channel.sendMessage("Tu dois mentionner un ou des joueur(s) pour le(s) ban !").queue();
            }
            else
            {
                Member selfMember = guild.getSelfMember();

                if (!selfMember.hasPermission(Permission.BAN_MEMBERS))
                {
                    channel.sendMessage("Désolé, mais je n'ai pas les permissions pour ban !").queue();
                    return; 
                }

                List<User> mentionedUsers = message.getMentionedUsers();
                for (User us : mentionedUsers)
                {
                    Member member = guild.getMember(us);  

                    if (!selfMember.canInteract(member))
                    {
                        channel.sendMessage("Impossible de ban ")
                               .append(member.getEffectiveName())
                               .append(", il est plus gradé que moi !")
                               .queue();
                        continue; 
                    }

                    guild.getController().ban(member, 0).queue(
                        success -> channel.sendMessage("").append(member.getEffectiveName()).append(" est kick ! Bye!").queue(),
                        error ->
                        {
                            if (error instanceof PermissionException)
                            {
                                PermissionException pe = (PermissionException) error;
                                Permission missingPermission = pe.getPermission();  

                                channel.sendMessage("PermissionError kick [")
                                       .append(member.getEffectiveName()).append("]: ")
                                       .append(error.getMessage()).queue();
                            }
                            else
                            {
                                channel.sendMessage("Erreur inconnue durant le kick [")
                                       .append(member.getEffectiveName())
                                       .append("]: <").append(error.getClass().getSimpleName()).append(">: ")
                                       .append(error.getMessage()).queue();
                            }
                        });
                }
            }
        }
	}

	public CommandMap getCommandMap() {
		return commandMap;
	}

}
