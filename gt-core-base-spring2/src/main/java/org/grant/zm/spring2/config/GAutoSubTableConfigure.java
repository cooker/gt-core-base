package org.grant.zm.spring2.config;

import lombok.extern.slf4j.Slf4j;
import org.grant.zm.spring2.base.IGScheduleManager;
import org.grant.zm.spring2.database.subtable.GSubTableConf;
import org.grant.zm.spring2.database.subtable.GSubTableTasker;
import org.grant.zm.spring2.extend.GSpringHelper;
import org.grant.zm.spring2.scheduling.QuartzJob;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.grant.zm.spring2.database.subtable.IGSubTableHandler.*;
import static org.grant.zm.spring2.database.subtable.IGSubTableHandler.confName;

/**
 * grant
 * 31/3/2020 4:07 下午
 * 描述：分表定时任务
 */
@Slf4j
@Configuration
public class GAutoSubTableConfigure implements ApplicationListener<ApplicationStartedEvent>, BeanDefinitionRegistryPostProcessor, Ordered {
    static BeanDefinitionRegistry registry = null;
    static ConfigurableListableBeanFactory factory = null;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("*********自动建表配置扫描*********");
        try {
            Resource[] resources = event.getApplicationContext().getResources(path + confName);
            Properties props = new Properties();
            for (Resource res : resources){
                log.info("自动建表配置：{}", res.getFilename());
                props.load(res.getInputStream());
            }
            if (props.isEmpty()){
                log.info("自动建表配置为空，不创建任务");
            }else {
                log.info("自动建表，任务开启");
                this.newTasker(props);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException io){
            io.printStackTrace();
        }
    }


    private void newTasker(Properties props){
        Enumeration<Object> keys = props.keys();
        String key = null;
        String val = null;
        GSubTableConf conf = null;
        Map<String, GSubTableConf> mapConf = new HashMap<>(props.keySet().size());
        while (keys.hasMoreElements()){
            key = keys.nextElement().toString();
            val = props.getProperty(key);
            conf = new GSubTableConf();
            conf.load(val);
            mapConf.put(key, conf);
        }
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(GSubTableTasker.class)
                .addPropertyValue("gSubTableConfs", mapConf);
        registry.registerBeanDefinition("gSubTableTasker", builder.getBeanDefinition());
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(-1L);
        quartzJob.setJobName("自动建表任务");
        quartzJob.setBeanName("gSubTableTasker");
//        quartzJob.setCronExpression("0 5 0 * * ?");
        quartzJob.setCronExpression("0/15 * * * * ?");
        quartzJob.setIsPause(false);
        quartzJob.setMethodName("runSubTableHanlder");
        quartzJob.setRemark("自动建表");
        GSpringHelper.getBean(IGScheduleManager.class).addJob(quartzJob);
    }

    /**
     * 注册定时任务bean
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.info("*********自动建表 Bean Registry 加载*********");
        this.registry = registry;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        log.info("*********自动建表 Bean Factory 加载*********");
        this.factory = factory;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}