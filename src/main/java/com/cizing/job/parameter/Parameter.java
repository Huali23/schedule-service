package com.cizing.job.parameter;

import com.cizing.job.parameter.job.JobParameter;
import com.cizing.job.parameter.trigger.CronTriggerParameter;
import com.cizing.job.parameter.trigger.SimpleTriggerParameter;
import lombok.Data;

/**
 * @author liujingeng
 * @description 参数
 * @create 2020/09/10
 */
@Data
public class Parameter {

    private JobParameter job;

    private CronTriggerParameter trigger;

}