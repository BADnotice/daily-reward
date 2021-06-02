package io.github.badnotice.dailyreward.configuration;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bukkit.configuration.ConfigurationSection;

import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigFile("rewards.yml")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RewardsValue implements ConfigurationInjectable {

    @Getter private static final RewardsValue instance = new RewardsValue();

    @ConfigField("rewards") private ConfigurationSection rewardsSection;

    public static <T> T get(Function<RewardsValue, T> function) {
        return function.apply(instance);
    }

}

