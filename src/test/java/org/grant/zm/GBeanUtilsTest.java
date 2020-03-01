package org.grant.zm;

import lombok.Data;
import org.grant.zm.utils.GBeanUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * ZoomGrant 2020/2/29
 */
public class GBeanUtilsTest {
    CopyBean a = null;
    @Before
    public void init(){
        a = new CopyBean();
        a.setStr("11");
        a.setB(false);
        a.setBt((byte) 1);
        a.setL(12);
        a.setBig(new BigDecimal("111"));
    }

    @Test
    public void copy() {
        CopyBean aa = new CopyBean();
        GBeanUtils.copyProperties(aa, a);
        Assert.assertThat(a.toString(), CoreMatchers.equalTo(aa.toString()));
    }

    @Test
    public void copy1() {
        CopyBean1 copyBean1 = new CopyBean1();
        GBeanUtils.copyProperties(copyBean1, a);
        Assert.assertThat(a.toString().replace("CopyBean", "CopyBean1"), CoreMatchers.equalTo(copyBean1.toString()));
    }

    @Data
    public static class CopyBean1 {
        private String str;
        private String b;
        private String s;
        private String l;
        private String bt;
        private String d;
        private String f;
        private String i;
        private String big;
    }


    @Data
    public static class CopyBean {
        private String str;
        private boolean b;
        private Short s;
        private long l;
        private byte bt;
        private double d;
        private float f;
        private int i;
        private BigDecimal big;
    }
}
