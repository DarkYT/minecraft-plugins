package com.acropolismc.play.sellstick;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.acropolismc.play.sellstick.Configs.StickConfig;

public class SellStickCommand implements CommandExecutor {

	private SellStick plugin;
	public static int index = 0;

	public SellStickCommand(SellStick plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(StickConfig.instance.prefix + ChatColor.GRAY + "" + ChatColor.ITALIC + "Sell Stick par Shmkane");
			if (sender.hasPermission("sellstick.give")) {
				sender.sendMessage(StickConfig.instance.prefix + ChatColor.GREEN + "/SellStick give <Joueur> <Nombre> <<uses>/infinite>");
			}
			return true;
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("sellstick.reload")) {
				plugin.getServer().getPluginManager().disablePlugin(plugin);
				sender.sendMessage(ChatColor.RED + "Plugin désactivé.");
				plugin.getServer().getPluginManager().enablePlugin(plugin);
				sender.sendMessage(ChatColor.GREEN + "Plugin activé.");
				return true;
			} else {
				sender.sendMessage(StickConfig.instance.prefix + ChatColor.GRAY + "" + ChatColor.ITALIC + "Sell Stick par Shmkane");
				return true;
			}
		} else if (args.length == 4) {
			if (args[0].equalsIgnoreCase("give")) {
				if (sender.hasPermission("sellstick.give")) {
					Player target = plugin.getServer().getPlayer(args[1]);
					if (target != null && target.isOnline()) {
						for (int i = 0; i < Integer.parseInt(args[2]); i++) {
							RandomString random = new RandomString(10);
							String UUID = random.nextString();

							ItemStack is = new ItemStack(Material.getMaterial(StickConfig.instance.item));
							ItemMeta im = is.getItemMeta();
							List<String> lores = new ArrayList<String>();

							im.setDisplayName(StickConfig.instance.name);

							for(int z = 0; z < StickConfig.instance.lore.size() ; z ++){
								lores.add(StickConfig.instance.lore.get(z).replace("&", "§"));
							}

							lores.add(1, "%usesLore%");

							if (args[3].equalsIgnoreCase("infinite") || args[3].equalsIgnoreCase("i")) {
								lores.set(1, lores.get(1).replace("%usesLore%", StickConfig.instance.infiniteLore));
							} else {
								int uses = Integer.parseInt(args[3]);
								lores.set(1, lores.get(1).replace("%usesLore%", StickConfig.instance.usesLore.replace("%remaining%", uses + "")));
							}

							lores.add(UUID);
							im.setLore(lores);
							is.setItemMeta(im);
							target.getInventory().addItem(is);
						}
						target.sendMessage(StickConfig.instance.prefix + "§6§l§oBâton §e§l§ode §c§l§oVente §6§l» §eVous §7venez de §crecevoir §e" + Integer.parseInt(args[2]) + "§6Bâton §ede §cVente §4§l! ");
						sender.sendMessage(StickConfig.instance.prefix + "Vous donnez " + Integer.parseInt(args[2]) + " Sell "+ ((Integer.parseInt(args[2]) > 1) ? "Sticks !" : "Stick !" + " à " + target.getName() + "."));

						return true;

					} else {
						sender.sendMessage(ChatColor.RED + "Joueur introuvable !");
					}
				} else {
					sender.sendMessage(StickConfig.instance.prefix + StickConfig.instance.noPerm);
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Commande invalide. Entrez /Sellstick pour de l'aide.");
		}
		return false;
	}
}