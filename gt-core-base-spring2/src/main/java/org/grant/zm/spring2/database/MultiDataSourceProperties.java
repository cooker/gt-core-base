package org.grant.zm.spring2.database;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * grant
 * 20/3/2020 9:58 上午
 * 描述：
 */
@Data
@ConfigurationProperties(
        prefix = "gt.datasource.multi"
)
public class MultiDataSourceProperties {
    boolean enabled = false;
    DataSourceProperties master;
    Map<String, DataSourceProperties> slave;
}
