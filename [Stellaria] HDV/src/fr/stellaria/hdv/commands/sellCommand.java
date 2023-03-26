package fr.stellaria.hdv.commands;

import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.stellaria.hdv.Core;

public class sellCommand implements CommandExecutor {

	Core core;
	public sellCommand(Core core) { this.core = core; }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("hdv")){
			if(!(sender instanceof Player)){
				sender.sendMessage("§cT'es une console ! Noob '-'");
				return true;
			}
			Player p = (Player) sender;
			if(args.length == 1 && !args[0].equalsIgnoreCase("help") && !args[0].equalsIgnoreCase("rewards") && !args[0].equalsIgnoreCase("spawn") && !args[0].equalsIgnoreCase("list") && !args[0].equalsIgnoreCase("remove")){
				p.sendMessage("§cPour mettre correctement ton article en vente, regarde le /sell help !");
				return true;
			}
			if(args.length == 0){
				// core.openSells(p, 0);
				core.opSell(p);
			}
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("help")){
					p.sendMessage("§6<<§m-----§r§9 HDV §6§m-----§r§6>>");
					p.sendMessage("§6/hdv sell <quantité (15, 10, 20...)> <monnaie (péridots, saphirs, rubis)> §eMet l'item qui est dans votre main en vente dans l'Hôtel des Ventes");
					p.sendMessage("§6/hdv rewards §eVérifie si vous avez vendu un item ; si oui, vous donne ce que vous avez gagné");
					p.sendMessage("§6/hdv spawn §eFait spawn le villageois de l'Hôtel des Ventes");
					p.sendMessage("§6/hdv list §eAffiche tous les items que vous avez mis en vente");
					p.sendMessage("§6/hdv remove <Item> §eSupprime l'item de l'Hôtel des Ventes, pour plus de précisions sur l'utilisation, faire /hdv list");
					p.sendMessage("§6/hdv help §eVous ouvre ce menu");
					p.sendMessage("§6<<§m-----§r§9 HDV §6§m-----§r§6>>");
					return true;
				}
				if(args[0].equalsIgnoreCase("list")){
					p.sendMessage("§eVoici vos items en vente :");
					for(String items : core.getConfig().getConfigurationSection("Sells." + p.getUniqueId()).getKeys(false)){
						ItemStack it = core.getConfig().getItemStack("Sells." + p.getUniqueId() + "." + items + ".item");
						p.sendMessage("§e" + it.getType());
						p.sendMessage("  §e-Prix: §6" + core.getConfig().getInt("Sells." + p.getUniqueId() + "." + items + ".price"));
						p.sendMessage("  §e-Monnaie: §6" + core.getConfig().getString("Sells." + p.getUniqueId() + "." + items + ".money"));
						p.sendMessage("  §e-Identifiant: §6" + core.getConfig().getInt("Sells." + p.getUniqueId() + "." + items + ".hastag"));
					}
					p.sendMessage("§cPour supprimer un item, veuillez inndiquer son type et son identifiant dans la commande /hdv remove (ex: /hdv remove IRON_LEGGINGS1154)");
					return true;
				}
				if(args[0].equalsIgnoreCase("rewards")){
					YamlConfiguration yc = YamlConfiguration.loadConfiguration(Core.confFile);
					if(yc.getConfigurationSection("Rewards").contains(p.getName())){
						for(String item : yc.getConfigurationSection("Rewards." + p.getName()).getKeys(false)){
							String money = yc.getString("Rewards." + p.getName() + "." + item + ".money");
							int price = yc.getInt("Rewards." + p.getName() + "." + item + ".price");
							core.getMoney(money, price, p);
							p.sendMessage("§aVous venez de recevoir " + price + " " + money);
							yc.set("Rewards." + p.getName() + "." + item, null);
							try {
								yc.save(Core.confFile);
						    } catch (IOException e1) {
						    	e1.printStackTrace();
						    }
						}
						if(yc.getConfigurationSection("Rewards." + p.getName()).getKeys(false).isEmpty()){
							yc.set("Rewards." + p.getName(), null);
							try {
								yc.save(Core.confFile);
						    } catch (IOException e1) {
						    	e1.printStackTrace();
						    }
						}
						return true;
					}else{
						p.sendMessage("§cPersonne ne veut de ce que tu vends... RT '-'");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("spawn")){
					if(p.isOp()){
						Location target = p.getTargetBlock((Set) null, 200).getLocation().clone().add(0, 1, 0);
						core.spawnVillager("§8Hôtel des Ventes", target);
						p.sendMessage("§aVous venez de faire spawn le villageois de l'HDV !");
						return true;
					}else{
						p.sendMessage("§cTu n'as pas le droit d'effectuer cette commande !");
					}
				}
			}
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("remove")){
					String delItem = args[1].toUpperCase();
					if(core.getConfig().getConfigurationSection("Sells." + p.getUniqueId()).contains(delItem)){
						for(String items : core.getConfig().getConfigurationSection("Sells." + p.getUniqueId()).getKeys(false)){
							if(items.equals(delItem)){
								core.getConfig().set("Sells." + p.getUniqueId() + "." + items, null);
								core.saveConfig();
								p.sendMessage("§aVotre item a bien été supprimé !");
								break;
							}
						}
						return true;
					}else{
						p.sendMessage("§cVous ne vendez pas cet item !");
						return true;
					}
				}
			}
			if(args.length == 3){
				if(args[2].equalsIgnoreCase("péridot")){
					p.sendMessage("§cLa monnaie à utiliser est: péridots");
					return true;
				}
				if(args[2].equalsIgnoreCase("saphir")){
					p.sendMessage("§cLa monnaie à utiliser est: saphirs");
					return true;
				}
				if(args[0].equalsIgnoreCase("sell")){
					if(p.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
						p.sendMessage("§cNe met pas en vente du vide !");
						return true;
					}
					int price = Integer.valueOf(args[1]);
					String money = args[2];
					ItemStack sellItem = p.getInventory().getItemInMainHand();
					int random = (int) (Math.random() * 5000 + 1);
					core.getConfig().set("Sells." + p.getUniqueId() + "." + sellItem.getType().toString() + random + ".seller", p.getName());
					core.getConfig().set("Sells." + p.getUniqueId() + "." + sellItem.getType().toString() + random + ".price", price);
					core.getConfig().set("Sells." + p.getUniqueId() + "." + sellItem.getType().toString() + random + ".money", money);
					core.getConfig().set("Sells." + p.getUniqueId() + "." + sellItem.getType().toString() + random + ".hastag", random);
					core.getConfig().set("Sells." + p.getUniqueId() + "." + sellItem.getType().toString() + random + ".item", sellItem);
					core.saveConfig();
					p.getInventory().setItemInMainHand(null);
					p.updateInventory();
					p.sendMessage("§aTu as bien mis en vente ton item !");
					for(Player pls : Bukkit.getOnlinePlayers()){
						if(core.watchs.containsKey(pls)){
							pls.closeInventory();
							pls.sendMessage("§aUn item a été ajouté à l'hôtel !");
							core.opSell(pls);
						}
					}
					return true;
				}
			}
		}
		return false;
	}

}
