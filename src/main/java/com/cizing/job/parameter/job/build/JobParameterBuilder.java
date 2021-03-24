package com.cizing.job.parameter.job.build;

import com.cizing.enums.ParameterEnum;
import com.cizing.job.parameter.job.JobParameter;
import org.apache.commons.lang.StringUtils;
import org.quartz.Job;

import java.util.Map;

/**
 * @author liujingeng
 * @description
 * @create 2020/09/14
 */
public class JobParameterBuilder {

    private Class<? extends Job> jobClass;
    private String name;
    private String group;
    private String description;
    private Map jobData;
    private Boolean durability;
    private Boolean requestsRecovery;

    private JobParameterBuilder(){}

    public static JobParameterBuilder newInstance(){
        return new JobParameterBuilder();
    }

    public JobParameterBuilder jobClass(Class<? extends Job> jobClass){
        this.jobClass = jobClass;
        return this;
    }

    public JobParameterBuilder jobClass(String jobClassName) {
        if (StringUtils.isNotBlank(jobClassName)){
            Class jobClass;
            try{
                jobClass = Class.forName(ParameterEnum.BASE_PACKAGE.getValue() + jobClassName);
                for (Class clazz : jobClass.getInterfaces()){
                    if (clazz.getName().equals(ParameterEnum.JOB_INTERFACE_NAME.getValue())){
                        this.jobClass = jobClass;
                    }
                }
            } catch (ClassNotFoundException e){
                throw new IllegalArgumentException("JobClass is not found, class name is : " + jobClassName);
            }
            throw new IllegalArgumentException("JobClass is not implements Job interface, class name is : " + jobClassName);
        }
        return this;
    }

    public JobParameterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public JobParameterBuilder group(String group) {
        this.group = group;
        return this;
    }

    public JobParameterBuilder description(String description) {
        this.description = description;
        return this;
    }

    public JobParameterBuilder jobData(Map jobData) {
        if (jobData != null){
            this.jobData = jobData;
        }
        return this;
    }

    public JobParameterBuilder durability(){
        this.durability = true;
        return this;
    }

    public JobParameterBuilder durability(boolean durability) {
        this.durability = durability;
        return this;
    }

    public JobParameterBuilder requestsRecovery() {
        this.requestsRecovery = true;
        return this;
    }

    public JobParameterBuilder requestsRecovery(boolean requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
        return this;
    }

    public JobParameter build(){
        JobParameter jobParameter = new JobParameter();
        jobParameter.setJobClass(jobClass);
        jobParameter.setName(name);
        jobParameter.setGroup(group);
        jobParameter.setJobData(jobData);
        jobParameter.setDurability(durability);
        jobParameter.setRequestsRecovery(requestsRecovery);
        jobParameter.setDescription(description);
        return jobParameter;
    }
}