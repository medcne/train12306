package com.leoliu.train.controller;


import com.leoliu.train.resp.CommonResp;
import com.leoliu.train.resp.StationQueryResp;
import com.leoliu.train.service.StationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("business/station")
public class StationController {

    @Resource
    private StationService stationService;

    @GetMapping("/query-all")
    public CommonResp<List<StationQueryResp>> queryList() {
        List<StationQueryResp> list = stationService.queryAll();
        return new CommonResp<>(list);
    }
}