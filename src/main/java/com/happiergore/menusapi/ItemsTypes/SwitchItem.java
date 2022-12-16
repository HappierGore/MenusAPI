package com.happiergore.menusapi.ItemsTypes;

import com.happiergore.menusapi.GUI;
import com.happiergore.menusapi.Utils.ItemUtils;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author HappierGore
 */
public abstract class SwitchItem extends Behaviour {

    private ItemStack initialItem;
    private ItemStack switchItem;
    private final ItemUtils item = new ItemUtils();
    private final int slot;

    public SwitchItem(GUI inv, int slot) {
        super(inv);
        this.slot = slot;
    }

    public void loadSwitchItem() {
        this.switchItem = generateSwitchItem();
    }

    public void loadInitialItem() {
        this.initialItem = generateMainItem();
    }

    private void updateItem() {
        this.setItem(this.getGUI().getInventory().getItem(this.slot));
    }

    public void updateSwitchItem() {
        if (item.compareItems(this.getItem(), switchItem, null)) {
            loadSwitchItem();
            this.getGUI().getInventory().setItem(this.slot, this.getSwitchItem());
            this.updateItem();
        }
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        e.setCancelled(true);
        ItemStack toSwitch = item.compareItems(this.getItem(), initialItem, null) ? switchItem : initialItem;
        this.getGUI().getInventory().setItem(this.slot, toSwitch);
        updateItem();
    }

    @Override
    public void onLoad() {
        if (this.loadCondition()) {
            this.getGUI().getInventory().setItem(this.slot, switchItem);
        } else {
            this.getGUI().getInventory().setItem(this.slot, initialItem);
        }
        updateItem();
    }

    public ItemStack getSwitchItem() {
        return switchItem;
    }

    @Override
    public void update() {
        this.loadInitialItem();
        this.loadSwitchItem();
        super.update();
    }

    public abstract boolean loadCondition();

    public abstract ItemStack generateSwitchItem();

}
