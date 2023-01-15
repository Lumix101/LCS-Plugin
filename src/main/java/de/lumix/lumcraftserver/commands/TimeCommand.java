package de.lumix.lumcraftserver.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("lcs.main.time")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("day")) {
                        player.setPlayerTime(1000, true);
                        player.sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] Es ist jetzt Tag!");
                    }else if (args[0].equalsIgnoreCase("night")) {
                        player.setPlayerTime(12000, true);
                        player.sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] Es ist jetzt Nacht!");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "[LCS-MainPlugin] Du hast nicht genug Rechte!");
            }
        }
        return false;
    }
}
