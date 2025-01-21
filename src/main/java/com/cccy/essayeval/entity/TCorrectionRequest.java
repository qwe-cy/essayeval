package com.cccy.essayeval.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 作文批改请求表
 * </p>
 *
 * @author baomidou
 * @since 2025-01-21
 */
@TableName("t_correction_request")
public class TCorrectionRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求ID
     */
    @TableId(value = "request_id", type = IdType.AUTO)
    private Long requestId;

    /**
     * 用户ID，与用户表关联
     */
    private Long userId;

    /**
     * 作文内容
     */
    private String essayContent;

    /**
     * 提交时间
     */
    private LocalDateTime submissionTime;

    /**
     * 批改状态，如未批改、已批改
     */
    private String correctionStatus;

    /**
     * 批改结果，批改后的作文内容
     */
    private String correctionResult;

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

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEssayContent() {
        return essayContent;
    }

    public void setEssayContent(String essayContent) {
        this.essayContent = essayContent;
    }

    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(LocalDateTime submissionTime) {
        this.submissionTime = submissionTime;
    }

    public String getCorrectionStatus() {
        return correctionStatus;
    }

    public void setCorrectionStatus(String correctionStatus) {
        this.correctionStatus = correctionStatus;
    }

    public String getCorrectionResult() {
        return correctionResult;
    }

    public void setCorrectionResult(String correctionResult) {
        this.correctionResult = correctionResult;
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
        return "TCorrectionRequest{" +
        "requestId = " + requestId +
        ", userId = " + userId +
        ", essayContent = " + essayContent +
        ", submissionTime = " + submissionTime +
        ", correctionStatus = " + correctionStatus +
        ", correctionResult = " + correctionResult +
        ", createdAt = " + createdAt +
        ", updatedAt = " + updatedAt +
        ", createdBy = " + createdBy +
        ", updatedBy = " + updatedBy +
        "}";
    }
}
