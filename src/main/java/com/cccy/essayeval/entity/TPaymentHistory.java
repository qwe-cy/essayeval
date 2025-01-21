package com.cccy.essayeval.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_payment_history")
public class TPaymentHistory implements Serializable {

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

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "TPaymentHistory{" +
        "paymentId = " + paymentId +
        ", userId = " + userId +
        ", paymentDate = " + paymentDate +
        ", paymentAmount = " + paymentAmount +
        ", paymentStatus = " + paymentStatus +
        ", createdAt = " + createdAt +
        ", updatedAt = " + updatedAt +
        ", createdBy = " + createdBy +
        ", updatedBy = " + updatedBy +
        "}";
    }
}
