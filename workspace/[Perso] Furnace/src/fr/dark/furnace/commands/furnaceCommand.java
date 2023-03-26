package fr.dark.furnace.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.dark.furnace.Furnace;

public class furnaceCommand implements CommandExecutor {
	
	Furnace core;
	public furnaceCommand(Furnace core) {
		this.core = core;
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player s = (Player) sender;
			if(args.length == 0) {
				if(s.hasPermission("furnace.use")) {
					if(Furnace.canFurnace(s.getInventory().getItemInMainHand())) {
						ItemStack cookItem = s.getInventory().getItemInMainHand();
						if(core.getTotalExperience(s) < core.getTotalCost(cookItem)) {
							s.sendMessage("§cTu n'as pas assez d'expérience pour cuire ce que tu tiens !");
							return false;
						}
						Furnace.furnace(s, s.getInventory().getHeldItemSlot());
						return true;
					}else {
						s.sendMessage("§cL'item que tu tiens ne peut pas être cuit ! RT..");
						return false;
					}
				}
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("all")) {
					if(s.hasPermission("furnace.all")) {
						double totalCost = 0;
						for(ItemStack it : s.getInventory().getContents()) {
							if(it != null) {
								if(Furnace.canFurnace(it)) {
									totalCost += core.getTotalCost(it);
								}
							}
						}
						if(core.getTotalExperience(s) < totalCost) {
							s.sendMessage("§cTu n'as pas assez d'expérience pour cuire le contenue de ton inventaire !");
							return false;
						}
						for(int i = 0; i < 35; i++) {
							if(s.getInventory().getItem(i) != null) {
								if(Furnace.canFurnace(s.getInventory().getItem(i))) {
									Furnace.furnace(s, i);
								}
							}
						}
						return true;
					}
				}
			}
		}else {
			sender.sendMessage("§cT'es une console '-'");
		}
		return false;
	}

}
