package org.grant.zm.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.*;

/**
 * ZoomGrant 2020/2/28
 */
public class GDateUtils {
    /**
     * 获取当前时间
     * @return
     */
    public static Date getNow(){
        return new Date();
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getNow2Str(String format){
        return DateFormatUtils.format(getNow(), format);
    }

    /**
     * @param val
     * @return
     */
    public static Date getAfterDay(int val){
        return getAfter(val, TimeUnit.DAYS);
    }

    /**
     * @param val
     * @return
     */
    public static Date getAfterHour(int val){
        return getAfter(val, TimeUnit.HOURS);
    }

    /**
     * @param val
     * @return
     */
    public static Date getAfterSecond(int val){
        return getAfter(val, TimeUnit.SECONDS);
    }

    /**
     * @param val
     * @return
     */
    public static Date getAfterMinute(int val){
        return getAfter(val, TimeUnit.MINUTES);
    }

    public static Date getAfter(int val, TimeUnit unit) {
        LocalDate localDate = LocalDate.now();
        switch (unit){
            case DAYS:
                localDate.plusDays(val);
                break;
            case HOURS:
                localDate.plus(val, HOURS);
                break;
            case MINUTES:
                localDate.plus(val, MINUTES);
                break;
            case SECONDS:
                localDate.plus(val, SECONDS);
                break;
            default:
                break;
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

}
