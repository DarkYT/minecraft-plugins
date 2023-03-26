package fr.earthsky.furnace.commands;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import fr.earthsky.furnace.Core;

public class FurnaceCommand implements CommandExecutor {

	Core core;
	public FurnaceCommand(Core core) {
		this.core = core;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("four")){
			Player p = (Player) sender;
			if(p.hasPermission("essentials.kits.corrompu")){
				if(p.getInventory().getItemInMainHand().getAmount() < 8){
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cT'as cru que tu allais nous avoir ! Il te faut 8 items minimum"));
				}
				if(p.getInventory().getItemInMainHand().getType().equals(Material.IRON_ORE) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.IRON_ORE, qt);
						ItemStack newIt = new ItemStack(Material.IRON_INGOT, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.PORK) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.PORK, qt);
						ItemStack newIt = new ItemStack(Material.GRILLED_PORK, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.RAW_BEEF) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.RAW_BEEF, qt);
						ItemStack newIt = new ItemStack(Material.COOKED_BEEF, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.RAW_CHICKEN) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.RAW_CHICKEN, qt);
						ItemStack newIt = new ItemStack(Material.COOKED_CHICKEN, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.POTATO_ITEM) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.POTATO_ITEM, qt);
						ItemStack newIt = new ItemStack(Material.BAKED_POTATO, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.RAW_FISH) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.RAW_FISH, qt);
						ItemStack newIt = new ItemStack(Material.COOKED_FISH, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.RABBIT) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.RABBIT, qt);
						ItemStack newIt = new ItemStack(Material.COOKED_RABBIT, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.MUTTON) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.MUTTON, qt);
						ItemStack newIt = new ItemStack(Material.COOKED_MUTTON, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.GOLD_ORE) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.GOLD_ORE, qt);
						ItemStack newIt = new ItemStack(Material.GOLD_INGOT, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.SAND) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.SAND, qt);
						ItemStack newIt = new ItemStack(Material.GLASS, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.COBBLESTONE) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.COBBLESTONE, qt);
						ItemStack newIt = new ItemStack(Material.STONE, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.CLAY_BALL) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.CLAY, qt);
						ItemStack newIt = new ItemStack(Material.CLAY_BRICK, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.NETHERRACK) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.NETHERRACK, qt);
						ItemStack newIt = new ItemStack(Material.NETHER_BRICK, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.CLAY) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.CLAY, qt);
						ItemStack newIt = new ItemStack(Material.HARD_CLAY, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.SMOOTH_BRICK) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.SMOOTH_BRICK, qt);
						ItemStack newIt = new ItemStack(Material.SMOOTH_BRICK, qt,(byte)2);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.LAPIS_ORE) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.LAPIS_ORE, qt);
						Dye lapisD = new Dye();
						lapisD.setColor(DyeColor.BLUE);
						ItemStack lapis = lapisD.toItemStack(qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(lapis);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.REDSTONE_ORE) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.REDSTONE_ORE, qt);
						ItemStack newIt = new ItemStack(Material.REDSTONE_WIRE, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.EMERALD_ORE) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.EMERALD_ORE, qt);
						ItemStack newIt = new ItemStack(Material.EMERALD, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.COAL_ORE) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.COAL_ORE, qt);
						ItemStack newIt = new ItemStack(Material.COAL, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.LOG) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.LOG, qt);
						ItemStack newIt = new ItemStack(Material.COAL, qt,(byte)1);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.CACTUS) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.CACTUS, qt);
						Dye cactusD = new Dye();
						cactusD.setColor(DyeColor.GREEN);
						ItemStack green = cactusD.toItemStack(qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(green);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else if(p.getInventory().getItemInMainHand().getType().equals(Material.CHORUS_FRUIT) && p.getInventory().getItemInMainHand().getAmount() >= 8){
					if(p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 1)){
						int qt = p.getInventory().getItemInMainHand().getAmount();
						int c = qt / 8;
						ItemStack it = new ItemStack(Material.CHORUS_FRUIT, qt);
						ItemStack newIt = new ItemStack(Material.CHORUS_FRUIT_POPPED, qt);
						ItemStack coal = new ItemStack(Material.COAL, c);
					    int nbXp = 2;
					    int currentxp = p.getTotalExperience();
					    p.setTotalExperience(0);
					    p.setLevel(0);
					    p.setExp(0);
					    p.giveExp(currentxp - nbXp);
						p.getInventory().removeItem(it);
						if(c >= 1){
							p.getInventory().removeItem(coal);
						}
						p.getInventory().addItem(newIt);
					}else{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIl te faut du charbon '-' ..."));
					}
				}else{
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cTu ne peux pas faire cuire ça !"));
				}
			}else{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cT'as pas le grade ! RT."));
			}
		}
			
		return false;
	}

}
