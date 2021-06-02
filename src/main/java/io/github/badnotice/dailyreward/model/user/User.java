package io.github.badnotice.dailyreward.model.user;

import lombok.Data;

@Data
public final class User {

    private final String name;
    private final UserReward userReward;
}
