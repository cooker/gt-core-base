package org.grant.zm;

import org.grant.zm.utils.GStringUtils;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

/**
 * ZoomGrant 2020/2/28
 */
public class GStringUtilsTest {

    @Test
    public void upper(){
        String str = GStringUtils.toUpperOrLower("我相信aAbss", true, 4);
        Assert.assertEquals("不一致", str, "我相信aAbss");
    }

    @Test
    public void camel(){
        String str = GStringUtils.toCamel("t_h");
        Assert.assertEquals("不一致", str, "tH");
    }

    @Test
    public void tableName(){
        String name = GStringUtils.tableName("tHistory");
        Assert.assertEquals("不一致", name, "t_history");
    }

    @Test
    public void money(){

    }

    @Test
    public void mark(){
        Assert.assertEquals(GStringUtils.mask("1234567890", 2, "*"), "**34567890");
        Assert.assertEquals(GStringUtils.mask("1234567890", -2, "*"), "12345678**");
        Assert.assertEquals(GStringUtils.mask("1234567890", 3, -2, "*"), "123*****90");
    }
}
