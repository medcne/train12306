package com.leoliu.train.controller;


import com.leoliu.train.context.LoginMemberContext;
import com.leoliu.train.req.PassengerQueryReq;
import com.leoliu.train.req.PassengerSaveReq;
import com.leoliu.train.resp.CommonResp;
import com.leoliu.train.resp.PassengerResp;
import com.leoliu.train.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req){
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<List<PassengerResp>> queryList(@Valid PassengerQueryReq req){
        req.setMemberId(LoginMemberContext.getId());
        List<PassengerResp> list = passengerService.queryList(req);
        return new CommonResp<>(list);
    }
}
