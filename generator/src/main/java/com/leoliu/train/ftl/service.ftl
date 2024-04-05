package com.leoliu.train.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoliu.train.context.LoginMemberContext;
import com.leoliu.train.domain.${Domain};
import com.leoliu.train.domain.${Domain}Example;
import com.leoliu.train.mapper.${Domain}Mapper;
import com.leoliu.train.req.${Domain}QueryReq;
import com.leoliu.train.req.${Domain}SaveReq;
import com.leoliu.train.resp.PageResp;
import com.leoliu.train.resp.${Domain}QueryResp;
import com.leoliu.train.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ${Domain}Service {
    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    public void save(${Domain}SaveReq req) {
        DateTime now = DateTime.now();
        ${Domain} ${domain} = BeanUtil.copyProperties(req, ${Domain}.class);
        if (ObjectUtil.isNull(${domain}.getId())) {
            ${domain}.setId(SnowUtil.getSnowflakeNextId());
            ${domain}.setMemberId(LoginMemberContext.getMember().getId());
            ${domain}.setCreateTime(now);
            ${domain}.setUpdateTime(now);
            ${domain}Mapper.insert(${domain});
        } else {
            ${domain}.setUpdateTime(now);
            ${domain}Mapper.updateByPrimaryKey(${domain});
        }
    }

    public PageResp<${Domain}QueryResp> queryList(${Domain}QueryReq req) {
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        ${Domain}Example.Criteria criteria = ${domain}Example.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        log.info("查询页码：{}", req.getPage());
        log.info("每页条数：{}", req.getSize());

        PageHelper.startPage(req.getPage(), req.getSize());
        List<${Domain}> list = ${domain}Mapper.selectByExample(${domain}Example);

        PageInfo<${Domain}> ${domain}PageInfo = new PageInfo<>(list);
        log.info("总行数：{}", ${domain}PageInfo.getTotal());
        log.info("总页数：{}", ${domain}PageInfo.getPages());

        List<${Domain}QueryResp> reqlist = BeanUtil.copyToList(list, ${Domain}QueryResp.class);
        PageResp<${Domain}QueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(${domain}PageInfo.getTotal());
        pageResp.setList(reqlist);
        return pageResp;
    }


    public void delete(Long id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }
}
