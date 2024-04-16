package com.leoliu.train.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoliu.train.domain.Ticket;
import com.leoliu.train.domain.TicketExample;
import com.leoliu.train.mapper.TicketMapper;
import com.leoliu.train.req.TicketQueryReq;
import com.leoliu.train.req.TicketSaveReq;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.TicketQueryResp;
import com.leoliu.train.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TicketService {
    @Resource
    private TicketMapper ticketMapper;

    public void save(TicketSaveReq req) {
        DateTime now = DateTime.now();
        Ticket ticket = BeanUtil.copyProperties(req, Ticket.class);
        if (ObjectUtil.isNull(ticket.getId())) {
            ticket.setId(SnowUtil.getSnowflakeNextId());
            ticket.setCreateTime(now);
            ticket.setUpdateTime(now);
            ticketMapper.insert(ticket);
        } else {
            ticket.setUpdateTime(now);
            ticketMapper.updateByPrimaryKey(ticket);
        }
    }

    public PageResp<TicketQueryResp> queryList(TicketQueryReq req) {
        TicketExample ticketExample = new TicketExample();
        TicketExample.Criteria criteria = ticketExample.createCriteria();

        log.info("查询页码：{}", req.getPage());
        log.info("每页条数：{}", req.getSize());

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ticket> list = ticketMapper.selectByExample(ticketExample);

        PageInfo<Ticket> ticketPageInfo = new PageInfo<>(list);
        log.info("总行数：{}", ticketPageInfo.getTotal());
        log.info("总页数：{}", ticketPageInfo.getPages());

        List<TicketQueryResp> reqlist = BeanUtil.copyToList(list, TicketQueryResp.class);
        PageResp<TicketQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(ticketPageInfo.getTotal());
        pageResp.setList(reqlist);
        return pageResp;
    }


    public void delete(Long id) {
        ticketMapper.deleteByPrimaryKey(id);
    }
}
