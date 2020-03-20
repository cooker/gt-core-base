package org.grant.zm.spring2.database;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * grant
 * 20/3/2020 9:56 上午
 * 描述：
 */
public class MultiDataSourceHandler {
    ThreadLocal<String> context = new ThreadLocal<>();

    public void setDataSourceName(String name){
        context.set(name);
    }

    public String getDataSourceName(){
        return context.get();
    }

    public void clearDataSourceName(){
        context.remove();
    }
}
