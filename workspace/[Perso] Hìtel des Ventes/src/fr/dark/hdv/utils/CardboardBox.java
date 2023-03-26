package fr.dark.hdv.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.dark.hdv.HDV;

/**
 * A serializable ItemStack
 */
public class CardboardBox implements Serializable {
	private static final long serialVersionUID = 729890133797629668L;

	// JSON String of item
	private final String rawValue;

	public CardboardBox(ItemStack item) {
		String type = item.getType().toString();
		int amount = item.getAmount();
		@SuppressWarnings("deprecation")
		byte damage = item.getData().getData();
		Map<String, Object> serialize = new HashMap<>();
		serialize.put("type", type);
		serialize.put("amount", amount);
		serialize.put("damage", damage);
		if (item.hasItemMeta()) {
			Map<String, Object> meta = new HashMap<>();
			ItemMeta itM = item.getItemMeta();
			if (itM.hasDisplayName()) {
				meta.put("dispname", itM.getDisplayName());
			}
			if (itM.hasLore()) {
				meta.put("lore", itM.getLore());
			}
			if (itM.hasEnchants()) {
				Map<String, Integer> enchs = new HashMap<>();
				Map<Enchantment, Integer> enchantments = item.getEnchantments();
				for (Enchantment enchantment : enchantments.keySet()) {
					enchs.put(enchantment.getName(), enchantments.get(enchantment));
				}
				meta.put("enchants", enchs);
			}
			if(item.getType() == Material.ENCHANTED_BOOK) {
				EnchantmentStorageMeta m = (EnchantmentStorageMeta) item.getItemMeta();
				if(m.hasStoredEnchants()) {
					Map<String, Integer> enchs = new HashMap<>();
					Map<Enchantment, Integer> enchantments = m.getStoredEnchants();
					for (Enchantment enchantment : enchantments.keySet()) {
						enchs.put(enchantment.getName(), enchantments.get(enchantment));
					}
					meta.put("storedenchants", enchs);
				}
			}
			if(itM instanceof SpawnEggMeta)
			{
				meta.put("entitytype", HDV.getType(item).toString());
			}
			serialize.put("meta", meta);
		}
		Gson gson = new Gson();
		this.rawValue = gson.toJson(serialize);
	}

	public String box() {
		return this.rawValue;
	}
	
	@SuppressWarnings("unchecked")
	static public ItemStack getBaseItem(String rawValue) {
		Gson gson = new Gson();
		Map<String, Object> keys = gson.fromJson(rawValue, new TypeToken<Map<String, Object>>() {
		}.getType());
		
		if (keys.get("amount") != null) {
			Double d = (Double) keys.get("amount");
			Integer i = d.intValue();
			keys.put("amount", i);
		}
		if (keys.get("damage") != null) {
			Double d = (Double) keys.get("damage");
			byte i = d.byteValue();
			keys.put("damage", i);
		}
		ItemStack it = new ItemStack(Material.valueOf((String) keys.get("type")), (int) keys.get("amount"),
				(byte) keys.get("damage"));
		if(keys.containsKey("meta")) {
			ItemMeta itM = it.getItemMeta();
			Map<String, Object> meta = (Map<String, Object>) keys.get("meta");
			meta = recursiveDoubleToInteger(meta);
			if(meta.containsKey("entitytype"))
			{
				EntityType entity = EntityType.valueOf((String) meta.get("entitytype"));
				((SpawnEggMeta) itM).setSpawnedType(entity);
			}
			if (meta.containsKey("enchants")) {
				HashMap<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
				Map<String, Integer> enchs = (Map<String, Integer>) meta.get("enchants");
				for (String cEnchantment : enchs.keySet()) {
					map.put(Enchantment.getByName(cEnchantment), enchs.get(cEnchantment));
				}
				for(Enchantment e : map.keySet()) {
					itM.addEnchant(e, map.get(e), true);
				}
			}
			if(meta.containsKey("storedenchants")) {
				EnchantmentStorageMeta m = (EnchantmentStorageMeta) itM;
				HashMap<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
				Map<String, Integer> enchs = (Map<String, Integer>) meta.get("storedenchants");
				for (String cEnchantment : enchs.keySet()) {
					map.put(Enchantment.getByName(cEnchantment), enchs.get(cEnchantment));
				}
				for(Enchantment e : map.keySet()) {
					m.addStoredEnchant(e, map.get(e), true);
				}
				it.setItemMeta(m);
			}else {
				it.setItemMeta(itM);
			}
		}
		return it;
	}

