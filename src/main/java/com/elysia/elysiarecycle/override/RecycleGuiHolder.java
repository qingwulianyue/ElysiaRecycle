package com.elysia.elysiarecycle.override;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class RecycleGuiHolder implements InventoryHolder {
    @Override
    public Inventory getInventory() {
        return null;
    }
}
