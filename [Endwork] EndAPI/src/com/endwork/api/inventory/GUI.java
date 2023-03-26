package com.endwork.api.inventory;

import com.endwork.api.inventory.buttons.GUIButton;
import com.endwork.api.inventory.buttons.InventoryListenerGUI;
import com.endwork.api.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class GUI implements InventoryHolder {

    private Map<Integer, GUIButton> items;
    private static InventoryListenerGUI inventoryListenerGUI;
    private String name;
    private int size;

    public GUI(String name, int size) {
        items = new HashMap<>();
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        this.size = size;
    }

    /**
     * Sets the display name of the PaginatedGUI.
     *
     * <br>
     *
     * Color Codes are supported (and should be prepended with an
     * ampersand [&amp;]; e.g. &amp;c for red.)
     *
     * @param name The desired name of the PaginatedGUI.
     */
    public void setDisplayName(String name){
        this.name = ChatColor.translateAlternateColorCodes('&', name);
    }

    /**
     * Gets the display name of the PaginatedGUI.
     *
     * <br>
     *
     * <b>Note:</b> If your inventory's display name contains
     * color codes, this will have substituted the
     * ampersands (&amp;)s with the rendering engine's
     * symbol (&sect;).
     *
     * @return The inventory's display name.
     */
    public String getDisplayName(){
        return name;
    }

    /**
     * Adds the provided {@link GUIButton} to the PaginatedGUI.
     *
     * <br>
     *
     * <b>Note:</b> This will place the button after the highest slot.
     * So if you have buttons in slot 0, 1 and 5, this will place the
     * added button in slot 6.
     *
     * @param button The button you wish to add.
     */
    public void addButton(GUIButton button){
        int slot = 0;

        if(!items.isEmpty()) {
            // Find the highest slot
            int highestSlot = -1;
            for(int itemSlot : items.keySet()){
                if(itemSlot > highestSlot){
                    highestSlot = itemSlot;
                }
            }

            // Set the target slot to one higher than the highest slot.
            slot = highestSlot + 1;
        }

        // Put the button in that slot.
        items.put(slot, button);
    }

    /**
     * Adds the provided {@link GUIButton} but places it in the desired slot in the PaginatedGUI.
     *
     * @param slot The desired slot for the button.
     * @param button The button you wish to add.
     */
    public void setButton(int slot, GUIButton button){
        items.put(slot, button);
    }

    public GUIButton getButton(int slot){
        return items.get(slot);
    }

    @Override
    public Inventory getInventory() {
        // Create an inventory (and set an appropriate size.)
        // TODO: Allow customisation of inventory size. Maybe at first, only if the inventory is not paginated.
        Inventory inventory = Bukkit.createInventory(this, size, name);
        // Add the main inventory items
        for(int i = 0; i < size; i++){
            if(items.containsKey(i)){
                inventory.setItem(i, items.get(i).getItem());
            }
        }

        return inventory;
    }

    public static void prepare(JavaPlugin plugin){
        if(inventoryListenerGUI == null){
            inventoryListenerGUI = new InventoryListenerGUI();
            plugin.getServer().getPluginManager().registerEvents(inventoryListenerGUI, plugin);
        }
    }
}
