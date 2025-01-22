package com.cccy.essayeval.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 响应对象 包含了coupon表和coupon_type表的相关字段
 */

@Data
@Builder
public class CouponResp {

    /**
     * 卡券ID
     */
    private String couponId;

    /**
     * 卡券代码，唯一标识卡券的代码
     */
    private String couponCode;

    /**
     * 卡券类型，与卡券类型表相关联
     */
    private String couponType;

    /**
     * 卡券面值，可能是折扣金额或批改次数
     */
    private BigDecimal couponValue;

    /**
     * 卡券有效期
     */
    private LocalDate expirationDate;

    /**
     * 卡券状态，如未使用、已使用、过期
     */
    private String couponStatus;

    /**
     * 用户ID，与用户表关联
     */
    private String userId;

    /**
     * 数据创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime updatedAt;

    /**
     * 创建人ID
     */
    private Long createdBy;

    /**
     * 最后修改人ID
     */
    private Long updatedBy;

    /**
     * 类型ID
     */
    private String typeId;

    /**
     * 类型名称，描述卡券类型
     */
    private String typeName;

    /**
     * 类型描述，详细说明卡券类型的信息
     */
    private String typeDescription;

    /**
     * 类型属性，可包括卡券类型的相关属性
     */
    private String typeProperties;

}