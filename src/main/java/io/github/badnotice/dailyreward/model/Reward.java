package io.github.badnotice.dailyreward.model;

import io.github.badnotice.dailyreward.enums.RewardType;
import io.github.badnotice.dailyreward.util.BukkitUtils;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

@Data
public final class Reward {

    private final String identifier;
    private final String permission;

    private final Set<String> commands;
    private final Set<ItemStack> items;

    private final ItemStack itemStack;
    private final int inventorySlot;

    private final RewardType type;

    public void executeActions(Player player) {
        switch (this.type) {
            case COMMAND:
                for (String command : this.getCommands()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName()));
                }
                break;

            case ITEM:
                for (ItemStack item : this.items) {
                    BukkitUtils.checkInventory(player, item);
                }
                break;

            case ALL:
                for (String command : this.getCommands()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName()));
                }

                for (ItemStack item : this.items) {
                    BukkitUtils.checkInventory(player, item);
                }
                break;
        }
    }

}
