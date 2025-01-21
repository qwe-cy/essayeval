package com.cccy.essayeval.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

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
@Data
@Builder
@TableName("correction_request")
public class CorrectionRequest implements Serializable {

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


}
