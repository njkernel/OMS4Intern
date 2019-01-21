let outputList = new Vue({
    el: '#outputList',
    data: function () {
        return {
            //分页数据
            page:{
                pageSize:5,
                currentPage:1,
                orderId:'', outputCode:'', deliveryCode:''
            },
            //选中行的记录id
            checkedDate:"",
            //搜索输入框
            searchInput:"",
            //出库单数据
            outputDate:[],
            //默认选中
            selected: 'orderId',
            //下拉框选项
            options: [
                { text: '订单号', value: 'orderId' },
                { text: '出库单号', value: 'outputCode' },
                { text: '快递单号', value: 'deliveryCode' }
            ],
            //搜索条件
            searchData:{
                orderId:'',
                outputCode:'',
                deliveryCode:''
            },
        }
    },
    created: function () {
        this.initTable();
    },

    methods: {
        //初始化表格
        initTable(){
            let url='/OutputDetails';
            callAxiosGet(url,this.page,this.getListSuc,this.Fail)
        },
        //初始化
        refresh(){
            this.initialize();
            this.initTable();
        },
        //清空搜索条件
        initialize(){
            this.page.orderId='';
            this.page.outputCode='';
            this.page.deliveryCode='';
            this.searchData.orderId='';
            this.searchData.outputCode='';
            this.searchData.deliveryCode='';
        },


        // 出库订单详情
        viewOutputDetails(){
            document.getElementById('iframeId2').src="/orderDetailsAll?orderId="+this.checkedDate;
        },

        // 确认收货，将已发货状态改为已完成
        confirmReceipt(){
            let url='/confirmReceiptUpdateOrderState';
            callAxiosGet(url,{orderId:this.checkedDate},this.Suc,this.Fail)
        },

        // 根据页码，查询到要显示的数据
        to_page(pn) {
            let that=this;
            axios.get('/OutputDetails', {
                params: {
                    currentPage:pn,
                    pageSize: that.page.pageSize,
                }
            }).then(res => {
                console.log(res);
                that.outputDate = res.data.data;
                console.log(that.outputDate)
            }, err => {
                console(err);
            })
        },

        //查询事件
        search(){
            if(this.selected==='orderId'){
                let reg = /^\d{1,10}$/;
                this.initialize();
                if (reg.test(this.searchInput)){
                    this.searchData.orderId=this.searchInput;
                    console.log(this.searchData.orderId);
                }else {
                    alert("输入错误");
                }
            }
            else if(this.selected==='outputCode'){
                this.initialize();
                this.searchData.outputCode=this.searchInput;
                console.log(this.searchData.outputCode);
            }
            else if(this.selected==='deliveryCode'){
                this.initialize();
                this.searchData.deliveryCode=this.searchInput;
                console.log(this.searchData.deliveryCode);
            }
            else {
                this.initialize();
            }

            //查询条件
            this.page.currentPage=1;
            this.page.orderId=this.searchData.orderId;
            this.page.outputCode=this.searchData.outputCode;
            this.page.deliveryCode=this.searchData.deliveryCode;
            //初始化表格
            this.initTable();
        },

        //出库订单列表接口反馈
        getListSuc(res){
            let that=this;
            if(res.status===200){
                that.outputDate=res.data;
                //默认选中第一条数据
                that.checkedDate=res.data.list[0].orderId;
            }else{
                alert(res.message);
            }
        },

        //出库处理接口反馈
        //接口连通
        Suc(res){
            alert(res.message);
            this.initTable();
        },
        //接口未连通
        Fail(err){
            alert(res.message);
        },
    }
});