package fr.efkabe.recolteur;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fr.efkabe.recolteur.commands.RecoltCommand;
import fr.efkabe.recolteur.listeners.EventListener;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getCommand("recharge").setExecutor(new RecoltCommand(this));
		getCommand("lost").setExecutor(new RecoltCommand(this));
		getCommand("rgive").setExecutor(new RecoltCommand(this));
		getServer().getPluginManager().registerEvents(new EventListener(this), this);
		saveDefaultConfig();
	}
	
	public boolean isEmptyConfig() {
		return this.getConfig().getKeys(false).isEmpty();
	}

	public void giveRecolt(Player p, String type) {
		switch (type) {
		case "default":
			ItemStack recolteur = new ItemStack(Material.BLAZE_ROD);
			ItemMeta CustomM = recolteur.getItemMeta();
			CustomM.setDisplayName("§cRécolteur");
			CustomM.setLore(Arrays.asList("Le Récolteur est un outil divin","Il vous fait gagner du temps","Cliquez droit pour récolter un plant"));
			recolteur.setItemMeta(CustomM);
			p.getInventory().addItem(recolteur);
			p.updateInventory();
			break;
		case "expert":
			ItemStack recolteurE = new ItemStack(Material.BLAZE_ROD);
			ItemMeta CustomEM = recolteurE.getItemMeta();
			CustomEM.setDisplayName(ChatColor.DARK_AQUA + "Récolteur Expert");
			CustomEM.setLore(Arrays.asList("Le Récolteur est un outil divin","Il vous fait gagner du temps","Cliquez droit pour récolter un plant"));
			recolteurE.setItemMeta(CustomEM);
			p.getInventory().addItem(recolteurE);
			p.updateInventory();
			break;
		case "master":
			ItemStack recolteurM = new ItemStack(Material.BLAZE_ROD);
			ItemMeta CustomMM = recolteurM.getItemMeta();
			CustomMM.setDisplayName(ChatColor.BLUE + "Récolteur Maître");
			CustomMM.setLore(Arrays.asList("Le Récolteur est un outil divin","Il vous fait gagner du temps","Cliquez droit pour récolter un plant"));
			recolteurM.setItemMeta(CustomMM);
			p.getInventory().addItem(recolteurM);
			p.updateInventory();
			break;
		case "elite":
			ItemStack recolteurEl = new ItemStack(Material.BLAZE_ROD);
			ItemMeta CustomEl = recolteurEl.getItemMeta();
			CustomEl.setDisplayName(ChatColor.AQUA + "Récolteur Élite");
			CustomEl.setLore(Arrays.asList("Le Récolteur est un outil divin","Il vous fait gagner du temps","Cliquez droit pour récolter un plant"));
			recolteurEl.setItemMeta(CustomEl);
			p.getInventory().addItem(recolteurEl);
			p.updateInventory();
			break;
		default:
			break;
		}
	}
}
