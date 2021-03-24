package com.cizing.job.parameter.trigger;

import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;

import java.util.Date;

/**
 * @author liujingeng
 * @description
 * @create 2020/09/15
 */
public abstract class AbstractTriggerParameter implements TriggerParameterBehavior {

    private String name;

    private String group;

    private Date startTime;

    private Date endTime;

    private int misfireInstruction = Trigger.MISFIRE_INSTRUCTION_SMART_POLICY;

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setMisfireInstruction(int misfireInstruction) {
        this.misfireInstruction = misfireInstruction;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public Date getStartTime() {
        return this.startTime;
    }

    @Override
    public Date getEndTime() {
        return this.endTime;
    }

    @Override
    public int getMisfireInstruction() {
        return this.misfireInstruction;
    }

    @Override
    public abstract ScheduleBuilder<? extends Trigger> getScheduleBuilder();

}