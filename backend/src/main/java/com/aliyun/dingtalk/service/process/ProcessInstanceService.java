package com.aliyun.dingtalk.service.process;

import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalk.model.ProcessInstanceInputVO;
import com.aliyun.dingtalk.model.ServiceResult;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;

@Service
public interface ProcessInstanceService {

    OapiProcessinstanceCreateRequest buildProcessInstanceCreateRequest(ProcessInstanceInputVO processInstanceInput);

    default ServiceResult createProcessInstance(ProcessInstanceInputVO processInstanceInput){

        try {

            // 创建client
            DefaultDingTalkClient client = new DefaultDingTalkClient(UrlConstant.CREATE_PROCESS_INSTANCE_URL);

            // 构建请求数据
            OapiProcessinstanceCreateRequest request = buildProcessInstanceCreateRequest(processInstanceInput);

            // 创建流程
            OapiProcessinstanceCreateResponse response = client.execute(request, AccessTokenUtil.getToken());
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

    default void postHandler(){};
}
