package io.github.badnotice.dailyreward.configuration;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
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
@ConfigSection("settings")
@ConfigFile("config.yml")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigValue implements ConfigurationInjectable {

    @Getter private static final ConfigValue instance = new ConfigValue();

    @ConfigField("database") private ConfigurationSection databaseSection;

    @ConfigField("inventory.title") private String titleInventory;
    @ConfigField("inventory.lines") private int linesInventory;

    @ConfigField("messages.reward-collected") private String rewardCollectedMessage;
    @ConfigField("messages.reward-expired") private String rewardExpiredMessage;
    @ConfigField("messages.already-collected") private String alreadyCollectedMessage;
    @ConfigField("messages.no-permission") private String noPermissionMessage;

    public static <T> T get(Function<ConfigValue, T> function) {
        return function.apply(instance);
    }

}