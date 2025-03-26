package com.cccy.essayeval.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.cccy.essayeval.common.Result;
import com.cccy.essayeval.entity.CorrectionCount;
import com.cccy.essayeval.entity.Coupon;
import com.cccy.essayeval.entity.CouponType;
import com.cccy.essayeval.entity.User;
import com.cccy.essayeval.request.ExchangeRequest;
import com.cccy.essayeval.response.CouponResp;
import com.cccy.essayeval.service.ICorrectionCountService;
import com.cccy.essayeval.service.ICouponService;
import com.cccy.essayeval.service.ICouponTypeService;
import com.cccy.essayeval.service.IUserService;
import com.cccy.essayeval.utils.WxMpServiceUtil;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 卡券表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2025-01-21
 */
@RestController
@RequestMapping("/api")
public class CouponController {

    @Autowired
    private ICouponService couponService;
    @Autowired
    private ICouponTypeService couponTypeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICorrectionCountService countService;

    /**
     * 用户查看卡券
     * @param code
     * @return
     * @throws WxErrorException
     */
    @GetMapping("/coupon")
    public Result myCoupon(@RequestParam String code) throws WxErrorException {
        if (StringUtils.isEmpty(code)) {
            return Result.error("请与微信公众号查看批改记录");
        }
        WxOAuth2AccessToken wxOAuth2AccessToken = WxMpServiceUtil.getWxMpService().getOAuth2Service().getAccessToken(code);
        String openId = wxOAuth2AccessToken.getOpenId();
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("wechat_user_id", openId));
        List<Coupon> tCoupons = couponService.list(new QueryWrapper<>(Coupon.class).eq("user_id", user.getUserId()));

        List<CouponResp> couponResps = new ArrayList<>();
        tCoupons.forEach(tCoupon -> {
            CouponType tCouponType = couponTypeService.getOne(new QueryWrapper<>(CouponType.class).eq("type_id", tCoupon.getCouponType()));
            CouponResp couponResp = CouponResp.builder().couponId(String.valueOf(tCoupon.getCouponId()))
                    .couponCode(tCoupon.getCouponCode())
                    .couponStatus(tCoupon.getCouponStatus())
                    .couponValue(tCoupon.getCouponValue())
                    .expirationDate(tCoupon.getExpirationDate())
                    .userId(String.valueOf(tCoupon.getUserId()))
                    .typeName(tCouponType.getTypeName())
                    .typeId(String.valueOf(tCouponType.getTypeId()))
                    .typeDescription(tCouponType.getTypeDescription())
                    .build();
            couponResps.add(couponResp);
        });
        return Result.success(couponResps);
    }

    /**
     * 用户兑换卡券
     * @return
     */
    @PostMapping("/exchange")
    public Result exchange(@RequestBody ExchangeRequest request){
        Long userId = request.getUserId();
        String couponCode = request.getCouponCode();
        if(userId == null || couponCode.isEmpty()){
            return Result.error("参数不完整，无法兑换");
        }
        // 1、检验卡券是否存在
        Coupon coupon = couponService.getOne(new QueryWrapper<Coupon>()
                .eq("user_id", userId)
                .eq("coupon_code", couponCode)
                .eq("coupon_status", "UNUSED"));
        if(Objects.isNull(coupon)){
            Result.error("卡券不存在，无法兑换");
        }

        // 2、将卡券状态更新为已兑换
        coupon.setCouponStatus("USED");
        couponService.updateById(coupon);

        // 3、修改用户权益，添加20次批改次数
        CorrectionCount correctionCount = countService.getOne(new QueryWrapper<CorrectionCount>().eq("user_id", userId));
        if(Objects.isNull(correctionCount)){
            correctionCount = new CorrectionCount();
            correctionCount.setUserId(userId);
        }
        correctionCount.setTotalCount(correctionCount.getTotalCount() + 20);
        countService.saveOrUpdate(correctionCount);

        return Result.success("兑换成功");
    }


}
