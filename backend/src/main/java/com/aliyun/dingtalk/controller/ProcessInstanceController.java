package com.aliyun.dingtalk.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalk.model.ProcessInstanceInputVO;
import com.aliyun.dingtalk.model.ServiceResult;
import com.aliyun.dingtalk.service.process.ProcessInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业 E应用审批解决方案示例代码
 * 实现了审批的基础功能
 */
@Slf4j
@RestController
@RequestMapping("/process")
public class ProcessInstanceController {

    @Autowired
    private ProcessInstanceService processInstanceService;


    /**
     * 发起审批
     */
    @PostMapping("/instance")
    public ServiceResult<String> startProcessInstance(@RequestBody ProcessInstanceInputVO processInstanceInputVO) {
//		processInstanceInputVO = buildProcessInstanceInputVO();
        log.info("ProcessInstanceController#startProcessInstance params: {}", JSONObject.toJSON(processInstanceInputVO));

        return processInstanceService.createProcessInstance(processInstanceInputVO);
    }

    // 构建请求对象
    private ProcessInstanceInputVO buildProcessInstanceInputVO() {
        ProcessInstanceInputVO processInstanceInputVO = new ProcessInstanceInputVO();

        List<ProcessInstanceInputVO.TextForm> textFormList = new ArrayList<>();
        ProcessInstanceInputVO.TextForm textForm1 = new ProcessInstanceInputVO.TextForm();
        textForm1.setName("物品用途");
        textForm1.setValue("测试物品领用");
        ProcessInstanceInputVO.TextForm textForm2 = new ProcessInstanceInputVO.TextForm();
        textForm2.setName("领用详情");
        textForm2.setValue("领用详情1");
        textFormList.add(textForm1);
        textFormList.add(textForm2);
        processInstanceInputVO.setTextForms(textFormList);

        List<ProcessInstanceInputVO.DetailForm> detailFormList = new ArrayList<>();
        ProcessInstanceInputVO.DetailForm detailForm = new ProcessInstanceInputVO.DetailForm();
        detailForm.setName("物品明细");
        List<ProcessInstanceInputVO.TextForm> detailTextFormList = new ArrayList<>();
        ProcessInstanceInputVO.TextForm detailTextForm1 = new ProcessInstanceInputVO.TextForm();
        detailTextForm1.setName("物品名称");
        detailTextForm1.setValue("测试物品领用-电脑");
        ProcessInstanceInputVO.TextForm detailTextForm2 = new ProcessInstanceInputVO.TextForm();
        detailTextForm2.setName("数量");
        detailTextForm2.setValue("3");
        detailTextFormList.add(detailTextForm1);
        detailTextFormList.add(detailTextForm2);
        detailForm.setTextForms(detailTextFormList);
        detailFormList.add(detailForm);
        processInstanceInputVO.setDetailForms(detailFormList);
        // 替换为钉钉用户部门
        processInstanceInputVO.setDeptId(1L);
        // 替换为钉钉用户ID
        processInstanceInputVO.setOriginatorUserId("***");

        return processInstanceInputVO;

    }

    /**
     * 根据审批实例id获取审批详情
     *
     * @param instanceId
     * @return
     */
    @GetMapping("/instance/{instanceId}")
    public ServiceResult getProcessInstanceById(@PathVariable String instanceId) {

        return ServiceResult.success(processInstanceService.getProcessInstanceById(instanceId));

    }

    /**
     * 根据审批实例id获取审批详情
     *
     * @return
     */
    @GetMapping("/instance")
    public ServiceResult getProcessInstanceList() {

        return ServiceResult.success(processInstanceService.getProcessInstanceList());

    }

}


