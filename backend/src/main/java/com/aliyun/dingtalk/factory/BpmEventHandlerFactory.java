package com.aliyun.dingtalk.factory;

import com.aliyun.dingtalk.config.ApplicationContextHolder;
import com.aliyun.dingtalk.constant.Constant;
import com.aliyun.dingtalk.service.handler.EventHandler;
import com.aliyun.dingtalk.service.handler.impl.BpmInstanceChangeEventHandler;
import com.aliyun.dingtalk.service.handler.impl.BpmTaskChangeEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 审批事件处理工厂 获取事件处理实现
 */
@Component
public class BpmEventHandlerFactory extends AbstractEventHandlerFactory {

    @Autowired
    private ApplicationContextHolder applicationContextHolder;

    @Override
    public EventHandler getEventHandler(String eventType) {
        if (Constant.BpmConstant.BPMS_TASK_CHANGE.equals(eventType)) {
            // 审批任务开始，结束，转交。
            // 获取处理事件实现
            return applicationContextHolder.getApplicationContext().getBean(BpmTaskChangeEventHandler.class);
        } else if (Constant.BpmConstant.BPMS_INSTANCE_CHANGE.equals(eventType)) {
            // 审批实例开始，结束。
            // 获取处理事件实现
            return applicationContextHolder.getApplicationContext().getBean(BpmInstanceChangeEventHandler.class);
        } else {
            // 传入事件不匹配，需要自行处理
            return null;
        }
    }
}
