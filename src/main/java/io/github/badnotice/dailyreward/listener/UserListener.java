package io.github.badnotice.dailyreward.listener;

import io.github.badnotice.dailyreward.DailyRewardPlugin;
import io.github.badnotice.dailyreward.api.event.impl.RewardExpiredEvent;
import io.github.badnotice.dailyreward.configuration.ConfigValue;
import io.github.badnotice.dailyreward.model.user.UserReward;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public final class UserListener implements Listener {

    private final DailyRewardPlugin plugin;

    public UserListener(DailyRewardPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();

        UserReward userReward = this.plugin.getUserStorage().getUncheckedUser(player.getName()).getUserReward();
        if (userReward == null) return;

        if (userReward.isExpired()) {
            RewardExpiredEvent expiredEvent = new RewardExpiredEvent(
                    userReward.getReward(),
                    player,
                    ConfigValue.get(ConfigValue::rewardExpiredMessage)
            );

            expiredEvent.call();
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        UserReward userReward = this.plugin.getUserStorage().getUncheckedUser(player.getName()).getUserReward();
        if (userReward == null) return;

        if (userReward.isExpired()) {
            RewardExpiredEvent expiredEvent = new RewardExpiredEvent(
                    userReward.getReward(),
                    player,
                    ConfigValue.get(ConfigValue::rewardExpiredMessage)
            );

            expiredEvent.call();
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.plugin.getUserStorage().invalidateUser(player.getName());
    }

}
