package fr.dark.classes.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.dark.classes.Core;
import fr.dark.classes.mainclass.Vocations;

public class classCommand implements CommandExecutor {

	Core core;

	public classCommand(Core core) {
		this.core = core;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (cmd.getName().equalsIgnoreCase("class")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 1) {
					if (p.isOp()) {
						if (args[0].equalsIgnoreCase("reset")) {
							if (core.vocations.containsKey(p)) {
								Vocations v = core.vocations.get(p);
								v.delete();
								core.vocations.remove(p);
							}

						}
					}
				}
				Inventory classInv = Bukkit.createInventory(null, 18,
						ChatColor.translateAlternateColorCodes('&', "&8Choisissez votre classe"));
				classInv.setItem(0, core.createItem(Material.BOW, "§6Pyro§4Archer", Enchantment.ARROW_DAMAGE, 1,
						Arrays.asList(" §7Classe gratuite", " §7Il enflamme ses flèches, le pyro !!")));
				classInv.setItem(1,
						core.createItem(Material.STICK, "§7Lancier", null, 0, Arrays.asList(" §7Classe gratuite",
								" §7Il lance des javelots sur ses ennemis", "§7un lancier, quoi..")));
				classInv.setItem(2, core.createItem(Material.IRON_SWORD, "§8Homme d'Armes", null, 0,
						Arrays.asList(" §7Classe gratuite", " §7Pour défendre ses coéquipiers, iléla")));
				classInv.setItem(3, core.createItem(Material.BOW, "§4Archer", null, 0,
						Arrays.asList(" §7Classe gratuite", " §7Joue au sniper avec un arc '-'")));
				classInv.setItem(4, core.createItem(Material.POTION, "§7Skirmisher", null, 0,
						Arrays.asList(" §7Classe gratuite", " §7Un drogué de la potion !")));
				classInv.setItem(5,
						core.createItem(Material.SHIELD, "§7Sentinelle", null, 0, Arrays.asList(" §7Classe gratuite",
								" §7Se cache derrière son bouclier,", "§7mais rigole moins devant l'albalétrier")));
				classInv.setItem(6, core.createItem(Material.IRON_AXE, "§6Hallebardier", Enchantment.DURABILITY, 1,
						Arrays.asList(" §6Classe payante", "§7S'amuse avec sa hache")));
				classInv.setItem(7,
						core.createItem(Material.WOOD_SWORD, "§2Ranger", null, 0, Arrays.asList(" §6Classe payante",
								" §7Possède 3 arcs différents,", "§7mais ne sait jamais lequel prendre")));
				classInv.setItem(8, core.createItem(Material.IRON_SPADE, "§7MaceMan", null, 0,
						Arrays.asList(" §6Classe payante", " §7Bourrain de service")));
				classInv.setItem(9, core.createItem(Material.PAPER, "§aMédecin", null, 0,
						Arrays.asList(" §6Classe payante", " §7Possède le coeur sur la main,", "§7littéralement..")));
				classInv.setItem(10, core.createItem(Material.IRON_AXE, "§8Exécuteur", null, 0,
						Arrays.asList(" §6Classe payante", " §7'Spèce de fou malade")));
				classInv.setItem(11, core.createItem(Material.APPLE, "§4Brute", null, 0,
						Arrays.asList(" §6Classe payante", " §7Cet étrange personnage adore", "§7taper les gens..")));
				classInv.setItem(12,
						core.createItem(Material.STONE_PLATE, "§7Ingénieur", null, 0, Arrays.asList(" §6Classe payante",
								" §7Petit génie des machines, avec", "§7lui tout va plus vite !")));
				classInv.setItem(13,
						core.createItem(Material.ARROW, "§4Arbalétrier", Enchantment.ARROW_DAMAGE, 1, Arrays.asList(
								" §6Classe payante", " §7Aussi rapide qu'efficace.. un", "§7nouveau Arrow quoi !")));
				classInv.setItem(14, core.createItem(Material.SADDLE, "§7Cavalier", null, 0,
						Arrays.asList(" §6Classe payante", " §7Avec son fidèle tagada, il tue..")));
				classInv.setItem(15, core.createItem(Material.BONE, "§7Chien de Guerre", null, 0,
						Arrays.asList(" §6Classe payante", " §7Petit toutou gentil, mais", "§7trèès dangereux !")));
				classInv.setItem(16, core.createItem(Material.FLINT, "§7Spadassin", null, 0,
						Arrays.asList(" §6Classe payante", " §7J'appelle Le lanceur de couteaux !")));
				p.openInventory(classInv);
			}
		}
		return false;
	}

}
