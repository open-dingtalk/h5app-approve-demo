package com.aliyun.dingtalk.service.process.impl;

import com.aliyun.dingtalk.cache.LocalCache;
import com.aliyun.dingtalk.config.AppConfig;
import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalk.model.ProcessInstanceInputVO;
import com.aliyun.dingtalk.service.process.ProcessInstanceService;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 物品领用流程
 */
@Slf4j
@Service
public class GoodsCollectionProcessInstanceImpl implements ProcessInstanceService {

    @Autowired
    private AppConfig appConfig;

    /**
     * 构建创建流程实例请求参数
     *
     * @param processInstanceInput
     * @return
     */
    @Override
    public OapiProcessinstanceCreateRequest buildProcessInstanceCreateRequest(ProcessInstanceInputVO processInstanceInput) {
        OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
        request.setProcessCode(appConfig.getProcessCode());
        request.setFormComponentValues(processInstanceInput.generateForms());
        /**
         * 如果想复用审批固定流程，使用或签会签的话，可以不传审批人，具体请参考文档： https://open-doc.dingtalk.com/microapp/serverapi2/ebkwx8
         * 本次quickstart，演示不传审批人的场景
         */
        request.setApprovers(processInstanceInput.getOriginatorUserId());
        request.setOriginatorUserId(processInstanceInput.getOriginatorUserId());
        request.setDeptId(processInstanceInput.getDeptId());
        request.setCcList(processInstanceInput.getOriginatorUserId());
        request.setCcPosition("FINISH");
        return request;
    }

    /**
     * 获取accessToken
     * @return
     */
    @Override
    public String getAccessToken() {

        return AccessTokenUtil.getToken(appConfig.getAppKey(), appConfig.getAppSecret());
    }

    @Override
    public OapiProcessinstanceGetResponse.ProcessInstanceTopVo getProcessInstanceById(String instanceId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_PROCESS_INSTANCE_URL);
            OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
            request.setProcessInstanceId(instanceId);
            OapiProcessinstanceGetResponse response = client.execute(request, getAccessToken());
            if (!response.isSuccess()) {
                log.info("get process instance fail, errCode: {}, errMsg: {}", response.getErrorCode(), response.getErrmsg());
            } else {
                return response.getProcessInstance();
            }
        } catch (Exception e) {
            log.info("get process instance exception, exception: {}", e);
        }
        return null;
    }

    /**
     * 从LocalCache获取所有实例信息
     * @return
     */
    @Override
    public List<OapiProcessinstanceGetResponse.ProcessInstanceTopVo> getProcessInstanceList() {
        Map<String, OapiProcessinstanceGetResponse.ProcessInstanceTopVo> cacheMap = LocalCache.getInstance().getCacheMap();
        return cacheMap.values().stream().collect(Collectors.toList());
    }
}
