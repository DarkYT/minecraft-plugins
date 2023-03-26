package fr.dark.bedwars.others;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Team {
	
	private String name;
	private String tag;
	private Color color;
	private Location spawn;
	private int maxSize;
	private byte woolData;
	private boolean hasBed;
	private List<Player> players = new ArrayList<>();
	
	public Team(String name, String tag, byte woolData, Color color, int maxSize, Location spawn) {
		this.color = color;
		this.name = name;
		this.maxSize = maxSize;
		this.spawn = spawn;
		this.tag = tag.replace("&", "§");
		this.woolData = woolData;
		this.hasBed = true;
	}
	
	public ItemStack getWool(){
		ItemStack i = new ItemStack(Material.WOOL, 1, woolData);
		ItemMeta iM = i.getItemMeta();
		iM.setDisplayName("Rejoindre l'équipe des " + tag + name);
		List<String> lore = new ArrayList<>();
		lore.add(tag+players.size()+"/"+maxSize);
		for(Player p : players) {
			lore.add(tag+p.getName());
		}
		iM.setLore(lore);
		i.setItemMeta(iM);
		return i;
	}
	
	public Location getSpawn() {
		return spawn;
	}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	public void removePlayer(Player player){
		players.remove(player);
	}
	
	public void breakBed(){
		this.hasBed = false;
	}
	
	public boolean hasBed(){
		return hasBed;
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
	public int getPlayersSize(){
		return players.size();
	}
	
	public String getName() {
		return name;
	}

	public String getChatColor() {
		return tag;
	}
	
	public byte getWoolData() {
		return woolData;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getMaxPlayerSize() {
		return maxSize;
	}
	

}
