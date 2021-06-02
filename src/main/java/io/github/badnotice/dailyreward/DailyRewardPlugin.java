package io.github.badnotice.dailyreward;

import com.henryfabio.minecraft.inventoryapi.manager.InventoryManager;
import com.henryfabio.sqlprovider.connector.SQLConnector;
import io.github.badnotice.dailyreward.configuration.ConfigValue;
import io.github.badnotice.dailyreward.registry.BukkitFrameRegistry;
import io.github.badnotice.dailyreward.registry.InjectConfigurationRegistry;
import io.github.badnotice.dailyreward.registry.ListenerRegistry;
import io.github.badnotice.dailyreward.registry.RewardRegistry;
import io.github.badnotice.dailyreward.sql.DailyRewardTable;
import io.github.badnotice.dailyreward.sql.factory.SQLDatabaseFactory;
import io.github.badnotice.dailyreward.storage.UserStorage;
import lombok.Getter;
import me.bristermitten.pdm.PluginDependencyManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class DailyRewardPlugin extends JavaPlugin {

    private UserStorage userStorage;
    private DailyRewardTable dailyRewardTable;

    private RewardRegistry rewardRegistry;
    private SQLConnector sqlConnector;

    public static DailyRewardPlugin getInstance() {
        return getPlugin(DailyRewardPlugin.class);
    }

    @Override
    public void onEnable() {
        PluginDependencyManager.of(this)
                .loadAllDependencies()
                .exceptionally(throwable -> {
                    throwable.printStackTrace();

                    getLogger().severe("Ocorreu um erro durante a inicialização do plugin!");
                    Bukkit.getPluginManager().disablePlugin(this);

                    return null;
                })
                .join();

        InventoryManager.enable(this);

        InjectConfigurationRegistry.of(this).init();
        BukkitFrameRegistry.of(this).init();
        ListenerRegistry.of(this).init();

        this.userStorage = new UserStorage(this);
        this.sqlConnector = SQLDatabaseFactory.of(this).createSqlConnector(ConfigValue.get(ConfigValue::databaseSection));

        this.dailyRewardTable = new DailyRewardTable(this);
        this.dailyRewardTable.createTable();

        this.rewardRegistry = new RewardRegistry();
        this.rewardRegistry.init();

        int pluginId = 11554;
        Metrics metrics = new Metrics(this, pluginId);

        metrics.addCustomChart(new Metrics.SimplePie("rewards_registed", () -> String.valueOf(this.rewardRegistry.getAll().size()) ));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
