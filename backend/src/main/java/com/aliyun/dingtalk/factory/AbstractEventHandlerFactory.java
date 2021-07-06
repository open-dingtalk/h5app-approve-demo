package com.aliyun.dingtalk.factory;

import com.aliyun.dingtalk.service.handler.EventHandler;

/**
 * 事件处理抽象工厂
 */
public abstract class AbstractEventHandlerFactory {

    public abstract EventHandler getEventHandler(String eventType);

}
