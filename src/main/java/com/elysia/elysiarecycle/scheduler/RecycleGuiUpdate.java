package com.elysia.elysiarecycle.scheduler;

import com.elysia.elysiarecycle.ElysiaRecycle;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecycleGuiUpdate {
    public static void start(UUID uuid){
        int taskId = Bukkit.getScheduler().runTaskTimerAsynchronously(ElysiaRecycle.getInstance(), () -> {
            Inventory inventory = Bukkit.getPlayer(uuid).getOpenInventory().getTopInventory();
            double valueResult = 0;
            double numberResult = 0;
            for (ItemStack itemStack : inventory.getContents()){
                if (itemStack == null || itemStack.getType() == Material.AIR) continue;
                double value = parseItem(itemStack);
                if (value == 0) continue;
                valueResult += value;
                numberResult += itemStack.getAmount();
            }
            ElysiaRecycle.getPlayerManager().setPlayerRecycleCounter(uuid, valueResult);
            ElysiaRecycle.getPlayerManager().setPlayerRecycleItemNumber(uuid, numberResult);
            updateGui(inventory, uuid);
        }, 0L, 20L).getTaskId();
        ElysiaRecycle.getPlayerManager().setPlayerRecycleTask(uuid, taskId);
    }
    public static void stop(UUID uuid){
        int taskId = ElysiaRecycle.getPlayerManager().getPlayerRecycleTask(uuid);
        Bukkit.getScheduler().cancelTask(taskId);
        ElysiaRecycle.getPlayerManager().removePlayerRecycleTask(uuid);
    }
    private static double parseItem(ItemStack item){
        double result = 0;
        double value = 0;
        String match = ElysiaRecycle.getConfigManager().getConfigData().getMatch();
        List<String> lore = item.getLore();
        if (lore == null) return result;
        for (String line : lore){
            if (!line.contains(match)) continue;
            String regex = match + ":\\s*(\\d+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) continue;
            value = Double.parseDouble(matcher.group(1));
            break;
        }
        result = value * item.getAmount();
        return result;
    }
    private static void updateGui(Inventory inventory, UUID uuid){
        ItemStack itemStack = inventory.getItem(40);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(
                ElysiaRecycle.getConfigManager().getConfigData().getTips().get("confirm")
                        .replaceAll("%count%", String.valueOf(ElysiaRecycle.getPlayerManager().getPlayerRecycleCounter(uuid)))
                        .replaceAll("%number%", String.valueOf(ElysiaRecycle.getPlayerManager().getPlayerRecycleItemNumber(uuid)))
        );
        itemStack.setItemMeta(itemMeta);
    }
}
