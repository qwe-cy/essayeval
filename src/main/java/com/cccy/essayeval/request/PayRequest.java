package com.cccy.essayeval.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付请求
 */

@Data
public class PayRequest {
    //用户微信id
    @JsonProperty("wechat_id")
    private String wechatId;

    @JsonProperty("payment_date")
    private Date paymentDate;

    @JsonProperty("payment_amount")
    private BigDecimal paymentAmount;

    @JsonProperty("payment_status")
    private String paymentStatus;
}
