package org.grant.zm.base;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 */
public interface GConstants {
    String SUCCESS="success";
    String FAIL="fail";

    String CHARSET_UTF8 = "utf-8";

    String ENV_PROD = "prod";
    String ENV_DEV = "dev";
    String ENV_SIT = "sit";
    String ENV_UAT = "uat";

    long KB = 1024L;
    long MB = 1048576L;
    long GB = 1073741824L;
    long TB = 1099511627776L;
    /**
     * 格式化小数
     */
    DecimalFormat DF = new DecimalFormat("0.00");

    BigDecimal BIG_DECIMAL$0 = BigDecimal.ZERO;
    BigDecimal BIG_DECIMAL$1 = BigDecimal.ONE;
    BigDecimal BIG_DECIMAL$0_5 = new BigDecimal("0.5");
    BigDecimal BIG_DECIMAL$0_01 = new BigDecimal("0.01");
    BigDecimal BIG_DECIMAL$0_06 = new BigDecimal("0.06");

    String DATE_FORMAT_YYYY = "yyyy";
    String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    String DATE_FORMAT_10 = "yyyy-MM-dd";
    String DATE_FORMAT_16 = "yyyyMMddHHmmss";
    String DATE_FORMAT_19 = "yyyy-MM-dd HH:mm:ss";
    String DATE_FORMAT_21_CH = "yyyy年MM月dd日 HH时mm分ss秒";
    String DATE_FORMAT_23 = "yyyy-MM-dd HH:mm:ss.SSS";
}
