## h5app-approve-demo

> 钉钉智能工作流，接入智能工作流需要登陆[开发者后台](https://open-dev.dingtalk.com/)，在创建的应用中配置“配置事件订阅“、申请智能工作流权限；登陆[OA审批管理后台](https://aflow.dingtalk.com/dingtalk/web/query/dashboard?dinghash=aflowSetting#/aflowSetting)创建新表单，需要记录模板**processCode**，demo使用的是”物品领用“表单。

##### 配置事件订阅

![image-20210617175113262](/Users/wan/Library/Application Support/typora-user-images/image-20210617175113262.png)

![image-20210617175143337](/Users/wan/Library/Application Support/typora-user-images/image-20210617175143337.png)

##### 申请只能工作流权限

![image-20210617175244254](/Users/wan/Library/Application Support/typora-user-images/image-20210617175244254.png)

##### 创建新表单（物品领用）

![image-20210617175833721](/Users/wan/Library/Application Support/typora-user-images/image-20210617175833721.png)

**processCode** 审批模板的唯一编码，在审批模板编辑页的URL中查看。

![image-20210617181749035](/Users/wan/Library/Application Support/typora-user-images/image-20210617181749035.png)

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

![image-20210617180814765](/Users/wan/Library/Application Support/typora-user-images/image-20210617180814765.png)

```txt
npm install
```

![image-20210617180910689](/Users/wan/Library/Application Support/typora-user-images/image-20210617180910689.png)

```txt
npm run build
```

![image-20210617181053688](/Users/wan/Library/Application Support/typora-user-images/image-20210617181053688.png)

### 将打包好的静态资源文件放入后端服务

![img](https://alidocs.oss-cn-zhangjiakou.aliyuncs.com/a/RNErEm4k2S2KW0jV/714875c9b4294970b07004542047fc672460.png)

### 替换后端应用配置

![image-20210617181426867](/Users/wan/Library/Application Support/typora-user-images/image-20210617181426867.png)

### 启动项目使用钉钉访问服务



### 参考文档

1. 智能工作流权限申请，文档链接：https://developers.dingtalk.com/document/app/logon-free-process?spm=ding_open_doc.document.0.0.6dbdad29INJCRg#topic-2025319
2. 工作流审批表单，文档链接：https://developers.dingtalk.com/document/app/workflow-overview
3. 发起审批实例，文档链接：https://developers.dingtalk.com/document/app/initiate-approval
4. 获取实例详情，文档链接：https://developers.dingtalk.com/document/app/obtains-the-details-of-a-single-approval-instance
5. 事件回调，文档链接：https://developers.dingtalk.com/document/app/callback-overview
