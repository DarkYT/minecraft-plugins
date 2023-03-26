package fr.shiick.shopambre;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.shiick.shopambre.commands.achat;
import fr.shiick.shopambre.listeners.clickInventoryAgriculture;
import fr.shiick.shopambre.listeners.clickInventoryBlocs;
import fr.shiick.shopambre.listeners.clickInventoryBlocs2;
import fr.shiick.shopambre.listeners.clickInventoryCailloux;
import fr.shiick.shopambre.listeners.clickInventoryChasseur;
import fr.shiick.shopambre.listeners.clickInventoryCollection;
import fr.shiick.shopambre.listeners.clickInventoryConverter;
import fr.shiick.shopambre.listeners.clickInventoryCorrompu;
import fr.shiick.shopambre.listeners.clickInventoryDemon;
import fr.shiick.shopambre.listeners.clickInventoryEggs;
import fr.shiick.shopambre.listeners.clickInventoryEnchants;
import fr.shiick.shopambre.listeners.clickInventoryJardinier;
import fr.shiick.shopambre.listeners.clickInventoryMain;
import fr.shiick.shopambre.listeners.clickInventoryMinerais;
import fr.shiick.shopambre.listeners.clickInventoryNether;
import fr.shiick.shopambre.listeners.clickInventoryRares;
import fr.shiick.shopambre.listeners.clickInventoryVampire;
import fr.shiick.shopambre.listeners.clickInventoryWarps;

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
		pm.registerEvents(new clickInventoryConverter(this), this);
		pm.registerEvents(new clickInventoryEggs(this), this );
		pm.registerEvents(new clickInventoryEnchants(this), this);
		pm.registerEvents(new clickInventoryJardinier(this), this);
		pm.registerEvents(new clickInventoryCollection(this), this);
		pm.registerEvents(new clickInventoryChasseur(this), this);
		pm.registerEvents(new clickInventoryWarps(this), this);
		pm.registerEvents(new clickInventoryDemon(this), this);
		pm.registerEvents(new clickInventoryCorrompu(this), this);
		pm.registerEvents(new clickInventoryVampire(this), this);
		pm.registerEvents(new clickInventoryRares(this), this);
		getCommand("achat").setExecutor(new achat(this));
	}
	
	public ItemStack addBookEnchantment(ItemStack item, Enchantment enchantment, int level){
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }

	
	
	public void openMain(Player p){
		
		Inventory mainAchat = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', "&6Shop"));
		
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
		glassM.setDisplayName("§6Shop");
		glass.setItemMeta(glassM);
		
		ItemStack glassGQ = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte)4);
		ItemMeta glassGQM = glassGQ.getItemMeta();
		glassGQM.setDisplayName("§6Shop §fGrosses Quantités");
		glassGQ.setItemMeta(glassGQM);
		
		ItemStack glassE = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte)14);
		ItemMeta glassEM = glassE.getItemMeta();
		glassEM.setDisplayName("§6Shop §fHiver Nucléaire");
		glassE.setItemMeta(glassEM);
		
		ItemStack ambre = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta ambreM = ambre.getItemMeta();
		ambreM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Convertisseur"));
		ambre.setItemMeta(ambreM);
		
		ItemStack book1 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta book1M = book1.getItemMeta();
		book1M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fEnchants"));
		book1.setItemMeta(book1M);
		
		ItemStack leaves = new ItemStack(Material.LEAVES, 1);
		ItemMeta leavesM = leaves.getItemMeta();
		leavesM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fJardinier"));
		leaves.setItemMeta(leavesM);
		
		ItemStack xp = new ItemStack(Material.EXP_BOTTLE, 1);
		ItemMeta xpM = xp.getItemMeta();
		xpM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fCollection"));
		xp.setItemMeta(xpM);
		
		ItemStack rotten = new ItemStack(Material.ROTTEN_FLESH, 1);
		ItemMeta rottenM = rotten.getItemMeta();
		rottenM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fChasseur"));
		rotten.setItemMeta(rottenM);
		
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta helmetM = helmet.getItemMeta();
		helmetM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fGrades"));
		helmet.setItemMeta(helmetM);
		
		ItemStack elytra = new ItemStack(Material.ELYTRA, 1);
		ItemMeta elytraM = elytra.getItemMeta();
		elytraM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fObjets Rares"));
		elytra.setItemMeta(elytraM);

		
		mainAchat.setItem(0, glass);
		mainAchat.setItem(1, grass);
		mainAchat.setItem(2, dirt);
		mainAchat.setItem(3, cobble);
		mainAchat.setItem(5, ironingot);
		mainAchat.setItem(6, carrot);
		mainAchat.setItem(4, netherrack);
		mainAchat.setItem(7, egg);
		mainAchat.setItem(8, glass);
		mainAchat.setItem(9, glass);
		mainAchat.setItem(10, book1);
		mainAchat.setItem(11, leaves);
		mainAchat.setItem(12, helmet);
		mainAchat.setItem(13, ambre);
		mainAchat.setItem(14, xp);
		mainAchat.setItem(15, rotten);
		mainAchat.setItem(16, elytra);
		mainAchat.setItem(17, glass);
		
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
		lapisM.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&61 Ambre"));
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
		
		ItemStack netherrack = new ItemStack(Material.NETHERRACK, 64);
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
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
        arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(arrowM);
		
		eggsAchat.setItem(0, pig);
		eggsAchat.setItem(1, cow);
		eggsAchat.setItem(2, sheep);
		eggsAchat.setItem(3, chicken);
		eggsAchat.setItem(4, arrow);
		eggsAchat.setItem(5, wolf);
		eggsAchat.setItem(6, rabbit);
		eggsAchat.setItem(7, mushroom);
		eggsAchat.setItem(8, horse);
		
		p.openInventory(eggsAchat); 
	}
	
	public void openConvert(Player p){
		Inventory convert = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Convertisseur"));
		
		ItemStack ambreT = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta ambreTM = ambreT.getItemMeta();
		ambreTM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Convertir 9 ambres en 1 topaze"));
		ambreTM.addEnchant(Enchantment.DURABILITY, 1, true);
		ambreTM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		ambreT.setItemMeta(ambreTM);
		
		ItemStack topazeA = new ItemStack(Material.DOUBLE_PLANT, 9);
		ItemMeta topazeAM = topazeA.getItemMeta();
		topazeAM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Convertir 1 topaze en 9 ambres"));
		topazeA.setItemMeta(topazeAM);
		
		ItemStack topazeP = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta topazePM = topazeP.getItemMeta();
		topazePM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Convertir 9 topazes en 1 platine"));
		topazeP.setItemMeta(topazePM);
		
		ItemStack platineT = new ItemStack(Material.DOUBLE_PLANT, 9);
		ItemMeta platineTM = platineT.getItemMeta();
		platineTM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Convertir 1 platine en 9 topazes"));
		platineTM.addEnchant(Enchantment.DURABILITY, 1, true);
		platineTM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		platineT.setItemMeta(platineTM);

		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
        arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(arrowM);
        
        convert.setItem(0, glass);
        convert.setItem(1, ambreT);
        convert.setItem(2, glass);
        convert.setItem(3, topazeA);
        convert.setItem(4, arrow);
        convert.setItem(5, topazeP);
        convert.setItem(6, glass);
        convert.setItem(7, platineT);
        convert.setItem(8, glass);
		
		p.openInventory(convert);
	}
	
	public void openEnchant(Player p){
		Inventory enchant = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fEnchants"));
		
		ItemStack book1 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta book1M = book1.getItemMeta();
		book1M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Topazes"));
		book1M.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Power 1")));
		book1.setItemMeta(book1M);
		
		ItemStack book2 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta book2M = book2.getItemMeta();
		book2M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Topazes"));
		book2M.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Sharpness 1")));
		book2.setItemMeta(book2M);
		
		ItemStack book3 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta book3M = book3.getItemMeta();
		book3M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Topazes"));
		book3M.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Efficiency 1")));
		book3.setItemMeta(book3M);
		
		ItemStack book4 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta book4M = book4.getItemMeta();
		book4M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Topazes"));
		book4M.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Protection 1")));
		book4.setItemMeta(book4M);
		
		ItemStack book5 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta book5M = book5.getItemMeta();
		book5M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&66 Topazes"));
		book5M.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Unbreaking 1")));
		book5.setItemMeta(book5M);
		
		ItemStack book6 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta book6M = book6.getItemMeta();
		book6M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&68 Topazes"));
		book6M.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Looting 1")));
		book6.setItemMeta(book6M);
		
		ItemStack book7 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta book7M = book7.getItemMeta();
		book7M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&68 Topazes"));
		book7M.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Feather Falling 1")));
		book7.setItemMeta(book7M);
		
		ItemStack book8 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta book8M = book8.getItemMeta();
		book8M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&664 Topazes"));
		book8M.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Mending")));
		book8.setItemMeta(book8M);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
        arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(arrowM);
        
        enchant.setItem(0, book1);
        enchant.setItem(1, book2);
        enchant.setItem(2, book3);
        enchant.setItem(3, book4);
        enchant.setItem(4, arrow);
        enchant.setItem(5, book5);
        enchant.setItem(6, book6);
        enchant.setItem(7, book7);
        enchant.setItem(8, book8);
        
        p.openInventory(enchant);
	}
	
	public void openJardinier(Player p){
		Inventory jardinier = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fJardinier"));
		
		ItemStack lava = new ItemStack(Material.LAVA_BUCKET, 1);
		ItemMeta lavaM = lava.getItemMeta();
		lavaM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		lava.setItemMeta(lavaM);
		
		ItemStack oak = new ItemStack(Material.SAPLING, 1);
		ItemMeta oakM = oak.getItemMeta();
		oakM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambres"));
		oak.setItemMeta(oakM);
		
		ItemStack spruce = new ItemStack(Material.SAPLING, 1,(byte)1);
		ItemMeta spruceM = spruce.getItemMeta();
		spruceM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambres"));
		spruce.setItemMeta(spruceM);
		
		ItemStack birch = new ItemStack(Material.SAPLING, 1,(byte)2);
		ItemMeta birchM = birch.getItemMeta();
		birchM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		birch.setItemMeta(birchM);
		
		ItemStack jungle = new ItemStack(Material.SAPLING, 1,(byte)3);
		ItemMeta jungleM = jungle.getItemMeta();
		jungleM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Ambres"));
		jungle.setItemMeta(jungleM);
		
		ItemStack acacia = new ItemStack(Material.SAPLING, 1,(byte)4);
		ItemMeta acaciaM = acacia.getItemMeta();
		acaciaM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&68 Ambres"));
		acacia.setItemMeta(acaciaM);
		
		ItemStack darkoak = new ItemStack(Material.SAPLING, 1,(byte)5);
		ItemMeta darkoakM = darkoak.getItemMeta();
		darkoakM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&616 Ambres"));
		darkoak.setItemMeta(darkoakM);
		
		ItemStack meal = new ItemStack(Material.INK_SACK, 64,(byte)15);
		ItemMeta mealM = meal.getItemMeta();
		mealM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		meal.setItemMeta(mealM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
        arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(arrowM);
        
        jardinier.setItem(0, lava);
        jardinier.setItem(1, oak);
        jardinier.setItem(2, spruce);
        jardinier.setItem(3, birch);
        jardinier.setItem(4, arrow);
        jardinier.setItem(5, jungle);
        jardinier.setItem(6, acacia);
        jardinier.setItem(7, darkoak);
        jardinier.setItem(8, meal);
        
        p.openInventory(jardinier);
		
	}
	
	public void openCollection(Player p){
		Inventory collection = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fCollection"));
		
		ItemStack sea = new ItemStack(Material.SEA_LANTERN, 1);
		ItemMeta seaM = sea.getItemMeta();
		seaM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Ambres"));
		sea.setItemMeta(seaM);
		
		ItemStack wool = new ItemStack(Material.WOOL, 4);
		ItemMeta woolM = wool.getItemMeta();
		woolM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		wool.setItemMeta(woolM);
		
		ItemStack saddle = new ItemStack(Material.SADDLE, 1);
		ItemMeta saddleM = saddle.getItemMeta();
		saddleM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&664 Ambres"));
		saddle.setItemMeta(saddleM);
		
		ItemStack xp = new ItemStack(Material.EXP_BOTTLE, 4);
		ItemMeta xpM = xp.getItemMeta();
		xpM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		xp.setItemMeta(xpM);
		
		ItemStack anvil = new ItemStack(Material.ANVIL, 1);
		ItemMeta anvilM = anvil.getItemMeta();
		anvilM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6128 Ambres"));
		anvil.setItemMeta(anvilM);
		
		ItemStack slime = new ItemStack(Material.SLIME_BALL, 4);
		ItemMeta slimeM = slime.getItemMeta();
		slimeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&68 Ambres"));
		slime.setItemMeta(slimeM);
		
		ItemStack ec = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta ecM = ec.getItemMeta();
		ecM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&664 Ambres"));
		ec.setItemMeta(ecM);
		
		ItemStack beetroot = new ItemStack(Material.BEETROOT_SEEDS, 1);
		ItemMeta beetrootM = beetroot.getItemMeta();
		beetrootM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		beetroot.setItemMeta(beetrootM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
        arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow.setItemMeta(arrowM);
        
        collection.setItem(0, sea);
        collection.setItem(1, wool);
        collection.setItem(2, saddle);
        collection.setItem(3, xp);
        collection.setItem(4, arrow);
        collection.setItem(5, anvil);
        collection.setItem(6, slime);
        collection.setItem(7, ec);
        collection.setItem(8, beetroot);
        
        p.openInventory(collection);
	}
	
	public void openChasseur(Player p){
		Inventory chasseur = Bukkit.createInventory(null,  9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fChasseur"));
		
		ItemStack eyes = new ItemStack(Material.SPIDER_EYE, 2);
		ItemMeta eyesM = eyes.getItemMeta();
		eyesM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		eyes.setItemMeta(eyesM);
		
		ItemStack rotten = new ItemStack(Material.ROTTEN_FLESH, 8);
		ItemMeta rottenM = rotten.getItemMeta();
		rottenM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		rotten.setItemMeta(rottenM);
		
		ItemStack creeper = new ItemStack(Material.SULPHUR, 8);
		ItemMeta creeperM = creeper.getItemMeta();
		creeperM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		creeper.setItemMeta(creeperM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 8);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		arrow.setItemMeta(arrowM);
		
		ItemStack string = new ItemStack(Material.STRING, 8);
		ItemMeta stringM = string.getItemMeta();
		stringM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		string.setItemMeta(stringM);
		
		ItemStack bone = new ItemStack(Material.BONE, 8);
		ItemMeta boneM = bone.getItemMeta();
		boneM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		bone.setItemMeta(boneM);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		ItemStack arrow1 = new ItemStack(Material.ARROW, 1);
		ItemMeta arrow1M = arrow1.getItemMeta();
		arrow1M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrow1M.addEnchant(Enchantment.DURABILITY, 1, true);
        arrow1M.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        arrow1.setItemMeta(arrow1M);
        
        chasseur.setItem(0, glass);
        chasseur.setItem(1, eyes);
        chasseur.setItem(2, rotten);
        chasseur.setItem(3, creeper);
        chasseur.setItem(4, arrow1);
        chasseur.setItem(5, string);
        chasseur.setItem(6, bone);
        chasseur.setItem(7, arrow);
        chasseur.setItem(8, glass);
        
        p.openInventory(chasseur);
	}
	
	public void openWarps(Player p){

		
		ItemStack diamond = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta diamondM = diamond.getItemMeta();
		diamondM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Warp &fDémon"));
		diamond.setItemMeta(diamondM);
		
		ItemStack iron = new ItemStack(Material.IRON_HELMET, 1);
		ItemMeta ironM = iron.getItemMeta();
		ironM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Warp &fCorrompu"));
		iron.setItemMeta(ironM);
		
		ItemStack gold = new ItemStack(Material.GOLD_HELMET, 1);
		ItemMeta goldM = gold.getItemMeta();
		goldM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Warp &fVampire"));
		gold.setItemMeta(goldM);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);
        
		if(p.hasPermission("essentials.kits.demon")){
			Inventory warps = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', "&6Shop &fWarps"));
			
	        warps.setItem(0, glass);
	        warps.setItem(1, iron);
	        warps.setItem(4, diamond);
	        warps.setItem(7, gold);
	        warps.setItem(8, glass);
	        warps.setItem(9, glass);
	        warps.setItem(13, arrow);
	        warps.setItem(17, glass);
	        
	        p.openInventory(warps);
		}else if(p.hasPermission("essentials.kits.vampire")){
			Inventory warps2 = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fWarps"));
			
			warps2.setItem(0, glass);
			warps2.setItem(1, iron);
			warps2.setItem(4, arrow);
			warps2.setItem(7, gold);
			warps2.setItem(8, glass);
	        
	        p.openInventory(warps2);
		}else if(p.hasPermission("essentials.kits.corrompu")){
			Inventory warps3 = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fWarps"));
			
			warps3.setItem(0, glass);
			warps3.setItem(4, iron);
			warps3.setItem(5, arrow);
			warps3.setItem(8, glass);
	        
	        p.openInventory(warps3);
		}

	}
	
	public void openDemon(Player p){
		Inventory demon = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fDémon"));
		
		ItemStack ores = new ItemStack(Material.COAL_ORE, 1);
		ItemMeta oresM = ores.getItemMeta();
		oresM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fMinerais"));
		ores.setItemMeta(oresM);
		
		ItemStack rich = new ItemStack(Material.DIAMOND_ORE, 1);
		ItemMeta richM = rich.getItemMeta();
		richM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fRare"));
		rich.setItemMeta(richM);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);
		
		demon.setItem(0, glass);
		demon.setItem(2, ores);
		demon.setItem(4, arrow);
		demon.setItem(6, rich);
		demon.setItem(8, glass);
		
		p.openInventory(demon);
	}
	
	public void openVampire(Player p){
		Inventory vampire = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fVampire"));
		
		ItemStack flower = new ItemStack(Material.RED_ROSE, 1);
		ItemMeta flowerM = flower.getItemMeta();
		flowerM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fFleurs"));
		flower.setItemMeta(flowerM);
		
		ItemStack end = new ItemStack(Material.ENDER_STONE, 1);
		ItemMeta endM = end.getItemMeta();
		endM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fEnder"));
		end.setItemMeta(endM);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);
		
		vampire.setItem(0, glass);
		vampire.setItem(2, flower);
		vampire.setItem(4, arrow);
		vampire.setItem(6, end);
		vampire.setItem(8, glass);
		
		p.openInventory(vampire);
	}
	
	public void openCorrompu(Player p){
		Inventory corrompu = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fCorrompu"));
		
		ItemStack aqua = new ItemStack(Material.SEA_LANTERN, 1);
		ItemMeta aquaM = aqua.getItemMeta();
		aquaM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fMarin"));
		aqua.setItemMeta(aquaM);
		
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
		ItemMeta headM = head.getItemMeta();
		headM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Shop &fTêtes"));
		head.setItemMeta(headM);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);
		
		corrompu.setItem(0, glass);
		corrompu.setItem(2, aqua);
		corrompu.setItem(4, arrow);
		corrompu.setItem(6, head);
		corrompu.setItem(8, glass);
		
		p.openInventory(corrompu);
	}
	
	public void openOres(Player p){
		Inventory ores = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fMinerais Démon"));
		
		ItemStack coal = new ItemStack(Material.COAL_ORE, 1);
		ItemMeta coalM = coal.getItemMeta();
		coalM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&68 Topazes"));
		coal.setItemMeta(coalM);
		
		ItemStack iron = new ItemStack(Material.IRON_ORE, 1);
		ItemMeta ironM = iron.getItemMeta();
		ironM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&68 Topazes"));
		iron.setItemMeta(ironM);
		
		ItemStack gold = new ItemStack(Material.GOLD_ORE, 1);
		ItemMeta goldM = gold.getItemMeta();
		goldM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&616 Topazes"));
		gold.setItemMeta(goldM);
		
		ItemStack lapis = new ItemStack(Material.LAPIS_ORE, 1);
		ItemMeta lapisM = lapis.getItemMeta();
		lapisM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&616 Topazes"));
		lapis.setItemMeta(lapisM);
		
		ItemStack redstone = new ItemStack(Material.REDSTONE_ORE, 1);
		ItemMeta redstoneM = redstone.getItemMeta();
		redstoneM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&616 Topazes"));
		redstone.setItemMeta(redstoneM);
		
		ItemStack emerald = new ItemStack(Material.EMERALD_ORE, 1);
		ItemMeta emeraldM = emerald.getItemMeta();
		emeraldM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&616 Topazes"));
		emerald.setItemMeta(emeraldM);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);
		
		ores.setItem(0, glass);
		ores.setItem(1, coal);
		ores.setItem(2, iron);
		ores.setItem(3, gold);
		ores.setItem(4, arrow);
		ores.setItem(5, lapis);
		ores.setItem(6, redstone);
		ores.setItem(7, emerald);
		ores.setItem(8, glass);
		
		p.openInventory(ores);
	}
	
	public void openRares(Player p){
		Inventory rares = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fRare"));
		
		ItemStack diamond = new ItemStack(Material.DIAMOND_ORE, 1);
		ItemMeta diamondM = diamond.getItemMeta();
		diamondM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&624 Topazes"));
		diamond.setItemMeta(diamondM);
		
		ItemStack villager = new ItemStack(Material.MONSTER_EGG, 1,(short)120);
		ItemMeta villagerM = villager.getItemMeta();
		villagerM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&632 Topazes"));
		villager.setItemMeta(villagerM);

		
		ItemStack egg = new ItemStack(Material.MONSTER_EGG, 1);
		ItemMeta eggM = egg.getItemMeta();
		eggM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&632 Platines"));
		egg.setItemMeta(eggM);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);
		
		rares.setItem(0, glass);
		rares.setItem(4, diamond);
		rares.setItem(5, arrow);
		rares.setItem(8, glass);
	
		p.openInventory(rares);
	}
	
	public void openMarin(Player p){
		Inventory marin = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fMarin"));
		
		ItemStack prismarine = new ItemStack(Material.PRISMARINE, 1);
		ItemMeta prismarineM = prismarine.getItemMeta();
		prismarineM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Ambres"));
		prismarine.setItemMeta(prismarineM);
		
		ItemStack pDark = new ItemStack(Material.PRISMARINE, 1,(byte)2);
		ItemMeta pDarkM = pDark.getItemMeta();
		pDarkM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Ambres"));
		pDark.setItemMeta(pDarkM);
		
		ItemStack pBrick = new ItemStack(Material.PRISMARINE, 1,(byte)1);
		ItemMeta pBrickM = pBrick.getItemMeta();
		pBrickM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&64 Ambres"));
		pBrick.setItemMeta(pBrickM);
		
		ItemStack cobweb = new ItemStack(Material.WEB, 1);
		ItemMeta cobwebM = cobweb.getItemMeta();
		cobwebM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&68 Ambres"));
		cobweb.setItemMeta(cobwebM);
		
		ItemStack ink = new ItemStack(Material.INK_SACK, 1);
		ItemMeta inkM = ink.getItemMeta();
		inkM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&616 Ambres"));
		ink.setItemMeta(inkM);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);
		
		marin.setItem(0, glass);
		marin.setItem(1, prismarine);
		marin.setItem(3, pBrick);
		marin.setItem(4, arrow);
		marin.setItem(5, pDark);
		marin.setItem(7, cobweb);
		marin.setItem(8, glass);
		
		p.openInventory(marin); 
	}
	
	public void openHead(Player p){
		Inventory skull = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fTêtes"));
		
		ItemStack diamond = new ItemStack(Material.DIAMOND_ORE, 1);
		ItemMeta diamondM = diamond.getItemMeta();
		diamondM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&628 Topazes"));
		diamond.setItemMeta(diamondM);
		
		ItemStack dragon = new ItemStack(Material.SKULL_ITEM, 1,(byte) 5);
		ItemMeta dragonM = dragon.getItemMeta();
		dragonM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&632 Platines"));
		dragon.setItemMeta(dragonM);
		
		ItemStack skeleton = new ItemStack(Material.SKULL_ITEM, 1);
		ItemMeta skeletonM = skeleton.getItemMeta();
		skeletonM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Topaze"));
		skeleton.setItemMeta(skeletonM);
		
		ItemStack zombie = new ItemStack(Material.SKULL_ITEM, 1,(byte) 2);
		ItemMeta zombieM = zombie.getItemMeta();
		zombieM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Topaze"));
		zombie.setItemMeta(zombieM);
		
		ItemStack creeper = new ItemStack(Material.SKULL_ITEM, 1,(byte) 4);
		ItemMeta creeperM = creeper.getItemMeta();
		creeperM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Topaze"));
		creeper.setItemMeta(creeperM);
		
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1,(byte) 3);
		ItemMeta headM = head.getItemMeta();
		headM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Topaze"));
		head.setItemMeta(headM);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);
		
		skull.setItem(0, glass);
		skull.setItem(1, diamond);
		skull.setItem(2, dragon);
		skull.setItem(3, skeleton);
		skull.setItem(4, arrow);
		skull.setItem(5, zombie);
		skull.setItem(6, creeper);
		skull.setItem(7, head);
		skull.setItem(8, glass);
		
		p.openInventory(skull);
	}
	
	public void openFlower(Player p){
		Inventory flowers = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fFleurs"));
		
		ItemStack blue = new ItemStack(Material.RED_ROSE, 4,(byte)1);
		ItemMeta blueM = blue.getItemMeta();
		blueM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		blue.setItemMeta(blueM);
		
		ItemStack pink = new ItemStack(Material.RED_ROSE, 4,(byte)2);
		ItemMeta pinkM = pink.getItemMeta();
		pinkM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		pink.setItemMeta(pinkM);
		
		ItemStack white = new ItemStack(Material.RED_ROSE, 4,(byte)3);
		ItemMeta whiteM = white.getItemMeta();
		whiteM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		white.setItemMeta(whiteM);
		
		ItemStack red = new ItemStack(Material.RED_ROSE, 4,(byte)4);
		ItemMeta redM = red.getItemMeta();
		redM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		red.setItemMeta(redM);
		
		ItemStack orange = new ItemStack(Material.RED_ROSE, 4,(byte)5);
		ItemMeta orangeM = orange.getItemMeta();
		orangeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		orange.setItemMeta(orangeM);
		
		ItemStack white2 = new ItemStack(Material.RED_ROSE, 4,(byte)6);
		ItemMeta white2M = white2.getItemMeta();
		white2M.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		white2.setItemMeta(white2M);
		
		ItemStack beige = new ItemStack(Material.RED_ROSE, 4,(byte)7);
		ItemMeta beigeM = beige.getItemMeta();
		beigeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		beige.setItemMeta(beigeM);
		
		ItemStack tulip = new ItemStack(Material.RED_ROSE, 4,(byte)8);
		ItemMeta tulipM = tulip.getItemMeta();
		tulipM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		tulip.setItemMeta(tulipM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);
		
		flowers.setItem(0, blue);
		flowers.setItem(1, pink);
		flowers.setItem(2, white);
		flowers.setItem(3, red);
		flowers.setItem(4, arrow);
		flowers.setItem(5, orange);
		flowers.setItem(6, white2);
		flowers.setItem(7, beige);
		flowers.setItem(8, tulip);
		
		p.openInventory(flowers);
		
	}
	
	public void openEnd(Player p){
		Inventory end = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fEnd"));
		
		ItemStack diamond = new ItemStack(Material.DIAMOND_ORE, 1);
		ItemMeta diamondM = diamond.getItemMeta();
		diamondM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&626 Topazes"));
		diamond.setItemMeta(diamondM);
		
		ItemStack endrode = new ItemStack(Material.END_ROD, 1);
		ItemMeta endrodeM = endrode.getItemMeta();
		endrodeM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&62 Ambres"));
		endrode.setItemMeta(endrodeM);
		
		ItemStack endstone = new ItemStack(Material.ENDER_STONE, 4);
		ItemMeta endstoneM = endstone.getItemMeta();
		endstoneM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		endstone.setItemMeta(endstoneM);
		
		ItemStack purpur = new ItemStack(Material.PURPUR_BLOCK, 4);
		ItemMeta purpurM = purpur.getItemMeta();
		purpurM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		purpur.setItemMeta(purpurM);
		
		ItemStack pPillar = new ItemStack(Material.PURPUR_PILLAR, 4);
		ItemMeta pPillarM = pPillar.getItemMeta();
		pPillarM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		pPillar.setItemMeta(pPillarM);
				
		ItemStack fruit = new ItemStack(Material.CHORUS_FRUIT_POPPED, 1);
		ItemMeta fruitM = fruit.getItemMeta();
		fruitM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		fruit.setItemMeta(fruitM);
		
		ItemStack chorus = new ItemStack(Material.CHORUS_FRUIT, 1);
		ItemMeta chorusM = chorus.getItemMeta();
		chorusM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&61 Ambre"));
		chorus.setItemMeta(chorusM);
		
		ItemStack endportal = new ItemStack(Material.ENDER_PORTAL_FRAME, 1);
		ItemMeta endportalM = endportal.getItemMeta();
		endportalM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&664 Platines"));
		endportal.setItemMeta(endportalM);
				
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);
		
		end.setItem(0, diamond);
		end.setItem(1, endrode);
		end.setItem(2, endstone);
		end.setItem(3, purpur);
		end.setItem(4, arrow);
		end.setItem(5, pPillar);
		end.setItem(6, fruit);
		end.setItem(7, chorus);
		end.setItem(8, endportal);
		
		p.openInventory(end);
	}
	
	public void openObjets(Player p){
		Inventory rares = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Shop &fObjets Rares"));
		
		ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta pearlM = pearl.getItemMeta();
		pearlM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&616 Ambres"));
		pearl.setItemMeta(pearlM);
		
		ItemStack diamond = new ItemStack(Material.DIAMOND_ORE, 1);
		ItemMeta diamondM = diamond.getItemMeta();
		diamondM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&630 Topazes"));
		diamond.setItemMeta(diamondM);
		
		ItemStack chorus = new ItemStack(Material.CHORUS_FLOWER, 1);
		ItemMeta chorusM = chorus.getItemMeta();
		chorusM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&68 Platines"));
		chorus.setItemMeta(chorusM);
		
		ItemStack elytra = new ItemStack(Material.ELYTRA, 1);
		ItemMeta elytraM = elytra.getItemMeta();
		elytraM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&616 Platines"));
		elytra.setItemMeta(elytraM);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName(" ");
		glass.setItemMeta(glassM);
		
		ItemStack arrow = new ItemStack(Material.ARROW, 1);
		ItemMeta arrowM = arrow.getItemMeta();
		arrowM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRetour"));
		arrowM.addEnchant(Enchantment.DURABILITY, 1, true);
		arrowM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(arrowM);
		
		rares.setItem(0, glass);
		rares.setItem(1, diamond);
		rares.setItem(3, pearl);
		rares.setItem(4, arrow);
		rares.setItem(5, chorus);
		rares.setItem(7, elytra);
		rares.setItem(8, glass);
		
		p.openInventory(rares);
		
		
		
	}
}
