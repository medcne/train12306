package com.leoliu.train.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoliu.train.domain.TrainCarriage;
import com.leoliu.train.domain.TrainCarriageExample;
import com.leoliu.train.mapper.TrainCarriageMapper;
import com.leoliu.train.req.TrainCarriageQueryReq;
import com.leoliu.train.req.TrainCarriageSaveReq;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.TrainCarriageQueryResp;
import com.leoliu.train.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TrainCarriageService {
    @Resource
    private TrainCarriageMapper trainCarriageMapper;

    public void save(TrainCarriageSaveReq req) {
        DateTime now = DateTime.now();
        TrainCarriage trainCarriage = BeanUtil.copyProperties(req, TrainCarriage.class);
        if (ObjectUtil.isNull(trainCarriage.getId())) {
            trainCarriage.setId(SnowUtil.getSnowflakeNextId());
            trainCarriage.setCreateTime(now);
            trainCarriage.setUpdateTime(now);
            trainCarriageMapper.insert(trainCarriage);
        } else {
            trainCarriage.setUpdateTime(now);
            trainCarriageMapper.updateByPrimaryKey(trainCarriage);
        }
    }

    public PageResp<TrainCarriageQueryResp> queryList(TrainCarriageQueryReq req) {
        TrainCarriageExample trainCarriageExample = new TrainCarriageExample();
        TrainCarriageExample.Criteria criteria = trainCarriageExample.createCriteria();

        log.info("查询页码：{}", req.getPage());
        log.info("每页条数：{}", req.getSize());

        PageHelper.startPage(req.getPage(), req.getSize());
        List<TrainCarriage> list = trainCarriageMapper.selectByExample(trainCarriageExample);

        PageInfo<TrainCarriage> trainCarriagePageInfo = new PageInfo<>(list);
        log.info("总行数：{}", trainCarriagePageInfo.getTotal());
        log.info("总页数：{}", trainCarriagePageInfo.getPages());

        List<TrainCarriageQueryResp> reqlist = BeanUtil.copyToList(list, TrainCarriageQueryResp.class);
        PageResp<TrainCarriageQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(trainCarriagePageInfo.getTotal());
        pageResp.setList(reqlist);
        return pageResp;
    }


    public void delete(Long id) {
        trainCarriageMapper.deleteByPrimaryKey(id);
    }
}
