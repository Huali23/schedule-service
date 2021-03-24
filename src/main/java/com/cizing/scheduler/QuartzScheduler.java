package com.cizing.scheduler;

import com.cizing.job.jobs.InitializationJob;
import com.cizing.job.parameter.job.JobParameterBehavior;
import com.cizing.job.parameter.Parameter;
import com.cizing.job.parameter.trigger.TriggerParameterBehavior;
import org.quartz.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface QuartzScheduler {

    JobDetail getJobDetail(JobKey jobKey) throws SchedulerException;

    JobDetail getJobDetail(String name, String group) throws  SchedulerException;

    JobDetail getJobDetailByDefaultGroup(String name) throws SchedulerException;

    Trigger getTrigger(TriggerKey triggerKey) throws SchedulerException;

    Trigger getTrigger(String name, String group) throws SchedulerException;

    Trigger getTriggerByDefaultGroup(String name) throws  SchedulerException;

    List<String> listJobGroupNames() throws SchedulerException;

    List<String> listTriggerGroupNames() throws SchedulerException;

    Set<JobKey> listJobKeys() throws SchedulerException;

    Set<JobKey> listJobKeys(String group) throws SchedulerException;

    Set<JobKey> listJobKeysByDefaultGroup() throws SchedulerException;

    void addJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException;

    void addJob(Parameter parameter) throws SchedulerException;

    void addJob(JobParameterBehavior jobParameterBehavior, TriggerParameterBehavior triggerParameterBehavior) throws SchedulerException;

    void addJob(InitializationJob initializationJob) throws SchedulerException;

    Date rescheduleJob(TriggerKey oldTriggerKey, Trigger newTrigger) throws SchedulerException;

    Date rescheduleJob(String oldName, String oldGroup, TriggerParameterBehavior triggerParameterBehavior) throws SchedulerException;

    boolean pauseJob(JobKey jobKey) throws SchedulerException;

    boolean pauseJob(String name, String group) throws SchedulerException;

    boolean pauseJobByDefaultGroup(String name) throws SchedulerException;

    void pauseAllJob() throws SchedulerException;

    boolean resumeJob(JobKey jobKey) throws SchedulerException;

    boolean resumeJob(String name, String group) throws SchedulerException;

    boolean resumeJobByDefaultGroup(String name) throws SchedulerException;

    void resumeAllJob() throws SchedulerException;

    boolean deleteJob(JobKey jobKey) throws SchedulerException;

    boolean deleteJob(String name, String group) throws SchedulerException;

}
