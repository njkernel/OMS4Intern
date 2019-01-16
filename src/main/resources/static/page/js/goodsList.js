let goodsList = new Vue({
    el: '#goodsList',
    data: function () {
        return {
            //分页数据
            page:{
                pageSize:5,
                pageNum:1,
            },
            //选中行的记录id
            checkedDate:"",
            //搜索输入框
            searchInput:"",
            //异常单数据
            goodsListDate:[],
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

         /*   //模态框数据
            modalData:{
                // goodsInfo:[],
                // abnormalInfo:[],
            },*/
        }
    },
    created: function () {
        this.initTable();
    },



    methods: {
        //初始化表格
        initTable(){
            let url='/getAllGoods';
            callAxiosGet(url,this.page,this.getListSuc,this.Fail);
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

        /*根据页码，查询到要显示的数据*/
        to_page(pn) {
            let that=this;
            axios.get('/getAllGoods', {
                params: {
                    pageNum:pn,
                    pageSize: that.page.pageSize
                }
            }).then(res => {
                console.log(res);
                that.goodsListDate = res.data.data;
                console.log(res)
            }, err => {
                console(err);
            })
        },
        //查询事件
        searchBtn(){
            if(this.selected==='orderId'){
                let reg = /^\d{1,10}$/;
                this.initialize();
                if (reg.test(this.searchInput)){
                    this.searchDate.orderId=this.searchInput;
                }else {
                    alert("输入错误");
                }
            }
            else if(this.selected==='abnormalState'){
                this.initialize();
                this.searchDate.abnormalState=this.searchInput;
            }
            else if(this.selected==='abnormalType'){
                this.initialize();
                this.searchDate.abnormalType=this.searchInput;
            }
            else {
                this.initialize();
                this.searchDate.modifiedUser=this.searchInput;
            }

            //查询条件
            this.page.currentPage=1;
            this.page.orderId=this.searchDate.orderId;
            this.page.abnormalState=this.searchDate.abnormalState;
            this.page.abnormalType=this.searchDate.abnormalType;
            this.page.modifiedUser=this.searchDate.modifiedUser;
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

        getListSuc(res){
        console.log(res);
            let that=this;
            if(res.status===200){
                that.goodsListDate=res.data;
                console.log(that.goodsListDate);
                //默认选中第一条数据
                that.checkedDate=res.data.list[0].abnormalId;
                let url='/abnormalDetail';
                // callAxiosGet(url,{abnormalId:res.data.list[0].abnormalId},that.detailSuc,that.Fail)
            }else{
                alert(res.message);
            }
        },

        //接口反馈
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




