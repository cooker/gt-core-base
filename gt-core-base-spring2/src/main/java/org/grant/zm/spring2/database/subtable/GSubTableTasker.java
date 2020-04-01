package org.grant.zm.spring2.database.subtable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grant.zm.spring2.database.MultiRoutingDataSource;
import org.grant.zm.spring2.extend.GSpringHelper;

import javax.sql.DataSource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * grant
 * 1/4/2020 9:23 上午
 * 描述：自动建表定时任务
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class GSubTableTasker {

    Map<String, GSubTableConf> gSubTableConfs;

    /**
     * 每天晚上00:00:05 执行
     */
//    @Scheduled(cron = "0/15 * * * * ?")
    public void runSubTableHanlder(){
        log.debug("自动建表 任务开始 ...");
        IGSubTableHandler handler = null;
        GSubTableConf conf = null;
        for (String key : gSubTableConfs.keySet()) {
            conf = gSubTableConfs.get(key);
            switch (conf.getSubTableType()){
                case YEAR:
                    handler = new GYearSubTableHandler();
                    break;
                case MONTH:
                    handler = new GMonthSubTableHandler();
                    break;
                case QUARTER:
                    handler = new GQuarterSubTableHandler();
                    break;
                default:
                    //squancher
                    handler = null;
                    break;
            }
            handler.loadDate(LocalDateTime.now());
            boolean isHas = false;
            DataSource dataSource = null;
            String subName = handler.nextSubName();
            for (String ds : conf.getDataSource()) {
                dataSource = this.getRealDataSource(ds);
                isHas = handler.exists(key, dataSource, subName);
                if (!isHas){
                    try {
                        handler.createTable(key, dataSource, subName);
                    } catch (IOException e) {
                        log.warn("自动建表 任务失败 table={}_{}", key, subName, e);
                    }
                }
            }
        }

        log.debug("自动建表 任务结束 ...");
    }

    private DataSource getRealDataSource(String dataSource){
        DataSource ds = GSpringHelper.getBean(DataSource.class);
        if (ds instanceof MultiRoutingDataSource){
            return ((MultiRoutingDataSource) ds).getDataSource(dataSource);
//            log.debug("匹配到多个数据源");
        }else {
//            log.debug("匹配到单个数据源");
            return ds;
        }

    }
}
