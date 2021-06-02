package io.github.badnotice.dailyreward.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BukkitUtils {

    @SuppressWarnings("deprecation")
    public static boolean checkInventory(Player player, ItemStack itemStack) {
        PlayerInventory playerInventory = player.getInventory();

        if (playerInventory.firstEmpty() == -1) {
            Location playerLocation = player.getLocation();
            playerLocation.getWorld().dropItemNaturally(playerLocation, itemStack);
            player.sendMessage("§cSeu inventario está cheio, o item foi dropado no chão.");
            return false;
        }

        playerInventory.addItem(itemStack);
        return true;
    }

}
