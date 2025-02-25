package com.elysia.elysiarecycle.filemanager;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {
    private PlayerManager(){}
    private static final PlayerManager instance = new PlayerManager();
    private static final HashMap<UUID, Double> playerRecycleItemNumber = new HashMap<>();
    private static final HashMap<UUID, Double> playerRecycleCounter = new HashMap<>();
    private static final HashMap<UUID, Integer> playerRecycleTask = new HashMap<>();
    public static PlayerManager getInstance() {
        return instance;
    }
    public Double getPlayerRecycleItemNumber(UUID uuid) {
        return playerRecycleItemNumber.getOrDefault(uuid, 0.0);
    }
    public void setPlayerRecycleItemNumber(UUID uuid, Double number) {
        playerRecycleItemNumber.put(uuid, number);
    }
    public Double getPlayerRecycleCounter(UUID uuid) {
        return playerRecycleCounter.getOrDefault(uuid, 0.0);
    }
    public void setPlayerRecycleCounter(UUID uuid, Double number) {
        playerRecycleCounter.put(uuid, number);
    }
    public Integer getPlayerRecycleTask(UUID uuid) {
        return playerRecycleTask.get(uuid);
    }
    public void setPlayerRecycleTask(UUID uuid, Integer taskId) {
        playerRecycleTask.put(uuid, taskId);
    }
    public void removePlayerRecycleTask(UUID uuid) {
        playerRecycleTask.remove(uuid);
    }
}
