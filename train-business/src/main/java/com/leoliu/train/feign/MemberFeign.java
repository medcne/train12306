package com.leoliu.train.feign;

import com.leoliu.train.req.MemberTicketReq;
import com.leoliu.train.resp.CommonResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "train-member")
@FeignClient(value = "train-member",fallback = MemberFeignFallback.class)
public interface MemberFeign {
    @GetMapping("/member/feign/ticket/save")
    CommonResp<Object> save(@RequestBody MemberTicketReq req);
}