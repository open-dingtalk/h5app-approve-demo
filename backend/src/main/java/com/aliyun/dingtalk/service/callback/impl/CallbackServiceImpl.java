package com.aliyun.dingtalk.service.callback.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.config.AppConfig;
import com.aliyun.dingtalk.service.callback.CallbackService;
import com.aliyun.dingtalk.service.factory.EventHandlerFactoryProducer;
import com.aliyun.dingtalk.util.DingCallbackCrypto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class CallbackServiceImpl implements CallbackService {

    @Autowired
    private EventHandlerFactoryProducer eventHandlerFactoryProducer;

    @Autowired
    private AppConfig appConfig;

    @Override
    public Map<String, String> callback(String msgSignature, String timeStamp, String nonce, JSONObject json) {
        try {
            // 1.使用加解密类型
            DingCallbackCrypto callbackCrypto = new DingCallbackCrypto(appConfig.getToken(), appConfig.getAesKey(), appConfig.getAppKey());
            String encryptMsg = json.getString("encrypt");
            String decryptMsg = callbackCrypto.getDecryptMsg(msgSignature, timeStamp, nonce, encryptMsg);

            // 2. 反序列化回调事件json数据
            JSONObject eventJson = JSON.parseObject(decryptMsg);
            log.info("eventJson: {}", eventJson);
            String eventType = eventJson.getString("EventType");

            // 3. 根据EventType分类处理
            eventHandlerFactoryProducer.getEventHandlerFactory(eventType).getEventHandler(eventType).handler(eventJson);

            // 4. 返回success的加密数据
            Map<String, String> successMap = callbackCrypto.getEncryptedMap("success");
            return successMap;

        } catch (DingCallbackCrypto.DingTalkEncryptException e) {
            e.printStackTrace();
            log.error("process callback failed！msg: {}", e);
            return null;
        }
    }
}
