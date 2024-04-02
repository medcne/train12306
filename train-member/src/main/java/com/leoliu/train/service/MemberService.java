package com.leoliu.train.service;

import cn.hutool.core.collection.CollUtil;
import com.leoliu.train.domain.Member;
import com.leoliu.train.domain.MemberExample;
import com.leoliu.train.mapper.MemberMapper;
import com.leoliu.train.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;
    public int count(){
        return (int) memberMapper.countByExample(null);
    }
    public long register(MemberRegisterReq req){
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(CollUtil.isNotEmpty(members)){
            throw new RuntimeException("手机号已经注册！");
        }
        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
