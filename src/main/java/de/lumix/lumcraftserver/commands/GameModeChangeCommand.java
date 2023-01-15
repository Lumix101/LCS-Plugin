package de.lumix.lumcraftserver.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeChangeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("lcs.main.gamemodechage")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("1")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] Dein Gamemode ist jetzt Creative");
                    }else if (args[0].equalsIgnoreCase("2")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] Dein Gamemode ist jetzt Survival");
                    }else if (args[0].equalsIgnoreCase("3")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] Dein Gamemode ist jetzt Spectator");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "[LCS-MainPlugin] Bitte verwende /gm <GAMEMODE>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "[LCS-MainPlugin] Du hast nicht genug Rechte!");
            }
        } else {
            sender.sendMessage(ChatColor.BLUE + "[LCS-MainPlugin] Böser Dev! Du kannst den Command nicht in der Console ausfüren!");
        }
        return false;
    }
}
