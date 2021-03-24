package com.cizing.job.parameter.job;

import com.cizing.enums.ParameterEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.quartz.Job;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujingeng
 * @description
 * @create 2020/09/09
 */
public class JobParameter implements JobParameterBehavior {

    @JsonIgnore
    private Class<? extends Job> jobClass;

    private String jobClassName;

    private String name;

    private String group;

    private String description;

    private Map jobData;

    private Boolean durability;

    private Boolean requestsRecovery;

    @Override
    public Class<? extends Job> getJobClass() {
        if (jobClass != null){
            return jobClass;
        } else if (StringUtils.isNotBlank(jobClassName)){
            Class<?> jobClass;
            try{
                jobClass = Class.forName(ParameterEnum.BASE_PACKAGE.getValue() + jobClassName);
                if (Job.class.isAssignableFrom(jobClass)){
                    return (Class<? extends Job>) jobClass;
                }
            } catch (ClassNotFoundException e){
                throw new IllegalArgumentException("JobClass is not found, class name is : " + jobClassName);
            }
            throw new IllegalArgumentException("JobClass is not implements Job interface, class name is : " + jobClassName);
        }
        throw new IllegalArgumentException("jobClass is null");
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
    public String getDescription() {
        return this.description;
    }

    @Override
    public Map getJobData() {
        if (this.jobData == null){
            return new HashMap();
        } else {
            return this.jobData;
        }
    }

    @Override
    public boolean getDurability() {
        return this.durability;
    }

    @Override
    public boolean getRequestsRecovery() {
        return this.requestsRecovery;
    }

    public void setJobClass(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJobData(Map jobData) {
        this.jobData = jobData;
    }

    public void setDurability(Boolean durability) {
        this.durability = durability;
    }

    public void setRequestsRecovery(Boolean requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }
}