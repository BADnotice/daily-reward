package io.github.badnotice.dailyreward.parser;

import com.google.common.collect.Sets;
import io.github.badnotice.dailyreward.enums.RewardType;
import io.github.badnotice.dailyreward.model.Reward;
import io.github.badnotice.dailyreward.model.builder.ItemBuilder;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.List;
import java.util.Set;

@Data(staticConstructor = "of")
public final class RewardParser {

    public Reward parserReward(ConfigurationSection section) {
        return new Reward(
                section.getName(),
                section.getString("permission"),
                this.loadCommandsFromReward(section),
                this.loadItemsFromReward(section),
                this.toItemStack(section.getConfigurationSection("display")),
                section.getInt("inventory-slot"),
                RewardType.find( section.getString("type"))
        );
    }

    private Set<ItemStack> loadItemsFromReward(ConfigurationSection section) {
        Set<ItemStack> result = Sets.newLinkedHashSet();

        ConfigurationSection itemsSection = section.getConfigurationSection("items");
        itemsSection.getKeys(false).forEach(s -> result.add(toItemStack(itemsSection.getConfigurationSection(s))));

        return result;
    }

    private Set<String> loadCommandsFromReward(ConfigurationSection section) {
        Set<String> result = Sets.newLinkedHashSet();

        result.addAll(section.getStringList("commands"));
        return result;
    }

    private MaterialData toMaterialData(ConfigurationSection section) {
        return new MaterialData(
                Material.getMaterial(section.getString("material")),
                (byte) section.getInt("data"));
    }

    private ItemStack toItemStack(ConfigurationSection section) {
        ItemBuilder itemBuilder = ItemBuilder.of(this.toMaterialData(section));

        itemBuilder.displayName(section.getString("display-name"));
        itemBuilder.lore(section.getStringList("description"));

        List<String> enchants = section.getStringList("enchants");
        if (enchants != null) {
            enchants.forEach(enchant -> {
                String[] split = enchant.split(":");
                itemBuilder.enchantment(
                        Enchantment.getByName(split[0]),
                        Integer.parseInt(split[1])
                );
            });
        }

        return itemBuilder.getItemStack();
    }

}
