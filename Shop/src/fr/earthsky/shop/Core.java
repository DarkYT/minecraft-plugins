package fr.earthsky.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.earthsky.shop.listeners.clickEventConfirm;
import fr.earthsky.shop.listeners.clickEventGlobal;
import fr.earthsky.shop.listeners.clickEventRank;
import fr.earthsky.shop.listeners.divers;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Core extends JavaPlugin {
	
	public static Economy econ = null;
	
	@Override
	public void onEnable(){
		if (!setupEconomy()){
			getLogger().severe(String.format("[%s] - Vault est absent ! Désactivation...", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		PluginManager pm = getServer().getPluginManager(); 		
		pm.registerEvents(new clickEventGlobal(this), this);
		pm.registerEvents(new clickEventConfirm(this), this);
		pm.registerEvents(new clickEventRank(this), this);
		pm.registerEvents(new divers(this), this);
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	public void menuGlobal(Player p){
		OfflinePlayer pl = p;
		double money = econ.getBalance(pl);
		Inventory global = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&2&lBoutique &7(&6" + money + "&7)"));
		
		ItemStack armor = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta armorm = armor.getItemMeta();
		armorm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lGrades"));
		armor.setItemMeta(armorm);
		
		ItemStack barrier = new ItemStack(Material.BARRIER, 1);
		ItemMeta barrierM = barrier.getItemMeta();
		barrierM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cA venir !"));
		barrier.setItemMeta(barrierM);
		
		global.setItem(1, armor);
		global.setItem(4, barrier);
		global.setItem(7, barrier);
		
		p.openInventory(global);
	}
	
	public void menuRanks(Player p){
		OfflinePlayer pl = p;
		double money = econ.getBalance(pl);
		Inventory ranks = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&a&lGrades &7(&6" + money + "&7)"));
		
		ItemStack corrompu = new ItemStack(Material.IRON_HELMET, 1);
		ItemMeta corrompuM = corrompu.getItemMeta();
		corrompuM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8&lGrade Corrompu"));
		corrompu.setItemMeta(corrompuM);
		
		ItemStack vampire = new ItemStack(Material.GOLD_HELMET, 1);
		ItemMeta vampireM = vampire.getItemMeta();
		vampireM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lGrade Vampire")); //&e et vampire
		vampire.setItemMeta(vampireM);
		
		ItemStack demon = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta demonM = demon.getItemMeta();
		demonM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lGrade Déén")); //&6 et demon
		demon.setItemMeta(demonM);
		
		ranks.setItem(1, corrompu);
		ranks.setItem(4, vampire);
		ranks.setItem(7, demon);
		
		p.openInventory(ranks);
	}
	
	public void confirmCor(Player p){
		Inventory corrompu  = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&8&lAcheter le grade coCrompu")); //Même noms et tu fais pour le vampire, comme moi
		
		I		ItemStack confirm = new ItemStack(Material.WOOL, 1, (byte) 5);
		ItemMeta confirmM = confirm.getItemMeta();
		confirmM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lConfirmer"));
		confirm.setItemMeta(confirmM);
		
		ItemStack denied = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta deniedM = confirm.getItemMeta();
		deniedM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lAnnuler"));
		denied.setItemMeta(deniedM);	
		ItemStack back = new ItemStack(Material.ARROW, 1);
		ItemMeta backM = back.getItemMeta();
		backM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9Retour en arrièreère")		back.setItemMeta(backM);
		
		corrompu.setItem(1, confirm);
		corrompu.setItem(4, back);
		corrompu.setItem(7, denied);
		
		p.openInventory(corrompu);
	} 
	
	public void confirmVam(Player p){
		Inventory vampire  = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&e&lAcheter le grade Vampire"));
		
		It		ItemStack confirm = new ItemStack(Material.WOOL, 1, (byte) 5);
		ItemMeta confirmM = confirm.getItemMeta();
		confirmM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lConfirmer"));
		confirm.setItemMeta(confirmM);
		
		ItemStack denied = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta deniedM = confirm.getItemMeta();
		deniedM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lAnnuler"));
		denied.setItemMeta(deniedM);
		ItemStack back = new ItemStack(Material.ARROW, 1);
		ItemMeta backM = back.getItemMeta();
		backM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9Retour en arrière"è;
		back.setItemMeta(backM);

		vampire.setItem(1, confirm);
		vampire.setItem(4, back);
		vampire.setItem(7, denied);
		
		p.openInventory(vampire);
	}
	
	public void confirmDem(Player p){
		Inventory demon  = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6&lAcheter le grade Démon"ém
		
		Item		ItemStack confirm = new ItemStack(Material.WOOL, 1, (byte) 5);
		ItemMeta confirmM = confirm.getItemMeta();
		confirmM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lConfirmer"));
		confirm.setItemMeta(confirmM);
		
		ItemStack denied = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta deniedM = confirm.getItemMeta();
		deniedM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lAnnuler"));
		denied.setItemMeta(deniedM);		ItemStack back = new ItemStack(Material.ARROW, 1);
		ItemMeta backM = back.getItemMeta();
		backM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9Retour en arrière"))ère"));
		back.setItemMeta(backM);
		demon.setItem(1, confirm);
		demon.setItem(4, back);
		demon.setItem(7, denied);
		
		p.openInventory(demon);
	}
	
	public void buyRank(Player p, String rank){
		OfflinePlayer pl = p;
		double money = econ.getBalance(pl);
		double prixCor = 1000.0; 
		double prixVam = 1500.0;
		double prixDem = 2500.0;
		if(rank.equalsIgnoreCase("corrompu")){
			EconomyResponse r = econ.withdrawPlayer(p, prixCor);
			double missing = (prixCor - money);
			if (r.transactionSuccess()) {
				p.sendMessage("Money : " + money);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sync console all manuadd " + p.getName() + " corrompu");
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de coins ! (Il vous manque " + missing + " coins pour acheter ce grade."));
			}
		} else if(rank.equalsIgnoreCase("vampire")){
			EconomyResponse r = econ.withdrawPlayer(p, prixVam);
			double missing = (prixVam - money);
			if (r.transactionSuccess()) {
				p.sendMessage("Money : " + money);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sync console all manuadd " + p.getName() + " vampire");
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de coins ! (Il vous manque " + missing + " coins pour acheter ce grade.)"));
			}
		} else if(rank.equalsIgnoreCase("demon")){
			EconomyResponse r = econ.withdrawPlayer(p, prixDem);
			double missing = (prixDem - money);
			if (r.transactionSuccess()) {
				p.sendMessage("Money : " + money);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sync console all manuadd " + p.getName() + " demon");
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas assez de coins ! (Il vous manque " + missing + " coins pour acheter ce grade."));
			}
		}
	}
	
}
