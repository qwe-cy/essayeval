package com.cccy.essayeval.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 作文批改次数表
 * </p>
 *
 * @author baomidou
 * @since 2025-01-21
 */
@TableName("t_correction_count")
public class TCorrectionCount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;

    /**
     * 用户ID，与用户表关联
     */
    private Long userId;

    /**
     * 总批改次数
     */
    private Integer totalCount;

    /**
     * 已使用批改次数
     */
    private Integer usedCount;

    /**
     * 到期日期，批改次数的有效期
     */
    private LocalDate expirationDate;

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

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
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
        return "TCorrectionCount{" +
        "recordId = " + recordId +
        ", userId = " + userId +
        ", totalCount = " + totalCount +
        ", usedCount = " + usedCount +
        ", expirationDate = " + expirationDate +
        ", createdAt = " + createdAt +
        ", updatedAt = " + updatedAt +
        ", createdBy = " + createdBy +
        ", updatedBy = " + updatedBy +
        "}";
    }
}
