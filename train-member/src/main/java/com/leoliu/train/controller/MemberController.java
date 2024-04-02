package com.leoliu.train.controller;

import com.leoliu.train.req.MemberLoginReq;
import com.leoliu.train.req.MemberRegisterReq;
import com.leoliu.train.req.MemberSendCodeReq;
import com.leoliu.train.resp.CommonResp;
import com.leoliu.train.resp.MemberLoginResp;
import com.leoliu.train.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;
    @GetMapping("/count")
    public CommonResp<Integer> count(){
        int count = memberService.count();
        return new CommonResp<>(count);
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req){
        long registered = memberService.register(req);
        return new CommonResp<>(registered);
    }

    @PostMapping("/send-code")
    public CommonResp<Long> register(@Valid @RequestBody MemberSendCodeReq req){
        memberService.sendCode(req);
        return new CommonResp<>();
    }
    @PostMapping("/login")
    public CommonResp<MemberLoginResp> register(@Valid MemberLoginReq req){
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
}
