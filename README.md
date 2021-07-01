## h5app-approve-demo

### 功能介绍
- OA物品领用是一个企业内部常用的应用功能，本例是钉钉企业内部H5微应用（https://developers.dingtalk.com/document/app/orgapp-development-process）。架构形态是一个Java单体应用，钉钉用户可以在页面上点击**领用并提交审批**按钮发起一个物品领用的审批，之后在钉钉上面进行审批，钉钉在用户审批时会通过应用配置的回调接口调用服务端接口，回调接口可以处理自己的业务逻辑，demo里面的回调处理为把审批信息放到一个map里面，用户可以在页面点击**获取审批信息**按钮获取创建的审批单。包含功能：
    - 领用并提交审批：用于发起一个物品领用的审批；
    - 获取提交的审批信息：查询当前已经提交的审批单；
### 开发环境准备
#### 钉钉开放平台环境准备

1. 需要有一个钉钉注册企业，如果没有可以创建：https://oa.dingtalk.com/register_new.htm?source=1008_OA&lwfrom=2018122711522903000&succJump=oa#/

2. 成为钉钉开发者，参考文档：https://developers.dingtalk.com/document/app/become-a-dingtalk-developer

3. 登录钉钉开放平台后台创建一个H5应用： https://open-dev.dingtalk.com/#/index

4. 配置应用

   配置开发管理，参考文档：https://developers.dingtalk.com/document/app/configure-orgapp

   配置事件订阅，配置回调接口时需要启动服务，参考文档：https://developers.dingtalk.com/document/app/configure-event-subcription

   ![image](https://img.alicdn.com/imgextra/i4/O1CN01X0DhYg1EjHxvxYf5U_!!6000000000387-2-tps-2818-1126.png)

   ![image-20210625173933467](https://img.alicdn.com/imgextra/i1/O1CN01tzFu9s1dbNWHrPgU1_!!6000000003754-2-tps-2868-1258.png)

   配置免登相关权限：https://developers.dingtalk.com/document/app/address-book-permissions

   ![image-20210628161245415](https://img.alicdn.com/imgextra/i4/O1CN01fvqz0z1J8iQ1XSiRi_!!6000000000984-2-tps-2828-1200.png)

   配置智能工作流相关的权限 参考文档：https://developers.dingtalk.com/document/app/permission-application-and-basic-concepts

   ![image-20210628161245415](https://img.alicdn.com/imgextra/i1/O1CN01zrVQbx1xKF14u0wgJ_!!6000000006424-2-tps-2822-1050.png)

5. 创建审批单，demo使用的是物品领用审表单，参考文档：https://developers.dingtalk.com/document/app/workflow-overview

##### 获取H5钉钉应用的参数

```properties
#钉钉组织ID
corpId=xxxxx
#H5应用Key
appKey=xxxx
#H5应用秘钥
appSecret=xxxxxx
#数据加密密钥。用于回调数据的加密
aesKey=xxxxx
#加解密需要用到的token
token=xxxxx
#审批模板唯一标识，可以在审批管理后台找到 物品领用模板
processCode=xxxxx
```

##### 钉钉应用参数需要登陆开发者后台

1. 首页获取corpId https://open-dev.dingtalk.com/#/index
2. 进入应用-基础信息获取appKey、appSecret
3. 进入应用-事件订阅获取aesKey、token
4. 审批管理后台processCode，参考文档：https://developers.dingtalk.com/document/app/workflow-overview

### 修改前端页面

#### 使用命令行安装依赖&打包

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

#### 将打包好的静态资源文件放入后端服务

![img](https://img.alicdn.com/imgextra/i2/O1CN01FyhgSA1xwFtAW37NP_!!6000000006507-2-tps-1728-1128.png)



### 参考文档

1. 智能工作流权限申请，文档链接：https://developers.dingtalk.com/document/app/logon-free-process?spm=ding_open_doc.document.0.0.6dbdad29INJCRg#topic-2025319
2. 工作流审批表单，文档链接：https://developers.dingtalk.com/document/app/workflow-overview
3. 发起审批实例，文档链接：https://developers.dingtalk.com/document/app/initiate-approval
4. 获取实例详情，文档链接：https://developers.dingtalk.com/document/app/obtains-the-details-of-a-single-approval-instance
5. 事件回调，文档链接：https://developers.dingtalk.com/document/app/callback-overview
