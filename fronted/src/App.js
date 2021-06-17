import './App.css';
import axios from 'axios';
import * as dd from 'dingtalk-jsapi';

//内网穿透工具介绍:
// https://developers.dingtalk.com/document/resourcedownload/http-intranet-penetration?pnamespace=app
// 替换成后端服务域名
const domain = "http://wanzq.vaiwan.com";
function App() {
    const goodsCollectionAndApprove = () => {
        // 获取存储的用户部门和ID
        const userId = sessionStorage.getItem('userId');
        const deptId = sessionStorage.getItem('deptId');
        // demo直接构建了要请求的数据，实际开发需要从页面获取
        const data = {"detailForms":[{"textForms":[{"name":"物品名称","value":"测试物品领用-电脑"},{"name":"数量","value":"3"}],"name":"物品明细"}],"originatorUserId":userId,"textForms":[{"name":"物品用途","value":"测试物品领用"},{"name":"领用详情","value":"领用详情1"}],"deptId":deptId};
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
                alert(JSON.stringify(response.data));
                // 根据instanceId获取实例详情
                axios.get(domain + '/process/instance/' + response.data.result)
                    .then(response => {
                        alert(JSON.stringify(response.data))
                        // console.log(response)
                    })
                    .catch(error => {
                        alert(JSON.stringify(error))
                        // console.log(error.message)
                    })
                // console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    };
    return (
        <div className="App">
            <header className="App-header">
                <button onClick={goodsCollectionAndApprove}>领用并提交审批</button>
            </header>
        </div>
    );
};


dd.ready(function() {
  // dd.ready参数为回调函数，在环境准备就绪时触发，jsapi的调用需要保证在该回调函数触发后调用，否则无效。
  dd.runtime.permission.requestAuthCode({
    corpId: "ding9f50b15bccd16741", //三方企业ID
    onSuccess: function(result) {
      // alert(JSON.stringify(result));
      axios.get(domain + "/login?authCode=" + result.code)
          .then(response => {
              // alert(JSON.stringify(response));
              // alert(JSON.stringify(response.data));
              // alert(JSON.stringify(response.data.result.userid));
              // alert(JSON.stringify(response.data.result.deptIdList[0]));
              // 登录成功后储存用户部门和ID
              sessionStorage.setItem("userId", response.data.result.userid);
              sessionStorage.setItem("deptId", response.data.result.deptIdList[0]);
          })
          .catch(error => {
            alert(JSON.stringify(error))
            // console.log(error.message)
          })

    },
    onFail : function(err) {
      alert(JSON.stringify(err))
    }
  });
});

export default App;
