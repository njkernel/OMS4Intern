var Exchange = new Vue({
    el: '#exchange',
    data: function (){
        return {
            //分页
            page:{
                pageSize:5,
                currentPage:1,
                returnId:'',
                returnType:'',
                orderId:'',
                returnState:'',
                modifiedUser:''
            },
            //搜索输入框
            searchInput:"",
            // 选择的checkbox id值
            ids:[],
            // 用于存放所有的页面信息
            returnOrderInfo:[],
            // 用于存放查询退换单详情的orderId
            detailsData:{
                orderId: null
            },
            //用于存放查询退换单详情返回的信息
            detailsInfo : null,
            //默认选择
            selected:'returnId',
            //退货换货
            options: [
                { text: '退货单号', value: 'returnId' },
                { text: '单号类型', value: 'returnType' },
                { text: '订单id', value: 'orderId' },
                { text: '退货状态', value: 'returnState' },
                { text: '修改人', value: 'modifiedUser' }
            ],
            //搜索条件
            searchData:{
                returnId:'',
                returnType:'',
                orderId:'',
                returnState:'',
                modifiedUser:''
            }
        }
    },
    mounted: function(){
        this.initInfo();
    },
    methods:{
        //清空搜索条件
        initialize(){
            this.page.returnId='';
            this.page.returnType='';
            this.page.orderId='';
            this.page.returnState='';
            this.page.modifiedUser='';
            this.searchData.returnId='';
            this.searchData.returnType='';
            this.searchData.orderId='';
            this.searchData.returnState='';
            this.searchData.modifiedUser='';
        },
        //展示所有换货/退货单
        initInfo(){
            var showAllExchanges=this;
            var url="/exchange/showAllExchanges";
            this.ids = [];
            axios.get(url,{params: this.page}).then(function(response) {
                console.log(response.data.data);
                showAllExchanges.returnOrderInfo=response.data.data;
            }).catch(function (err) {
                console.log(err)
            });
        },

        // 刷新当前页面
        refresh(){
            this.initialize();
            this.initInfo();
            location.reload();
        },

        //审核，分流
        toAudit(){
            var toAudit=this;
            var url="/return/checkReturnOrExchange";
            if (toAudit.ids.length === 0){
                alert("您还未选择！");
                return false;
            }
            axios.get(url,{params: {
                    returnId : toAudit.ids + ''
                }}).then(function(response) {
                if (response.status === 200) {
                    alert("批量审核操作成功!不符合状态的将不会进行审核！");
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
            axios.get(url,{params: {
                    returnId : toCancel.ids + ''
                }}).then(function (response) {
                if (response.status === 200) {
                    alert("批量操作成功，不符合状态的将不会被取消!");
                    toCancel.refresh();
                } else if (response.status === 500) {
                    alert("操作失败!");
                }
            }).catch(function (err) {
                console.log(err)
            });
        },
        exchangeDetails(){
            var exchangeDetails=this;
            var url="/exchange/exchangeDetails";
            if (exchangeDetails.ids.length === 0){
                $("#iframe").css({"width":"0","height":"0","border":"0px"});
                alert("您还未选择！");
                return false;
            }
            //根据checkbox绑定的returnId获取对应的orderId
            if (exchangeDetails.ids.length>1){
                alert("只能选择单条信息进行查看！");
                $("#iframe").css({"width":"0","height":"0","border":"0px"});
                return false;
            }
            for (var temp in exchangeDetails.returnOrderInfo.list) {

                if (exchangeDetails.returnOrderInfo.list[temp].returnId === exchangeDetails.ids[0]) {
                    exchangeDetails.detailsData.orderId = exchangeDetails.returnOrderInfo.list[temp].orderId;
                }
            }
            $("#iframe").css({"width":"100%","height":"100%","border":"0px"});
            $("#iframe").attr("src","/index/returnDetails?orderId="+exchangeDetails.detailsData.orderId);
            // console.log("orderId为"+exchangeDetails.detailsData.orderId);
            // axios.get(url,{params: exchangeDetails.detailsData}).then(function (response) {
            //     exchangeDetails.detailsInfo = response.data.data;
            // }).catch(function (err) {
            //     console.log(err)
            // });
        },
        //批量处理
        batch(){
            var _this = this;
            if (this.checkedStatus) {//实现反选
                _this.ids = [];
            }else{//实现全选
                _this.ids = [];
                _this.returnOrderInfo.list.forEach(function(item) {
                    _this.ids.push(item.returnId);
                });
            }
            this.checkedStatus=!this.checkedStatus;
        },
        /*根据页码，查询到要显示的数据*/
        to_page(pn) {
            var that = this;
            axios.get("/exchange/showAllExchanges", {
                params: {
                    currentPage: pn,
                    pageSize: that.page.pageSize
                }
            }).then(function (response) {
                if (response.status === 200) {
                    that.returnOrderInfo = response.data.data;
                    that.ids = [];
                    $("#MyAbnormalModel").attr('disabled',true);
                } else if (response.status === 500) {
                    alert("操作失败!");
                }
            }).catch(function (err) {
                console.log(err)
            })
        },
        //查询事件
        search(){
            if(this.selected==='returnId'){
                var reg = /^\d{1,10}$/;
                this.initialize();
                if (reg.test(this.searchInput)){
                    this.searchData.returnId=this.searchInput;
                }else {
                    alert("输入错误");
                }
            }
            else if(this.selected==='returnType'){
                this.initialize();
                this.searchData.returnType=this.searchInput;
            }
            else if(this.selected==='orderId'){
                this.initialize();
                this.searchData.orderId=this.searchInput;
            }
            else if(this.selected==='returnState'){
                this.initialize();
                this.searchData.returnState=this.searchInput;
            }
            else if(this.selected==='modifiedUser'){
                this.initialize();
                this.searchData.modifiedUser=this.searchInput;
            }

            //查询条件
            this.page.currentPage=1;
            this.page.returnId=this.searchData.returnId;
            this.page.returnType=this.searchData.returnType;
            this.page.orderId=this.searchData.orderId;
            this.page.returnState=this.searchData.returnState;
            this.page.modifiedUser=this.searchData.modifiedUser;
            //初始化
            this.initInfo();
        }
    }
});