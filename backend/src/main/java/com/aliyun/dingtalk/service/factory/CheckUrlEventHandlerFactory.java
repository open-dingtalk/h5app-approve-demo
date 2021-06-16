package com.aliyun.dingtalk.service.factory;

import com.aliyun.dingtalk.config.ApplicationContextHolder;
import com.aliyun.dingtalk.service.handler.EventHandler;
import com.aliyun.dingtalk.service.handler.impl.CheckUrlEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 回调测试事件工厂
 */
@Service
public class CheckUrlEventHandlerFactory extends AbstractEventHandlerFactory {

    @Autowired
    private ApplicationContextHolder applicationContextHolder;

    @Override
    public EventHandler getEventHandler(String eventType) {
        return applicationContextHolder.getApplicationContext().getBean(CheckUrlEventHandler.class);
    }
}
