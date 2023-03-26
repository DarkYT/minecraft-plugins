package fr.stellaria.chest;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
	}

	@EventHandler
	public void onCloseInventory(InventoryCloseEvent e) {
		if (e.getInventory().getName().equals("§7Coffre personnel de: " + e.getPlayer().getName())) {
			if (e.getPlayer() instanceof Player) {
				for (String pl : getConfig().getConfigurationSection("Chests").getKeys(false)) {
					if (pl.equals(e.getPlayer().getUniqueId().toString())) {
						getConfig().set("Chests." + pl, null);
						saveConfig();
						getConfig().createSection("Chests." + e.getPlayer().getUniqueId());
						saveConfig();
						char c = '1';
						for (ItemStack item : e.getInventory()) {
							saveItem("Chests." + e.getPlayer().getUniqueId() + "." + c++, item);
						}
						saveConfig();
					}
				}
			}
		}
	}

	@EventHandler
	public void takeInventory(InventoryClickEvent e) {
		if (e.getInventory().getName().contains("§7Coffre personnel de: ")) {
			if (!e.getInventory().getName().contains(e.getWhoClicked().getName())) {
				e.setCancelled(true);
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chest")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cT'es une console '-' ! 'Spèce de noob");
				return false;
			}
			Player p = (Player) sender;
			if (args.length == 0) {
				// Open p inventory
				if (p.hasPermission("chest.default")) {
					pChest(p, p);
					return true;
				} else {
					p.sendMessage("§cSi tu veux un coffre, va acheter un grade x)");
				}
			}
			if (args.length == 1) {
				// Open target inventory
				String name = args[0];
				Player target = Bukkit.getServer().getPlayer(name);
				if (target == null) {
					p.sendMessage("§cCe joueur n'existe pas !");
					return false;
				}
				if (p.hasPermission("chest.other")) {
					pChest(p, target);
					return true;
				} else {
					p.sendMessage("§cOccupe toi de ton coffre !");
				}
			}
		}

		return false;
	}

	private void saveItem(String path, ItemStack itemStack) {
		getConfig().set(path, itemStack);
		saveConfig();
	}

	private ItemStack loadItem(String path) {
		ItemStack i = new ItemStack(getConfig().getItemStack(path));
		return i;
	}

	private void pChest(Player p, Player target) {
		if (target.hasPermission("chest.master")) {
			Inventory inv = Bukkit.getServer().createInventory(target, 54, "§7Coffre personnel de: " + target.getName());
			if (getConfig().contains("Chests." + target.getUniqueId())) {
				for (String item : getConfig().getConfigurationSection("Chests." + target.getUniqueId()).getKeys(false)) {
					inv.addItem(loadItem("Chests." + target.getUniqueId() + "." + item));
				}
				p.openInventory(inv);
			} else {
				getConfig().createSection("Chests." + target.getUniqueId());
			}
		} else if (target.hasPermission("chest.default")) {
			Inventory inv = Bukkit.getServer().createInventory(target, 27, "§7Coffre personnel de: " + target.getName());
			if (getConfig().contains("Chests." + target.getUniqueId())) {
				for (String item : getConfig().getConfigurationSection("Chests." + target.getUniqueId()).getKeys(false)) {
					inv.addItem(loadItem("Chests." + target.getUniqueId() + "." + item));
				}
				p.openInventory(inv);
			} else {
				getConfig().createSection("Chests." + target.getUniqueId());
			}
		}
	}

	@SuppressWarnings("unused")
	private boolean emptyChest(Inventory inv) {
		return inv.firstEmpty() == 0;
	}

}
