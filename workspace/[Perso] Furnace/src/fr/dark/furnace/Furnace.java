package fr.dark.furnace;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dark.furnace.commands.furnaceCommand;

public class Furnace extends JavaPlugin {
	
	public static Furnace instance;

	@Override
	public void onEnable() {
		instance = this;
		getCommand("furnace").setExecutor(new furnaceCommand(this));
	}
	
	public static Furnace getInstance() {
		return instance;
	}
	
	public double getTotalCost(ItemStack it) {
		int amount = it.getAmount();
		double cost = Furnace.getXPCost(it.getType());
		return cost*amount;
	}

	public int getTotalExperience(Player Who) {
		int level = Who.getLevel();
		float progress = Who.getExp();
		int totalExp = 0;
		for (int n = 1; n < level + 1; n++)
			totalExp = (n >= 16) ? ((n >= 31) ? totalExp + 112 + (n - 31) * 9 : totalExp + 37 + (n - 16) * 5)
					: totalExp + 7 + (n - 1) * 2;
		progress = (level >= 15)
				? ((level >= 30) ? progress * (112 + (level - 30) * 9) : progress * (37 + (level - 15) * 5))
				: progress * (7 + level * 2);
		totalExp = totalExp + Math.round(progress);
		return totalExp;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public static ItemStack getResult(final ItemStack it) {
		switch (it.getType()) {
		case RAW_FISH:
			if (it.getData().getData() == 0) {
				return new ItemStack(Material.COOKED_FISH, it.getAmount(), (byte)0);
			} else if(it.getData().getData() == 1){
				return new ItemStack(Material.COOKED_FISH, it.getAmount(), (byte)1);
			}
		case RAW_BEEF:
			return new ItemStack(Material.COOKED_BEEF, it.getAmount(), (byte)0);
		case RAW_CHICKEN:
			return new ItemStack(Material.COOKED_CHICKEN, it.getAmount(), (byte)0);
		case POTATO_ITEM:
			return new ItemStack(Material.BAKED_POTATO, it.getAmount(), (byte)0);
		case RABBIT:
			return new ItemStack(Material.COOKED_RABBIT, it.getAmount(), (byte)0);
		case MUTTON:
			return new ItemStack(Material.COOKED_MUTTON, it.getAmount(), (byte)0);
		case PORK:
			return new ItemStack(Material.GRILLED_PORK, it.getAmount(), (byte)0);
		case IRON_ORE:
			return new ItemStack(Material.IRON_INGOT, it.getAmount(), (byte)0);
		case GOLD_ORE:
			return new ItemStack(Material.GOLD_INGOT, it.getAmount(), (byte)0);
		case SAND:
			return new ItemStack(Material.GLASS, it.getAmount(), (byte)0);
		case COBBLESTONE:
			return new ItemStack(Material.STONE, it.getAmount(), (byte)0);
		case CLAY_BALL:
			return new ItemStack(Material.CLAY_BRICK, it.getAmount(), (byte)0);
		case NETHERRACK:
			return new ItemStack(Material.NETHER_BRICK_ITEM, it.getAmount(), (byte)0);
		case CLAY:
			return new ItemStack(Material.HARD_CLAY, it.getAmount(), (byte)0);
		case SMOOTH_BRICK:
			return new ItemStack(Material.SMOOTH_BRICK, it.getAmount(), (byte)2);
		case STAINED_CLAY:
			return new ItemStack(getTerracotaWithByte(it.getData().getData()), it.getAmount(), (byte)0);
		case DIAMOND_ORE:
			return new ItemStack(Material.DIAMOND, it.getAmount(), (byte)0);
		case LAPIS_ORE:
			return new ItemStack(Material.INK_SACK, it.getAmount(), (byte)4);
		case REDSTONE_ORE:
			return new ItemStack(Material.REDSTONE, it.getAmount(), (byte)0);
		case COAL_ORE:
			return new ItemStack(Material.COAL, it.getAmount(), (byte)0);
		case EMERALD_ORE:
			return new ItemStack(Material.EMERALD, it.getAmount(), (byte)0);
		case QUARTZ_ORE:
			return new ItemStack(Material.QUARTZ, it.getAmount(), (byte)0);
		case CACTUS:
			return new ItemStack(Material.INK_SACK, it.getAmount(), (byte)2);
		case LOG:
			return new ItemStack(Material.COAL, it.getAmount(), (byte)1);
		case LOG_2:
			return new ItemStack(Material.COAL, it.getAmount(), (byte)1);
		case SPONGE:
			return new ItemStack(Material.SPONGE, it.getAmount(), (byte)0);
		case CHORUS_FRUIT:
			return new ItemStack(Material.CHORUS_FRUIT_POPPED, it.getAmount(), (byte)0);
		default:
			return null;
		}
	}

	private static Material getTerracotaWithByte(byte data) {
		switch(data) {
		case 0:
			return Material.WHITE_GLAZED_TERRACOTTA;
		case 1:
			return Material.ORANGE_GLAZED_TERRACOTTA;
		case 2:
			return Material.MAGENTA_GLAZED_TERRACOTTA;
		case 3:
			return Material.LIGHT_BLUE_GLAZED_TERRACOTTA;
		case 4:
			return Material.YELLOW_GLAZED_TERRACOTTA;
		case 5:
			return Material.LIME_GLAZED_TERRACOTTA;
		case 6:
			return Material.PINK_GLAZED_TERRACOTTA;
		case 7:
			return Material.GRAY_GLAZED_TERRACOTTA;
		case 8:
			return Material.SILVER_GLAZED_TERRACOTTA;
		case 9:
			return Material.CYAN_GLAZED_TERRACOTTA;
		case 10:
			return Material.PURPLE_GLAZED_TERRACOTTA;
		case 11:
			return Material.BLUE_GLAZED_TERRACOTTA;
		case 12:
			return Material.BROWN_GLAZED_TERRACOTTA;
		case 13:
			return Material.GREEN_GLAZED_TERRACOTTA;
		case 14:
			return Material.RED_GLAZED_TERRACOTTA;
		case 15:
			return Material.BLACK_GLAZED_TERRACOTTA;
		default:
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean canFurnace(final ItemStack it) {
		switch (it.getType()) {
		case RAW_FISH:
			if (it.getData().getData() == 0 || it.getData().getData() == 1) {
				return true;
			} else {
				return false;
			}
		case RAW_BEEF:
			return true;
		case RAW_CHICKEN:
			return true;
		case POTATO_ITEM:
			return true;
		case RABBIT:
			return true;
		case MUTTON:
			return true;
		case PORK:
			return true;
		case IRON_ORE:
			return true;
		case GOLD_ORE:
			return true;
		case SAND:
			return true;
		case COBBLESTONE:
			return true;
		case CLAY_BALL:
			return true;
		case NETHERRACK:
			return true;
		case CLAY:
			return true;
		case SMOOTH_BRICK:
			if (it.getData().getData() == 0) {
				return true;
			} else {
				return false;
			}
		case STAINED_CLAY:
			return true;
		case DIAMOND_ORE:
			return true;
		case LAPIS_ORE:
			return true;
		case REDSTONE_ORE:
			return true;
		case COAL_ORE:
			return true;
		case EMERALD_ORE:
			return true;
		case QUARTZ_ORE:
			return true;
		case CACTUS:
			return true;
		case LOG:
			return true;
		case LOG_2:
			return true;
		case SPONGE:
			if(it.getData().getData() == 1) {
				return true;
			}else {
				return false;
			}
		case CHORUS_FRUIT:
			return true;
		default:
			return false;
		}
	}

	public static double getXPCost(final Material mat) {
		switch (mat) {
		case RAW_FISH:
			return 0.35;
		case RAW_BEEF:
			return 0.35;
		case RAW_CHICKEN:
			return 0.35;
		case POTATO_ITEM:
			return 0.35;
		case RABBIT:
			return 0.35;
		case MUTTON:
			return 0.35;
		case PORK:
			return 0.35;
		case IRON_ORE:
			return 0.7;
		case GOLD_ORE:
			return 1.0;
		case SAND:
			return 0.1;
		case COBBLESTONE:
			return 0.1;
		case CLAY_BALL:
			return 0.3;
		case NETHERRACK:
			return 0.1;
		case CLAY:
			return 0.35;
		case SMOOTH_BRICK:
			return 0.3;
		case STAINED_CLAY:
			return 0.1;
		case DIAMOND_ORE:
			return 1.0;
		case LAPIS_ORE:
			return 0.2;
		case REDSTONE_ORE:
			return 0.7;
		case COAL_ORE:
			return 0.1;
		case EMERALD_ORE:
			return 1.0;
		case QUARTZ_ORE:
			return 0.2;
		case CACTUS:
			return 0.2;
		case LOG:
			return 0.15;
		case LOG_2:
			return 0.15;
		case SPONGE:
			return 0.15;
		case CHORUS_FRUIT:
			return 0.1;
		default:
			return 0;
		}
	}

	public static void furnace(Player p, int slot) {
		ItemStack it = p.getInventory().getItem(slot);
		if(canFurnace(it)) {
			double removeXP = getInstance().getTotalCost(it);
			p.getInventory().setItem(slot, getResult(it));
			p.updateInventory();
			int totXP = getInstance().getTotalExperience(p);
			p.setLevel(0);
			p.setTotalExperience(0);
			p.setExp(0);
			
			p.giveExp((int) (totXP-removeXP));
		}
	}

}
