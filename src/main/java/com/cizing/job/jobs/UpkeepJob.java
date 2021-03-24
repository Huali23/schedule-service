package com.cizing.job.jobs;

import com.cizing.job.parameter.job.JobParameterBehavior;
import com.cizing.job.parameter.job.build.JobParameterBuilder;
import com.cizing.job.parameter.trigger.TriggerParameterBehavior;
import com.cizing.job.parameter.trigger.build.CronTriggerParameterBuilder;
import com.cizing.service.jobs.upkeep.UpkeepJobService;
import org.quartz.CronTrigger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.TimeZone;

/**
 * @author liujingeng
 * @description 生成保养记录任务
 * @create 2020/09/03
 */
@DisallowConcurrentExecution
public class UpkeepJob extends InitializationJob {

    private final static Logger log = LoggerFactory.getLogger(UpkeepJob.class);

    private final String JOB_NAME = "upkeepJob";
    private final String JOB_GROUP = "upkeepGroup";
    private final String TRIGGER_NAME = "upkeepTrigger";
    private final String TRIGGER_GROUP = "upkeepTriggerGroup";
    private final String CRON = "0 5 0 * * ?";

    @Autowired
    UpkeepJobService upkeepJobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("upkeep job start at " + new Date());
        upkeepJobService.insertUpkeepTasksByRuntime();
    }

    @Override
    public JobParameterBehavior getJobParameterBehavior() {
        return JobParameterBuilder.newInstance()
                .jobClass(this.getClass())
                .name(JOB_NAME)
                .group(JOB_GROUP)
                .description("upkeep make task")
                .durability()
                .requestsRecovery()
                .build();
    }

    @Override
    public TriggerParameterBehavior getTriggerParameterBehavior() {
        return CronTriggerParameterBuilder.newInstance()
                .cronExpression(CRON)
                .name(TRIGGER_NAME)
                .group(TRIGGER_GROUP)
                .startTime(this.getStartTime())
                .misfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW)
                .build();
    }

    private Date getStartTime() {
        long nowTime =System.currentTimeMillis();
        long todayStartTime =nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
        return new Date(todayStartTime);
    }

}