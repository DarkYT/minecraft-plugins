package fr.dark.catapulte.commands;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.dark.catapulte.Core;

public class mainCommand implements CommandExecutor {

	Core core;
	public mainCommand(Core core) {this.core = core;}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("catapulte")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.isOp()){
					if(args.length == 2){
						if((args[1].equalsIgnoreCase("north")) || (args[1].equalsIgnoreCase("south")) || (args[1].equalsIgnoreCase("east")) || (args[1].equalsIgnoreCase("west"))){
							
							ItemStack it = new ItemStack(Material.STONE_PLATE);
							ItemMeta itm = it.getItemMeta();
							itm.setDisplayName("§8Catapulte");
							itm.setLore(Arrays.asList(args[1]));
							it.setItemMeta(itm);
							p.getInventory().addItem(it);
						}else{
							p.sendMessage("§4Wrong Direction");
						}
					}else{
						System.out.println(p.getEyeLocation().getDirection().normalize());
					}
				}
			}
		}
		return false;
	}

}
