package com.leoliu.train.controller.admin;


import com.leoliu.train.context.LoginMemberContext;
import com.leoliu.train.req.TicketQueryReq;
import com.leoliu.train.req.TicketSaveReq;
import com.leoliu.train.resp.CommonResp;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.TicketQueryResp;
import com.leoliu.train.service.TicketService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member/admin/ticket")
public class TicketAdminController {

    @Resource
    private TicketService ticketService;

    @GetMapping("/query-list")
    public CommonResp<PageResp<TicketQueryResp>> queryList(@Valid TicketQueryReq req) {
        PageResp<TicketQueryResp> list = ticketService.queryList(req);
        return new CommonResp<>(list);
    }

}
