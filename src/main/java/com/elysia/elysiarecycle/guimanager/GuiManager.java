package com.elysia.elysiarecycle.guimanager;

import com.elysia.elysiarecycle.ElysiaRecycle;
import com.elysia.elysiarecycle.guimanager.gui.RecycleGui;
import org.bukkit.scheduler.BukkitRunnable;

public class GuiManager {
    private GuiManager() {}
    private final static GuiManager instance = new GuiManager();
    public static GuiManager getInstance() {
        return instance;
    }
    public void openGui(String playerName){
        new BukkitRunnable(){

            @Override
            public void run() {
                RecycleGui recycleGui = new RecycleGui();
                recycleGui.create();
                recycleGui.openInventory(playerName);
            }
        }.runTaskAsynchronously(ElysiaRecycle.getInstance());
    }
}
