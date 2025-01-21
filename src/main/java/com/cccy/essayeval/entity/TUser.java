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
 * 用户表
 * </p>
 */
@TableName("t_user")
@Builder
@Data
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private String userId;

    /**
     * 微信用户唯一标识
     */
    private String wechatUserId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 付款信息，如信用卡信息
     */
    private String paymentInfo;

    /**
     * 微信昵称
     */
    private String wechatNickname;

    /**
     * 微信头像图片URL
     */
    private String wechatAvatar;

    /**
     * 微信性别信息（男、女、未知）
     */
    private String wechatGender;

    /**
     * 微信城市信息
     */
    private String wechatCity;

    /**
     * 微信省份信息
     */
    private String wechatProvince;

    /**
     * 微信国家信息
     */
    private String wechatCountry;

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


    @Override
    public String toString() {
        return "TUser{" +
                "userId = " + userId +
                ", wechatUserId = " + wechatUserId +
                ", username = " + username +
                ", email = " + email +
                ", password = " + password +
                ", paymentInfo = " + paymentInfo +
                ", wechatNickname = " + wechatNickname +
                ", wechatAvatar = " + wechatAvatar +
                ", wechatGender = " + wechatGender +
                ", wechatCity = " + wechatCity +
                ", wechatProvince = " + wechatProvince +
                ", wechatCountry = " + wechatCountry +
                ", createdAt = " + createdAt +
                ", updatedAt = " + updatedAt +
                ", createdBy = " + createdBy +
                ", updatedBy = " + updatedBy +
                "}";
    }
}
