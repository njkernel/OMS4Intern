function checkAll(){
    if ($(':checkbox').is(':checked')) {
        if($("input:checked").length === 1){
            //选中一个，启用相应按钮
            $("#return").attr('disabled',false);
            $("#exchange").attr('disabled',false);
            $("#MyAbnormalModel").attr('disabled',false);
            $("#outstock").attr('disabled',false);
            $("#checked").attr('disabled',false);
        }
        else {
            $("#return").attr('disabled',true);
            $("#exchange").attr('disabled',true);
            $("#MyAbnormalModel").attr('disabled',true);
            $("#outstock").attr('disabled',true);
            $("#checked").attr('disabled',true);
        }
    }
    else {
        $("#exchange").attr('disabled',true);
        $("#return").attr('disabled',true);
        $("#MyAbnormalModel").attr('disabled',true);
        $("#outstock").attr('disabled',true);
        $("#checked").attr('disabled',true);
    }
}
$(document).on('click',"input[type='checkbox']",function(){
    checkAll(this);
});
let orderList = new Vue({
    el: '#orderList',
    data: function () {
        return {
            //分页数据
            page:{
                pageSize:5,
                currentPage:1,
                orderCode:'',
                receiverName:'',
                orderState:'',
                deliveryCode:'',
            },
            orderId:"",
            orderListDate:[],
            //选中行的记录id
            checkedNames:[],
            //搜索输入框
            searchInput3:"",
            //异常单数据
            goodsInfo:[],
            //默认选中
            selected: 'orderId',
            //下拉框选项
            options3: [
                { text: '收货人', value: 'receiverName' },
                { text: '订单状态', value: 'orderState' },
                { text: '物流单号', value: 'deliveryCode' },
                { text: '订单编码', value: 'orderCode' },
            ],
            //搜索条件
            searchDate:{

                receiverName:'',
                orderState:'',
                deliveryCode:'',
                orderCode:'',
            },
            //yonyongi添加
            //订单商品详情，用于退换货选择数量信息
            orderGoodsInfo : [],
            //用于判断按钮点击事件为退货还是换货
            returnType : '',
            //用于存放订单商品原本的商品数量,用于验证退换货数量异常
            beforeGoodsNum : [],
            flag : true
        }
    },
    created: function () {
        this.initTable();
    },
    methods: {
        //初始化表格
        initTable(){
            this.orderId="";
            this.checkedNames=[];
            let url='/getAllOrder';
            callAxiosGet(url,this.page,this.getListSuc,this.Fail);
        },
        //初始化
        refresh(){
            this.initialize();
            this.initTable();
        },
        //清空搜索条件
        initialize(){

            this.page.receiverName='';
            this.page.orderState='';
            this.page.deliveryCode='';
            this.page.orderCode='';
          /*  */
            this.searchDate.receiverName='';
            this.searchDate.orderState='';
            this.searchDate.deliveryCode='';
            this.searchDate.orderCode='';
        },

        // 路由操作接口 Jay新增 2019/1/16
        updateRoute(){
            var confirm_ = confirm('确认？');
            var arr=[];
            arr=this.checkedNames;
            if(arr.length>0){
                if(confirm_){
                    $.ajax({
                        type:'post',
                        url:'/UpdateOrderIntoWaitOutPut',
                        data:{
                            "id":arr.join(",")},
                        dataType:'JSON',
                        success:function (data) {
                            alert(data.message);
                        }
                    })
                }
            }else {
                alert("请先选择订单");
            }
        },

        // 异常处理
        abnormalHandle(){
            let url='/abnormalHandle';
            callAxiosGet(url,{abnormalId:this.checkedNames[0]},this.Suc,this.Fail)
        },
        // 出库操作，将订单出库 Jay新增 2019/1/16
        outputOrder(){
            $(":checkbox").removeAttr("checked");
            this.orderId = this.checkedNames[0];
            let url = '/Output';
            callAxiosGet(url, {id: this.orderId}, this.Suc, this.Fail)
            checkAll();
        },

        //订单详情
        orderDetails(){
            this.orderId=this.checkedNames[0];
            console.log(this.orderId);
            document.getElementById('iframeId3').src="/orderDetail?orderId="+this.orderId;
        },
        to_page(pn) {
            let that=this;
            axios.get('/getAllOrder', {
                params: {
                    currentPage:pn,
                    pageSize: that.page.pageSize,

                    receiverName : this.searchDate. receiverName,
                    orderState :this.searchDate.orderState,
                    deliveryCode :this.searchDate.deliveryCode,
                    orderCode : this.searchDate.orderCode
                }
            }).then(res => {
                $(':checkbox').removeAttr("checked");
                that.orderId="";
                that.checkedNames=[];
                // console.log(res);
                that.orderListDate = res.data.data;
                checkAll();
            }, err => {
                console(err);
            })
        },
        //查询事件
        searchBtn3(){
            if(this.selected==='orderId'){
                let reg = /^\d{1,10}$/;
                this.initialize();
                if (reg.test(this.searchInput3)){
                    this.searchDate.orderId=this.searchInput3;
                }else {
                    alert("输入错误");
                }
            }

            else if(this.selected==='receiverName'){
                this.initialize();
                this.searchDate.receiverName=this.searchInput3;
            }
            else if(this.selected==='orderState'){
                this.initialize();
                this.searchDate.orderState=this.searchInput3;
            }
            else if(this.selected==='deliveryCode'){
                this.initialize();
                this.searchDate.deliveryCode=this.searchInput3;
            }
            else if(this.selected==='orderCode'){
                this.initialize();
                this.searchDate.orderCode=this.searchInput3;
            }


            //查询条件
            this.page.currentPage=1;

            this.page.receiverName=this.searchDate.receiverName;
            this.page.orderState=this.searchDate.orderState;
            this.page.deliveryCode=this.searchDate.deliveryCode;
            this.page.orderCode=this.searchDate.orderCode;
            //初始化表格
            this.initTable();
        },

        detailSuc(res){
            let that=this;
            if(res.status===200){
                that.goodsInfo=res.data.goodsInfo;
                console.log("goodsInfo"+JSON.stringify(that.goodsInfo));
                console.log("abnormalDate"+JSON.stringify(that.abnormalDate));
            }else{
                alert(res.message);
            }
        },

        //异常订单列表接口反馈
        getListSuc(res){
            let that=this;
            if(res.status===200){
                that.orderListDate=res.data;
                console.log(that.orderListDate);
                //默认选中第一条数据
                that.checkedDate[0]=res.data.list[0].abnormalId;

            }else{
                alert(res.message);
            }
        },

        //异常处理接口反馈
        //接口连通
        Suc(res){
            alert(res.message);
            this.initTable();
        },
        //接口未连通
        Fail(err){

        },
        orderCheck(){
            if (this.checkedNames.length===0){
                alert("请先选择订单");
                return null
            }
            if (this.checkedNames.length===1){
                this.orderId=this.checkedNames[0];
                let url='/orderCheck?orderId='+this.orderId;
                callAxiosGetNoParam(url,this.orderCheckSuc,this.orderCheckFail);
            }
            else{
                alert("当前只支持单笔订单预检");
                return null;
            }


        },
        orderCheckSuc(res){
            alert(res.message);
            this.initTable()
        },
        orderCheckFail(res){
            alert("请先选择订单")
        },
        //yonyong添加
        //请求订单相关的商品详情
        toRequestForGoods (){
            var toRequestForGoods = this;
            var url = "getGoodsList";
            //获取触发该方法的按钮所属类别 return/exchange
            var e = window.event;
            var target = e.target||e.srcElement;
            var eventName = target.getAttribute("id");
            toRequestForGoods.beforeGoodsNum=[];
            if ("return" === eventName){
                toRequestForGoods.returnType = "return";
            }else if ("exchange" === eventName){
                toRequestForGoods.returnType = "exchange";
            }
            if (toRequestForGoods.checkedNames.length === 0) {
                alert("请选择一条订单！");
                return false;
            }
            else if (toRequestForGoods.checkedNames.length > 1) {
                alert("只能对一条订单进行操作！");
                return false;
            }
            toRequestForGoods.orderId = toRequestForGoods.checkedNames[0];
            axios.get(url,{params: {orderId : toRequestForGoods.orderId}}).then(function(response) {
                toRequestForGoods.orderGoodsInfo=response.data.data;
                for (var t = 0;t<toRequestForGoods.orderGoodsInfo.length;t++) {
                    toRequestForGoods.$set(toRequestForGoods.orderGoodsInfo[t][0],"goodNum",0);
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
        countDown (index){
            this.orderGoodsInfo[index][0].goodNum--
        },
        countUp (index){
            this.orderGoodsInfo[index][0].goodNum++
        },
        // 生成换货单
        exchangeReturn (){
            var exchangeReturn = this;
            this.orderId=this.checkedNames[0];
            var url = null;
            var data = null;
            exchangeReturn.flag = true;
            if(!exchangeReturn.checkExchangeReturnIsNull(exchangeReturn.getExchangeReturnGoodsNum(exchangeReturn.orderGoodsInfo))){
                alert("没有选择任何商品！");
                return false;
            }
            exchangeReturn.checkExchangeReturnIsOverFlow(exchangeReturn.getExchangeReturnGoodsNum(exchangeReturn.orderGoodsInfo));

            setTimeout(function () {
                console.log(!exchangeReturn.flag);
                if (!exchangeReturn.flag){
                    return false;
                }
                var goodIds = [];
                var goodNums = [];
                for (var i in exchangeReturn.getExchangeReturnGoodsNum(exchangeReturn.orderGoodsInfo)) {
                    if (exchangeReturn.getExchangeReturnGoodsNum(exchangeReturn.orderGoodsInfo)[i] > 0) {
                        goodIds [i] = exchangeReturn.getExchangeReturnGoodsId(exchangeReturn.orderGoodsInfo)[i];
                        goodNums [i] = exchangeReturn.getExchangeReturnGoodsNum(exchangeReturn.orderGoodsInfo)[i];
                    }
                }
                console.log(exchangeReturn.ArrayFormat(goodIds));
                console.log(exchangeReturn.ArrayFormat(goodNums));
                if ("return" === exchangeReturn.returnType){
                    url = 'return/addReturnOrder';
                    data = {
                        orderId : exchangeReturn.orderId,
                        goodsId : exchangeReturn.ArrayFormat(goodIds)+"",
                        number : exchangeReturn.ArrayFormat(goodNums)+""
                    };
                }else if ("exchange" === exchangeReturn.returnType){
                    url = 'exchange/toGenerateExchangeOrder';
                    data = {
                        orderId : exchangeReturn.orderId,
                        goodId : goodIds+"",
                        num : goodNums+""
                    };
                }
                axios.get(url,{params: data}).then(function(response) {
                    if (response.data.status == "401"){
                        alert("订单已经有过退换货记录，不能进行退换操作!");
                        return false;
                    }
                    if (response.data.status == "402"){
                        alert("只能对已完成的订单操作!");
                        return false;
                    }
                    alert("操作成功！");
                }).catch(function (err) {
                    alert("操作失败！");
                });
            },3000)
        },
        //获取退换货的商品id
        getExchangeReturnGoodsId(res) {
            var t = [];
            for (var i = 0; i<res.length;i++){
                t[i] = res [i][0].goodsId;
            }
            return t;
        },
        //获取退换货的数量
        getExchangeReturnGoodsNum(res){
            var t = [];
            for (var i = 0; i<res.length;i++){
                t[i] = res [i][0].goodNum;
            }
            return t;
        },
        //选择换货退货时退货换货数量为0
        checkExchangeReturnIsNull(goodsNum){
            for (var i = 0; i<goodsNum.length;i++){
                if (goodsNum[i] != 0){
                    return true;
                }
            }
            return false;
        },
        //检查选择换货退货数量是否超过原本的数量
        checkExchangeReturnIsOverFlow(goodsNum){
            var checkExchangeReturnIsOverFlow = this;
            var url = "exchange/getOrderGoodsNumsDetails";
            axios.get(url,{params: {orderId : checkExchangeReturnIsOverFlow.orderId}}).then(function(response) {
                var temp = response.data.data;
                for (var i = 0;i<temp.length;i++){
                    checkExchangeReturnIsOverFlow.beforeGoodsNum[i] = temp[i].num;
                }
                for (var p = 0; p<checkExchangeReturnIsOverFlow.beforeGoodsNum.length;p++){
                    console.log("原来的"+checkExchangeReturnIsOverFlow.beforeGoodsNum[p]);
                    console.log("现在的"+goodsNum[p]);
                    if (checkExchangeReturnIsOverFlow.beforeGoodsNum[p]<goodsNum[p]){
                        alert("异常！商品数量已超过原订单数量！");
                        checkExchangeReturnIsOverFlow.flag = false;
                    }
                }
                return true;
            }).catch(function (err) {
                checkExchangeReturnIsOverFlow.flag = false;
            });
            return true;
        },
        //退换货商品数组格式化
        ArrayFormat(res){
            for (var t = 0;t < res.length;t++){
                if (res[t]==null){
                    res[t] = 0;
                }
            }
            return res;
        },
        //刷新页面
        refreshPage(){
            location.reload();
        }
    }
});

