package com.cccy.essayeval.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_coupon_type")
public class TCouponType implements Serializable {

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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getTypeProperties() {
        return typeProperties;
    }

    public void setTypeProperties(String typeProperties) {
        this.typeProperties = typeProperties;
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
        return "TCouponType{" +
        "typeId = " + typeId +
        ", typeName = " + typeName +
        ", typeDescription = " + typeDescription +
        ", typeProperties = " + typeProperties +
        ", createdAt = " + createdAt +
        ", updatedAt = " + updatedAt +
        ", createdBy = " + createdBy +
        ", updatedBy = " + updatedBy +
        "}";
    }
}
