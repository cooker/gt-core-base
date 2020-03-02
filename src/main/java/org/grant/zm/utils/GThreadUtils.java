package org.grant.zm.utils;

import java.io.InputStream;
import java.net.URL;

/**
 * grant
 * 2/3/2020 9:41 AM
 * 描述：线程操作
 */
public class GThreadUtils {
    /**
     * 回去当前线程classloader
     * @return
     */
    public static ClassLoader getCurrClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static URL getCurrResource(String name){
        return getCurrClassLoader().getResource(name);
    }

    public static InputStream getCurrResourceInput(String name){
        return getCurrClassLoader().getResourceAsStream(name);
    }
}
