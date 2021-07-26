import React from "react";
import axios from "axios";
import {domain} from "./App";
import {Form, Input, Button, Space, Table} from "antd";
import { MinusCircleOutlined, PlusOutlined } from '@ant-design/icons';

const buttonStyle = {height:'60px',margin: '10px', padding: '10px', fontsize:'18px'};

class TrData extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            this.props.items.map((item) => {
                const name = item.formComponentValues[0].name;
                const result = item.result;
                const approveUserid = item.approverUserids[0];
                return (
                    <tr className="text-center">
                        <td>{name}</td>
                        <td>{result}</td>
                        <td>{approveUserid}</td>
                    </tr>
                )
            })
        )
    }
}

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

    getTableRowData = () => {
        // 获取审批列表
        axios.get(domain + '/process/instance')
            .then(response => {
                // alert(JSON.stringify(response.data.data))
                console.log(response,'=====')
                let params = [];
                response.data.map((vl)=>{
                    let ret = {operationResult:vl.operationRecords.operationResult,status:vl.status};
                    vl.formComponentValues.map(it=>{
                        let res = {};
                        if(it.name==='物品用途'){
                            res.purpose = it.value;
                        }else if(it.name === '物品明细'){
                            res.more = it.value;
                        }else if(it.name === '领用详情'){
                            res.detail = it.value;
                        }
                        params.push(res)
                    })
                    ret.formComponentValues = params
                })
                this.setState(
                    {items: ret, isLoaded: true}
                )
                console.log(ret,'params',params,'11111======')
            })
            .catch(error => {
                alert(JSON.stringify(error))
                // console.log(error.message)
            })
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
                this.setState({auditList:true})
                this.getTableRowData()
                }} >
                查看审批记录
            </a>
            {
                this.state.auditList && <Table columns={this.columns} dataSource={this.state.items} />
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
          }
        },
        {
          title: '领用详情',
          dataIndex: 'detail',
          key: 'detail',
        }]
}

export default List;
