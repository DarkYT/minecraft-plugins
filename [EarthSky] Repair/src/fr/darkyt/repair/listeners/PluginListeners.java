package fr.darkyt.repair.listeners;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.darkyt.repair.Main;
import net.milkbowl.vault.economy.EconomyResponse;

public class PluginListeners implements Listener {
	
	Main main;
	
	public PluginListeners(Main main) {
		this.main = main;
	}


	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSignClick(PlayerInteractEvent event){
		
		Player player = event.getPlayer();
		Action action = event.getAction();
		
		OfflinePlayer pl = player;
		int price = 2;
		
		if(event.getClickedBlock() != null && action == Action.RIGHT_CLICK_BLOCK){
			BlockState bs = event.getClickedBlock().getState();
			if(bs instanceof Sign){
				Sign sign = (Sign) bs;
				if(sign.getLine(0).equals("§a§l[Repair]") && sign.getLine(1).equals("§aRépare l'item") && sign.getLine(2).equals("§aDans votre main") && sign.getLine(3).equals("§cCoût : 2 Coins !")){
					ItemStack it = player.getInventory().getItemInMainHand();
					ItemStack newItem = new ItemStack(it.getType(), it.getAmount());
					
					if(player.getInventory().getItemInMainHand().getDurability() == 0){
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVotre item n'a pas besoin d'être repair !"));
						return;
						
					}

					if(it == null || it.getTypeId() == 0){
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVous n'avez pas d'item a repair !"));
						return;
					}
					
					if(Main.econ.has(pl, 2)){
					    EconomyResponse r = Main.econ.withdrawPlayer(player, price);
					    if (r.transactionSuccess()){
					    	ItemMeta meta = it.getItemMeta();
							if(it.hasItemMeta()){
								if(it.getItemMeta().hasDisplayName()) meta.setDisplayName(it.getItemMeta().getDisplayName());
								if(it.getItemMeta().hasLore()) meta.setLore(it.getItemMeta().getLore());
								if(it.getItemMeta().hasEnchants()) newItem.addUnsafeEnchantments(it.getEnchantments());
							}
							
							newItem.setItemMeta(meta);
							player.getInventory().setItemInMainHand(newItem);
					    
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVous venez de réparer votre item"));
					    }
					    
					}else{
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLe repair coûte 2 coins !"));
					}
				}
			}
		}

	}
}

