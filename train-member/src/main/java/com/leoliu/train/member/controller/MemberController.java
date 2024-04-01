package com.leoliu.train.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @GetMapping("/member")
    public String hello(){
        return "hello member!";
    }
}
