package com.elysia.elysiarecycle.filemanager;

import com.elysia.elysiarecycle.ElysiaRecycle;
import com.elysia.elysiarecycle.filemanager.data.ConfigData;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class ConfigManager {
    private ConfigManager(){}
    private static final ConfigManager instance = new ConfigManager();
    private ConfigData configData;
    private final ElysiaRecycle plugin = ElysiaRecycle.getInstance();
    public static ConfigManager getInstance(){
        return instance;
    }
    public ConfigData getConfigData(){
        if (configData == null)
            load();
        return configData;
    }
     public void load(){
         configData = null;
         plugin.reloadConfig();
         FileConfiguration config = plugin.getConfig();
         HashMap<String, String> tips = new HashMap<>();
         HashMap<String, String> messages = new HashMap<>();
         for (String key : config.getConfigurationSection("tips").getKeys(false)) {
             tips.put(key, config.getString("tips." + key));
         }
         for (String key : config.getConfigurationSection("messages").getKeys(false)) {
             messages.put(key, config.getString("messages." + key));
         }
         configData = new ConfigData(
                 config.getBoolean("debug"),
                 config.getString("prefix"),
                 config.getString("match"),
                 tips,
                 messages
         );
         logConfigInfoIfDebug();
     }
    private void logConfigInfoIfDebug(){
        if (configData.isDebug()){
            plugin.getLogger().info("§eDebug: §a" + configData.isDebug());
            plugin.getLogger().info("§ePrefix: §a" + configData.getPrefix());
            plugin.getLogger().info("§eMatch: §a" + configData.getMatch());
            plugin.getLogger().info("§eTips:");
            for (String key : configData.getTips().keySet())
                plugin.getLogger().info("§e" + key + ": §a" + configData.getTips().get(key));
            plugin.getLogger().info("§eMessages:");
            for (String key : configData.getMessages().keySet())
                plugin.getLogger().info("§e" + key + ": §a" + configData.getMessages().get(key));
        }
    }
}
