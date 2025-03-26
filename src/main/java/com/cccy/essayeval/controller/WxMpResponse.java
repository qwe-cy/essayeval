package com.cccy.essayeval.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cccy.essayeval.common.Result;
import com.cccy.essayeval.entity.CorrectionCount;
import com.cccy.essayeval.entity.User;
import com.cccy.essayeval.service.ICorrectionCountService;
import com.cccy.essayeval.service.IUserService;
import com.cccy.essayeval.utils.SnowflakeIdGenerator;
import com.cccy.essayeval.utils.WxMpServiceUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialFileBatchGetResult;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;


@RestController
public class WxMpResponse {

    @Autowired
    private ICorrectionCountService countService;

    @Autowired
    private IUserService userService;

    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    private WxMpMessageRouter router;

    @PostConstruct
    public void postConstruct() {
        WxMpMessageHandler getOpenIdHandler = new WxMpMessageHandler() {

            /**
             * 处理微信推送消息.
             *
             * @param wxMessage      微信推送消息
             * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
             * @param wxMpService    服务类
             * @param sessionManager session管理器
             * @return xml格式的消息，如果在异步规则里处理的话，可以返回null
             * @throws WxErrorException 异常
             */
            @Override
            public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

                return WxMpXmlOutMessage.TEXT().content("你的唯一标识：" + wxMessage.getFromUser())
                        .toUser(wxMessage.getFromUser())
                        .fromUser(wxMessage.getToUser())
                        .build();
            }
        };
        WxMpMessageHandler payMethodHandler = new WxMpMessageHandler() {

            /**
             * 处理微信推送消息.
             *
             * @param wxMessage      微信推送消息
             * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
             * @param wxMpService    服务类
             * @param sessionManager session管理器
             * @return xml格式的消息，如果在异步规则里处理的话，可以返回null
             * @throws WxErrorException 异常
             */
            @Override
            public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
                return WxMpXmlOutMessage.IMAGE()
                        .mediaId("S3-_hmOnV47TWGYclsD-Ik8n9aticz_XK9yT2-kLeQ6U85zcnfvDsL4vYCVhahm8")
                        .toUser(wxMessage.getFromUser())
                        .fromUser(wxMessage.getToUser())
                        .build();
            }
        };
        WxMpMessageHandler amountHandler = new WxMpMessageHandler() {

            /**
             * 处理微信推送消息.
             *
             * @param wxMessage      微信推送消息
             * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
             * @param wxMpService    服务类
             * @param sessionManager session管理器
             * @return xml格式的消息，如果在异步规则里处理的话，可以返回null
             * @throws WxErrorException 异常
             */
            @Override
            public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
                // 返回用户总额度-已使用额度
                // 通过openId查询用户并且判断用户是否有批改权益“
                User tUser = userService.getOne(new QueryWrapper<User>()
                        .eq("wechat_user_id", wxMessage.getFromUser()));
                if (Objects.isNull(tUser)) {
                    return WxMpXmlOutMessage.TEXT().content("未找到有效用户")
                            .toUser(wxMessage.getFromUser())
                            .fromUser(wxMessage.getToUser())
                            .build();
                }
                CorrectionCount tCorrectionCount = countService.getOne(new QueryWrapper<CorrectionCount>().eq("user_id", tUser.getUserId()));
                int count = tCorrectionCount.getTotalCount() - tCorrectionCount.getUsedCount();
                return WxMpXmlOutMessage.TEXT().content("您当前可用额度：" + count + "次")
                        .toUser(wxMessage.getFromUser())
                        .fromUser(wxMessage.getToUser())
                        .build();
            }
        };

        // 处理公众号关注事件
        /**
         * 1. 确定为用户关注事件
         * 2. 获取是否为新用户
         * 3. 新用户则注册并授予两次批改权益
         * 4. 老用户则回复感谢回来老朋友
         */
        WxMpMessageHandler subscribeHandler = new WxMpMessageHandler() {
            @Override
            public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
                if (org.apache.commons.lang3.StringUtils.equals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT) &&
                        org.apache.commons.lang3.StringUtils.equals(wxMessage.getEvent(), WxConsts.EventType.SUBSCRIBE)) {
                    // 获取是否为新用户
                    String openId = wxMessage.getFromUser();
                    if (!Objects.isNull(userService.getOne(new QueryWrapper<User>()
                            .eq("wechat_user_id", openId)))) {
                        // 老用户则回复感谢回来老朋友
                        return WxMpXmlOutTextMessage.TEXT().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).content("感谢回来老朋友").build();
                    }
                    // 新用户则注册并授予两次批改权益
                    long userId = snowflakeIdGenerator.generateId();
                    userService.save(User.builder().wechatUserId(openId).userId(userId).build());
                    countService.save(CorrectionCount.builder().expirationDate(LocalDate.now().plusMonths(6L)).userId(userId).totalCount(2).usedCount(0).build());
                    return WxMpXmlOutTextMessage.TEXT().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).content("欢迎关注简E作文批改！我们将为您提供两次作文批改的免费权益，您可以立即享用。此外，如果您有更多的作文批改需求，可以通过自助充值获得更多权益，随时满足您的需求。").build();
                }
                return null;
            }
        };

        router = new WxMpMessageRouter(WxMpServiceUtil.getWxMpService());
        router
            .rule()
            .event(WxConsts.EventType.CLICK)
            .msgType(WxConsts.XmlMsgType.EVENT)
            .eventKey("MY_OPENID")
