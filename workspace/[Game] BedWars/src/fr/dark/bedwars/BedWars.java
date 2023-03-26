package fr.dark.bedwars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.dark.bedwars.commands.gameCommand;
import fr.dark.bedwars.listeners.damageListener;
import fr.dark.bedwars.listeners.headshotListener;
import fr.dark.bedwars.listeners.joinEvent;
import fr.dark.bedwars.managers.MapManager;
import fr.dark.bedwars.others.BPlayer;
import fr.dark.bedwars.others.BedMap;
import fr.dark.bedwars.others.ScoreboardSign;
import fr.dark.bedwars.others.Team;
import fr.dark.bedwars.others.WaitMap;
import fr.dark.bedwars.spawn.DState;
import fr.dark.bedwars.spawn.EState;
import fr.dark.bedwars.tasks.RespawnTask;
import fr.dark.bedwars.utils.Titles;
import fr.dark.bedwars.utils.Utils;

public class BedWars extends JavaPlugin {

	BedStates current;
	EState estate;
	DState dstate;

	public Titles title = new Titles();

	public List<Team> teams = new ArrayList<>();
	public List<Player> spectators = new ArrayList<>();
	public List<BPlayer> bPlayers = new ArrayList<>();
	public List<Player> respawn = new ArrayList<>();

	public BedMap activeMap;
	public WaitMap lobby;

	public Map<Player, Team> players = new HashMap<>();
	public Map<Player, ScoreboardSign> boards = new HashMap<>();
	public Map<Player, Integer> deaths = new HashMap<>();
	public Map<Player, Integer> kills = new HashMap<>();
	public Map<Player, Integer> beds = new HashMap<>();

	public MapManager mapManager = new MapManager(this);

	@Override
	public void onEnable() {
		current = BedStates.WAITING;
		estate = EState.START;
		dstate = DState.START;

		saveDefaultConfig();

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new joinEvent(this), this);
		pm.registerEvents(new headshotListener(this), this);
		pm.registerEvents(new damageListener(this), this);

		getCommand("bedwars").setExecutor(new gameCommand(this));

		mapManager.loadMaps();

