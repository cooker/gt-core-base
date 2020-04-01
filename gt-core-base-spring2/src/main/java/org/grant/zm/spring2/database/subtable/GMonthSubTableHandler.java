package org.grant.zm.spring2.database.subtable;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.grant.zm.spring2.annotation.GSubTableType;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * grant
 * 31/3/2020 4:05 下午
 * 描述：
 */
@Slf4j
public class GMonthSubTableHandler extends GAbstractSubTableHandler{

    public GMonthSubTableHandler() {
        super("yyyyMM", GSubTableType.MONTH);
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

    protected String realSubName(int am){
        LocalDate localDate = getDate().toLocalDate().plusMonths(am);
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
