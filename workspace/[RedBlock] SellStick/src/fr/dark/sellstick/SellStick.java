package fr.dark.sellstick;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class SellStick {

	Player p;
	int d, id;
	String dura;
	Core core;

	public SellStick(Core core, Player p) {
		this.core = core;
		this.p = p;
	}
	
	public int getDura(ItemStack stick) {
		String[] s = core.getConfig().getString("SellStick.Item.DurabilityLoreString").split(" ");
		int i = 0;
		for(int y = 0; y < s.length; y++) {
			if(s[y].equals("<dura>")) {
				i = y;
				break;
			}
		}
		String[] strings = stick.getItemMeta().getLore().get(stick.getItemMeta().getLore().size()-1).split(" ");
		String durability = ChatColor.stripColor(strings[i]);
		if(!(durability.equals("i"))) {
			int durabilityInt = Integer.valueOf(durability);
			return durabilityInt;
		}
		return -1;
	}
	
	public void reloadStick(Player p, int slot, ItemStack stick) {
		ItemStack it = new ItemStack(Material.valueOf(core.getConfig().getString("SellStick.Item.Type")));
		ItemMeta itm = it.getItemMeta();
		itm.addEnchant(Enchantment.DURABILITY, 0, true);
		itm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		itm.setDisplayName(core.utils.colorMessage(core.getConfig().getString("SellStick.Item.Name")));
		List<String> lore = new ArrayList<>();
		for (String s : core.getConfig().getStringList("SellStick.Item.Lore")) {
			lore.add(core.utils.colorMessage(s));
		}
		lore.add("");
		lore.add(core.utils.modifyMessage(core.getConfig().getString("SellStick.Item.DurabilityLoreString"), "<dura>", ""+(getDura(stick)-1)));
		itm.setLore(lore);
		it.setItemMeta(itm);
		
		p.getInventory().setItem(slot, it);
		p.updateInventory();
	}
	
	public void deleteStick(int slot, Player p) {
		ItemStack it = new ItemStack(Material.AIR);
		p.getInventory().setItem(slot, it);
		p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
		p.updateInventory();
	}

	public ItemStack stick(String durability) {
		ItemStack it = new ItemStack(Material.valueOf(core.getConfig().getString("SellStick.Item.Type")));
		ItemMeta itm = it.getItemMeta();
		itm.addEnchant(Enchantment.DURABILITY, 0, true);
		itm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		itm.setDisplayName(core.utils.colorMessage(core.getConfig().getString("SellStick.Item.Name")));
		List<String> lore = new ArrayList<>();
		for (String s : core.getConfig().getStringList("SellStick.Item.Lore")) {
			lore.add(core.utils.colorMessage(s));
		}
		lore.add("");
		lore.add(core.utils.modifyMessage(core.getConfig().getString("SellStick.Item.DurabilityLoreString"), "<dura>", durability));
		itm.setLore(lore);
		it.setItemMeta(itm);

		return it;
	}

}
