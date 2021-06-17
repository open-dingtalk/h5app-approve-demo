package com.aliyun.dingtalk.service.user.impl;

import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalk.service.user.DingTalkUserService;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.request.OapiV2UserGetuserinfoRequest;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.dingtalk.api.response.OapiV2UserGetuserinfoResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * 用户管理
 */
@Slf4j
@Service
public class DingTalkUserServiceImpl implements DingTalkUserService {


    public OapiV2UserGetResponse.UserGetResponse getUserInfo(String authCode) {

        // 1. 获取access_token
        String accessToken = AccessTokenUtil.getToken();

        // 2. 获取用户ID
        String userId = getUserId(authCode, accessToken);

        // 2. 根据用户ID获取用户详情
        return getOapiV2UserGetResponseByUserId(userId, accessToken);

    }

    /**
     * 根据authCode获取用户ID
     * @param authCode
     * @param accessToken
     * @return
     */
    private String getUserId(String authCode, String accessToken) {
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_USER_INFO_URL);
        OapiV2UserGetuserinfoRequest req = new OapiV2UserGetuserinfoRequest();
        req.setCode(authCode);
        OapiV2UserGetuserinfoResponse oapiV2UserGetuserinfoResponse;
        try {
            oapiV2UserGetuserinfoResponse = client.execute(req, accessToken);
            if (!Objects.isNull(oapiV2UserGetuserinfoResponse)) {
                if (oapiV2UserGetuserinfoResponse.isSuccess()) {
                    OapiV2UserGetuserinfoResponse.UserGetByCodeResponse userGetByCodeResponse = oapiV2UserGetuserinfoResponse.getResult();
                    if (!Objects.isNull(userGetByCodeResponse)) {
                        return userGetByCodeResponse.getUserid();
                    }
                } else {
                    log.error("获取用户信息失败，errCode:{}, errMsg:{}", oapiV2UserGetuserinfoResponse.getErrcode(), oapiV2UserGetuserinfoResponse.getMsg());
                }
            } else {
                log.error("获取用户信息响应为空！");
            }
        } catch (ApiException e) {
            // 需要自己处理异常
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据用户ID获取用户详情
     * @param userId
     * @param accessToken
     * @return
     */
    private OapiV2UserGetResponse.UserGetResponse getOapiV2UserGetResponseByUserId(String userId, String accessToken) {
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.USER_GET_URL);
        OapiV2UserGetRequest req = new OapiV2UserGetRequest();
        req.setUserid(userId);
        req.setLanguage("zh_CN");

        try {
            OapiV2UserGetResponse oapiV2UserGetResponse = client.execute(req, accessToken);
            if (!Objects.isNull(oapiV2UserGetResponse)) {
                if (oapiV2UserGetResponse.isSuccess()) {
                    return oapiV2UserGetResponse.getResult();
                } else {
                    log.error("获取用户详情失败，errCode:{}, errMsg:{}", oapiV2UserGetResponse.getErrcode(), oapiV2UserGetResponse.getMsg());
                }
            } else {
                log.error("获取用户详情响应为空！");
            }
        } catch (ApiException e) {
            // 需要自己处理异常
            e.printStackTrace();
        }
        return null;
    }
}
