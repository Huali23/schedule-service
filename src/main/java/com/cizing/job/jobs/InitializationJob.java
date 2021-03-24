package com.cizing.job.jobs;

import com.cizing.job.parameter.job.JobParameterBehavior;
import com.cizing.job.parameter.trigger.TriggerParameterBehavior;
import org.quartz.Job;

public abstract class InitializationJob implements Job {

    public abstract JobParameterBehavior getJobParameterBehavior();

    public abstract TriggerParameterBehavior getTriggerParameterBehavior();

}
