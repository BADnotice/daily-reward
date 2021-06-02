package io.github.badnotice.dailyreward.registry;

import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import io.github.badnotice.dailyreward.configuration.ConfigValue;
import io.github.badnotice.dailyreward.configuration.RewardsValue;
import lombok.Data;
import org.bukkit.plugin.java.JavaPlugin;

@Data(staticConstructor = "of")
public final class InjectConfigurationRegistry {

    private final JavaPlugin plugin;

    public void init() {
        BukkitConfigurationInjector configurationInjector = new BukkitConfigurationInjector(plugin);

        configurationInjector.saveDefaultConfiguration(
                plugin,
                "config.yml",
                "rewards.yml"
        );

        configurationInjector.injectConfiguration(
                ConfigValue.instance(),
                RewardsValue.instance()
        );

    }

}