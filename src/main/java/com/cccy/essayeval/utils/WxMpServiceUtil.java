package com.cccy.essayeval.utils;

import lombok.Getter;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;


public class WxMpServiceUtil {

    @Getter
    private static final WxMpDefaultConfigImpl config;
    @Getter
    private static final WxMpService wxMpService;
    private static WxMpMessageRouter wxMpMessageRouter;

    static {
        config = new WxMpDefaultConfigImpl();
        config.setAppId("wxc7e1d043c81ab610"); // 设置微信公众号的appid
        config.setSecret("9bc8e779c415200418567244aa3cbbab"); // 设置微信公众号的app corpSecret
        config.setToken("cccy"); // 设置微信公众号的token

        wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
    }

}
