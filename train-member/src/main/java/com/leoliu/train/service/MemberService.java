package com.leoliu.train.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.leoliu.train.domain.Member;
import com.leoliu.train.domain.MemberExample;
import com.leoliu.train.exception.BusinessException;
import com.leoliu.train.exception.BusinessExceptionEnum;
import com.leoliu.train.mapper.MemberMapper;
import com.leoliu.train.req.MemberLoginReq;
import com.leoliu.train.req.MemberRegisterReq;
import com.leoliu.train.req.MemberSendCodeReq;
import com.leoliu.train.resp.MemberLoginResp;
import com.leoliu.train.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(members)){
            return members.get(0);
        }else {
            return null;
        }

    }
    public int count(){
        return (int) memberMapper.countByExample(null);
    }
    public long register(MemberRegisterReq req){
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);
        if(ObjectUtil.isNotNull(memberDB)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }


    public void sendCode(MemberSendCodeReq req){
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);
        //如果手机号不存在则插入记录
        if(ObjectUtil.isNull(memberDB)){
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        }
        String s = RandomUtil.randomString(4);
        //保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        //对接短信通道，发送短信
        //对接短信通道，发送短信
    }
    public MemberLoginResp login(MemberLoginReq req){
        String code = req.getCode();
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);

        //如果手机号不存在则抛出异常
        if(ObjectUtil.isNull(memberDB)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }
        if(!"8888".equals(code)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }
        return BeanUtil.copyProperties(memberDB,MemberLoginResp.class);
    }


}
