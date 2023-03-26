package fr.shiick.redcore.events;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import fr.shiick.redcore.RedCore;

public class inventoryEvent implements Listener {

	RedCore core;

	public inventoryEvent(RedCore redCore) {
		this.core = redCore;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(RedCore.confFile);
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString();
		if (!(yc.getConfigurationSection("Preferences").contains(uuid))) {
			yc.set("Preferences." + uuid, true);
			core.saveConfig(yc, RedCore.confFile);
		}
	}

	@EventHandler
	public void onPick(BlockBreakEvent e) {
		Player p = e.getPlayer();
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(RedCore.confFile);
		String uuid = p.getUniqueId().toString();
		boolean isEnable = yc.getBoolean("Preferences." + uuid);
		if (isEnable) {
			if (e.getPlayer().getInventory().firstEmpty() == -1) {
				for (int i = 0; i < 35; i++) {
					for(ItemStack item : e.getBlock().getDrops()) {
						if (e.getPlayer().getInventory().getItem(i).getType().equals(item.getType())) {
							if (e.getPlayer().getInventory().getItem(i).getAmount() + item.getAmount() <= item.getMaxStackSize()) {

								return;
							}
						}
						if (i == 34) {
							String subtitle = core.utils
									.colorMessage(core.getConfig().getString("InventoryFull.Title.Subtitle"));
							String title = core.utils.colorMessage(core.getConfig().getString("InventoryFull.Title.Title"));
							e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_LLAMA_ANGRY, 1, 1);
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " times 40 30 20");
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"title " + p.getName() + " subtitle {\"text\":\"" + subtitle + "\"}");
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"title " + p.getName() + " title {\"text\":\"" + title + "\"}");
						}

					
					}
				}
			}

		}
	}

}
