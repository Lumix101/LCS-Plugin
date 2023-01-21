package de.lumix.lumcraftserver.utils;

import de.lumix.lumcraftserver.mysql.MySqlGetter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardMain {
    private MySqlGetter data;

    public ScoreboardMain(MySqlGetter data) {
        this.data = data;
    }

    public static void setScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("main", "dummy", "LCS-MC.de");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("LCS-MC.de").setScore(0);
        objective.getScore("&a").setScore(1);
        objective.getScore("").setScore(2);
        objective.getScore("Coins:").setScore(3);
        objective.getScore("&c").setScore(4);

        Team coinsTeam = scoreboard.registerNewTeam("coins");
        coinsTeam.addEntry(ChatColor.AQUA.toString() + ChatColor.RED);
        objective.getScore(ChatColor.AQUA.toString() + ChatColor.RED).setScore(2);

        int coins = data.getCoins(player.getUniqueId());
        coinsTeam.setPrefix(String.valueOf(coins));
    }
}
