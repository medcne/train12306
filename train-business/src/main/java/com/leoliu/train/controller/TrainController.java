package com.leoliu.train.controller;

import com.leoliu.train.req.TrainQueryReq;
import com.leoliu.train.req.TrainSaveReq;
import com.leoliu.train.resp.CommonResp;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.TrainQueryResp;
import com.leoliu.train.service.TrainSeatService;
import com.leoliu.train.service.TrainService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("business/train")
public class TrainController {

    @Resource
    private TrainService trainService;
    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryList() {
        List<TrainQueryResp> list = trainService.queryAll();
        return new CommonResp<>(list);
    }

}