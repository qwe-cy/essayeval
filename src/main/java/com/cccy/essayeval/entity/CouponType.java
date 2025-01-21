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
 * 卡券类型表
 * </p>
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Data
@Builder
@TableName("coupon_type")
public class CouponType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型ID
     */
    @TableId(value = "type_id", type = IdType.AUTO)
    private Long typeId;

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
