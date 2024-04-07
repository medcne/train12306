package com.leoliu.train.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoliu.train.domain.TrainSeat;
import com.leoliu.train.domain.TrainSeatExample;
import com.leoliu.train.mapper.TrainSeatMapper;
import com.leoliu.train.req.TrainSeatQueryReq;
import com.leoliu.train.req.TrainSeatSaveReq;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.TrainSeatQueryResp;
import com.leoliu.train.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TrainSeatService {
    @Resource
    private TrainSeatMapper trainSeatMapper;

    public void save(TrainSeatSaveReq req) {
        DateTime now = DateTime.now();
        TrainSeat trainSeat = BeanUtil.copyProperties(req, TrainSeat.class);
        if (ObjectUtil.isNull(trainSeat.getId())) {
            trainSeat.setId(SnowUtil.getSnowflakeNextId());
            trainSeat.setCreateTime(now);
            trainSeat.setUpdateTime(now);
            trainSeatMapper.insert(trainSeat);
        } else {
            trainSeat.setUpdateTime(now);
            trainSeatMapper.updateByPrimaryKey(trainSeat);
        }
    }

    public PageResp<TrainSeatQueryResp> queryList(TrainSeatQueryReq req) {
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        TrainSeatExample.Criteria criteria = trainSeatExample.createCriteria();

        log.info("查询页码：{}", req.getPage());
        log.info("每页条数：{}", req.getSize());

        PageHelper.startPage(req.getPage(), req.getSize());
        List<TrainSeat> list = trainSeatMapper.selectByExample(trainSeatExample);

        PageInfo<TrainSeat> trainSeatPageInfo = new PageInfo<>(list);
        log.info("总行数：{}", trainSeatPageInfo.getTotal());
        log.info("总页数：{}", trainSeatPageInfo.getPages());

        List<TrainSeatQueryResp> reqlist = BeanUtil.copyToList(list, TrainSeatQueryResp.class);
        PageResp<TrainSeatQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(trainSeatPageInfo.getTotal());
        pageResp.setList(reqlist);
        return pageResp;
    }


    public void delete(Long id) {
        trainSeatMapper.deleteByPrimaryKey(id);
    }
}
