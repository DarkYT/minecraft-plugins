package fr.dark.modbot.commands;

import java.lang.reflect.Method;

import fr.dark.modbot.commands.Command.ExecutorType;

public final class BotCommand {
	
	private final String name, description;
	private final ExecutorType executorType;
	private final Object object;
	private final Method method;
	private final int power;
	
	public BotCommand(String name, String description, ExecutorType executorType, Object object, Method method, int power){
		super();
		this.name = name;
		this.description = description;
		this.executorType = executorType;
		this.object = object;
		this.method = method;
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ExecutorType getExecutorType() {
		return executorType;
	}

	public Object getObject() {
		return object;
	}

	public Method getMethod() {
		return method;
	}
	
	public int getPower() {
		return power;
	}

}
