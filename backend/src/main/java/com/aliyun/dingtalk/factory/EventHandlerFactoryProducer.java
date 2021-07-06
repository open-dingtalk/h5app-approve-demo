package com.aliyun.dingtalk.factory;

import com.aliyun.dingtalk.config.ApplicationContextHolder;
import com.aliyun.dingtalk.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取事件工厂 事件参考文档：https://developers.dingtalk.com/document/app/event-list
 */
@Component
public class EventHandlerFactoryProducer {

    @Autowired
    private ApplicationContextHolder applicationContextHolder;

    /**
     * 根据事件类型获取响应工厂
     * @param eventType
     * @return
     */
    public AbstractEventHandlerFactory getEventHandlerFactory(String eventType) {

        if (eventType.startsWith(Constant.BpmConstant.BPMS)) {
            // 审批事件
            // 获取审批事件工厂
            return applicationContextHolder.getApplicationContext().getBean(BpmEventHandlerFactory.class);
        } else if (eventType.startsWith("check_url")) {
            // 测试回调url事件
            return applicationContextHolder.getApplicationContext().getBean(CheckUrlEventHandlerFactory.class);
        }
//        else if (eventType.startsWith("chat")) {
//            // 群会话事件
//        } else if (eventType.startsWith("check_in")) {
//            // 签到事件
//        } else if (eventType.startsWith("attendance")) {
//            // 考勤事件
//        } else if (eventType.startsWith("meetingroom")) {
//            // 会议室事件
//        } else if (eventType.startsWith("edu")) {
//            // 家校通讯录事件
//        } else if (eventType.startsWith("user")) {
//            // 通讯录员工相关事件
//        } else if (eventType.startsWith("org")) {
//            // 通讯录企业相关事件
//        } else if (eventType.startsWith("label")) {
//            // 通讯录角色相关事件
//        }
        else {
            // 传入事件不匹配，需要自行处理
            return null;
        }
    }
}
