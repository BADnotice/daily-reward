package io.github.badnotice.dailyreward.api;

import io.github.badnotice.dailyreward.DailyRewardPlugin;
import io.github.badnotice.dailyreward.model.user.User;
import io.github.badnotice.dailyreward.model.user.UserReward;
import io.github.badnotice.dailyreward.storage.UserStorage;
import io.github.badnotice.dailyreward.util.NumberFormatterUtils;
import lombok.Data;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@Data(staticConstructor = "of")
public final class DailyRewardAPI {

    private final String playerName;

    public static DailyRewardAPI of(Player player) {
        return of(player.getName());
    }

    public static DailyRewardAPI of(OfflinePlayer offlinePlayer) {
        return of(offlinePlayer.getName());
    }

    /**
     * Check if the player has received the reward.
     *
     * @return Sucess or Failure
     */
    public boolean isCollectedReward() {
        UserStorage userStorage = DailyRewardPlugin.getInstance().getUserStorage();
        return userStorage.getUncheckedUser(this.playerName) != null;
    }

    /**
     * Get collect reward
     *
     * @return User Object
     */
    public User getUser() {
        UserStorage userStorage = DailyRewardPlugin.getInstance().getUserStorage();
        return userStorage.getUncheckedUser(this.playerName);
    }

    /**
     * Get time remaining formatted from collected reward
     *
     * @return Time left formatted
     */
    public String getTimeLeftFormatted() {
        UserReward userReward = getUser().getUserReward();
        if (userReward == null)
            throw new NullPointerException("UserReward is null");

        return NumberFormatterUtils.format(userReward.getExpireAt());
    }

    /**
     * Get time remaining from collected reward
     *
     * @return Time left
     */
    public long getTimeLeft() {
        UserReward userReward = getUser().getUserReward();
        if (userReward == null)
            throw new NullPointerException("UserReward is null");

        return userReward.getExpireAt();
    }

}
