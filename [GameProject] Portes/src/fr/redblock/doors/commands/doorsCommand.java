package fr.redblock.doors.commands;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.redblock.doors.Core;
import fr.redblock.doors.Doors;
import fr.redblock.doors.utils.Utils;

public class doorsCommand implements CommandExecutor {

	Core core;
	Utils utils;
	
	public doorsCommand(Core core) {
		this.core = core;
		utils = new Utils(core);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.isOp()) {
				if (args.length == 3) {
					if (args[2].equalsIgnoreCase("herse")) {
						int l = Integer.valueOf(args[0]);
						int h = Integer.valueOf(args[1]);
						String id = utils.getID();
						ItemStack levier = new ItemStack(Material.LEVER, 1);
						ItemMeta lM = levier.getItemMeta();
						lM.setDisplayName(utils.colorMessage("&8" + id));
						lM.setLore(Arrays.asList(utils.colorMessage("&7&nLargeur :"), utils.colorMessage("&7" + l), utils.colorMessage("&7&nHauteur :"), utils.colorMessage("&7" + h)));
						levier.setItemMeta(lM);
						p.getInventory().addItem(levier);
						p.sendMessage(utils.colorMessage("&7Posez ce levier pour créer une herse de taille &6" + l + "&7x&6" + h + "&7."));
						core.type.put(p.getName(), Doors.Herse);
					} else if (args[2].equalsIgnoreCase("levis")) {
						int l = Integer.valueOf(args[0]);
						int h = Integer.valueOf(args[1]);
						String id = utils.getID();
						ItemStack levier = new ItemStack(Material.LEVER, 1);
						ItemMeta lM = levier.getItemMeta();
						lM.setDisplayName(utils.colorMessage("&8" + id));
						lM.setLore(Arrays.asList(utils.colorMessage("&7&nLargeur :"), utils.colorMessage("&7" + l), utils.colorMessage("&7&nHauteur :"), utils.colorMessage("&7" + h)));
						levier.setItemMeta(lM);
						p.getInventory().addItem(levier);
						p.sendMessage(utils.colorMessage("&7Posez ce levier pour créer un pont-levis de taille &6" + l + "&7x&6" + h + "&7."));
						core.type.put(p.getName(), Doors.Levis);
					} else {
						p.sendMessage(utils.colorMessage("&cVeuillez entrer un modèle de porte valide."));
						return false;
					}
				} else if (args.length != 3 || args[0].equalsIgnoreCase("help")) {
					p.sendMessage(utils.colorMessage("&6<<&m-----&r&9 RedDoors &6&m-----&r&6>>"));
					p.sendMessage(utils.colorMessage("&6/doors <longueur (1, 2, 3...)> <hauteur (1, 2, 3...)> <modèle> &ePermet de créer la porte"));
					p.sendMessage(utils.colorMessage("&6Différents modèles: Herse (herse); Pont-Levis (levis); Battante (battante)"));
					p.sendMessage(utils.colorMessage("&6/doors help &eVous ouvre ce menu"));
					p.sendMessage(utils.colorMessage("&6<<&m-----&r&9 RedDoors &6&m-----&r&6>>"));
				}
			} else {
				p.sendMessage(utils.colorMessage("&cVous n'avez pas la permission d'éxecuter cette commande."));
			}
		}
		return false;
	}

}
