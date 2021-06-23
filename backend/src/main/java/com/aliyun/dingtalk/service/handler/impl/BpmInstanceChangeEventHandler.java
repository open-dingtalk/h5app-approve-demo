package com.aliyun.dingtalk.service.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.cache.LocalCache;
import com.aliyun.dingtalk.service.handler.EventHandler;
import com.aliyun.dingtalk.service.process.ProcessInstanceService;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 审批实例开始，结束事件处理
 */
@Service
public class BpmInstanceChangeEventHandler implements EventHandler {

    @Autowired
    private ProcessInstanceService processInstanceService;

    @Override
    public void handler(JSONObject eventJson) {
        Map<String, OapiProcessinstanceGetResponse.ProcessInstanceTopVo> cacheMap = LocalCache.getInstance().getCacheMap();

        String processInstanceId = eventJson.getString("processInstanceId");

        OapiProcessinstanceGetResponse.ProcessInstanceTopVo processInstanceTopVo = processInstanceService.getProcessInstanceById(processInstanceId);

        cacheMap.put(processInstanceId, processInstanceTopVo);

    }
}
