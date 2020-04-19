package org.grant.zm.lazy.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * grant
 * 18/4/2020 11:24 上午
 * 描述：
 */
public class PropertiesUtils {

    public static Properties getProperties(String projectPath, String targetPath, String filePath){
        String file = projectPath + targetPath + "/" + filePath;
        Properties pro = new Properties();
        try(FileInputStream fin = new FileInputStream(file)) {
            pro.load(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pro;
    }


    public static boolean check(String propVal, String val, boolean matchIfMissing){
        if (StringUtils.isEmpty(propVal)) {
            return matchIfMissing;
        }

        return StringUtils.equals(propVal, val);
    }
}