		loadTeams();
		getLogger().info(teams.size() + " Teams sont actives !");

	}
	
	@Override
	public void onDisable() {
		for (Chunk chunk : activeMap.getWorld().getLoadedChunks()) {
			for (Entity entity : chunk.getEntities()) {
				if (!(entity instanceof Player)) {
					entity.remove();
				}
			}
		}
	}

	// Teams
	public void loadTeams() {
		for (String s : getConfig().getConfigurationSection("BedWars.Config.ActiveMap").getKeys(false)) {
			if (s.contains("Team")) {
				String name = getConfig().getString("BedWars.Config.ActiveMap." + s + ".Name");
				String tag = getConfig().getString("BedWars.Config.ActiveMap." + s + ".Tag");
				byte woolData = (byte) getConfig().getInt("BedWars.Config.ActiveMap." + s + ".WoolData");
				Color color = Utils.getColorString(getConfig().getString("BedWars.Config.ActiveMap." + s + ".Color"));
				int size = getConfig().getInt("BedWars.Config.ActiveMap." + s + ".MaxSize");
				Location spawn = Utils.getLocationString(
						getConfig().getString("BedWars.Config.ActiveMap." + s + ".Spawn"), activeMap.getWorld());
				Team team = new Team(name, tag, woolData, color, size, spawn);
				teams.add(team);
			}

		}
	}

	public Team getTeamWithData(byte data) {
		for (Team t : teams) {
			if (t.getWoolData() == data) {
				return t;
			}
		}
		return null;
	}

	public void randomTeam(Player p) {
		for (Team t : teams) {
			if (t.getPlayersSize() < t.getMaxPlayerSize()) {
				t.addPlayer(p);
				loadTeamWaiting(p, t);
				players.put(p, t);
				boards.get(p).setLine("§3Equipe : " + t.getChatColor() + t.getName(), 3);
				break;
			}
		}
	}

	public int getMaxPlayersSize() {
		int i = 0;
		for (Team t : teams) {
			i += t.getMaxPlayerSize();
		}
		return i;
	}

	public boolean hasTeam(Player p) {
		return players.containsKey(p);
	}

	public Team getTeam(Player p) {
		for (Team t : teams) {
			if (t.getPlayers().contains(p)) {
				return t;
			}
		}
		return null;
	}

	public void loadTeamWaiting(Player p, Team t) {
		p.setPlayerListName(t.getChatColor() + "[" + t.getName() + "] " + p.getName());
		p.setDisplayName(t.getChatColor() + "[" + t.getName() + "] " + p.getName());
		bPlayers.add(new BPlayer(p, t));
	}

	public BPlayer getBPlayerFromPlayer(Player p) {
		for (BPlayer bP : bPlayers) {
			if (bP.getPlayer().getUniqueId() == p.getUniqueId()) {
				return bP;
			}
		}
		return null;
	}

	// States
	public BedStates getState() {
		return current;
	}

	public void setState(BedStates s) {
		current = s;
	}

	public boolean isState(BedStates s) {
		return current == s;
	}

	public EState getEState() {
		return estate;
	}

	public void setEState(EState s) {
		estate = s;
	}

	public boolean isEState(EState s) {
		return estate == s;
	}

	public DState getDState() {
		return dstate;
	}

	public void setDState(DState s) {
		dstate = s;
	}

	public boolean isDState(DState s) {
		return dstate == s;
	}

	// Others
	public void openTeamChoice(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, "§8Choix des équipes");
		for (int i = 0; i < teams.size(); i++) {
			inv.setItem(i, teams.get(i).getWool());
		}
		p.openInventory(inv);
	}

	static public ItemStack coloredArmour(Material m, int amount, Color color, Enchantment ench, int enchLvl) {
		if (ench == null) {
			ItemStack lhelmet = new ItemStack(m, amount);
			LeatherArmorMeta lam = (LeatherArmorMeta) lhelmet.getItemMeta();
			lam.setColor(color);
			lhelmet.setItemMeta(lam);

			return lhelmet;
		} else {
			ItemStack lhelmet = new ItemStack(m, amount);
			LeatherArmorMeta lam = (LeatherArmorMeta) lhelmet.getItemMeta();
			lam.setColor(color);
			lam.addEnchant(ench, enchLvl, true);
			lhelmet.setItemMeta(lam);

			return lhelmet;
		}
	}

	static public ItemStack createItem(Material mat, Enchantment ench, int enchLvl, String name, List<String> lore) {
		if (ench == null) {
			ItemStack it = new ItemStack(mat, 1);
			ItemMeta itm = it.getItemMeta();
			itm.setDisplayName(name);
			itm.setLore(lore);
			itm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			itm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			it.setItemMeta(itm);
			return it;
		} else {
			ItemStack it = new ItemStack(mat, 1);
			ItemMeta itm = it.getItemMeta();
			itm.setDisplayName(name);
			itm.addEnchant(ench, enchLvl, true);
			itm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			itm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			itm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			itm.setLore(lore);
			it.setItemMeta(itm);
			return it;
		}
	}

	public void respawn(Player p) {
		p.setHealth(20D);
		p.setFoodLevel(20);
		Team t = getTeam(p);
		respawn.add(p);

		if (t.hasBed()) {
			p.setGameMode(GameMode.SPECTATOR);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
			BPlayer bP = getBPlayerFromPlayer(p);
			bP.equip();
			p.teleport(activeMap.getSpawn());
			RespawnTask task = new RespawnTask(p, this);
			task.runTaskTimer(this, 20, 20);
		} else {
			p.teleport(activeMap.getSpawn());
			p.setGameMode(GameMode.SPECTATOR);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
			p.sendMessage("§o§7Vous êtes mort, merci d'avoir joué !");
			respawn.remove(p);
		}
	}

}