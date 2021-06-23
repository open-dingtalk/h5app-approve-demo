package com.aliyun.dingtalk.constant;

/**
 * 项目中的常量定义类
 */
public class Constant {
//    /**
//     * 企业corpid, 需要修改成开发者所在企业
//     */
//    public static final String CORP_ID = "ding02bwv6xa2vdmtbvw";
//
//    /**
//     * Constant.OWNER_KEY 说明：
//     *   1、开发者后台配置的订阅事件为应用级事件推送，此时OWNER_KEY为应用的APP_KEY（企业内部应用）或SUITE_KEY（三方应用）。
//     *   2、调用订阅事件接口订阅的事件为企业级事件推送，此时OWNER_KEY为：企业的CORP_ID（企业内部应用）或SUITE_KEY（三方应用）
//     */
//    public static final String OWNER_KEY = "ding02bwv6xa2vdmtbvw";
//
//    /**
//     * 应用的AppKey，登录开发者后台，点击应用管理，进入应用详情可见
//     */
//    public static final String APPKEY = "ding02bwv6xa2vdmtbvw";
//    /**
//     * 应用的AppSecret，登录开发者后台，点击应用管理，进入应用详情可见
//     */
//    public static final String APPSECRET = "XCttkN4ofR3Oejm2gAPBA1Av3cTi2_HqcHNsdBgKtjmN8u0IFcJgcaNoWE8k8OiW";
//
//    /**
//     * 数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成
//     * 需要和钉钉开发后台配置事件订阅里面配置的一致
//     */
//    public static final String AES_KEY = "SF57P8MsSFBd2BEJW8gWLmL2UyB1gyozQihFzK5JiJd";
//
//    /**
//     * 加解密需要用到的token，企业可以随机填写。如 "12345"
//     * 需要和钉钉开发后台配置事件订阅里面配置的一致
//     */
//    public static final String TOKEN = "V6ui9rvFmcvUZ2wyFJvjWhrfJ4Z1xbeAyUl7";
//
//    /**
//     * 应用的agentdId，登录开发者后台可查看
//     */
//    public static final Long AGENTID = 111L;
//
//    /**
//     * 审批模板唯一标识，可以在审批管理后台找到
//     */
//    public static final String PROCESS_CODE = "PROC-B7F31370-9086-4BE8-A6B5-994040DE0153";

    public static class BpmConstant{

        /**
         * 用于获取相对应工厂
         */
        public static final String BPMS = "bpms";

        /**
         * 事件类型 审批任务开始，结束，转交。
         */
        public static final String BPMS_TASK_CHANGE = "bpms_task_change";

        /**
         * 事件类型 审批实例开始，结束。
         */
        public static final String BPMS_INSTANCE_CHANGE = "bpms_instance_change";
    }
}
