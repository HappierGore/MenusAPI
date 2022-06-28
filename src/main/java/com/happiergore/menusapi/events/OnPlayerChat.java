package com.happiergore.menusapi.events;

import static com.happiergore.menusapi.ItemsTypes.TextRequest.StandingBy;
import org.bukkit.event.player.PlayerChatEvent;

/**
 *
 * @author HappierGore
 */
public class OnPlayerChat {

    public void OnPlayerChat(PlayerChatEvent e) {
        if (StandingBy.containsKey(e.getPlayer())) {
            StandingBy.get(e.getPlayer()).onTextEntry(e);
        }
    }
}
