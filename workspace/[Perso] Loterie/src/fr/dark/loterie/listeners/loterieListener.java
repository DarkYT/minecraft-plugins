package fr.dark.loterie.listeners;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.dark.loterie.Loterie;
import net.md_5.bungee.api.ChatColor;

public class loterieListener implements Listener {

	Loterie core;
	public loterieListener(Loterie loterie) {this.core = loterie;}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(Loterie.confFile);
		String name = e.getPlayer().getName();
		if(yc.getConfigurationSection("Offlines").contains(name)) {
			int rew = yc.getInt("Offlines."+e.getPlayer().getName());
			e.getPlayer().sendMessage("§aVous avez gagné "+rew+" topazes dans une loterie !");
			ItemStack topaze = new ItemStack(Material.DOUBLE_PLANT, rew);
			ItemMeta topazem = topaze.getItemMeta();
			topazem.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lTopaze"));
			topazem.addEnchant(Enchantment.DURABILITY, 1, true);
			topazem.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
			topaze.setItemMeta(topazem);
			e.getPlayer().getInventory().addItem(topaze);
			yc.set("Offlines."+e.getPlayer().getName(), null);
			core.saveCustomYml(yc, Loterie.confFile);
		}
	}

}
