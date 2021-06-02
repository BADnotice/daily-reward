package io.github.badnotice.dailyreward.api.event;

import io.github.badnotice.dailyreward.model.Reward;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public abstract class EventWrapper extends Event {

    @Getter
    private static final HandlerList handlerList = new HandlerList();

    private final Reward reward;

    public EventWrapper(Reward reward) {
        this.reward = reward;
    }

    public boolean call() {
        Bukkit.getPluginManager().callEvent(this);

        if (this instanceof Cancellable) {
            return !((Cancellable) this).isCancelled();
        }

        return true;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

}
