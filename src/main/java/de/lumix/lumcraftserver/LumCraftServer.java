package de.lumix.lumcraftserver;

import de.lumix.lumcraftserver.commands.FlyCommand;
import de.lumix.lumcraftserver.commands.GameModeChangeCommand;
import de.lumix.lumcraftserver.commands.HealCommand;
import de.lumix.lumcraftserver.listener.JoinListener;
import de.lumix.lumcraftserver.listener.QuitListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LumCraftServer extends JavaPlugin {

    public static FileConfiguration cfg;

    @Override
    public void onEnable() {

        loadConfig();
        cfg = getConfig();

        // Plugin Start Message
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] ###########################");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] # Plugin wurde gestartet! #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] ###########################");

        //Register Listener
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);
        manager.registerEvents(new QuitListener(), this);

        //Register Commands
        (getCommand("heal")).setExecutor(new HealCommand());
        (getCommand("gm")).setExecutor(new GameModeChangeCommand());
        (getCommand("fly")).setExecutor(new FlyCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[LCS-MainPlugin] ###########################");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[LCS-MainPlugin] #  Plugin wurde gestoppt! #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[LCS-MainPlugin] ###########################");
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}