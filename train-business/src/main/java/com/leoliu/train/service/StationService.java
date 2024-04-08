package com.leoliu.train.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoliu.train.domain.Station;
import com.leoliu.train.domain.StationExample;
import com.leoliu.train.exception.BusinessException;
import com.leoliu.train.exception.BusinessExceptionEnum;
import com.leoliu.train.mapper.StationMapper;
import com.leoliu.train.req.StationQueryReq;
import com.leoliu.train.req.StationSaveReq;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.StationQueryResp;
import com.leoliu.train.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StationService {
    @Resource
    private StationMapper stationMapper;

    public void save(StationSaveReq req) {
        DateTime now = DateTime.now();
        Station station = BeanUtil.copyProperties(req, Station.class);
        if (ObjectUtil.isNull(station.getId())) {

            // 保存之前，先校验唯一键是否存在
            Station stationDB = selectByUnique(req.getName());
            if (ObjectUtil.isNotEmpty(stationDB)) {
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_STATION_NAME_UNIQUE_ERROR);
            }

            station.setId(SnowUtil.getSnowflakeNextId());
            station.setCreateTime(now);
            station.setUpdateTime(now);
            stationMapper.insert(station);
        } else {
            station.setUpdateTime(now);
            stationMapper.updateByPrimaryKey(station);
        }
    }

    private Station selectByUnique(String name) {
        StationExample stationExample = new StationExample();
        stationExample.createCriteria().andNameEqualTo(name);
        List<Station> list = stationMapper.selectByExample(stationExample);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public PageResp<StationQueryResp> queryList(StationQueryReq req) {
        StationExample stationExample = new StationExample();
        StationExample.Criteria criteria = stationExample.createCriteria();

        log.info("查询页码：{}", req.getPage());
        log.info("每页条数：{}", req.getSize());

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Station> list = stationMapper.selectByExample(stationExample);

        PageInfo<Station> stationPageInfo = new PageInfo<>(list);
        log.info("总行数：{}", stationPageInfo.getTotal());
        log.info("总页数：{}", stationPageInfo.getPages());

        List<StationQueryResp> reqlist = BeanUtil.copyToList(list, StationQueryResp.class);
        PageResp<StationQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(stationPageInfo.getTotal());
        pageResp.setList(reqlist);
        return pageResp;
    }

    public List<StationQueryResp> queryAll() {
        StationExample stationExample = new StationExample();
        stationExample.setOrderByClause("name_pinyin asc");
        List<Station> stationList = stationMapper.selectByExample(stationExample);
        return BeanUtil.copyToList(stationList, StationQueryResp.class);
    }


    public void delete(Long id) {
        stationMapper.deleteByPrimaryKey(id);
    }
}
