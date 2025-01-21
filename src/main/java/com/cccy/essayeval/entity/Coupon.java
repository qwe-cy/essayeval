package com.cccy.essayeval.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 卡券表
 * </p>
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Data
@Builder
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 卡券ID
     */
    @TableId(value = "coupon_id", type = IdType.AUTO)
    private Long couponId;

    /**
     * 卡券代码，唯一标识卡券的代码
     */
    private String couponCode;

    /**
     * 卡券类型，与卡券类型表相关联
     */
    private Long couponType;

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
    private Long userId;

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


}
