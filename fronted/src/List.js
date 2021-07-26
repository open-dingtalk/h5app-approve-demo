import React from "react";
import axios from "axios";
import {domain} from "./App";
import {Form, Input, Button, Space, Table} from "antd";
import { MinusCircleOutlined, PlusOutlined } from '@ant-design/icons';

// const buttonStyle = {height:'60px',margin: '10px', padding: '10px', fontsize:'18px'};

// class TrData extends React.Component {
//     constructor(props) {
//         super(props);

//     }

//     render() {
//         return (
//             this.props.items.map((item) => {
//                 const name = item.formComponentValues[0].name;
//                 const result = item.result;
//                 const approveUserid = item.approverUserids[0];
//                 return (
//                     <tr className="text-center">
//                         <td>{name}</td>
//                         <td>{result}</td>
//                         <td>{approveUserid}</td>
//                     </tr>
//                 )
//             })
//         )
//     }
// }

class List extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoaded: false,
            auditList:false,
        };
    };

    goodsCollectionAndApprove = (detailForms,textForms) => {
        // 获取存储的用户部门和ID
        const userId = sessionStorage.getItem('userId');
        const deptId = sessionStorage.getItem('deptId');
        // demo直接构建了要请求的数据，实际开发需要从页面获取
        const data = {
            "detailForms": detailForms,
            "originatorUserId": userId,
            "textForms": textForms,
            "deptId": deptId
        };
        // 创建审批流程
        axios({
            url: domain + '/process/instance',
            method: 'post',
            data: data,
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(function (response) {
                alert("success");
                // 根据instanceId获取实例详情
                // axios.get(domain + '/process/instance/' + response.data.data)
                //     .then(response => {
                //         alert(JSON.stringify(response.data))
                //         // console.log(response)
                //     })
                //     .catch(error => {
                //         alert(JSON.stringify(error))
                //         // console.log(error.message)
                //     })
                // console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    // response = {
    //     "success": true,
    //     "errorCode": null,
    //     "errorMsg": null,
    //     "data": [
    //         {
    //             "approverUserids": [
    //                 "043217290519980938"
    //             ],
    //             "attachedProcessInstanceIds": [],
    //             "bizAction": "NONE",
    //             "businessId": "202107261018000327301",
    //             "ccUserids": [
    //                 "043217290519980938"
    //             ],
    //             "createTime": "2021-07-26T02:18:33.000+00:00",
    //             "finishTime": null,
    //             "formComponentValues": [
    //                 {
    //                     "componentType": "TextField",
    //                     "extValue": null,
    //                     "id": "物品用途",
    //                     "name": "物品用途",
    //                     "value": "测试物品领用"
    //                 },
    //                 {
    //                     "componentType": "TableField",
    //                     "extValue": "{\"statValue\":[],\"componentName\":\"TableField\"}",
    //                     "id": "TableField-MINGXI",
    //                     "name": "物品明细",
    //                     "value": "[{\"rowValue\":[{\"componentType\":\"TextField\",\"label\":\"物品名称\",\"value\":\"测试物品领用-电脑\",\"key\":\"物品名称\"},{\"componentType\":\"NumberField\",\"label\":\"数量\",\"value\":\"3\",\"key\":\"数量\"},{\"componentType\":\"DDPhotoField\",\"label\":\"图片\",\"value\":\"[\\\"https://img.alicdn.com/imgextra/i4/O1CN01Y2sejp27k1TCmDENg_!!6000000007834-2-tps-1924-918.png\\\",\\\"https://img.alicdn.com/imgextra/i4/O1CN015zvwqo1WDNSZzfk4O_!!6000000002754-2-tps-2878-1520.png\\\"]\",\"key\":\"DDPhotoField_1D4MDW1RQTZ40\"}]},{\"rowValue\":[{\"componentType\":\"TextField\",\"label\":\"物品名称\",\"value\":\"测试物品领用-电脑1\",\"key\":\"物品名称\"},{\"componentType\":\"NumberField\",\"label\":\"数量\",\"value\":\"2\",\"key\":\"数量\"}]}]"
    //                 },
    //                 {
    //                     "componentType": "TextareaField",
    //                     "extValue": null,
    //                     "id": "领用详情",
    //                     "name": "领用详情",
    //                     "value": "领用详情1"
    //                 },
    //                 {
    //                     "componentType": "DDPhotoField",
    //                     "extValue": null,
    //                     "id": "图片",
    //                     "name": "图片",
    //                     "value": "null"
    //                 }
    //             ],
    //             "mainProcessInstanceId": null,
    //             "operationRecords": [
    //                 {
    //                     "attachments": null,
    //                     "date": "2021-07-26T02:18:32.000+00:00",
    //                     "operationResult": "NONE",
    //                     "operationType": "START_PROCESS_INSTANCE",
    //                     "remark": null,
    //                     "userid": "043217290519980938"
    //                 }
    //             ],
    //             "originatorDeptId": "-1",
    //             "originatorDeptName": "浩倡测试企业申请认证",
    //             "originatorUserid": "043217290519980938",
    //             "result": "",
    //             "status": "RUNNING",
    //             "tasks": [
    //                 {
    //                     "activityId": "b5ebc027-2f09-47b8-9b20-34c7ac4f02a3",
    //                     "createTime": "2021-07-26T02:18:33.000+00:00",
    //                     "finishTime": null,
    //                     "taskResult": "NONE",
    //                     "taskStatus": "RUNNING",
    //                     "taskid": "69699095515",
    //                     "url": "?procInsId=57b2e166-2897-40f5-b71b-698d7cc5ef40&taskId=69699095515&businessId=202107261018000327301",
    //                     "userid": "043217290519980938"
    //                 }
    //             ],
    //             "title": "万志强提交的测试物品领用"
    //         }
    //     ]
    // }

    getTableRowData = () => {
        // 获取审批列表
        axios.get(domain + '/process/instance')
            .then(response => {
                // alert(JSON.stringify(response.data.data))
                let res = {};
                let params = [];
                response.data.map((vl)=>{
                    res = {operationResult:vl.operationRecords[0].operationResult,status:vl.status};
                    let ret = {};
                    vl.formComponentValues.map(it=>{
                        if(it.name==='物品用途'){
                            ret.purpose = it.value;
                        }else if(it.name === '物品明细'){
                            ret.more = JSON.parse(it.value).map(item=>{
                                return `${item.rowValue[0].value}--${item.rowValue[1].value}`
                            });
                        }else if(it.name === '领用详情'){
                            ret.detail = it.value;
                        }
                    })
                    params.push(ret)
                    res.formComponentValues = params
                })
                this.setState(
                    {items: res, isLoaded: true}
                )
            })
            .catch(error => {
                alert(JSON.stringify(error))
            })
                // console.log(error.message)
    };

    onFinish = (values) => {
        console.log(values.add);
        const detailForms = [];
        values.add.map(element => {
            detailForms.push({
                "textForms": [{"name": "物品名称", "value": element.name}, {
                    "name": "数量",
                    "value": element.count
                }], "name": "物品明细"
            });
        });

        const textForms = [{"name": "物品用途","value":values.purpose},{"name":"领用详情","value":values.detail}]

        this.goodsCollectionAndApprove(detailForms,textForms)
        console.log('Success:', values);
      };

    render() {
        return <div style={{padding:'20px'}}>
            {!this.state.auditList&&<Form
            name="basic"
            labelCol={{ span: 8 }}
            wrapperCol={{ span: 16 }}
            initialValues={{ remember: true }}
            onFinish={this.onFinish}
            onFinishFailed={this.onFinishFailed}
            >
                <Form.Item
                    label="物品用途"
                    name="purpose"
                    rules={[{ required: true, message: '请输入物品用途!' }]}
                >
                    <Input placeholder="如：日常办公"/>
                </Form.Item>

                <Form.List name="add" initialValue={[{}]}>
                    {(fields, { add , remove }) => (
                    <>
                        {fields.map(({ key, name, fieldKey, ...restField }) => (
                        <Space key={key} style={{ display: 'flex', marginBottom: 8 }} align="baseline">

                            <Form.Item
                                label="物品名称"
                                name={[name, 'name']}
                                // fieldKey={[fieldKey, 'first']}
                                rules={[{ required: true, message: '请输入物品名称!' }]}
                            >
                                <Input placeholder="请输入物品名称"/>
                            </Form.Item>

                            <Form.Item
                                label="数量"
                                name={[name, 'count']}
                                // fieldKey={[fieldKey, 'last']}
                                rules={[{ required: true, message: '请输入数量!' }]}
                            >
                                <Input placeholder="请输入数量"/>
                            </Form.Item>

                            <MinusCircleOutlined onClick={() => remove(name)} />
                        </Space>
                        ))}
                        <Form.Item>
                        <Button type="dashed" onClick={() => add(1)} block icon={<PlusOutlined />}>
                            增加物品明细
                        </Button>
                        </Form.Item>
                    </>
                    )}
                </Form.List>

                <Form.Item
                    label="领用详情"
                    name="detail"
                    rules={[{ required: true, message: '请输入物品零用详情说明!' }]}
                >
                    <Input placeholder="请输入物品零用详情说明"/>
                </Form.Item>

                <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                    <Button type="primary" htmlType="submit">
                    领用并提交审批
                    </Button>
                </Form.Item>
            </Form>}
            <a onClick={()=>{
                this.setState({auditList:!this.state.auditList})
                this.getTableRowData()
                }} >
                {this.state.auditList?'提交申领审批':'查看审批记录'}
            </a>
            {
                this.state.auditList && <Table columns={this.columns} dataSource={this.state.items.formComponentValues} />
            }
        </div>
        // if (!this.state.isLoaded) {
        //     return (<div>
        //         <button style={buttonStyle} onClick={this.goodsCollectionAndApprove}>领用并提交审批</button>
        //         <button style={buttonStyle} onClick={this.getTableRowData}>获取提交的审批信息</button>
        //     </div>)
        // } else {
        //     return (<div>
        //         <button style={buttonStyle} onClick={this.goodsCollectionAndApprove}>领用并提交审批</button>
        //         <button style={buttonStyle} onClick={this.getTableRowData}>获取提交的审批信息</button>
        //         <table className="table table-bordered">
        //             <thead>
        //             <tr>
        //                 <th>物品用途</th>
        //                 <th>审批状态</th>
        //                 <th>审批人</th>
        //             </tr>
        //             </thead>
        //             <tbody>

        //             <TrData items={this.state.items}/>

        //             </tbody>
        //         </table>
        //     </div>)
        // }
    }

    columns = [
        {
          title: '物品用途',
          dataIndex: 'purpose',
          key: 'purpose',
        },
        {
          title: '物品明细',
          dataIndex: 'more',
          key: 'more',
          render:value=>{
            console.log(value,'=====')
            return value.map(text=>{
                return <p>{text}</p>
            })
          }
        },
        {
          title: '领用详情',
          dataIndex: 'detail',
          key: 'detail',
        }]
}

export default List;
