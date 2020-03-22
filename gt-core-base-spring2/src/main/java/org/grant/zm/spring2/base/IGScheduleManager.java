package org.grant.zm.spring2.base;

import org.grant.zm.spring2.scheduling.QuartzJob;

/**
 * grant
 * 20/3/2020 2:34 下午
 * 描述：管理器
 */
public interface IGScheduleManager {

    void addJob(QuartzJob quartzJob);
    void updateJobCron(QuartzJob quartzJob);
    void deleteJob(QuartzJob quartzJob);
    void resumeJob(QuartzJob quartzJob);
    void runJobNow(QuartzJob quartzJob);
    void pauseJob(QuartzJob quartzJob);
}
