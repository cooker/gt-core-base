package org.grant.zm.spring2.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * grant
 * 20/3/2020 10:14 上午
 * 描述：
 */
public class MultiRoutingDataSource extends AbstractRoutingDataSource {
    MultiDataSourceHandler multiDataSourceHandler;

    public MultiRoutingDataSource(MultiDataSourceHandler multiDataSourceHandler, DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources)
    {
        this.multiDataSourceHandler = multiDataSourceHandler;
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return multiDataSourceHandler.getDataSourceName();
    }
}