//            .handler(getOpenIdHandler)
            .handler(getOpenIdHandler)
            .async(false)
            .end()
            .rule()
            .event(WxConsts.EventType.CLICK)
            .msgType(WxConsts.XmlMsgType.EVENT)
            .eventKey("REDEEM_VOUCHER")
            .handler(payMethodHandler)
            .async(false)
            .end()
            .rule()
            .async(false)
            .event(WxConsts
            .EventType.SUBSCRIBE)
            .msgType(WxConsts.XmlMsgType.EVENT)
            .handler(subscribeHandler)
            .end()
            .rule()
            .event(WxConsts.EventType.CLICK)
            .msgType(WxConsts.XmlMsgType.EVENT)
            .eventKey("REMAINING_AMOUNT")
            .handler(amountHandler)
            .async(false)
            .end();

    }


    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public void wxMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");

        if (!WxMpServiceUtil.getWxMpService().checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            response.getWriter().println("非法请求");
            return;
        }

        String echostr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            response.getWriter().println(echostr);
            return;
        }
        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
                "raw" :
                request.getParameter("encrypt_type");

        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            WxMpXmlOutMessage outMessage = router.route(inMessage);
            if (outMessage == null) {
                //为null，说明路由配置有问题，需要注意
                response.getWriter().write("");
            }
            if (outMessage != null) {
                response.getWriter().write(outMessage.toXml());
            }
            return;
        }
    }

    @GetMapping("/url")
    public String getUrl(@RequestParam String url){
        return WxMpServiceUtil.getWxMpService()
                .getOAuth2Service()
                .buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE,"state");
    }

    @PostMapping("/uploadMedia")
    public Result upload() throws WxErrorException {
        // 单图文消息的例子
        WxMediaImgUploadResult resSingle = WxMpServiceUtil.getWxMpService().getMaterialService().mediaImgUpload(new File("C:\\Users\\cccy\\Desktop\\Projects\\essayeval\\src\\main\\resources\\test.jpg"));
        // http://mmbiz.qpic.cn/mmbiz_jpg/7Ozu9ib0dZJLhdKc7oic0jia1bLQcmicu2ROlzCNbhElyqiaRuLnOjn7DWhlJ2jjIjPoGEWOUia5wU2P8ZQReibiaibV7hQ/0?from=appmsg
        System.out.println(resSingle);
        return Result.success(resSingle);
    }

    @PostMapping("/getMediaList")
    public Result getMediaList() throws WxErrorException {
        WxMpMaterialFileBatchGetResult resSingle = WxMpServiceUtil.getWxMpService().getMaterialService().materialFileBatchGet(WxConsts.MaterialType.IMAGE, 0, 10);
        System.out.println(resSingle);
        return Result.success(resSingle);
    }
}
