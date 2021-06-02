package io.github.badnotice.dailyreward.listener;

import io.github.badnotice.dailyreward.DailyRewardPlugin;
import io.github.badnotice.dailyreward.api.event.impl.RewardExpiredEvent;
import io.github.badnotice.dailyreward.api.event.impl.RewardRequestEvent;
import io.github.badnotice.dailyreward.configuration.ConfigValue;
import io.github.badnotice.dailyreward.model.Reward;
import io.github.badnotice.dailyreward.model.user.User;
import io.github.badnotice.dailyreward.model.user.UserReward;
import io.github.badnotice.dailyreward.util.NumberFormatterUtils;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.concurrent.TimeUnit;

public final class RewardListener implements Listener {

    private final DailyRewardPlugin plugin;

    public RewardListener(DailyRewardPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onRewardCollected(RewardRequestEvent event) {
        Player player = event.getPlayer();
        User user = this.plugin.getUserStorage().getUncheckedUser(player.getName());

        UserReward userReward = user.getUserReward();
        if (userReward != null) {
            player.sendMessage(ConfigValue.get(ConfigValue::alreadyCollectedMessage)
                    .replace("{time-left}", NumberFormatterUtils.format(userReward.getExpireAt()))
                    .replace("{reward-name}", event.getReward().getIdentifier())
            );
            return;
        }

        Reward reward = event.getReward();
        reward.executeActions(player);

        player.sendMessage(event.getCollectedMessage()
                .replace("{reward-name}", reward.getIdentifier())
        );

        insertUser(player, reward);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onRewardExpired(RewardExpiredEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(event.getExpiredMessage()
                .replace("{reward-name}", event.getReward().getIdentifier())
        );
        this.plugin.getUserStorage().removeUser(player.getName());
    }

    protected void insertUser(@NonNull Player player, @NonNull Reward reward) {
        this.plugin.getUserStorage().insertUser(player.getName(),
                new UserReward(
                        reward,
                        System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)
                ));
    }

}
