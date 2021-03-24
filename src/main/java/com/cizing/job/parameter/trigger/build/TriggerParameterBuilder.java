package com.cizing.job.parameter.trigger.build;

import com.cizing.job.parameter.trigger.TriggerParameterBehavior;

import java.util.Date;

/**
 * @author liujingeng
 * @description
 * @create 2020/09/11
 */
public abstract class TriggerParameterBuilder<T extends TriggerParameterBehavior> {

     protected String name;

     protected String group;

     protected Date startTime;

     protected Date endTime;

     protected int misfireInstruction;

     public abstract TriggerParameterBehavior build();

}