package io.github.badnotice.dailyreward.sql.adapter;

import com.henryfabio.sqlprovider.executor.adapter.SQLResultAdapter;
import com.henryfabio.sqlprovider.executor.result.SimpleResultSet;
import io.github.badnotice.dailyreward.DailyRewardPlugin;
import io.github.badnotice.dailyreward.model.user.UserReward;
import io.github.badnotice.dailyreward.registry.RewardRegistry;

import java.util.concurrent.TimeUnit;

public final class DailyRewardSQLAdapter implements SQLResultAdapter<UserReward> {

    @Override
    public UserReward adaptResult(SimpleResultSet resultSet) {
        RewardRegistry rewardRegistry = DailyRewardPlugin.getInstance().getRewardRegistry();
        return new UserReward(
                rewardRegistry.find(resultSet.get("reward_id"))
                        .orElseThrow(NullPointerException::new),
                TimeUnit.SECONDS.toMillis(resultSet.<Integer>get("expire_at"))
        );
    }

}
