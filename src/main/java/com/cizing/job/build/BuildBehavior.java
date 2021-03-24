package com.cizing.job.build;

import com.cizing.job.parameter.job.JobParameterBehavior;
import com.cizing.job.parameter.trigger.TriggerParameterBehavior;
import org.quartz.*;
import org.springframework.stereotype.Component;

public interface BuildBehavior {

    JobKey buildJobKey(JobParameterBehavior jobParameterBehavior);

    TriggerKey buildTriggerKey(TriggerParameterBehavior triggerParameterBehavior);

    JobDetail buildJobDetail(JobParameterBehavior jobParameterBehavior);

    Trigger buildTrigger(TriggerParameterBehavior triggerParameterBehavior);

}
