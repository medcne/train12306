package com.leoliu.train.controller;


import com.leoliu.train.req.DailyTrainTicketQueryReq;
import com.leoliu.train.req.DailyTrainTicketSaveReq;
import com.leoliu.train.resp.CommonResp;
import com.leoliu.train.resp.DailyTrainTicketQueryResp;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.service.DailyTrainTicketService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business/daily-train-ticket")
public class DailyTrainTicketController {
    @Resource
    private DailyTrainTicketService dailyTrainTicketService;
    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req){
        PageResp<DailyTrainTicketQueryResp> pageResp = dailyTrainTicketService.queryList(req);
        return new CommonResp<>(pageResp);
    }

}
