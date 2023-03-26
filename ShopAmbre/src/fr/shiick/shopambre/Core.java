package fr.shiick.shopambre;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.shiick.shopambre.commands.achat;
import fr.shiick.shopambre.listeners.clickInventoryAgriculture;
import fr.shiick.shopambre.listeners.clickInventoryBlocs;
import fr.shiick.shopambre.listeners.clickInventoryBlocs2;
import fr.shiick.shopambre.listeners.clickInventoryCailloux;
import fr.shiick.shopambre.listeners.clickInventoryMain;
import fr.shiick.shopambre.listeners.clickInventoryMinerais;
import fr.shiick.shopambre.listeners.clickInventoryNether;

public class Core extends JavaPlugin {
	
	@Override
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new clickInventoryMain(this), this);
		pm.registerEvents(new clickInventoryBlocs(this), this);
		pm.registerEvents(new clickInventoryBlocs2(this), this);
		pm.registerEvents(new clickInventoryCailloux(this), this);
		pm.registerEvents(new clickInventoryMinerais(this), this);
		pm.registerEvents(new clickInventoryAgriculture(this), this);
		pm.registerEvents(new clickInventoryNether(this), this);
		getCommand("achat").setExecutor(new achat(this));
	}
	
	
	public void openMain(Player p){
		
		Inventory mainAchat = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop"));
		
		ItemStack grass = new ItemStack(Material.GRASS, 1);
		ItemMeta grassM = grass.getItemMeta();
		grassM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fBlocs"));
		grass.setItemMeta(grassM);

		ItemStack dirt = new ItemStack(Material.DIRT, 1);
		ItemMeta dirtM = dirt.getItemMeta();
		dirtM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fBlocs 2"));
		dirt.setItemMeta(dirtM);

		ItemStack cobble = new ItemStack(Material.COBBLESTONE, 1);
		ItemMeta cobbleM = cobble.getItemMeta();
		cobbleM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fCailloux"));
		cobble.setItemMeta(cobbleM);

		ItemStack ironingot = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta ironingotM = ironingot.getItemMeta();
		ironingotM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fMinerais"));
		ironingot.setItemMeta(ironingotM);
		
		ItemStack carrot = new ItemStack(Material.CARROT_ITEM, 1);
		ItemMeta carrotM = carrot.getItemMeta();
		carrotM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fAgriculture"));
		carrot.setItemMeta(carrotM);

		ItemStack netherrack = new ItemStack(Material.NETHERRACK, 1);
		ItemMeta netherrackM = netherrack.getItemMeta();
		netherrackM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fNether"));
		netherrack.setItemMeta(netherrackM);

		ItemStack egg = new ItemStack(Material.EGG, 1);
		ItemMeta eggM = egg.getItemMeta();
		eggM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fOeufs"));
		egg.setItemMeta(eggM);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		mainAchat.setItem(0, glass);
		mainAchat.setItem(1, grass);
		mainAchat.setItem(2, dirt);
		mainAchat.setItem(3, cobble);
		mainAchat.setItem(5, ironingot);
		mainAchat.setItem(6, carrot);
		mainAchat.setItem(4, netherrack);
		mainAchat.setItem(7, egg);
		mainAchat.setItem(8, glass);
		
		p.openInventory(mainAchat);
	}

	public void openBlock(Player p){
		
		Inventory blocsAchat = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fBlocs"));
		
		ItemStack ice = new ItemStack(Material.ICE, 16);
		ItemMeta iceM = ice.getItemMeta();
		iceM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		ice.setItemMeta(iceM);
		
		ItemStack packedIce = new ItemStack(Material.PACKED_ICE, 6);
		ItemMeta packedIceM = packedIce.getItemMeta();
		packedIceM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		packedIce.setItemMeta(packedIceM);
		
		ItemStack snow = new ItemStack(Material.SNOW_BLOCK, 4);
		ItemMeta snowM = snow.getItemMeta();
		snowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		snow.setItemMeta(snowM);
		
		ItemStack grass = new ItemStack(Material.GRASS, 1);
		ItemMeta grassM = grass.getItemMeta();
		grassM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Ambres"));
		grass.setItemMeta(grassM);
		
		ItemStack podzol = new ItemStack(Material.DIRT, (short) 1, (byte) 2);
		ItemMeta podzolM = podzol.getItemMeta();
		podzolM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&68 Ambres"));
		podzol.setItemMeta(podzolM);
		
		ItemStack mycel = new ItemStack(Material.MYCEL, 1);
		ItemMeta mycelM = mycel.getItemMeta();
		mycelM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&610 Ambres"));
		mycel.setItemMeta(mycelM);
		
		ItemStack sponge = new ItemStack(Material.SPONGE, 1);
		ItemMeta spongeM = sponge.getItemMeta();
		spongeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		sponge.setItemMeta(spongeM);
		
		ItemStack obsi = new ItemStack(Material.OBSIDIAN, 1);
		ItemMeta obsiM = obsi.getItemMeta();
		obsiM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Ambres"));
		obsi.setItemMeta(obsiM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
        arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(arrowM);
		
		blocsAchat.setItem(0, ice);
		blocsAchat.setItem(1, packedIce);
		blocsAchat.setItem(2, snow);
		blocsAchat.setItem(3, grass);
		blocsAchat.setItem(4, arrow);
		blocsAchat.setItem(5, podzol);
		blocsAchat.setItem(6, mycel);
		blocsAchat.setItem(7, sponge);
		blocsAchat.setItem(8, obsi);
		
		p.openInventory(blocsAchat);
	}
	
	public void openBlock2(Player p){
		
		Inventory blocs2Achat = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fBlocs 2"));
				
		ItemStack dirt = new ItemStack(Material.DIRT, 4);
		ItemMeta dirtM = dirt.getItemMeta();
		dirtM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		dirt.setItemMeta(dirtM);
		
		ItemStack sand = new ItemStack(Material.SAND, 4);
		ItemMeta sandM = sand.getItemMeta();
		sandM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		sand.setItemMeta(sandM);

		ItemStack redSand = new ItemStack(Material.SAND, 4, (byte) 1);
		ItemMeta redSandM = redSand.getItemMeta();
		redSandM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		redSand.setItemMeta(redSandM);
		
		ItemStack gravel = new ItemStack(Material.GRAVEL, 4);
		ItemMeta gravelM = gravel.getItemMeta();
		gravelM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		gravel.setItemMeta(gravelM);
		
		ItemStack clay = new ItemStack(Material.CLAY, 4);
		ItemMeta clayM = clay.getItemMeta();
		clayM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		clay.setItemMeta(clayM);
		
		ItemStack oak = new ItemStack(Material.LOG, 4);
		ItemMeta oakM = oak.getItemMeta();
		oakM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		oak.setItemMeta(oakM);
		
		ItemStack brick = new ItemStack(Material.BRICK, 4);
		ItemMeta brickM = brick.getItemMeta();
		brickM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		brick.setItemMeta(brickM);
		
		ItemStack enderstone = new ItemStack(Material.ENDER_STONE, 1);
		ItemMeta enderstoneM = enderstone.getItemMeta();
		enderstoneM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Ambres"));
		enderstone.setItemMeta(enderstoneM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
        arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(arrowM);
		
		blocs2Achat.setItem(0, dirt);
		blocs2Achat.setItem(1, sand);
		blocs2Achat.setItem(2, redSand);
		blocs2Achat.setItem(3, gravel);
		blocs2Achat.setItem(4, arrow);
		blocs2Achat.setItem(5, clay);
		blocs2Achat.setItem(6, oak);
		blocs2Achat.setItem(7, brick);
		blocs2Achat.setItem(8, enderstone);
		
		p.openInventory(blocs2Achat);
	}
	
	public void openCailloux(Player p){
		
		Inventory caillouxAchat = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fCailloux"));
		
		ItemStack cobble = new ItemStack(Material.COBBLESTONE, 16);
		ItemMeta cobbleM = cobble.getItemMeta();
		cobbleM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		cobble.setItemMeta(cobbleM);
		
		ItemStack andesite = new ItemStack(Material.STONE, 4, (byte)5);
		ItemMeta andesiteM = andesite.getItemMeta();
		andesiteM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		andesite.setItemMeta(andesiteM);
		
		ItemStack diorite = new ItemStack(Material.STONE, 4, (byte)3);
		ItemMeta dioriteM = diorite.getItemMeta();
		dioriteM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		diorite.setItemMeta(dioriteM);
		
		ItemStack granite = new ItemStack(Material.STONE, 4, (byte)1);
		ItemMeta graniteM = granite.getItemMeta();
		graniteM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		granite.setItemMeta(graniteM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
        arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(arrowM);
		
		caillouxAchat.setItem(1, cobble);
		caillouxAchat.setItem(3, andesite);
		caillouxAchat.setItem(4, arrow);
		caillouxAchat.setItem(5, diorite);
		caillouxAchat.setItem(7, granite);
		
		p.openInventory(caillouxAchat);
		
	}
	
	public void openMinerais(Player p){
		
		Inventory blocsMinerais = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fMinerais"));
		
		ItemStack redstone = new ItemStack(Material.REDSTONE, 8);
		ItemMeta redstoneM = redstone.getItemMeta();
		redstoneM.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&61 Ambre")); 
		redstone.setItemMeta(redstoneM);
		
		ItemStack coal = new ItemStack(Material.COAL, 4);
		ItemMeta coalM = coal.getItemMeta(); 
		coalM.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&61 Ambre"));
		coal.setItemMeta(coalM);		
		
		ItemStack quartz = new ItemStack(Material.QUARTZ, 4);
		ItemMeta quartzM = quartz.getItemMeta();
		quartzM.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&61 Ambre"));
		quartz.setItemMeta(quartzM);
		
		Dye lapisD = new Dye();
		lapisD.setColor(DyeColor.BLUE);
		ItemStack lapis = lapisD.toItemStack(3);
		ItemMeta lapisM = lapis.getItemMeta();
		lapisM.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&62 Ambres"));
		lapis.setItemMeta(lapisM);
		
		ItemStack ironingot = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta ironingotM = ironingot.getItemMeta();
		ironingotM.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&68 Ambres"));
		ironingot.setItemMeta(ironingotM);
		
		ItemStack diamant = new ItemStack(Material.DIAMOND, 1); 
		ItemMeta diamantM = diamant.getItemMeta();
		diamantM.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&616 Ambres"));
		diamant.setItemMeta(diamantM);
		
		ItemStack goldingot = new ItemStack(Material.GOLD_INGOT, 1); 
		ItemMeta goldingotM = goldingot.getItemMeta();
		goldingotM.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&64 Ambres"));
		goldingot.setItemMeta(goldingotM);
		
		ItemStack emerald = new ItemStack(Material.EMERALD, 1); 
		ItemMeta emeraldM = goldingot.getItemMeta();
		emeraldM.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&64 ambres"));
		emerald.setItemMeta(emeraldM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
        arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(arrowM);
		
		blocsMinerais.setItem(0, redstone);
		blocsMinerais.setItem(1, coal);
		blocsMinerais.setItem(2, quartz);
		blocsMinerais.setItem(3, lapis);
		blocsMinerais.setItem(4, arrow);
		blocsMinerais.setItem(5, ironingot);
		blocsMinerais.setItem(6, diamant);
		blocsMinerais.setItem(7, goldingot);
		blocsMinerais.setItem(8, emerald);
		
		p.openInventory(blocsMinerais);
		
	}
	
	public void openAgriculture(Player p){
		
		Inventory agricultureAchat = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fAgriculture"));
				
		ItemStack carrot = new ItemStack(Material.CARROT_ITEM, 1);
		ItemMeta carrotM = carrot.getItemMeta();
		carrotM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		carrot.setItemMeta(carrotM);
		
		ItemStack potato = new ItemStack(Material.POTATO_ITEM, 1);
		ItemMeta potatoM = potato.getItemMeta();
		potatoM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		potato.setItemMeta(potatoM);
		
		ItemStack seeds = new ItemStack(Material.SEEDS, 1);
		ItemMeta seedsM = seeds.getItemMeta();
		seedsM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		seeds.setItemMeta(seedsM);
		
		ItemStack sugarcane = new ItemStack(Material.SUGAR_CANE, 1);
		ItemMeta sugarcaneM = sugarcane.getItemMeta();
		sugarcaneM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		sugarcane.setItemMeta(sugarcaneM);
		
		ItemStack melon = new ItemStack(Material.MELON_SEEDS, 1);
		ItemMeta melonM = melon.getItemMeta();
		melonM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		melon.setItemMeta(melonM);
		
		ItemStack pumpkin = new ItemStack(Material.PUMPKIN_SEEDS, 1);
		ItemMeta pumpkinM = pumpkin.getItemMeta();
		pumpkinM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		pumpkin.setItemMeta(pumpkinM);
		
		ItemStack cactus = new ItemStack(Material.CACTUS, 1);
		ItemMeta cactusM = cactus.getItemMeta();
		cactusM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		cactus.setItemMeta(cactusM);
		
		Dye cocoaD = new Dye();
		cocoaD.setColor(DyeColor.BROWN);
		ItemStack cocoa = cocoaD.toItemStack(1);
		ItemMeta cocoaM = cocoa.getItemMeta();
		cocoaM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		cocoa.setItemMeta(cocoaM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
        arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(arrowM);
		
		agricultureAchat.setItem(0, carrot);
		agricultureAchat.setItem(1, potato);
		agricultureAchat.setItem(2, seeds);
		agricultureAchat.setItem(3, sugarcane);
		agricultureAchat.setItem(4, arrow);
		agricultureAchat.setItem(5, melon);
		agricultureAchat.setItem(6, pumpkin);
		agricultureAchat.setItem(7, cactus);
		agricultureAchat.setItem(8, cocoa);
		
		p.openInventory(agricultureAchat); 
	}
	
	public void openNether(Player p){
		
		Inventory netherAchat = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fNether"));
		
		ItemStack netherrack = new ItemStack(Material.NETHERRACK, 32);
		ItemMeta netherrackM = netherrack.getItemMeta();
		netherrackM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		netherrack.setItemMeta(netherrackM);
		
		ItemStack netherbrick = new ItemStack(Material.NETHER_BRICK, 8);
		ItemMeta netherbrickM = netherbrick.getItemMeta();
		netherbrickM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		netherbrick.setItemMeta(netherbrickM);
		
		ItemStack quartz = new ItemStack(Material.QUARTZ_BLOCK, 4);
		ItemMeta quartzM = quartz.getItemMeta();
		quartzM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		quartz.setItemMeta(quartzM);
		
		ItemStack soulsand = new ItemStack(Material.SOUL_SAND, 4);
		ItemMeta soulsandM = soulsand.getItemMeta();
		soulsandM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		soulsand.setItemMeta(soulsandM);
		
		ItemStack glowstone = new ItemStack(Material.GLOWSTONE, 2);
		ItemMeta glowstoneM = glowstone.getItemMeta();
		glowstoneM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		glowstone.setItemMeta(glowstoneM);
		
		ItemStack netherwart = new ItemStack(Material.NETHER_STALK, 1);
		ItemMeta netherwartM = netherwart.getItemMeta();
		netherwartM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&63 Ambres"));
		netherwart.setItemMeta(netherwartM);
		
		ItemStack blazerod = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta blazerodM = blazerod.getItemMeta();
		blazerodM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Ambres"));
		blazerod.setItemMeta(blazerodM);
		
		ItemStack ghasttear = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta ghasttearM = ghasttear.getItemMeta();
		ghasttearM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&632 Ambres"));
		ghasttear.setItemMeta(ghasttearM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
        arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(arrowM);
		
		netherAchat.setItem(0, netherrack);
		netherAchat.setItem(1, netherbrick);
		netherAchat.setItem(2, quartz);
		netherAchat.setItem(3, soulsand);
		netherAchat.setItem(4, arrow);
		netherAchat.setItem(5, glowstone);
		netherAchat.setItem(6, netherwart);
		netherAchat.setItem(7, blazerod);
		netherAchat.setItem(8, ghasttear);
		
		p.openInventory(netherAchat); 
		
	}
	
	public void openEggs(Player p){
		
		Inventory eggsAchat = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fOeufs"));
		
		ItemStack pig = new ItemStack(Material.MONSTER_EGG, 1, (byte) 90);
		ItemMeta pigM = pig.getItemMeta();
		pigM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Topaze"));
		pig.setItemMeta(pigM);
		
		ItemStack cow = new ItemStack(Material.MONSTER_EGG, 1, (byte) 92);
		ItemMeta cowM = cow.getItemMeta();
		cowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Topaze"));
		cow.setItemMeta(cowM);
		
		ItemStack sheep = new ItemStack(Material.MONSTER_EGG, 1, (byte) 91);
		ItemMeta sheepM = sheep.getItemMeta();
		sheepM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Topaze"));
		sheep.setItemMeta(sheepM);
		
		ItemStack chicken = new ItemStack(Material.MONSTER_EGG, 1, (byte) 93);
		ItemMeta chickenM = chicken.getItemMeta();
		chickenM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Topaze"));
		chicken.setItemMeta(chickenM);
		
		ItemStack wolf = new ItemStack(Material.MONSTER_EGG, 1, (byte) 95);
		ItemMeta wolfM = wolf.getItemMeta();
		wolfM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Topaze"));
		wolf.setItemMeta(wolfM);
		
		ItemStack rabbit = new ItemStack(Material.MONSTER_EGG, 1, (byte) 101);
		ItemMeta rabbitM = cow.getItemMeta();
		rabbitM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Topaze"));
		rabbit.setItemMeta(rabbitM);
		
		ItemStack mushroom = new ItemStack(Material.MONSTER_EGG, 1, (byte) 96);
		ItemMeta mushroomM = mushroom.getItemMeta();
		mushroomM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Topazes"));
		mushroom.setItemMeta(mushroomM);
		
		ItemStack horse = new ItemStack(Material.MONSTER_EGG, 1, (byte) 100);
		ItemMeta horseM = horse.getItemMeta();
		horseM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Topazes"));
		horse.setItemMeta(horseM);
		
		eggsAchat.setItem(0, pig);
		eggsAchat.setItem(1, cow);
		eggsAchat.setItem(2, sheep);
		eggsAchat.setItem(3, chicken);
		eggsAchat.setItem(5, wolf);
		eggsAchat.setItem(6, rabbit);
		eggsAchat.setItem(7, mushroom);
		eggsAchat.setItem(8, horse);
		
		p.openInventory(eggsAchat); 
	}
	
}
