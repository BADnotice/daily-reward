package io.github.badnotice.dailyreward.sql.factory;

import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.connector.type.impl.MySQLDatabaseType;
import com.henryfabio.sqlprovider.connector.type.impl.SQLiteDatabaseType;
import io.github.badnotice.dailyreward.DailyRewardPlugin;
import lombok.Data;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;

@Data(staticConstructor = "of")
public final class SQLDatabaseFactory {

    private final DailyRewardPlugin plugin;

    public SQLConnector createSqlConnector(ConfigurationSection section) {
        String databaseType = section.getString("type");

        ConfigurationSection typeSection = section.getConfigurationSection(databaseType);
        switch (databaseType) {
            case "sqlite":
                return buildSqliteDatabaseType(typeSection).connect();
            case "mysql":
                return buildMysqlDatabaseType(typeSection).connect();
            default:
                throw new UnsupportedOperationException("database type unsupported!");
        }
    }

    private SQLiteDatabaseType buildSqliteDatabaseType(ConfigurationSection typeSection) {
        return SQLiteDatabaseType.builder()
                .file(new File(plugin.getDataFolder(), typeSection.getString("fileName")))
                .build();
    }

    private MySQLDatabaseType buildMysqlDatabaseType(ConfigurationSection typeSection) {
        return MySQLDatabaseType.builder()
                .address(typeSection.getString("address"))
                .username(typeSection.getString("username"))
                .password(typeSection.getString("password"))
                .database(typeSection.getString("database"))
                .build();
    }

}
