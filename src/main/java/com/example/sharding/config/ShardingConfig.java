package com.example.sharding.config;

import com.google.common.collect.Lists;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wzl on 2019/8/6 17:26.
 */
@Configuration
public class ShardingConfig {

    @Value("${spring.datasource.master.maxPoolSize:300}")
    private String maxPoolSize;

    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean(name = "dsMaster")
    public DataSource dataSource1() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setMaximumPoolSize(Integer.valueOf(maxPoolSize));
        hikariDataSource.setMinimumIdle(Integer.valueOf(maxPoolSize));
        return hikariDataSource;
    }



    @Primary
    @DependsOn("dsMaster")
    @Bean(name = "shardingDataSource")
    DataSource getShardingDataSource(DataSource dsMaster) throws SQLException {
        Map<String, DataSource> result = new HashMap<>();
        result.put("ds0", dsMaster);

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        //把需要分表的配置注入
        List<TableRuleConfiguration> objects = Lists.newArrayList(
                getLogUserWalletChangeConfiguration()
                ,getLogDividendChangeConfiguration()
        );
        shardingRuleConfig.setTableRuleConfigs(objects);

        shardingRuleConfig.getBindingTableGroups().add("log_user_wallet_change,log_dividend_change");
        //分表的字段以及分表算法
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_info_id", new TablePreciseShardingAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(result, shardingRuleConfig, getProperties());
    }

    TableRuleConfiguration getLogUserWalletChangeConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("log_user_wallet_change", "ds0.log_user_wallet_change_${0..19}");
        return result;
    }


    TableRuleConfiguration getLogDividendChangeConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("log_dividend_change", "ds0.log_dividend_change_${0..127}");
        return result;
    }




    /**
     * 获取其余配置信息
     */
    private Properties getProperties() {
        Properties properties = new Properties();
        // 打印出分库路由后的 sql
        properties.setProperty("sql.show", "true");
        return properties;
    }
}