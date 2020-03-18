package org.grant.zm.base;

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
}
