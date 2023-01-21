package de.lumix.lumcraftserver.scoreboard;

import de.lumix.lumcraftserver.mysql.MySqlGetter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class MainScorebaord {

    private MySqlGetter DATA;

    public MainScorebaord(MySqlGetter data) {
        this.DATA = data;
    }
    public void setScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("MainScorebaord", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.BLUE.toString() + ChatColor.BOLD + "LCS-MC.de");

        Team coins = board.registerNewTeam("coins");

        obj.getScore(ChatColor.GOLD + "Coins").setScore(4);
        obj.getScore(ChatColor.YELLOW + "").setScore(3);

        obj.getScore(ChatColor.RED + "").setScore(2); //Platzhalter

        obj.getScore(ChatColor.WHITE + "Server-Adresse:").setScore(1);
        obj.getScore(ChatColor.WHITE + "LCS-MC.de").setScore(0);

        coins.addEntry(ChatColor.YELLOW.toString() + DATA.getCoins(player.getUniqueId()));

        player.setScoreboard(board);
    }

    public void updateScoreboard(Player player){
        Scoreboard board = player.getScoreboard();
        Team coins = board.getTeam("coins");

        coins.setPrefix(ChatColor.YELLOW.toString() + DATA.getCoins(player.getUniqueId()));

    }
}
