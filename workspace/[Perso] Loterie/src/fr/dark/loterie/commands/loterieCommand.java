package fr.dark.loterie.commands;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.dark.loterie.Loterie;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class loterieCommand implements CommandExecutor {

	Loterie core;
	public loterieCommand(Loterie core) {
		this.core = core;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
				if(sender.isOp()) {
					core.reloadConfig();
					sender.sendMessage("§a[§2Loterie§a] §2La config a bien été reload");
					return true;
				}
			}
		}
		if(sender instanceof Player) {
			Player s = (Player) sender;
			if(args.length == 0) {
				s.sendMessage("§6Etes-vous sûr de vouloir acheter un ticket pour la loterie ?");
				TextComponent message = new TextComponent("[Oui] ");
				message.setColor(ChatColor.GREEN);
				message.setBold(true);
				message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/lt validate"));
				TextComponent no = new TextComponent( "[Non]" );
				no.setColor( ChatColor.RED );
				no.setBold( true );
				no.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/lt cancel") );
				message.addExtra(no);
				s.spigot().sendMessage(message);
				return true;
			}
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("validate")) {
					int price = core.getConfig().getInt("Loterie.TicketPrice");
					ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT);
					ItemMeta topazem = topaze.getItemMeta();
					topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
					topazem.addEnchant(Enchantment.DURABILITY, 1, true);
					topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
					topaze.setItemMeta(topazem);
					
					ItemStack topazeP = new ItemStack(Material.DOUBLE_PLANT, price);
					ItemMeta topazePm = topazeP.getItemMeta();
					topazePm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
					topazePm.addEnchant(Enchantment.DURABILITY, 1, true);
					topazePm.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
					topazeP.setItemMeta(topazePm);
					if(s.getInventory().containsAtLeast(topaze, price)) {
						int i = 0;
						int max = core.getConfig().getInt("Loterie.TicketPerPlayer");
						YamlConfiguration yc = YamlConfiguration.loadConfiguration(Loterie.confFile);
						for(String st : yc.getConfigurationSection("Players").getKeys(false)) {
							String uuid = yc.getString("Players."+st);
							if(uuid.equals(s.getUniqueId().toString())) {
								if(i<max) {
									i++;
									continue;
								}else {
									s.sendMessage("§cVous avez acheté le maximum de ticket pour cette loterie !");
									return false;
								}
							}
						}
						s.getInventory().removeItem(topazeP);
						int length = yc.getConfigurationSection("Players").getKeys(false).size();
						yc.set("Players."+length, s.getUniqueId().toString());
						core.saveCustomYml(yc, Loterie.confFile);
						return true;
					}else {
						s.sendMessage("§cVous n'avez pas assez de topazes dans votre inventaire");
						return false;
					}
				}else if(args[0].equalsIgnoreCase("cancel")) {
					s.sendMessage("§aVous venez d'annuler l'achat !");
					return false;
				}else if(args[0].equalsIgnoreCase("help")){
					s.sendMessage("§6<<§m-----§r§9 Loterie §6§m-----§r§6>>");
					s.sendMessage("§6/lt §eVous permet d'acheter un ticket");
					s.sendMessage("§6/lt help §eVous affiche ce message");
					s.sendMessage("§6<<§m-----§r§9 Loterie §6§m-----§r§6>>");
					return true;
				}else if(args[0].equalsIgnoreCase("launch")) {
					if(s.isOp()) {
						Loterie.lotteryDraw("§aTirage de la loterie en cours ...");
						return true;
					}else {
						return false;
					}
				}else if(args[0].equalsIgnoreCase("give")){
					ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT, 64*15);
					ItemMeta topazem = topaze.getItemMeta();
					topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
					topazem.addEnchant(Enchantment.DURABILITY, 1, true);
					topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
					topaze.setItemMeta(topazem);
					s.getInventory().addItem(topaze);
				}
			}
		}else {
			sender.sendMessage("§cT'es une console, sale nooby --'");
		}
		return false;
	}

}
