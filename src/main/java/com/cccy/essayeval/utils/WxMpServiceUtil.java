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
        config.setAppId("appid"); // 设置微信公众号的appid
        config.setSecret("app corpSecret"); // 设置微信公众号的app corpSecret
        config.setToken("cccy"); // 设置微信公众号的token

        wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
    }

}
