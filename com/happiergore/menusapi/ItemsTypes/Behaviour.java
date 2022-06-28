package com.happiergore.menusapi.ItemsTypes;

import com.happiergore.menusapi.GUI;
import com.happiergore.menusapi.ItemGUI;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 *
 * @author HappierGore
 */
public abstract class Behaviour extends ItemGUI {

    public Behaviour(GUI inventory) {
        super(inventory);
    }

    public abstract void onClick(InventoryClickEvent e);

    public abstract void onLoad();

    @Override
    public void update() {
        super.update();
        this.onLoad();
    }

}
