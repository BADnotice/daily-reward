package io.github.badnotice.dailyreward.inventory;

import com.henryfabio.minecraft.inventoryapi.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.inventory.impl.simple.SimpleInventory;
import com.henryfabio.minecraft.inventoryapi.item.InventoryItem;
import com.henryfabio.minecraft.inventoryapi.viewer.Viewer;
import io.github.badnotice.dailyreward.DailyRewardPlugin;
import io.github.badnotice.dailyreward.api.event.impl.RewardRequestEvent;
import io.github.badnotice.dailyreward.configuration.ConfigValue;
import io.github.badnotice.dailyreward.model.Reward;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public final class RewardInventory extends SimpleInventory {

    public RewardInventory() {
        super(
                "rewardinventory.main",
                ConfigValue.get(ConfigValue::titleInventory),
                ConfigValue.get(ConfigValue::linesInventory) * 9
        );
    }

    @Override
    protected void configureInventory(@NotNull Viewer viewer, @NotNull InventoryEditor editor) {
        Collection<Reward> values = DailyRewardPlugin.getInstance().getRewardRegistry().getAll().values();
        for (Reward reward : values) {
            editor.setItem(reward.getInventorySlot(), inventoryItem(reward));
        }
    }

    private InventoryItem inventoryItem(Reward reward) {
        return InventoryItem.of(reward.getItemStack())
                .defaultCallback(event -> {
                    Player player = event.getPlayer();

                    if (!player.hasPermission(reward.getPermission())) {
                        player.sendMessage(ConfigValue.get(ConfigValue::noPermissionMessage)
                                .replace("{permission}", reward.getPermission())
                        );
                        return;
                    }

                    player.closeInventory();;

                    RewardRequestEvent rewardEvent = new RewardRequestEvent(
                            reward,
                            player,
                            ConfigValue.get(ConfigValue::rewardCollectedMessage)
                    );

                    rewardEvent.call();
                });
    }

}
