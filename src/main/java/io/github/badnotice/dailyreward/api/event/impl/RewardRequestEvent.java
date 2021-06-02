package io.github.badnotice.dailyreward.api.event.impl;

import io.github.badnotice.dailyreward.api.event.EventWrapper;
import io.github.badnotice.dailyreward.model.Reward;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Called when the player requests a reward.
 */

@Getter
@Setter
public final class RewardRequestEvent extends EventWrapper implements Cancellable {

    private final Player player;
    private final String collectedMessage;

    private boolean cancelled;

    public RewardRequestEvent(Reward reward, Player player, String collectedMessage) {
        super(reward);
        this.player = player;
        this.collectedMessage = collectedMessage;
    }

}
