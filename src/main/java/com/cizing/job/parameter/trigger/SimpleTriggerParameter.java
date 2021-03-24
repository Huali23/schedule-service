package com.cizing.job.parameter.trigger;

import org.quartz.ScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;

import static org.quartz.SimpleTrigger.*;

/**
 * 分类名称
 *
 * @author liujingeng
 * @description 触发器参数
 * @create 2020/09/10
 */
public class SimpleTriggerParameter extends AbstractTriggerParameter {

    @Override
    public ScheduleBuilder<? extends Trigger> getScheduleBuilder() {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        switch(getMisfireInstruction()) {
            case MISFIRE_INSTRUCTION_FIRE_NOW : simpleScheduleBuilder.withMisfireHandlingInstructionFireNow();
                break;
            case MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT : simpleScheduleBuilder.withMisfireHandlingInstructionNextWithExistingCount();
                break;
            case MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT : simpleScheduleBuilder.withMisfireHandlingInstructionNextWithRemainingCount();
                break;
            case MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT : simpleScheduleBuilder.withMisfireHandlingInstructionNowWithExistingCount();
                break;
            case MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT : simpleScheduleBuilder.withMisfireHandlingInstructionNowWithRemainingCount();
                break;
        }
        return simpleScheduleBuilder;
    }
}