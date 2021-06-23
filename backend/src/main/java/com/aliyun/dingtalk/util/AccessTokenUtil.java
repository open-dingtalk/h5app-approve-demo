package com.aliyun.dingtalk.util;

import com.aliyun.dingtalk.constant.UrlConstant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取access_token工具类
 */
@Slf4j
public class AccessTokenUtil {


    public static String getToken(String appKey, String appSecret) throws RuntimeException {
        try {

            DefaultDingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_TOKEN_URL);
            OapiGettokenRequest request = new OapiGettokenRequest();

            request.setAppkey(appKey);
            request.setAppsecret(appSecret);
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);
            String accessToken = response.getAccessToken();
            return accessToken;
        } catch (ApiException e) {
            log.error("getAccessToken failed", e);
            throw new RuntimeException();
        }

    }
}
