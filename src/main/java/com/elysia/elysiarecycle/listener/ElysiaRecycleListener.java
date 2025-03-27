package com.elysia.elysiarecycle.listener;

import com.elysia.elysiarecycle.ElysiaRecycle;
import com.elysia.elysiarecycle.override.RecycleGuiHolder;
import com.elysia.elysiarecycle.scheduler.RecycleGuiUpdate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
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
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR || !inventory.getItem(i).hasItemMeta()) continue;
            List<String> lore = inventory.getItem(i).getItemMeta().getLore();
            if (lore == null || lore.isEmpty()) {
                items.add(inventory.getItem(i));
                continue;
            }
            boolean flag = true;
            for (String line : lore) {
                if (line.contains(ElysiaRecycle.getConfigManager().getConfigData().getMatch() + ":")) {
                    flag = false;
                    break;
                }
            }
            if (flag) items.add(inventory.getItem(i));
        }
        Player player = Bukkit.getPlayer(uuid);
        if (!items.isEmpty()) {
            PlayerInventory playerInventory = player.getInventory();
            for (ItemStack item : items) {
                if (playerInventory.firstEmpty() != -1) playerInventory.addItem(item);
                else player.getWorld().dropItemNaturally(player.getLocation(), item);
            }
        }
        player.closeInventory();
        ElysiaRecycle.getEconomy().depositPlayer(player, ElysiaRecycle.getPlayerManager().getPlayerRecycleCounter(uuid));
        player.sendMessage(
                ElysiaRecycle.getConfigManager().getConfigData().getPrefix() +
                        ElysiaRecycle.getConfigManager().getConfigData().getMessages().get("recycle")
                                .replaceAll("%number%", String.valueOf(ElysiaRecycle.getPlayerManager().getPlayerRecycleItemNumber(uuid)))
                                .replaceAll("%count%", String.valueOf(ElysiaRecycle.getPlayerManager().getPlayerRecycleCounter(uuid)))

        );
    }
    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent event){
        if (!(event.getInventory().getHolder() instanceof  RecycleGuiHolder)) return;
        RecycleGuiUpdate.stop(event.getPlayer().getUniqueId());
    }
}
