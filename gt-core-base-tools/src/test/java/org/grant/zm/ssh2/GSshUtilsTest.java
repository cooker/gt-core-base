package org.grant.zm.ssh2;

import ch.ethz.ssh2.Connection;
import org.junit.Test;


/**
 * ZoomGrant 2020/3/18 23:07
 */
public class GSshUtilsTest {
    @Test
    public void login(){
        Connection con = GSshUtils.login("127.0.0.1", "grant", "grant9111");

        System.out.println(con);
    }
}
