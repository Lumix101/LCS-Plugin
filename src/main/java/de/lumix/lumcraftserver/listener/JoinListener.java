package de.lumix.lumcraftserver.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener  implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage("Ein Typ ist beigetreten!");

        player.sendMessage("########################### \n" + player.getName() + "\n ###########################");
    }

}

