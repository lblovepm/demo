package com.job;

import org.quartz.*;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/13 16:09
 */
public class OrderJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Trigger trigger = jobExecutionContext.getTrigger();
        System.out.println("我是order-job---------->"+trigger.getJobKey());
    }

}
