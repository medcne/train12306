package com.leoliu.train.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.leoliu.train.exception.BusinessException;
import com.leoliu.train.exception.BusinessExceptionEnum;
import com.leoliu.train.req.ConfirmOrderDoReq;
import com.leoliu.train.service.ConfirmOrderService;
import com.leoliu.train.resp.CommonResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/business/confirm-order")
public class ConfirmOrderController {

    @Resource
    private ConfirmOrderService confirmOrderService;
    @SentinelResource(value = "businessConfirmOrderDo", blockHandler = "doConfirmBlock")
    @PostMapping("/do")
    public CommonResp<Object> doConfirm(@Valid @RequestBody ConfirmOrderDoReq req) {
        confirmOrderService.doConfirm(req);
        return new CommonResp<>();
    }
    public CommonResp<Object> doConfirmBlock(ConfirmOrderDoReq req, BlockException e){
        log.info("请求被限流：{}","doConfirm");
        CommonResp<Object> resp = new CommonResp<>();
        resp.setMessage(BusinessExceptionEnum.CONFIRM_ORDER_LOCK_FAIL.getDesc());
        return resp;
    }
}
