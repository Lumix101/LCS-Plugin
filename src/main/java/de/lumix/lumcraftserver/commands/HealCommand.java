package de.lumix.lumcraftserver.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("lcs.main.heal")) {
                if (args.length == 0){
                    player.setHealth(20);
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] Du hast dich geheielt!");
                } else if (args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        target.setHealth(20);
                        target.setFoodLevel(20);
                        target.sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] Du wurdest geheilt von " + player.getName() + "!");

                    } else {
                        player.sendMessage(ChatColor.RED + "[LCS-MainPlugin] Spieler " + args[0] + " wurde nicht gefunden!");
                    }
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
