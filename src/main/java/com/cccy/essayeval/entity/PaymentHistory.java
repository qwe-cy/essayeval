package com.cccy.essayeval.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 付款历史记录表
 * </p>
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Data
@Builder
@TableName("payment_history")
public class PaymentHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 付款ID
     */
    @TableId(value = "payment_id", type = IdType.AUTO)
    private Long paymentId;

    /**
     * 用户ID，与用户表关联
     */
    private Long userId;

    /**
     * 付款日期
     */
    private LocalDateTime paymentDate;

    /**
     * 付款金额
     */
    private BigDecimal paymentAmount;

    /**
     * 付款状态，如成功、失败等
     */
    private String paymentStatus;

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
