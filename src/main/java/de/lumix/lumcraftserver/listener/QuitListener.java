package de.lumix.lumcraftserver.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerQuitEvent event) {
        event.setQuitMessage("");
    }
}
