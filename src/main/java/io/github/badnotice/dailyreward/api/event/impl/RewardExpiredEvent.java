package io.github.badnotice.dailyreward.api.event.impl;

import io.github.badnotice.dailyreward.api.event.EventWrapper;
import io.github.badnotice.dailyreward.model.Reward;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Called when the player's collected reward is expired.
 */

@Getter
@Setter
public final class RewardExpiredEvent extends EventWrapper implements Cancellable {

    private final Player player;
    private final String expiredMessage;

    private boolean cancelled;

    public RewardExpiredEvent(Reward reward, Player player, String expiredMessage) {
        super(reward);
        this.player = player;
        this.expiredMessage = expiredMessage;
    }

}