package org.grant.zm.ssh2;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * ZoomGrant 2020/3/18 22:28
 * ssh 工具
 */
@Slf4j
public class GSshUtils {

    public static final String LOACL_CHARSET = "UTF-8";
    public static final int TIMEOUT = 6000;

    public static Connection login(String ip, String username, String password) {
        return login(ip, 22, username, password);
    }

    /**
     * 登录
     * @param ip
     * @param port
     * @param username
     * @param password
     * @return
     */
    public static Connection login(String ip, int port, String username, String password) {
        Connection con = null;
        try {
            con = new Connection(ip, port);
            con.connect(null, TIMEOUT, 0);
            if (con.authenticateWithPassword(username, password)) {
                return con;
            }
        }catch (IOException e) {
            log.warn("ssh2 登录异常 {} >> {}", ip, port);
            con.close();
        }
        return con;
    }

    /**
     * 登出
     * @param con
     */
    public static void logout(Connection con) {
        if (null != con){
            con.close();
        }
    }

    public static String exec(Connection con, String shell){
        String ret = "";
        try {
            if (null != con) {
                Session session = con.openSession();
                session.execCommand(shell, LOACL_CHARSET);
                ret = IOUtils.toString(session.getStdout(), LOACL_CHARSET);
                if (StringUtils.isBlank(ret)) {
                    ret = IOUtils.toString(session.getStderr(), LOACL_CHARSET);
                }
                session.close();
                //con.close(); 不直接关闭连接
            }
        }catch (IOException e) {
            log.warn("ssh2 执行shell异常 [[ {} ]]", shell);
        }
        return ret;
    }
}
