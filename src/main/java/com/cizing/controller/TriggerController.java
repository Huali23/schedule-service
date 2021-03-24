package com.cizing.controller;

import com.cizing.scheduler.QuartzScheduler;
import com.cizing.system.common.utils.ResponseModel;
import com.cizing.system.common.utils.ResultCode;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务触发器
 *
 * @author liujingeng
 * @description 触发器控制层
 * @create 2020/09/09
 */
@RestController
public class TriggerController {

    @Autowired
    private QuartzScheduler quartzSchedulerImpl;

    /**
     * 查询触发器详情
     * 通过分组和名字查询触发器详情
     * @param group 分组名
     * @param name 触发器名
     * @return TriggerDetail
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @GetMapping("/trigger/{group}/{name}")
    ResponseModel getTrigger(@PathVariable("group") String group,
                             @PathVariable("name") String name) {
        try{
            return ResponseModel.success(quartzSchedulerImpl.getTrigger(group, name)).message("查询成功").code(ResultCode.SUCCESS);
        } catch (SchedulerException e){
            return ResponseModel.failure("查询失败").code(ResultCode.ERROR);
        }
    }

    /**
     * 查询触发器分组列表
     * @return list
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @GetMapping("/trigger/group/names")
    ResponseModel listTriggerGroupNames() {
        try{
            return ResponseModel.success(quartzSchedulerImpl.listTriggerGroupNames()).message("查询成功").code(ResultCode.SUCCESS);
        } catch (SchedulerException e){
            return ResponseModel.failure("查询失败").code(ResultCode.ERROR);
        }
    }

}