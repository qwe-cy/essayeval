package com.cccy.essayeval.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cccy.essayeval.common.Result;
import com.cccy.essayeval.entity.CorrectionCount;
import com.cccy.essayeval.entity.CorrectionRequest;
import com.cccy.essayeval.entity.User;
import com.cccy.essayeval.request.EccSubmitRequest;
import com.cccy.essayeval.request.EvalRecordRequest;
import com.cccy.essayeval.service.ICorrectionCountService;
import com.cccy.essayeval.service.ICorrectionRequestService;
import com.cccy.essayeval.service.IUserService;
import com.cccy.essayeval.utils.RedisUtil;
import com.cccy.essayeval.utils.WxMpServiceUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ecc.v20181213.EccClient;
import com.tencentcloudapi.ecc.v20181213.models.ECCRequest;
import com.tencentcloudapi.ecc.v20181213.models.ECCResponse;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/ecc")
@Slf4j
public class EccController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ICorrectionCountService countService;
    @Autowired
    private ICorrectionRequestService correctionRequestService;
    @Autowired
    private JedisPool jedisPool;

    /**
     * 英文作文提交
     * @param request 标题、内容、级别
     * @return result
     * @throws TencentCloudSDKException
     */
    @PostMapping("/submit")
    @Transactional
    public Result submit(@RequestBody EccSubmitRequest request) throws TencentCloudSDKException, WxErrorException {
        if(Objects.isNull(request) || StringUtils.isEmpty(request.getContent())){
            return Result.error("参数非法，请检查输入项是否为空！");
        }

//        // 开了 redis 再去掉注释
//        boolean getLock = RedisUtil.tryGetDistributedLock(jedisPool.getResource(), "user_code_" + request.getCode(), "1", 5);
//        if(!getLock){
//            return Result.error("请勿频繁提交");
//        }

        // 通过 code 获取用户openid，即wechat_user_id
        WxOAuth2AccessToken wxOAuth2AccessToken = WxMpServiceUtil.getWxMpService().getOAuth2Service().getAccessToken(request.getCode());
        String openId = wxOAuth2AccessToken.getOpenId();
        // 通过openId查询用户并且判断用户是否有批改权益
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("wechat_user_id", openId));
        if(Objects.isNull(user)){
            return Result.error("未查询到有效用户");
        }
        CorrectionCount correctionCount = countService.getOne(new QueryWrapper<CorrectionCount>().eq("user_id", user.getUserId()));
        if(correctionCount.getTotalCount() - correctionCount.getUsedCount() <= 0){
            return Result.error("您暂无批改次数，请先通过自助充值进行购买");
        }

        // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
        Credential cred = new Credential("AKIDMoBOTSTPM9kDar9UhkR1O5d2FG9wa5Rf", "86B9MVaZP3a0IcxDLNSgolYBcsvnT3Qa");
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("ecc.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        EccClient client = new EccClient(cred, "", clientProfile);
        // 实例化一个请求对象,每个接口都会对应一个request对象
        ECCRequest req = new ECCRequest();
        req.setTitle(request.getTitle());
        req.setContent(request.getContent());
        req.setGrade(request.getGrade());
        // 返回的resp是一个ECCResponse的实例，与请求对象对应
//        ECCResponse resp = client.ECC(req);
//        String data = ECCResponse.toJsonString(resp);
        log.info("作文标题为: {}，开始批改...",request.getTitle());
        String data ="{\"Data\":{\"Score\":65.62,\"ScoreCat\":{\"Words\":{\"Name\":\"词汇\",\"Score\":59.41,\"Percentage\":42.0},\"Sentences\":{\"Name\":\"句子\",\"Score\":91.23,\"Percentage\":28.0},\"Structure\":{\"Name\":\"篇章结构\",\"Score\":38.46,\"Percentage\":23.0},\"Content\":{\"Name\":\"内容\",\"Score\":89.64,\"Percentage\":7.0},\"Score\":0.0,\"Percentage\":0.0},\"Comment\":\"作文词汇表达匮乏，请注意词汇积累；句式变化多样，句法方面做的很棒；文章衔接不够流畅；内容紧凑，主旨清晰。请多加练习，更上一层楼。\",\"SentenceComments\":[{\"Suggestions\":[{\"Type\":\"Error\",\"ErrorType\":\"动词时态建议\",\"Origin\":\"\",\"Replace\":\"have\",\"Message\":\"请检查是否有动词时态错误，建议新增have。\",\"ErrorPosition\":[15,14],\"ErrorCoordinates\":[]}],\"Sentence\":{\"Sentence\":\"My Background as a Java Programmer: Having worked at a prestigious internet company, I gained invaluable experience in Java programming.\",\"ParaID\":1,\"SentenceID\":4}},{\"Suggestions\":[{\"Type\":\"Error\",\"ErrorType\":\"介词建议\",\"Origin\":\"in\",\"Replace\":\"of\",\"Message\":\"请检查in，可能存在介词错误，建议替换为of。\",\"ErrorPosition\":[4,4],\"ErrorCoordinates\":[]}],\"Sentence\":{\"Sentence\":\"My skills and knowledge in Java were honed through real-world challenges and collaborations with brilliant minds.\",\"ParaID\":1,\"SentenceID\":6}},{\"Suggestions\":[{\"Type\":\"Error\",\"ErrorType\":\"其他建议\",\"Origin\":\"ever-growing\",\"Replace\":\"ever growing\",\"Message\":\"请检查ever-growing，可能存在其他错误，建议替换为ever growing。\",\"ErrorPosition\":[16,16],\"ErrorCoordinates\":[]}],\"Sentence\":{\"Sentence\":\"The world of Java programming is vast, and the demand for skilled Java developers is ever-growing.\",\"ParaID\":1,\"SentenceID\":20}}]},\"TaskId\":\"\",\"RequestId\":\"6d5c95af-5695-4c5e-9845-df0bebeb436c\"}";

        // 增加批改记录
//        CorrectionRequest correctionRequest = CorrectionRequest.builder().userId(user.getUserId())
//                .essayContent(JSON.toJSONString(request))
//                .submissionTime(LocalDateTime.now())
//                .correctionStatus("1")
//                .correctionResult(ECCResponse.toJsonString(resp.getData())).build();
//        correctionRequestService.save(correctionRequest);
//        // 已使用次数加一
//        correctionCount.setUsedCount(correctionCount.getUsedCount() + 1);
//        countService.updateById(correctionCount);
        return Result.success(data);
    }

    @PostMapping("/eval_record")
    public Result getEval(@RequestBody EvalRecordRequest request) throws WxErrorException {
        if (StringUtils.isEmpty(request.getCode())) {
            return Result.error("请与微信公众号查看批改记录");
        }
        WxOAuth2AccessToken wxOAuth2AccessToken = WxMpServiceUtil.getWxMpService().getOAuth2Service().getAccessToken(request.getCode());
        String openId = wxOAuth2AccessToken.getOpenId();
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("wechat_user_id", openId));
        if(Objects.isNull(user)){
            return Result.error("未查询到有效用户");
        }
        List<CorrectionRequest> requestList = correctionRequestService.list(new QueryWrapper<CorrectionRequest>().eq("user_id", user.getUserId()));
        System.out.println(requestList);
        return Result.success(requestList);
    }
}
