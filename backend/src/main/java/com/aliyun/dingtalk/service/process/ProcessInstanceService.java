package com.aliyun.dingtalk.service.process;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalk.model.ProcessInstanceInputVO;
import com.aliyun.dingtalk.model.ServiceResult;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public interface ProcessInstanceService {

    Logger logger = LoggerFactory.getLogger(ProcessInstanceService.class);

    OapiProcessinstanceCreateRequest buildProcessInstanceCreateRequest(ProcessInstanceInputVO processInstanceInput);

    default ServiceResult createProcessInstance(ProcessInstanceInputVO processInstanceInput) {

        try {
            // 创建client
            DefaultDingTalkClient client = new DefaultDingTalkClient(UrlConstant.CREATE_PROCESS_INSTANCE_URL);

            // 构建请求数据
            OapiProcessinstanceCreateRequest request = buildProcessInstanceCreateRequest(processInstanceInput);
            // 创建流程
            logger.info("invoke dingTalk create process instance params request: {}", JSONObject.toJSON(request));
            OapiProcessinstanceCreateResponse response = client.execute(request, AccessTokenUtil.getToken());
            logger.info("invoke dingTalk response: {}", JSONObject.toJSON(response));
            if (!response.isSuccess()) {
                return ServiceResult.failure(String.valueOf(response.getErrorCode()), response.getErrmsg());
            }
            // 流程创建完成处理业务逻辑
            postHandler();

            return ServiceResult.success(response.getProcessInstanceId());

        } catch (ApiException e) {

            e.printStackTrace();
            return ServiceResult.failure(e.getErrCode(), e.getMessage());
        }

    }

    default void postHandler() {}

    OapiProcessinstanceGetResponse.ProcessInstanceTopVo getProcessInstanceById(String instanceId);
}
