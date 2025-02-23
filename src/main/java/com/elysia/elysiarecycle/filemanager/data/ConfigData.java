package com.elysia.elysiarecycle.filemanager.data;

import java.util.HashMap;

public class ConfigData {
    private final boolean debug;
    private final String prefix;
    private final String match;
    private final HashMap<String, String> tips;
    private final HashMap<String, String> messages;

    public ConfigData(boolean debug, String prefix, String match, HashMap<String, String> tips, HashMap<String, String> messages) {
        this.debug = debug;
        this.prefix = prefix;
        this.match = match;
        this.tips = tips;
        this.messages = messages;
    }

    public boolean isDebug() {
        return debug;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getMatch() {
        return match;
    }

    public HashMap<String, String> getTips() {
        return tips;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }
}
