package org.grant.zm.spring2.database.subtable;

import org.apache.commons.io.IOUtils;
import org.grant.zm.spring2.extend.GSpringHelper;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;

/**
 * grant
 * 31/3/2020 3:42 下午
 * 描述：分表处理器
 */
public interface IGSubTableHandler {
    String path = "classpath:cron/sub-table/";
    String confName = "subtable-*.properties";
    String defaultName = "subtable-default.properties";

    /**
     * 表是否存在
     * @param tableName
     * @param dataSource
     * @param subName
     * @return
     */
    boolean exists(String tableName, DataSource dataSource, String subName);

    /**
     * @param tableName
     * @param dataSource
     */
    default void createTable(String tableName, DataSource dataSource, String subName) throws IOException {
        Resource url = GSpringHelper.getResource(path + tableName + ".sql");
        String createSQL = IOUtils.toString(url.getInputStream(), "utf-8");
        String sql = createSQL.replaceAll("#tableName#", tableName)
                              .replaceAll("#subName#", subName);
        getLogger().debug("分表sql执行：{}", sql);
        try(Connection con = dataSource.getConnection()) {
            con.setAutoCommit(true);
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        }catch (Exception io) {
            io.printStackTrace();
            getLogger().error("分表sql执行失败 >> {}_{}", tableName, subName, io);
        }
    }

    default void createNextTable(String tableName, DataSource dataSource) throws IOException {
        createTable(tableName, dataSource, nextSubName());
    }

    default void createCurrTable(String tableName, DataSource dataSource) throws IOException {
        createTable(tableName, dataSource, currSubName());
    }

    default void createPreTable(String tableName, DataSource dataSource) throws IOException {
        createTable(tableName, dataSource, preSubName());
    }

    String nextSubName();

    String currSubName();

    String preSubName();

    Logger getLogger();

    void loadDate(LocalDateTime date);
}
