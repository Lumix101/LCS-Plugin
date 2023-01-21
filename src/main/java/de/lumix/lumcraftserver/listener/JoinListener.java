package de.lumix.lumcraftserver.listener;

import de.lumix.lumcraftserver.LumCraftServer;
import de.lumix.lumcraftserver.mysql.MySqlGetter;
import de.lumix.lumcraftserver.scoreboard.MainScorebaord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener  implements Listener {
    private MySqlGetter DATA;

    public JoinListener(MySqlGetter data) {
        this.DATA = data;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage("");

        player.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Wilkommen, "+ player.getName() + " Viel Spaß und viel " + ChatColor.GREEN + ChatColor.BOLD + "Erfolg!");

        DATA.createPlayer(player);
    }

}


