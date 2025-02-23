package com.elysia.elysiarecycle;

import com.elysia.elysiarecycle.command.CommandManager;
import com.elysia.elysiarecycle.command.CommandTabComplete;
import com.elysia.elysiarecycle.command.subcommands.HelpCommand;
import com.elysia.elysiarecycle.command.subcommands.OpenCommand;
import com.elysia.elysiarecycle.command.subcommands.ReloadCommand;
import com.elysia.elysiarecycle.filemanager.ConfigManager;
import com.elysia.elysiarecycle.guimanager.GuiManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElysiaRecycle extends JavaPlugin {
    private static ElysiaRecycle instance;
    private static ConfigManager configManager;
    private static GuiManager guiManager;
    public static ElysiaRecycle getInstance() {
        return instance;
    }
    public static ConfigManager getConfigManager() {
        return configManager;
    }
    public static GuiManager getGuiManager() {
        return guiManager;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        configManager = ConfigManager.getInstance();
        guiManager = GuiManager.getInstance();
        saveDefaultConfig();
        configManager.load();
        Bukkit.getPluginCommand("ElysiaRecycle").setExecutor(new CommandManager());
        Bukkit.getPluginCommand("ElysiaRecycle").setTabCompleter(new CommandTabComplete());
        new HelpCommand().register();
        new ReloadCommand().register();
        new OpenCommand().register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
