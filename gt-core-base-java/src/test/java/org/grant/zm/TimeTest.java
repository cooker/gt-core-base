package org.grant.zm;

import org.grant.zm.utils.GDateUtils;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * ZoomGrant 2020/2/29
 */
public class TimeTest {
    @Test
    public void nextMonth(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Month month = localDateTime.withMonth(8).getMonth().firstMonthOfQuarter();

        System.out.println(month.getValue());
    }

    @Test
    public void nexyQuarter(){
        int firstMonth = LocalDateTime.now().getMonth().firstMonthOfQuarter().getValue();
        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(0);
        localDateTime = localDateTime.withMonth(firstMonth).plusMonths(3 * -1);
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyyMM")));
    }

    public static void main(String[] args) {
        System.out.println(GDateUtils.getAfterDay(1));
    }
}
