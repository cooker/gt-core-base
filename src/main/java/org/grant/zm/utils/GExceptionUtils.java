package org.grant.zm.utils;

/**
 * ZoomGrant 2020/3/1
 */
public class GExceptionUtils {

    public static StackTraceElement getTopStack(Throwable e){
        return e.getStackTrace()[0];
    }
}
