package com.happiergore.menusapi.events;

import com.happiergore.menusapi.GUI;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 *
 * @author HappierGore
 */
public class OnClickGUI {

    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getClickedInventory() == null || e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getInventory().getHolder() == null) {
            return;
        }

        if (e.getInventory().getHolder() instanceof GUI) {
            ((GUI) e.getInventory().getHolder()).onInventoryClick(e);
        }
    }

}
