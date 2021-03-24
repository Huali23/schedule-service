package com.cizing.enums;

public enum ParameterEnum {

    BASE_PACKAGE("com.cizing.job.jobs."),
    JOB_INTERFACE_NAME("org.quartz.Job"),
    ;

    private String value;

    ParameterEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
