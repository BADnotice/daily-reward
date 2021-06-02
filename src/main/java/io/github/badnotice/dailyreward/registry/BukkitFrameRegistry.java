package io.github.badnotice.dailyreward.registry;

import io.github.badnotice.dailyreward.DailyRewardPlugin;
import io.github.badnotice.dailyreward.commands.RewardCommand;
import lombok.Data;
import me.saiintbrisson.bukkit.command.BukkitFrame;

@Data(staticConstructor = "of")
public final class BukkitFrameRegistry {

    private final DailyRewardPlugin plugin;

    public void init() {
        BukkitFrame bukkitFrame = new BukkitFrame(this.plugin);
        bukkitFrame.registerCommands(
                new RewardCommand()
        );
    }

}
