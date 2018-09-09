package com.buy.controller;

/*import com.buy.quartz.SchedulerJob1;
import com.buy.quartz.SchedulerJob2;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Set;

@Controller
public class QuartzController2 {
    @Resource(name = "multitaskScheduler")
    private Scheduler scheduler;

    @ResponseBody
    @RequestMapping("task1")
    public Object task1() throws SchedulerException {
        //配置定时任务对应的Job，这里执行的是ScheduledJob类中定时的方法
        JobDetail jobDetail = JobBuilder.newJob(SchedulerJob1.class).withIdentity("job1", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
        // 每3s执行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);

        return "任务1";
    }

    @ResponseBody
    @RequestMapping("task2/{jobName}")
    public Object task2(@PathVariable(value = "jobName") String jobName) throws SchedulerException {
        //配置定时任务对应的Job，这里执行的是ScheduledJob类中定时的方法
        JobDetail jobDetail = JobBuilder
                .newJob(SchedulerJob2.class)
                .usingJobData("jobName",jobName)
                .withIdentity(jobName, "group1")
                .build();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
        // 每3s执行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2"+jobName, "group1")
                .withSchedule(scheduleBuilder)
                .build();

        scheduler.scheduleJob(jobDetail,cronTrigger);

        return jobName;
    }

    @ResponseBody
    @RequestMapping("jobs")
    public Object Jobs() throws SchedulerException {

        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());

        //获取所有的job集合
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());

        //可以在这进行线上任务和数据库任务匹配操作，确保该进行的活动进行活动

        return jobKeys;
    }
}*/
