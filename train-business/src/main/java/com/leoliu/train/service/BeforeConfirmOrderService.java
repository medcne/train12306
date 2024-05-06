package com.leoliu.train.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.leoliu.train.domain.ConfirmOrder;
import com.leoliu.train.dto.ConfirmOrderMQDto;
import com.leoliu.train.enums.ConfirmOrderStatusEnum;
import com.leoliu.train.enums.RedisKeyPreEnum;
import com.leoliu.train.enums.RocketMQTopicEnum;
import com.leoliu.train.mapper.ConfirmOrderMapper;
import com.leoliu.train.req.ConfirmOrderDoReq;
import com.leoliu.train.context.LoginMemberContext;
import com.leoliu.train.exception.BusinessException;
import com.leoliu.train.exception.BusinessExceptionEnum;
import com.leoliu.train.req.ConfirmOrderTicketReq;
import com.leoliu.train.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BeforeConfirmOrderService {


    @Resource
    private ConfirmOrderMapper confirmOrderMapper;

    @Autowired
    private SkTokenService skTokenService;

    @Resource
    public RocketMQTemplate rocketMQTemplate;

    @SentinelResource(value = "beforeDoConfirm", blockHandler = "beforeDoConfirmBlock")
    public Long beforeDoConfirm(ConfirmOrderDoReq req) {
        req.setMemberId(LoginMemberContext.getId());
        // 校验令牌余量
        boolean validSkToken = skTokenService.validSkToken(req.getDate(), req.getTrainCode(), LoginMemberContext.getId());
        if (validSkToken) {
            log.info("令牌校验通过");
        } else {
            log.info("令牌校验不通过");
            throw new BusinessException(BusinessExceptionEnum.CONFIRM_ORDER_SK_TOKEN_FAIL);
        }

        Date date = req.getDate();
        String trainCode = req.getTrainCode();
        String start = req.getStart();
        String end = req.getEnd();
        List<ConfirmOrderTicketReq> tickets = req.getTickets();

        // 保存确认订单表，状态初始
        DateTime now = DateTime.now();
        ConfirmOrder confirmOrder = new ConfirmOrder();
        confirmOrder.setId(SnowUtil.getSnowflakeNextId());
        confirmOrder.setCreateTime(now);
        confirmOrder.setUpdateTime(now);
        confirmOrder.setMemberId(req.getMemberId());
        confirmOrder.setDate(date);
        confirmOrder.setTrainCode(trainCode);
        confirmOrder.setStart(start);
        confirmOrder.setEnd(end);
        confirmOrder.setDailyTrainTicketId(req.getDailyTrainTicketId());
        confirmOrder.setStatus(ConfirmOrderStatusEnum.INIT.getCode());
        confirmOrder.setTickets(JSON.toJSONString(tickets));
        confirmOrderMapper.insert(confirmOrder);

        // 发送MQ排队购票
//        req.setLogId(MDC.get("LOG_ID"));
//        String reqJson = JSON.toJSONString(req);

        ConfirmOrderMQDto confirmOrderMQDto = new ConfirmOrderMQDto();
        confirmOrderMQDto.setDate(req.getDate());
        confirmOrderMQDto.setTrainCode(req.getTrainCode());
        confirmOrderMQDto.setLogId(MDC.get("LOG_ID"));
        String reqJson = JSON.toJSONString(confirmOrderMQDto);

        log.info("排队购票，发送mq开始，消息：{}", reqJson);
        rocketMQTemplate.convertAndSend(RocketMQTopicEnum.CONFIRM_ORDER.getCode(), reqJson);
        log.info("排队购票，发送mq结束");
        return confirmOrder.getId();
    }

    /**
     * 降级方法，需包含限流方法的所有参数和BlockException参数
     * @param req
     * @param e
     */
    public void beforeDoConfirmBlock(ConfirmOrderDoReq req, BlockException e) {
        log.info("购票请求被限流：{}", req);
        throw new BusinessException(BusinessExceptionEnum.CONFIRM_ORDER_FLOW_EXCEPTION);
    }
}