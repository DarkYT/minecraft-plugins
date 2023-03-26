package fr.shiick.coins;

import org.bukkit.entity.Player;

public class CoinsAPI {
	
	public int getCoins(Player p) {
		int coins = Coins.sql.getCoins(p);
		return coins;
	}
	
	public void addCoins(Player p, int coins) {
		int pCoins = getCoins(p);
		Coins.sql.setCoins(p, pCoins + coins);
	}
	
	public void withdrawCoins(Player p, int coins) {
		int pCoins = getCoins(p);
		Coins.sql.setCoins(p, pCoins - coins);
	}
	
	public void resetCoins(Player p) {
		Coins.sql.setCoins(p, Utils.getDefault());
	}
	
	public void setCoins(Player p, int coins) {
		Coins.sql.setCoins(p, coins);
	}
	
	public boolean canAfford(Player p, int price) {
		if(getCoins(p) >= price) {
			return true;
		} else {
			return false;
		}
	}
	
}
