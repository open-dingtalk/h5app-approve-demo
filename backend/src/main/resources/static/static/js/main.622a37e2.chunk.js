(this.webpackJsonpfronted=this.webpackJsonpfronted||[]).push([[0],{268:function(e,t,n){"use strict";n.r(t);var o=n(7),s=n.n(o),a=n(27),i=n.n(a),c=(n(32),n(33),n(12)),r=n.n(c),d=n(13),u=n(3),l="http://wanzq.vaiwan.com";d.ready((function(){d.runtime.permission.requestAuthCode({corpId:"ding9f50b15bccd16741",onSuccess:function(e){r.a.get(l+"/login?authCode="+e.code).then((function(e){sessionStorage.setItem("userId",e.data.result.userid),sessionStorage.setItem("deptId",e.data.result.deptIdList[0])})).catch((function(e){alert(JSON.stringify(e))}))},onFail:function(e){alert(JSON.stringify(e))}})}));var p=function(){return Object(u.jsx)("div",{className:"App",children:Object(u.jsx)("header",{className:"App-header",children:Object(u.jsx)("button",{onClick:function(){var e={detailForms:[{textForms:[{name:"\u7269\u54c1\u540d\u79f0",value:"\u6d4b\u8bd5\u7269\u54c1\u9886\u7528-\u7535\u8111"},{name:"\u6570\u91cf",value:"3"}],name:"\u7269\u54c1\u660e\u7ec6"}],originatorUserId:sessionStorage.getItem("userId"),textForms:[{name:"\u7269\u54c1\u7528\u9014",value:"\u6d4b\u8bd5\u7269\u54c1\u9886\u7528"},{name:"\u9886\u7528\u8be6\u60c5",value:"\u9886\u7528\u8be6\u60c51"}],deptId:sessionStorage.getItem("deptId")};r()({url:l+"/process/instance",method:"post",data:e,headers:{"Content-Type":"application/json"}}).then((function(e){console.log(e)})).catch((function(e){console.log(e)}))},children:"\u9886\u7528\u5e76\u63d0\u4ea4\u5ba1\u6279"})})})},f=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,269)).then((function(t){var n=t.getCLS,o=t.getFID,s=t.getFCP,a=t.getLCP,i=t.getTTFB;n(e),o(e),s(e),a(e),i(e)}))};i.a.render(Object(u.jsx)(s.a.StrictMode,{children:Object(u.jsx)(p,{})}),document.getElementById("root")),f()},32:function(e,t,n){},33:function(e,t,n){}},[[268,1,2]]]);
//# sourceMappingURL=main.622a37e2.chunk.js.map