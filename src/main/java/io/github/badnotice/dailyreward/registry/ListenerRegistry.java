package io.github.badnotice.dailyreward.registry;

import io.github.badnotice.dailyreward.DailyRewardPlugin;
import io.github.badnotice.dailyreward.listener.RewardListener;
import io.github.badnotice.dailyreward.listener.UserListener;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

@Data(staticConstructor = "of")
public final class ListenerRegistry {

    private final DailyRewardPlugin plugin;

    public void init() {
        registerListeners(
                new RewardListener(this.plugin),
                new UserListener(this.plugin)
        );
    }

    private void registerListeners(Listener... listeners) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, plugin);
        }
    }

}
