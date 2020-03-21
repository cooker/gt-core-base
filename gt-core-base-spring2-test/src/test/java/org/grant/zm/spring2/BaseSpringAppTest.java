package org.grant.zm.spring2;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ZoomGrant 2020/3/21 8:14
 */
@EnableAsync
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class BaseSpringAppTest {

}
