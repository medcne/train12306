package com.leoliu.train.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoliu.train.domain.DailyTrain;
import com.leoliu.train.domain.DailyTrainExample;
import com.leoliu.train.mapper.DailyTrainMapper;
import com.leoliu.train.req.DailyTrainQueryReq;
import com.leoliu.train.req.DailyTrainSaveReq;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.DailyTrainQueryResp;
import com.leoliu.train.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DailyTrainService {
    @Resource
    private DailyTrainMapper dailyTrainMapper;

    public void save(DailyTrainSaveReq req) {
        DateTime now = DateTime.now();
        DailyTrain dailyTrain = BeanUtil.copyProperties(req, DailyTrain.class);
        if (ObjectUtil.isNull(dailyTrain.getId())) {
            dailyTrain.setId(SnowUtil.getSnowflakeNextId());
            dailyTrain.setCreateTime(now);
            dailyTrain.setUpdateTime(now);
            dailyTrainMapper.insert(dailyTrain);
        } else {
            dailyTrain.setUpdateTime(now);
            dailyTrainMapper.updateByPrimaryKey(dailyTrain);
        }
    }

    public PageResp<DailyTrainQueryResp> queryList(DailyTrainQueryReq req) {
        DailyTrainExample dailyTrainExample = new DailyTrainExample();
        DailyTrainExample.Criteria criteria = dailyTrainExample.createCriteria();

        log.info("查询页码：{}", req.getPage());
        log.info("每页条数：{}", req.getSize());

        PageHelper.startPage(req.getPage(), req.getSize());
        List<DailyTrain> list = dailyTrainMapper.selectByExample(dailyTrainExample);

        PageInfo<DailyTrain> dailyTrainPageInfo = new PageInfo<>(list);
        log.info("总行数：{}", dailyTrainPageInfo.getTotal());
        log.info("总页数：{}", dailyTrainPageInfo.getPages());

        List<DailyTrainQueryResp> reqlist = BeanUtil.copyToList(list, DailyTrainQueryResp.class);
        PageResp<DailyTrainQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(dailyTrainPageInfo.getTotal());
        pageResp.setList(reqlist);
        return pageResp;
    }


    public void delete(Long id) {
        dailyTrainMapper.deleteByPrimaryKey(id);
    }
}
