package fr.dark.hikabrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dark.hikabrain.api.HTitles;
import fr.dark.hikabrain.api.ScoreboardSign;
import fr.dark.hikabrain.listeners.hikaListeners;
import fr.dark.hikabrain.tasks.respawnTask;
import fr.dark.hikabrain.teams.Team;
import net.minecraft.server.v1_10_R1.EntityPlayer;

public class Core extends JavaPlugin {

	HStates current;
	public HTitles titles = new HTitles();
	private List<Team> teams = new ArrayList<>();
	public Map<Player, Integer> deaths = new HashMap<>();
	public Map<Player, Team> team = new HashMap<>();
	public Map<Player, ScoreboardSign> boards = new HashMap<>();
	public List<Block> placedblocks = new ArrayList<>();
	public Map<Location, Material> destroyedBlocks = new HashMap<>();

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new hikaListeners(this), this);
		this.current = HStates.WAITING;
		saveDefaultConfig();
		for (String confTeams : getConfig().getConfigurationSection("Teams").getKeys(false)) {
			String name = getConfig().getString("Teams." + confTeams + ".name");
			String tag = getConfig().getString("Teams." + confTeams + ".tag").replace("&", "§");
			String spawn = getConfig().getString("Teams." + confTeams + ".spawn");
			String world = getConfig().getString("Teams." + confTeams + ".world");
			byte woolData = (byte) getConfig().getInt("Teams." + confTeams + ".data");
			teams.add(new Team(name, tag, woolData, getParseLoc(spawn, world), 0));
		}
		System.out.println(teams.size() + " teams ont été chargé !");
	}

	public void loadTeams() {
		for (Team myTeam : teams) {
			for (Player player : myTeam.getPlayers()) {
				team.put(player, myTeam);
			}
		}
	}

	public int getPing(Player p) {
		CraftPlayer cp = (CraftPlayer) p;
		EntityPlayer ep = cp.getHandle();
		return ep.ping;
	}

	public Team getTeam(Player player) {
		return team.get(player);
	}

	public Team getTeamByColorData(byte data) {
		for (Team team : teams) {
			if (team.getWoolData() == data) {
				return team;
			}
		}
		return null;
	}

	public void setState(HStates state) {
		current = state;
	}

	public boolean isState(HStates state) {
		return current == state;
	}

	public Location getParseLoc(String spawn, String worldname) {
		String[] spawns = spawn.split(";");
		double x = Double.valueOf(spawns[0]);
		double y = Double.valueOf(spawns[1]);
		double z = Double.valueOf(spawns[2]);
		float yaw = 0;
		float pitch = 0;

		if (spawns.length >= 5) {
			yaw = Float.valueOf(spawns[3]);
			pitch = Float.valueOf(spawns[4]);
		}


		World world = Bukkit.getWorld(worldname);
		return new Location(world, x, y, z, yaw, pitch);
	}

	public void addPlayer(Player p, Team team) {
		String tag = team.getTag() + team.getName();

		if(team.getPlayers().contains(p)){
			p.sendMessage("§8[§c!§8] §8Tu es déjà dans cette team !");
			return;
		}
		
		if(team.getSize() >= 1){
			p.sendMessage("§8[§c!§8] §8La team est full !");
			return;
		}
		
		team.addPlayer(p);
		p.setPlayerListName(team.getTag() + p.getName());
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fVous rejoignez l'équipe des " + tag));

	}

	public void removePlayer(Player player) {
		for (Team team : teams) {
			if (team.getPlayers().contains(player)) {
				team.removePlayer(player);
			}
		}
	}

	public void randomTeam(Player player) {
		for (Team team : teams) {
			if (team.getSize() < 1) {
				addPlayer(player, team);
				break;
			}
		}

	}

	public List<Team> getTeams() {
		return teams;
	}
	
	public void equip(Player p){
		ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta swordM = sword.getItemMeta();
		swordM.addEnchant(Enchantment.KNOCKBACK, 2, true);
		sword.setItemMeta(swordM);
		
		ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE, 1);
		ItemMeta pickaxeM = pickaxe.getItemMeta();
		pickaxeM.addEnchant(Enchantment.DIG_SPEED, 3, true);
		pickaxe.setItemMeta(pickaxeM);
		
		p.getInventory().setItemInOffHand(new ItemStack(Material.SANDSTONE, 64));
		p.setHealth(p.getMaxHealth());
		p.setFoodLevel(20);
		p.getInventory().setItem(0, sword);
		p.getInventory().setItem(1, pickaxe);
		p.getInventory().addItem(new ItemStack(Material.SANDSTONE, 448));
	}
	
	public void reset() {


		setState(HStates.WAIT);
		respawnTask task = new respawnTask(this);
		task.runTaskTimer(this, 20, 20);
		Iterator<Block> bs = placedblocks.iterator();
		while(bs.hasNext()){
			bs.next().setType(Material.AIR);
		}
		
		for(Entry<Location, Material> blocks : destroyedBlocks.entrySet()){
			blocks.getKey().getBlock().setType(blocks.getValue());
		}
		
		for(Player pls : Bukkit.getOnlinePlayers()){
			respawn(pls);
		}
		
	}
	
	public void respawn(Player p){
		p.teleport(getTeam(p).getSpawn());
		p.getInventory().clear();
		equip(p);
	}
	
	public void endGame(){
		setState(HStates.FINISHING);
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			p.setGameMode(GameMode.SPECTATOR);
		}
	}
}
