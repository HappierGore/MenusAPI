package com.happiergore.menusapi;

import com.happiergore.menusapi.ItemsTypes.Behaviour;
import com.happiergore.menusapi.Utils.PlayerUtils;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 *
 * 
 * @author HappierGore
 */
public abstract class GUI implements InventoryHolder {

    //****************
    //    CLASS
    //***************
    private Inventory inventory;
    private final Map<Integer, Behaviour> items = new HashMap<>();
    private final PlayerUtils player;
    public final String INVENTORY_TITLE;

    public GUI(Player player, String inventoryTitle) {
        this.INVENTORY_TITLE = inventoryTitle;
        this.player = new PlayerUtils(player);
    }

    public final void open() {
        this.updateInventory();
        this.player.get().openInventory(this.getInventory());
        this.onOpen();
    }

    public final void close(InventoryCloseEvent e) {
        this.onClose(e);
    }

    public void onInventoryClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item != null && item.getType() != Material.AIR) {
            for (int i : this.getItems().keySet()) {
                if (i == e.getSlot()) {
                    this.getItems().get(i).onClick(e);
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }

    public void onOpen() {
        registerButtons();
        this.getItems().forEach((slot, btn) -> {
            this.getInventory().setItem(slot, btn.getItem());
            btn.onLoad();
        });
    }

    public abstract void onClose(InventoryCloseEvent e);

    public abstract void registerButtons();

    public void registerBtn(int slot, Behaviour btn) {
        this.items.putIfAbsent(slot, btn);
    }

    //*******************
    // Getters & Setters
    //*******************
    public Map<Integer, Behaviour> getItems() {
        return items;
    }

    public PlayerUtils getPlayer() {
        return this.player;
    }

    @Override
    public String toString() {
        return "GUI{" + "inventory=" + this.getInventory() + ", items=" + items + ", player=" + player + ", Memory = " + super.toString() + "}";
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void updateInventory() {
        this.items.values().forEach(item -> {
            item.update();
        });
    }

}
