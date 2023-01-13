package de.lumix.lumcraftserver;

import org.bukkit.plugin.java.JavaPlugin;

public final class LumCraftServer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin Wurde gestartet!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
