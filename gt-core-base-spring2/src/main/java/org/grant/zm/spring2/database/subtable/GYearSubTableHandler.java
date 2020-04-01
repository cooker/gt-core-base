package org.grant.zm.spring2.database.subtable;

import lombok.extern.slf4j.Slf4j;
import org.grant.zm.spring2.annotation.GSubTableType;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * grant
 * 31/3/2020 4:05 下午
 * 描述：
 */
@Slf4j
public class GYearSubTableHandler extends GAbstractSubTableHandler{

    public GYearSubTableHandler() {
        super("yyyy", GSubTableType.YEAR);
    }

    @Override
    public boolean exists(String tableName, DataSource dataSource, String subName) {
        return false;
    }

    @Override
    protected String realSubName(int am) {
        LocalDateTime localDate = getDate().plusYears(am);
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    @Override
    public String nextSubName() {
        return realSubName(1);
    }

    @Override
    public String currSubName() {
        return realSubName(0);
    }

    @Override
    public String preSubName() {
        return realSubName(-1);
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
