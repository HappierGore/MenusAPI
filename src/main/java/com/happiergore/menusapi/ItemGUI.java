package com.happiergore.menusapi;

import org.bukkit.inventory.ItemStack;

/**
 *
 * @author HappierGore
 */
public abstract class ItemGUI {

    private GUI inventory;
    private ItemStack item;

    public ItemGUI(GUI inventory) {
        this.inventory = inventory;
    }

    public void loadMainItem() {
        this.item = this.generateMainItem();
    }

    public abstract ItemStack generateMainItem();

    //*****************
    //      GETTERS
    //*****************
    public GUI getGUI() {
        return this.inventory;
    }

    public ItemStack getItem() {
        return item;
    }

    //*****************
    //      SETTERS
    //*****************
    public void setInventory(GUI inventory) {
        this.inventory = inventory;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void update() {
        this.loadMainItem();
    }

}
