package com.aliyun.dingtalk.service.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.service.handler.EventHandler;
import org.springframework.stereotype.Service;

/**
 * 回调测试url，不需要处理
 */
@Service
public class CheckUrlEventHandler implements EventHandler {

    @Override
    public void handler(JSONObject eventJson) {

    }
}
