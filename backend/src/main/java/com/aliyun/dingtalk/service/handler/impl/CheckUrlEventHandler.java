package com.aliyun.dingtalk.service.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.service.handler.EventHandler;

/**
 * 回调测试url，不需要处理
 */
public class CheckUrlEventHandler implements EventHandler {

    @Override
    public void handler(JSONObject eventJson) {

    }
}
