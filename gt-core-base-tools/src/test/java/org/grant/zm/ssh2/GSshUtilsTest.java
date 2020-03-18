package org.grant.zm.ssh2;

import org.junit.Test;

/**
 * ZoomGrant 2020/3/18 23:07
 */
public class GSshUtilsTest {
    @Test
    public void login(){
        GSshUtils.login("192.168.0.1", "root", "root");
    }
}
