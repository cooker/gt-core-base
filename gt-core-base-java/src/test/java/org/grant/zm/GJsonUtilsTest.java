package org.grant.zm;


import org.grant.zm.utils.GJsonUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * ZoomGrant 2020/2/29
 */
public class GJsonUtilsTest {
    @Test
    public void testJson() throws IOException {
        String json = "{\n" +
                "  \"aa\" : 1.001999999999999999\n" +
                "}";
        Map<String, Object> map = GJsonUtils.toBean(json, Map.class);
        Assert.assertEquals("1.001999999999999999", map.get("aa").toString());
    }
}
