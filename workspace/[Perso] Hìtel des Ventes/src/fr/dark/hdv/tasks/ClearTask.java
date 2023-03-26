package fr.dark.hdv.tasks;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.dark.hdv.HDV;
import fr.dark.hdv.utils.CardboardBox;

public class ClearTask extends BukkitRunnable
{

	@Override
	public void run() 
	{
		
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(HDV.confFile);
		
		for(String item : yc.getConfigurationSection("Players").getKeys(false))
		{
			if(System.currentTimeMillis() >= yc.getLong("Players."+item+".Date") + HDV.days)
			{
				
				Player seller = Bukkit.getPlayer(UUID.fromString(yc.getString("Players."+item+".Vendeur")));
				
				if(seller != null)
				{
					seller.sendMessage("§cLa date d'expiration de votre annonce dans l'hôtel des ventes est arrivée, veuillez récupérer votre item avec /hdv recup");
					HDV.getInstance().items.put(UUID.fromString(yc.getString("Players."+item+".Vendeur")), CardboardBox.unbox(yc.getString("Players."+item+".Item")));
				}
				else
				{
					UUID uuid = UUID.randomUUID();
					HDV.getInstance().getConfig().set("ExpiredItems."+uuid+".Vendeur", yc.getString("Players."+item+".Vendeur"));
					HDV.getInstance().getConfig().set("ExpiredItems."+uuid+".Item", yc.getString("Players."+item+".Item"));
					HDV.getInstance().saveConfig();
				}
				
				yc.set("Players."+item, null);
				
				saveCustomYml(yc, HDV.confFile);
			}
		}

		
	}
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
