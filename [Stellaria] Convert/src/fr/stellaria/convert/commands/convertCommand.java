package fr.stellaria.convert.commands;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;

import fr.stellaria.convert.Core;

public class convertCommand implements CommandExecutor {

	Core core;
	public convertCommand(Core core) {
		this.core = core;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player p = (Player) sender;
		
		Inventory inv = p.getInventory();
		
		ItemStack rubis = new ItemStack(Material.FIREWORK_CHARGE, 1);
		ItemMeta meta = rubis.getItemMeta();
		FireworkEffectMeta metaFw = (FireworkEffectMeta) meta;
		FireworkEffect effect = FireworkEffect.builder().withColor(Color.RED).build();
		metaFw.setEffect(effect);
		meta.setDisplayName("§4§lRubis");
		rubis.setItemMeta(metaFw);
		
		ItemStack saphir = new ItemStack(Material.FIREWORK_CHARGE, 1);
		ItemMeta meta2 = saphir.getItemMeta();
		FireworkEffectMeta metaFw2 = (FireworkEffectMeta) meta2;
		FireworkEffect effect2 = FireworkEffect.builder().withColor(Color.BLUE).build();
		metaFw2.setEffect(effect2);
		meta2.setDisplayName("§1§lSaphir");
		saphir.setItemMeta(metaFw2);
		
		ItemStack peridot = new ItemStack(Material.FIREWORK_CHARGE, 1);
		ItemMeta meta3 = peridot.getItemMeta();
		FireworkEffectMeta metaFw3 = (FireworkEffectMeta) meta3;
		FireworkEffect effect3 = FireworkEffect.builder().withColor(Color.LIME).build();
		metaFw3.setEffect(effect3);
		meta3.setDisplayName("§a§lPéridot");
		peridot.setItemMeta(metaFw3);
		
		if(cmd.getName().equalsIgnoreCase("convert")){
			if(args.length == 0 || args.length == 1){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLa commande est: /convert <péridot/saphir/rubis> <péridot/saphir/rubis>"));
			}
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("péridot") && args[1].equalsIgnoreCase("saphir") && inv.containsAtLeast(peridot, 9)){
					ItemStack[] inventory = p.getInventory().getContents();
                    
				    int cuantity = 0;
				    for(int i = 0; i < inventory.length; i++) {
				        if(inventory[i] != null){
				            if( inventory[i].getType().equals(Material.FIREWORK_CHARGE) && inventory[i].getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&a&lPéridot"))){
				                int cant = inventory[i].getAmount();
				                int ambreCount = cuantity + cant;
				                
				                int quotient = ambreCount / 9;
				                int reste = ambreCount - (quotient * 9);
				                
				        		ItemStack saphir1 = new ItemStack(Material.FIREWORK_CHARGE, quotient);
				        		ItemMeta meta21 = saphir1.getItemMeta();
				        		FireworkEffectMeta metaFw21 = (FireworkEffectMeta) meta21;
				        		FireworkEffect effect21 = FireworkEffect.builder().withColor(Color.BLUE).build();
				        		metaFw21.setEffect(effect21);
				        		meta21.setDisplayName("§1§lSaphir");
				        		saphir1.setItemMeta(metaFw21);
				        		
				        		ItemStack peridot1 = new ItemStack(Material.FIREWORK_CHARGE, ambreCount);
				        		ItemMeta meta31 = peridot1.getItemMeta();
				        		FireworkEffectMeta metaFw31 = (FireworkEffectMeta) meta31;
				        		FireworkEffect effect31 = FireworkEffect.builder().withColor(Color.LIME).build();
				        		metaFw31.setEffect(effect31);
				        		meta31.setDisplayName("§a§lPéridot");
				        		peridot1.setItemMeta(metaFw31);
								
				        		ItemStack peridot11 = new ItemStack(Material.FIREWORK_CHARGE, reste);
				        		ItemMeta meta311 = peridot11.getItemMeta();
				        		FireworkEffectMeta metaFw311 = (FireworkEffectMeta) meta311;
				        		FireworkEffect effect311 = FireworkEffect.builder().withColor(Color.LIME).build();
				        		metaFw311.setEffect(effect311);
				        		meta311.setDisplayName("§a§lPéridot");
				        		peridot11.setItemMeta(metaFw311);

								p.getInventory().removeItem(peridot1);
								p.getInventory().addItem(saphir1);
								if(reste != 0){
									p.getInventory().addItem(peridot11);
								}
				            }
				        }
				    }
				    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'échanger vos péridots en saphir !"));
				}else if(args[0].equalsIgnoreCase("saphir") && args[1].equalsIgnoreCase("péridot") && inv.containsAtLeast(saphir, 1)){
			          ItemStack[] inventory = p.getInventory().getContents();
	                    
					    int cuantity = 0;
					    for(int i = 0; i < inventory.length; i++) {
					        if(inventory[i] != null){
					            if( inventory[i].getType().equals(Material.FIREWORK_CHARGE) && inventory[i].getItemMeta().getDisplayName().equalsIgnoreCase("§1§lSaphir")){
					                int cant = inventory[i].getAmount();
					                int topazeCount = cuantity + cant;

									int ambreTot = topazeCount * 9;
									ItemStack saphir1 = new ItemStack(Material.FIREWORK_CHARGE, topazeCount);
					        		ItemMeta meta21 = saphir1.getItemMeta();
					        		FireworkEffectMeta metaFw21 = (FireworkEffectMeta) meta21;
					        		FireworkEffect effect21 = FireworkEffect.builder().withColor(Color.BLUE).build();
					        		metaFw21.setEffect(effect21);
					        		meta21.setDisplayName("§1§lSaphir");
					        		saphir1.setItemMeta(metaFw21);
					        		
					        		ItemStack peridot1 = new ItemStack(Material.FIREWORK_CHARGE, ambreTot);
					        		ItemMeta meta31 = peridot1.getItemMeta();
					        		FireworkEffectMeta metaFw31 = (FireworkEffectMeta) meta31;
					        		FireworkEffect effect31 = FireworkEffect.builder().withColor(Color.LIME).build();
					        		metaFw31.setEffect(effect31);
					        		meta31.setDisplayName("§a§lPéridot");
					        		peridot1.setItemMeta(metaFw31);

									p.getInventory().removeItem(saphir1);
									p.getInventory().addItem(peridot1);
					            }
					        }
					    }
			          p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'échanger vos saphirs en péridots !"));
				}else if(args[0].equalsIgnoreCase("saphir") && args[1].equalsIgnoreCase("rubis") && inv.containsAtLeast(saphir, 9)){
			          ItemStack[] inventory = p.getInventory().getContents();
	                    
					    int cuantity = 0;
					    for(int i = 0; i < inventory.length; i++) {
					        if(inventory[i] != null){
					            if( inventory[i].getType().equals(Material.FIREWORK_CHARGE) && inventory[i].getItemMeta().getDisplayName().equalsIgnoreCase("§1§lSaphir")){
					                int cant = inventory[i].getAmount();
					                int topazeCount = cuantity + cant;

									int platineTot = topazeCount / 9;
									int reste = topazeCount - (platineTot * 9);
									ItemStack rubis1 = new ItemStack(Material.FIREWORK_CHARGE, platineTot);
									ItemMeta meta1 = rubis1.getItemMeta();
									FireworkEffectMeta metaFw1 = (FireworkEffectMeta) meta1;
									FireworkEffect effect1 = FireworkEffect.builder().withColor(Color.RED).build();
									metaFw1.setEffect(effect1);
									meta1.setDisplayName("§4§lRubis");
									rubis1.setItemMeta(metaFw1);
									
									ItemStack saphir1 = new ItemStack(Material.FIREWORK_CHARGE, topazeCount);
									ItemMeta meta21 = saphir1.getItemMeta();
									FireworkEffectMeta metaFw21 = (FireworkEffectMeta) meta21;
									FireworkEffect effect21 = FireworkEffect.builder().withColor(Color.BLUE).build();
									metaFw21.setEffect(effect21);
									meta21.setDisplayName("§1§lSaphir");
									saphir1.setItemMeta(metaFw21);
									
									ItemStack peridot11 = new ItemStack(Material.FIREWORK_CHARGE, reste);
					        		ItemMeta meta311 = peridot11.getItemMeta();
					        		FireworkEffectMeta metaFw311 = (FireworkEffectMeta) meta311;
					        		FireworkEffect effect311 = FireworkEffect.builder().withColor(Color.BLUE).build();
					        		metaFw311.setEffect(effect311);
					        		meta311.setDisplayName("§1§lSaphir");
					        		peridot11.setItemMeta(metaFw311);


									p.getInventory().removeItem(saphir1);
									p.getInventory().addItem(rubis1);
									if(reste != 0){
										p.getInventory().addItem(peridot11);
									}
					            }
					        }
					    }
			          p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'échanger vos saphirs en rubis !"));
				}else if(args[0].equalsIgnoreCase("rubis") && args[1].equalsIgnoreCase("saphir") && inv.containsAtLeast(rubis, 1)){
			          ItemStack[] inventory = p.getInventory().getContents();
	                    
					    int cuantity = 0;
					    for(int i = 0; i < inventory.length; i++) {
					        if(inventory[i] != null){
					            if( inventory[i].getType().equals(Material.FIREWORK_CHARGE) && inventory[i].getItemMeta().getDisplayName().equalsIgnoreCase("§4§lRubis")){
					                int cant = inventory[i].getAmount();
					                int platineCount = cuantity + cant;

									int topazeTot = platineCount * 9;
									ItemStack rubis1 = new ItemStack(Material.FIREWORK_CHARGE, platineCount);
									ItemMeta meta1 = rubis1.getItemMeta();
									FireworkEffectMeta metaFw1 = (FireworkEffectMeta) meta1;
									FireworkEffect effect1 = FireworkEffect.builder().withColor(Color.RED).build();
									metaFw1.setEffect(effect1);
									meta1.setDisplayName("§4§lRubis");
									rubis1.setItemMeta(metaFw1);
									
									ItemStack saphir1 = new ItemStack(Material.FIREWORK_CHARGE, topazeTot);
									ItemMeta meta21 = saphir1.getItemMeta();
									FireworkEffectMeta metaFw21 = (FireworkEffectMeta) meta21;
									FireworkEffect effect21 = FireworkEffect.builder().withColor(Color.BLUE).build();
									metaFw21.setEffect(effect21);
									meta21.setDisplayName("§1§lSaphir");
									saphir1.setItemMeta(metaFw21);
									p.getInventory().removeItem(rubis1);
									p.getInventory().addItem(saphir1);
					            }
					        }
					    }
			          p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez d'échanger vos rubis en saphirs !"));
				}
			}
		}
		return false;
	}

}
