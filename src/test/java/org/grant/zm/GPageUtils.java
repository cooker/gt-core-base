package org.grant.zm;

import lombok.Data;
import org.grant.zm.base.GPage;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * ZoomGrant 2020/3/1
 */
public class GPageUtils {

    @Test
    public void page(){
        Assert.assertTrue(CoreMatchers.equalTo(4).matches(GPage.getPage(10, 3)));
        Assert.assertTrue(CoreMatchers.equalTo(5).matches(GPage.getPage(10, 2)));
    }

    @Data
    class A{
        String sa;
    }
}
