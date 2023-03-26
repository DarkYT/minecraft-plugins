package fr.dark.ctf.commands;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.dark.ctf.CTF;
import fr.dark.ctf.enums.Doors;
import fr.dark.ctf.enums.GameState;

public class editCommand implements CommandExecutor {

	CTF ctf;
	public editCommand(CTF ctf) {this.ctf = ctf;}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ctfedit")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.isOp()) {
					if(ctf.isEditor(p)) {
						if(!(ctf.isState(GameState.PLAYING))) {
							if(args.length == 0 || args.length == 1) {
								p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.wrongUsage.General")));
								return false;
							}
							if(args.length == 2) {
								String machine = args[0];
								String face = args[1];
								if(machine.equalsIgnoreCase("porte")) {
									p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.wrongUsage.Door")));
									return false;
								}else if(machine.equalsIgnoreCase("belier")) {
									if(face.equalsIgnoreCase("north") || face.equalsIgnoreCase("south") || face.equalsIgnoreCase("east") || face.equalsIgnoreCase("west")) {
										ItemStack it = new ItemStack(Material.STONE_PLATE);
										ItemMeta itm = it.getItemMeta();
										itm.setDisplayName(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.itemName.Belier")));
										itm.setLore(Arrays.asList(args[1]));
										it.setItemMeta(itm);
										p.getInventory().addItem(it);
										return true;
									}else {
										p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.wrongUsage.wrongDirection")));
										return false;
									}
								}else if(machine.equalsIgnoreCase("catapulte")) {
									if(face.equalsIgnoreCase("north") || face.equalsIgnoreCase("south") || face.equalsIgnoreCase("east") || face.equalsIgnoreCase("west")) {
										ItemStack it = new ItemStack(Material.STONE_PLATE);
										ItemMeta itm = it.getItemMeta();
										itm.setDisplayName(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.itemName.Catapulte")));
										itm.setLore(Arrays.asList(args[1]));
										it.setItemMeta(itm);
										p.getInventory().addItem(it);
										return true;
									}else {
										p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.wrongUsage.wrongDirection")));
										return false;
									}
								}else if(machine.equalsIgnoreCase("baliste")){
									if(face.equalsIgnoreCase("north") || face.equalsIgnoreCase("south") || face.equalsIgnoreCase("east") || face.equalsIgnoreCase("west")) {
										ItemStack it = new ItemStack(Material.STONE_PLATE);
										ItemMeta itm = it.getItemMeta();
										itm.setDisplayName(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.itemName.Baliste")));
										itm.setLore(Arrays.asList(args[1]));
										it.setItemMeta(itm);
										p.getInventory().addItem(it);
										return true;
									}else {
										p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.wrongUsage.wrongDirection")));
										return false;
									}
								}else {
									p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.wrongUsage.wrongMachine")));
									return false;
								}
							}
							
							if(args.length == 3) {
								p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.wrongUsage.Door")));
								return false;
							}
							
							if(args.length == 4) {
								if(ctf.utils.isNumeric(args[1])) {
									if(ctf.utils.isNumeric(args[2])) {
										int l = Integer.valueOf(args[1]);
										int h = Integer.valueOf(args[2]);
										if (args[3].equalsIgnoreCase("herse")) {
											String id = ctf.utils.getID();
											ItemStack levier = new ItemStack(Material.LEVER, 1);
											ItemMeta lM = levier.getItemMeta();
											lM.setDisplayName(ctf.utils.colorMessage("&8" + id));
											lM.setLore(Arrays.asList(ctf.utils.colorMessage("&7&nLargeur :"), ctf.utils.colorMessage("&7" + l), ctf.utils.colorMessage("&7&nHauteur :"), ctf.utils.colorMessage("&7" + h)));
											levier.setItemMeta(lM);
											p.getInventory().addItem(levier);
											p.sendMessage(ctf.utils.colorMessage("&7Posez ce levier pour créer une herse de taille &6" + l + "&7x&6" + h + "&7."));
											ctf.type.put(p.getName(), Doors.Herse);
										} else if (args[3].equalsIgnoreCase("levis")) {
											String id = ctf.utils.getID();
											ItemStack levier = new ItemStack(Material.LEVER, 1);
											ItemMeta lM = levier.getItemMeta();
											lM.setDisplayName(ctf.utils.colorMessage("&8" + id));
											lM.setLore(Arrays.asList(ctf.utils.colorMessage("&7&nLargeur :"), ctf.utils.colorMessage("&7" + l), ctf.utils.colorMessage("&7&nHauteur :"), ctf.utils.colorMessage("&7" + h)));
											levier.setItemMeta(lM);
											p.getInventory().addItem(levier);
											p.sendMessage(ctf.utils.colorMessage("&7Posez ce levier pour créer un pont-levis de taille &6" + l + "&7x&6" + h + "&7."));
											ctf.type.put(p.getName(), Doors.Levis);
										} else {
											p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.wrongUsage.wrongModele")));
											return false;
										}
									}else {
										p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.wrongUsage.Door")));
										return false;
									}
								}else {
									p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.wrongUsage.Door")));
									return false;
								}
							}
						}else {
							p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.isPlaying")));
							return false;
						}
					}else {
						p.sendMessage(ctf.utils.colorMessage(ctf.getConfig().getString("CTF.Editor.isNotEditor")));
						return false;
					}
				}
			}
		}
		return false;
	}

}
