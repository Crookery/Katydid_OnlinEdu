package com.xg.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = {"com.xg.cms.mapper"})
@SpringBootApplication
@ComponentScan(basePackages = {"com.xg"})
public class BannerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BannerApplication.class,args);
    }
}
