package com.acropolismc.play.sellstick;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.acropolismc.play.sellstick.Configs.PriceConfig;
import com.acropolismc.play.sellstick.Configs.StickConfig;
import com.earth2me.essentials.Essentials;

import net.milkbowl.vault.economy.Economy;

public class SellStick extends JavaPlugin {

	public Plugin essentialsPlugin;
	public Essentials ess;
	private static Economy econ = null;
	private static final Logger log = Logger.getLogger("Minecraft");
	
	public boolean skyblock = false;

	public void onEnable() {
		essentialsPlugin = Bukkit.getPluginManager().getPlugin("Essentials");
		if (essentialsPlugin.isEnabled() && (essentialsPlugin instanceof Essentials)) {
			this.essentialsPlugin = (Essentials) essentialsPlugin;
		}
		
		ess = (Essentials) essentialsPlugin;
		
		this.getCommand("sellstick").setExecutor(new SellStickCommand(this));
		this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		this.saveDefaultConfig();
		StickConfig.instance.setup(getDataFolder());
		PriceConfig.instance.setup(getDataFolder());
		
		if (!setupEconomy()) {
			log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		Plugin skyblock = getServer().getPluginManager().getPlugin("ASkyBlock");

		if(StickConfig.instance.useEssentialsWorth) {
			if(essentialsPlugin == null || !essentialsPlugin.isEnabled()) {
				log.warning("[SellStick] Essentials est introuvable !");
			}else {
				log.info("[Sellstick] Essentials détecté, liens avec Worth");
			}
		}

		if(skyblock != null && skyblock.isEnabled()) {
			this.skyblock = true;
			log.info("[SellStick] ASkyBlock détecté ! Liens avec ASkyBlock");
		} else {
			log.warning("[SellStick] SKYBLOCK N'A PAS ETE TROUVE ! LE PLUGIN EST INUTILISABLE !");

		}

	}

	public Economy getEcon() {
		return SellStick.econ;
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
	
}
