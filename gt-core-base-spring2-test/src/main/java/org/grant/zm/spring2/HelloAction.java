package org.grant.zm.spring2;

import lombok.extern.slf4j.Slf4j;
import org.grant.zm.spring2.annotation.GDataSource;
import org.grant.zm.spring2.annotation.GLog;
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
public class HelloAction {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    @GLog("test 接口")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/db")
    public String db(){
        Map map = jdbcTemplate.queryForMap("select * from dict limit 1");
        return map.toString();
    }

    @GDataSource("yili")
    @RequestMapping("/db-yili")
    public String dbYili(){
        Map map = jdbcTemplate.queryForMap("select * from dict limit 1");
        return map.toString();
    }
}
