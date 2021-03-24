package com.cizing.job.parameter.trigger.build;

import com.cizing.job.parameter.trigger.CronTriggerParameter;

import java.util.Date;

/**
 * @author liujingeng
 * @description
 * @create 2020/09/11
 */
public class CronTriggerParameterBuilder extends TriggerParameterBuilder<CronTriggerParameter> {

    private String cronExpression;

    private CronTriggerParameterBuilder() {

    }

    public static CronTriggerParameterBuilder newInstance() {
        return new CronTriggerParameterBuilder();
    }

    public CronTriggerParameterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CronTriggerParameterBuilder group(String group) {
        this.group = group;
        return this;
    }

    public CronTriggerParameterBuilder startTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public CronTriggerParameterBuilder startNow() {
        this.startTime = new Date();
        return this;
    }

    public CronTriggerParameterBuilder endTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public CronTriggerParameterBuilder misfireInstruction(int misfireInstruction) {
        this.misfireInstruction = misfireInstruction;
        return this;
    }

    public CronTriggerParameterBuilder cronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        return this;
    }

    @Override
    public CronTriggerParameter build() {
        CronTriggerParameter cronTriggerParameter = new CronTriggerParameter();
        cronTriggerParameter.setName(name);
        cronTriggerParameter.setGroup(group);
        cronTriggerParameter.setStartTime(startTime);
        cronTriggerParameter.setEndTime(endTime);
        cronTriggerParameter.setCronExpression(cronExpression);
        cronTriggerParameter.setMisfireInstruction(misfireInstruction);
        return cronTriggerParameter;
    }

}