package de.lumix.lumcraftserver.commands;

import de.lumix.lumcraftserver.mysql.MySqlGetter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {
    private MySqlGetter data;
    public CoinsCommand(MySqlGetter data) {
        this.data = data;
    }
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("lcs.main.coinscommand")) {
                    //COMMANDS
                    player.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Coins: " + data.getCoins(player.getUniqueId()));
                } else {
                    player.sendMessage(ChatColor.RED + "[LCS-MainPlugin] Du hast nicht genug Rechte!");
                }
            } else {
                sender.sendMessage(ChatColor.BLUE + "[LCS-MainPlugin] Böser Dev! Du kannst den Command nicht in der Console ausfüren!");
            }
            return false;
        }
    }

