package com.happiergore.menusapi;

import com.happiergore.menusapi.ItemsTypes.Behaviour;
import com.happiergore.menusapi.Utils.ConsoleUtils;
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
 * @author HappierGore
 */
public abstract class GUI implements InventoryHolder {

    //****************
    //    CLASS
    //***************
    private Inventory inventory;
    private final Map<Integer, Behaviour> items = new HashMap<>();
    private PlayerUtils player;
    public String INVENTORY_TITLE;
    private final ConsoleUtils console = new ConsoleUtils();

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
        if (item != null) {
            //i = Slot (map with slot and item)
            for (int i : this.getItems().keySet()) {
                if (i == e.getRawSlot()) {
                    this.getItems().get(i).onClick(e);
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }

    public void onOpen() {
        registerButtons();
        for (int slot : this.getItems().keySet()) {
            Behaviour btn = this.getItems().get(slot);
            if (this.getInventory().getSize() < slot) {
                console.errorMsg("&cThere was an error when trying to load the item: "
                        + btn.getItem().getItemMeta().getDisplayName()
                        + " because the slot defined (" + slot + ") is greather than the inventory size "
                        + "(" + this.getInventory().getSize() + ").");
                continue;
            }
            this.getInventory().setItem(slot, btn.getItem());
            btn.onLoad();
        }
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

    public void setPlayer(Player player) {
        this.player = new PlayerUtils(player);
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
