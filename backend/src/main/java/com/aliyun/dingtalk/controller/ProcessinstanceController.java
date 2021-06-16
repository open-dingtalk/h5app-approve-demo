package com.aliyun.dingtalk.controller;

import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalk.model.ProcessInstanceInputVO;
import com.aliyun.dingtalk.model.ServiceResult;
import com.aliyun.dingtalk.model.ServiceResultCode;
import com.aliyun.dingtalk.service.process.ProcessInstanceService;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
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
public class ProcessinstanceController {

	@Autowired
	private ProcessInstanceService processInstanceService;

	/**
	 * 欢迎页面
	 */
	@GetMapping("/instance/welcome")
	public String welcome() {
		return "welcome";
	}


	/**
	 * 发起审批
	 */
	@PostMapping("/instance")
	public ServiceResult<String> startProcessInstance(@RequestBody ProcessInstanceInputVO processInstanceInputVO) {

		return processInstanceService.createProcessInstance(buildProcessInstanceInputVO());
	}

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
		detailFormList.add(detailForm);
		processInstanceInputVO.setDetailForms(detailFormList);
		processInstanceInputVO.setDeptId(1L);
		processInstanceInputVO.setOriginatorUserId("043217290519980938");

		return processInstanceInputVO;

	}

	/**
	 * 根据审批实例id获取审批详情
	 * @param instanceId
	 * @return
	 */
	@GetMapping("/instance/{instanceId}")
	public ServiceResult getProcessInstanceById(@PathVariable String instanceId) {
		try {
			DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_PROCESS_INSTANCE_URL);
			OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
			request.setProcessInstanceId(instanceId);
			OapiProcessinstanceGetResponse response = client.execute(request, AccessTokenUtil.getToken());
			if (!response.isSuccess()) {
				return ServiceResult.failure(String.valueOf(response.getErrorCode()), response.getErrmsg());
			}
			return ServiceResult.success(response.getProcessInstance());
		} catch (Exception e) {
			log.info("get process instance fail, msg: {}", e);
			return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(),ServiceResultCode.SYS_ERROR.getErrMsg());
		}
	}
}


