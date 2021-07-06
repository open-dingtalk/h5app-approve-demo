package com.aliyun.dingtalk.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalk.exception.InvokeDingTalkException;
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


    public static String getAccessToken(String appKey, String appSecret) throws RuntimeException {
        try {

            DefaultDingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_TOKEN_URL);
            OapiGettokenRequest request = new OapiGettokenRequest();

            request.setAppkey(appKey);
            request.setAppsecret(appSecret);
            request.setHttpMethod("GET");
            log.info("getAccessToken request: {}", JSONObject.toJSON(request));
            OapiGettokenResponse response = client.execute(request);
            log.info("getAccessToken response:{}", JSONObject.toJSON(response));
            if (response.isSuccess()) {
                return response.getAccessToken();
            } else {
                throw new InvokeDingTalkException(response.getErrorCode(), response.getErrmsg());
            }
        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        }

    }
}
