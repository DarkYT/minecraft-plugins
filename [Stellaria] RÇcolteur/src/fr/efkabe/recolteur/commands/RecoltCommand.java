package fr.efkabe.recolteur.commands;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;

import fr.efkabe.recolteur.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class RecoltCommand implements CommandExecutor {

	Main main;
	public RecoltCommand(Main main){ this.main = main; }
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("recharge")){
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(args.length == 0){
					ItemStack rubis = new ItemStack(Material.FIREWORK_CHARGE, 1);
					ItemMeta meta = rubis.getItemMeta();
					FireworkEffectMeta metaFw = (FireworkEffectMeta) meta;
					FireworkEffect effect = FireworkEffect.builder().withColor(Color.RED).build();
					metaFw.setEffect(effect);
					meta.setDisplayName("§4§lRubis");
					rubis.setItemMeta(metaFw);
					
					if(p.getInventory().containsAtLeast(rubis, 1)){
						if(main.getConfig().getConfigurationSection("Recolteurs").contains(p.getUniqueId().toString())){
							int currentDurability = main.getConfig().getInt("Recolteurs." + p.getUniqueId() + ".durability");
							main.getConfig().set("Recolteurs." + p.getUniqueId() + ".durability", (currentDurability + 1500));
							main.saveConfig();
							p.getInventory().removeItem(rubis);
							p.updateInventory();
							p.sendMessage("§aVous venez d'ajouter 1500 durabilités à votre Récolteur !");
							return true;
						}
					}else{
						p.sendMessage("§cVous n'avez pas assez d'argent pour recharger votre Récolteur ! (Coût : 1 Rubis pour 1500 utilisations)");
						return true;
					}
				}else if(args.length == 1 && args[0].equalsIgnoreCase("level")){
					p.sendMessage("§eIl vous reste §6" + main.getConfig().getInt("Recolteurs." + p.getUniqueId() + ".durability") + "§e utilisations !");
					return true;
				}
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("rgive")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.isOp()){
					if(args.length == 0){
						p.sendMessage("§cTu dois indiquer le Récolteur que tu veux ! (expert, master, elite)");
						return true;
					}
					if(args.length == 1){
						String type = args[0].toLowerCase();
						if(main.isEmptyConfig()){
							main.getConfig().createSection("Recolteurs." + p.getUniqueId());
							main.saveConfig();
						}
						if(!main.getConfig().getConfigurationSection("Recolteurs").contains(p.getUniqueId().toString())){
							main.getConfig().createSection("Recolteurs." + p.getUniqueId());
							main.saveConfig();
						}
						main.getConfig().set("Recolteurs." + p.getUniqueId() + ".durability", 0);
						main.getConfig().set("Recolteurs." + p.getUniqueId() + ".level", type);
						main.saveConfig();
						main.giveRecolt(p, type);
						return true;
					}
				}
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("lost")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.isOp()){
					if(args.length == 0){
						p.sendMessage("§cMauvais usage ! Veuillez indiquer le nom du joueur à vérifier");
						return true;
					}
					if(args.length == 1){
						Player target = Bukkit.getPlayer(args[0]);
						if(target == null){
							p.sendMessage("§cLe joueur n'existe pas ! Réessayez !");
							return true;
						}else{
							p.sendMessage("§eVoulez vous vérifier l'achat d'un Récolteur de la part de §6" + target.getName() + "§e ?");
							TextComponent message = new TextComponent("[Oui] ");
							message.setColor(ChatColor.GREEN);
							message.setBold(true);
							message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/lost " + target.getName() + " verify"));
							TextComponent no = new TextComponent( "[Non]" );
							no.setColor( ChatColor.RED );
							no.setBold( true );
							no.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/lost " + target.getName() + " stop" ) );
							message.addExtra(no);
							p.spigot().sendMessage(message);
						}
					}
					if(args.length == 2){
						Player target = Bukkit.getPlayer(args[0]);
						if(args[1].equalsIgnoreCase("verify")){
							if(!main.getConfig().getConfigurationSection("Recolteurs").contains(target.getUniqueId().toString())){
								p.sendMessage("§eLe joueur §6" + target.getName() + "§e n'a jamais acheté de Récolteur !");
								return true;
							}
							for(String uuids : main.getConfig().getConfigurationSection("Recolteurs").getKeys(false)){
								if(uuids.equals(target.getUniqueId().toString())){
									String type = main.getConfig().getString("Recolteurs." + target.getUniqueId() + ".level");
									p.sendMessage("§eLe joueur §6" + target.getName() + "§e a bien acheté un Récolteur de type: §6" + type);
									p.sendMessage("§eVoulez-vous lui redonner son Récolteur ?");
									TextComponent message = new TextComponent("[Oui] ");
									message.setColor(ChatColor.GREEN);
									message.setBold(true);
									message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/lost " + target.getName() + " give"));
									TextComponent no = new TextComponent( "[Non]" );
									no.setColor( ChatColor.RED );
									no.setBold( true );
									no.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/lost " + target.getName() + " nogive" ) );
									message.addExtra(no);
									p.spigot().sendMessage(message);
									return true;
								}
							}
						}else if(args[1].equalsIgnoreCase("stop")){
							p.sendMessage("§f[§c!§f] §cVous avez annulé la vérification !");
							return true;
						}else if(args[1].equalsIgnoreCase("give")){
							for(String uuids : main.getConfig().getConfigurationSection("Recolteurs").getKeys(false)){
								if(uuids.equals(target.getUniqueId().toString())){
									String type = main.getConfig().getString("Recolteurs." + target.getUniqueId() + ".level");
									main.giveRecolt(target, type);
									p.sendMessage("§aVous avez bien rendu le Récolteur de type " + type + " à " + target.getName());
									target.sendMessage("§a" + p.getName() + " vous a rendu votre Récolteur !");
									return true;
								}
							}
						}else if(args[1].equalsIgnoreCase("nogive")){
							p.sendMessage("§f[§c!§f] §cVous avez annulé le give du Récolteur !");
							return true;
						}
					}
				}else{
					p.sendMessage("§cTu n'as pas accès à cette commande !");
					return true;
				}
			}
		}
		return false;
	}

}
