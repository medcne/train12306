package com.leoliu.train.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.leoliu.train.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessTestController {

    @Resource
    private TestService testService;

    @SentinelResource("hello")
    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        testService.hello2();
        return "Hello World! Business!";
    }

    @SentinelResource("hello1")
    @GetMapping("/hello1")
    public String hello1() throws InterruptedException {
        testService.hello2();
        return "Hello World! Business1!";
    }

}
