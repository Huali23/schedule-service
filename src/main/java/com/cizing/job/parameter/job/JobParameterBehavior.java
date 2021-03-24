package com.cizing.job.parameter.job;

import org.quartz.Job;

import java.util.Map;

public interface JobParameterBehavior {

    Class<? extends Job> getJobClass();

    String getName();

    String getGroup();

    String getDescription();

    Map getJobData();

    boolean getDurability();

    boolean getRequestsRecovery();

}