	@SuppressWarnings("unchecked")
	static public ItemStack unbox(String rawValue) {
		// Decode the item from JSON
		Gson gson = new Gson();
		Map<String, Object> keys = gson.fromJson(rawValue, new TypeToken<Map<String, Object>>() {
		}.getType());

		// Repair Gson thinking int == double
		if (keys.get("amount") != null) {
			Double d = (Double) keys.get("amount");
			Integer i = d.intValue();
			keys.put("amount", i);
		}
		if (keys.get("damage") != null) {
			Double d = (Double) keys.get("damage");
			byte i = d.byteValue();
			keys.put("damage", i);
		}

		ItemStack it = new ItemStack(Material.valueOf((String) keys.get("type")), (int) keys.get("amount"),
				(byte) keys.get("damage"));

		// Create item
		if (keys.containsKey("meta")) {
			ItemMeta itM = it.getItemMeta();
			Map<String, Object> meta = (Map<String, Object>) keys.get("meta");
			meta = recursiveDoubleToInteger(meta);

			if (meta.containsKey("dispname")) {
				itM.setDisplayName((String) meta.get("dispname"));
			}
			if (meta.containsKey("lore")) {
				itM.setLore((List<String>) meta.get("lore"));
			}
			if(meta.containsKey("entitytype"))
			{
				EntityType entity = EntityType.valueOf((String) meta.get("entitytype"));
				((SpawnEggMeta) itM).setSpawnedType(entity);
			}
			if (meta.containsKey("enchants")) {
				HashMap<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
				Map<String, Integer> enchs = (Map<String, Integer>) meta.get("enchants");
				for (String cEnchantment : enchs.keySet()) {
					map.put(Enchantment.getByName(cEnchantment), enchs.get(cEnchantment));
				}
				for(Enchantment e : map.keySet()) {
					itM.addEnchant(e, map.get(e), true);
				}
			}
			if(meta.containsKey("storedenchants")) {
				EnchantmentStorageMeta m = (EnchantmentStorageMeta) itM;
				HashMap<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
				Map<String, Integer> enchs = (Map<String, Integer>) meta.get("storedenchants");
				for (String cEnchantment : enchs.keySet()) {
					map.put(Enchantment.getByName(cEnchantment), enchs.get(cEnchantment));
				}
				for(Enchantment e : map.keySet()) {
					m.addStoredEnchant(e, map.get(e), true);
				}
				it.setItemMeta(m);
			}else {
				it.setItemMeta(itM);
			}
			
		}

		return it;
	}

	private static Map<String, Object> recursiveDoubleToInteger(Map<String, Object> map) {
		// We copy the map
		Map<String, Object> map2 = new HashMap<String, Object>();
		for (String key : map.keySet()) {
			Object o = map.get(key);
			if (o instanceof Double) {
				// Convert Double -> Int
				Double d = (Double) o;
				Integer i = d.intValue();
				map2.put(key, i);
			} else if (o instanceof Map) { // We have a map, we assume it's a serialized object
				@SuppressWarnings("unchecked")
				Map<String, Object> map3 = (Map<String, Object>) o;
				map2.put(key, recursiveDoubleToInteger(map3)); // Doubles -> Ints
			} else {
				map2.put(key, o); // Else: Insert the object as-is
			}
		}
		return map2;
	}

}