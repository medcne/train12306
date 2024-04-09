package com.leoliu.train;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.leoliu.train.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class BatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class,args);
    }
}


