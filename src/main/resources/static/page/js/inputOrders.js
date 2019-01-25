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
            checkedDate: 0,
            //搜索输入框
            searchInputIO:"",
            //异常单数据
            inputOrdersDate:[],
            //默认选中
            selected: 'orderId',
            //下拉框选项
            optionsIO: [
                { text: '入库单号', value: 'inputCode' },
                { text: '入库单状态', value: 'inputState' },

            ],
            //搜索条件
            searchDate:{
                inputCode:'',
                inputState:''
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
            location.reload();

        },
        //清空搜索条件
        initialize(){
            this.page.inputCode='';
            this.page.inputState='';

            this.searchDate.inputCode='';
            this.searchDate.inputState='';

        },
        //查询事件
        searchIO(){
            if(this.selected==='orderId'){
                let reg = /^\d{1,10}$/;
                this.initialize();
                if (reg.test(this.searchInput2)){
                    this.searchDate.orderId=this.searchInput2;
                }else {
                    alert("请选择搜索条件");
                }
            }
            else if(this.selected==='inputCode'){
                this.initialize();
                this.searchDate.inputCode=this.searchInputIO;
            }
            else if(this.selected==='inputState'){
                this.initialize();
                this.searchDate.inputState=this.searchInputIO;
            }
            /*else {
                this.initialize();
                this.searchDate.modifiedUser=this.searchInput;
            }*/
            //查询条件
            this.page.currentPage=1;
            this.page.inputCode = this.searchDate.inputCode;
            this.page.inputState = this.searchDate.inputState;
            //初始化表格
            this.initTable();
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



        //入库订单详情
        checkInputOrder(){
            /* let url='/abnormalDetail';
             callAxiosGet(url,{abnormalId:this.checkedDate},this.detailSuc,this.Fail)*/
            console.log(this.checkedDate);
            document.getElementById('iframeId1').src="/inputDetails?orderId="+this.checkedDate;
            console.log($("#iframeId1").attr("src"));
        },

        //入库单单列表接口反馈
        getListSuc(res){
            let that=this;
            if(res.status===200){
                that.inputOrdersDate=res.data;
                //默认选中第一条数据
                that.checkedDate=res.data.list[0].orderId;
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
                    pageSize: that.page.pageSize,
                    inputCode :this.searchDate.inputCode='',
                    inputState : this.searchDate.inputState=''
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


