package org.grant.zm.spring2;

import lombok.extern.slf4j.Slf4j;
import org.grant.zm.spring2.async.GAsyncCall;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * ZoomGrant 2020/3/21 8:44
 */
@Slf4j
public class AsyncCallTest extends BaseSpringAppTest {

    @Autowired
    GAsyncCall asyncCall;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void async() throws InterruptedException {
        log.info("aaaaaaaaaaaaa");
        asyncCall.newCall(()->{
            log.info("111111111111111111111111");
        });
        log.info("--------------");
        asyncCall.newCall(()->{
           log.info("2222222222222222222");
        });

        Thread.sleep(5000L);
    }


    @Test
    public void callHttp(){
        String h = restTemplate.getForObject("/", String.class);
        System.out.println(h);
    }

    @Test
    public void callDbMaster(){
        String h = restTemplate.getForObject("/db", String.class);
        System.out.println(h);
    }

    @Test
    public void callDbSalve(){
        String h = restTemplate.getForObject("/db-yili", String.class);
        System.out.println(h);
    }
}
