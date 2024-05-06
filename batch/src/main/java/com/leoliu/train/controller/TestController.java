package com.leoliu.train.controller;

import com.leoliu.train.feign.BusinessFeignApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/batch")
public class TestController {

    @Resource
    BusinessFeignApi businessFeignApi;

    @GetMapping("/hello")
    public String hello() {
        String businessHello = businessFeignApi.hello();
        log.info(businessHello);
        return "Hello World! Batch! " + businessHello;
    }
}
