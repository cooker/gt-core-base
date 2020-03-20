package org.grant.zm.spring2.config;

import org.grant.zm.spring2.aop.GLogAspect;
import org.grant.zm.spring2.database.MultiDataSourceHandler;
import org.grant.zm.spring2.database.MultiDataSourceProperties;
import org.grant.zm.spring2.database.MultiRoutingDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * grant
 * 20/3/2020 10:04 上午
 * 描述：
 */
@Configuration
@ConditionalOnClass(AbstractRoutingDataSource.class)
@ConditionalOnMissingBean({DataSource.class})
@EnableConfigurationProperties({MultiDataSourceProperties.class})
public class MultiAutoDataSourceConfigure {

    @Bean
    @ConditionalOnProperty(value = "gt.datasource.multi.enabled", havingValue = "true", matchIfMissing = false)
    MultiDataSourceHandler dataSourceHandler(){
        return new MultiDataSourceHandler();
    }

    @Bean
    public GLogAspect gLogAspect(){
        return new GLogAspect();
    }

    @Bean
    public DataSource maserDataSource(MultiDataSourceProperties properties){
        return properties.getMaster().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConditionalOnBean({DataSource.class, MultiDataSourceHandler.class})
    public MultiRoutingDataSource salveDataSource(MultiDataSourceHandler handler,
                                                  DataSource dataSource,
                                                  MultiDataSourceProperties properties){
        Map<String, DataSourceProperties> salve = properties.getSlave();
        Map<Object, Object> dSalve = getSalveDataSources(salve);
        return new MultiRoutingDataSource(handler, dataSource, dSalve);
    }

    private Map<Object, Object> getSalveDataSources(Map<String, DataSourceProperties> salve){
        Map<Object, Object> mDataSource = new HashMap<>();
        for (String key : salve.keySet()){
            DataSourceProperties sourceProperties = salve.get(key);
            mDataSource.put(key, sourceProperties.initializeDataSourceBuilder().build());
        }
        return mDataSource;
    }

}