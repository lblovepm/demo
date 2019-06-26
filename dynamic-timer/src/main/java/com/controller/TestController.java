package com.controller;

import com.manager.QuartzSchedulerManager;
import com.util.QuartzUtil;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/13 16:33
 */
@RestController
@RequestMapping("/quartz")
public class TestController {

    @RequestMapping("/test")
    public void test(String jobName,String cronExpression,String executeType) throws SchedulerException {

        switch (executeType){
            case "add":
                System.out.println("==============开始添加任务==============");
                QuartzSchedulerManager.addJob(QuartzUtil.getSchedulerInstance(),jobName,cronExpression);
                System.out.println("==============结束添加任务==============");
                break;
            case "update":
                System.out.println("==============开始更新任务==============");
                QuartzSchedulerManager.updateJob(QuartzUtil.getSchedulerInstance(),jobName,cronExpression);
                System.out.println("==============结束更新任务==============");
                break;
            case "delete":
                System.out.println("==============开始删除任务==============");
                QuartzSchedulerManager.removeJob(QuartzUtil.getSchedulerInstance(),jobName);
                System.out.println("==============结束删除任务==============");
                break;
            case "resumeAll":
                System.out.println("==============开始重启所有任务==============");
                QuartzSchedulerManager.resumeAllJob(QuartzUtil.getSchedulerInstance());
                System.out.println("==============结束重启所有任务==============");
                break;
            case "pauseAll":
                System.out.println("==============开始暂停所有任务==============");
                QuartzSchedulerManager.pauseAllJob(QuartzUtil.getSchedulerInstance());
                System.out.println("==============结束暂停所有任务==============");
                break;
            default:
                System.out.println("==============开始停止所有任务==============");
                QuartzSchedulerManager.shutdownScheduler(QuartzUtil.getSchedulerInstance());
                System.out.println("==============结束停止所有任务==============");
        }
    }
}
