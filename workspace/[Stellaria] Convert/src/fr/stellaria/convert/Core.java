package fr.stellaria.convert;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fr.stellaria.convert.commands.convertCommand;

public class Core extends JavaPlugin {
	@Override
	public void onEnable() {
		getCommand("convert").setExecutor(new convertCommand(this));
	}

	public void getMoney(String name, int nbr, Player p) {
		switch (name) {
		case "RUBIS":
			ItemStack rubis = new ItemStack(Material.FIREWORK_CHARGE, nbr);
			ItemMeta meta = rubis.getItemMeta();
			FireworkEffectMeta metaFw = (FireworkEffectMeta) meta;
			FireworkEffect effect = FireworkEffect.builder().withColor(Color.RED).build();
			metaFw.setEffect(effect);
			meta.setDisplayName("§4§lRubis");
			rubis.setItemMeta(metaFw);
			p.getInventory().addItem(rubis);
			p.updateInventory();
			break;
		case "SAPHIR":
			ItemStack saphir = new ItemStack(Material.FIREWORK_CHARGE, nbr);
			ItemMeta meta2 = saphir.getItemMeta();
			FireworkEffectMeta metaFw2 = (FireworkEffectMeta) meta2;
			FireworkEffect effect2 = FireworkEffect.builder().withColor(Color.BLUE).build();
			metaFw2.setEffect(effect2);
			meta2.setDisplayName("§1§lSaphir");
			saphir.setItemMeta(metaFw2);
			p.getInventory().addItem(saphir);
			p.updateInventory();
			break;
		case "PERIDOT":
			ItemStack peridot = new ItemStack(Material.FIREWORK_CHARGE, nbr);
			ItemMeta meta3 = peridot.getItemMeta();
			FireworkEffectMeta metaFw3 = (FireworkEffectMeta) meta3;
			FireworkEffect effect3 = FireworkEffect.builder().withColor(Color.LIME).build();
			metaFw3.setEffect(effect3);
			meta3.setDisplayName("§a§lPéridot");
			peridot.setItemMeta(metaFw3);
			p.getInventory().addItem(peridot);
			p.updateInventory();
			break;
		default:
			break;
		}
	}
}
