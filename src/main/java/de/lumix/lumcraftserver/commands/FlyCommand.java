package de.lumix.lumcraftserver.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("lcs.main.flycommand")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("on")) {
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        player.sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] Du kannst jetzt Fliegen!");
                    }else if (args[0].equalsIgnoreCase("off")) {
                        player.setAllowFlight(false);
                        player.setFlying(false);
                        player.sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] Du kannst jetzt nicht mehr Fliegen!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "[LCS-MainPlugin] Bitte verwende /fly <Boolen>");
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
