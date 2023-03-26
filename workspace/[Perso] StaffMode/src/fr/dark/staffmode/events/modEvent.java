package fr.dark.staffmode.events;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.dark.staffmode.StaffMode;
import fr.dark.staffmode.utils.Utils;

public class modEvent implements Listener {
	
	StaffMode core;
	public modEvent(StaffMode core) {
		this.core = core;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(!e.getPlayer().hasPermission("staffmode.use")) {
			for(int i = 0; i < core.vanish.size(); i++) {
				e.getPlayer().hidePlayer(core.vanish.get(i));
			}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if(core.freeze.contains(e.getPlayer())) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), core.getConfig().getString("StaffMode.Freeze.Sanction").replace("{PLAYER}", e.getPlayer().getName()));
			for(Entry<Player, ItemStack[]> entries : core.modeUsers.entrySet()) {
				Utils.sendColorMessage(entries.getKey(), "&l&8[&cStaffMode&8] &r&c&l"+e.getPlayer().getName()+" &r&fa été ban suite a un déco lors d'un freeze !");
			}
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(core.freeze.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getClickedInventory() != null) {
			if(e.getClickedInventory().getName().contains("§8EnderChest de")) {
				e.setCancelled(true);
			}else if(e.getClickedInventory().getName().contains("§8Inventaire de")) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void interactEvent(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(core.modeUsers.containsKey(e.getPlayer())) {
				Player p = e.getPlayer();
				if(e.getItem() == null) return;
				if(!e.getItem().hasItemMeta()) return;
				if(!e.getItem().getItemMeta().hasDisplayName()) return;
				switch(e.getItem().getType()) {
				case INK_SACK:
					int data = e.getItem().getDurability();
					if(data == core.getConfig().getInt("StaffMode.Inventory.Vanish.Disable.Data")) {
						core.vanish.add(p);
						for(Player pl : Bukkit.getOnlinePlayers()) {
							if(!pl.hasPermission("staffmode.use")) {
								pl.hidePlayer(p);
							}
						}
						Utils.sendColorMessage(p, "&l&8[&cStaffMode&8] &r&a&lActivation &r&fdu vanish");
						p.getInventory().setItem(1, Utils.getModItem(Material.valueOf(core.getConfig().getString("StaffMode.Inventory.Vanish.Enable.Type")), 
								(byte) core.getConfig().getInt("StaffMode.Inventory.Vanish.Enable.Data"), Utils.color(core.getConfig().getString("StaffMode.Inventory.Vanish.Enable.DispName")), 
								core.getConfig().getBoolean("StaffMode.Inventory.Vanish.Enable.Enchant"), null));
					}else if(data == (byte) core.getConfig().getInt("StaffMode.Inventory.Vanish.Enable.Data")) {
						core.vanish.remove(p);
						for(Player pl : Bukkit.getOnlinePlayers()) {
							pl.showPlayer(p);
						}
						Utils.sendColorMessage(p, "&l&8[&cStaffMode&8] &r&c&lDésactivation &r&fdu vanish");
						p.getInventory().setItem(1, Utils.getModItem(Material.valueOf(core.getConfig().getString("StaffMode.Inventory.Vanish.Disable.Type")), 
								(byte) core.getConfig().getInt("StaffMode.Inventory.Vanish.Disable.Data"), Utils.color(core.getConfig().getString("StaffMode.Inventory.Vanish.Disable.DispName")), 
								core.getConfig().getBoolean("StaffMode.Inventory.Vanish.Disable.Enchant"), null));
					}
					break;
				case FEATHER:
					String name = e.getItem().getItemMeta().getDisplayName();
					if(name.equals(Utils.color(core.getConfig().getString("StaffMode.Inventory.Fly.Disable.DispName")))) {
						if(p.getGameMode() == GameMode.CREATIVE) {
							Utils.sendColorMessage(p, "&l&8[&cStaffMode&8] &r&cVous êtes déjà en créatif !");
						}else {
							p.setAllowFlight(true);
							if(!p.isFlying()) {
								p.setFlying(true);
							}
							Utils.sendColorMessage(p, "&l&8[&cStaffMode&8] &r&a&lActivation &r&fdu fly");
							p.getInventory().setItem(7, Utils.getModItem(Material.valueOf(core.getConfig().getString("StaffMode.Inventory.Fly.Enable.Type")), 
									(byte) core.getConfig().getInt("StaffMode.Inventory.Fly.Enable.Data"), Utils.color(core.getConfig().getString("StaffMode.Inventory.Fly.Enable.DispName")), 
									core.getConfig().getBoolean("StaffMode.Inventory.Fly.Enable.Enchant"), null));
						}
					}else if(name.equals(Utils.color(core.getConfig().getString("StaffMode.Inventory.Fly.Enable.DispName")))) {
						if(p.getGameMode() == GameMode.CREATIVE) {
							Utils.sendColorMessage(p, "&l&8[&cStaffMode&8] &r&cVous êtes en créatif !");
						}else {
							p.setAllowFlight(false);
							if(p.isFlying()) {
								p.setFlying(false);
							}
							Utils.sendColorMessage(p, "&l&8[&cStaffMode&8] &r&c&lDésactivation &r&fdu fly");
							p.getInventory().setItem(7, Utils.getModItem(Material.valueOf(core.getConfig().getString("StaffMode.Inventory.Fly.Disable.Type")), 
									(byte) core.getConfig().getInt("StaffMode.Inventory.Fly.Disable.Data"), Utils.color(core.getConfig().getString("StaffMode.Inventory.Fly.Disable.DispName")), 
									core.getConfig().getBoolean("StaffMode.Inventory.Fly.Disable.Enchant"), null));
						}
					}
					break;
				case ENDER_PEARL:
					Random r = new Random();
					int rand = r.nextInt(Bukkit.getOnlinePlayers().size());
					int i = 0;
					for(Player pl : Bukkit.getOnlinePlayers()) {
						if(i == rand) {
							if(pl == p) {
								i--;
							}else {
								p.teleport(pl);
								Utils.sendColorMessage(p, "&l&8[&cStaffMode&8] &r&fVous vous êtes téléporté à &c"+pl.getName());
								break;
							}
						}
						i++;
					}
					break;
				default:
					break;
				}
				e.setCancelled(true);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEntityEvent e) {
		if(e.getRightClicked() instanceof Player) {
			Player t = (Player) e.getRightClicked();
			Player p = e.getPlayer();
			if(p.getInventory().getItemInMainHand() == null) return;
			if(!p.getInventory().getItemInMainHand().hasItemMeta()) return;
			if(!p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) return;
			if(e.getHand() == EquipmentSlot.OFF_HAND) return;
			
			if(core.modeUsers.containsKey(p)) {
				switch(p.getInventory().getItemInMainHand().getType()) {
				case BARRIER:
					if (core.cooldown.containsKey(p.getName())) {

						int secondes = 60;
						long timeleft = ((core.cooldown.get(p.getName()) / 1000) + secondes)
								- (System.currentTimeMillis() / 1000);
						if (timeleft > 0) {
							Utils.sendColorMessage(p, "&l&8[&cStaffMode&8] &r&cVous venez de report un joueur ! Attendez &f"+timeleft+"&cs avant de le refaire");
							return;
						}

					}

					core.cooldown.put(p.getName(), System.currentTimeMillis());
					Bukkit.dispatchCommand(p, "report " + t.getName() + " Suspect Le comportement de " + t.getName() + " est anormal lors de la filature");
					Utils.sendColorMessage(p, "&l&8[&cStaffMode&8] &r&fVous venez de report &c"+t.getName()+"&f !");
					break;
				case PACKED_ICE:
					if(!core.freeze.contains(t)) {
						core.freeze.add(t);
						for(String s : core.getConfig().getStringList("StaffMode.Freeze.FreezeMessage")) {
							if(s.contains("{PLAYER}")) {
								Utils.sendColorMessage(t, s.replace("{PLAYER}", p.getName()));
							}else {
								Utils.sendColorMessage(t, s);
							}
						}
						Utils.sendColorMessage(p, "&l&8[&cStaffMode&8] &r&fVous venez de freeze &c"+t.getName()+"&f !");
					}else {
						core.freeze.remove(t);
						Utils.sendColorMessage(t, "&l&8[&cStaffMode&8] &r&fVous venez d'être dé-freeze");
						Utils.sendColorMessage(p, "&l&8[&cStaffMode&8] &r&fVous venez de dé-freeze &c"+t.getName()+"&f !");
					}
					break;
				case ENDER_CHEST:
					Inventory inv = Bukkit.createInventory(p, 27, Utils.color("&8EnderChest de &c&l"+t.getName()));
					Inventory ec = t.getEnderChest();
					for(ItemStack it : ec.getContents()) {
						if(it != null) {
							inv.addItem(it);
						}
					}
					p.openInventory(inv);
					break;
				case SKULL_ITEM:
					Inventory inventory = Bukkit.createInventory(p, 54, Utils.color("&8Inventaire de &c&l"+t.getName()));
					
					ItemStack helmet = t.getInventory().getHelmet();
					ItemStack chest = t.getInventory().getChestplate();
					ItemStack legs = t.getInventory().getLeggings();
					ItemStack boots = t.getInventory().getBoots();
					
					if(helmet == null) {
						inventory.setItem(0, Utils.getModItem(Material.BARRIER, (byte) 0, "§c§lPas de casque", true, null));
					}else {
						inventory.setItem(0, helmet);
					}
					
					if(chest == null) {
						inventory.setItem(1, Utils.getModItem(Material.BARRIER, (byte) 0, "§c§lPas de plastron", true, null));
					}else {
						inventory.setItem(1, chest);
					}
					
					if(legs == null) {
						inventory.setItem(2, Utils.getModItem(Material.BARRIER, (byte) 0, "§c§lPas de jambières", true, null));
					}else {
						inventory.setItem(2, legs);
					}
					
					if(boots == null) {
						inventory.setItem(3, Utils.getModItem(Material.BARRIER, (byte) 0, "§c§lPas de bottes", true, null));
					}else {
						inventory.setItem(3, boots);
					}
					
					ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
					SkullMeta sM = (SkullMeta) skull.getItemMeta();
					sM.setOwner(t.getName());
					sM.setDisplayName("§7Joueur: §c"+t.getName());
					double health = Math.round(t.getHealth()*10)/10;
					sM.setLore(Arrays.asList(Utils.color("&m&8----------------"),"§8Vie: "+health+"§c♥","§8Nourriture: "+t.getFoodLevel()));
					skull.setItemMeta(sM);
					
					inventory.setItem(8, skull);
					
					for(int i = 9; i < 18; i++) {
						inventory.setItem(i, Utils.getModItem(Material.STAINED_GLASS_PANE, (byte) 15, "", false, null));
					}
					
					for(int y = 0; y < 35; y++) {
						if(t.getInventory().getItem(y) != null) {
							inventory.setItem(y+18, t.getInventory().getItem(y));
						}else {
							inventory.setItem(y+18, new ItemStack(Material.AIR));
						}
					}
					
					p.openInventory(inventory);
					break;
				default:
					break;
				}
			}
		}
	}

}
