var ExchangeDetails = new Vue({
    el: '#exchangeDetails',
    data: function () {
        return {
            //用于存放查询退换单详情返回的信息
            detailsInfo : null
        }
    },
    created:function () {
        // 注册监听postMessage事件
        var that = this;
        if (window.addEventListener){
            //接收消息
            console.log("!!!!!!!!!!");
            window.addEventListener("detailsInfo",that.messageHandle,false);
        }else if (window.attachEvent){
            window.attachEvent("ondetailsInfo",that.messageHandle);
        }
    },
    methods :{
        messageHandle:function(e){
            console.log("EEEEEEEE");
            console.log(e.data);
            this.detailsInfo=e.data;
        },
    }
});