package fr.dark.classes.mainclass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import fr.dark.classes.Core;

public class Vocations {

	Core core;
	Player p;
	String vocation;
	boolean has5;
	List<Player> maceP = new ArrayList<>();
	int arrows = 25;

	public Vocations(Core core, Player p) {
		this.core = core;
		this.p = p;
	}
	
	public int getArrowNbr(){
		return arrows;
	}
	
	public void setArrows(){
		arrows--;
	}

	public void setTrue() {
		this.has5 = true;
	}

	public boolean getB() {
		return has5;
	}

	public void addPlayerToMace(Player p) {
		maceP.add(p);
	}

	public boolean isMace(Player p) {
		return maceP.contains(p);
	}

	public void createPyroArcher() {
		this.vocation = "PyroArcher";
		p.getInventory().clear();
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		p.getInventory().setItem(0,
				core.createItem(Material.BOW, "§6ArcPyro", Enchantment.ARROW_DAMAGE, 0, Arrays.asList("")));
		p.getInventory().setItem(1, new ItemStack(Material.WOOD_SWORD));
		p.getInventory().setItem(3, new ItemStack(Material.ARROW, 35));
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
		p.getInventory().setItem(4, new ItemStack(Material.CAULDRON_ITEM));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0, false, false));
		this.has5 = false;
	}

	public void createArcher() {
		this.vocation = "Archer";
		p.getInventory().clear();
		p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
		p.getInventory().setItem(0,
				core.createItem(Material.BOW, "§6Arc", Enchantment.ARROW_DAMAGE, 1, Arrays.asList("")));
		p.getInventory().setItem(1, new ItemStack(Material.WOOD_SWORD));
		p.getInventory().setItem(2, new ItemStack(Material.ARROW, 48));
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
	}

	public void createHandMan() {
		this.vocation = "HandMan";
		p.getInventory().clear();
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		p.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
	}

	public void createLancier() {
		this.vocation = "Lancier";
		p.getInventory().clear();
		p.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
		p.getInventory().setItem(0, new ItemStack(Material.STICK, 5));
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 10));
	}

	public void createSentinelle() {
		this.vocation = "Sentinelle";
		p.getInventory().clear();
		p.getInventory().setChestplate(core.createItem(Material.GOLD_CHESTPLATE, "§FGold Chestplate",
				Enchantment.PROTECTION_PROJECTILE, 2, Arrays.asList("")));
		p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
		p.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));
		p.getInventory().setItem(1, new ItemStack(Material.SHIELD));
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
	}

	public void createSkirmisher() {
		this.vocation = "Skirmisher";
		p.getInventory().clear();
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		p.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));
		p.getInventory().setItem(8, new ItemStack(Material.LADDER, 10));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 0, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0, false, false));
	}

	@SuppressWarnings("deprecation")
	public void createHallebardier() {
		this.vocation = "Hallebardier";
		p.getInventory().clear();
		p.getInventory().setChestplate(core.createItem(Material.DIAMOND_CHESTPLATE, "§FDiamond Chestplate",
				Enchantment.THORNS, 1, Arrays.asList("")));
		p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		p.getInventory().setItem(0, core.createItem(Material.IRON_AXE, "§8La tueuse",
				Enchantment.DAMAGE_ALL, 2, Arrays.asList("")));
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
		p.setMaxHealth(30D);
		p.setHealth(30D);
		p.setWalkSpeed(0.1F);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 0, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0, false, false));
	}

	public void createRanger() {
		ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta lam = (LeatherArmorMeta) lchest.getItemMeta();
		lam.setColor(Color.GREEN);
		lchest.setItemMeta(lam);

		ItemStack llegs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta leam = (LeatherArmorMeta) llegs.getItemMeta();
		leam.setColor(Color.LIME);
		llegs.setItemMeta(leam);

		ItemStack lbo = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta labo = (LeatherArmorMeta) lbo.getItemMeta();
		labo.setColor(Color.GREEN);
		lbo.setItemMeta(labo);

		this.vocation = "Ranger";
		p.getInventory().clear();
		p.getInventory().setChestplate(lchest);
		p.getInventory().setLeggings(llegs);
		p.getInventory().setBoots(lbo);
		p.getInventory().setItem(0,
				core.createItem(Material.BOW, "§6Arc", Enchantment.ARROW_DAMAGE, 1, Arrays.asList("")));
		p.getInventory().setItem(1,
				core.createItem(Material.BOW, "§6Arc Rafale", Enchantment.ARROW_DAMAGE, 1, Arrays.asList("")));
		p.getInventory().setItem(2,
				core.createItem(Material.BOW, "§6Arc Volée", Enchantment.ARROW_DAMAGE, 1, Arrays.asList("")));
		p.getInventory().setItem(3, new ItemStack(Material.ARROW, 48));
		p.getInventory().setItem(4, core.createItem(Material.WOOD_SWORD, "§7Dague", null, 0, Arrays.asList("")));
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
	}

	public void createExecuteur() {
		ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta lam = (LeatherArmorMeta) lchest.getItemMeta();
		lam.setColor(Color.BLACK);
		lchest.setItemMeta(lam);

		ItemStack llegs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta leam = (LeatherArmorMeta) llegs.getItemMeta();
		leam.setColor(Color.GRAY);
		llegs.setItemMeta(leam);

		ItemStack lbo = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta labo = (LeatherArmorMeta) lbo.getItemMeta();
		labo.setColor(Color.BLACK);
		lbo.setItemMeta(labo);

		this.vocation = "Executeur";
		p.getInventory().clear();
		p.getInventory().setChestplate(lchest);
		p.getInventory().setLeggings(llegs);
		p.getInventory().setBoots(lbo);
		p.getInventory().setItem(0, core.createItem(Material.IRON_AXE, "§8Hache Maudite", null, 0, Arrays.asList("")));
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));

		
	}

	public void createMaceM() {
		ItemStack llegs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta leam = (LeatherArmorMeta) llegs.getItemMeta();
		leam.setColor(Color.ORANGE);
		llegs.setItemMeta(leam);

		this.vocation = "MaceMan";
		p.getInventory().clear();
		p.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setLeggings(llegs);
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		p.getInventory().setItem(0, core.createItem(Material.IRON_SPADE, "§8L'assomeuse", null, 0, Arrays.asList("")));
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
	}

	public void createMedecin() {
		ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta lam = (LeatherArmorMeta) lchest.getItemMeta();
		lam.setColor(Color.WHITE);
		lchest.setItemMeta(lam);

		ItemStack llegs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta leam = (LeatherArmorMeta) llegs.getItemMeta();
		leam.setColor(Color.GRAY);
		llegs.setItemMeta(leam);

		ItemStack lbo = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta labo = (LeatherArmorMeta) lbo.getItemMeta();
		labo.setColor(Color.WHITE);
		lbo.setItemMeta(labo);
		
		ItemStack itm = new ItemStack(Material.CAKE, 5);
		ItemMeta itmM = itm.getItemMeta();
		itmM.setDisplayName("§8Gâteaux de soins");
		itm.setItemMeta(itmM);

		this.vocation = "Medecin";
		p.getInventory().clear();
		p.getInventory().setChestplate(lchest);
		p.getInventory().setLeggings(llegs);
		p.getInventory().setBoots(lbo);
		p.getInventory().setItem(0, core.createItem(Material.WOOD_SPADE, "§8LA Pelle", null, 0, Arrays.asList("")));
		p.getInventory().setItem(2, core.createItem(Material.PAPER, "§8Bandages", null, 0, Arrays.asList("")));
		p.getInventory().setItem(3, itm);
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
	}

	public void createBrute() {
		ItemStack potion = new ItemStack(Material.POTION, 3);
		PotionMeta meta = (PotionMeta) potion.getItemMeta();
		meta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
		meta.setDisplayName("§8Potion Berserk");
		potion.setItemMeta(meta);

		this.vocation = "Brute";
		p.getInventory().clear();
		p.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));
		p.getInventory().setItem(1, potion);
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
	}

	public void createIngenieur() {
		ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta lam = (LeatherArmorMeta) lchest.getItemMeta();
		lam.setColor(Color.GRAY);
		lchest.setItemMeta(lam);

		ItemStack llegs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta leam = (LeatherArmorMeta) llegs.getItemMeta();
		leam.setColor(Color.GRAY);
		llegs.setItemMeta(leam);

		ItemStack lbo = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta labo = (LeatherArmorMeta) lbo.getItemMeta();
		labo.setColor(Color.GRAY);
		lbo.setItemMeta(labo);

		ItemStack trappes = new ItemStack(Material.STONE_PLATE, 8);
		ItemMeta m = trappes.getItemMeta();
		m.setDisplayName("§8Trappe");
		trappes.setItemMeta(m);

		this.vocation = "Ingenieur";
		p.getInventory().clear();
		p.getInventory().setChestplate(lchest);
		p.getInventory().setLeggings(llegs);
		p.getInventory().setBoots(lbo);
		p.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD));
		p.getInventory().setItem(2, trappes);
		p.getInventory().setItem(3, new ItemStack(Material.WEB, 12));
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
	}

	public void createArbaletrier() {
		ItemStack llegs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta leam = (LeatherArmorMeta) llegs.getItemMeta();
		leam.setColor(Color.YELLOW);
		llegs.setItemMeta(leam);

		ItemStack lbo = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta labo = (LeatherArmorMeta) lbo.getItemMeta();
		labo.setColor(Color.YELLOW);
		lbo.setItemMeta(labo);

		this.vocation = "Arbaletrier";
		p.getInventory().clear();
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(llegs);
		p.getInventory().setBoots(lbo);
		p.getInventory().setItem(0, core.createItem(Material.BOW, "§8Arbalète", null, 0, Arrays.asList("")));
		p.getInventory().setItem(1, new ItemStack(Material.ARROW,25));
		p.sendMessage("§6Attention, vous possèdez 25 flèches !");
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
	}

	public void createCavalier() {
		ItemStack lbo = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta labo = (LeatherArmorMeta) lbo.getItemMeta();
		labo.setColor(Color.BLACK);
		lbo.setItemMeta(labo);

		this.vocation = "Cavalier";
		p.getInventory().clear();
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		p.getInventory().setBoots(lbo);
		p.getInventory().setItem(0,
				core.createItem(Material.IRON_SWORD, "§8Lame légendaire", Enchantment.KNOCKBACK, 3, Arrays.asList("")));
		p.getInventory().setItem(1, new ItemStack(Material.SADDLE));
		p.getInventory().setItem(7, new ItemStack(Material.LADDER, 4));
		p.getInventory().setItem(8, new ItemStack(Material.COOKED_BEEF, 5));
	}

	public void createChien() {
		this.vocation = "Chien";
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp "+p.getName()+" iDisguise.mob.wolf");
		p.chat("/disguise wolf");
		p.getInventory().clear();
		p.getInventory().setItem(0, new ItemStack(Material.IRON_NUGGET));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0, false, false));
	}

	public void createSpadassin() {
		ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta lam = (LeatherArmorMeta) lchest.getItemMeta();
		lam.setColor(Color.BLUE);
		lchest.setItemMeta(lam);

		ItemStack lbo = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta labo = (LeatherArmorMeta) lbo.getItemMeta();
		labo.setColor(Color.BLUE);
		lbo.setItemMeta(labo);

		this.vocation = "Spadassin";
		p.getInventory().clear();
		p.getInventory().setChestplate(lchest);
		p.getInventory().setBoots(lbo);
		p.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));
		p.getInventory().setItem(1, new ItemStack(Material.FLINT, 5));
		p.getInventory().setItem(2, new ItemStack(Material.GOLD_HOE));
		p.getInventory().setItem(8, new ItemStack(Material.LADDER, 4));

		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0, false, false));
	}

	public String getVocation() {
		return vocation;
	}

	@SuppressWarnings("deprecation")
	public void delete() {
		p.getInventory().clear();
		if(p.hasPermission("iDisguise.mob.wolf")){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manudelp "+p.getName()+" iDisguise.mob.wolf");
			p.chat("/undisguise");
		}
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
		p.setMaxHealth(20.0D);
		p.setWalkSpeed(0.2F);
		core.vocations.remove(p);
	}
}
