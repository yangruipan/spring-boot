package com.buy.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class SchedulerJob2 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //这里可以获取控制器绑定的值，实际应用中可以设置为某个活动的id,以便进行数据库操作
        Object jobName = jobExecutionContext.getJobDetail().getKey();
        System.err.println("这是"+jobName+"任务"+new Date());
    }
}
