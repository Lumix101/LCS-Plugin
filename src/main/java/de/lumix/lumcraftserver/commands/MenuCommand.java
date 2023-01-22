package de.lumix.lumcraftserver.commands;

import de.lumix.lumcraftserver.mysql.MySqlGetter;
import de.lumix.lumcraftserver.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static org.bukkit.Bukkit.createInventory;

public class MenuCommand implements CommandExecutor {
    private MySqlGetter data;
    public MenuCommand(MySqlGetter data) {
        this.data = data;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("lcs.main.menucommand")) {
                //COMMANDS
                Inventory inventory = Bukkit.createInventory(null, 9*3, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Main Menu");
                inventory.setItem(11, new ItemBuilder(Material.PAPER).setDisplayname(ChatColor.GREEN.toString() + ChatColor.BOLD + "Spielerinfo").setLore(ChatColor.GOLD.toString() + ChatColor.BOLD + "Spielername:" + player.getName(),ChatColor.RED.toString() + ChatColor.BOLD + "UUID:" + player.getUniqueId() ,ChatColor.YELLOW.toString() + ChatColor.BOLD + "Coins:" + data.getCoins(player.getUniqueId())).build());

                inventory.setItem(15, new ItemBuilder(Material.PAPER).setDisplayname(ChatColor.GREEN.toString() + ChatColor.BOLD + "Spielerinfo").setLore(ChatColor.GOLD.toString() + ChatColor.BOLD + "Spielername:" + player.getName(),ChatColor.RED.toString() + ChatColor.BOLD + "UUID:" + player.getUniqueId() ,ChatColor.YELLOW.toString() + ChatColor.BOLD + "Coins:" + data.getCoins(player.getUniqueId())).build());


                player.openInventory(inventory);
            } else {
                player.sendMessage(ChatColor.RED + "[LCS-MainPlugin] Du hast nicht genug Rechte!");
            }
        } else {
            sender.sendMessage(ChatColor.BLUE + "[LCS-MainPlugin] Böser Dev! Du kannst den Command nicht in der Console ausfüren!");
        }
        return false;
    }
}
