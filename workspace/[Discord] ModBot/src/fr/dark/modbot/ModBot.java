package fr.dark.modbot;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import fr.dark.modbot.commands.CommandMap;
import fr.dark.modbot.events.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class ModBot implements Runnable{
	
	private final JDA jda;
	private final CommandMap CommandManager = new CommandMap(this);
	private final Scanner scanner = new Scanner(System.in);
	
	private boolean running;
	
	public ModBot() throws LoginException, IllegalArgumentException, RateLimitedException {
		jda = new JDABuilder(AccountType.BOT).setToken("NDYzNzA4MzAxNDE1Mjg0NzU3.Dh0fLw.BcL0GFY24HvKRXlMER1XcQI8xKc").buildAsync();
		jda.addEventListener(new BotListener(CommandManager));
		System.out.println("[ModBot] Bot Enable !");		
	}
	
	public JDA getJda() {
		return jda;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	@Override
	public void run() {
		running = true;
		
		while (running) {
			if(scanner.hasNextLine()) CommandManager.commandConsole(scanner.nextLine());
		}
		
		scanner.close();
		System.out.println("[ModBot] Bot Disable !");
		jda.shutdown();
		CommandManager.save();
		System.exit(0);
	}
	
	public static void main(String[] args) {
		try {
			ModBot ModBot = new ModBot();
			new Thread(ModBot, "bot").start();
		} catch (LoginException | IllegalArgumentException | RateLimitedException e) {
			e.printStackTrace();
		}
	}

}
