package org.grant.zm.spring2.database.subtable;

/**
 * grant
 * 31/3/2020 3:52 下午
 * 描述：
 */
public interface IGSubTableSQL {
    String MYSQL_EXIST_SQL = "SELECT table_name as tb FROM information_schema.TABLES WHERE table_name = ?";
}
