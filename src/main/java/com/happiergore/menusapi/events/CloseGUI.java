package com.happiergore.menusapi.events;

import com.happiergore.menusapi.GUI;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 *
 * @author HappierGore
 */
public class CloseGUI {

    public void onCloseInv(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() == null) {
            return;
        }

        if (e.getInventory().getHolder() instanceof GUI) {
            ((GUI) e.getInventory().getHolder()).close(e);
        }
    }
}
