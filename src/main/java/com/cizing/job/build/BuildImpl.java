package com.cizing.job.build;

import com.cizing.job.parameter.job.JobParameterBehavior;
import com.cizing.job.parameter.trigger.TriggerParameterBehavior;
import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @author liujingeng
 * @description 构建 jobDetails 和 trigger
 * @create 2020/09/10
 */
@Component
public class BuildImpl implements BuildBehavior {

    @Override
    public JobKey buildJobKey(JobParameterBehavior jobParameterBehavior) {
        return new JobKey(jobParameterBehavior.getName(), jobParameterBehavior.getGroup());
    }

    @Override
    public TriggerKey buildTriggerKey(TriggerParameterBehavior triggerParameterBehavior) {
        return new TriggerKey(triggerParameterBehavior.getName(), triggerParameterBehavior.getGroup());
    }

    @Override
    public JobDetail buildJobDetail(JobParameterBehavior jobParameterBehavior) {
        return JobBuilder.newJob(jobParameterBehavior.getJobClass())
                .withIdentity(this.buildJobKey(jobParameterBehavior))
                .usingJobData(new JobDataMap(jobParameterBehavior.getJobData()))
                .requestRecovery(jobParameterBehavior.getRequestsRecovery())
                .storeDurably(jobParameterBehavior.getDurability())
                .build();
    }

    @Override
    public Trigger buildTrigger(TriggerParameterBehavior triggerParameterBehavior) {
        return TriggerBuilder.newTrigger()
                .withIdentity(this.buildTriggerKey(triggerParameterBehavior))
                .withSchedule(triggerParameterBehavior.getScheduleBuilder())
                .startAt(triggerParameterBehavior.getStartTime())
                .endAt(triggerParameterBehavior.getEndTime())
                .build();
    }

}