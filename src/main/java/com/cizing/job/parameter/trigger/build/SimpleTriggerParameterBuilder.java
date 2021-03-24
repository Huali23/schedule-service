package com.cizing.job.parameter.trigger.build;

import com.cizing.job.parameter.trigger.SimpleTriggerParameter;

import java.util.Date;

/**
 * @author liujingeng
 * @description
 * @create 2020/09/14
 */
public class SimpleTriggerParameterBuilder extends TriggerParameterBuilder<SimpleTriggerParameter> {

    private SimpleTriggerParameterBuilder() {

    }

    public static SimpleTriggerParameterBuilder newInstance() {
        return new SimpleTriggerParameterBuilder();
    }

    public SimpleTriggerParameterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public SimpleTriggerParameterBuilder group(String group) {
        this.group = group;
        return this;
    }

    public SimpleTriggerParameterBuilder startTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public SimpleTriggerParameterBuilder startNow() {
        this.startTime = new Date();
        return this;
    }

    public SimpleTriggerParameterBuilder endTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public SimpleTriggerParameterBuilder misfireInstruction(int misfireInstruction) {
        this.misfireInstruction = misfireInstruction;
        return this;
    }

    @Override
    public SimpleTriggerParameter build() {
        SimpleTriggerParameter simpleTriggerParameter = new SimpleTriggerParameter();
        simpleTriggerParameter.setName(name);
        simpleTriggerParameter.setGroup(group);
        simpleTriggerParameter.setStartTime(startTime);
        simpleTriggerParameter.setEndTime(endTime);
        simpleTriggerParameter.setMisfireInstruction(misfireInstruction);
        return simpleTriggerParameter;
    }

}