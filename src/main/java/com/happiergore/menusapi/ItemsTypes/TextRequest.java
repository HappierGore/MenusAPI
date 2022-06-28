package com.happiergore.menusapi.ItemsTypes;

import com.happiergore.menusapi.GUI;
import com.happiergore.menusapi.Utils.PlayerUtils;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;

/**
 *
 * @author HappierGore
 */
public abstract class TextRequest extends Behaviour {

    public static Map<Player, TextRequest> StandingBy = new HashMap<>();
    private PlayerUtils player;

    private String text = "";
    private final String msgRequest;

    public TextRequest(GUI inventory, String msgRequest) {
        super(inventory);
        this.msgRequest = msgRequest;
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        this.player = new PlayerUtils((Player) e.getWhoClicked());
        this.player.get().closeInventory();
        this.player.sendColoredMsg(msgRequest);
        StandingBy.put(this.player.get(), this);
    }

    public void onTextEntry(PlayerChatEvent e) {
        this.text = e.getMessage();
        StandingBy.remove(this.player.get());
        e.setCancelled(true);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
