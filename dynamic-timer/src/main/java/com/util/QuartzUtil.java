package com.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/13 14:06
 */
public class QuartzUtil {

    private static Scheduler scheduler = null;

    /**
     * 获取JobDetail实例
     * @param jobName
     * @param jobGroupName
     * @return
     */
    public static JobDetail getJobDetailInstance(Class<? extends Job> jobClass,String jobName, String jobGroupName){
        JobBuilder jobBuilder = JobBuilder.newJob(jobClass);

        JobKey jobKey = new JobKey(jobName,jobGroupName);
        return jobBuilder.withIdentity(jobKey).build();
    }

    /**
     * 获取Trigger实例
     * @param jobName
     * @param triggerGroupName
     * @return
     */
    public static CronTrigger getCronTriggerInstance(String jobName, String triggerGroupName,String cronExpression){
        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        return  TriggerBuilder.newTrigger().withIdentity(jobName,triggerGroupName).withSchedule(scheduleBuilder).build();
    }

    /**
     * 获取Scheduler实例
     * @return
     * @throws SchedulerException
     */
    public static Scheduler getSchedulerInstance() throws SchedulerException {
        if(null == scheduler || scheduler.isShutdown()){
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
        }

        return scheduler;
    }

}
