package com.aliyun.dingtalk.service.user;

import com.dingtalk.api.response.OapiV2UserGetResponse;


public interface DingTalkUserService {
    OapiV2UserGetResponse.UserGetResponse getUserInfo(String authCode);
}
