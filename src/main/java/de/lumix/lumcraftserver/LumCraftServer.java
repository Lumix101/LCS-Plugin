package de.lumix.lumcraftserver;

import de.lumix.lumcraftserver.commands.FlyCommand;
import de.lumix.lumcraftserver.commands.GameModeChangeCommand;
import de.lumix.lumcraftserver.commands.HealCommand;
import de.lumix.lumcraftserver.listener.JoinListener;
import de.lumix.lumcraftserver.listener.QuitListener;
import de.lumix.lumcraftserver.mysql.MySql;
import de.lumix.lumcraftserver.mysql.MySqlGetter;
import de.lumix.lumcraftserver.scoreboard.MainScorebaord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class LumCraftServer extends JavaPlugin {

    public static FileConfiguration cfg;

    public MySql SQL;
    public MySqlGetter DATA;

    private static LumCraftServer instance;
    public  MainScorebaord mainScorebaord = new MainScorebaord(DATA);

    @Override
    public void onEnable() {

        instance = this;
        loadConfig();
        cfg = getConfig();

        // Plugin Start Message
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] ###########################");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] # Plugin wurde gestartet! #");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] ###########################");

        //SQL Connect
        this.SQL = new MySql();
        this.DATA = new MySqlGetter(this);

        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[LCS-MainPlugin] Es konnte keine Verbindung mit der Datenbank aufgebaut werden!");
        }

        if(SQL.isConnected()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LCS-MainPlugin] Verbindung zu Datenbank wurde erfolgreich hergestellt!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "");
            DATA.createTable();
        }

        //Register Listener
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(DATA), this);
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

        //SQL Disconnect
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[LCS-MainPlugin] Verbindung zu Datenbank wurde erfolgreich geschlossen!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "");
        SQL.discconnect();
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static LumCraftServer getInstance(){
        return instance;
    }
}