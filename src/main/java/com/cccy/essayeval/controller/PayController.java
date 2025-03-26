package com.cccy.essayeval.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cccy.essayeval.common.Result;
import com.cccy.essayeval.entity.Coupon;
import com.cccy.essayeval.entity.PaymentHistory;
import com.cccy.essayeval.entity.User;
import com.cccy.essayeval.request.PayRequest;
import com.cccy.essayeval.service.ICouponService;
import com.cccy.essayeval.service.IPaymentHistoryService;
import com.cccy.essayeval.service.IUserService;
import com.cccy.essayeval.utils.SnowflakeIdGenerator;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin")
public class PayController {

    private static final String topic = "PAY_FINISHED";

    @Autowired
    private IUserService userService;
    @Autowired
    private ICouponService couponService;
    @Autowired
    private IPaymentHistoryService paymentHistoryService;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @PostMapping("/pay")
    public Result pay(@RequestBody PayRequest payRequest){

        if(Objects.isNull(payRequest) || payRequest.getWechatId().isEmpty() || payRequest.getPaymentAmount().compareTo(BigDecimal.valueOf(9.9)) != 0){
            return Result.error("参数错误");
        }

        // 1、根据用户微信 id 获取用户 id
        User user = userService.getOne(new QueryWrapper<>(User.class).eq("wechat_user_id", payRequest.getWechatId()));
        if(user == null){
            return Result.error("未查询到有效用户");
        }

        // 2、添加支付记录
        PaymentHistory paymentHistory = PaymentHistory.builder()
                .userId(user.getUserId())
                .paymentAmount(payRequest.getPaymentAmount())
                .paymentStatus(payRequest.getPaymentStatus())
                .build();
        paymentHistoryService.save(paymentHistory);

        // 3、发放待兑换卡券(RocketMQ解耦版本)
        Message<Long> message = MessageBuilder.withPayload(user.getUserId()).build();
        SendResult sendResult = rocketMQTemplate.syncSend(topic, message);
        System.out.println(sendResult);

//        Coupon coupon = Coupon.builder()
//                .userId(user.getUserId())
//                .couponType(1334L)
//                // 雪花算法，实现分布式 id 唯一
//                .couponCode("CO" + snowflakeIdGenerator.generateId())
//                .couponValue(new BigDecimal(20))
//                .couponStatus("UNUSED")
//                .expirationDate(LocalDate.now().plusMonths(6L))
//                .build();
//        couponService.save(coupon);

        return Result.success("success");
    }
}
