package io.github.badnotice.dailyreward.sql;

import com.henryfabio.sqlprovider.executor.SQLExecutor;
import io.github.badnotice.dailyreward.DailyRewardPlugin;
import io.github.badnotice.dailyreward.model.user.UserReward;
import io.github.badnotice.dailyreward.sql.adapter.DailyRewardSQLAdapter;

import java.util.concurrent.TimeUnit;

public final class DailyRewardTable {

    private final DailyRewardPlugin plugin;
    private final String table = "reward_players";

    public DailyRewardTable(DailyRewardPlugin plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        this.executor().updateQuery(
                "CREATE TABLE IF NOT EXISTS " + table + "(" +
                        "user_name VARCHAR(16) NOT NULL PRIMARY KEY," +
                        "reward_id NOT NULL," +
                        "expire_at INTEGER NOT NULL" +
                        ");"
        );
    }

    public UserReward findUser(String playerName) {
        return this.executor().resultOneQuery(
                "SELECT * FROM " + table + " WHERE user_name = ?",
                statement -> statement.set(1, playerName),
                DailyRewardSQLAdapter.class
        );
    }

    public void insertOrReplaceUser(String playerName, UserReward userReward) {
        this.executor().updateQuery(
                "REPLACE INTO " + table + " VALUES(?, ?, ?)",
                statement -> {
                    statement.set(1, playerName);
                    statement.set(2, userReward.getReward().getIdentifier());
                    statement.set(3, TimeUnit.MILLISECONDS.toSeconds(userReward.getExpireAt()));
                }
        );
    }

    public void deleteUser(String playerName) {
        this.executor().updateQuery(
                "DELETE FROM " + table + " WHERE user_name = ?",
                statement -> statement.set(1, playerName)
        );
    }

    private SQLExecutor executor() {
        return new SQLExecutor(this.plugin.getSqlConnector());
    }

}
