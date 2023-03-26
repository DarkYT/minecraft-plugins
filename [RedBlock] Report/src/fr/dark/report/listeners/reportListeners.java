package fr.dark.report.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.dark.report.Core;

public class reportListeners implements Listener{

	Core core;
	public reportListeners(Core core) {this.core = core;}
	
	@EventHandler
	public void inventoryClickEvent(InventoryClickEvent e){
		if(e.getWhoClicked() instanceof Player){
			Player p = (Player) e.getWhoClicked();
			if(e.getInventory().getName().equals(core.utils.colorMessage(core.getConfig().getString("Report.AReport.InventoryTitle")))){
				if(e.getCurrentItem().getType().equals(Material.SKULL_ITEM)){
					String s = e.getCurrentItem().getItemMeta().getDisplayName();
					String[] ss = s.split(" ");
					p.closeInventory();
					core.openPersonnalReport(p, ss[1]);
				}
				e.setCancelled(true);
			}
			if(e.getInventory().getName().contains(core.utils.colorMessage(core.getConfig().getString("Report.AReport.PlayerReportInventory.TitleConfirm")))){
				if(e.getCurrentItem().getType().equals(Material.PAPER)){
					String idvalue = e.getCurrentItem().getItemMeta().getLore().get(7);
					idvalue = idvalue.replace("#", "");
					idvalue = ChatColor.stripColor(idvalue);
					int id = Integer.valueOf(idvalue);
					if(e.getClick().equals(ClickType.LEFT)){
						p.closeInventory();
						p.sendMessage(core.utils.modifyMessage(core.getConfig().getString("Report.AReport.ChatMessage.Presentation"), "<player>", Core.sql.getPseudo(id)));
						p.sendMessage(core.utils.modifyMessage(core.getConfig().getString("Report.AReport.ChatMessage.Server"), "<server>", Core.sql.getServer(id)));
						p.sendMessage(core.utils.modifyMessage(core.getConfig().getString("Report.AReport.ChatMessage.Raison"), "<raison>", Core.sql.getRaison(id)));
						String report = core.getConfig().getString("Report.AReport.ChatMessage.Reporter");
						report = report.replace("<player>", Core.sql.getPseudo(id));
						report = report.replace("<reporter>", Core.sql.getReporter(id));
						p.sendMessage(core.utils.colorMessage(report));
						p.sendMessage(core.utils.modifyMessage(core.getConfig().getString("Report.AReport.ChatMessage.Preuve"), "<preuve>", Core.sql.getPreuve(id)));
					}else if(e.getClick().equals(ClickType.SHIFT_RIGHT)){
						p.closeInventory();
						p.sendMessage(core.utils.modifyMessage(core.getConfig().getString("Report.AReport.ChatMessage.RemoveSuccess"), "<player>", Core.sql.getPseudo(id)));
						Core.sql.remove(id);
						e.getCurrentItem().setType(Material.AIR);
						core.openReport(p);
					}
				}
				e.setCancelled(true);
			}
		}
	}

}
