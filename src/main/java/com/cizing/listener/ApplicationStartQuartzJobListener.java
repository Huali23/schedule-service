package com.cizing.listener;

import com.cizing.job.JobFactory;
import com.cizing.scheduler.QuartzSchedulerImpl;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.concurrent.*;

/**
 * @author liujingeng
 * @create 2020/09/03
 */
@Configuration
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationStartQuartzJobListener.class);

    @Autowired
    QuartzSchedulerImpl quartzSchedulerImpl;

    @Autowired
    JobFactory jobFactory;

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event){
        try{
            quartzSchedulerImpl.schedulerJob();
        } catch (SchedulerException e){
            logger.error("ApplicationStartQuartzJobListener error : " + e.getMessage());
        }
    }

    @Bean("schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setStartupDelay(1);
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(300);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, queue);
        schedulerFactoryBean.setTaskExecutor(executor);
        schedulerFactoryBean.setJobFactory(jobFactory);
        return schedulerFactoryBean;
    }

}