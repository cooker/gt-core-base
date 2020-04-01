package org.grant.zm.utils;

import java.math.BigDecimal;

/**
 * grant
 * 9/3/2020 6:52 下午
 * 描述：数学工具
 */
public class GMathUtils {

    /**
     * @param source 金额1
     * @param target 金额2
     * @param allowValue 金额误差范围
     * @return
     */
    public static boolean isEqualsAbs(BigDecimal source, BigDecimal target, BigDecimal allowValue){
        return source.abs().subtract(target.abs()).abs().compareTo(allowValue) <= 0;
    }

}
