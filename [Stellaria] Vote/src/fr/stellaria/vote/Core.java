package fr.stellaria.vote;

import org.bukkit.plugin.java.JavaPlugin;

import fr.stellaria.vote.commands.voteCommand;

public class Core extends JavaPlugin {
	
	public sqlConnect sql;
	
	@Override
	public void onEnable() {
		sql = new sqlConnect("jdbc:mysql://","localhost","vote","root","StellariaTED**");
		sql.connection();
		getCommand("vote").setExecutor(new voteCommand(this, sql));
		saveDefaultConfig();
	}
	
	@Override
	public void onDisable(){
		sql.disconnect();
	}
	
	public boolean isEmptyConfig() {
		return this.getConfig().getKeys(false).isEmpty();
	}

}
