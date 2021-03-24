package com.cizing.job.parameter.trigger;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.quartz.CronScheduleBuilder;

import static org.quartz.CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING;
import static org.quartz.CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW;

/**
 * @author liujingeng
 * @description
 * @create 2020/09/10
 */
public class CronTriggerParameter extends AbstractTriggerParameter {

    private String cronExpression;

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Override
    public CronScheduleBuilder getScheduleBuilder() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionIgnoreMisfires();
        switch (getMisfireInstruction()){
            case MISFIRE_INSTRUCTION_DO_NOTHING:
                cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
                break;
            case MISFIRE_INSTRUCTION_FIRE_ONCE_NOW :
                cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
        }
        return cronScheduleBuilder;
    }

}