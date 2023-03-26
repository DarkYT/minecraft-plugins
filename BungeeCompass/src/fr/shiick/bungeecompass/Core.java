package fr.shiick.bungeecompass;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.shiick.bungeecompass.listeners.Listeners;

public class Core extends JavaPlugin {

	@Override
	public void onEnable() {
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Listeners(this), this);
		for (Player pl : Bukkit.getOnlinePlayers()) {
			Bukkit.dispatchCommand(pl, "actualisationscoreboard");
		}
	}

	public void teleportServer(Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aTéléportation sur le serveur &6" + server + "&a."));
	}

	public void MenuMain(Player p) {
		Inventory Main = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&6&lTéléporteur"));

		ItemStack sky = new ItemStack(Material.GRASS, 1);
		ItemMeta meta1 = sky.getItemMeta();
		meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8&l➤ &2&lSkyblock easy 1"));
		sky.setItemMeta(meta1);

		ItemStack sky2 = new ItemStack(Material.MYCEL, 1);
		ItemMeta meta2 = sky2.getItemMeta();
		meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8&l➤ &8&lSkyblock easy 2"));
		sky2.setItemMeta(meta2);

		ItemStack skyw = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta meta3 = skyw.getItemMeta();
		meta3.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8&l➤ &9&lSkywars &c(Indisponible)"));
		meta3.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cIndisponible pour le moment !")));
		skyw.setItemMeta(meta3);

		ItemStack water = new ItemStack(Material.WATER_BUCKET, 1);
		ItemMeta meta4 = water.getItemMeta();
		meta4.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8&l➤ &b&lWaterBlock"));
		water.setItemMeta(meta4);

		ItemStack brick = new ItemStack(Material.BRICK, 1);
		ItemMeta meta5 = brick.getItemMeta();
		meta5.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8&l➤ &c&lSkyblock Build"));
		brick.setItemMeta(meta5);

		ItemStack feather = new ItemStack(Material.FEATHER, 1);
		ItemMeta meta6 = feather.getItemMeta();
		meta6.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8&l➤ &9&lJump"));
		feather.setItemMeta(meta6);

		ItemStack bed = new ItemStack(Material.BED, 1);
		ItemMeta meta7 = bed.getItemMeta();
		meta7.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8&l➤ &a&lSpawn"));
		bed.setItemMeta(meta7);

		for (int i = 0; i < 45; i++) {
			if (Main.getItem(i) == null) {
				ItemStack wall = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
				ItemMeta meta = wall.getItemMeta();
				meta.setDisplayName(" ");
				wall.setItemMeta(meta);
				Main.setItem(i, wall);
			}
		}

		Main.setItem(10, sky);
		Main.setItem(13, skyw);
		Main.setItem(16, sky2);
		Main.setItem(20, water);
		Main.setItem(24, brick);
		Main.setItem(30, feather);
		Main.setItem(32, bed);

		p.openInventory(Main);
	}

}
