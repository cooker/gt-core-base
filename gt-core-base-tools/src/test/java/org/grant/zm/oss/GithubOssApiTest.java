package org.grant.zm.oss;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * grant
 * 19/3/2020 3:48 下午
 * 描述：
 */
public class GithubOssApiTest {
    @Test
    public void put() throws IOException {
       String resp = new GithubOssApi().uploadByGithub(Paths.get("/Users/grant/Documents/desktop.ini").toFile(),"cooker", "oss", "8bff3fe30553dc43c21c00438e4b800076e09d05");
       System.out.println(resp);
    }
}
