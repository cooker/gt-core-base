package org.grant.zm.spring2;

import lombok.extern.slf4j.Slf4j;
import org.grant.zm.spring2.annotation.GDataSource;
import org.grant.zm.spring2.annotation.GLimit;
import org.grant.zm.spring2.annotation.GLog;
import org.grant.zm.spring2.base.BaseController;
import org.grant.zm.spring2.base.IGScheduleManager;
import org.grant.zm.spring2.scheduling.QuartzJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ZoomGrant 2020/3/21 8:57
 */
@Slf4j
@RestController
@RequestMapping("/")
public class HelloAction extends BaseController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    @GLog("test 接口")
    @GLimit(value = "111", req = 2)
    public String hello(){
        return "hello";
    }

    @RequestMapping("/db")
    public String db(){
        Map map = jdbcTemplate.queryForMap("select * from dict limit 1");
        return map.toString();
    }

    @GDataSource("yili")
    @GLimit(value = "111", req = 2)
    @RequestMapping("/db-yili")
    public String dbYili(){
        Map map = jdbcTemplate.queryForMap("select * from filelogging limit 1");
        return map.toString();
    }


    @Autowired
    IGScheduleManager scheduleManager;

    @RequestMapping("/aaa")
    public String task(){
        QuartzJob job = new QuartzJob();
        job.setBeanName("helloService");
        job.setJobName("aaa");
        job.setMethodName("say");
        job.setCronExpression("/3 * * * * ?");
        scheduleManager.addJob(job);
        return "task";
    }
}
