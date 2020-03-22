package org.grant.zm.spring2.scheduling;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ZoomGrant 2020/3/21 12:24
 */
@Data
public class QuartzJob  implements Serializable {

    public static final String JOB_KEY = "JOB_KEY";

    private Long id;

    /** 定时器名称 */
    private String jobName;

    /** Bean名称 */
    private String beanName;

    /** 方法名称 */
    private String methodName;

    /** 参数 */
    private String params;

    /** cron表达式 */
    private String cronExpression;

    /** 状态 */
    private Boolean isPause = false;

    /** 备注 */
    private String remark;

    private Timestamp createTime;
}
