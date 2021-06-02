package io.github.badnotice.dailyreward.model.user;

import io.github.badnotice.dailyreward.model.Reward;
import lombok.Data;

@Data
public final class UserReward {

    private final Reward reward;
    private final long expireAt;

    public boolean isExpired() {
        return expireAt <= System.currentTimeMillis();
    }

}