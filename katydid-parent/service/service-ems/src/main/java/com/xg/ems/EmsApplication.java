package com.xg.ems;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient  //nacos服务发现
@ComponentScan(basePackages = {"com.xg"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmsApplication.class,args);
    }
}
