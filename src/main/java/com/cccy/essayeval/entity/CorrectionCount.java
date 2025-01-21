package com.cccy.essayeval.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

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
@Data
@Builder
@TableName("correction_count")
public class CorrectionCount implements Serializable {

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

}
