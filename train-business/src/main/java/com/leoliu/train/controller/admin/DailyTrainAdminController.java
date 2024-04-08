package com.leoliu.train.controller.admin;


import com.leoliu.train.context.LoginMemberContext;
import com.leoliu.train.req.DailyTrainQueryReq;
import com.leoliu.train.req.DailyTrainSaveReq;
import com.leoliu.train.resp.CommonResp;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.DailyTrainQueryResp;
import com.leoliu.train.service.DailyTrainService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business/admin/daily-train")
public class DailyTrainAdminController {
    @Resource
    private DailyTrainService dailyTrainService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainSaveReq req){
        dailyTrainService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainQueryResp>> queryList(@Valid DailyTrainQueryReq req){
        PageResp<DailyTrainQueryResp> pageResp = dailyTrainService.queryList(req);
        return new CommonResp<>(pageResp);
    }

    @DeleteMapping("delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        dailyTrainService.delete(id);
        return new CommonResp<>();
    }
}
