package com.buy.controller;

/*import org.quartz.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class QuartzController {

    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;


    @ResponseBody
    @GetMapping("/{second}/quart")
    public Object quartzTest(@PathVariable("second")Integer second) throws SchedulerException {
        CronTrigger cron  = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        String currentCron = cron.getCronExpression();// 当前Trigger使用的
        System.err.println("当前trigger使用的-"+currentCron);

        //修改每隔?秒执行任务
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/"+second+" * * * * ?");

        // 按新的cronExpression表达式重新构建trigger
        cron = cron.getTriggerBuilder().withIdentity(cronTrigger.getKey())
                .withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(cronTrigger.getKey(),cron);

        return "-这是quartz测试！";
    }
}*/
