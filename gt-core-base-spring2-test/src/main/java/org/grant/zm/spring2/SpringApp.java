package org.grant.zm.spring2;

import org.grant.zm.spring2.config.GAutoLimitConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * ZoomGrant 2020/3/21 8:14
 */
//@EnableWebSocket
@EnableScheduling
@SpringBootApplication(exclude = GAutoLimitConfigure.class)
public class SpringApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class);
    }

}
