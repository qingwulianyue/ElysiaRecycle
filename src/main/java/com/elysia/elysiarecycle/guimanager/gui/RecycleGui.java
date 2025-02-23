package com.elysia.elysiarecycle.guimanager.gui;

import com.elysia.elysiarecycle.override.RecycleGuiHolder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class RecycleGui {
    private Inventory inventory;
    public void create(){
        inventory = Bukkit.createInventory(new RecycleGuiHolder(), 45, "回收界面");
    }
    public void openInventory(String name){
        Bukkit.getPlayer(name).openInventory(inventory);
    }
}
