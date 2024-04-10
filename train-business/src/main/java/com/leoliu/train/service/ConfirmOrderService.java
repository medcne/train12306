package com.leoliu.train.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoliu.train.domain.ConfirmOrder;
import com.leoliu.train.domain.ConfirmOrderExample;
import com.leoliu.train.mapper.ConfirmOrderMapper;
import com.leoliu.train.req.ConfirmOrderQueryReq;
import com.leoliu.train.req.ConfirmOrderSaveReq;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.ConfirmOrderQueryResp;
import com.leoliu.train.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConfirmOrderService {
    @Resource
    private ConfirmOrderMapper confirmOrderMapper;

    public void save(ConfirmOrderSaveReq req) {
        DateTime now = DateTime.now();
        ConfirmOrder confirmOrder = BeanUtil.copyProperties(req, ConfirmOrder.class);
        if (ObjectUtil.isNull(confirmOrder.getId())) {
            confirmOrder.setId(SnowUtil.getSnowflakeNextId());
            confirmOrder.setCreateTime(now);
            confirmOrder.setUpdateTime(now);
            confirmOrderMapper.insert(confirmOrder);
        } else {
            confirmOrder.setUpdateTime(now);
            confirmOrderMapper.updateByPrimaryKey(confirmOrder);
        }
    }

    public PageResp<ConfirmOrderQueryResp> queryList(ConfirmOrderQueryReq req) {
        ConfirmOrderExample confirmOrderExample = new ConfirmOrderExample();
        ConfirmOrderExample.Criteria criteria = confirmOrderExample.createCriteria();

        log.info("查询页码：{}", req.getPage());
        log.info("每页条数：{}", req.getSize());

        PageHelper.startPage(req.getPage(), req.getSize());
        List<ConfirmOrder> list = confirmOrderMapper.selectByExample(confirmOrderExample);

        PageInfo<ConfirmOrder> confirmOrderPageInfo = new PageInfo<>(list);
        log.info("总行数：{}", confirmOrderPageInfo.getTotal());
        log.info("总页数：{}", confirmOrderPageInfo.getPages());

        List<ConfirmOrderQueryResp> reqlist = BeanUtil.copyToList(list, ConfirmOrderQueryResp.class);
        PageResp<ConfirmOrderQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(confirmOrderPageInfo.getTotal());
        pageResp.setList(reqlist);
        return pageResp;
    }


    public void delete(Long id) {
        confirmOrderMapper.deleteByPrimaryKey(id);
    }
}
