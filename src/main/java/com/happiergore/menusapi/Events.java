package com.happiergore.menusapi;

import com.happiergore.menusapi.events.CloseGUI;
import com.happiergore.menusapi.events.OnClickGUI;
import com.happiergore.menusapi.events.OnPlayerChat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerChatEvent;

/**
 *
 * @author HappierGore
 */
public class Events implements Listener {

    private final static Events instance = new Events();

    public static Events getInstance() {
        return instance;
    }

    @EventHandler
    public void InventoryCloseEvent(InventoryCloseEvent e) {
        new CloseGUI().onCloseInv(e);
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e) {
        new OnClickGUI().onInventoryClick(e);
    }

    @EventHandler
    public void OnPlayerChat(PlayerChatEvent e) {
        new OnPlayerChat().OnPlayerChat(e);
    }
}
