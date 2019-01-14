var Exchange = new Vue({
    el: '#exchange',
    data: function (){
        return {
            //分页
            returnData:{
                pageSize:5,
                currentPage:1,
                returnType:''
            },
            // 选择的checkbox id值
            ids:[],
            // 用于存放所有的页面信息
            returnOrderInfo:[],
            // 用于存放查询退换单详情的orderId
            detailsData:{
                orderId: null
            },
            //用于存放查询退换单详情返回的信息
            detailsInfo : null
        }
    },
    mounted: function(){
        this.initInfo();
    },
    methods:{
        //展示所有换货/退货单
        initInfo(){
            var showAllExchanges=this;
            var url="/exchange/showAllExchanges";
            axios.get(url,{params: this.returnData}).then(function(response) {
                showAllExchanges.returnOrderInfo=response.data.data.list;
            }).catch(function (err) {
                console.log(err)
            });
        },

        // 刷新当前页面
        refresh(){
            this.initInfo();
            location.reload();
            alert("刷新成功！");
        },

        //审核，分流
        toAudit(){
            var toAudit=this;
            var url="/exchange/toAudit";
            axios.get(url,{params: this.returnData}).then(function(response) {
            }).catch(function (err) {
                console.log(err)
            });
        },
        toCancel(){
            var cancelIds=this;
            var url="/exchange/toCancel";
            axios.get(url,{params: cancelIds.ids}).then(function (response) {
            }).cache(function (err) {
                console.log(err)
            });
        },
        exchangeDetails(){
            var exchangeDetails=this;
            var url="/exchange/exchangeDetails";
            if (exchangeDetails.ids.length === 0){
                alert("您还未选择！")
                return false;
            }
            //根据checkbox绑定的returnId获取对应的orderId
            if (exchangeDetails.ids.length>1){
                alert("只能选择单条信息进行查看！");
                return false;
            }
            for (var temp in exchangeDetails.returnOrderInfo) {
                if (exchangeDetails.returnOrderInfo[temp].returnId === exchangeDetails.ids[0]) {
                    exchangeDetails.detailsData.orderId = exchangeDetails.returnOrderInfo[temp].orderId;
                }
            }
            // console.log("orderId为"+exchangeDetails.detailsData.orderId);
            axios.get(url,{params: exchangeDetails.detailsData}).then(function (response) {
                exchangeDetails.detailsInfo = response.data.data;
            }).catch(function (err) {
                console.log(err)
            });
        }
    }
});