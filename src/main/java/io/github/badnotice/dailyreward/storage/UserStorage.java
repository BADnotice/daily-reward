package io.github.badnotice.dailyreward.storage;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.github.badnotice.dailyreward.DailyRewardPlugin;
import io.github.badnotice.dailyreward.model.user.User;
import io.github.badnotice.dailyreward.model.user.UserReward;
import io.github.badnotice.dailyreward.sql.DailyRewardTable;

import java.util.concurrent.TimeUnit;

public final class UserStorage {

    private final  DailyRewardPlugin plugin;
    private final LoadingCache<String, User> users = CacheBuilder.newBuilder()
            .expireAfterAccess(15, TimeUnit.MINUTES)
            .build(new UserCacheLoader());

    public UserStorage(DailyRewardPlugin plugin) {
        this.plugin = plugin;
    }

    public User getUncheckedUser(String playerName) {
        return users.getUnchecked(playerName);
    }

    public void insertUser(String playerName, UserReward userReward) {
        DailyRewardTable dailyRewardTable = this.plugin.getDailyRewardTable();
        dailyRewardTable.insertOrReplaceUser(playerName, userReward);

        users.invalidate(playerName);
    }

    public void removeUser(String playerName) {
        DailyRewardTable dailyRewardTable = this.plugin.getDailyRewardTable();
        dailyRewardTable.deleteUser(playerName);

        users.invalidate(playerName);
    }

    public void invalidateUser(String playerName) {
        users.invalidate(playerName);
    }

}

@SuppressWarnings("NullableProblems")
class UserCacheLoader extends CacheLoader<String, User> {

    @Override
    public User load(String playerName) {
        DailyRewardTable dailyRewardTable = DailyRewardPlugin.getInstance().getDailyRewardTable();
        return new User(playerName, dailyRewardTable.findUser(playerName));
    }

}