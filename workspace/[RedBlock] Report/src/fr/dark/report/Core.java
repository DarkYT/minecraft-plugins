package fr.dark.report;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dark.report.commands.reportAdminCommand;
import fr.dark.report.commands.reportPlayerCommand;
import fr.dark.report.listeners.reportListeners;
import fr.dark.report.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class Core extends JavaPlugin {

	public Utils utils = new Utils(this);
	public static SQLConnection sql;

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new reportListeners(this), this);
		getCommand("report").setExecutor(new reportPlayerCommand(this));
		getCommand("areport").setExecutor(new reportAdminCommand(this));
		saveDefaultConfig();
		String protocol = getConfig().getString("MySQL.Protocol");
		String host = getConfig().getString("MySQL.Host");
		String database = getConfig().getString("MySQL.Database");
		String username = getConfig().getString("MySQL.Username");
		String password = getConfig().getString("MySQL.Password");

		sql = new SQLConnection(protocol, host, database, username, password, this);
		sql.connection();

	}

	@SuppressWarnings("deprecation")
	public void openReport(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, utils.colorMessage(getConfig().getString("Report.AReport.InventoryTitle")));
		List<String> l = sql.getPlayers();
		for (int i = 0; i < l.size(); i++) {
			OfflinePlayer pl = Bukkit.getOfflinePlayer(l.get(i));
			ItemStack explain = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta explainM = (SkullMeta) explain.getItemMeta();
			explainM.setOwner(l.get(i));
			explainM.setDisplayName(
					utils.modifyMessage(getConfig().getString("Report.AReport.Name"), "<joueur>", l.get(i)));
			explainM.setLore(Arrays.asList(
					utils.modifyMessage(getConfig().getString("Report.AReport.NumberOfReports"), "<int>", ""+sql.getReportsOfPlayer(pl).size())));
			explain.setItemMeta(explainM);
			inv.setItem(i, explain);
		}
		p.openInventory(inv);
	}
	
	@SuppressWarnings("deprecation")
	public void openPersonnalReport(Player p, String targetName){
		Inventory inv = Bukkit.createInventory(null, 54, utils.modifyMessage(getConfig().getString("Report.AReport.PlayerReportInventory.Title"),"<player>",targetName));
		OfflinePlayer pl = Bukkit.getOfflinePlayer(ChatColor.stripColor(targetName));
		List<String> l = sql.getReportsOfPlayer(pl);
		
		for (int i = 0; i < l.size(); i++) {
			String[] player = l.get(i).split(":");
			int id = Integer.valueOf(player[1]);
			ItemStack explain = new ItemStack(Material.PAPER);
			ItemMeta explainM = explain.getItemMeta();
			explainM.addEnchant(Enchantment.DURABILITY, 0, true);
			explainM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			explainM.setDisplayName(utils.modifyMessage(getConfig().getString("Report.AReport.PlayerReportInventory.Name"), "<int>", ""+(i+1)));
			explainM.setLore(Arrays.asList(
					utils.modifyMessage(getConfig().getString("Report.AReport.PlayerReportInventory.Server"), "<server>", ""+sql.getServer(id)),
					utils.modifyMessage(getConfig().getString("Report.AReport.PlayerReportInventory.Raison"), "<raison>", ""+sql.getRaison(id)),
					"", utils.modifyMessage(getConfig().getString("Report.AReport.PlayerReportInventory.Reporter"), "<reporter>", ""+sql.getReporter(id)),
					"",utils.colorMessage(getConfig().getString("Report.AReport.PlayerReportInventory.Left")),
					utils.colorMessage(getConfig().getString("Report.AReport.PlayerReportInventory.Right")),
					"§7#"+id));
			explain.setItemMeta(explainM);
			inv.setItem(i, explain);
		}
		p.openInventory(inv);
	}

}
