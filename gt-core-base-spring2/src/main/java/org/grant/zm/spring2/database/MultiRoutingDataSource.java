package org.grant.zm.spring2.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Field;
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

    public DataSource getDataSource(String key){
        Map<Object, DataSource> resolvedDataSources = getResolvedDataSources();
        return resolvedDataSources.get(key);
    }

    public boolean hasDataSource(String key){
        Map<Object, DataSource> resolvedDataSources = getResolvedDataSources();
        return resolvedDataSources.containsKey(key);
    }

    protected Map<Object, DataSource> getResolvedDataSources(){
        Field field = ReflectionUtils.findField(AbstractRoutingDataSource.class, "resolvedDataSources");
        field.setAccessible(true);
        Map<Object, DataSource> resolvedDataSources = (Map<Object, DataSource>) ReflectionUtils.getField(field, this);
        return resolvedDataSources;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return multiDataSourceHandler.getDataSourceName();
    }
}
