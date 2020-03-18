package org.grant.zm;

import org.apache.commons.lang3.SystemUtils;
import org.grant.zm.config.GIConfig;
import org.grant.zm.config.impl.GClassConfig;
import org.grant.zm.config.impl.GFileConfig;
import org.grant.zm.utils.GConfigUtils;
import org.junit.Test;

/**
 * grant
 * 2/3/2020 10:53 AM
 * 描述：
 */
public class GConfigUtilsTest {


    @Test
    public void fileTest(){
        String filePath = SystemUtils.USER_DIR + "/target/test-classes/" + "props-test.properties";
        GIConfig giConfig = GConfigUtils.getConfig(filePath, GFileConfig.class);
        System.out.println(giConfig.getMap());
    }

    @Test
    public void classTest(){
        GIConfig giConfig = GConfigUtils.getConfig("props-test.properties", GClassConfig.class);
        System.out.println(giConfig.getMap());
    }
}
