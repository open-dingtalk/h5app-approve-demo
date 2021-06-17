package com.aliyun.dingtalk.service.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.service.handler.EventHandler;
import org.springframework.stereotype.Service;

/**
 * 审批实例开始，结束事件处理
 */
@Service
public class BpmInstanceChangeEventHandler implements EventHandler {

    @Override
    public void handler(JSONObject eventJson) {

    }
}
