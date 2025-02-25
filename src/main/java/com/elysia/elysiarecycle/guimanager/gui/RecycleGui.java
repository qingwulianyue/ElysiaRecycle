package com.elysia.elysiarecycle.guimanager.gui;

import com.elysia.elysiarecycle.ElysiaRecycle;
import com.elysia.elysiarecycle.override.RecycleGuiHolder;
import com.elysia.elysiarecycle.scheduler.RecycleGuiUpdate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class RecycleGui {
    private Inventory inventory;
    public void create(){
        inventory = Bukkit.createInventory(new RecycleGuiHolder(), 45, "回收界面");
        ItemStack confirmItem = new ItemStack(Material.PAPER, 1);
        ItemMeta confirmMeta = confirmItem.getItemMeta();
        confirmMeta.setDisplayName(ElysiaRecycle.getConfigManager().getConfigData().getTips().get("confirm").replaceAll("%count%", "0"));
        confirmItem.setItemMeta(confirmMeta);
        inventory.setItem(40, confirmItem);
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        for (int i = 36;i <= 44;i++){
            if (i != 40)
                inventory.setItem(i, itemStack);
        }
    }
    public void openInventory(String name){
        UUID uuid = Bukkit.getPlayer(name).getUniqueId();
        ElysiaRecycle.getPlayerManager().setPlayerRecycleItemNumber(uuid, 0.0);
        ElysiaRecycle.getPlayerManager().setPlayerRecycleCounter(uuid, 0.0);
        Bukkit.getPlayer(name).openInventory(inventory);
        RecycleGuiUpdate.start(uuid);
    }
}
