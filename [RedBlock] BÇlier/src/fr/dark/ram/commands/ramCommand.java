package fr.dark.ram.commands;

import org.bukkit.Location;
import org.bukkit.Material;
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
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
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
							message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ram set ok"));
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
						if(args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("ok")){
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
								Location body1 = p.getLocation().add(0,-1,1);
								Location body2 = p.getLocation().add(0,-1,2);
								Location body3 = p.getLocation().add(0,-1,3);
								Location tail = p.getLocation().add(0,-1,4);
								yc.set("Rams.Ram/"+X+Y+Z+".body1.loc", body1.getBlockX()+";"+body1.getBlockY()+";"+body1.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body1.bloc", body1.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.loc", body2.getBlockX()+";"+body2.getBlockY()+";"+body2.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.bloc", body2.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.loc", body3.getBlockX()+";"+body3.getBlockY()+";"+body3.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.bloc", body3.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.loc", tail.getBlockX()+";"+tail.getBlockY()+";"+tail.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.bloc", tail.getBlock().getType().toString());
								core.saveCustomYml(yc, Core.confFile);
								p.getLocation().add(1,0,1).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(1,0,4).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(-1,0,1).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(-1,0,4).getBlock().setType(Material.STONE_PLATE);
								yc.set("Plates.Ram/"+X+Y+Z+".first", (X+1)+";"+(Y+1)+";"+(Z+1));
								yc.set("Plates.Ram/"+X+Y+Z+".second", (X+1)+";"+(Y+1)+";"+(Z+4));
								yc.set("Plates.Ram/"+X+Y+Z+".third", (X-1)+";"+(Y+1)+";"+(Z+1));
								yc.set("Plates.Ram/"+X+Y+Z+".fourth", (X-1)+";"+(Y+1)+";"+(Z+4));
								core.saveCustomYml(yc, Core.confFile);
								break;
							case SOUTH:
								Location body21 = p.getLocation().add(0,-1,-1);
								Location body22 = p.getLocation().add(0,-1,-2);
								Location body23 = p.getLocation().add(0,-1,-3);
								Location tail2 = p.getLocation().add(0,-1,-4);
								yc.set("Rams.Ram/"+X+Y+Z+".body1.loc", body21.getBlockX()+";"+body21.getBlockY()+";"+body21.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body1.bloc", body21.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.loc", body22.getBlockX()+";"+body22.getBlockY()+";"+body22.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.bloc", body22.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.loc", body23.getBlockX()+";"+body23.getBlockY()+";"+body23.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.bloc", body23.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.loc", tail2.getBlockX()+";"+tail2.getBlockY()+";"+tail2.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.bloc", tail2.getBlock().getType().toString());
								core.saveCustomYml(yc, Core.confFile);
								p.getLocation().add(1,0,-1).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(1,0,-4).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(-1,0,-1).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(-1,0,-4).getBlock().setType(Material.STONE_PLATE);
								yc.set("Plates.Ram/"+X+Y+Z+".first", (X+1)+";"+(Y+1)+";"+(Z+1));
								yc.set("Plates.Ram/"+X+Y+Z+".second", (X+1)+";"+(Y+1)+";"+(Z+4));
								yc.set("Plates.Ram/"+X+Y+Z+".third", (X-1)+";"+(Y+1)+";"+(Z+1));
								yc.set("Plates.Ram/"+X+Y+Z+".fourth", (X-1)+";"+(Y+1)+";"+(Z+4));
								core.saveCustomYml(yc, Core.confFile);
								break;
							case EAST:
								Location body41 = p.getLocation().add(-1,-1,0);
								Location body42 = p.getLocation().add(-2,-1,0);
								Location body43 = p.getLocation().add(-3,-1,0);
								Location tail4 = p.getLocation().add(-4,-1,0);
								yc.set("Rams.Ram/"+X+Y+Z+".body1.loc", body41.getBlockX()+";"+body41.getBlockY()+";"+body41.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body1.bloc", body41.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.loc", body42.getBlockX()+";"+body42.getBlockY()+";"+body42.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.bloc", body42.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.loc", body43.getBlockX()+";"+body43.getBlockY()+";"+body43.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.bloc", body43.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.loc", tail4.getBlockX()+";"+tail4.getBlockY()+";"+tail4.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.bloc", tail4.getBlock().getType().toString());
								core.saveCustomYml(yc, Core.confFile);
								p.getLocation().add(-1,0,-1).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(-4,0,-1).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(-1,0,1).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(-4,0,1).getBlock().setType(Material.STONE_PLATE);
								yc.set("Plates.Ram/"+X+Y+Z+".first", (X-1)+";"+(Y+1)+";"+(Z-1));
								yc.set("Plates.Ram/"+X+Y+Z+".second", (X-4)+";"+(Y+1)+";"+(Z-1));
								yc.set("Plates.Ram/"+X+Y+Z+".third", (X-1)+";"+(Y+1)+";"+(Z+1));
								yc.set("Plates.Ram/"+X+Y+Z+".fourth", (X-4)+";"+(Y+1)+";"+(Z+1));
								core.saveCustomYml(yc, Core.confFile);
								break;
							case WEST:
								Location body31 = p.getLocation().add(1,-1,0);
								Location body32 = p.getLocation().add(2,-1,0);
								Location body33 = p.getLocation().add(3,-1,0);
								Location tail3 = p.getLocation().add(4,-1,0);
								yc.set("Rams.Ram/"+X+Y+Z+".body1.loc", body31.getBlockX()+";"+body31.getBlockY()+";"+body31.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body1.bloc", body31.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.loc", body32.getBlockX()+";"+body32.getBlockY()+";"+body32.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body2.bloc", body32.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.loc", body33.getBlockX()+";"+body33.getBlockY()+";"+body33.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".body3.bloc", body33.getBlock().getType().toString());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.loc", tail3.getBlockX()+";"+tail3.getBlockY()+";"+tail3.getBlockZ());
								yc.set("Rams.Ram/"+X+Y+Z+".tail.bloc", tail3.getBlock().getType().toString());
								core.saveCustomYml(yc, Core.confFile);
								p.getLocation().add(1,0,-1).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(4,0,-1).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(1,0,1).getBlock().setType(Material.STONE_PLATE);
								p.getLocation().add(4,0,1).getBlock().setType(Material.STONE_PLATE);
								yc.set("Plates.Ram/"+X+Y+Z+".first", (X+1)+";"+(Y+1)+";"+(Z+1));
								yc.set("Plates.Ram/"+X+Y+Z+".second", (X+4)+";"+(Y+1)+";"+(Z+1));
								yc.set("Plates.Ram/"+X+Y+Z+".third", (X+1)+";"+(Y+1)+";"+(Z-1));
								yc.set("Plates.Ram/"+X+Y+Z+".fourth", (X+4)+";"+(Y+1)+";"+(Z-1));
								core.saveCustomYml(yc, Core.confFile);
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
