package com.elysia.elysiarecycle.listener;

import com.elysia.elysiarecycle.ElysiaRecycle;
import com.elysia.elysiarecycle.override.RecycleGuiHolder;
import com.elysia.elysiarecycle.scheduler.RecycleGuiUpdate;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import java.util.UUID;

public class ElysiaRecycleListener implements Listener {
    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent event){
        if (!(event.getInventory().getHolder() instanceof RecycleGuiHolder)) return;
        if (event.getClickedInventory() != event.getView().getTopInventory()) return;
        int slot = event.getSlot();
        if (36 <= slot && slot <= 44) {
            event.setCancelled(true);
            if (slot == 40) clickConfirm(event.getClickedInventory(), event.getWhoClicked().getUniqueId());
        }
    }
    private void clickConfirm(Inventory inventory, UUID uuid){
        System.out.println(ElysiaRecycle.getPlayerManager().getPlayerRecycleCounter(uuid));
        System.out.println(ElysiaRecycle.getPlayerManager().getPlayerRecycleItemNumber(uuid));
    }
    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent event){
        if (!(event.getInventory().getHolder() instanceof  RecycleGuiHolder)) return;
        RecycleGuiUpdate.stop(event.getPlayer().getUniqueId());
    }
}
