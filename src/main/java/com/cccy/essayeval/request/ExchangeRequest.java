package com.cccy.essayeval.request;

import lombok.Data;

@Data
public class ExchangeRequest {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 卡券唯一标识（雪花算法实现）
     */
    private String couponCode;
}
