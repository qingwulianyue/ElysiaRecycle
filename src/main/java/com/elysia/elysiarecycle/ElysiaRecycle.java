package com.elysia.elysiarecycle;

import com.elysia.elysiarecycle.command.CommandManager;
import com.elysia.elysiarecycle.command.CommandTabComplete;
import com.elysia.elysiarecycle.command.subcommands.HelpCommand;
import com.elysia.elysiarecycle.command.subcommands.ReloadCommand;
import com.elysia.elysiarecycle.filemanager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElysiaRecycle extends JavaPlugin {
    private static ElysiaRecycle instance;
    private static ConfigManager configManager;
    public static ElysiaRecycle getInstance() {
        return instance;
    }
    public static ConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        configManager = ConfigManager.getInstance();
        saveDefaultConfig();
        configManager.load();
        Bukkit.getPluginCommand("ElysiaRecycle").setExecutor(new CommandManager());
        Bukkit.getPluginCommand("ElysiaRecycle").setTabCompleter(new CommandTabComplete());
        new HelpCommand().register();
        new ReloadCommand().register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
