package com.cizing.controller;

import com.cizing.job.parameter.Parameter;
import com.cizing.job.parameter.trigger.CronTriggerParameter;
import com.cizing.scheduler.QuartzScheduler;
import com.cizing.system.common.utils.ResponseModel;
import com.cizing.system.common.utils.ResultCode;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 任务调度
 *
 * @author liujingeng
 * @description 任务调度控制层
 * @create 2020/09/09
 */
@RestController
public class JobController {

    @Autowired
    private QuartzScheduler quartzSchedulerImpl;

    /**
     * 查询任务详情
     * @param group 任务组
     * @param name 任务名
     * @return JobDetail
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @GetMapping("/job/{name}/{group}")
    ResponseModel getJob(@PathVariable("group") String group,
                         @PathVariable("name") String name) {
        try{
            return ResponseModel.success(quartzSchedulerImpl.getJobDetail(name, group)).message("查询成功").code(ResultCode.SUCCESS);
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }
    }

    /**
     * 查询全部任务组列表
     * @return list
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @GetMapping("/job/group/names")
    ResponseModel listJobGroupNames() {
        try{
            return ResponseModel.success(quartzSchedulerImpl.listJobGroupNames()).message("查询成功").code(ResultCode.SUCCESS);
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }
    }

    /**
     * 查询全部任务key列表
     * @return list
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @GetMapping("/job/keys")
    ResponseModel listJobKeys() {
        try{
            return ResponseModel.success(quartzSchedulerImpl.listJobKeys()).message("查询成功").code(ResultCode.SUCCESS);
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }
    }

    /**
     * 查询任务组内所有任务key列表
     * @param group 任务组
     * @return list
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @GetMapping("/job/keys/{group}")
    ResponseModel listJobKeysByGroup(@PathVariable("group") String group) {
        try{
            return ResponseModel.success(quartzSchedulerImpl.listJobKeys(group)).message("查询成功").code(ResultCode.SUCCESS);
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }
    }

    /**
     * 查询默认任务组下任务key列表
     * @return list
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @GetMapping("/job/keys/default")
    ResponseModel listJobKeysByDefaultGroup() {
        try{
            return ResponseModel.success(quartzSchedulerImpl.listJobKeysByDefaultGroup()).message("查询成功").code(ResultCode.SUCCESS);
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }
    }

    /**
     * 新建任务
     * @param parameter 任务参数
     * @return void
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @PostMapping("/job")
    ResponseModel addJob(@RequestBody Parameter parameter) {
        try{
            quartzSchedulerImpl.addJob(parameter);
            return ResponseModel.success("").message("新增成功").code(ResultCode.SUCCESS);
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }
    }

    /**
     * 修改任务表达式
     * 修改任务cronTrigger
     * @param oldName 任务名
     * @param oldGroup 任务组
     * @param cronTriggerParameter cron表达式参数
     * @return date
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @PatchMapping("/job/trigger/{oldName}/{oldGroup}")
    ResponseModel rescheduleJob(@PathVariable("oldName") String oldName,
                                @PathVariable("oldGroup") String oldGroup,
                                @RequestBody CronTriggerParameter cronTriggerParameter) {
        try{
            Date nextRuntime = quartzSchedulerImpl.rescheduleJob(oldName, oldGroup, cronTriggerParameter);
            if (nextRuntime != null){
                return ResponseModel.success(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nextRuntime)).message("修改").code(ResultCode.SUCCESS);
            } else {
                return ResponseModel.failure("修改").code(ResultCode.ERROR);
            }
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }

    }

    /**
     * 暂停任务
     * @param name 任务名
     * @param group 任务组
     * @return boolean
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @PatchMapping("/job/pause/{name}/{group}")
    ResponseModel pauseJob(@PathVariable("name") String name,
                           @PathVariable("group") String group) {
        try{
            if (quartzSchedulerImpl.pauseJob(name, group)){
                return ResponseModel.success("").message("操作成功").code(ResultCode.SUCCESS);
            } else {
                return ResponseModel.failure("操作失败").code(ResultCode.ERROR);
            }
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }
    }

    /**
     * 暂停全部任务
     * @return void
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @PatchMapping("/jobs/pause")
    ResponseModel pauseJob() {
        try{
            quartzSchedulerImpl.pauseAllJob();
            return ResponseModel.success("").message("操作成功").code(ResultCode.SUCCESS);
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }
    }

    /**
     * 唤醒任务
     * @param name 任务名
     * @param group 任务组
     * @return void
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @PatchMapping("/job/resume/{name}/{group}")
    ResponseModel resumeJob(@PathVariable("name") String name,
                           @PathVariable("group") String group) {
        try{
            if (quartzSchedulerImpl.resumeJob(name, group)){
                return ResponseModel.success("").message("操作成功").code(ResultCode.SUCCESS);
            } else {
                return ResponseModel.failure("操作失败").code(ResultCode.ERROR);
            }
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }
    }

    /**
     * 唤醒全部任务
     * @return void
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @PatchMapping("/jobs/resume")
    ResponseModel resumeJob() {
        try{
            quartzSchedulerImpl.resumeAllJob();
            return ResponseModel.success("").message("操作成功").code(ResultCode.SUCCESS);
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }
    }

    /**
     * 删除任务
     * 删除任务组下对应任务
     * @param name 任务名
     * @param group 任务组
     * @return boolean
     * @Author liujingeng
     * @Date 2020/11/12
     */
    @DeleteMapping("job/{name}/{group}")
    ResponseModel deleteJob(@PathVariable("name") String name,
                            @PathVariable("group") String group) {
        try{
            if (quartzSchedulerImpl.deleteJob(name, group)){
                return ResponseModel.success("").message("删除成功").code(ResultCode.SUCCESS);
            } else {
                return ResponseModel.failure("删除失败").code(ResultCode.ERROR);
            }
        } catch (SchedulerException e){
            return ResponseModel.failure(e.getMessage()).code(ResultCode.ERROR);
        }
    }

}