package com.leoliu.train.controller.admin;


import com.leoliu.train.context.LoginMemberContext;
import com.leoliu.train.req.TrainQueryReq;
import com.leoliu.train.req.TrainSaveReq;
import com.leoliu.train.resp.CommonResp;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.TrainQueryResp;
import com.leoliu.train.service.TrainService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business/admin/train")
public class TrainAdminController {
    @Resource
    private TrainService trainService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody TrainSaveReq req){
        trainService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainQueryResp>> queryList(@Valid TrainQueryReq req){
        PageResp<TrainQueryResp> pageResp = trainService.queryList(req);
        return new CommonResp<>(pageResp);
    }

    @DeleteMapping("delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        trainService.delete(id);
        return new CommonResp<>();
    }

    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryAll(){
        List<TrainQueryResp> queryRespList = trainService.queryAll();
        return new CommonResp<>(queryRespList);
    }
}
