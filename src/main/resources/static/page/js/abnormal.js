let Abnormal = new Vue({
    el: '#abnormal',
    data: function () {
        return {
            //分页数据
            page:{
                pageSize:5,
                currentPage:1,
                abnormalState:'', orderId:'', abnormalType:'', modifiedUser:''
            },
            //选中行的记录id
            checkedDate:"",
            //搜索输入框
            searchInput:"",
            //异常单数据
            abnormalDate:[],
            goodsInfo:[],
            //默认选中
            selected: 'orderId',
            //下拉框选项
            options: [
                { text: '订单id', value: 'orderId' },
                { text: '异常类型', value: 'abnormalType' },
                { text: '异常状态', value: 'abnormalState' }
            ],
            //搜索条件
            searchDate:{
                abnormalState:'',
                orderId:'',
                abnormalType:'',
                modifiedUser:''
            },

            //模态框数据
            modalData:{
                // goodsInfo:[],
                // abnormalInfo:[],
            },
        }
    },
    created: function () {
        this.initTable();
    },



    methods: {
        //初始化表格
        initTable(){
            let url='/abnormalList';
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
        //异常处理
        abnormalHandle(){
            let url='/abnormalHandle';
            /*console.log(this.checkedDate);*/
            callAxiosGet(url,{abnormalId:this.checkedDate},this.Suc,this.Fail)
        },
        //把当前行id存在缓存中
        /*     toPageStorage(){
                 localStorage.setItem("goodsId", this.checkedDate);
                 /!*console.log(sessionStorage.getItem("id"))*!/
             },*/

        //异常订单详情
        checkOrder(){
            /* let url='/abnormalDetail';
             callAxiosGet(url,{abnormalId:this.checkedDate},this.detailSuc,this.Fail)*/
            console.log(this.checkedDate);
            document.getElementById('iframeId').src="/abnormalDetail?abnormalId="+this.checkedDate;
        },

        /*根据页码，查询到要显示的数据*/
        to_page(pn) {
            let that=this;
            axios.get('/abnormalList', {
                params: {
                    currentPage:pn,
                    pageSize: that.page.pageSize
                }
            }).then(res => {
                console.log(res);
                that.abnormalDate = res.data.data;
                console.log(that.abnormalDate)
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

        //异常订单列表接口反馈
        getListSuc(res){

            let that=this;
            if(res.status===200){
                that.abnormalDate=res.data;
                console.log(that.abnormalDate);
                //默认选中第一条数据
                that.checkedDate=res.data.list[0].abnormalId;
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
    }
});




