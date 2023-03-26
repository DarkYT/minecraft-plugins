package fr.dark.ram.commands;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.dark.ram.Core;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ramCommand implements CommandExecutor {
	
	Core core;
	public ramCommand(Core core) {this.core = core;}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ram")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.isOp()){
					YamlConfiguration yc = YamlConfiguration.loadConfiguration(Core.confFile);
					if(args.length == 0 || args[0].equalsIgnoreCase("help")){
						for(String help : core.getConfig().getStringList("Messages.Command.Help")){
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
						}
						return true;
					}
					if(args.length == 1){
						if(args[0].equalsIgnoreCase("reload")){
							core.reloadConfig();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("Messages.Command.Reload")));
						}
						if(args[0].equalsIgnoreCase("view")){
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("Messages.Command.View.Intro")));
							for(String s : yc.getConfigurationSection("Rams").getKeys(false)){
								String[] coords = yc.getString("Rams."+s+".head").split(";");
								String message = core.getConfig().getString("Messages.Command.View.DispMess");
								message = message.replace("<X>", coords[0]);
								message = message.replace("<Y>", coords[1]);
								message = message.replace("<Z>", coords[2]);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
							}
						}else if(args[0].equalsIgnoreCase("set")){
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("Messages.Command.Confirm")));
							TextComponent message = new TextComponent("[Oui] ");
							message.setColor(ChatColor.GREEN);
							message.setBold(true);
							message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ram set sok"));
							TextComponent no = new TextComponent( "[Non]" );
							no.setColor( ChatColor.RED );
							no.setBold( true );
							no.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/ram set stop" ) );
							message.addExtra(no);
							p.spigot().sendMessage(message);
							return true;
						}
					}
					if(args.length == 2){
						if(args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("sok")){
							Location head = p.getLocation().add(0,-1,0);
							BlockFace face = core.CardinalDirection(p);
							int X = (int) head.getBlockX();
							int Y = (int) head.getBlockY();
							int Z = (int) head.getBlockZ();
							yc.set("Rams.Ram/"+X+Y+Z+".world", p.getLocation().getWorld().getName());
							yc.set("Rams.Ram/"+X+Y+Z+".head", X+";"+Y+";"+Z);
							yc.set("Rams.Ram/"+X+Y+Z+".face", face.toString());
							yc.set("Plates.Ram/"+X+Y+Z+".world", p.getLocation().getWorld().getName());
							core.saveCustomYml(yc, Core.confFile);
							switch (face) {
							case NORTH:
								Location body1 = p.getLocation().add(0,0,1);
								Location body2 = p.getLocation().add(0,0,2);
								Location body3 = p.getLocation().add(0,0,3);
								Location body4 = p.getLocation().add(0,0,4);
								Location body5 = p.getLocation().add(0,0,5);
								Location tail = p.getLocation().add(0,0,6);
								Location handle1 = p.getLocation().add(1,0,2);
								Location handle2 = p.getLocation().add(1,0,4);
								Location handle3 = p.getLocation().add(-1,0,2);
								Location handle4 = p.getLocation().add(-1,0,4);
								yc.set("Rams.Ram/"+X+Y+Z+".body1.loc", body1.getBlockX()+";"+body1.getBlockY()+";"+body1.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.loc", body2.getBlockX()+";"+body2.getBlockY()+";"+body2.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.loc", body3.getBlockX()+";"+body3.getBlockY()+";"+body3.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body4.loc", body4.getBlockX()+";"+body4.getBlockY()+";"+body4.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body5.loc", body5.getBlockX()+";"+body5.getBlockY()+";"+body5.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.loc", tail.getBlockX()+";"+tail.getBlockY()+";"+tail.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle1.loc", handle1.getBlockX()+";"+handle1.getBlockY()+";"+handle1.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle2.loc", handle2.getBlockX()+";"+handle2.getBlockY()+";"+handle2.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle3.loc", handle3.getBlockX()+";"+handle3.getBlockY()+";"+handle3.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle4.loc", handle4.getBlockX()+";"+handle4.getBlockY()+";"+handle4.getBlockZ());
								core.saveCustomYml(yc, Core.confFile);
								Location p1 = handle1.add(0,0,1);
								Location p2 = handle2.add(0,0,1);
								Location p3 = handle3.add(0,0,1);
								Location p4 = handle4.add(0,0,1);
								yc.set("Plates.Ram/"+X+Y+Z+".first", p1.getBlockX()+";"+p1.getBlockY()+";"+p1.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".second", p2.getBlockX()+";"+p2.getBlockY()+";"+p2.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".third", p3.getBlockX()+";"+p3.getBlockY()+";"+p3.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".fourth", p4.getBlockX()+";"+p4.getBlockY()+";"+p4.getBlockZ());
								core.saveCustomYml(yc, Core.confFile);
								core.create("Ram/"+X+""+Y+""+Z, face);
								break;
							case SOUTH:
								Location body12 = p.getLocation().add(0,0,-1);
								Location body22 = p.getLocation().add(0,0,-2);
								Location body32 = p.getLocation().add(0,0,-3);
								Location body42 = p.getLocation().add(0,0,-4);
								Location body52 = p.getLocation().add(0,0,-5);
								Location tail2 = p.getLocation().add(0,0,-6);
								Location handle12 = p.getLocation().add(1,0,-2);
								Location handle22 = p.getLocation().add(1,0,-4);
								Location handle32 = p.getLocation().add(-1,0,-2);
								Location handle42 = p.getLocation().add(-1,0,-4);
								yc.set("Rams.Ram/"+X+Y+Z+".body1.loc", body12.getBlockX()+";"+body12.getBlockY()+";"+body12.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.loc", body22.getBlockX()+";"+body22.getBlockY()+";"+body22.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.loc", body32.getBlockX()+";"+body32.getBlockY()+";"+body32.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body4.loc", body42.getBlockX()+";"+body42.getBlockY()+";"+body42.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body5.loc", body52.getBlockX()+";"+body52.getBlockY()+";"+body52.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.loc", tail2.getBlockX()+";"+tail2.getBlockY()+";"+tail2.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle1.loc", handle12.getBlockX()+";"+handle12.getBlockY()+";"+handle12.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle2.loc", handle22.getBlockX()+";"+handle22.getBlockY()+";"+handle22.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle3.loc", handle32.getBlockX()+";"+handle32.getBlockY()+";"+handle32.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle4.loc", handle42.getBlockX()+";"+handle42.getBlockY()+";"+handle42.getBlockZ());
								core.saveCustomYml(yc, Core.confFile);
								Location p12 = handle12.add(0,0,-1);
								Location p22 = handle22.add(0,0,-1);
								Location p32 = handle32.add(0,0,-1);
								Location p42 = handle42.add(0,0,-1);
								yc.set("Plates.Ram/"+X+Y+Z+".first", p12.getBlockX()+";"+p12.getBlockY()+";"+p12.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".second", p22.getBlockX()+";"+p22.getBlockY()+";"+p22.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".third", p32.getBlockX()+";"+p32.getBlockY()+";"+p32.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".fourth", p42.getBlockX()+";"+p42.getBlockY()+";"+p42.getBlockZ());
								core.saveCustomYml(yc, Core.confFile);
								core.create("Ram/"+X+""+Y+""+Z, face);
								break;
							case EAST:
								Location body13 = p.getLocation().add(-1,0,0);
								Location body23 = p.getLocation().add(-2,0,0);
								Location body33 = p.getLocation().add(-3,0,0);
								Location body43 = p.getLocation().add(-4,0,0);
								Location body53 = p.getLocation().add(-5,0,0);
								Location tail3 = p.getLocation().add(-6,0,0);
								Location handle13 = p.getLocation().add(-2,0,1);
								Location handle23 = p.getLocation().add(-4,0,1);
								Location handle33 = p.getLocation().add(-2,0,-1);
								Location handle43 = p.getLocation().add(-4,0,-1);
								yc.set("Rams.Ram/"+X+Y+Z+".body1.loc", body13.getBlockX()+";"+body13.getBlockY()+";"+body13.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.loc", body23.getBlockX()+";"+body23.getBlockY()+";"+body23.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.loc", body33.getBlockX()+";"+body33.getBlockY()+";"+body33.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body4.loc", body43.getBlockX()+";"+body43.getBlockY()+";"+body43.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body5.loc", body53.getBlockX()+";"+body53.getBlockY()+";"+body53.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.loc", tail3.getBlockX()+";"+tail3.getBlockY()+";"+tail3.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle1.loc", handle13.getBlockX()+";"+handle13.getBlockY()+";"+handle13.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle2.loc", handle23.getBlockX()+";"+handle23.getBlockY()+";"+handle23.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle3.loc", handle33.getBlockX()+";"+handle33.getBlockY()+";"+handle33.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle4.loc", handle43.getBlockX()+";"+handle43.getBlockY()+";"+handle43.getBlockZ());
								core.saveCustomYml(yc, Core.confFile);
								Location p13 = handle13.add(-1,0,0);
								Location p23 = handle23.add(-1,0,0);
								Location p33 = handle33.add(-1,0,0);
								Location p43 = handle43.add(-1,0,0);
								yc.set("Plates.Ram/"+X+Y+Z+".first", p13.getBlockX()+";"+p13.getBlockY()+";"+p13.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".second", p23.getBlockX()+";"+p23.getBlockY()+";"+p23.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".third", p33.getBlockX()+";"+p33.getBlockY()+";"+p33.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".fourth", p43.getBlockX()+";"+p43.getBlockY()+";"+p43.getBlockZ());
								core.saveCustomYml(yc, Core.confFile);
								core.create("Ram/"+X+""+Y+""+Z, face);
								break;
							case WEST:
								Location body14 = p.getLocation().add(1,0,0);
								Location body24 = p.getLocation().add(2,0,0);
								Location body34 = p.getLocation().add(3,0,0);
								Location body44 = p.getLocation().add(4,0,0);
								Location body54 = p.getLocation().add(5,0,0);
								Location tail4 = p.getLocation().add(6,0,0);
								Location handle14 = p.getLocation().add(2,0,1);
								Location handle24 = p.getLocation().add(4,0,1);
								Location handle34 = p.getLocation().add(2,0,-1);
								Location handle44 = p.getLocation().add(4,0,-1);
								yc.set("Rams.Ram/"+X+Y+Z+".body1.loc", body14.getBlockX()+";"+body14.getBlockY()+";"+body14.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.loc", body24.getBlockX()+";"+body24.getBlockY()+";"+body24.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.loc", body34.getBlockX()+";"+body34.getBlockY()+";"+body34.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body4.loc", body44.getBlockX()+";"+body44.getBlockY()+";"+body44.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body5.loc", body54.getBlockX()+";"+body54.getBlockY()+";"+body54.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.loc", tail4.getBlockX()+";"+tail4.getBlockY()+";"+tail4.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle1.loc", handle14.getBlockX()+";"+handle14.getBlockY()+";"+handle14.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle2.loc", handle24.getBlockX()+";"+handle24.getBlockY()+";"+handle24.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle3.loc", handle34.getBlockX()+";"+handle34.getBlockY()+";"+handle34.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".handle4.loc", handle44.getBlockX()+";"+handle44.getBlockY()+";"+handle44.getBlockZ());
								core.saveCustomYml(yc, Core.confFile);
								Location p14 = handle14.add(1,0,0);
								Location p24 = handle24.add(1,0,0);
								Location p34 = handle34.add(1,0,0);
								Location p44 = handle44.add(1,0,0);
								yc.set("Plates.Ram/"+X+Y+Z+".first", p14.getBlockX()+";"+p14.getBlockY()+";"+p14.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".second", p24.getBlockX()+";"+p24.getBlockY()+";"+p24.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".third", p34.getBlockX()+";"+p34.getBlockY()+";"+p34.getBlockZ());
								yc.set("Plates.Ram/"+X+Y+Z+".fourth", p44.getBlockX()+";"+p44.getBlockY()+";"+p44.getBlockZ());
								core.saveCustomYml(yc, Core.confFile);
								core.create("Ram/"+X+""+Y+""+Z, face);
								break;
							default:
								break;
							}
						}else if(args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("stop")){
							return false;
						}
					}
				}else{
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("Messages.Command.Deny.Command")));
					return false;
				}
			}else{
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("Messages.Command.Deny.Console")));
				return false;
			}
		}
		return false;
	}

}
