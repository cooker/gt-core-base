package org.grant.zm.spring2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.task.TaskExecutor;

/**
 * ZoomGrant 2020/3/21 8:14
 */
@SpringBootApplication
public class SpringApp {

    @Autowired
    TaskExecutor scheduledTask;

    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class);
    }

}
