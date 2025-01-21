package com.cccy.essayeval.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_coupon")
public class TCoupon implements Serializable {

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

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Long getCouponType() {
        return couponType;
    }

    public void setCouponType(Long couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return "TCoupon{" +
        "couponId = " + couponId +
        ", couponCode = " + couponCode +
        ", couponType = " + couponType +
        ", couponValue = " + couponValue +
        ", expirationDate = " + expirationDate +
        ", couponStatus = " + couponStatus +
        ", userId = " + userId +
        ", createdAt = " + createdAt +
        ", updatedAt = " + updatedAt +
        ", createdBy = " + createdBy +
        ", updatedBy = " + updatedBy +
        "}";
    }
}
