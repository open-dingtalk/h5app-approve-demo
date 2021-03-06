package com.aliyun.dingtalk.service.process;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalk.exception.InvokeDingTalkException;
import com.aliyun.dingtalk.model.ProcessInstanceInputVO;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public interface ProcessInstanceService {

    Logger logger = LoggerFactory.getLogger(ProcessInstanceService.class);


    /**
     * 构建创建实例请求对象
     * @param processInstanceInput
     * @return
     */
    OapiProcessinstanceCreateRequest buildProcessInstanceCreateRequest(ProcessInstanceInputVO processInstanceInput);

    /**
     * 创建流程实例
     * @param processInstanceInput
     * @return
     */
    default String createProcessInstance(ProcessInstanceInputVO processInstanceInput) {

        try {
            // 创建client
            DefaultDingTalkClient client = new DefaultDingTalkClient(UrlConstant.CREATE_PROCESS_INSTANCE_URL);

            // 构建请求数据
            OapiProcessinstanceCreateRequest request = buildProcessInstanceCreateRequest(processInstanceInput);
            // 创建流程
            logger.info("invoke dingTalk create process instance params request: {}", JSONObject.toJSON(request));
            OapiProcessinstanceCreateResponse response = client.execute(request, getAccessToken());
            logger.info("invoke dingTalk response: {}", JSONObject.toJSON(response));
            if (response.isSuccess()) {
                // 流程创建完成处理业务逻辑
                postHandler();
                return response.getProcessInstanceId();
            } else {
                throw new InvokeDingTalkException(response.getErrorCode(), response.getErrmsg());
            }

        } catch (ApiException e) {
            e.printStackTrace();
            throw new InvokeDingTalkException(e.getErrCode(), e.getErrMsg());
        }

    }

    /**
     * 流程创建完成处理业务逻辑
     */
    default void postHandler() {}

    /**
     * 获取accessToken
     * @return
     */
    String getAccessToken();

    /**
     * 根据instanceId获取实例详情
     * @param instanceId
     * @return
     */
    OapiProcessinstanceGetResponse.ProcessInstanceTopVo getProcessInstanceById(String instanceId);

    List<OapiProcessinstanceGetResponse.ProcessInstanceTopVo> getProcessInstanceList();
}
