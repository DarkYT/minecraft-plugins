package fr.shiick.recycleur;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.shiick.recycleur.commands.recycleurCmd;
import fr.shiick.recycleur.events.recyclerInventoryEvent;

public class Recycleur extends JavaPlugin {

	@Override
	public void onEnable() {
		// Commands
		getCommand("recycleur").setExecutor(new recycleurCmd(this));
		// Events
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new recyclerInventoryEvent(this), this);
		// Config
		saveDefaultConfig();
	}

	@SuppressWarnings("deprecation")
	public void openRecycleur(Player p) {
		Inventory recycleur = Bukkit.createInventory(null, 27, colorMessage(getConfig().getString("Recycleur.Name")));

		ItemStack hopper = new ItemStack(Material.HOPPER);
		ItemMeta hopperM = hopper.getItemMeta();
		hopperM.setDisplayName(colorMessage(getConfig().getString("Recycleur.Hopper")));
		hopper.setItemMeta(hopperM);

		ItemStack explain = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta explainM = (SkullMeta) explain.getItemMeta();
		explainM.setOwner("MHF_Question");
		explainM.setDisplayName(colorMessage(getConfig().getString("Recycleur.Skull")));
		explainM.setLore(Arrays.asList(colorMessage(getConfig().getString("Recycleur.SkullLore1")), colorMessage(getConfig().getString("Recycleur.SkullLore2")), colorMessage(getConfig().getString("Recycleur.SkullLore3")), colorMessage(getConfig().getString("Recycleur.SkullLore4"))));
		explain.setItemMeta(explainM);

		ItemStack confirmation = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
		ItemMeta confirmationM = confirmation.getItemMeta();
		confirmationM.setDisplayName(colorMessage(getConfig().getString("Recycleur.Confirm")));
		confirmation.setItemMeta(confirmationM);

		ItemStack infos = new ItemStack(Material.DIAMOND);
		ItemMeta infosM = infos.getItemMeta();
		infosM.setDisplayName(colorMessage(getConfig().getString("Recycleur.Diamond")));
		infosM.setLore(Arrays.asList(colorMessage(getConfig().getString("Recycleur.DiamondLore1")), colorMessage(getConfig().getString("Recycleur.DiamondLore2")), colorMessage(getConfig().getString("Recycleur.DiamondLore3")), colorMessage(getConfig().getString("Recycleur.DiamondLore4"))));
		infosM.addEnchant(Enchantment.DURABILITY, 1, true);
		infosM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		infos.setItemMeta(infosM);

		for (int i = 0; i < 27; i++) {
			if (recycleur.getItem(i) == null) {
				ItemStack wall = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
				ItemMeta meta7 = wall.getItemMeta();
				meta7.setDisplayName(" ");
				wall.setItemMeta(meta7);
				recycleur.setItem(i, wall);
			}
		}
		recycleur.setItem(4, hopper);
		recycleur.setItem(10, explain);
		recycleur.setItem(16, infos);
		recycleur.setItem(22, confirmation);
		recycleur.setItem(13, new ItemStack(Material.AIR));

		p.openInventory(recycleur);
	}

	public String getType(Material material) {
		if (material == Material.WOOD_SPADE || material == Material.STONE_SPADE || material == Material.IRON_SPADE || material == Material.GOLD_SPADE || material == Material.DIAMOND_SPADE) {
			return "shovel";
		} else if (material == Material.WOOD_HOE || material == Material.STONE_HOE || material == Material.IRON_HOE || material == Material.GOLD_HOE || material == Material.DIAMOND_HOE) {
			return "hoe";
		} else if (material == Material.WOOD_SWORD || material == Material.STONE_SWORD || material == Material.IRON_SWORD || material == Material.GOLD_SWORD || material == Material.DIAMOND_SWORD) {
			return "sword";
		} else if (material == Material.WOOD_AXE || material == Material.STONE_AXE || material == Material.IRON_AXE || material == Material.GOLD_AXE || material == Material.DIAMOND_AXE) {
			return "axe";
		} else if (material == Material.WOOD_PICKAXE || material == Material.STONE_PICKAXE || material == Material.IRON_PICKAXE || material == Material.GOLD_PICKAXE || material == Material.DIAMOND_PICKAXE) {
			return "pickaxe";
		} else if (material == Material.LEATHER_HELMET || material == Material.CHAINMAIL_HELMET || material == Material.IRON_HELMET || material == Material.GOLD_HELMET || material == Material.DIAMOND_HELMET) {
			return "helmet";
		} else if (material == Material.LEATHER_CHESTPLATE || material == Material.CHAINMAIL_CHESTPLATE || material == Material.IRON_CHESTPLATE || material == Material.GOLD_CHESTPLATE || material == Material.DIAMOND_CHESTPLATE) {
			return "chestplate";
		} else if (material == Material.LEATHER_LEGGINGS || material == Material.CHAINMAIL_LEGGINGS || material == Material.IRON_LEGGINGS || material == Material.GOLD_LEGGINGS || material == Material.DIAMOND_LEGGINGS) {
			return "leggings";
		} else if (material == Material.LEATHER_BOOTS || material == Material.CHAINMAIL_BOOTS || material == Material.IRON_BOOTS || material == Material.GOLD_BOOTS || material == Material.DIAMOND_BOOTS) {
			return "boots";
		} else if (material == Material.SHIELD || material == Material.BOW || material == Material.FISHING_ROD || material == Material.FLINT_AND_STEEL || material == Material.SHEARS) {
			return "misc";
		} else {
			return "error";
		}
	}

	public void recycleItem(String type, Material material, short durability, List<String> lore, Player p) {
		switch (type) {
		case "shovel":
			switch (material) {
			case WOOD_SPADE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.WOOD, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case STONE_SPADE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case IRON_SPADE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case GOLD_SPADE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case DIAMOND_SPADE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			default:
				break;
			}
		case "hoe":
			switch (material) {
			case WOOD_HOE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.WOOD, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability < 29) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.WOOD, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case STONE_HOE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability < 65) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case IRON_HOE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability < 124) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case GOLD_HOE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability < 15) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case DIAMOND_HOE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability < 780) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			default:
				break;
			}
		case "sword":
			switch (material) {
			case WOOD_SWORD:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.WOOD, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability < 29) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.WOOD, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case STONE_SWORD:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability < 65) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case IRON_SWORD:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability < 124) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case GOLD_SWORD:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability < 15) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case DIAMOND_SWORD:
				if((lore != null )&& (lore.contains(colorMessage(getConfig().getString("Recycleur.DiamondsItemLore"))))){
					p.closeInventory();
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.Invalid")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
					
				}else{
					if (durability == 0) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability >= 1 && durability < 780) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else {
						p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
						p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
					}
				}
				
				break;
			default:
				break;
			}
		case "axe":
			switch (material) {
			case WOOD_AXE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.WOOD, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 19) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.WOOD, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 19 && durability <= 38) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.WOOD, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case STONE_AXE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 43) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 43 && durability <= 86) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case IRON_AXE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 83) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 83 && durability <= 166) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case GOLD_AXE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 10) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 10 && durability <= 20) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case DIAMOND_AXE:
				if((lore != null )&& (lore.contains(colorMessage(getConfig().getString("Recycleur.DiamondsItemLore"))))){
					p.closeInventory();
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.Invalid")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
					
				}else{
					if (durability == 0) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 3));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability >= 1 && durability <= 520) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability > 520 && durability <= 1040) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else {
						p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
						p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
					}
				}
				
				break;
			default:
				break;
			}
		case "pickaxe":
			switch (material) {
			case WOOD_PICKAXE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.WOOD, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 19) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.WOOD, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 19 && durability <= 38) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.WOOD, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case STONE_PICKAXE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 43) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 43 && durability <= 86) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case IRON_PICKAXE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 83) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 83 && durability <= 166) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case GOLD_PICKAXE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 10) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 10 && durability <= 20) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case DIAMOND_PICKAXE:
				if((lore != null )&& (lore.contains(colorMessage(getConfig().getString("Recycleur.DiamondsItemLore"))))){
					p.closeInventory();
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.Invalid")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
					
				}else{
					if (durability == 0) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 3));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability >= 1 && durability <= 520) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability > 520 && durability <= 1040) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else {
						p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
						p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
					}
				}
				
				break;
			default:
				break;
			}
		case "helmet":
			switch (material) {
			case LEATHER_HELMET:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 11) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 11 && durability <= 22) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 22 && durability <= 33) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 33 && durability <= 44) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case CHAINMAIL_HELMET:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 33) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 33 && durability <= 66) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 66 && durability <= 99) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 99 && durability <= 132) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case GOLD_HELMET:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 15) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 15 && durability <= 30) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 30 && durability <= 45) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 45 && durability <= 60) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case IRON_HELMET:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 33) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 33 && durability <= 66) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 66 && durability <= 99) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 99 && durability <= 132) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case DIAMOND_HELMET:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 72) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 72 && durability <= 144) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 144 && durability <= 216) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 216 && durability <= 288) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			default:
				break;
			}
		case "chestplate":
			switch (material) {
			case LEATHER_CHESTPLATE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 8));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 10) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 7));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 10 && durability <= 20) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 6));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 20 && durability <= 30) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 30 && durability <= 40) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 40 && durability <= 50) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 50 && durability <= 60) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 60 && durability <= 70) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case GOLD_CHESTPLATE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 8));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 14) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 7));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 14 && durability <= 28) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 6));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 28 && durability <= 42) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 42 && durability <= 56) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 56 && durability <= 70) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 70 && durability <= 84) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 84 && durability <= 98) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case IRON_CHESTPLATE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 8));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 30) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 7));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 30 && durability <= 60) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 6));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 60 && durability <= 90) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 90 && durability <= 120) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 120 && durability <= 150) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 150 && durability <= 180) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 180 && durability <= 210) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case CHAINMAIL_CHESTPLATE:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 8));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 30) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 7));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 30 && durability <= 60) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 6));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 60 && durability <= 90) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 90 && durability <= 120) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 120 && durability <= 150) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 150 && durability <= 180) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 180 && durability <= 210) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case DIAMOND_CHESTPLATE:
				if((lore != null )&& (lore.contains(colorMessage(getConfig().getString("Recycleur.DiamondsItemLore"))))){
					p.closeInventory();
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.Invalid")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
					
				}else{
					if (durability == 0) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 8));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability >= 1 && durability <= 66) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 7));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability > 66 && durability <= 132) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 6));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability > 132 && durability <= 198) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 5));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability > 198 && durability <= 264) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 4));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability > 264 && durability <= 330) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 3));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability > 330 && durability <= 396) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else if (durability > 396 && durability <= 462) {
						Inventory inv = p.getOpenInventory().getTopInventory();
						if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
							inv.setItem(13, new ItemStack(Material.AIR));
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
						}
					} else {
						p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
						p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
					}
				}
				break;
			default:
				break;
			}
		case "leggings":
			switch (material) {
			case LEATHER_LEGGINGS:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 7));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 10) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 6));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 10 && durability <= 20) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 20 && durability <= 30) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 30 && durability <= 40) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 40 && durability <= 50) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability <= 50 && durability <= 60) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case GOLD_LEGGINGS:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 7));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 15) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 6));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 15 && durability <= 30) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 30 && durability <= 45) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 45 && durability <= 60) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 60 && durability <= 75) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 75 && durability <= 90) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case CHAINMAIL_LEGGINGS:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 7));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 32) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 6));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 32 && durability <= 64) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 64 && durability <= 96) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 96 && durability <= 128) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 128 && durability <= 160) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 160 && durability <= 192) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case IRON_LEGGINGS:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 7));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 32) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 6));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 32 && durability <= 64) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 64 && durability <= 96) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 96 && durability <= 128) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 128 && durability <= 160) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 160 && durability <= 192) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case DIAMOND_LEGGINGS:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 7));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 70) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 6));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 70 && durability <= 140) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 5));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 140 && durability <= 210) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 210 && durability <= 280) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 280 && durability <= 350) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 350 && durability <= 420) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			default:
				break;
			}
		case "boots":
			switch (material) {
			case LEATHER_BOOTS:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 16) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 16 && durability <= 32) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 32 && durability <= 48) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.LEATHER, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case IRON_BOOTS:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 48) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 48 && durability <= 96) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 96 && durability <= 144) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case CHAINMAIL_BOOTS:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 48) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 48 && durability <= 96) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 96 && durability <= 144) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case GOLD_BOOTS:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 22) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 22 && durability <= 44) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 44 && durability <= 66) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case DIAMOND_BOOTS:
				if (durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 4));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability >= 1 && durability <= 107) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 107 && durability <= 214) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if (durability > 214 && durability <= 321) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			default:
				break;
			}
		case "misc":
			switch(material) {
			case FLINT_AND_STEEL:
				if(durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case SHIELD:
				if(durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case SHEARS:
				if(durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if(durability >= 1 && durability <= 119) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case FISHING_ROD:
				if(durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.STRING, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if(durability >= 1 && durability <= 32) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.STRING, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			case BOW:
				if(durability == 0) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.STRING, 3));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if(durability >= 1 && durability <= 128) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.STRING, 2));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else if(durability > 128 && durability <= 256) {
					Inventory inv = p.getOpenInventory().getTopInventory();
					if (inv.getName().equalsIgnoreCase(colorMessage(getConfig().getString("Recycleur.Name")))) {
						inv.setItem(13, new ItemStack(Material.AIR));
						p.getInventory().addItem(new ItemStack(Material.STRING, 1));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " actionbar \" " + colorMessage(getConfig().getString("Recycleur.Recycled")) + " \"");
						p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 0);
					}
				} else {
					p.sendMessage(colorMessage(getConfig().getString("Recycleur.NotRecyclable")));
					p.playSound(p.getLocation(), Sound.ENTITY_VINDICATION_ILLAGER_AMBIENT, 1, 1);
				}
				break;
			default:
				break;
			}
		default:
			break;
		}
	}

	public String colorMessage(String message) {
		String msg = ChatColor.translateAlternateColorCodes('&', message);
		return msg;
	}

}
