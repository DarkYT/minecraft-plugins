package fr.blizzrpg.classsystem.listeners;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import fr.blizzrpg.classsystem.ClassAPI;
import net.minecraft.server.v1_10_R1.EntityHorse;

public class deathEvent implements Listener {
	
	ClassAPI classapi;
	
	public deathEvent(ClassAPI capi){
		classapi = capi;
	}

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		Entity victim = e.getEntity();
		Entity player = e.getEntity().getKiller();
		int xpZombie = classapi.getConfig().getInt("XP.Zombie");
		int xpCreeper = classapi.getConfig().getInt("XP.Creeper");
		int xpSkeleton = classapi.getConfig().getInt("XP.Squelette");
		int xpSpider = classapi.getConfig().getInt("XP.Spider");
		int xpCSpider = classapi.getConfig().getInt("XP.CaveSpider");
		int xpPigman = classapi.getConfig().getInt("XP.Pigman");
		int xpEnderman = classapi.getConfig().getInt("XP.Enderman");
		int xpSlime = classapi.getConfig().getInt("XP.Slime");
		int xpMagmaCube = classapi.getConfig().getInt("XP.MagmaCube");
		int xpSilverfish = classapi.getConfig().getInt("XP.Silverfish");
		int xpEndermite = classapi.getConfig().getInt("XP.Endermite");
		int xpBat = classapi.getConfig().getInt("XP.Bat");
		int xpBlaze = classapi.getConfig().getInt("XP.Blaze");
		int xpGhast = classapi.getConfig().getInt("XP.Ghast");
		int xpGuardian = classapi.getConfig().getInt("XP.Guardian");
		int xpWitch = classapi.getConfig().getInt("XP.Witch");
		int xpPig = classapi.getConfig().getInt("XP.Pig");
		int xpCow = classapi.getConfig().getInt("XP.Cow");
		int xpSheep = classapi.getConfig().getInt("XP.Sheep");
		int xpOcelot = classapi.getConfig().getInt("XP.Ocelot");
		int xpChicken = classapi.getConfig().getInt("XP.Chicken");
		int xpHorse = classapi.getConfig().getInt("XP.Horse");
		int xpSquid = classapi.getConfig().getInt("XP.Squid");
		int xpMooshroom = classapi.getConfig().getInt("XP.Mooshroom");
		int xpWolf = classapi.getConfig().getInt("XP.Wolf");
		int xpRabbit = classapi.getConfig().getInt("XP.Rabbit");
		int xpWither = classapi.getConfig().getInt("XP.Wither");
		int xpDragon = classapi.getConfig().getInt("XP.EnderDragon");
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(ClassAPI.confFile);
		if (victim instanceof Zombie) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpZombie);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpZombie + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Creeper) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpCreeper);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpCreeper + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof PigZombie) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpPigman);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpPigman + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Skeleton) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpSkeleton);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpSkeleton + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Spider) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpSpider);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpSpider + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof CaveSpider) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpCSpider);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpCSpider + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Slime) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpSlime);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpSlime + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Silverfish) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpSilverfish);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpSilverfish + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Endermite) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpEndermite);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpEndermite + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Bat) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpBat);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpBat + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof MagmaCube) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpMagmaCube);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpMagmaCube + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Blaze) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpBlaze);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpBlaze + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Witch) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpWitch);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpWitch + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Guardian) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpGuardian);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpGuardian + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Ghast) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpGhast);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpGhast + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Enderman) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpEnderman);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpEnderman + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Pig) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpPig);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpPig + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Sheep) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpSheep);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpSheep + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Cow) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpCow);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpCow + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Chicken) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpChicken);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpChicken + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Squid) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpSquid);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpSquid + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Wolf) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpWolf);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpWolf + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof MushroomCow) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpMooshroom);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpMooshroom + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Ocelot) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpOcelot);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpOcelot + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof EntityHorse) {
			if (player instanceof Player) {
				Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpHorse);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpHorse + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Rabbit) {
			if (player instanceof Player) {
			Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpRabbit);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpRabbit + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof Wither) {
			if (player instanceof Player) {
			Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpWither);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpWither + "&b&lXP"));
				classapi.checkLevel(p);
			}
		} else if (victim instanceof EnderDragon) {
			if (player instanceof Player) {
			Player p = (Player) player;
				int xp = yc.getInt(p.getName() + ".XP");
				int level = yc.getInt(p.getName() + ".Niveau");
				if (level == 0) {
					yc.set(p.getName() + ".Niveau", 0);
				}
				yc.set(p.getName() + ".XP", xp + xpDragon);
				try {
					yc.save(ClassAPI.confFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l+&6&l" + xpDragon + "&b&lXP"));
				classapi.checkLevel(p);
			}
		}
	}

}
