package de.lumix.lumcraftserver;

import de.lumix.lumcraftserver.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LumCraftServer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin Start Message
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] ###########################");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] # Plugin wurde gestartet! #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] ###########################");

        //Register Listener
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[LCS-MainPlugin] ###########################");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[LCS-MainPlugin] #  Plugin wurde gestoppt! #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[LCS-MainPlugin] ###########################");
    }
}