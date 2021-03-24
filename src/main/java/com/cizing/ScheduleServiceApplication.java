package com.cizing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients
@SpringBootApplication
public class ScheduleServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(ScheduleServiceApplication.class);
    }

}