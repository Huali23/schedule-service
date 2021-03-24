package com.cizing.scheduler;

import com.cizing.job.build.BuildImpl;
import com.cizing.job.jobs.InitializationJob;
import com.cizing.job.jobs.KoalaEventJob;
import com.cizing.job.jobs.UpkeepJob;
import com.cizing.job.parameter.Parameter;
import com.cizing.job.parameter.job.JobParameterBehavior;
import com.cizing.job.parameter.trigger.TriggerParameterBehavior;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author liujingeng
 * @description 任务调度处理器
 * @create 2020/09/03
 */
@Component("QuartzScheduler")
public class QuartzSchedulerImpl implements QuartzScheduler {

    @Autowired
    Scheduler scheduler;

    @Autowired
    BuildImpl buildImpl;

    public final void schedulerJob() throws SchedulerException {
        this.setDefaultJob();
        scheduler.start();
    }

    private void setDefaultJob() throws SchedulerException {
        this.addJob(new UpkeepJob());
        this.addJob(new KoalaEventJob());
    }

    @Override
    public JobDetail getJobDetail(JobKey jobKey) throws SchedulerException {
        return scheduler.getJobDetail(jobKey);
    }

    @Override
    public JobDetail getJobDetail(String name, String group) throws SchedulerException {
        return this.getJobDetail(new JobKey(name, group));
    }

    @Override
    public JobDetail getJobDetailByDefaultGroup(String name) throws SchedulerException {
        return this.getJobDetail(name, Scheduler.DEFAULT_GROUP);
    }

    @Override
    public Trigger getTrigger(TriggerKey triggerKey) throws SchedulerException {
        return scheduler.getTrigger(triggerKey);
    }

    @Override
    public Trigger getTrigger(String name, String group) throws SchedulerException {
        return this.getTrigger(new TriggerKey(name, group));
    }

    @Override
    public Trigger getTriggerByDefaultGroup(String name) throws SchedulerException {
        return this.getTrigger(name, Scheduler.DEFAULT_GROUP);
    }

    @Override
    public List<String> listJobGroupNames() throws SchedulerException {
        return this.scheduler.getJobGroupNames();
    }

    @Override
    public List<String> listTriggerGroupNames() throws SchedulerException {
        return this.scheduler.getTriggerGroupNames();
    }

    @Override
    public Set<JobKey> listJobKeys() throws SchedulerException {
        return scheduler.getJobKeys(GroupMatcher.anyGroup());
    }

    @Override
    public Set<JobKey> listJobKeys(String group) throws SchedulerException {
        return scheduler.getJobKeys(GroupMatcher.groupContains(group));
    }

    @Override
    public Set<JobKey> listJobKeysByDefaultGroup() throws SchedulerException {
        return this.listJobKeys(Scheduler.DEFAULT_GROUP);
    }

    @Override
    public void addJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public void addJob(Parameter parameter) throws SchedulerException {
        this.addJob(parameter.getJob(), parameter.getTrigger());
    }

    @Override
    public void addJob(JobParameterBehavior jobParameterBehavior, TriggerParameterBehavior triggerParameterBehavior) throws SchedulerException {
        JobDetail jobDetail = this.buildImpl.buildJobDetail(jobParameterBehavior);
        Trigger trigger = this.buildImpl.buildTrigger(triggerParameterBehavior);
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public void addJob(InitializationJob initializationJob) throws SchedulerException {
        this.addJob(initializationJob.getJobParameterBehavior(), initializationJob.getTriggerParameterBehavior());
    }

    @Override
    public Date rescheduleJob(TriggerKey oldTriggerKey, Trigger newTrigger) throws SchedulerException {
        return scheduler.rescheduleJob(oldTriggerKey, newTrigger);
    }

    @Override
    public Date rescheduleJob(String oldName, String oldGroup, TriggerParameterBehavior triggerParameterBehavior) throws SchedulerException {
        return this.rescheduleJob(new TriggerKey(oldName, oldGroup), buildImpl.buildTrigger(triggerParameterBehavior));
    }

    @Override
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    @Override
    public boolean pauseJob(JobKey jobKey) throws SchedulerException {
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null){
            scheduler.pauseJob(jobKey);
            return true;
        }
        return false;
    }

    @Override
    public boolean pauseJob(String name, String group) throws SchedulerException {
        return this.pauseJob(new JobKey(name, group));
    }

    @Override
    public boolean pauseJobByDefaultGroup(String name) throws SchedulerException {
        return this.pauseJob(name, Scheduler.DEFAULT_GROUP);
    }

    @Override
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    @Override
    public boolean resumeJob(JobKey jobKey) throws SchedulerException {
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null){
            scheduler.resumeJob(jobKey);
            return true;
        }
        return false;
    }

    @Override
    public boolean resumeJob(String name, String group) throws SchedulerException {
        return this.resumeJob(new JobKey(name, group));
    }

    @Override
    public boolean resumeJobByDefaultGroup(String name) throws SchedulerException {
        return this.resumeJob(name, Scheduler.DEFAULT_GROUP);
    }

    @Override
    public boolean deleteJob(JobKey jobKey) throws SchedulerException {
        JobDetail jobDetail = this.getJobDetail(jobKey);
        if (jobDetail == null){
            return false;
        } else {
            return scheduler.deleteJob(jobKey);
        }
    }

    public boolean deleteJob(String name, String group) throws SchedulerException {
        return this.deleteJob(new JobKey(name, group));
    }

}