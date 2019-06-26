package com.manager;

import com.job.OrderJob;
import com.util.QuartzUtil;
import org.quartz.*;

/**
 * @author Mr.LB
 * @description: Quartz调度管理器
 * @date 2019/6/13 11:55
 * TODO
 */
public class QuartzSchedulerManager {

    private static String JOB_GROUP_NAME = "JOB_GROUP_NAME";

    private static String TRIGGER_GROUP_NAME = "TRIGGER_GROUP_NAME";

    /**
     * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     *
//     * @param jobClass 执行任务的类
     * @param jobName 任务名
     * @param cronExpression 时间设置，参考quartz说明文档
     *
     * @Title: QuartzManager.javascheduler
     */
    public static void addJob(Scheduler scheduler,String jobName,String cronExpression) throws SchedulerException {
        //创建任务
        JobDetail jobDetail = QuartzUtil.getJobDetailInstance(OrderJob.class,jobName,JOB_GROUP_NAME);
        //创建触发器
        CronTrigger trigger = QuartzUtil.getCronTriggerInstance(jobName,TRIGGER_GROUP_NAME,cronExpression);
        scheduler.scheduleJob(jobDetail,trigger);
        //启动调度器
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    /**
     * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName
     * @param cronExpression
     *
     * @Title: QuartzManager.java
     */
    public static void updateJob(Scheduler scheduler,String jobName, String cronExpression) {
        try {
            TriggerKey triggerKey = new TriggerKey(jobName,TRIGGER_GROUP_NAME);
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                throw new Exception("没有找到该触发器!");
            }
            //先将当前查询到的触发器移除
            removeJob(scheduler,jobName);
            //再新增
            addJob(scheduler,jobName,cronExpression);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param scheduler 调度器
     * @param jobName
     *
     * @Title: QuartzManager.java
     */
    public static void removeJob(Scheduler scheduler, String jobName) {
        try {
            TriggerKey triggerKey = new TriggerKey(jobName,TRIGGER_GROUP_NAME);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            JobKey jobKey = new JobKey(jobName,JOB_GROUP_NAME);
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 启动所有定时任务
     *
     * @param scheduler 调度器
     * @Title: QuartzManager.java
     */
    public static void resumeAllJob(Scheduler scheduler) {
        try {
//            Set<String> pausedTriggerGroupSet = scheduler.getPausedTriggerGroups();
//            if(null == pausedTriggerGroupSet || pausedTriggerGroupSet.size() == 0){
//                throw new Exception("没有可启动的定时任务");
//            }
            scheduler.resumeAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 暂停所有定时任务
     *
     * @param scheduler 调度器
     */
    public static void pauseAllJob(Scheduler scheduler){
        try {
            scheduler.pauseAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description:关闭所有定时任务
     *
     * @param scheduler 调度器
     * @Title: QuartzManager.java
     */
    public static void shutdownScheduler(Scheduler scheduler) {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
