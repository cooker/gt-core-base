package org.grant.zm.spring2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.grant.zm.spring2.annotation.GDataSource;
import org.grant.zm.spring2.annotation.GLimit;
import org.grant.zm.spring2.annotation.GLog;
import org.grant.zm.spring2.base.BadException;
import org.grant.zm.spring2.base.BaseController;
import org.grant.zm.spring2.base.IGScheduleManager;
import org.grant.zm.spring2.database.MultiRoutingDataSource;
import org.grant.zm.spring2.extend.GSpringHelper;
import org.grant.zm.spring2.scheduling.QuartzJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @RequestMapping("/show-dbs")
    public String showDbs(){
        MultiRoutingDataSource multiRoutingDataSource = GSpringHelper.getBean(MultiRoutingDataSource.class);
//        return Stream.of(GSpringHelper.getBeanNamesForType(DataSource.class)).collect(Collectors.toList()).toString();
        return multiRoutingDataSource.getDataSource("master").toString();
    }

    @RequestMapping("/tasker")
    public String tasker(){
//        GSpringHelper.getBean("gSubTableTasker");
        return "aaaa";
    }


    @RequestMapping("/aa")
    public String error(@RequestParam("id") String id){
        if (StringUtils.isEmpty(id)) throw new BadException("aaa");
        return "sasa";
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
