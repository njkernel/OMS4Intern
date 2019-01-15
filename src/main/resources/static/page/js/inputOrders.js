var inputOrders = new Vue({
    el: '#warehouse-in-list',
    data: function () {
        return {
            //分页数据
            page:{
                pageSize:5,
                currentPage:1,
            },
            //选中行的记录id
            checkedDate:"",
            //搜索输入框
            searchInput:"",
            //异常单数据
            inputOrdersDate:[],
            //默认选中
            selected: 'orderId',
            //下拉框选项
            options: [
                { text: '订单id', value: 'orderId' },
                { text: '异常类型', value: 'abnormalType' },
                { text: '异常状态', value: 'abnormalState' },
                { text: '修改人', value: 'modifiedUser' }
            ],
            //搜索条件
            searchDate:{
                abnormalState:'',
                orderId:'',
                abnormalType:'',
                modifiedUser:''
            },


        }
    },
    created: function () {
        this.initTable();
    },



    methods: {
        //初始化表格
        initTable(){
            let url='/return/toInput';
            callAxiosGet(url,this.page,this.getListSuc,this.Fail)
        },
        //初始化
        refresh(){
            this.initialize();
            this.initTable();
        },
        //清空搜索条件
        initialize(){
            this.page.abnormalState='';
            this.page.orderId='';
            this.page.abnormalType='';
            this.page.modifiedUser='';
            this.searchDate.abnormalState='';
            this.searchDate.orderId='';
            this.searchDate.abnormalType='';
            this.searchDate.modifiedUser='';
        },

        detailSuc(res){
            let that=this;
            if(res.status===200){
                that.goodsInfo=res.data.goodsInfo;
                console.log("goodsInfo"+JSON.stringify(that.goodsInfo));
                console.log("inputOrdersDate"+JSON.stringify(that.inputOrdersDate));
            }else{
                alert(res.message);
            }
        },

        //异常订单详情
        checkInputOrder(){
            /* let url='/abnormalDetail';
             callAxiosGet(url,{abnormalId:this.checkedDate},this.detailSuc,this.Fail)*/
            console.log(this.checkedDate);
            document.getElementById('iframeId1').src="/inputDetails?orderId="+this.checkedDate;
            console.log($("#iframeId1").attr("src"));
        },

        //入库单单列表接口反馈
        getListSuc(res){
            console.log(res);
            let that=this;
            if(res.status===200){
                that.inputOrdersDate=res.data;
                console.log(this.inputOrdersDate)
                //默认选中第一条数据

            }else{
                alert(res.message);
            }
        },


        /*根据页码，查询到要显示的数据*/
        to_page(pn) {
            let that=this;
            axios.get('/return/toInput', {
                params: {
                    currentPage:pn,
                    pageSize: that.page.pageSize
                }
            }).then(res => {
                console.log(res);
                that.inputOrdersDate = res.data.data;
                console.log(that.inputOrdersDate)
            }, err => {
                console(err);
            })
        },

        //异常处理接口反馈
        //接口连通
        Suc(res){
            alert(res.message);
            this.initTable();
        },
        //接口未连通
        Fail(err){
            console.log("网络连接错误")
        },
    }
});


