package fr.efkabe.recolteur.listeners;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.CropState;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.NetherWartsState;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.material.NetherWarts;

import fr.efkabe.recolteur.Main;
import net.md_5.bungee.api.ChatColor;

public class EventListener implements Listener {
	Main main;
	public EventListener(Main main){
		this.main = main;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		//TODO Random give de graine (Blé, Betterave)
		
		ItemStack item = e.getItem();

		if (item == null || item.getType() == Material.AIR)
			return;// Check si il y a un item en main

		ItemMeta meta = item.getItemMeta();

		if (meta.getDisplayName() == null)
			return;// Check si l'item à un nom

		if (e.getItem().getType().equals(Material.BLAZE_ROD)
				&& (e.getItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Récolteur")
						|| e.getItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "Récolteur Expert")
						|| e.getItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Récolteur Maître")
						|| e.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Récolteur Élite"))) {
			// Vérifie si l'objet tenu est un récolteur
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK)
			// Vérifie si le joueur fais un click droit
			{
				MaterialData data = e.getClickedBlock().getState().getData();
				boolean grown = false;

				if (data instanceof Crops)
					grown = ((Crops) data).getState() == CropState.RIPE;
				else if (data instanceof NetherWarts)
					grown = ((NetherWarts) data).getState() == NetherWartsState.RIPE;
				else
					return;

				if (grown) {
					if(!main.getConfig().getConfigurationSection("Recolteurs").contains(p.getUniqueId().toString())){
						p.sendMessage("§cTu n'as pas le droit d'utiliser le Récolteur !");
						return;
					}
					int durability = main.getConfig().getInt("Recolteurs." + p.getUniqueId() + ".durability");
					if(durability == 0){
						p.sendMessage("§cVous n'avez pas de durabilité sur votre Récolteur (Faites /recharge) !");
						return;
					}else{
						String itemName = e.getItem().getItemMeta().getDisplayName();
						int nbr = 0;
						Material material = Material.STONE;
						if (e.getClickedBlock().getType().equals(Material.POTATO)) {
							material = Material.POTATO_ITEM;
							if (itemName.equals(ChatColor.RED + "Récolteur")) {
								nbr = new Random().nextInt(3 - 1 + 1) + 2;
							} else if (itemName.equals(ChatColor.DARK_AQUA + "Récolteur Expert")) {
								nbr = new Random().nextInt(4 - 1 + 1) + 2;
							} else if (itemName.equals(ChatColor.BLUE + "Récolteur Maître")) {
								nbr = new Random().nextInt(5 - 1 + 1) + 2;
							} else if (itemName.equals(ChatColor.AQUA + "Récolteur Élite")) {
								nbr = new Random().nextInt(6 - 1 + 1) + 2;
							}
						} else if (e.getClickedBlock().getType().equals(Material.CROPS)) {
							material = Material.WHEAT;
							if (itemName.equals(ChatColor.RED + "Récolteur")) {
								p.getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(Material.SEEDS, new Random().nextInt(2 - 1 + 1) + 2));
							} else if (itemName.equals(ChatColor.DARK_AQUA + "Récolteur Expert")) {
								p.getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(Material.SEEDS, new Random().nextInt(3 - 1 + 1) + 2));
							} else if (itemName.equals(ChatColor.BLUE + "Récolteur Maître")) {
								p.getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(Material.SEEDS, new Random().nextInt(4 - 1 + 1) + 2));
							} else if (itemName.equals(ChatColor.AQUA + "Récolteur Élite")) {
								p.getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(Material.SEEDS, new Random().nextInt(5 - 1 + 1) + 2));
							}
							nbr = 1;
						} else if (e.getClickedBlock().getType().equals(Material.BEETROOT_BLOCK)) {
							material = Material.BEETROOT;
							if (itemName.equals(ChatColor.RED + "Récolteur")) {
								p.getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(Material.BEETROOT_SEEDS, new Random().nextInt(2 - 1 + 1) + 2));
							} else if (itemName.equals(ChatColor.DARK_AQUA + "Récolteur Expert")) {
								p.getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(Material.BEETROOT_SEEDS, new Random().nextInt(3 - 1 + 1) + 2));
							} else if (itemName.equals(ChatColor.BLUE + "Récolteur Maître")) {
								p.getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(Material.BEETROOT_SEEDS, new Random().nextInt(4 - 1 + 1) + 2));
							} else if (itemName.equals(ChatColor.AQUA + "Récolteur Élite")) {
								p.getWorld().dropItem(e.getClickedBlock().getLocation(), new ItemStack(Material.BEETROOT_SEEDS, new Random().nextInt(5 - 1 + 1) + 2));
							}
							nbr = 1;
						} else if (e.getClickedBlock().getType().equals(Material.CARROT)) {
							material = Material.CARROT_ITEM;
							if (itemName.equals(ChatColor.RED + "Récolteur")) {
								nbr = new Random().nextInt(3 - 1 + 1) + 2;
							} else if (itemName.equals(ChatColor.DARK_AQUA + "Récolteur Expert")) {
								nbr = new Random().nextInt(4 - 1 + 1) + 2;
							} else if (itemName.equals(ChatColor.BLUE + "Récolteur Maître")) {
								nbr = new Random().nextInt(5 - 1 + 1) + 2;
							} else if (itemName.equals(ChatColor.AQUA + "Récolteur Élite")) {
								nbr = new Random().nextInt(6 - 1 + 1) + 2;
							}
						}

						else if (e.getClickedBlock().getType().equals(Material.NETHER_WARTS)) {
							material = Material.NETHER_STALK;
							if (itemName.equals(ChatColor.RED + "Récolteur")) {
								nbr = new Random().nextInt(3 - 1 + 1) + 2;
							} else if (itemName.equals(ChatColor.DARK_AQUA + "Récolteur Expert")) {
								nbr = new Random().nextInt(4 - 1 + 1) + 2;
							} else if (itemName.equals(ChatColor.BLUE + "Récolteur Maître")) {
								nbr = new Random().nextInt(5 - 1 + 1) + 2;
							} else if (itemName.equals(ChatColor.AQUA + "Récolteur Élite")) {
								nbr = new Random().nextInt(6 - 1 + 1) + 2;
							}
						}
						main.getConfig().set("Recolteurs." + p.getUniqueId() + ".durability", 0);
						main.getConfig().set("Recolteurs." + p.getUniqueId() + ".durability", (durability - 1));
						main.saveConfig();
						ItemStack drop = new ItemStack(material, nbr);
						World world = p.getWorld();
						world.dropItem(e.getClickedBlock().getLocation(), drop);
						System.out.println(nbr);
						System.out.println(e.getClickedBlock().getType());
						p.playSound(p.getLocation(), Sound.BLOCK_GRASS_BREAK, 20.0F, 1.0F);
						if (data instanceof Crops) {
							((Crops) data).setState(CropState.SEEDED);
						} else if (data instanceof NetherWarts) {
							((NetherWarts) data).setState(NetherWartsState.SEEDED);
						}
						BlockState state = e.getClickedBlock().getState();
						state.setData(data);
						state.update();
					}
				}
				e.setCancelled(true);
				return;
			}
			return;
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		if(e.getWhoClicked() instanceof Player){
			Player p = (Player) e.getWhoClicked();
			Inventory inv = e.getInventory();
			
			ItemStack rubis = new ItemStack(Material.FIREWORK_CHARGE, 1);
			ItemMeta meta = rubis.getItemMeta();
			FireworkEffectMeta metaFw = (FireworkEffectMeta) meta;
			FireworkEffect effect = FireworkEffect.builder().withColor(Color.RED).build();
			metaFw.setEffect(effect);
			meta.setDisplayName("§4§lRubis");
			rubis.setItemMeta(metaFw);
			
			if(inv.getName().equalsIgnoreCase("§9Shop §bRécolte")){
				ItemStack clickedItem = e.getCurrentItem();
				if(clickedItem.getType().equals(Material.AIR)){
					return;
				}
				if(clickedItem.getItemMeta().getDisplayName().equals("§9Récolteur")){
					if(p.hasPermission("recolt.default")){
						if(p.getInventory().containsAtLeast(rubis, 64)){
							
							ItemStack rubis1 = new ItemStack(Material.FIREWORK_CHARGE, 64);
							ItemMeta meta1 = rubis1.getItemMeta();
							FireworkEffectMeta metaFw1 = (FireworkEffectMeta) meta;
							FireworkEffect effect1 = FireworkEffect.builder().withColor(Color.RED).build();
							metaFw1.setEffect(effect1);
							meta1.setDisplayName("§4§lRubis");
							rubis1.setItemMeta(metaFw1);
							
							p.getInventory().removeItem(rubis1);
							p.updateInventory();
							
							if(main.isEmptyConfig()){
								main.getConfig().createSection("Recolteurs." + p.getUniqueId());
								main.saveConfig();
							}
							
							if(!main.getConfig().getConfigurationSection("Recolteurs").contains(p.getUniqueId().toString())){
								main.getConfig().createSection("Recolteurs." + p.getUniqueId());
								main.saveConfig();
							}
							main.getConfig().set("Recolteurs." + p.getUniqueId() + ".durability", 0);
							main.getConfig().set("Recolteurs." + p.getUniqueId() + ".level", "default");
							main.saveConfig();
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " buy.recolt.default");
							ItemStack recolteur = new ItemStack(Material.BLAZE_ROD);
							ItemMeta CustomM = recolteur.getItemMeta();
							CustomM.setDisplayName("§cRécolteur");
							CustomM.setLore(Arrays.asList("Le Récolteur est un outil divin","Il vous fait gagner du temps","Cliquez droit pour récolter un plant"));
							recolteur.setItemMeta(CustomM);
							
							if(p.getInventory().firstEmpty() != -1) {
								p.getInventory().addItem(recolteur);
								p.sendMessage("&eVous avez obtenu un &6Récolteur".replace("&", "§"));
							}
							else {
								p.sendMessage("&cVotre inventaire est plein".replace("&", "§"));
							}
						}else{
							p.sendMessage("§cVous n'avez pas assez de rubis !");
						}
					}
					
				}else if(clickedItem.getItemMeta().getDisplayName().equals("§9Récolteur §bExpert")){
					if(p.hasPermission("recolt.expert")){
						if(p.hasPermission("buy.recolt.default")){
							if(p.getInventory().containsAtLeast(rubis, 96)){
								
								ItemStack rubis1 = new ItemStack(Material.FIREWORK_CHARGE, 96);
								ItemMeta meta1 = rubis1.getItemMeta();
								FireworkEffectMeta metaFw1 = (FireworkEffectMeta) meta;
								FireworkEffect effect1 = FireworkEffect.builder().withColor(Color.RED).build();
								metaFw1.setEffect(effect1);
								meta1.setDisplayName("§4§lRubis");
								rubis1.setItemMeta(metaFw1);
								
								p.getInventory().removeItem(rubis1);
								p.updateInventory();
								
								if(main.isEmptyConfig()){
									main.getConfig().createSection("Recolteurs." + p.getUniqueId());
									main.saveConfig();
								}
								
								if(!main.getConfig().getConfigurationSection("Recolteurs").contains(p.getUniqueId().toString())){
									main.getConfig().createSection("Recolteurs." + p.getUniqueId());
									main.saveConfig();
								}
								main.getConfig().set("Recolteurs." + p.getUniqueId() + ".durability", 0);
								main.getConfig().set("Recolteurs." + p.getUniqueId() + ".level", "expert");
								main.saveConfig();
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " buy.recolt.expert");
								ItemStack recolteur = new ItemStack(Material.BLAZE_ROD);
								ItemMeta CustomM = recolteur.getItemMeta();
								CustomM.setDisplayName(ChatColor.DARK_AQUA + "Récolteur Expert");
								CustomM.setLore(Arrays.asList("Le Récolteur est un outil divin","Il vous fait gagner du temps","Cliquez droit pour récolter un plant"));
								recolteur.setItemMeta(CustomM);
								
								if(p.getInventory().firstEmpty() != -1) {
									p.getInventory().addItem(recolteur);
									p.sendMessage("&eVous avez obtenu un &6Récolteur Expert".replace("&", "§"));
								}
								else {
									p.sendMessage("&cVotre inventaire est plein".replace("&", "§"));
								}
							}else{
								p.sendMessage("§cVous n'avez pas assez de rubis !");
							}
						}else{
							p.sendMessage("§cVous n'avez pas acheter le Récolteur de base !");
						}
					}
				}else if(clickedItem.getItemMeta().getDisplayName().equals("§9Récolteur §bMaître")){
					if(p.hasPermission("recolt.master")){
						if(p.hasPermission("buy.recolt.expert")){
							if(p.getInventory().containsAtLeast(rubis, 128)){
								
								ItemStack rubis1 = new ItemStack(Material.FIREWORK_CHARGE, 128);
								ItemMeta meta1 = rubis1.getItemMeta();
								FireworkEffectMeta metaFw1 = (FireworkEffectMeta) meta;
								FireworkEffect effect1 = FireworkEffect.builder().withColor(Color.RED).build();
								metaFw1.setEffect(effect1);
								meta1.setDisplayName("§4§lRubis");
								rubis1.setItemMeta(metaFw1);
								
								p.getInventory().removeItem(rubis1);
								p.updateInventory();
								
								if(main.isEmptyConfig()){
									main.getConfig().createSection("Recolteurs." + p.getUniqueId());
									main.saveConfig();
								}
								
								if(!main.getConfig().getConfigurationSection("Recolteurs").contains(p.getUniqueId().toString())){
									main.getConfig().createSection("Recolteurs." + p.getUniqueId());
									main.saveConfig();
								}
								main.getConfig().set("Recolteurs." + p.getUniqueId() + ".durability", 0);
								main.getConfig().set("Recolteurs." + p.getUniqueId() + ".level", "master");
								main.saveConfig();
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " buy.recolt.master");
								ItemStack recolteur = new ItemStack(Material.BLAZE_ROD);
								ItemMeta CustomM = recolteur.getItemMeta();
								CustomM.setDisplayName(ChatColor.BLUE + "Récolteur Maître");
								CustomM.setLore(Arrays.asList("Le Récolteur est un outil divin","Il vous fait gagner du temps","Cliquez droit pour récolter un plant"));
								recolteur.setItemMeta(CustomM);
								
								if(p.getInventory().firstEmpty() != -1) {
									p.getInventory().addItem(recolteur);
									p.sendMessage("&eVous avez obtenu un &6Récolteur Maître".replace("&", "§"));
								}
								else {
									p.sendMessage("&cVotre inventaire est plein".replace("&", "§"));
								}
							}else{
								p.sendMessage("§cVous n'avez pas assez de rubis !");
							}
						}else{
							p.sendMessage("§cVous n'avez pas acheter le Récolteur Expert !");
						}
					}
				}else if(clickedItem.getItemMeta().getDisplayName().equals("§9Récolteur §bElite")){
					if(p.hasPermission("recolt.elite")){
						if(p.hasPermission("buy.recolt.master")){
							if(p.getInventory().containsAtLeast(rubis, 160)){
								
								ItemStack rubis1 = new ItemStack(Material.FIREWORK_CHARGE, 160);
								ItemMeta meta1 = rubis1.getItemMeta();
								FireworkEffectMeta metaFw1 = (FireworkEffectMeta) meta;
								FireworkEffect effect1 = FireworkEffect.builder().withColor(Color.RED).build();
								metaFw1.setEffect(effect1);
								meta1.setDisplayName("§4§lRubis");
								rubis1.setItemMeta(metaFw1);
								
								p.getInventory().removeItem(rubis1);
								p.updateInventory();
								
								if(main.isEmptyConfig()){
									main.getConfig().createSection("Recolteurs." + p.getUniqueId());
									main.saveConfig();
								}
								
								if(!main.getConfig().getConfigurationSection("Recolteurs").contains(p.getUniqueId().toString())){
									main.getConfig().createSection("Recolteurs." + p.getUniqueId());
									main.saveConfig();
								}
								main.getConfig().set("Recolteurs." + p.getUniqueId() + ".durability", 0);
								main.getConfig().set("Recolteurs." + p.getUniqueId() + ".level", "elite");
								main.saveConfig();
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " buy.recolt.elite");
								ItemStack recolteur = new ItemStack(Material.BLAZE_ROD);
								ItemMeta CustomM = recolteur.getItemMeta();
								CustomM.setDisplayName(ChatColor.AQUA + "Récolteur Élite");
								CustomM.setLore(Arrays.asList("Le Récolteur est un outil divin","Il vous fait gagner du temps","Cliquez droit pour récolter un plant"));
								recolteur.setItemMeta(CustomM);
								
								if(p.getInventory().firstEmpty() != -1) {
									p.getInventory().addItem(recolteur);
									p.sendMessage("&eVous avez obtenu un &6Récolteur Élite".replace("&", "§"));
								}
								else {
									p.sendMessage("&cVotre inventaire est plein".replace("&", "§"));
								}
							}else{
								p.sendMessage("§cVous n'avez pas assez de rubis !");
							}
						}else{
							p.sendMessage("§cVous n'avez pas acheter le Récolteur Maître !");
						}
					}
				}
				e.setCancelled(true);
			}
		}
		
	}
}
