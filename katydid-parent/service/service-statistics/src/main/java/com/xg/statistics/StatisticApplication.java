package com.xg.statistics;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling       //定时任务
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.xg"})
@MapperScan(basePackages = {"com.xg.statistics.mapper"})
@SpringBootApplication
public class StatisticApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticApplication.class,args);
    }
}
