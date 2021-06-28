## h5app-approve-demo

> 钉钉智能工作流，接入智能工作流需要登陆[开发者后台](https://open-dev.dingtalk.com/)，在创建的应用中配置“配置事件订阅“、申请智能工作流权限；登陆[OA审批管理后台](https://aflow.dingtalk.com/dingtalk/web/query/dashboard?dinghash=aflowSetting#/aflowSetting)创建新表单，需要记录模板**processCode**，demo使用的是”物品领用“表单。

##### 配置事件订阅

![image-20210617175113262](https://img.alicdn.com/imgextra/i3/O1CN01yseZfx1PokSgnxFHI_!!6000000001888-2-tps-1962-1382.png)

![image-20210617175143337](https://img.alicdn.com/imgextra/i1/O1CN01M2yocP1k484ng0lJX_!!6000000004629-2-tps-2302-1452.png)

##### 申请只能工作流权限

![image-20210617175244254](https://img.alicdn.com/imgextra/i2/O1CN01iae3im1Ccl5X9ezcy_!!6000000000102-2-tps-2864-1050.png)

##### 创建新表单（物品领用）

![image-20210617175833721](https://img.alicdn.com/imgextra/i3/O1CN01CX9YPd1UHq54kNi9f_!!6000000002493-2-tps-2192-1182.png)

**processCode** 审批模板的唯一编码，在审批模板编辑页的URL中查看。

![image-20210617181749035](https://img.alicdn.com/imgextra/i1/O1CN0176xhxY1KPUXbCacPq_!!6000000001156-2-tps-2846-1052.png)

## Getting Started

### 克隆代码仓库到本地

git clone

```
https://github.com/open-dingtalk/h5app-approve-demo.git
```

### 使用命令行安装依赖&打包

```txt
cd fronted/
```

![image-20210617180814765](https://img.alicdn.com/imgextra/i3/O1CN012KHlLL28HumHd2Sii_!!6000000007908-2-tps-2388-568.png)

```txt
npm install
```

![image-20210617180910689](https://img.alicdn.com/imgextra/i1/O1CN01lvjJRL1VfwXcafY1f_!!6000000002681-2-tps-2162-934.png)

```txt
npm run build
```

![image-20210617181053688](https://img.alicdn.com/imgextra/i4/O1CN01ggVh2o1opzw8oT2fq_!!6000000005275-2-tps-2276-954.png)

### 将打包好的静态资源文件放入后端服务

![img](https://img.alicdn.com/imgextra/i3/O1CN01nnr42n1tgu9N0xAp9_!!6000000005932-2-tps-1728-1128.png)

### 替换后端应用配置

![image-20210628141544633](https://img.alicdn.com/imgextra/i1/O1CN01eJCtZM1dnkDEj55ap_!!6000000003781-2-tps-2602-1186.png)



### 参考文档

1. 智能工作流权限申请，文档链接：https://developers.dingtalk.com/document/app/logon-free-process?spm=ding_open_doc.document.0.0.6dbdad29INJCRg#topic-2025319
2. 工作流审批表单，文档链接：https://developers.dingtalk.com/document/app/workflow-overview
3. 发起审批实例，文档链接：https://developers.dingtalk.com/document/app/initiate-approval
4. 获取实例详情，文档链接：https://developers.dingtalk.com/document/app/obtains-the-details-of-a-single-approval-instance
5. 事件回调，文档链接：https://developers.dingtalk.com/document/app/callback-overview
