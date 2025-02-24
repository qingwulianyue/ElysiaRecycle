package com.elysia.elysiarecycle.guimanager.gui;

import com.elysia.elysiarecycle.ElysiaRecycle;
import com.elysia.elysiarecycle.override.RecycleGuiHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RecycleGui {
    private Inventory inventory;
    public void create(){
        inventory = Bukkit.createInventory(new RecycleGuiHolder(), 45, "回收界面");
        ItemStack confirmItem = new ItemStack(Material.PAPER, 1);
        ItemMeta confirmMeta = confirmItem.getItemMeta();
        confirmMeta.setDisplayName(ElysiaRecycle.getConfigManager().getConfigData().getTips().get("confirm").replaceAll("%count%", "0"));
        confirmItem.setItemMeta(confirmMeta);
        inventory.setItem(40, confirmItem);
    }
    public void openInventory(String name){
        Bukkit.getPlayer(name).openInventory(inventory);
    }
}
