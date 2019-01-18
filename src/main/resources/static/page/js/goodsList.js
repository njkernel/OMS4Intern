let goodsList = new Vue({
    el: '#goodsList',
    data: function () {
        return {
            //分页数据
            page:{
                pageSize:5,
                currentPage:1,
                goodsName : '',
                goodsCode : ''
            },
            //选中行的记录id
            checkedDate:"",
            //搜索输入框
            searchInput2:"",
            //异常单数据
            goodsListDate:[],
            //默认选中
            selected: 'orderId',
            //下拉框选项
            options2: [
                { text: '商品名称', value: 'goodsName' },
                { text: '商品编码', value: 'goodsCode' },
            ],
            //搜索条件
            searchDate:{
                goodsName:'',
                goodsCode:''
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
            this.page.goodsName='';
            this.page.goodsCode='';
            this.searchDate.goodsName='';
            this.searchDate.goodsCode='';
        },

        /*根据页码，查询到要显示的数据*/
        to_page(pn) {
            let that=this;
            axios.get('/getAllGoods', {
                params: {
                    currentPage:pn,
                    pageSize: that.page.pageSize,
                    goodsName : this.searchDate.goodsName,
                    goodsCode :this.searchDate.goodsCode
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
        searchBtn2(){
            if(this.selected==='orderId'){
                let reg = /^\d{1,10}$/;
                this.initialize();
                if (reg.test(this.searchInput2)){
                    this.searchDate.orderId=this.searchInput2;
                }else {
                    alert("输入错误");
                }
            }
            else if(this.selected==='goodsCode'){
                this.initialize();
                this.searchDate.goodsCode=this.searchInput2;
            }
            else if(this.selected==='goodsName'){
                this.initialize();
                this.searchDate.goodsName=this.searchInput2;
            }

            //查询条件
            this.page.currentPage=1;
            this.page.goodsName=this.searchDate.goodsName;
            this.page.goodsCode=this.searchDate.goodsCode;
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




