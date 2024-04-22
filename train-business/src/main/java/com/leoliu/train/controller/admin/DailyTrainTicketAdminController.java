package com.leoliu.train.controller.admin;


import com.leoliu.train.context.LoginMemberContext;
import com.leoliu.train.req.DailyTrainTicketQueryReq;
import com.leoliu.train.req.DailyTrainTicketSaveReq;
import com.leoliu.train.resp.CommonResp;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.DailyTrainTicketQueryResp;
import com.leoliu.train.service.DailyTrainTicketService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business/admin/daily-train-ticket")
public class DailyTrainTicketAdminController {
    @Resource
    private DailyTrainTicketService dailyTrainTicketService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainTicketSaveReq req){
        dailyTrainTicketService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req){
        PageResp<DailyTrainTicketQueryResp> pageResp = dailyTrainTicketService.queryList(req);
        return new CommonResp<>(pageResp);
    }
    @GetMapping("/query-list2")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList2(@Valid DailyTrainTicketQueryReq req) {
        PageResp<DailyTrainTicketQueryResp> list = dailyTrainTicketService.queryList2(req);
        return new CommonResp<>(list);
    }
    @DeleteMapping("delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        dailyTrainTicketService.delete(id);
        return new CommonResp<>();
    }
}
