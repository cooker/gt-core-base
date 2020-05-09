package org.grant.zm.spring2.config;

import org.grant.zm.spring2.aop.GDataSourceAspect;
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
import org.springframework.context.annotation.Primary;
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
    @Primary
//    @ConditionalOnMissingBean(MultiDataSourceHandler.class)
    public DataSource masterDataSource(MultiDataSourceProperties properties){
        return properties.getMaster().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    @ConditionalOnBean({MultiDataSourceHandler.class})
    public MultiRoutingDataSource multiRoutingDataSource(MultiDataSourceHandler handler,
                                                  MultiDataSourceProperties properties){
        Map<Object, Object> dSalve = getSalveDataSources(properties);
        DataSource dataSource = properties.getMaster().initializeDataSourceBuilder().build();
        dSalve.put("master", dataSource);
        return new MultiRoutingDataSource(handler, dataSource, dSalve);
    }

    @Bean
    @ConditionalOnBean({MultiDataSourceHandler.class})
    public GDataSourceAspect gDataSourceAspect(MultiDataSourceHandler multiDataSourceHandler){
        return new GDataSourceAspect(multiDataSourceHandler);
    }

    private Map<Object, Object> getSalveDataSources(MultiDataSourceProperties properties){
        Map<Object, Object> mDataSource = new HashMap<>();
        if (null != properties.getSlave()){
            for (String key : properties.getSlave().keySet()){
                DataSourceProperties sourceProperties = properties.getSlave().get(key);
                if (sourceProperties.getType() == null){
                    try {
                        sourceProperties.setType((Class<? extends DataSource>) Class.forName("com.zaxxer.hikari.HikariDataSource"));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                mDataSource.put(key, sourceProperties.initializeDataSourceBuilder().build());
            }
        }
        return mDataSource;
    }

}