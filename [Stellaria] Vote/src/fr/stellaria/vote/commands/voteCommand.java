package fr.stellaria.vote.commands;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.stellaria.vote.Core;
import fr.stellaria.vote.sqlConnect;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class voteCommand implements CommandExecutor {

	Core core;
	sqlConnect sql;
	public voteCommand(Core core, sqlConnect sql) {
		this.core = core;
		this.sql = sql;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(core.isEmptyConfig()){
				core.getConfig().set("Votes." + p.getName() + ".site", (new SimpleDateFormat("HH:mm:ss")).format(Calendar.getInstance().getTime()));
				core.saveConfig();
				TextComponent message = new TextComponent("Vous n'avez pas voté, rendez vous sur");
				message.setColor(ChatColor.YELLOW);
				TextComponent url = new TextComponent( " Stellaria-mc.fr" );
				url.setColor( ChatColor.DARK_PURPLE );
				url.setBold( true );
				url.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "http://stellaria-mc.fr/vote.php" ) );
				message.addExtra(url);
				message.addExtra(" pour voter");
				p.spigot().sendMessage(message);
				return true;
			}
			if(!sql.hasVoted(p)){
				p.sendMessage("§cVas voter sur le serveur avant !");
				return true;
			}
			if(sql.hasVoted(p) && !core.getConfig().getConfigurationSection("Votes." + p.getName()).contains("key")){
				p.sendMessage("§e Merci d'avoir voté pour Stellaria. Pour vous remercier, voici §5§lune clé de vote §e!");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crates key "+ p.getName() +" vote");
				core.getConfig().set("Votes." + p.getName() + ".key", (new SimpleDateFormat("HH:mm:ss")).format(Calendar.getInstance().getTime()));
				sql.hasRewarded(p);
				core.saveConfig();
				return true;
			}
			if(!core.getConfig().getConfigurationSection("Votes").contains(p.getName())){
				core.getConfig().createSection("Votes." + p.getName());
				core.saveConfig();
			}
			core.getConfig().set("Votes." + p.getName() + ".site", (new SimpleDateFormat("HH:mm:ss")).format(Calendar.getInstance().getTime()));
			core.saveConfig();
			TextComponent message = new TextComponent("Vous n'avez pas voté, rendez vous sur");
			message.setColor(ChatColor.YELLOW);
			TextComponent url = new TextComponent( " Stellaria-mc.fr" );
			url.setColor( ChatColor.DARK_PURPLE );
			url.setBold( true );
			url.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "http://stellaria-mc.fr/vote.php" ) );
			message.addExtra(url);
			message.addExtra(" pour voter");
			p.spigot().sendMessage(message);
			return true;
		}
		return false;
	}

}
