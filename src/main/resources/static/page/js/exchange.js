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
            var url="/return/checkReturnOrExchange";
            if (toAudit.ids.length === 0){
                alert("您还未选择！");
                return false;
            }
            axios.get(url,{params: toAudit.ids}).then(function(response) {
                if (response.status === 200) {
                    alert("操作成功!");
                    toAudit.refresh();
                } else if (response.status === 500) {
                    alert("操作失败!");
                }
            }).catch(function (err) {
                console.log(err)
            });
        },
        toCancel(){
            var toCancel=this;
            var url="/return/returnOrderCancel";
            if (toCancel.ids.length === 0){
                alert("您还未选择！");
                return false;
            }
            axios.get(url,{params: toCancel.ids}).then(function (response) {
                if (response.status === 200) {
                    alert("操作成功!");
                    toCancel.refresh();
                } else if (response.status === 500) {
                    alert("操作失败!");
                }
            }).cache(function (err) {
                console.log(err)
            });
        },
        exchangeDetails(){
            var exchangeDetails=this;
            var url="/exchange/exchangeDetails";
            if (exchangeDetails.ids.length === 0){
                $("#iframe").attr("src","");
                alert("您还未选择！");
                return false;
            }
            //根据checkbox绑定的returnId获取对应的orderId
            if (exchangeDetails.ids.length>1){
                alert("只能选择单条信息进行查看！");
                $("#iframe").attr("src","");
                return false;
            }
            for (var temp in exchangeDetails.returnOrderInfo) {

                if (exchangeDetails.returnOrderInfo[temp].returnId === exchangeDetails.ids[0]) {
                    exchangeDetails.detailsData.orderId = exchangeDetails.returnOrderInfo[temp].orderId;
                }
            }
            $("#iframe").attr("src","/index/returnDetails?orderId="+exchangeDetails.detailsData.orderId);
            // console.log("orderId为"+exchangeDetails.detailsData.orderId);
            // axios.get(url,{params: exchangeDetails.detailsData}).then(function (response) {
            //     exchangeDetails.detailsInfo = response.data.data;
            // }).catch(function (err) {
            //     console.log(err)
            // });
        }
    }
});