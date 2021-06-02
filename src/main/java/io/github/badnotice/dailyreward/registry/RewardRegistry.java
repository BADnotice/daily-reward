package io.github.badnotice.dailyreward.registry;

import com.google.common.collect.ImmutableMap;
import io.github.badnotice.dailyreward.configuration.RewardsValue;
import io.github.badnotice.dailyreward.model.Reward;
import io.github.badnotice.dailyreward.parser.RewardParser;
import org.bukkit.configuration.ConfigurationSection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class RewardRegistry {

    private final Map<String, Reward> rewards = new LinkedHashMap<>();

    public void init() {
        ConfigurationSection rewardsSection = RewardsValue.get(RewardsValue::rewardsSection);
        for (String section : rewardsSection.getKeys(false)) {
            register(RewardParser.of().parserReward(rewardsSection.getConfigurationSection(section)));
        }
    }

    public void register(Reward reward) {
        this.rewards.put(reward.getIdentifier(), reward);
    }

    public Optional<Reward> find(String id) {
        return Optional.ofNullable(this.rewards.get(id));
    }

    public Map<String, Reward> getAll() {
        return ImmutableMap.copyOf(this.rewards);
    }

}
