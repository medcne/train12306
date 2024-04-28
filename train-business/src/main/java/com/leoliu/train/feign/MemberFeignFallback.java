package com.leoliu.train.feign;

import com.leoliu.train.req.MemberTicketReq;
import com.leoliu.train.resp.CommonResp;
import org.springframework.stereotype.Component;

@Component
public class MemberFeignFallback implements MemberFeign{

    @Override
    public CommonResp<Object> save(MemberTicketReq req) {
        return new CommonResp<>("fallback");
    }
}
