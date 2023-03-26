package fr.earthsky.convert.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.earthsky.convert.Core;

public class ConvertCommand implements CommandExecutor {

	Core core;
	public ConvertCommand(Core core) {
		this.core = core;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player p = (Player) sender;
		
		Inventory inv = p.getInventory();
		
		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta ambrem = ambre.getItemMeta();
		ambrem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
		ambre.setItemMeta(ambrem);

		ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta topazem = topaze.getItemMeta();
		topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
		topazem.addEnchant(Enchantment.DURABILITY, 1, true);
		topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		topaze.setItemMeta(topazem);

		ItemStack platine = new ItemStack(Material.NETHER_STAR);
		ItemMeta platinem = platine.getItemMeta();
		platinem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
		platine.setItemMeta(platinem);
		
		if(cmd.getName().equalsIgnoreCase("convert")){
			if(args.length == 0 || args.length == 1){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLa commande est: /convert <ambre/topaze/platine> <ambre/topaze/platine>"));
			}
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("ambre") && args[1].equalsIgnoreCase("topaze") && inv.containsAtLeast(ambre, 9)){
					ItemStack[] inventory = p.getInventory().getContents();
                    
				    int cuantity = 0;
				    for(int i = 0; i < inventory.length; i++) {
				        if(inventory[i] != null){
				            if( inventory[i].getType().equals(Material.DOUBLE_PLANT) && inventory[i].getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"))){
				                int cant = inventory[i].getAmount();
				                int ambreCount = cuantity + cant;
				                
				                int quotient = ambreCount / 9;
				                int reste = ambreCount - (quotient * 9);
								ItemStack totalAmbre = new ItemStack(Material.DOUBLE_PLANT, ambreCount);
								ItemMeta totalAmbreM = totalAmbre.getItemMeta();
								totalAmbreM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
								totalAmbre.setItemMeta(totalAmbreM);
								
								ItemStack totalTopaze = new ItemStack(Material.DOUBLE_PLANT, quotient);
								ItemMeta totalTopazeM = totalTopaze.getItemMeta();
								totalTopazeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
								totalTopazeM.addEnchant(Enchantment.DURABILITY, 1, true);
								totalTopazeM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
								totalTopaze.setItemMeta(totalTopazeM);
								
								ItemStack rest = new ItemStack(Material.DOUBLE_PLANT, reste);
								ItemMeta restM = rest.getItemMeta();
								restM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
								rest.setItemMeta(restM);

								p.getInventory().removeItem(totalAmbre);
								p.getInventory().addItem(totalTopaze);
								if(reste != 0){
									p.getInventory().addItem(rest);
								}
				            }
				        }
				    }
				    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'échanger vos ambres en topazes !"));
				}else if(args[0].equalsIgnoreCase("topaze") && args[1].equalsIgnoreCase("ambre") && inv.containsAtLeast(topaze, 1)){
			          ItemStack[] inventory = p.getInventory().getContents();
	                    
					    int cuantity = 0;
					    for(int i = 0; i < inventory.length; i++) {
					        if(inventory[i] != null){
					            if( inventory[i].getType().equals(Material.DOUBLE_PLANT) && inventory[i].getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"))){
					                int cant = inventory[i].getAmount();
					                int topazeCount = cuantity + cant;

									int ambreTot = topazeCount * 9;
									ItemStack totalAmbre = new ItemStack(Material.DOUBLE_PLANT, ambreTot);
									ItemMeta totalAmbreM = totalAmbre.getItemMeta();
									totalAmbreM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lAmbre"));
									totalAmbre.setItemMeta(totalAmbreM);
									
									ItemStack totalTopaze = new ItemStack(Material.DOUBLE_PLANT, topazeCount);
									ItemMeta totalTopazeM = totalTopaze.getItemMeta();
									totalTopazeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
									totalTopazeM.addEnchant(Enchantment.DURABILITY, 1, true);
									totalTopazeM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
									totalTopaze.setItemMeta(totalTopazeM);

									p.getInventory().removeItem(totalTopaze);
									p.getInventory().addItem(totalAmbre);
					            }
					        }
					    }
			          p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'échanger vos topazes en ambres !"));
				}else if(args[0].equalsIgnoreCase("topaze") && args[1].equalsIgnoreCase("platine") && inv.containsAtLeast(topaze, 9)){
			          ItemStack[] inventory = p.getInventory().getContents();
	                    
					    int cuantity = 0;
					    for(int i = 0; i < inventory.length; i++) {
					        if(inventory[i] != null){
					            if( inventory[i].getType().equals(Material.DOUBLE_PLANT) && inventory[i].getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"))){
					                int cant = inventory[i].getAmount();
					                int topazeCount = cuantity + cant;

									int platineTot = topazeCount / 9;
									int reste = topazeCount - (platineTot * 9);
									ItemStack totalPlatine = new ItemStack(Material.NETHER_STAR, platineTot);
									ItemMeta totalPlatineM = totalPlatine.getItemMeta();
									totalPlatineM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
									totalPlatine.setItemMeta(totalPlatineM);
									
									ItemStack totalTopaze = new ItemStack(Material.DOUBLE_PLANT, topazeCount);
									ItemMeta totalTopazeM = totalTopaze.getItemMeta();
									totalTopazeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
									totalTopazeM.addEnchant(Enchantment.DURABILITY, 1, true);
									totalTopazeM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
									totalTopaze.setItemMeta(totalTopazeM);
									
									ItemStack rest = new ItemStack(Material.DOUBLE_PLANT, reste);
									ItemMeta restM = rest.getItemMeta();
									restM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
									restM.addEnchant(Enchantment.DURABILITY, 1, true);
									restM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
									rest.setItemMeta(restM);

									p.getInventory().removeItem(totalTopaze);
									p.getInventory().addItem(totalPlatine);
									if(reste != 0){
										p.getInventory().addItem(rest);
									}
					            }
					        }
					    }
			          p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'échanger vos topazes en platines !"));
				}else if(args[0].equalsIgnoreCase("platine") && args[1].equalsIgnoreCase("topaze") && inv.containsAtLeast(platine, 1)){
			          ItemStack[] inventory = p.getInventory().getContents();
	                    
					    int cuantity = 0;
					    for(int i = 0; i < inventory.length; i++) {
					        if(inventory[i] != null){
					            if( inventory[i].getType().equals(Material.NETHER_STAR) && inventory[i].getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"))){
					                int cant = inventory[i].getAmount();
					                int platineCount = cuantity + cant;

									int topazeTot = platineCount * 9;
									ItemStack totalPlatine = new ItemStack(Material.NETHER_STAR, platineCount);
									ItemMeta totalPlatineM = totalPlatine.getItemMeta();
									totalPlatineM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lPlatine"));
									totalPlatine.setItemMeta(totalPlatineM);
									
									ItemStack totalTopaze = new ItemStack(Material.DOUBLE_PLANT, topazeTot);
									ItemMeta totalTopazeM = totalTopaze.getItemMeta();
									totalTopazeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
									totalTopazeM.addEnchant(Enchantment.DURABILITY, 1, true);
									totalTopazeM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
									totalTopaze.setItemMeta(totalTopazeM);

									p.getInventory().removeItem(totalPlatine);
									p.getInventory().addItem(totalTopaze);
					            }
					        }
					    }
			          p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'échanger vos platines en topazes !"));
				}
			}
		}
		return false;
	}

}
