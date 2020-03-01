package org.grant.zm;

import org.grant.zm.utils.GDateUtils;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * ZoomGrant 2020/2/29
 */
public class TimeTest {
    public static void main(String[] args) {
        System.out.println(GDateUtils.getAfterDay(1));
    }
}
