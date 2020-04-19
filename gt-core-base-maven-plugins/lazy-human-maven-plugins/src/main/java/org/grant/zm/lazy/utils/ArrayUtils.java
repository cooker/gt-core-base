package org.grant.zm.lazy.utils;

import org.apache.commons.lang3.CharUtils;

/**
 * grant
 * 18/4/2020 2:24 下午
 * 描述：
 */
public class ArrayUtils {

    public static String[] toArrays(String value){
        if (value.startsWith("{") && CharUtils.compare(value.charAt(value.length() - 1), '}') == 0){
            String temp = value.replace("{", "");
            temp = value.replace("}", "");
            temp.split(",");
        }

        return new String[]{value};
    }
}
