package com.happiergore.menusapi.ItemsTypes;

import com.happiergore.menusapi.GUI;
import com.happiergore.menusapi.ItemGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Item to open "Create kit" menu
 * @author HappierGore
 */
public abstract class OpenMenu extends Behaviour {

    private final GUI toOpen;
    private final Player player;

    public OpenMenu(GUI invOrigin, GUI toOpen, Player player) {
        super(invOrigin);
        this.loadMainItem();
        this.toOpen = toOpen;
        this.player = player;
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        this.toOpen.open();
    }

    public Player getPlayer() {
        return this.player;
    }

}
