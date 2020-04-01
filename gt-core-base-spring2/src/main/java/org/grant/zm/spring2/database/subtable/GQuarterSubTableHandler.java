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
public class GQuarterSubTableHandler extends GAbstractSubTableHandler {

    public GQuarterSubTableHandler() {
        super("yyyyMM", GSubTableType.QUARTER);
    }

    @Override
    public boolean exists(String tableName, DataSource dataSource, String subName) {
        return false;
    }

    @Override
    protected String realSubName(int am) {
        int firstMonth = getDate().getMonth().firstMonthOfQuarter().getValue();
        LocalDateTime localDateTime = getDate().plusSeconds(0);
        localDateTime = localDateTime.withMonth(firstMonth).plusMonths(3 * am);
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
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
