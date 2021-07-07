import React from "react";
import axios from "axios";
import {domain} from "./App";

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
            isLoaded: false
        };
    };

    goodsCollectionAndApprove = () => {
        // 获取存储的用户部门和ID
        const userId = sessionStorage.getItem('userId');
        const deptId = sessionStorage.getItem('deptId');
        // demo直接构建了要请求的数据，实际开发需要从页面获取
        const data = {
            "detailForms": [{
                "textForms": [{"name": "物品名称", "value": "测试物品领用-电脑"}, {
                    "name": "数量",
                    "value": "3"
                }], "name": "物品明细"
            }],
            "originatorUserId": userId,
            "textForms": [{"name": "物品用途", "value": "测试物品领用"}, {"name": "领用详情", "value": "领用详情1"}],
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
                this.setState(
                    {items: response.data.data, isLoaded: true}
                )
            })
            .catch(error => {
                alert(JSON.stringify(error))
                // console.log(error.message)
            })
    };

    render() {
        if (!this.state.isLoaded) {
            return (<div>
                <button style={buttonStyle} onClick={this.goodsCollectionAndApprove}>领用并提交审批</button>
                <button style={buttonStyle} onClick={this.getTableRowData}>获取提交的审批信息</button>
            </div>)
        } else {
            return (<div>
                <button style={buttonStyle} onClick={this.goodsCollectionAndApprove}>领用并提交审批</button>
                <button style={buttonStyle} onClick={this.getTableRowData}>获取提交的审批信息</button>
                <table className="table table-bordered">
                    <thead>
                    <tr>
                        <th>物品用途</th>
                        <th>审批状态</th>
                        <th>审批人</th>
                    </tr>
                    </thead>
                    <tbody>

                    <TrData items={this.state.items}/>

                    </tbody>
                </table>
            </div>)
        }
    }
}

export default List;
