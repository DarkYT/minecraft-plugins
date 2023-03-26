package fr.dark.hdv.commands;

import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.dark.hdv.HDV;
import fr.dark.hdv.utils.CardboardBox;

public class menuCommand implements CommandExecutor {

	HDV core;
	public menuCommand(HDV hdv) {this.core = hdv;}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				core.open(p);
				return true;
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help")) {
					p.sendMessage("§6<<§m-----§r§9 Hôtel des Ventes §6§m-----§r§6>>");
					p.sendMessage("§6/hdv §eVous ouvre l'Hôtel des Ventes");
					p.sendMessage("§6/hdv help §eVous affiche ce message");
					p.sendMessage("§6/hdv recup §eVous permet de récupérer vos items expirés");
					p.sendMessage("§6/hdv sell <prix (1,2,3000..)> <monnaie (a,t,p)> §eMet en vente l'item que vous tenez");
					p.sendMessage("§6/hdv rewards §eVous donne, s'il y en a, les gains de vos ventes");
					p.sendMessage("§6<<§m-----§r§9 Hôtel des Ventes §6§m-----§r§6>>");
				}else if(args[0].equalsIgnoreCase("rewards")) {
					YamlConfiguration yc = YamlConfiguration.loadConfiguration(HDV.confFile);
					int i = 0;
					for(String s : yc.getConfigurationSection("Sells").getKeys(false)) {
						if(yc.getString("Sells."+s+".Vendeur").equals(p.getUniqueId().toString())) {
							int price = yc.getInt("Sells."+s+".Prix");
							String money = yc.getString("Sells."+s+".Monnaie");
							if(price == 1) {
								p.sendMessage("§aVous avez effectué une vente, pour un total d'un "+money);
							}else {
								p.sendMessage("§aVous avez effectué une vente, pour un total de "+price+" "+money+"s");
							}
							core.give(p, money, price);
							yc.set("Sells."+s, null);
							core.saveCustomYml(yc, HDV.confFile);
							i++;
						}
					}
					if(i == 0) {
						p.sendMessage("§cPersonne ne veut de ce que tu vends.. RT '-'");
					}
				}else if(args[0].equalsIgnoreCase("recup")) {
					int i = 0;
					for(Entry<UUID, ItemStack> entry : HDV.getInstance().items.entrySet()) {
						if(entry.getKey().toString().equals(p.getUniqueId().toString())) {
							p.getInventory().addItem(entry.getValue());
							HDV.getInstance().items.remove(entry.getKey(), entry.getValue());
							i++;
							p.sendMessage("§aVous venez de récupérer votre item");
						}
					}
					if(i == 0){
						p.sendMessage("§cTu n'as aucun item à récupérer !");
					}
				}
			}
			if(args.length > 1) {
				if(args.length == 2 || args.length > 3) {
					p.sendMessage("§6<<§m-----§r§9 Hôtel des Ventes §6§m-----§r§6>>");
					p.sendMessage("§6/hdv §eVous ouvre l'Hôtel des Ventes");
					p.sendMessage("§6/hdv help §eVous affiche ce message");
					p.sendMessage("§6/hdv sell <prix (1,2,3000..)> <monnaie (a,t,p)> §eMet en vente l'item que vous tenez");
					p.sendMessage("§6/hdv rewards §eVous donne, s'il y en a, les gains de vos ventes");
					p.sendMessage("§6<<§m-----§r§9 Hôtel des Ventes §6§m-----§r§6>>");
				}
				if(args.length == 3) {
					if(core.isNumeric(args[1])) {
						int price = Integer.valueOf(args[1]);
						if(price < 1) {
							p.sendMessage("§cTu ne peux pas vendre un item avec un prix inférieur à 1 !");
							return false;
						}
						if(args[2].equalsIgnoreCase("a") || args[2].equalsIgnoreCase("t") || args[2].equalsIgnoreCase("p")) {
							if(p.getInventory().getItemInMainHand().getType() != Material.AIR) {
								String money = core.getMoney(args[2]);
								CardboardBox cb = new CardboardBox(p.getInventory().getItemInMainHand());
								YamlConfiguration yc = YamlConfiguration.loadConfiguration(HDV.confFile);
								Random r = new Random();
								int size = r.nextInt(5000);
								yc.set("Players."+size+".Vendeur", p.getUniqueId().toString());
								yc.set("Players."+size+".Item", cb.box());
								yc.set("Players."+size+".Prix", price);
								yc.set("Players."+size+".Monnaie", money);
								yc.set("Players."+size+".Date", System.currentTimeMillis());
								core.saveCustomYml(yc, HDV.confFile);
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), new ItemStack(Material.AIR));
								p.sendMessage("§aVous venez de mettre en vente votre item !");
								return true;
							}else {
								p.sendMessage("§cEssaye pas de vendre du vide ! Arnaqueur..");
								return false;
							}
						}else {
							p.sendMessage("§cTa monnaie n'est pas valide (a,t,p)");
							return false;
						}
					}
				}
			}
		}else {
			sender.sendMessage("§cT'es une console '-'..");
		}
		return false;
	}

}
