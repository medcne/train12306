package com.leoliu.train.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoliu.train.domain.TrainStation;
import com.leoliu.train.domain.TrainStationExample;
import com.leoliu.train.mapper.TrainStationMapper;
import com.leoliu.train.req.TrainStationQueryReq;
import com.leoliu.train.req.TrainStationSaveReq;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.TrainStationQueryResp;
import com.leoliu.train.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TrainStationService {
    @Resource
    private TrainStationMapper trainStationMapper;

    public void save(TrainStationSaveReq req) {
        DateTime now = DateTime.now();
        TrainStation trainStation = BeanUtil.copyProperties(req, TrainStation.class);
        if (ObjectUtil.isNull(trainStation.getId())) {
            trainStation.setId(SnowUtil.getSnowflakeNextId());
            trainStation.setCreateTime(now);
            trainStation.setUpdateTime(now);
            trainStationMapper.insert(trainStation);
        } else {
            trainStation.setUpdateTime(now);
            trainStationMapper.updateByPrimaryKey(trainStation);
        }
    }

    public PageResp<TrainStationQueryResp> queryList(TrainStationQueryReq req) {
        TrainStationExample trainStationExample = new TrainStationExample();
        TrainStationExample.Criteria criteria = trainStationExample.createCriteria();

        log.info("查询页码：{}", req.getPage());
        log.info("每页条数：{}", req.getSize());

        PageHelper.startPage(req.getPage(), req.getSize());
        List<TrainStation> list = trainStationMapper.selectByExample(trainStationExample);

        PageInfo<TrainStation> trainStationPageInfo = new PageInfo<>(list);
        log.info("总行数：{}", trainStationPageInfo.getTotal());
        log.info("总页数：{}", trainStationPageInfo.getPages());

        List<TrainStationQueryResp> reqlist = BeanUtil.copyToList(list, TrainStationQueryResp.class);
        PageResp<TrainStationQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(trainStationPageInfo.getTotal());
        pageResp.setList(reqlist);
        return pageResp;
    }


    public void delete(Long id) {
        trainStationMapper.deleteByPrimaryKey(id);
    }
}
