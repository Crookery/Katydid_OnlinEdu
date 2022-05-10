package com.xg.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient
@MapperScan(basePackages = {"com.xg.ucenter.mapper"})
@ComponentScan(basePackages = {"com.xg"})
@SpringBootApplication
public class UCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UCenterApplication.class,args);
    }
}
