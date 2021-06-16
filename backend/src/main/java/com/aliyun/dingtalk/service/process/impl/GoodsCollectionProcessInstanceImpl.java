package com.aliyun.dingtalk.service.process.impl;

import com.aliyun.dingtalk.constant.Constant;
import com.aliyun.dingtalk.model.ProcessInstanceInputVO;
import com.aliyun.dingtalk.service.process.ProcessInstanceService;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import org.springframework.stereotype.Service;

/**
 * 物品领用流程
 */
@Service
public class GoodsCollectionProcessInstanceImpl implements ProcessInstanceService {

    /**
     * 构建创建流程实例请求参数
     * @param processInstanceInput
     * @return
     */
    @Override
    public OapiProcessinstanceCreateRequest buildProcessInstanceCreateRequest(ProcessInstanceInputVO processInstanceInput) {
        OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
        request.setProcessCode(Constant.PROCESS_CODE);
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
}
