package com.cizing.job.parameter.trigger;

import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;

import java.util.Date;

public interface TriggerParameterBehavior {

    String getName();

    String getGroup();

    Date getStartTime();

    Date getEndTime();

    int getMisfireInstruction();

    ScheduleBuilder<? extends Trigger> getScheduleBuilder();

}
