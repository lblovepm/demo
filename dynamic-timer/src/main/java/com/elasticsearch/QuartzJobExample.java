package com.elasticsearch;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mr.LB
 * @description: quartz示例定时器类
 * @date 2019/6/13 11:53
 */
public class QuartzJobExample implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "★★★★★★★★★★★");
    }

}
