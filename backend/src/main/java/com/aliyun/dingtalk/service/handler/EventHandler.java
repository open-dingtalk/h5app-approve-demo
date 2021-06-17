package com.aliyun.dingtalk.service.handler;

import com.alibaba.fastjson.JSONObject;

/**
 * 钉钉回调接口接收到事件后处理接口，根据业务自行实现业务逻辑
 */
public interface EventHandler {
    void handler(JSONObject eventJson);
}
