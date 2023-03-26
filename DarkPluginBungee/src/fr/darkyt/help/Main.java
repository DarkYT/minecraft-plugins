package fr.darkyt.help;

import java.util.HashMap;
import java.util.Map;

import fr.darkyt.help.commands.CommandHelp;
import fr.darkyt.help.commands.CommandReport;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin{
	
	public static Map<String, Long> cooldown = new HashMap<>();
	
	@Override
	public void onEnable() {

		System.out.println("Le plugin s'allume");
		getProxy().getPluginManager().registerCommand(this, new CommandHelp("helpme", this));
		getProxy().getPluginManager().registerCommand(this, new CommandReport("report", this));
		
		for (ProxiedPlayer pl : getProxy().getPlayers()){
			cooldown.put(pl.getName(), (long) 0);
		}
	}
}
