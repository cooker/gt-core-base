package org.grant.zm.utils;

import org.grant.zm.config.GAbstractConfig;
import org.grant.zm.config.GIConfig;

import java.lang.reflect.InvocationTargetException;

/**
 * ZoomGrant 2020/3/1
 * 配置文件读取
 */
public class GConfigUtils {

    public static <T extends GAbstractConfig> GIConfig getConfig(String filePath, Class<T> cl){
        try {
            return GBeanUtils.getInstance(cl, filePath);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
